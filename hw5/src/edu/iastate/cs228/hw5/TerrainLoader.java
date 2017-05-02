package edu.iastate.cs228.hw5;

import edu.iastate.cs228.hw5.mapStructures.*;
import edu.iastate.cs228.hw5.rendering.TerrainGraph;
import edu.iastate.cs228.hw5.util.NoiseFilterReader;
import edu.iastate.cs228.hw5.util.TerrainScanner;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TerrainLoader {
    private static final String kWaterError = "Unable to create water.";
    private static final String kFlagError = "Unable to create flag.";
    private static final String kBrushError = "Unable to create brush";
    private static final String kForestError = "Unable to create forrest.";
    private static final String kDirtError = "Unable to create dirt.";
    private static final String kHwy2Error = "Unable to create hwy2.";
    private static final String kHwy4Error = "Unable to create hwy2";
    private static final String kRiverError = "Unable to create river.";
    private static final String kWallError = "Unable to create wall.";
    private static final String kDefaultParseError = "Unable to parse file.";
    private static final String kPathError = "A path error has occurred.";

    TerrainGraph tGraph = null;
    TerrainScanner scan = null;
    InputStream inStream;
    Reader rdr = null;

    private String failGeometryMsg = "Terrain Specification file does not start"
            + "with valid geometry section. Cannot initialize" + "terrain map.";

    public TerrainLoader(String textData) {
        rdr = new NoiseFilterReader(new StringReader(textData));
    }

    public TerrainLoader(Reader reader) {
        if (!(reader instanceof NoiseFilterReader)) {
            rdr = new NoiseFilterReader(reader);
        } else {
            rdr = reader;
        }
    }

    public TerrainGraph load() throws ParseErrorException {
        scan = new TerrainScanner(rdr);

        // throws exception if fails to construct "blank" graph.
        tGraph = processGeometry();

        while (scan.hasNext()) {
            processTerrainSpec();
        }
        return tGraph;
    }

    private TerrainGraph processGeometry() throws ParseErrorException {
        scan.next("geometry");
        if (!scan.hasNextInt())
            throw new NoSuchElementException("geometry must include board dimentions");
        int cols = scan.nextInt();
        if (!scan.hasNextInt())
            throw new NoSuchElementException("geometry must include board dimentions");
        int rows = scan.nextInt();
        Coordinate.setGeometry(cols, rows);
        tGraph = new TerrainGraph(cols, rows);
        return tGraph;
    }

    private void processTerrainSpec() throws ParseErrorException {
        String keyword = scan.next().toLowerCase();
        System.out.println("Processing " + keyword);

        switch (keyword) {
            case Feature.kFlags:
                Coordinate[] flags = readTwo(kFlagError);
                tGraph.setFlags(flags[0], flags[1]);
                return;
            case Feature.kWater:
                readCells(TerrainType.kWater, kWaterError);
                return;
            case Feature.kBrush:
                readCells(TerrainType.kBrush, kBrushError);
                return;
            case Feature.kForest:
                readCells(TerrainType.kForest, kForestError);
                return;
            case Feature.kDirt:
                readPath(ConnType.dirt, kDirtError);
                return;
            case Feature.kHwy2:
                readPath(ConnType.hwy2, kHwy2Error);
                return;
            case Feature.kHwy4:
                readPath(ConnType.hwy4, kHwy4Error);
                return;
            case Feature.kRiver:
                readPath(ConnType.river, kRiverError);
                return;
            case Feature.kWall:
                readBorderPath(ConnType.wall, kWallError);
                return;
            case Feature.kPath:
                Path path = scanPath(ConnType.path, kPathError);
                tGraph.setSolution(path);
                return;
            default:
                System.out.println("Failure on keyword: " + keyword);
                throw new ParseErrorException(kDefaultParseError);
        }
    }

    /**
     * collects up one or more border segment (barrier) descriptors and passes
     * them along with the Connector type (there might be different kinds of
     * border elements in the future) to the tGraph. Reports parse elements.
     *
     * @param type the BorderSegment's connector type
     * @param errMsg Text to use in parse error messages
     * @throws ParseErrorException
     */
    private void readBorderPath(ConnType type, String errMsg) throws ParseErrorException {
        try {
            List<BorderSegment> borderSegments = new ArrayList<>();

            while(scan.hasNextBorderSegment()) {
                BorderSegment borderSegment = scan.nextBorderSegment();
                borderSegments.add(borderSegment);
            }

            tGraph.setBorderEdges(borderSegments, type);
        } catch (Exception e) {
            throw new ParseErrorException(errMsg);
        }
    }

    /**
     * This reads the path information that accompanies a Connector description.
     * Once it has read and validated the path, it calls the terrain graph to
     * put the terrain information into the graph.
     * <p>
     * Uses utility function scanPath to do the heavy lifting.
     *
     * @param type   the type of terrain to be created
     * @param errMsg text to go in the parse error message if needed
     * @throws ParseErrorException if it enounters noSuchElement or the
     *                             Coordinates that don't meet the requirements for a path.
     */
    private void readPath(ConnType type, String errMsg) throws ParseErrorException {
        try {
            tGraph.setConnectedPath(scanPath(type, errMsg), type);
        } catch (Exception e) {
            throw new ParseErrorException(errMsg);
        }
    }

    private Path scanPath(ConnType type, String errMsg) throws ParseErrorException {
        if (!scan.hasNextCoord()) {
            String msg = String.format("%s: Coordinate must follow %s specifier",
                    errMsg, type.name());
            throw new NoSuchElementException(msg);
        }
        Coordinate c1 = scan.nextCoord();
        if (!scan.hasNextCoord()) {
            String msg = String.format("%s: %s Path requires at least two coordinates",
                    errMsg, type.name());
            throw new NoSuchElementException(msg);
        }
        try {
            Path path = new Path(c1, scan.nextCoord());
            while (scan.hasNextCoord()) {
                path.add(scan.nextCoord());
            }
            return path;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ParseErrorException(errMsg + "Path must be adjacent elements");
        }
    }

    /**
     * This collects up the coordinate information for a terrain section, and then
     * passes that information (in object form) to the terrain graph.
     *
     * @param terrain    The type of terrain to which these coordinates belong.
     * @param errMessage text to go in the parse error if we have an int left over
     *                   after consuming all coordinate pairs (once hasNextCoord() returns false, ask
     *                   hasNextInt(). Answer should be no.
     * @throws ParseErrorException
     */
    private void readCells(TerrainType terrain, String errMessage) throws ParseErrorException {
        try {
            tGraph.setTerrain(scanCoordList(), terrain);
        } catch (Exception e) {
            throw new ParseErrorException(errMessage);
        }
    }

    /**
     * A utility function that scans as many coordinates as it can. It should
     * never be called unless the syntax requires there be at least one
     * coordinate at this point. If it finds none, it returns an empty list. The
     * caller should consider an empty list a sign there was an error.
     *
     * @return
     */
    private List<Coordinate> scanCoordList() {
        List<Coordinate> coordinateList = new ArrayList<>();

        while (scan.hasNextCoord()) {
            coordinateList.add(scan.nextCoord());
        }

        return coordinateList;
    }

    /**
     * This consumes two Board Coordinates (four integers from the stream
     * and returns two coordinate objects ,
     *
     * @param errMessage text to go in the parse error if we don't find
     *                   two coordinates
     * @return the coordinates.
     */
    private Coordinate[] readTwo(String errMessage) throws ParseErrorException {
        Coordinate[] coordinates = new Coordinate[2];

        try {
            while (scan.hasNextCoord()) {
                coordinates[0] = scan.nextCoord();
                coordinates[1] = scan.nextCoord();
            }
        } catch (Exception e) {
            throw new ParseErrorException(errMessage);
        }

        tGraph.setFlags(coordinates[0], coordinates[1]);

        return coordinates;
    }

    public interface Feature {
        public static final String kGeometry = "geometry";
        public static final String kFlags = "flags";
        public static final String kWater = "water";
        public static final String kBrush = "brush";
        public static final String kForest = "forest";
        public static final String kHwy4 = "dividedhwy";
        public static final String kHwy2 = "hwy";
        public static final String kDirt = "unpaved";
        public static final String kRiver = "river";
        public static final String kWall = "barrierwall";
        public static final String kPath = "testpath";
    }
}