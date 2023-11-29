package oy.interact.tira.model;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import java.util.Comparator;
import oy.interact.tira.util.Pair;


public class TreeNode<K extends Comparable<K>, V> {
 
  K key;
  V value;
  int treeDepth;
  int leftChildren;
  int rightChildren;
  int currentIndex;
  TreeNode<K,V> leftChild;
  TreeNode<K,V> rightChild;
  TreeNode<K,V> parentNode;
  // nextNodeKey is for the linked list duplicate
  TreeNode<K,V> nextNode;
  AtomicInteger indexHelper = new AtomicInteger(-1);


  public TreeNode() {}

  public TreeNode(K key, V value) {
    this.key = key;
    this.value = value;
  }


  // Miksi 11 on 109 oikealla puolella testissä GenericTests rivillä 90
  public boolean insert(TreeNode<K,V> newNode, Comparator<K> comparator) {
    boolean result = false;

    if (this.value.equals(newNode.value)) {
      key = newNode.key;
      return false;
    }

    if (comparator.compare(newNode.key, key) < 0) {
      if (leftChild == null) {
        addLeftChild();
        leftChild = newNode;
        result = true;
      } else {
        if (leftChild.insert(newNode, comparator)) {
          addLeftChild();
          result = true;
        }
      }
    } else if (comparator.compare(newNode.key, key) > 0) {
      if (rightChild == null) {
        addRightChild();
        rightChild= newNode;
        result = true;
      } else {
        if (rightChild.insert(newNode, comparator)) {
          addRightChild();
          result = true;
        }
      }
    } else {
      addDuplicateNode(newNode);
      result = true;
    }
    return result;
  }

  public void addDuplicateNode(TreeNode<K,V> newNode) {
    if (nextNode == null) {
      new TreeNode<K,V>(newNode.key, newNode.value);
      nextNode = newNode;
    } else {
      addDuplicateNode(this.nextNode);
    }
  }

  public V find(K targetKey, Comparator<K> comparator) {

    V result = null;
    if (comparator.compare(targetKey, key) == 0) {
      return value;
    }
    
    if (comparator.compare(targetKey, key) < 0 && leftChild != null) {
      result = leftChild.find(targetKey, comparator);
    } else if (comparator.compare(targetKey, key) > 0 && rightChild != null) {
      result = rightChild.find(targetKey, comparator);
    }
    return result;
  }

  public V findPredicate(Predicate<V> searcher) {

    if (searcher.test(this.value)) {
      return this.value;
    }
    if (leftChild != null) {
      V temp = leftChild.findPredicate(searcher);
      if (temp != null) {
        return temp;
      }
    }
    if (rightChild != null) {
      V temp = rightChild.findPredicate(searcher);
      if (temp != null) {
        return temp;
      }
    }
    return null;
  }


  public AtomicInteger findIndex(K key, AtomicInteger index) {

    if (leftChild != null) {
      return leftChild.findIndex(key, index);
    } 

    if (this.key.equals(key)) {
      return index; 
    }
    
    index.incrementAndGet();
    if (rightChild != null) {
      rightChild.findIndex(key, index);
    }

    return new AtomicInteger(-1);
  }

  public AtomicInteger findIndexPredicate(AtomicInteger index, Predicate<V> searcher) {
    
    if (leftChild != null) {
      indexHelper = leftChild.findIndexPredicate(index, searcher);
      if (index.get() != -1) {
        return indexHelper;
      }
    }
    
    if (searcher.test(this.value)) {
      return index;
    }
    
    index.incrementAndGet();

    if (rightChild != null) {
      indexHelper = rightChild.findIndexPredicate(index, searcher);
      if (index.get() != -1) {
        return indexHelper;
      }
    }
    return indexHelper;
  }

  public void makeArray(Pair<K,V>[] array, AtomicInteger index) {

    if (leftChild != null) {
      leftChild.makeArray(array, index);
    }

    if (nextNode == null) {
      array[index.get()] = new Pair<K,V>(key, value);
      index.incrementAndGet();
    } else {
      array[index.get()] = new Pair<K,V>(key, value);
      index.incrementAndGet();
      traverseLinkedList(array, index, this);
    }

    if (rightChild != null) {
      rightChild.makeArray(array, index);
    }
  }

  private void traverseLinkedList(Pair<K,V>[] array, AtomicInteger index, TreeNode<K,V> node) {

    if (nextNode == null) {
      return;
    }
    
    array[index.get()] = new Pair<K,V>(nextNode.key, nextNode.value);
    index.incrementAndGet();
  }

  public K getKey() {
    return key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  public V getValue() {
    return value;
  }

  public void setValue(V value) {
    this.value = value;
  }

  public TreeNode<K,V> getLeft() {
    return leftChild;
  }

  public void setLeft(TreeNode<K,V> leftChild) {
    this.leftChild = leftChild;
  }

  public TreeNode<K,V> getRight() {
    return rightChild;
  }

  public void setRight(TreeNode<K,V> rightChild) {
    this.rightChild = rightChild;
  }

  public TreeNode<K,V> getParent() {
    return parentNode;
  }

  public void setParent(TreeNode<K,V> parent) {
    this.parentNode = parent;
  }

  public int getTreeDepth() {
    return treeDepth;
  }

  public int getLeftChildren() {
    return leftChildren;
  }

  public int getRightChildren() {
    return rightChildren;
  }

  public void addLeftChild(){
    leftChildren++;
  }

  public void addRightChild(){
    rightChildren++;
  }

  public TreeNode<K,V> getNextNode() {
    return nextNode;
  }

}
