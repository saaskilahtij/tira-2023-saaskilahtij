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
  K nextNodeKey;


  public TreeNode() {}

  public TreeNode(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public boolean insert(TreeNode<K,V> newNode, TreeNode<K,V> parent, Comparator<K> comparator) {
    boolean result = false;

    // Jos duplikaatti, tekee linkitetyn listan
    if (comparator.compare(key, newNode.key) == 0) {
      parent.nextNodeKey = newNode.key;
      return false;
    }

    if (comparator.compare(key, newNode.key) < 0) {
      treeDepth++;
      if (leftChild == null) {
        parent.addLeftChild();
        parent.leftChild = new TreeNode<>(newNode.key, newNode.value);
        result = true;
      } else {
        if (leftChild.insert(newNode, parent.leftChild, comparator)) {
          parent.addLeftChild();
          result = true;
        } else {
          result = false;
        }
      }
    } else if (comparator.compare(key, newNode.key) > 0) {
      treeDepth++;
      if (rightChild == null) {
        parent.addRightChild();
        parent.rightChild = new TreeNode<>(newNode.key, newNode.value);
        result = true;
      } else {
        if (rightChild.insert(newNode, parent.rightChild, comparator)) {
          parent.addRightChild();
          result = true;
        } else {
          result = false;
        }
      }
    }
    return result;
  }


  // MF find toimii muuten, mutta kaatuu koodarinimiin
  public V find(K keyToFind, TreeNode<K,V> root, Comparator<K> comparator) {
    int comparison = comparator.compare(root.key, keyToFind);
    V result = null;
    if (comparison == 0 || leftChild == null || rightChild == null) {
      result = root.value;
    }

    if (comparison < 0) {
      if (leftChild != null) {
        result = root.find(keyToFind, root.leftChild, comparator);
      }
    } else if (comparison > 0){
      if (rightChild !=  null) {
        result = root.find(keyToFind, root.rightChild, comparator);
      }
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
      findIndex(key, index);
    } 

    if (this.key.equals(key)) {
      return index;
    }
    
    index.incrementAndGet();
    if (rightChild != null) {
      findIndex(key, index);
    }

    return new AtomicInteger(-1);
  }


  public void makeArray(Pair<K,V>[] array, AtomicInteger index) {

    if (leftChild != null) {
      leftChild.makeArray(array, index);
    }
    index.incrementAndGet();
    array[index.get()] = new Pair<K,V>(key, value);
    if (rightChild != null) {
      rightChild.makeArray(array, index);
    }
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

  public K getNextNodeKey() {
    return nextNodeKey;
  }

  public void setNextNodeKey(K nextNodeKey) {
    this.nextNodeKey = nextNodeKey;
  }

}
