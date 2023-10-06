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
  
    // @throws OutOfMemoryError if no additional room can be allocated for the stack
    if (element == null) {
      throw new NullPointerException("Can't add null element to the stack");
    }

    if (lastIndex == itemArray.length) {
      Object[] newArray = new Object[DEFAULT_STACK_SIZE];

      for (int i = 0; i < itemArray.length; i++) {
          newArray[i] = itemArray[i];
      }
      itemArray = newArray;
    }

    itemArray[lastIndex] = element;
    ++lastIndex;
  }

  @Override
  public E pop() {

    if (itemArray == null) {
      return null;
    }

    itemArray[lastIndex] = null;
    return null;
  }

  public E peek() {
    return null;
  }

  public int size() {
    return 0;
  }

  public boolean isEmpty() {
    return false;
  }

  public void clear() {

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