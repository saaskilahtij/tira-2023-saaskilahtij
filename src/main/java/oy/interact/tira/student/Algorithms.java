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
      for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
        // Initialize the current value
        T current = array[outerIndex];
        // Initialize the index of the value that gets compared
        int innerIndex = outerIndex - 1;
        // If the current value is less than the value on the left move
        // the value on the left to the right until the right place has been found.
        while (innerIndex >= 0 && current.compareTo(array[innerIndex]) < 0) {
          // Swap the value if it's bigger than the current value
          swap(array, innerIndex + 1, innerIndex);
          innerIndex--;
        }
        // When coming here, it means that the current value is bigger 
        // than the value on the left.
        // This means putting the current value to the right of the smaller compared value at j
        array[innerIndex + 1] = current;
      }
   }

   ///////////////////////////////////////////
   // Insertion Sort for a slice of the array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array, int fromIndex, int toIndex) {

    if(fromIndex < 0 || fromIndex >= toIndex || toIndex > array.length) {
      throw new IllegalArgumentException("Invalid given indexes");
    }

      // Loop from fromIndex to toIndex
      for (int outerIndex = (fromIndex + 1); outerIndex < toIndex; outerIndex++) {
        T current = array[outerIndex];
        int innerIndex = outerIndex - 1;

        // Same algorithm as the loop above
        while (innerIndex >= 0 && current.compareTo(array[innerIndex]) < 0) {
          swap(array, innerIndex + 1, innerIndex);
          innerIndex--;
        }
        array[innerIndex + 1] = current;
      }
   }

   //////////////////////////////////////////////////////////
   // Insertion Sort for the whole array using a Comparator
   //////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, Comparator<T> comparator) {

    // Same as above but using the comparator
    for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
      T current = array[outerIndex];
      int innerIndex = outerIndex - 1;

      while (innerIndex >= 0 && comparator.compare(current, array[innerIndex]) < 0) {
        swap(array, innerIndex + 1, innerIndex);
        innerIndex--;
      }
      array[innerIndex + 1] = current;
    }
   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
    
    // Same algorithm but with comparator    
    if(fromIndex < 0 || fromIndex >= toIndex || toIndex > array.length) {
      throw new IllegalArgumentException("Invalid given indexes");
    }

    for (int outerIndex = (fromIndex + 1); outerIndex < toIndex; outerIndex++) {
      T current = array[outerIndex];
      int innerIndex = outerIndex - 1;

      while (innerIndex >= 0 && comparator.compare(current, array[innerIndex]) < 0) {
        swap(array, innerIndex + 1, innerIndex);
        innerIndex--;
      }
      
      array[innerIndex + 1] = current;
    }
   }

   ///////////////////////////////////////////
   // Reversing an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array) {
    
    int innerIndex = 0;
    int outerIndex = array.length - 1;

    // Loop until the variables meet
    while (innerIndex < outerIndex) {
      // If both != null swap them
      if (array[innerIndex] != null && array[outerIndex] != null) {
        swap(array, innerIndex, outerIndex);
        ++innerIndex;
        --outerIndex;
      } else {  // Else skip the null value
        if (array[innerIndex] == null) {
          ++innerIndex;
        }
        if (array[outerIndex] == null) {
          --outerIndex;
        }
      }
    }
   }

   ///////////////////////////////////////////
   // Reversing a slice of an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array, int fromIndex, int toIndex) {

    // Ensure that the indexes are in valid range
    if (fromIndex < 0 || fromIndex >= toIndex || toIndex > array.length) {
      throw new IllegalArgumentException("Invalid given indexes");
    }
    // Initialize the helper variables
    // i loops from the beginning
    int innerIndex = fromIndex;
    // k loops from the end of the list
    int outerIndex = toIndex;

    // Loop until the variables meet
    while (innerIndex < outerIndex) {
      // If none of them are null, swap the variables and move the indexes
      if(array[innerIndex] != null && array[outerIndex] != null) {
        swap(array, innerIndex, outerIndex);
        ++innerIndex;
        --outerIndex;
      } else { 
        // If null, skip
        if (array[innerIndex] == null) {
          ++innerIndex;                  
        } 
        if (array[outerIndex] == null) {
          --outerIndex;                  
        }
      } 

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
