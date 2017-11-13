
public interface Deque<E> {
    void addFirst(E e);

    void addLast(E e);

    boolean isEmpty();

    int size();

    void printDeque();

    E removeFirst();

    E removeLast();

    E get(int index);

}
