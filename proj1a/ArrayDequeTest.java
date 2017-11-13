/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for compare two ints. */
    public static boolean checkEqual(int expected, int actual) {
        if (expected != actual) {
            System.out.println("get() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for compare two ints. */
    public static boolean checkEqual(double expected, double actual) {
        if (expected != actual) {
            System.out.println("get() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<Integer> arrayList = new ArrayDeque<>();

        boolean passed = checkEmpty(true, arrayList.isEmpty());

        arrayList.addFirst(0);

        passed = checkEmpty(false, arrayList.isEmpty()) && passed;

        for (int i = 1; i <= 5; i++) {
            arrayList.addLast(i);
            passed = checkSize(i + 1, arrayList.size());

        }
        arrayList.printDeque();
        for (int i = -1; i >= -10; i--) {
            arrayList.addFirst(i);
            passed = checkSize(6 - i, arrayList.size());
        }
        System.out.println("Printing out deque: ");
        arrayList.printDeque();

        printTestStatus(passed);
    }

    public static void removeGetTest() {
        System.out.println("Running remove/get test.");

        ArrayDeque<Integer> arrayList = new ArrayDeque<>();

        boolean passed = checkEmpty(true, arrayList.isEmpty());

        for (int i = 5; i < 10; i++) {
            arrayList.addLast(i);
        }

        for (int i = 4; i >= 0; i--) {
            arrayList.addFirst(i);
        }

        System.out.println("Testing get method.");
        for (int i = 0; i < 10; i++) {
            passed = passed && checkEqual(i, arrayList.get(i));
        }

        System.out.println("Testing removeFirst method.");
        for (int i = 0; i < 10; i++) {
            passed = passed && checkEqual(i, arrayList.removeFirst());
        }

        System.out.println("Testing removeLast method. ");
        for (int i = 0; i < 10; i++) {
            arrayList.addLast(i);
        }
        for (int i = 9; i >= 0; i--) {
            passed = passed && checkEqual(i, arrayList.removeLast());
        }

        passed = passed && arrayList.removeFirst() == null
                    && arrayList.removeLast() == null;

        System.out.println("Printing out deque: ");
        arrayList.printDeque();

        printTestStatus(passed);
    }

//    public static void resizeTest() {
//        System.out.println("Running resize test.");
//        ArrayDeque<Integer> arrayList = new ArrayDeque<>();
//        boolean passed = true;
//        for (int i = 0; i <= 32; i++) {
//            arrayList.addLast(i);
//            System.out.print(arrayList.getLoadFactor() + " ");
//        }
//        System.out.println();
//        for (int i = 0; i <= 32; i++) {
//            arrayList.removeLast();
//            System.out.print(arrayList.getLoadFactor() + " ");
//        }
//        System.out.println();
//        System.out.println("Printing out deque: ");
//        arrayList.printDeque();
//
//        printTestStatus(passed);
//    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        removeGetTest();
        //resizeTest();
    }
} 