package edu.iastate.cs228.hw4;

import java.util.Arrays;

/**
 * @author Tyler Jaacks
 *         <p>
 *         An entry tree class
 *         Add Javadoc comments to each method
 */
public class EntryTree<K, V> {
    /**
     * dummy root node made public for grading
     */
    protected Node root;
    /**
     * prefixlen is the largest index such that the keys in the subarray keyarr
     * from index 0 to index prefixlen - 1 are, respectively, with the nodes on
     * the path from the root to a node. prefixlen is computed by a private
     * method shared by both search() and prefix() to avoid duplication of code.
     */
    protected int prefixlen;
    private Node current;
    private String tree;

    /**
     * Constructs a new EntryTree.
     */
    public EntryTree() {
        root = new Node(null, null);
        current = root;
    }

    /**
     * Returns the value of the entry with a specified key sequence, or null if
     * this tree contains no entry with the key sequence.
     *
     * @param keyarr The key.
     * @return the node.
     */
    public V search(K[] keyarr) {
        Node temp = root;

        if (keyarr == null || keyarr.length == 0) {
            return null;
        }

        for (K k : keyarr) {
            if (k == null) {
                throw new NullPointerException();
            }
        }

        for (int i = 0; i < keyarr.length; i++) {
            temp = findNode(keyarr[i], temp);
        }

        if (temp == null) {
            return null;
        }

        System.out.println(temp.value);

        return temp.value;
    }

    /**
     * The method returns an array of type K[] with the longest prefix of the
     * key sequence specified in keyarr such that the keys in the prefix label
     * the nodes on the path from the root to a node. The length of the returned
     * array is the length of the longest prefix.
     *
     * @param keyarr
     * @return
     */
    public K[] prefix(K[] keyarr) {
        Node temp = root;
        prefixlen = keyarr.length;
        Node parent = temp;

        K[] tempArr = (K[]) new Object[prefixlen];

        if (keyarr == null || keyarr.length == 0) {
            return null;
        }

        for (K k : keyarr) {
            if (k == null) {
                throw new NullPointerException();
            }
        }

        if (search(keyarr) != null) {
            prefixlen = keyarr.length;
            return keyarr;
        } else {
            return prefix(Arrays.copyOf(keyarr, keyarr.length - 1));
        }
    }

    /**
     * The method locates the node P corresponding to the longest prefix of the
     * key sequence specified in keyarr such that the keys in the prefix label
     * the nodes on the path from the root to the node. If the length of the
     * prefix is equal to the length of keyarr, then the method places aValue at
     * the node P and returns true. Otherwise, the method creates a new path of
     * nodes (starting at a node S) labelled by the corresponding suffix for the
     * prefix, connects the prefix path and suffix path together by making the
     * node S a child of the node P, and returns true.
     *
     * @param keyarr
     * @param aValue
     * @return
     */
    public boolean add(K[] keyarr, V aValue) {
        Node nodeToAdd = null;
        current = root;

        if (keyarr == null || keyarr.length == 0 || aValue == null) {
            return false;
        }

        for (K k : keyarr) {
            if (k == null) {
                throw new NullPointerException();
            }
        }

        for (int i = 0; i < keyarr.length; i++) {
            if (i == keyarr.length - 1) {
                nodeToAdd = new Node(keyarr[i], aValue);
            } else {
                nodeToAdd = new Node(keyarr[i], null);
            }

            if (current.child == null) {
                linkNodesVertically(current, nodeToAdd);
                current = current.child;
            } else {
                current = current.child;

                if (!doesNodeExistInRow(keyarr[i], current)) {
                    linkNodesHorizontally(current, nodeToAdd);
                    current = nodeToAdd;
                } else {
                    if (current.key == keyarr[i] && i == keyarr.length - 1) {
                        current.value = aValue;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Link nodes together in the same column.
     *
     * @param parent The parent node that you wish to add.
     * @param child  The new node that you wish to add.
     */
    private void linkNodesVertically(Node parent, Node child) {
        parent.child = child;
        child.parent = parent;
    }

    /**
     * Links nodes to together in the same row.
     *
     * @param left  The parent node that you wish to add.
     * @param right The new node that you wish to add.
     */
    private void linkNodesHorizontally(Node left, Node right) {
        left.next = right;
        right.prev = left;
        right.parent = left.parent;
    }

    /**
     * Recursively searches the tree and returns the node.
     *
     * @param key  key to search for.
     * @param node the node to start the search for.
     * @return the node based on the key.
     */
    private Node findNode(K key, Node node) {
        if (key.equals(node.key)) {
            return node;
        }

        if (node.next != null) {
            return findNode(key, node.next);
        }

        if (node.child != null) {
            return findNode(key, node.child);
        }

        return null;
    }

    /**
     * Checks if the key exists in the row.
     *
     * @param key  key to check for.
     * @param node the node to start with.
     * @return if the node does exist returns true otherwise return false.
     */
    private boolean doesNodeExistInRow(K key, Node node) {
        current = node;

        if (current.key == null) {
            return false;
        }

        while (!current.key.equals(key)) {
            if (current.next == null) {
                return false;
            }

            current = current.next;
        }

        return true;
    }

    /**
     * Removes the entry for a key sequence from this tree and returns its value
     * if it is present. Otherwise, it makes no change to the tree and returns
     * null.
     *
     * @param keyarr
     * @return
     */
    public V remove(K[] keyarr) {
        Node toRemove = root;

        if (keyarr == null || keyarr.length == 0) {
            return null;
        }

        for (K k : keyarr) {
            if (k == null) {
                throw new NullPointerException();
            }
        }

        for (int i = 0; i < keyarr.length; i++) {
            toRemove = findNode(keyarr[i], toRemove);
        }

        if (toRemove.child != null) {
            throw new IllegalStateException();
        } else {
            toRemove.parent.child = toRemove.next;

            if (toRemove.next != null) {
                toRemove.next.prev = null;
            }
        }

        return toRemove.value;
    }

    /**
     * The method prints the tree on the console in the output format shown in
     * an example output file.
     */
    public void showTree() {
        showTreeRec(root, 0);
        System.out.println();
    }

    /**
     * Recursively prints the tree.
     *
     * @param node     the starting node.
     * @param tabCount the starting tab count.
     */
    private void showTreeRec(Node node, int tabCount) {
        for (int i = 0; i < tabCount; i++) {
            System.out.print("\t");
        }

        System.out.println(node.key + "->" + node.value);

        if (node.child != null) {
            showTreeRec(node.child, tabCount += 1);
        }

        while (node != null && node.next != null) {
            node = node.next;

            showTreeRec(node, tabCount);
        }
    }

    /**
     * Coverts the tree to a readable string.
     *
     * @return the tree as a string.
     */
    @Override
    public String toString() {
        String temp = toStringRec(root);
        String substring = temp.substring(4);
        tree = "";
        return substring;
    }

    /**
     * Recurisively gets the tree as a string.
     *
     * @param node the node to start with.
     * @return the tree as a string.
     */
    private String toStringRec(Node node) {
        tree += node.key + "->" + node.value + " ";

        if (node.child != null) {
            toStringRec(node.child);
        }

        while (node != null && node.next != null) {
            node = node.next;

            toStringRec(node);
        }

        return tree;
    }

    protected class Node implements EntryNode<K, V> {
        protected Node child;
        protected Node parent;
        protected Node prev;
        protected Node next;
        protected K key;
        protected V value;

        /**
         * Constructs a new Node with a key and value pair.
         *
         * @param aKey   The key.
         * @param aValue The value.
         */
        public Node(K aKey, V aValue) {
            key = aKey;
            value = aValue;
            child = null;
            parent = null;
            prev = null;
            next = null;
        }

        /**
         * Gets the parent node.
         *
         * @return the parent node.
         */
        @Override
        public EntryNode<K, V> parent() {
            return parent;
        }

        /**
         * Gets the child node.
         *
         * @return the child node.
         */
        @Override
        public EntryNode<K, V> child() {
            return child;
        }

        /**
         * Gets the next node.
         *
         * @return the next node.
         */
        @Override
        public EntryNode<K, V> next() {
            return next;
        }

        /**
         * Gets the prev node.
         *
         * @return the prev node.
         */
        @Override
        public EntryNode<K, V> prev() {
            return prev;
        }

        /**
         * Gets the key node.
         *
         * @return the key node.
         */
        @Override
        public K key() {
            return key;
        }

        /**
         * Gets the value node.
         *
         * @return the value node.
         */
        @Override
        public V value() {
            return value;
        }
    }
}