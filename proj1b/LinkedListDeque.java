/** Doubly linked list based list.
 *  @author Jiashuo Zhang
 */

public class LinkedListDeque<E> implements Deque<E> {

    /** A ListNode class used by the doubly linked list. */
    private static class ListNode<E> {
        E data;
        ListNode next;
        ListNode prev;

        /** Creates a new ListNode. */
        ListNode(E e) {
            new ListNode<E>(e, null, null);
        }

        /** Creates a new ListNode with given next and prev. */
        ListNode(E e, ListNode next, ListNode prev) {
            this.data = e;
            this.next = next;
            this.prev = prev;
        }
    }

    private int size;
    private ListNode sentinel;

    /** Creates an empty list. */
    public LinkedListDeque() {
        sentinel = new ListNode<E>(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    /** Adds an item to the front of the Deque. */
    public void addFirst(E e) {
        sentinel.next = new ListNode<>(e, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    @Override
    /** Adds an item to the back of the Deque. */
    public void addLast(E e) {
        sentinel.prev = new ListNode<>(e, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    @Override
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    /** Returns the number of items in the Deque. */
    public int size() {
        return size;
    }

    @Override
    /** Prints the items in the Deque from first to last. */
    public void printDeque() {
        ListNode temp;
        for (temp = sentinel; temp.next != sentinel; temp = temp.next) {
            System.out.print(temp.next.data);
            if (temp.next != sentinel) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    @Override
    /** Removes and returns the item at the front of the Deque.
     *  If no such item exists, returns null.
     */
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E item = (E) sentinel.next.data;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return item;
    }

    @Override
    /** Removes and returns the item at the back of the Deque.
     *  If no such item exists, returns null.
     */
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        E item = (E) sentinel.prev.data;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return item;
    }

    @Override
    /** Gets the item at the given index using iteration.
     *  If no such item exists, returns null.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        ListNode ptr = sentinel;
        for (int i = 0; i <= index; i++) {
            ptr = ptr.next;
        }
        return (E) ptr.data;
    }

    /** Same as get function, but uses recursion. */
    public E getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return (E) getRecursive(index, sentinel.next).data;
    }

    /** Helper function to recursively search the target element. */
    private ListNode<E> getRecursive(int index, ListNode<E> list) {
        if (index == 0) {
            return list;
        }
        return getRecursive(index - 1, list.next);
    }
}
