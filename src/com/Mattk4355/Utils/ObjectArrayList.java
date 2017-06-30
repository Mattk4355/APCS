package com.Mattk4355.Utils;

import java.util.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class ObjectArrayList<E> implements ListUtils<E>, CanClone<ObjectArrayList<E>>, java.io.Serializable{

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * Shared empty array instance used for default sized empty instances. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * first element is added.
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     */
    private transient Object[] elementData;

    /**
     * The size of the ArrayList (the number of elements it contains).
     *
     * @serial
     */
    private int size;

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public ObjectArrayList(){
        elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
    public ObjectArrayList(int initialCapacity){
        if (initialCapacity > 0){
            elementData = new Object[initialCapacity];
        }
        else if (initialCapacity == 0){
            elementData = EMPTY_ELEMENTDATA;
        }
        else{
            throw new IllegalArgumentException("Capacity cannot be less than or equal to zero.");
        }
    }
    public ObjectArrayList(ListUtils<? extends E> l){
        Utils.Objects.requireNonNull(l);
        elementData = l.toArray();
        if ((size = elementData.length) != 0){
            // l.toArray might (incorrectly) not return Object[]
            if (elementData.getClass() != Object[].class){
                Utils.Arrays.copyOf(elementData, Object[].class);
            }
        }
        else{
            // replace with empty array.
            elementData = EMPTY_ELEMENTDATA;
        }
    }

    public ObjectArrayList(E[] e){
        Utils.Objects.requireNonNull(e);
        if ((size = e.length) != 0){
            elementData = Utils.Arrays.copyOf(e, Object[].class);
        }
        else{
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public ObjectArrayList(Collection<? extends E> c) {
        Utils.Objects.requireNonNull(c);
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[]
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        }
        else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size(){
        return size;
    }

    /**
     * Trims the capacity of this <tt>ObjectArrayList</tt> instance to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an <tt>ArrayList</tt> instance.
     */
    public void trimToSize() {
        if (size < elementData.length) {
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * Increases the capacity of this <tt>ArrayList</tt> instance, if
     * necessary, to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param   minCapacity   the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                // any size if not default element table
                ? 0
                // larger than default for default empty table. It's already
                // supposed to be at default size.
                : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {

        // overflow-conscious code
        if (minCapacity - elementData.length > 0){
            grow(minCapacity);
        }
    }

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0){
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0){
            newCapacity = hugeCapacity(minCapacity);
        }
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0){// overflow
            throw new OutOfMemoryError();
        }

        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this list contains
     * at least one element <tt>e</tt> such that
     * <tt>(o == null && e == null) || (e.equals(o))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     */
    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o == null && get(i) == null) || (o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @return index of o, or -1 if the list doesn't not contain the specified object.
     */
    public int indexOf(Object o){
        if (o == null) {
            for (int i = 0; i < size(); i++){
                if (elementData[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = 0; i < size(); i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o == null && get(i) == null) || (o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @return index of o, or -1 if the list doesn't not contain the specified object.
     */
    public int lastIndexOf(Object o){
        if (o == null) {
            for (int i = size() - 1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = size() - 1; i >= 0; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * @return an array containing all of the elements in this list in
     *         proper sequence
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size());
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element), with the runtime type
     * specified by the array passed in.
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * @return an array containing all of the elements in this list in
     *         proper sequence with the runtime type specified when the list
     *         was created
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a){
        if (a.length < size()){
            a = (T[]) Arrays.copyOf(elementData, size(), a.getClass());
        }
        else{ //a.length >= size()
            a = (T[]) Arrays.copyOf(elementData, a.length, a.getClass());
        }
        return a;
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index){
        return (E) elementData[index];
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     */
    public E get(int index){
        rangeCheck(index);

        return elementData(index);
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param e element to be stored at the specified position
     * @param index index of the element to replace
     * @return the element previously at the specified position
     */
    public E set(E e, int index){
        checkNotSelf(e);
        E oldVal = elementData(index);
        elementData[index] = e;
        return oldVal;
    }

    /**
    * Appends the specified element to the end of this list.
     *
     * @param e the element to be appended to the end of the list
    */
    public void add(E e){
        checkNotSelf(e);
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param e element to be inserted
     * @param index index at which the specified element is to be inserted
     */
    public void add(E e, int index){
        checkNotSelf(e);
        rangeCheck(index);

        Object[] temp = new Object[size() + 1];
        for (int i = 0; i < size(); i++) {
            if (i < index){
               temp[i] = elementData[i];
            }
            else if (i > index){
                temp[i + 1] = elementData[i];
            }
        }
        temp[index] = e;

        elementData = temp;
        size++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    public E remove(int index){
        rangeCheck(index);

        E oldVal = get(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        return oldVal;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * <tt>i</tt> such that
     * <tt>(o == null && get(i) == null) || (o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     */
    public boolean remove(Object o){
        if (indexOf(o) == -1){
            return false;
        }
        remove(indexOf(o));
        return true;
    }

    /**
     * Removes the last occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.  More formally, removes the element with the highest index
     * <tt>i</tt> such that
     * <tt>(o == null && get(i) == null) || (o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     */
    public boolean removeLast(Object o){
        if (lastIndexOf(o) == -1){
            return false;
        }
        remove(lastIndexOf(o));
        return true;
    }

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    public void clear(){
        // clear to let GC do its work
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }


    /**
     * Checks to see if the element being added is the list itself, and throws an exception if so.
     *
     * @throws IllegalArgumentException if the element is the list itself
     */
    private void checkNotSelf(E e){
        if (e == this){
            throw new IllegalArgumentException("Cannot add a List to itself");
        }
    }
    /**
     * Checks if the given index is in range. If not, throws an appropriate
     * runtime exception.
     *
     * @throws IndexOutOfBoundsException if index is outside of array bounds
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size();
    }

    @Override
    public ObjectArrayList<E> clone(){
        try{
            @SuppressWarnings("unchecked")
            ObjectArrayList<E> x = (ObjectArrayList<E>) super.clone();
            x.elementData = Arrays.copyOf(elementData, size());
            return x;
        }
        catch (CloneNotSupportedException e){
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * Returns a String representation of this ObjectArrayList
     *
     * @return A String representation of this ObjectArrayList
     */
    @Override
    public String toString(){
        trimToSize();
        return Utils.Arrays.toString(elementData);
    }

    /**
     * Tests if the given object is equal to this <code> ObjectArrayList </code> instance.
     * Returns <code> true </code> if it is, <code> false </code> otherwise.
     *
     * @param o the object to be tested
     * @return true if, an only if:
     *         - o.getClass() == ObjectArrayList.class
     *         - o.size() == this.size()
     *         - the values of the list are the same and in the same order.
     *         and false otherwise.
     */
    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (o instanceof ObjectArrayList){
            ObjectArrayList that = (ObjectArrayList) o;
            if (!(this.size() == that.size())){
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!(this.get(i).equals(that.get(i)))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the hash code of this <code> ObjectArrayList </code>
     * @see Object#hashCode()
     *
     * @return the hash code of this <code> ObjectArrayList </code>
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < size; i++) {
            hash += get(i).hashCode();
        }
        return hash / size;
    }

    /**
     * Returns the same hash code for the given object as
     * would be returned by the default method hashCode().
     * @see System#identityHashCode(Object)
     *
     * @return  the hashCode
     */
    public int identityHashCode(){
        return System.identityHashCode(this);
    }

    /**
     * Save the state of the <tt>ArrayList</tt> instance to a stream (that
     * is, serialize it).
     *
     * @serialData The length of the array backing the <tt>ArrayList</tt>
     *             instance is emitted (int), followed by all of its elements
     *             (each an <tt>Object</tt>) in the proper order.
     */
    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException{
        // Write out element count, and any hidden stuff
        s.defaultWriteObject();

        // Write out capacity
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (int i = 0; i < size(); i++) {
            s.writeObject(elementData[i]);
        }
    }

    /**
     * Reconstitute the <tt>ArrayList</tt> instance from a stream (that is,
     * deserialize it).
     */
    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in capacity
        size = s.readInt();

        elementData = new Object[size];

        // Read in all elements in the proper order.
        for (int i = 0; i < size; i++) {
            elementData[i] = s.readObject();
        }
    }
}