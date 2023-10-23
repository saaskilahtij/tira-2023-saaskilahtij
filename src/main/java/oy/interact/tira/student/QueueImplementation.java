package oy.interact.tira.student;

import oy.interact.tira.util.QueueInterface;

public class QueueImplementation<E> implements QueueInterface<E> {

  private class Node<T> {
    T data;
    Node<T> nextNode;
    Node<T> previousNode;

    public Node(T data) {
      this.data = data;
    }
  }

  private Node<E> headNode;
  private Node<E> tailNode;
  private int size;
  private int capacity;

  public QueueImplementation(){}

  public QueueImplementation(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public int capacity() {
    return capacity;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void enqueue(Object element) throws OutOfMemoryError, NullPointerException {
    if (element == null) {
      throw new NullPointerException("Added element can't be null");
    }
    Node newNode = new Node<>(element);
    
    if (size() == 0) {
      headNode = newNode;
      tailNode =  newNode;
    } else {
      tailNode.nextNode = newNode;
      newNode.previousNode = tailNode;
      tailNode = newNode;
    }
    ++size;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E dequeue() throws IllegalStateException {
    
    if (size() == 0) {
      throw new IllegalStateException("Can't dequeue, the queue is empty");
    }
    
    Node tempNode = new Node<>(headNode);
    
    headNode = tempNode.nextNode;
    tempNode.nextNode = null; 
    headNode = null;
    --size;
    return (E)tempNode.data;
  }

  @Override
  public E element() throws IllegalStateException {
    if (size == 0) {
      throw new IllegalStateException("Can't dequeue, the queue is empty");
    }
    return headNode.data;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    if (headNode == null) {
      return true;
    }
    return false;
  }

  @Override
  public void clear() {
    headNode = null;
    tailNode = null;
    size = 0;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append("[");

    if (!isEmpty()) {
      builder.append(headNode.data);
    }

    for (int i = 1; i < size; i++) {
      if (headNode.nextNode != null) {
        builder.append(", ");
        builder.append(headNode.nextNode.data);
        headNode = headNode.nextNode;
      }
    }
    builder.append("]");
    return builder.toString();
  }

}
