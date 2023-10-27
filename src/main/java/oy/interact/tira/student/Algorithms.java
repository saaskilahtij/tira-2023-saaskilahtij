package oy.interact.tira.student;

import java.io.Console;
import java.util.Comparator;
import java.util.Random;

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
        // Check if the value at outerIndex is null and return if it is
        if (array[outerIndex] == null) {
          return;
        }
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
        if (array[outerIndex] == null) {
          return;
        }
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
      if (array[outerIndex] == null) {
        return;
      }
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
    if (fromIndex < 0 || fromIndex >= toIndex || toIndex > array.length) {
      throw new IllegalArgumentException("Invalid given indexes");
    }

    for (int outerIndex = (fromIndex + 1); outerIndex < toIndex; outerIndex++) {
      if (array[outerIndex] == null) {
        return;
      }
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
    int outerIndex = toIndex - 1;

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

    if (fromIndex < 0 || fromIndex >= toIndex || toIndex > fromArray.length) {
      throw new IllegalArgumentException("Invalid given indexes");
    }

    if (aValue == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }
    
    // Take temporary variables for the indexes to mutate
    int left = fromIndex;
    int right = toIndex - 1;
    
    // Loop until the indexes meet
    while (left <= right) {

      // Calculate the middle index
      int middle = (right + left) / 2;

      if (aValue.compareTo(fromArray[middle]) < 0) {  // If value < value at middle index, move the right index
        right = middle - 1;
      } else if (aValue.compareTo(fromArray[middle]) > 0) { // If value > value at middle index, move the left index
        left = middle + 1;
      } else {
        return middle;  // If value == value at middle index, return the index
      }
    }
    return -1;   // If not found, return -1
   }


   ///////////////////////////////////////////
   // Binary search using a Comparator
   ///////////////////////////////////////////


   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {

    if (fromIndex < 0 || fromIndex >= toIndex || toIndex > fromArray.length) {
        throw new IllegalArgumentException("Invalid given indexes");
    }

    if (comparator == null){
      throw new IllegalArgumentException("Comparator cannot be null");
    }

    if (aValue == null) {
      throw new IllegalArgumentException("Value cannot be null");
    }

    int left = fromIndex;
    int right = toIndex;

    while (left <= right) {

      int middle = (right + left) / 2;

      if (fromArray[middle] == null) {
        return -1;
      }
      if (comparator.compare(aValue, fromArray[middle]) < 0) {
        right = middle - 1;
      } else if (comparator.compare(aValue, fromArray[middle]) > 0) {
        left = middle + 1;
      } else {
        return middle;
      }
    }
    
    return -1;
   }

  //TODO: Recursive binary search with Comparator
  // Remember to mention in the report!

   // Fix stack overflow!
   // Fix heap overflow!
  @SuppressWarnings("unchecked")
  public static <E extends Comparable<E>> void fastSort(E [] array) {

    if (array == null) {
      return;
    }

    int length = array.length;
    if (length < 2) {
      return;
    }

    int middle =  length / 2;
    E[] firstHalf = (E[]) new Comparable[middle];
    E[] secondHalf = (E[]) new Comparable[length - middle];


    for (int i = 0; i < middle; i++) {
      firstHalf[i] = array[i];
    }

    for (int i = middle; i < length; i++) {
      secondHalf[i - middle] = array[i];
    }

    fastSort(firstHalf);
    fastSort(secondHalf);
    merge(array, firstHalf, secondHalf);
  }

  @SuppressWarnings("unchecked")
  public static <E> void fastSort(E [] array, Comparator<E> comparator) {

    if (array == null) {
      throw new IllegalArgumentException("Given array cannot be null");
    }

    int length = array.length;
    if (length < 2) {
      return;
    }

    int middle =  length / 2;
    E[] firstHalf = (E[]) new Comparable[middle];
    E[] secondHalf = (E[]) new Comparable[length - middle];

    for (int i = 0; i < middle; i++) {
      firstHalf[i] = array[i];
    }

    for (int i = middle; i < length; i++) {
      secondHalf[i - middle] = array[i];
    }

    fastSort(firstHalf, comparator);
    fastSort(secondHalf, comparator);
    mergeComparator(array, firstHalf, secondHalf, comparator);
  }


  @SuppressWarnings("unchecked")
  public static <E> void fastSort(E [] array, int fromIndex, int toIndex, Comparator<E> comparator) {
    
    if (array == null) {
      throw new IllegalArgumentException("Given array cannot be null");
    }

    int length = array.length;
    if (length < 2) {
      return;
    }
    
    if (fromIndex < 0 || fromIndex >= length || toIndex < fromIndex || toIndex > length ) {
      throw new IllegalArgumentException("Invalid given indexes");
    }

    int middle = (fromIndex + toIndex) / 2;
    E[] firstHalf = (E[]) new Comparable[middle - fromIndex];
    E[] secondHalf = (E[]) new Comparable[toIndex - middle];

    int initialArrayIndex = 0;
    for (int i = 0; i < firstHalf.length; i++, initialArrayIndex++) {
      firstHalf[i] = array[initialArrayIndex];
    }
    for (int i = 0; i < secondHalf.length; i++, initialArrayIndex++) {
      secondHalf[i] = array[initialArrayIndex];
    }
    
    fastSort(array, fromIndex, toIndex, comparator);
    fastSort(array, fromIndex, toIndex, comparator);
     
    mergeComparator(array, firstHalf, secondHalf, comparator);
  }


  private static <E extends Comparable<E>> void merge(E[] initialArray, E[] firstHalf, E[] secondHalf) {
    int sizeFirst = firstHalf.length;
    int sizeSecond = secondHalf.length;

    int indexFirst = 0;
    int indexSecond = 0;
    int indexInitial = 0;

    while(indexFirst < sizeFirst && indexSecond < sizeSecond) {
      // if firsthalf is less than equal to 
      if(firstHalf[indexFirst].compareTo(secondHalf[indexSecond]) <= 0) {
        initialArray[indexInitial] = firstHalf[indexFirst];
        ++indexFirst;
      } else {
        initialArray[indexInitial] = secondHalf[indexSecond];
        ++indexSecond;
      }
      ++indexInitial;
    } 
    while(indexFirst < sizeFirst) {
      initialArray[indexInitial] = firstHalf[indexFirst];
      ++indexFirst;
      ++indexInitial;
    }
    while(indexSecond < sizeSecond) {
      initialArray[indexInitial] = secondHalf[indexSecond];
      ++indexSecond;
      ++indexInitial;
    }
  }


  private static <E> void mergeComparator(E[] initialArray, E[] firstHalf, E[] secondHalf, Comparator<E> comparator) {
    
    int sizeFirst = firstHalf.length;
    int sizeSecond = secondHalf.length;

    int indexFirst = 0;
    int indexSecond = 0;
    int indexInitial = 0;

    while(indexFirst < sizeFirst && indexSecond < sizeSecond) {

      if (comparator.compare(firstHalf[indexFirst], secondHalf[indexSecond]) <= 0) {
        initialArray[indexInitial] = firstHalf[indexFirst];
        ++indexFirst;
      } else {
        initialArray[indexInitial] = secondHalf[indexSecond];
        ++indexSecond;
      }
      ++indexInitial;
    } 
    while(indexFirst < sizeFirst) {
      initialArray[indexInitial] = firstHalf[indexFirst];
      ++indexFirst;
      ++indexInitial;
    }
    while(indexSecond < sizeSecond) {
      initialArray[indexInitial] = secondHalf[indexSecond];
      ++indexSecond;
      ++indexInitial;
    }
  }


}
