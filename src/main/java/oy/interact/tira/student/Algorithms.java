package oy.interact.tira.student;

import java.io.Console;
import java.util.Comparator;

public class Algorithms {

   private Algorithms() {
      // nada
   }

  // Function that swaps the given values in the array
  public static <T> void swap(T[] array, int index1, int index2) {
    T temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }



   ///////////////////////////////////////////
   // Insertion Sort for the whole array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array) {
      // Loop through the whole array
      for (int i = 1; i < array.length; i++) {
        // Initialize the current value
        T current = array[i];
        // Initialize the index of the value that gets compared
        int j = i - 1;
        // If the current value is less than the value on the left move
        // the value on the left to the right until the right place has been found.
        while (j >= 0 && current.compareTo(array[j]) < 0) {
          // Swap the value if it's bigger than the current value
          swap(array, j + 1, j);
          j--;
        }
        // When coming here, it means that the current value is bigger 
        // than the value on the left.
        // This means putting the current value to the right of the smaller compared value at j
        array[j + 1] = current;
      }
   }

   ///////////////////////////////////////////
   // Insertion Sort for a slice of the array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array, int fromIndex, int toIndex) {

      // Loop from fromIndex to toIndex
      for (int i = (fromIndex + 1); i < toIndex; i++) {
        T current = array[i];
        int j = i - 1;

        // Same algorithm as the loop above
        while (j >= 0 && current.compareTo(array[j]) < 0) {
          swap(array, j + 1, j);
          j--;
        }
        array[j + 1] = current;
      }
   }

   //////////////////////////////////////////////////////////
   // Insertion Sort for the whole array using a Comparator
   //////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, Comparator<T> comparator) {
      // TODO: Student, implement this.
   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
      // TODO: Student, implement this.
   }

   ///////////////////////////////////////////
   // Reversing an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array) {
    
    // Loop to the middle of the array
    for (int i = 0; i < (array.length / 2); i++) {
      // Swap the value at the index i with the corresponding from the end
      swap(array, i, array.length - i - 1);
    }
   }

   ///////////////////////////////////////////
   // Reversing a slice of an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array, int fromIndex, int toIndex) {
      // TODO: Student, implement this.
      for (int i = (fromIndex + 1); i < (toIndex / 2); i++) {
        swap(array, i, toIndex - i - 1);
      }
   }




   ///////////////////////////////////////////
   // Binary search bw indices
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
      return -1;
   }

   ///////////////////////////////////////////
   // Binary search using a Comparator
   ///////////////////////////////////////////

   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      return -1;
   }

   public static <E extends Comparable<E>> void fastSort(E [] array) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E [] array, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E [] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

}
