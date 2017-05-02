package edu.iastate.cs228.hw5.shared;

import java.util.*;

/**
 * @author Tyler Jaacks
 */

public class PathFinder {

    /**
     * This member always holds the cost of the path (if any)
     * found by the most recently finished solving operation.
     * MIN_VALUE is used to signal that the value is not yet valid.
     */
    public static int lastCost = Integer.MIN_VALUE;

    /**
     * First, computes a shortest path from a source vertex to a destination
     * vertex in a graph by using Dijkstra's algorithm. Second, visits and saves
     * (in a stack) each vertex in the path, in reverse order starting from the
     * destination vertex, by using the map object pred. Third, uses a
     * List and Stack to generate the return Integer List by first pushing
     * each vertex into the stack, and then poping vertices
     * from the stack and adding the index of each to the
     * return list. The vertex indices in the return object are now in the
     * right order. Note that the getIndex() method is called from a
     * BareV object (vertex) to get its original integer name.
     *
     * @param g      - The graph in which a shortest path is to be computed
     * @param source - The first vertex of the shortest path
     * @param dest   - The last vertex of the shortest path
     * @return A List of Integers corresponding the the vertices on the path
     * in order from source to dest.
     * <p>
     * The contents of an example String object: Path Length: 5 Path
     * Cost: 4 Path: 0 4 2 5 7 9
     * @throws NullPointerException - If any arugment is null
     * @throws RuntimeException     - If the given source or dest vertex is not in the graph
     */

    public static List<Integer> findPath(BareG g, BareV source, BareV dest) {
        // the supplied heap, and stack.
        // you may also use HashMap and HashSet from JCF.
        // the following is only here so that the app will run (but not
        // product correct results when first unpacked from the templates.
        lastCost = Integer.MIN_VALUE;

        List<Integer> path = new ArrayList<>();

        HashMap<BareV, Integer> dist = new HashMap<>();
        HashMap<BareV, BareV> pred = new HashMap<>();
        Heap<Vpair<BareV, Integer>> priq = new Heap<>();
        HashSet<BareV> vset = new HashSet<>();

        if (g == null || source == null || dest == null) {
            throw new NullPointerException();
        }

        if (!g.checkVertex(source) || !g.checkVertex(dest)) {
            throw new RuntimeException();
        }

        dist.put(source, 0);
        priq.add(new Vpair<>(source, 0));

        while (!priq.isEmpty()) {
            Vpair<BareV, Integer> pair = priq.removeMin();
            BareV u = pair.getVertex();

            if (!vset.contains(u)) {
                vset.add(u);

                for (BareE tup : u.getBareEdges()) {
                    BareV v = tup.getToVertex();
                    Integer altdist = dist.get(u) + tup.getWeight();
                    Integer vdist = dist.get(v);

                    if (vdist == null || vdist > altdist) {
                        dist.put(v, altdist);
                        pred.put(v, u);
                        priq.add(new Vpair<>(v, altdist));
                    }
                }
            }
        }

        System.out.format("findPath was called with start=%d, dest=%d%n"
                        + "Now you need to give it a real implementation.%n",
                source.getIndex(), dest.getIndex());

        Stack<Vpair<BareV, Integer>> vertices = new Stack<>();
        BareV temp = pred.get(dest);
        vertices.push(new Vpair<>(dest, dist.get(dest)));

        while (temp != null) {
            vertices.push(new Vpair<>(temp, dist.get(temp)));

            temp = pred.get(temp);
        }

        while (!vertices.isEmpty()) {
            Vpair<BareV, Integer> vertexPair = vertices.pop();

            path.add(vertexPair.getVertex().getIndex());
        }

        lastCost = dist.get(dest);

        return path;
    }

    /**
     * A pair class with two components of types V and C, where V is a vertex
     * type and C is a cost type.
     */
    private static class Vpair<V, C extends Comparable<? super C>> implements
            Comparable<Vpair<V, C>> {
        private V node;
        private C cost;

        Vpair(V n, C c) {
            node = n;
            cost = c;
        }

        public V getVertex() {
            return node;
        }

        public C getCost() {
            return cost;
        }

        public int compareTo(Vpair<V, C> other) {
            return cost.compareTo(other.getCost());
        }

        public String toString() {
            return "<" + node.toString() + ", " + cost.toString() + ">";
        }

        public int hashCode() {
            return node.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if ((obj == null) || (obj.getClass() != this.getClass()))
                return false;

            // object must be Vpair at this point
            Vpair<?, ?> test = (Vpair<?, ?>) obj;
            return (node == test.node || (node != null && node
                    .equals(test.node)));
        }
    }
}