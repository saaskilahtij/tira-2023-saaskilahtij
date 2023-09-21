package oy.interact.tira.util;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.student.Algorithms;

public class SimpleContainer<E extends Comparable<E>> implements TIRAContainer<E> {

	private static final int DEFAULT_ARRAY_SIZE = 20;

	private E [] array = null;
	private Class<E> clazz;

	private int count = 0;
	private int reallocationCount = 0;

	private boolean sorted = false;

	public SimpleContainer(Class<E> clazz) {
		this(clazz, DEFAULT_ARRAY_SIZE);
	}

	@SuppressWarnings("unchecked")
	public SimpleContainer(Class<E> clazz, int capacity) {
		this.clazz = clazz;
		array = (E[]) Array.newInstance(clazz, capacity);
		count = 0;
		reallocationCount = 0;
	}

	@Override
	public void add(E element) throws OutOfMemoryError, IllegalArgumentException {
		if (null == element)
			throw new IllegalArgumentException("Key cannot be null");
		// Must not have duplicate items, so check if key is already in the array.
		for (int index = 0; index < array.length; index++) {
			if (array[index] == null) {
				break;
			}
			if (array[index].equals(element)) {
				array[index] = element;
				return;
			}
		}
		if (count >= array.length) {
			reallocate(array.length * 2);
		}
		array[count] = element;
		count++;
		sorted = false;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		return array[index];
	}

	@Override
	public E get(E element) throws IllegalArgumentException {
    for (int i = 0; i < (array.length - 1); i++) {
      if (element.equals(array[i]) && array[i] != null) {
        return array[i];
      }
    }
    throw new NoSuchElementException("Given element is not found in the list");
	}

	@Override
	public int indexOf(E element, Comparator<E> usingComparator) {
    for (int i = 0; i < (array.length - 1); i++) {
      if (element.compareTo(array[i]) == 0 && array[i] != null) {
        return i; 
      }
    }
    throw new NoSuchElementException("Given element is not found in the list");
	}

	// Note: This method is NOT USED by tests and TIRA Coders GUI.
	@Override
	public E remove(E element) throws IllegalArgumentException {
		if (null == element)
			throw new IllegalArgumentException("Key to remove cannot be null");
		E found = null;
		int foundIndex = 0;

		if (sorted) {
			// Does not work until binary search has been implemented.
			foundIndex = Algorithms.binarySearch(element, (E[]) array, 0, count);
			if (foundIndex >= 0) {
				found = (E) array[foundIndex];
				array[foundIndex] = null;
				count--;
			}
		} else {
			for (; foundIndex < count; foundIndex++) {
				if (array[foundIndex] == null) {
					break;
				}
				if (element.equals(array[foundIndex])) {
					found = (E) array[foundIndex];
					array[foundIndex] = null;
					count--;
					break;
				}
			}
		}
		// Move all the rest down one step to fill the null in array.
		if (null != found) {
			while (foundIndex < array.length - 1) {
				if (array[foundIndex + 1] == null) {
					break;
				}
				array[foundIndex] = array[foundIndex + 1];
			}
		}
		return found;
	}

	@Override
	public int findIndex(Predicate<E> searcher) {
    for (int i = 0; i < (array.length - 1); i++) {
      if (searcher == null) {
        throw new IllegalArgumentException("Given predicate is null");
      }
      if (array[i] != null) {
        if (searcher.test(array[i])) {
          return i;
        }
      }
    }
    return -1;
	}

	@Override
	public E find(Predicate<E> searcher) {
    for (int i = 0; i < (array.length - 1); i++) {
      if (searcher == null) {
        throw new IllegalArgumentException("Given predicate is null");
      }
      if (array[i] != null) {
        if (searcher.test(array[i])) {
          return array[i];
        }
      }
    }
    return null;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public int capacity() {
		return array.length;
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
		if (capacity <= count) {
			throw new IllegalArgumentException("New capacity cannot be smaller than count of elements");
		}
		if (count == 0) {
			array = (E[]) Array.newInstance(clazz, capacity);
			count = 0;
			reallocationCount = 0;
		} else {
			reallocate(capacity);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		array = (E[]) Array.newInstance(clazz, DEFAULT_ARRAY_SIZE);
		count = 0;
		reallocationCount = 0;
	}

	@java.lang.SuppressWarnings("unchecked")
	private void reallocate(int newSize) throws OutOfMemoryError {
		E[] newArray = (E[]) Array.newInstance(clazz, newSize);
		for (int index = 0; index < count; index++) {
			newArray[index] = array[index];
		}
		array = newArray;
		reallocationCount++;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E[] toArray() {
		E[] toReturn = (E[]) Array.newInstance(clazz, count);
		for (int index = 0; index < count; index++) {
			toReturn[index] = array[index];
		}
		return toReturn;
	}

	@Override
	public boolean isSorted() {
		return sorted;
	}

	@Override
	public void reverse() {
    if (array == null || array.length == 0) {
      return;
    }
    Algorithms.reverse(array, 0, (array.length - 1));
    // TODO: Student: finish this as part of task 02.
	}

	// TEACHERS: TODO: Remove the call to Algorithms sort method.
	@Override
	public void sort() {
		
    // TODO: Student: finish this as part of task 02.

	}

	// TEACHERS: TODO: Remove the call to Algorithms sort method.
	@Override
	public void sort(Comparator<E> usingComparator) {
    if (array == null || array.length == 0) {
      return;
    }
    Algorithms.insertionSort(array, 0, array.length, usingComparator);
    sorted = true;
	}

}
