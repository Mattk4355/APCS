package com.Mattk4355.Utils;

public interface ListUtils<E> {
    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this list contains
     * at least one element <tt>e</tt> such that
     * <tt>(o == null && e == null) || (e.equals(o))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     */
    boolean contains(Object o);

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o == null && get(i) == null) || (o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @return index of o, or -1 if the list doesn't not contain the specified object.
     */
    int indexOf(Object o);

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o == null && get(i) == null) || (o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @return index of o, or -1 if the list doesn't not contain the specified object.
     */
    int lastIndexOf(Object o);

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
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element), with the runtime type
     * specified when the list was created
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * @return an array containing all of the elements in this list in
     *         proper sequence with the runtime type specified when the list
     *         was created
     */
    <T> T[] toArray(T[] a);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     */
    E get(int index);

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param e element to be stored at the specified position
     * @param index index of the element to replace
     * @return the element previously at the specified position
     */
    E set(E e, int index);

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e the element to be appended to the end of the list
     */
    void add(E e);

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param e element to be inserted
     * @param index index at which the specified element is to be inserted
     */
    void add(E e, int index);

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    E remove(int index);

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
    boolean remove(Object o);

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
    boolean removeLast(Object o);

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    void clear();
}