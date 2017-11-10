public class ArrayDeque<E> {
    private E[] elements;
    private int head;
    private int tail;
    private int size;
    private double loadFactor;
    private final static int DEFAULT_SIZE = 8;

    public double getLoadFactor() { return (double) size / elements.length; }

    /** Creates an empty list. */
    public ArrayDeque() {
        elements = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
        head = 0;
        tail = 1;
        loadFactor = 0;
    }

    /** Adds an item to the front of the Deque. */
    public void addFirst(E e) {
        resize();
        elements[head] = e;
        ++size;
        head = minusOne(head);
    }

    /** Adds an item to the back of the Deque. */
    public void addLast(E e) {
        resize();
        elements[tail] = e;
        ++size;
        tail = plusOne(tail);
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the Deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the Deque from first to last. */
    public void printDeque() {
        int ptr = head;
        for (int i = 0; i < size; i++) {
            System.out.print(elements[plusOne(ptr)]);
            ptr = plusOne(ptr);
            if (i != size - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the Deque.
     *  If no such item exists, returns null.
     */
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        head = plusOne(head);
        E toReturn = elements[head];
        elements[head] = null;
        --size;
        resize();
        return toReturn;
    }

    /** Removes and returns the item at the back of the Deque.
     *  If no such item exists, returns null.
     */
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        tail = minusOne(tail);
        E toReturn = elements[tail];
        elements[tail] = null;
        --size;
        resize();
        return toReturn;
    }

    /** Gets the item at the given index using iteration.
     *  If no such item exists, returns null.
     */
    public E get(int index) {
        if (index < 0 && index >= size) {
            return null;
        }
        return elements[plusOne(head + index)];
    }

    /** Returns the index after the current index. */
    private int plusOne(int index) {
        return (index + 1) % elements.length;
    }

    /** Returns the index before the current index. */
    private int minusOne(int index) {
        return (index - 1 + elements.length) % elements.length;
    }

    /** Resize the array depending on the load factor. */
    private void resize() {
        int newSize;
        if (size == elements.length) {
            newSize = elements.length * 2;
        } else if (elements.length >= 16 && size * 4 < elements.length) {
            newSize = elements.length / 2;
        } else {
            return;
        }

        E[] copy = (E[]) new Object[newSize];
        for (int i = 0 ; i < size; i++, head = plusOne(head)) {
            copy[i] = elements[plusOne(head)];
        }
        head = copy.length - 1;
        tail = size;
        elements = copy;
    }
}
