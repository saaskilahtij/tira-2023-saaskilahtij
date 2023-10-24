package oy.interact.tira.student;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.util.QueueInterface;

public class QueueImplementation<E> implements QueueInterface<E> {

  private static final int DEFAULT_CAPACITY = 10;
  


  private Object [] queue;
  private int headNode;
  private int tailNode;
  private int count;

  public QueueImplementation(){
    queue = new Object[DEFAULT_CAPACITY];
  }

  public QueueImplementation(int capacity) {
    queue = new Object[capacity];
  }

  @Override
  public int capacity() {
    return queue.length;
  }


  @SuppressWarnings("unchecked")
  @Override
  public void enqueue(E element) throws OutOfMemoryError, NullPointerException {

    if (element == null) {
      throw new NullPointerException("Can't add null element to the queue");
    }

    if (count >= capacity()) {
      Object [] moreSpace = new Object[capacity() * 2];

      for (int i = headNode, j = 0; j < count; i++, j++) {
        if (i == queue.length) {
          i = 0;
        }
        moreSpace[j] = queue[i];
      }
      queue = moreSpace; 
      headNode = 0;
      tailNode = count;
    }

    if (tailNode >= capacity() && headNode > 0) {
      tailNode = 0;
    }
    queue[tailNode++] = element;
    count++;
  }


  @SuppressWarnings("unchecked")
  @Override
  public E dequeue() throws IllegalStateException {
    
    if (count == 0) {
      throw new IllegalStateException("Can't dequeue empty queue");
    }
    
    E tempNode = (E)queue[headNode];
    queue[headNode] = null;
    headNode++;
    count--;

    if (headNode >= capacity()) {
      headNode = 0;
    }

    return tempNode;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E element() throws IllegalStateException {
    if (isEmpty()) {
      throw new IllegalStateException("Can't access the element the queue is empty");
    }
    return (E)queue[headNode];
  }

  @Override
  public int size() {
    return count;
  }

  @Override
  public boolean isEmpty() {
    if (size() == 0) {
      return true;
    }
    return false;
  }

  @Override
  public void clear() {
    Object [] emptyQueue = new Object[DEFAULT_CAPACITY];
    queue = emptyQueue;
    count = 0;
    headNode = 0;
    tailNode = 0;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("[");

    int i = headNode;
    int counter = count;
    while(counter > 0) {
      builder.append(queue[i]);
      if (counter > 1) {
        builder.append(", ");
      }
      ++i;
      --counter;
      if (i >= capacity()) {
        i = 0;
      }
    }

    builder.append("]");
    return builder.toString();
  }
}
