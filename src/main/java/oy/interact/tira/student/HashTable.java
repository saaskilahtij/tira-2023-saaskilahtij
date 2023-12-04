package oy.interact.tira.student;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

public class HashTable<K extends Comparable<K>, V> implements TIRAKeyedContainer<K,V>{

  
  private class LinkedListNode<T,R> {
    Pair<K,V> nodeData;
    LinkedListNode<T,R> nextNode;
    LinkedListNode<K,V> headNode;
    
    LinkedListNode(K key, V value) {
      nodeData = new Pair<>(key, value);
    }
  }
  
  private int capacity = 32;
  private int elementCount;
  @SuppressWarnings("unchecked")
  private LinkedListNode<K,V> [] array = new LinkedListNode[capacity];


  @Override
  public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {  
  
    if (key == null) {
      throw new IllegalArgumentException("Given key can not be null");
    }
    if (value == null) {
      throw new IllegalArgumentException("Given value can not be null");
    }

    ensureCapacity(capacity + 1);
    int index = calculateIndex(key);
    LinkedListNode<K,V> newNode = new LinkedListNode<>(key, value);

    if (array[index] == null) {
      array[index] = newNode;
      array[index].headNode = newNode;
      elementCount++;
    } else {
      addToLinkedList(array[index], newNode);
    }
  }


  private void addToLinkedList(LinkedListNode<K,V> current, LinkedListNode<K,V> newNode) {
    
    boolean isDuplicate = current.nodeData.getKey().equals(newNode.nodeData.getKey());
    
    if (isDuplicate && current.nextNode == null) {
      newNode.headNode = current.headNode;
      newNode.nextNode = current.nextNode;
      current = newNode;
    } else if (current.nextNode == null) {
      // ensureCapacity(capacity + 1);
      current.nextNode = newNode;
      elementCount++;
    } else {
      addToLinkedList(current.nextNode, newNode);
    }
  }


  @Override
  public V get(K key) throws IllegalArgumentException {
    
    if (key == null) {
      throw new IllegalArgumentException("Given key can not be null");
    }
    
    int index = calculateIndex(key);

    if (array[index] != null) {
      return findFromLinkedList(array[index], key);
    }
    return null;
  }

  private V findFromLinkedList(LinkedListNode<K,V> current, K key) {
    if (current == null) {
      return null;
    }
    
    if (current.nodeData.getKey().equals(key)) {
      return current.nodeData.getValue();
    } else {
      return findFromLinkedList(current.nextNode, key);
    }
  }


  @Override
  public V remove(K key) throws IllegalArgumentException {
    
    if (key == null) {
      throw new IllegalArgumentException("Given key can not be null");
    }
    
    int index = calculateIndex(key);
    LinkedListNode<K,V> headNode = array[index];
    if (key.equals(headNode.nodeData.getKey())) {
      headNode.nodeData.getValue();
      array[index] = headNode.nextNode;
      elementCount--;
      return headNode.nodeData.getValue();
    }
    return removeNode(key, headNode, headNode.nextNode);
  }

  private V removeNode(K key, LinkedListNode<K,V> current, LinkedListNode<K,V> nextNode) {
    V returnValue = null;

    if (nextNode.nodeData.getKey().equals(key)) {
      returnValue = nextNode.nodeData.getValue();
      current.nextNode = nextNode.nextNode;
      nextNode = null;
      elementCount--;
    } else {
      removeNode(key, nextNode, nextNode.nextNode);
    }
    return returnValue;
  }


  @Override
  public V find(Predicate<V> searcher) {
    
    V result = null;

    for (int i = 0; i < array.length; i++) {
      if (array[i] != null) {
        if (searcher.test(array[i].nodeData.getValue())) {  
          result = array[i].nodeData.getValue();
        }
      }
    }

    return result;
  }

  @Override
  public int size() {
    return elementCount;
  }

  @Override
  public int capacity() {
    if (array.length > capacity) {
      return array.length;
    }
    return capacity;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
    
    if (capacity < 0) {
      throw new IllegalArgumentException("Given capacity can not be negative");
    }

    if (capacity <= capacity) {
      return;
    }

    try {
      LinkedListNode<K,V> [] newlyAllocated = new LinkedListNode[capacity *= 2];
      for (int i = 0; i < size(); i++) {
        if (array[i] != null) {
          int newlyAllocatedIndex = calculateIndex(array[i].nodeData.getKey());
          newlyAllocated[newlyAllocatedIndex] = array[i];
          array = newlyAllocated;
        }
      }
    } catch (OutOfMemoryError e) {
      throw new OutOfMemoryError("Out of memory when tried allocating more space");
    }
  }
    

  @Override
  @SuppressWarnings("unchecked")
  public void clear() {
    capacity = 32;
    array = new LinkedListNode[capacity];
    elementCount = 0;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Pair<K,V>[] toArray() throws Exception {
    
    Pair<K,V>[] returnArray = new Pair[elementCount];
    AtomicInteger returnArrayIndex = new AtomicInteger(0);
    AtomicInteger initialArrayIndex = new AtomicInteger(0);
    
    // Loops through the array; increment when index gets added, but not when adding linked list
    while (initialArrayIndex.get() < array.length) {
      if (array[initialArrayIndex.get()] != null) {
        if (array[initialArrayIndex.get()].nextNode == null) {
          returnArray[returnArrayIndex.get()] = array[initialArrayIndex.get()].nodeData;
          returnArrayIndex.incrementAndGet();
        } else {
          arraytisizeLinkedList(returnArray, array[initialArrayIndex.get()], returnArrayIndex);
        }
      }
      if (array.length > initialArrayIndex.get()) {
        initialArrayIndex.incrementAndGet();
      }
    }
    
    return returnArray;
  }
  
  private void arraytisizeLinkedList(Pair<K,V> [] array, LinkedListNode<K,V> node, AtomicInteger index) {
    if (node.nextNode == null) {
      return;
    }

    array[index.get()] = node.nodeData;
    index.incrementAndGet();
    arraytisizeLinkedList(array, node.nextNode, index);
  }

  private int calculateIndex(K key) throws IllegalArgumentException {
    if (key == null) {
      throw new IllegalArgumentException("Given key can not be null");
    }
    return (key.hashCode() % 0x7fffffff) % capacity;
  }

}