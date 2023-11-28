package oy.interact.tira.student;

import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

public class HashTable<K extends Comparable<K>, V> implements TIRAKeyedContainer<K,V>{

  int size;
  final int CAPACITY = 500000;
  @SuppressWarnings("unchecked")
  Pair<K,V> [] array = new Pair[CAPACITY];

  /* 
    Käytä törmäyksessä linkitettyä listaa!
  */

  @Override
  public void add(Comparable key, Object value) throws OutOfMemoryError, IllegalArgumentException {
    
    // index = calculateIndexFor(value);
    // A[index] = e
    // return true;
  }

  @Override
  public Object get(Comparable key) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'get'");
  }

  @Override
  public Object remove(Comparable key) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }

  @Override
  public Object find(Predicate searcher) {
    // index = calculateIndexFor(searcher.get()?);
    // return A[index];
    return null;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'size'");
  }

  @Override
  public int capacity() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'capacity'");
  }

  @Override
  public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'ensureCapacity'");
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'clear'");
  }

  @Override
  public Pair[] toArray() throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'toArray'");
  }
  
}
