package com.Mattk4355.Utils;

import java.util.*;
import java.util.function.*;

public class ValueMap<T, U> implements Map<T, U>, CanClone<ValueMap<T, U>> {
    private ArrayList<ObjectEntry<T, U>> table;

    public ValueMap(){
        this(0);
    }
    public ValueMap(int size){
        if (size < 0) throw new IllegalArgumentException("Illegal size: " + size);
        table = new ArrayList<>(size);
    }
    public ValueMap(Map<? extends T,? extends U> m){
        this(0);
        this.putAll(m);
    }

    @Override
    public int size() {
        return table.size();
    }
    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }
    @Override
    public boolean containsKey(Object key) {
        Utils.Objects.requireNonNull(key, "Key cannot be null");
        for (ObjectEntry<T, U> entry : table){
            if (entry.getKey().equals(key)) return true;
        }
        return false;
    }
    @Override
    public boolean containsValue(Object value) {
        if (value != null){
            for (ObjectEntry<T, U> entry : table){
                if (entry.getValue().equals(value)) return true;
            }
        }
        else{
            for (ObjectEntry<T, U> entry : table){
                if (entry.getValue() == null) return true;
            }
        }
        return false;
    }

    @Override
    public U get(Object key) {
        Utils.Objects.requireNonNull(key, "Key cannot be null");
        for (ObjectEntry<T, U> entry : table){
            if (entry.getKey().equals(key)) return entry.getValue();
        }
        return null;
    }
    @Override
    public U put(T key, U value) {
        Utils.Objects.requireNonNull(key, "Key cannot be null");
        int i;
        if ((i = indexOf(key)) != -1) return table.get(i).setValue(value);
        else{
            ObjectEntry<T, U> entry = new ObjectEntry<>(key, value);
            table.add(entry);
            return value;
        }
    }
    private int indexOf(T key){
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).getKey().equals(key)) return i;
        }
        return -1;
    }
    @Override
    public U remove(Object key) {
        Utils.Objects.requireNonNull(key, "Key cannot be null");
        for (ObjectEntry<T, U> entry : table){
            if (entry.getKey().equals(key)){
                table.remove(entry);
                return entry.getValue();
            }
        }
        return null;
    }
    @Override
    public void putAll(Map<? extends T, ? extends U> m) {
        for (Entry<? extends T,? extends U> entry : m.entrySet()){
            this.put(entry.getKey(), entry.getValue());
        }
    }
    @Override
    public void clear() {
        table.clear();
    }
    @Override
    public Set<T> keySet() {
        ImmutableNoDuplicateList<T> list = new ImmutableNoDuplicateList<>();
        for (ObjectEntry<T, U> entry : table){
            list.add(entry.getKey());
        }
        return list;
    }
    @Override
    public Collection<U> values() {
        ArrayList<U> list = new ArrayList<>();
        for (ObjectEntry<T, U> entry : table){
            list.add(entry.getValue());
        }
        return list;
    }
    @Override
    public Set<Entry<T, U>> entrySet() {
        return new ImmutableNoDuplicateList<>(table);
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o instanceof ValueMap){
            ValueMap other = (ValueMap) o;
            if (this.size() != other.size()){
                return false;
            }
            for (int i = 0; i < size(); i++){
                if (!Objects.equals(this.table.get(i), other.table.get(i))) return false;
            }
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return table.hashCode();
    }

    @Override
    @SuppressWarnings("unchecked")
    public ValueMap<T, U> clone() {
        try{
            ValueMap<T, U> newMap = (ValueMap<T, U>) super.clone();
            newMap.table = (ArrayList<ObjectEntry<T,U>>) this.table.clone();
            return newMap;
        }
        catch (CloneNotSupportedException e){
            throw new InternalError(e);
        }
    }

    private static final class ObjectEntry<X, Y> implements Entry<X, Y>{
        private final X key;
        private Y value; //null values may be permitted by map
        private ObjectEntry(X key, Y value){
            this.key = Objects.requireNonNull(key, "Key cannot be null");
            this.value = value;
        }
        @Override
        public X getKey() {
            return key;
        }
        @Override
        public Y getValue() {
            return value;
        }
        @Override
        public Y setValue(Y value) {
            Y oldVal = this.value;
            this.value = value;
            return oldVal;
        }
        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof ObjectEntry){
                ObjectEntry other = (ObjectEntry) o;
                return Objects.equals(this.key, other.key) &&
                        Objects.equals(this.value, other.value);
            }
            return false;
        }
        @Override
        public int hashCode(){
            final int keyHash = key.hashCode();
            final int valueHash = value.hashCode();
            return (keyHash / 2) + (valueHash / 2);
        }
    }
    private static final class ImmutableNoDuplicateList<X> extends NoDuplicateList<X>{
        private ImmutableNoDuplicateList(){
            super();
        }
        private ImmutableNoDuplicateList(Collection<? extends X> c){
            super();
            for (X x : c){
                super.add(x); //call to supertype method only to add initial elements
            }
        }
        @Override
        public boolean add(X x) {
            throw new UnsupportedOperationException("add");
        }
        @Override
        public Object[] toArray(){
            throw new UnsupportedOperationException("toArray");
        }
        @Override
        public <S> S[] toArray(S[] a) {
            throw new UnsupportedOperationException("toArray");
        }
        @Override
        public X set(int index, X element) {
            throw new UnsupportedOperationException("set");
        }
        @Override
        public void add(int index, X element) {
            throw new UnsupportedOperationException("add");
        }
        @Override
        public X remove(int index) {
            throw new UnsupportedOperationException("remove");
        }
        @Override
        public List<X> subList(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException("subList");
        }
        @Override
        public void forEach(Consumer<? super X> action) {
            throw new UnsupportedOperationException("forEach");
        }
        @Override
        public boolean removeIf(Predicate<? super X> filter) {
            throw new UnsupportedOperationException("removeIf");
        }
        @Override
        public void replaceAll(UnaryOperator<X> operator) {
            throw new UnsupportedOperationException("replaceAll");
        }
        @Override
        public void sort(Comparator<? super X> c) {
            throw new UnsupportedOperationException("sort");
        }
        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("remove");
        }
        @Override
        public void clear() {
            throw new UnsupportedOperationException("clear");
        }
        @Override
        public boolean addAll(Collection<? extends X> c) {
            throw new UnsupportedOperationException("addAll");
        }
        @Override
        public boolean addAll(int index, Collection<? extends X> c) {
            throw new UnsupportedOperationException("addAll");
        }
        @Override
        public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException("removeAll");
        }
        @Override
        public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException("retainAll");
        }
    }
}
