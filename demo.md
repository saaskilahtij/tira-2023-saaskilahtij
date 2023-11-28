```Java

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    private static class ArrayWrapper {
        Integer [] array;
        int index;
    }

    public static void main(String[] args) throws Exception {
        Integer [] array = new Integer[42];

        System.out.println("*****************************************");
        System.out.println("** Testing Integer as index parameter....");
        int index = -1;
        addToTable(array, index);      // "Vasen lapsi"
        printToIndex(array, index);    // "Oikea lapsi"

        array = new Integer[42];
        System.out.println("** Testing AtomicInteger as index parameter....");
        AtomicInteger atomicIndex = new AtomicInteger(-1);
        addToTable(array, atomicIndex);
        printToIndex(array, atomicIndex.get());

        array = new Integer[42];
        System.out.println("** Testing Integer array as index parameter....");
        Integer [] indexTable = {-1};
        addToTable(array, indexTable);
        printToIndex(array, indexTable[0]);

        System.out.println("** Testing wrapper object as parameter....");
        ArrayWrapper wrapper = new ArrayWrapper();
        wrapper.array = new Integer[42];
        wrapper.index = -1;
        addToTable(wrapper);
        printToIndex(wrapper.array, wrapper.index);
    }

    private static void addToTable(Integer [] array, Integer index) throws ArrayIndexOutOfBoundsException {
        if (index < array.length) {
            int value = ThreadLocalRandom.current().nextInt(-3, 16);
            if (value >= 0) {
                array[++index] = value;
                System.out.format("array[%d] is: %d\n", index, value);
                addToTable(array, index);
            } else {
                System.out.println("Negative value, stopping recursion");
            }
        }
    }

    private static void addToTable(Integer [] array, AtomicInteger index) throws ArrayIndexOutOfBoundsException {
        if (index.get() < array.length) {
            int value = ThreadLocalRandom.current().nextInt(-3, 16);
            if (value >= 0) {
                array[index.incrementAndGet()] = value;
                System.out.format("array[%d] is: %d\n", index.get(), value);
                addToTable(array, index);
            } else {
                System.out.println("Negative value, stopping recursion");
            }
        }
    }

    private static void addToTable(Integer [] array, Integer [] index) throws ArrayIndexOutOfBoundsException {
        if (index[0] < array.length) {
            int value = ThreadLocalRandom.current().nextInt(-3, 16);
            if (value >= 0) {
                index[0] = index[0] + 1;
                array[index[0]] = value;
                System.out.format("array[%d] is: %d\n", index[0], value);
                addToTable(array, index);
            } else {
                System.out.println("Negative value, stopping recursion");
            }
        }
    }

    private static void addToTable(ArrayWrapper wrapper) throws ArrayIndexOutOfBoundsException {
        if (wrapper.index < wrapper.array.length) {
            int value = ThreadLocalRandom.current().nextInt(-3, 16);
            if (value >= 0) {
                wrapper.index += 1;
                wrapper.array[wrapper.index] = value;
                System.out.format("array[%d] is: %d\n", wrapper.index, value);
                addToTable(wrapper);
            } else {
                System.out.println("Negative value, stopping recursion");
            }
        }
    }

    private static void printToIndex(Integer [] array, int index) {
        if (index < 0 || index >= array.length) {
            System.out.println(">> Nothing to print from array");
        }
        for (int counter = 0; counter <= index; counter++) {
            System.out.print(array[counter] + ", ");
        }
        System.out.println("");
    }
}