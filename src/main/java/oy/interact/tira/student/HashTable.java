package oy.interact.tira.student;

import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

public class HashTable<K extends Comparable<K>, V> implements TIRAKeyedContainer<K,V>{

  int size;
  final int CAPACITY = 32;

  private class LinkedListNode<T,R> {
    Pair<K,V> nodeData;
    LinkedListNode<T,R> nextNode;

    LinkedListNode(K key, V value) {
      nodeData = new Pair<>(key, value);
    }
  }
  
  @SuppressWarnings("unchecked")
  LinkedListNode<K,V> [] array = new LinkedListNode[CAPACITY];
  LinkedListNode<K,V> headNode;

  @Override
  public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
    int index = key.hashCode() % CAPACITY;
    LinkedListNode<K,V> newNode = new LinkedListNode<>(key, value);

    if (array[index] == null) {
      array[index] = newNode;
      headNode = newNode;
    } else {
      addToLinkedList(array[index], newNode);
    }
  }


  private void addToLinkedList(LinkedListNode<K,V> current, LinkedListNode<K,V> newNode) {
    if (current.nextNode == null) {
      current.nextNode = newNode;
    } else {
      addToLinkedList(current.nextNode, newNode);
    }
  }


  @Override
  public V get(K key) throws IllegalArgumentException {
    int index = key.hashCode() % CAPACITY;

    if (array[index] != null) {
      return findFromLinkedList(array[index], key);
    }
    return null;
  }

  private V findFromLinkedList(LinkedListNode<K,V> current, K key) {
    if (current.nodeData.getKey().equals(key)) {
      return current.nodeData.getValue();
    } else {
      findFromLinkedList(current.nextNode, key);
    }
    return null;
  }


  @Override
  public V remove(Comparable key) throws IllegalArgumentException {
    
    // Here I need to do LinkedList removal

    return null;
  }

  @Override
  public V find(Predicate searcher) {
    // index = calculateIndexFor(searcher.get()?);
    // return A[index];
    return null;
  }

  @Override
  public int size() {
    return array.length;
  }

  @Override
  public int capacity() {
    if (array.length > CAPACITY) {
      return 0;
    }
    return CAPACITY;
  }

  @Override
  public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
    /* if (capacity > capacity()) {
      
    } */
  }

  @Override
  public void clear() {
    array = null;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Pair<K,V>[] toArray() throws Exception {
    Pair<K,V>[] returnArray = new Pair[array.length];
    for (int i = 0; i < array.length; i++) {
      returnArray[i] = array[i].nodeData;
    }
    return returnArray;
  }
  
  /* private boolean addCapacity(Pair<K,V> [] array) {
    boolean result = false;
  } */

}
