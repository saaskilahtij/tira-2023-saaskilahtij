package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;;

public class StackImplementation<E> implements StackInterface<E> {

  private static final int DEFAULT_STACK_SIZE = 10;
  
  private Object [] itemArray;
  private int top = -1;
  
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
    if (top == (itemArray.length - 1)) {
      try {
        Object[] moreSpace = new Object[itemArray.length * 2];

        for (int i = 0; i < itemArray.length; i++) {
          moreSpace[i] = itemArray[i];
        }
        itemArray = moreSpace;
      } catch (OutOfMemoryError e) {
        throw new OutOfMemoryError("Out of memory while trying to push an element to the stack");
      }
    }
    ++top;
    itemArray[top] = element;
  }

  
  @SuppressWarnings("unchecked")
  @Override
  public E pop() {
    
    if (top < 0) {
      throw new IllegalStateException("Stack is empty, can't pop an element");
    }

    E returnObj = (E) itemArray[top];

    itemArray[top] = null;
    --top;
    return  returnObj;
  }


  @SuppressWarnings("unchecked")
  @Override
  public E peek() {
    
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty, can't peek");
    }
    return (E) itemArray[top];
  }

  @Override
  public int size() {
    return (top + 1);
  }

  @Override
  public boolean isEmpty() {
    if (top < 0) {
      return true;
    }
    return false;
  }

  @Override
  public void clear() {
    Object [] temp = new Object[DEFAULT_STACK_SIZE];
    top = -1;
    itemArray = temp;
  }

  @Override 
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append("[");
    if (!isEmpty()) {
      builder.append(itemArray[0]);
    } 
    for (int i = 1; i < itemArray.length; i++) {
      if (itemArray[i] != null) {
        builder.append(", ");
        builder.append(itemArray[i]);
      } 
    }
    builder.append("]");
    return builder.toString();
  }
}