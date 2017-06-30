package com.Mattk4355.Utils;

import java.util.*;

/**
 * The class NoDuplicateList is a specialization of a {@linkplain ArrayList}, such that it conforms
 * to the {@linkplain Set} interface, i.e. only one of any given element (or null).
 *
 * @param <E> the type of elements in this List
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class NoDuplicateList<E> extends ArrayList<E> implements Set<E> {
    public NoDuplicateList(){
        super();
    }
    public NoDuplicateList(int size){
        super(size);
    }
    public NoDuplicateList(Collection<? extends E> c){
        super();
        this.addAll(c);
    }
    @Override
    public boolean add(E e){
        checkIfContains(e);
        return super.add(e);
    }
    @Override
    public boolean addAll(Collection<? extends E> c){
        Utils.Objects.requireNonNull(c, "Collection cannot be null");
        boolean modified = false;
        for (E e : c){
            modified |= this.add(e);
        }
        return modified;
    }
    @Override
    public E set(int index, E e) {
        checkIfContains(e);
        return super.set(index, e);
    }
    @Override
    public void add(int index, E e){
        checkIfContains(e);
        super.add(index, e);
    }
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Utils.Objects.requireNonNull(c, "Collection cannot be null");
        for (E e : c){
            this.add(index++, e);
        }
        return c.size() != 0;
    }
    private void checkIfContains(E e){
        if (this.contains(e)) {
            throw new IllegalArgumentException("This list already contains the element: " +
                    (e != null ? e.toString() : "null"));
        }
    }
}
