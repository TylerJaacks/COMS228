package edu.iastate.cs228.hw3;

/**
 * @author Tyler Jaacks
 * <p>
 * An implementation of List<E> based on a doubly-linked list with an array for indexed reads/writes
 */

import java.lang.reflect.Array;
import java.util.*;

public class AdaptiveList<E> implements List<E> {
    public ListNode head;
    public ListNode tail;
    public E[] theArray;
    private int numItems;
    private boolean linkedUTD;
    private boolean arrayUTD;

    /**
     * Constructs a new AdpativeLinkedList.
     */
    public AdaptiveList() {
        clear();
    }

    /**
     * Constructs a new linked list given a collection.
     *
     * @param c
     */
    public AdaptiveList(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Null collection given.");
        }

        clear();

        for (E e : c) {
            add(e);
        }
    }

    private void checkIndex(int pos) {
        if (pos >= numItems || pos < 0) {
            throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + numItems);
        }
    }

    private void checkIndex2(int pos) {
        if (pos > numItems || pos < 0) {
            throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + numItems);
        }
    }

    private void checkNode(ListNode cur) {
        if (cur == null || cur == tail) {
            throw new RuntimeException("numItems: " + numItems + " is too large");
        }
    }

    /**
     * Finds a node based on a given index.
     *
     * @param pos Index of the list.
     * @return The node of the given index.
     */
    private ListNode findNode(int pos) {
        ListNode cur = head;

        for (int i = 0; i < pos; i++) {
            checkNode(cur);
            cur = cur.link;
        }

        checkNode(cur);

        return cur;
    }

    /**
     * Checks whether or not the linked list is up-to-date.
     *
     * @return Returns true if the linked list is up-to-date.
     */
    public boolean getlinkedUTD() {
        return linkedUTD;
    }

    /**
     * Checks whether or not the array is up-to-date.
     *
     * @return Returns true if the array list is up-to-date.
     */
    public boolean getarrayUTD() {
        return arrayUTD;
    }

    /**
     * Removes the node from the linked list.
     * This method should be used to remove a node from the linked list.
     *
     * @param toRemove Node to remove.
     */
    private void unlink(ListNode toRemove) {
        if (toRemove == head || toRemove == tail) {
            throw new RuntimeException("An attempt to remove head or tail");
        }

        toRemove.prev.link = toRemove.link;
        toRemove.link.prev = toRemove.prev;
    }

    /**
     * Inserts new node toAdd right after old node current.
     * This method should be used to add a node to the linked list.
     *
     * @param current The current node.
     * @param toAdd   The node that is to be linked to current node.
     */
    private void link(ListNode current, ListNode toAdd) {
        if (current == tail) {
            throw new RuntimeException("An attempt to link after tail");
        }

        if (toAdd == head || toAdd == tail) {
            throw new RuntimeException("An attempt to add head/tail as a new node");
        }

        toAdd.link = current.link;
        toAdd.link.prev = toAdd;

        toAdd.prev = current;
        current.link = toAdd;
    }

    /**
     * Updates the internal array.
     */
    private void updateArray() {
        if (numItems < 0)
            throw new RuntimeException("numItems is negative: " + numItems);

        if (!linkedUTD)
            throw new RuntimeException("linkedUTD is false");

        theArray = (E[]) new Object[size()];
//
//        Iterator iterator = new AdaptiveListIterator();
//        ArrayList<E> arrayList = new ArrayList<E>();
//
//        while (iterator.hasNext()) {
//            for (int i = 0; i < theArray.length; i++) {
//                theArray[i] = (E) iterator.next();
//            }
//        }

//        while (iterator.hasNext()) {
//            Object next = (E) iterator.next();
//
//            if (next != null) {
//                arrayList.add((E) next);
//            }
//        }
//
//        for (E e : arrayList) {
//            System.out.println(e);
//        }

        ListIterator<E> iter = listIterator();

        for (int i = 0; i < numItems; i++)
            theArray[i] = iter.next();

        arrayUTD = true;
        linkedUTD = false;
    }

    /**
     * Updates the AdaptiveList to reflect the internal arrays data.
     */
    private void updateLinked() {
//        if (numItems < 0)
//            throw new RuntimeException("numItems is negative: " + numItems);
//        if (!arrayUTD)
//            throw new RuntimeException("arrayUTD is false");
//
//        if (theArray == null || theArray.length < numItems)
//            throw new RuntimeException("theArray is null or shorter");
//
//        head = new ListNode(null);
//        tail = new ListNode(null);
//        head.link = tail;
//        tail.prev = head;
//        numItems = 0;
//
//        for (int i = 0; i < theArray.length; i++) {
//            link(tail.prev, new ListNode(theArray[i]));
//        }
//
//        linkedUTD = true;
//        arrayUTD = false;
        if (numItems < 0)
            throw new RuntimeException("numItems is negative: " + numItems);
        if (!arrayUTD)
            throw new RuntimeException("arrayUTD is false");

        if (theArray == null || theArray.length < numItems)
            throw new RuntimeException("theArray is null or shorter");

        head.link = tail;
        tail.prev = head;

        for (int i = 0; i < numItems; i++) {
            link(tail.prev, new ListNode(theArray[i]));
        }

        linkedUTD = true;

    }

    public String toStringArray() {
        String eol = System.getProperty("line.separator");
        StringBuilder strb = new StringBuilder();
        strb.append("A sequence of items from the most recent array:" + eol);
        strb.append('[');
        if (theArray != null)
            for (int j = 0; j < theArray.length; ) {
                if (theArray[j] != null)
                    strb.append(theArray[j].toString());
                else
                    strb.append("-");
                j++;
                if (j < theArray.length)
                    strb.append(", ");
            }
        strb.append(']');
        return strb.toString();
    }

    public String toStringLinked() {
        return toStringLinked(null);
    }

    public String toStringLinked(ListIterator<E> iter) {
        int cnt = 0;
        int loc = iter == null ? -1 : iter.nextIndex();

        String eol = System.getProperty("line.separator");
        StringBuilder strb = new StringBuilder();
        strb.append("A sequence of items from the most recent linked list:" + eol);
        strb.append('(');
        for (ListNode cur = head.link; cur != tail; ) {
            if (cur.data != null) {
                if (loc == cnt) {
                    strb.append("| ");
                    loc = -1;
                }
                strb.append(cur.data.toString());
                cnt++;

                if (loc == numItems && cnt == numItems) {
                    strb.append(" |");
                    loc = -1;
                }
            } else
                strb.append("-");

            cur = cur.link;
            if (cur != tail)
                strb.append(", ");
        }
        strb.append(')');
        return strb.toString();
    }

    /**
     * Resizes a given array.
     *
     * @param arr The array to resize.
     * @return The resized array.
     */
    private E[] resizeArray(E[] arr) {
        E[] temp = (E[]) Array.newInstance(arr.getClass().getComponentType(), arr.length * 2);
        System.arraycopy(arr, 0, temp, 0, arr.length * 2);

        return arr;
    }

    /**
     * Clears the linked list.
     */
    @Override
    public void clear() {
        head = new ListNode(null);
        tail = new ListNode(null);
        head.link = tail;
        tail.prev = head;
        numItems = 0;
        linkedUTD = true;
        arrayUTD = false;
        theArray = null;
    }

    /**
     * Gets the total size of the AdaptiveLinkedList.
     *
     * @return The length of the AdaptiveLinkedList.
     */
    @Override
    public int size() {
        return numItems;
    }

    /**
     * Checks if the AdaptiveLinkedList is empty.
     *
     * @return Weather or not it is empty.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds a new node at the end of the AdaptiveList.
     *
     * @param obj Data to add to Node.
     * @return Returns if the add was successful.
     */
    @Override
    public boolean add(E obj) {
        if (!linkedUTD) {
            updateLinked();
        }

        ListNode node = new ListNode(obj);

        link(tail.prev, node);

        numItems++;

        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list,
     * in the order that they are returned by the specified collection's Iterator.
     *
     * @param c This is the collection containing elements to be added to this list.
     * @return This method returns true if this list changed as a result of the call.
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Null collection given.");
        }

        if (!linkedUTD) {
            updateLinked();
        }

        if (c.isEmpty()) {
            return false;
        }

        for (E e : c) {
            if (e != null) {
                add(e);
            }
        }

        return true;
    }

    @Override
    public boolean remove(Object obj) {
        if (!linkedUTD) {
            updateLinked();
        }

        ListNode prev;
        ListNode cur;

        for (prev = null, cur = head; cur != null; prev = cur, cur = cur.link) {
            if (obj == cur.data || obj != null && obj.equals(cur.data)) {
                break;
            }
        }

        if (cur == null) {
            return false;
        }

        if (prev != null) {
            prev.link = cur.link;
        } else {
            head = head.link;
        }

        numItems--;

        return true;
    }

    @Override
    public void add(int pos, E obj) {
        if (!linkedUTD) {
            updateLinked();
        }

        if (pos < 0 || pos > numItems) {
            throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + size());
        }

        ListNode newNode = new ListNode(obj);
        ListNode cur = findNode(pos);

        if (pos == 0) {
            link(head, newNode);
            numItems++;

            return;
        }

        link(cur, newNode);

        numItems++;
    }

    @Override
    public boolean addAll(int pos, Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Null collection given.");
        }

        if (!linkedUTD) {
            updateLinked();
        }

        AdaptiveListIterator iterator = new AdaptiveListIterator(pos);

        for (int i = 0; i <= pos; i++) {
            iterator.next();
        }

        for (E e : c) {
            iterator.add(e);
        }

        return true;
    }

    @Override
    public E remove(int pos) {
        Iterator<E> iterator = new AdaptiveListIterator();
        E removed = null;

        if (pos > numItems - 1) {
            throw new IndexOutOfBoundsException("Index: " + pos + ", Size: " + size());
        }

        for (int i = 0; i <= pos; i++) {
            removed = iterator.next();
        }

        iterator.remove();

        return removed;
    }

    /**
     * Gets a value at a specific index.
     *
     * @param pos the index.
     * @return the value at a specific position.
     */
    @Override
    public E get(int pos) {
        if (!arrayUTD) {
            updateArray();
        }

        if (pos > theArray.length || pos < 0) {
            throw new IndexOutOfBoundsException("Index: " + pos + ", " + "Size: " + size());
        }

        return theArray[pos];
    }

    /**
     * Sets the Data of a the AdaptiveList at a given position.
     *
     * @param pos the position to replace the data.
     * @param obj the new data.
     * @return the new data.
     */
    @Override
    public E set(int pos, E obj) {
        if (!arrayUTD) {
            updateArray();
        }

        if (pos > theArray.length || pos < 0) {
            throw new IndexOutOfBoundsException("Index: " + pos + ", " + "Size: " + size());
        }

        E temp = theArray[pos];

        theArray[pos] = obj;

        //updateLinked();

        return temp;
    }

    /**
     * Checks if the object is contained in the AdaptiveList.
     *
     * @param obj The object to check.
     * @return true if the object is in the AdaptiveList and false if it is not.
     */
    @Override
    public boolean contains(Object obj) {
        if (!linkedUTD) {
            updateLinked();
        }

        ListNode cur;

        if (isEmpty()) {

        }

        for (cur = head; cur != null; cur = cur.link) {
            if (obj == cur.data || cur != null && cur.equals(obj)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     *
     * @param c collection to be checked for containment in this list.
     * @return true if this list contains all of the elements of the specified collection.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator iterator = new AdaptiveListIterator();

        // TODO Check Into This
        for (Object e : c) {
            if (!c.contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int indexOf(Object obj) {
        // TODO This method does not work, I think?
        Iterator<E> iterator = new AdaptiveListIterator();

        if (!linkedUTD) {
            updateLinked();
        }

        if (!arrayUTD) {
            updateArray();
        }

        for (int i = 0; i < size(); i++) {
            if (theArray[i].equals(obj)) {
                return i;
            }
        }

        ListNode cur;
        int pos = 0;
        for (cur = head; cur != null; cur = cur.link, pos++)
            if (obj == cur.data || obj != null && obj.equals(cur.data))
                return pos;

        return -1;
    }

    @Override
    public int lastIndexOf(Object obj) {
        // TODO This method does not work, I think?
        AdaptiveListIterator iterator = new AdaptiveListIterator(size());
        int index = -1;

        if (isEmpty()) {
            return -1;
        }

        while (iterator.cur.data != obj) {
            index = indexOf(iterator.previous());
        }

        return index;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Null collection given.");
        }

        Iterator<E> iterator = new AdaptiveListIterator();
        boolean changed = false;

        while (iterator.hasNext()) {
            E next = iterator.next();

            if (c.contains(next)) {
                remove(next);
                changed = true;
            }
        }

        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Null collection given.");
        }

        Iterator<E> iterator = new AdaptiveListIterator();
        boolean changed = false;

        while (iterator.hasNext()) {
            E next = iterator.next();

            if (!c.contains(next)) {
                remove(next);
                changed = true;
            }
        }

        return changed;
    }

    /**
     * Converts a AdaptiveList to Array.
     *
     * @return Returns the Array representation of the AdaptiveList.
     */
    @Override
    public Object[] toArray() {
        if (!linkedUTD) {
            updateLinked();
        }

        Object[] array = new Object[numItems];
        Iterator<E> iterator = new AdaptiveListIterator();

        for (int i = 0; i < numItems; i++) {
            array[i] = iterator.next();
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] arr) {
        if (!arrayUTD) {
            updateArray();
        }

        if (arr.length < theArray.length) {
            arr = Arrays.copyOf(arr, theArray.length);
        }

        System.arraycopy(theArray, 0, arr, 0, arr.length);

        if (arr.length > theArray.length) {
            arr[theArray.length] = null;
        }

        return arr;
    }

    @Override
    public List<E> subList(int fromPos, int toPos) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns if the Object is equal.
     *
     * @param obj The object to compare.
     * @return Returns if the object is equal or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (!linkedUTD) updateLinked();

        if ((obj == null) || !(obj instanceof List<?>))
            return false;

        List<?> list = (List<?>) obj;

        if (list.size() != numItems) return false;

        Iterator<?> iter = list.iterator();

        for (ListNode tmp = head.link; tmp != tail; tmp = tmp.link) {
            if (!iter.hasNext()) return false;
            Object t = iter.next();
            if (!(t == tmp.data || t != null && t.equals(tmp.data)))
                return false;
        }

        if (iter.hasNext()) return false;

        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new AdaptiveListIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new AdaptiveListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int pos) {
        checkIndex2(pos);
        return new AdaptiveListIterator(pos);
    }

    /**
     * Something, something, HashCode?
     *
     * @return HashCode Duh!
     */
    @Override
    public int hashCode() {
        if (!linkedUTD) updateLinked();
        int hashCode = 1;
        for (E e : this)
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
    }

    /**
     * Gets the string representation of the AdaptiveList.
     *
     * @return String version of the AdaptiveList.
     */
    @Override
    public String toString() {
        String eol = System.getProperty("line.separator");
        return toStringArray() + eol + toStringLinked();
    }

    // TODO Set this to private.
    public class ListNode {
        public E data;
        public ListNode link;
        public ListNode prev;

        /**
         * Constructs a new ListNode.
         *
         * @param item Data to add to the ListNode.
         */
        public ListNode(E item) {
            data = item;
            link = prev = null;
        }
    }

    private class AdaptiveListIterator implements ListIterator<E> {
        private int index;
        private ListNode cur;
        private ListNode last;

        /**
         * Constructs a new AdaptiveListIterator.
         */
        public AdaptiveListIterator() {
            if (!linkedUTD) updateLinked();

            cur = head.link;
        }

        /**
         * Constructs a new AdaptiveListIterator with a position.
         *
         * @param pos a position to construct the AdaptiveListIterator.
         */
        public AdaptiveListIterator(int pos) {
            Iterator<E> iterator = new AdaptiveListIterator();

            if (!linkedUTD) {
                updateLinked();
            }

            checkIndex2(pos);
            index = pos;
            last = null;
            cur = findNode(pos);
            cur = cur.link;
        }

        /**
         * Checks if there is a next node in the AdaptiveList.
         *
         * @return Returns if there is a next node in the list.
         */
        @Override
        public boolean hasNext() {
            return cur != tail;
        }

        /**
         * Checks if there is a previous node in the AdaptiveList.
         *
         * @return Returns if there is a previous node in the list.
         */
        @Override
        public boolean hasPrevious() {
            return cur.prev != head;
        }

        /**
         * Returns the next element in the list and advances the cursor position.
         *
         * @return the next element in the list
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            last = cur;

            E temp = cur.data;

            cur = cur.link;

            index++;

            return temp;
        }

        /**
         * Returns the previous element in the list and moves the cursor position backwards.
         *
         * @return the previous element in the list
         */
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("No more elements in the list");
            }

            cur = cur.prev;
            last = cur;

            E temp = cur.data;

            index--;

            return temp;
        }

        /**
         * Gets the next index.
         *
         * @return The next index.
         */
        @Override
        public int nextIndex() {
            return index;
        }

        /**
         * Gets the previous index.
         *
         * @return The previous index.
         */
        @Override
        public int previousIndex() {
            return index - 1;
        }

        /**
         * Removes from the list the last element.
         */
        public void remove() {
            if (last == null) {
                throw new IllegalStateException("Neither next nor previous have been called");
            }

//            if (cur != last) {
            //     cur = cur.link;
            //          } else {
            //            index++;
            //      }

            unlink(last);

            numItems--;
        }

        /**
         * Inserts the specified element into the list.
         *
         * @param obj the element to insert.
         */
        public void add(E obj) {
            ListNode newNode = new ListNode(obj);

            link(last, newNode);

            index++;

            numItems++;
        }

        /**
         * Replaces the last element returned by next() or previous() with the specified element (optional operation).
         *
         * @param obj the element with which to replace the last element returned by next or previous
         */
        @Override
        public void set(E obj) {
            if (last == null) {
                throw new IllegalStateException("Neither next nor previous have been called");
            }

            last.data = obj;
        }
    }
}