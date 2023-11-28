package oy.interact.tira.student;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.Comparator;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;
import oy.interact.tira.model.TreeNode;

public class BinarySearchTree<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K,V>  {

  TreeNode<K,V> root = null;
  Comparator<K> comparator;
  int nodeCount = 0;
  int capacity = Integer.MAX_VALUE; 
  int maxDepth; // Miksi max depth = 0?

  public BinarySearchTree() {}

  public BinarySearchTree(Comparator<K> comparator) {
    this.comparator = comparator;
  }


  // Children indexing doesn't work!
  @Override
  public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
    if (key == null) {
      throw new IllegalArgumentException("Given key can not be null");
    }
    if (value == null) {
      throw new IllegalArgumentException("Given value can not be null");
    }
    TreeNode<K,V> newNode = new TreeNode<K,V>(key, value);
    
    if (root == null) {
      root = newNode;
      nodeCount++;
      maxDepth = 0;
    } else if (root.insert(newNode, root, comparator)) {
      maxDepth =  Math.max(maxDepth, root.getTreeDepth());
      nodeCount++;
    }
  }

  @Override
  public V get(K key) throws IllegalArgumentException {
    if (key == null) {
      throw new IllegalArgumentException("Given key can not be null");
    }
    if (root == null) {
      return null;
    }
    return root.find(key, root, comparator);
  }

  @Override
  public V remove(K key) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }


  @Override
  public V find(Predicate<V> searcher) {
    if (root == null) {
      return null;
    }
    return root.findPredicate(searcher);
  }


  @Override
  public int size() {
    return nodeCount;
  }

  @Override
  public int capacity() {
    return capacity;
  }

  @Override
  public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
    if (capacity > Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Capacity can not be bigger than MAX INT");
    } else if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity can not be zero or negative");
    } else {
      this.capacity = capacity;
    }
  }

  @Override
  public void clear() {
    root = null;
    nodeCount = 0;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Pair<K, V>[] toArray() throws Exception {
    Pair<K,V> [] array = new Pair[size()];
    AtomicInteger index = new AtomicInteger(-1);
    if (root == null) {
      return array;
    }
    root.makeArray(array, index);
    return array;
  }

  @Override
  public int indexOf(K itemKey) {
    if (root == null) {
      return -1;      
    }
    AtomicInteger index = new AtomicInteger(0);
    return root.findIndex(itemKey, index).get();
  }


  // Ei vielä päivitetty ja ei toimi
  @Override
  public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
    if (index >= size()) {
      throw new IndexOutOfBoundsException("Given index is over the size of the tree");
    }
    if (index < 0) {
      throw new IndexOutOfBoundsException("Given index can not be negative");
    }
    if (root == null) {
      return null;
    }
    return getIndexHelper(root, index);
  }

  // Tämä ei toimi koska lasten indeksit ei toimi
  private Pair<K,V> getIndexHelper(TreeNode<K, V> node, int indexToFind) {

    if (node.getLeftChildren() > indexToFind) {
      return getIndexHelper(node.getLeft(), indexToFind);
    } else if (node.getLeftChildren() < indexToFind) {
      indexToFind -= node.getLeftChildren() + 1;
      return getIndexHelper(node.getRight(), indexToFind);
    } else {
      return new Pair<K,V>(node.getKey(), node.getValue());
    }
  }


  @Override
  public int findIndex(Predicate<V> searcher) {
    if (root == null) {
      return -1;
    }
    return findIndexHelper(root, searcher, -1);
  }

  private int findIndexHelper(TreeNode<K,V> node, Predicate<V> searcher, int targetIndex) {
    int currentIndex = 0;
    if (node.getLeft() != null) {
      targetIndex = findIndexHelper(node.getLeft(), searcher, targetIndex);
      if (targetIndex != -1) {
        return targetIndex;
      }
    }
    
    if (searcher.test(node.getValue())) {
      return currentIndex;
    }
    currentIndex++;

    if (node.getRight() != null) {
      targetIndex = findIndexHelper(node.getRight(), searcher, targetIndex);
      if (targetIndex != -1) {
        return targetIndex;
      }
    }
    return targetIndex;
  }


  @Override
  public void accept(Visitor<K, V> visitor) throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'accept'");
  }

  public int getMaxDepth() {
    return maxDepth;
  }

}
