import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B {
    @Test
    public void testStudentArrayDeque() {
        OperationSequence opSequence = new OperationSequence();
        DequeOperation op;

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        assertEquals(ads1.isEmpty(), sad1.isEmpty());

        for (int i = 0; i < 10; i++) {
            double n = StdRandom.uniform();

            if (n < 0.5) {
                op = new DequeOperation("addFirst", i);
                sad1.addFirst(i);
                ads1.addFirst(i);
            } else {
                op = new DequeOperation("addLast", i);
                sad1.addLast(i);
                ads1.addLast(i);
            }
            opSequence.addOperation(op);
            assertEquals(opSequence.toString(), ads1.size(), sad1.size());
        }

        for (int i = 0; i < 10; i++) {
            opSequence.addOperation(new DequeOperation("get", i));
            assertEquals(opSequence.toString(), ads1.get(i), sad1.get(i));
        }

        for (int i = 0; i < 10; i++) {
            double n = StdRandom.uniform();
            if (n < 0.5) {
                opSequence.addOperation(new DequeOperation("removeFirst"));
                assertEquals(opSequence.toString(), ads1.removeFirst(), sad1.removeFirst());
            } else {
                opSequence.addOperation(new DequeOperation("removeLast"));
                assertEquals(opSequence.toString(), ads1.removeLast(), sad1.removeLast());
            }
            opSequence.addOperation(new DequeOperation("size"));
            assertEquals(opSequence.toString(), ads1.size(), sad1.size());
        }
    }

    @Test
    public void testMyLinkedListDeque() {
        OperationSequence opSequence = new OperationSequence();
        DequeOperation op;

        Deque<Integer> lld = new LinkedListDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        assertEquals(ads1.isEmpty(), lld.isEmpty());

        for (int i = 0; i < 10; i++) {
            double n = StdRandom.uniform();

            if (n < 0.5) {
                op = new DequeOperation("addFirst", i);
                lld.addFirst(i);
                ads1.addFirst(i);
            } else {
                op = new DequeOperation("addLast", i);
                lld.addLast(i);
                ads1.addLast(i);
            }
            opSequence.addOperation(op);
            assertEquals(opSequence.toString(), ads1.size(), lld.size());
        }

        for (int i = 0; i < 10; i++) {
            opSequence.addOperation(new DequeOperation("get", i));
            assertEquals(opSequence.toString(), ads1.get(i), lld.get(i));
        }

        for (int i = 0; i < 10; i++) {
            double n = StdRandom.uniform();
            if (n < 0.5) {
                opSequence.addOperation(new DequeOperation("removeFirst"));
                assertEquals(opSequence.toString(), ads1.removeFirst(), lld.removeFirst());
            } else {
                opSequence.addOperation(new DequeOperation("removeLast"));
                assertEquals(opSequence.toString(), ads1.removeLast(), lld.removeLast());
            }
            opSequence.addOperation(new DequeOperation("size"));
            assertEquals(opSequence.toString(), ads1.size(), lld.size());
        }
    }

    @Test
    public void testMyArrayDeque() {
        OperationSequence opSequence = new OperationSequence();
        DequeOperation op;

        Deque<Integer> lld = new ArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        assertEquals(ads1.isEmpty(), lld.isEmpty());

        for (int i = 0; i < 10; i++) {
            double n = StdRandom.uniform();

            if (n < 0.5) {
                op = new DequeOperation("addFirst", i);
                lld.addFirst(i);
                ads1.addFirst(i);
            } else {
                op = new DequeOperation("addLast", i);
                lld.addLast(i);
                ads1.addLast(i);
            }
            opSequence.addOperation(op);
            assertEquals(opSequence.toString(), ads1.size(), lld.size());
        }

        for (int i = 0; i < 10; i++) {
            opSequence.addOperation(new DequeOperation("get", i));
            assertEquals(opSequence.toString(), ads1.get(i), lld.get(i));
        }

        for (int i = 0; i < 10; i++) {
            double n = StdRandom.uniform();
            if (n < 0.5) {
                opSequence.addOperation(new DequeOperation("removeFirst"));
                assertEquals(opSequence.toString(), ads1.removeFirst(), lld.removeFirst());
            } else {
                opSequence.addOperation(new DequeOperation("removeLast"));
                assertEquals(opSequence.toString(), ads1.removeLast(), lld.removeLast());
            }
            opSequence.addOperation(new DequeOperation("size"));
            assertEquals(opSequence.toString(), ads1.size(), lld.size());
        }
    }
}
