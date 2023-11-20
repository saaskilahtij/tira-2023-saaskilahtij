package oy.interact.tira.student;

import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedContainer<K,V>  {

  // Node class
  private class Node {
    K key;
    V value;
    Node left;
    Node right;
    Node parent;

    protected void addNode(Node node) {
      if (this.key.compareTo(node.key) < 0) {
        if (this.left == null) {
          this.left = node;
        } else {
          this.left.addNode(node);
        }
      } else if (this.key.compareTo(node.key) > 0) {
          if (this.right == null) {
            this.right = node;
          } else {
            this.right.addNode(node);
          }
      }
    }
  }

  // Declaration of the root node
  Node root = null;

  @Override
  public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
    Node newNode = new Node();
    newNode.key = key;
    newNode.value = value;

    if (root == null) {
      root = newNode;
    } else {
      root.addNode(newNode);
    } 
  }

  @Override
  public V get(K key) throws IllegalArgumentException {
    Node currentNode = root;

    while (currentNode != null) {
      int comparison = currentNode.key.compareTo(key);

      if (comparison == 0) {
          return currentNode.value;
      } else if (comparison < 0) {
          currentNode = currentNode.right;
      } else {
          currentNode = currentNode.left;
      }
    }
    return null;
  }

  @Override
  public V remove(K key) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'remove'");
  }


  @Override
  public V find(Predicate<V> searcher) {
    Node currentNode = root;

    while (currentNode != null) {
      if (searcher.test(currentNode.value)) {
        return currentNode.value;
      } 
      // TODO: this mf function
    }

    return null;
  }


  @Override
  public int size() {
    return nodeCount(root);
  }

  private int nodeCount(Node root) {
    int count = 0;
    if (root != null) {
      count = count + nodeCount(root.left);
      count = count + nodeCount(root.right);
      return count + 1;
    }
    return count;
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
  public Pair<K, V>[] toArray() throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'toArray'");
  }




}