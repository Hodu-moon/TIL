public interface List<E>{
    // Appends the specified element to the end of this list (optional operation).
    boolean add(E e);

    // Inserts the specified element at the specified position in this list (optional operation).
    void add(int index, E element);
    void clear();
    boolean contains(Object o);
    E get(int index);
    boolean isEmpty();
    E remove(int index);
    E set(int index, E element);
    int size();
}