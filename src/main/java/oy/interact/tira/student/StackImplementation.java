package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;;

public class StackImplementation<E> implements StackInterface<E> {

  private static final int DEFAULT_STACK_SIZE = 20;
  
  private Object [] itemArray;
  int lastIndex;
  
  public StackImplementation() {
    itemArray = new Object[DEFAULT_STACK_SIZE];
  }

  public StackImplementation(int size) {
    itemArray = new Object[size];
  }

  @Override
  public int capacity() {
    return itemArray.length;
  }

  @Override
  public void push(E element) {
  
    // Element can't be null
    if (element == null) {
      throw new NullPointerException("Can't add null element to the stack");
    }

    // Allocates more space if all of it gets used
    // Throws OutOfMemoryError if out of memory
    if (lastIndex == (itemArray.length - 1)) {
      try {
        Object[] moreSpace = new Object[itemArray.length + DEFAULT_STACK_SIZE];
      
        for (int i = 0; i < itemArray.length; i++) {
          moreSpace[i] = itemArray[i];
        }
        itemArray = moreSpace;
      } catch (OutOfMemoryError e) {
        throw new OutOfMemoryError("Out of memory while trying to push an element to the stack");
      }
    }
    itemArray[lastIndex] = element;
    ++lastIndex;
  }

  @Override
  public E pop() {

    E returnObj = (E) itemArray[lastIndex];

    if (lastIndex == 0) {
      throw new IllegalStateException("Stack is empty, can't pop an element");
    }
    itemArray[lastIndex] = null;
    return returnObj;
  }

  @Override
  public E peek() {
    
    if (lastIndex == 0) {
      throw new IllegalStateException("Stack is empty, can't peek");
    }
    return (E) itemArray[lastIndex];
  }

  @Override
  public int size() {
    return (lastIndex + 1);
  }

  @Override
  public boolean isEmpty() {
    if (lastIndex == 0) {
      return false;
    }
    return true;
  }

  @Override
  public void clear() {
    for (int i = 0; i <= lastIndex; i++) {
      itemArray[i] = null;
    }
  }

  @Override 
  public String toString() {
    StringBuilder builder = new StringBuilder();

    if (itemArray == null) {
      return "[]";
    }

    for (int i = 0; i < itemArray.length; i++) {
      builder.append(itemArray[i].toString());
    }

    return builder.toString();
  }
}