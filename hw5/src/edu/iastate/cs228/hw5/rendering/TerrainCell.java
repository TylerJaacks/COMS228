package edu.iastate.cs228.hw5.rendering;

import edu.iastate.cs228.hw5.mapStructures.Coordinate;
import edu.iastate.cs228.hw5.mapStructures.TerrainType;

public class TerrainCell
        implements BoardCell, Cloneable {

    TerrainType terrain;
    Coordinate coord;

    public TerrainCell(TerrainType type) {
        terrain = type;
    }

    public TerrainType getTerrain() {
        return terrain;
    }

    public void setTerrain(TerrainType type) {
        terrain = type;
    }

    @Override
    public int getHexX() {
        return (coord == null) ? -1 : coord.getHexX();
    }

    @Override
    public void setHexX(int x) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public int getHexY() {
        return (coord == null) ? -1 : coord.getHexY();
    }

    @Override
    public void setHexY(int y) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public TerrainCell clone() {
        TerrainCell rval = null;
        try {
            Object temp = super.clone();
            if (!(temp instanceof TerrainCell)) {
                return null;
            }
            coord = null;
            rval = (TerrainCell) temp;
        } catch (CloneNotSupportedException e) {
            throw new Error();
        }
        return rval;
    }

    @Override
    public Coordinate getCoordinates() {
        return coord;
    }

    @Override
    public void setCoordinate(Coordinate ycx) {
        coord = ycx;
    }

    @Override
    public void setCoordinate(int index) {
        coord = new Coordinate(index);
    }


}
