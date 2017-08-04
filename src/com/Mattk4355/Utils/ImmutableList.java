package com.Mattk4355.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ImmutableList<T> extends ArrayList<T> {
    public ImmutableList() {
        super();
    }

    public ImmutableList(int initialCapacity) {
        super(initialCapacity);
    }

    public ImmutableList(Collection<? extends T> c) {
        super();
        super.addAll(c);
    }

    public ImmutableList(T[] t){
        //noinspection ManualArrayToCollectionCopy
        for (T element : t){
            super.add(element);
        }
    }

    @Override
    public final Object[] toArray() {
        throw new UnsupportedOperationException("toArray");
    }

    @Override
    public final <S> S[] toArray(S[] s) {
        throw new UnsupportedOperationException("toArray");
    }

    @Override
    public final T remove(int index) {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public final boolean remove(Object o) {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public final void clear() {
        throw new UnsupportedOperationException("clear");
    }

    @Override
    public final boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("removeAll");
    }

    @Override
    public final boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("retainAll");
    }

    @Override
    public final List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("subList");
    }

    @Override
    public final void forEach(Consumer<? super T> action) {
        throw new UnsupportedOperationException("forEach");
    }

    @Override
    public final boolean removeIf(Predicate<? super T> filter) {
        throw new UnsupportedOperationException("removeIf");
    }

    @Override
    public final T set(int index, T element) {
        throw new UnsupportedOperationException("set");
    }

    @Override
    public final boolean add(T t) {
        throw new UnsupportedOperationException("add");
    }

    @Override
    public final void add(int index, T element) {
        throw new UnsupportedOperationException("add");
    }

    @Override
    public final boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("addAll");
    }

    @Override
    public final void sort(Comparator<? super T> c) {
        throw new UnsupportedOperationException("sort");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("addAll");
    }

    @Override
    public final void replaceAll(UnaryOperator<T> operator) {
        throw new UnsupportedOperationException("replaceAll");
    }
}
