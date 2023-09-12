package TP1;

import java.util.ArrayList;
import java.util.List;
import resources.NodeTree;

public class BinomialHeap <T extends Comparable<T>> {
  private NodeTree<T> head;

  public BinomialHeap(NodeTree<T> head) {
    this.head = head;
  }

  //Verifica si el arbol esta vacio
  public boolean isEmpty() {//O(1)
    return head == null;
  }

  //Vacia el arbol
  public void clear() {//O(1)
    head = null;
  }

/* Crea un nuevo heap con el elemento parametro y luego
* lo combina usando la operacion de union*/
  public void insert(T elem) {
    NodeTree<T> node= new NodeTree<T>(elem);
    BinomialHeap<T> tempHeap= new BinomialHeap<T>(node);
    head = union(tempHeap);
  }

  /*Itera a traves de las raices de cada arbol del heap buscando
  * el minimo*/
  public Object findMinimum() {//O(N)
    Object minimum;
    if(head == null) {
      minimum = null;
    }else {
      NodeTree<T> min= head;
      NodeTree<T> next= min.getSibling();

      while(next != null) {
        if (next.compareTo(min)<0) {
          min=next;
        }
        next = next.getSibling();
      }
      minimum= min.getElem();
    }
    return minimum;
  }

  /*Busca y devuelve el nodo con el elemento parametro*/
  public NodeTree<T> search(T elem) {
    NodeTree<T> nodeSearched = null;
    List<NodeTree<T>> nodes= new ArrayList<NodeTree<T>>();
    nodes.add(head); //Adds head to list
    while(!nodes.isEmpty()) {
      NodeTree<T> curr = nodes.get(0);
      nodes.remove(0);
      if(curr.getElem() == elem) {
        nodeSearched = curr;
      }
      if(curr.getSibling() != null) {
        nodes.add(curr.getSibling());
      }
      if(curr.getChild() != null) {
        nodes.add(curr.getChild());
      }
    }
    return nodeSearched;
  }

  /*Reduce el valor del nodo especificado y lo hace "flotar" hacia
  * arriba atraves de sus ancestos hasta que el arbol cumple condicion
  * de heap */
  public void decreaseKey(NodeTree<T> node, T newElem) {
    node.setElem(newElem);
    bubbleUp(node, false);
  }

  /*Elimina el nodo pasado por parametro*/
  public void delete(NodeTree<T> node) {
    node = bubbleUp(node,true);
    if(head == node) {
      removeTreeRoot(node,null);
    }else {
      NodeTree<T> prev = head;
      while(prev.getSibling().compareTo(node) != 0) {
        prev = prev.getSibling();
      }
      removeTreeRoot(node,prev);
    }
  }

  private NodeTree<T> bubbleUp(NodeTree<T> node, boolean toRoot) {
    NodeTree<T> parent = node.getParent();
    while(parent != null && (toRoot || node.compareTo(parent)<0)) {
      T temp = node.getElem();
      node.setElem(parent.getElem());
      parent.setElem(temp);
      node = parent;
      parent = parent.getParent();
    }
    return node;
  }

  public T extractMin() {
    T minimum;
    if(head == null) {
      minimum= null;
    }else {
      NodeTree<T> min = head;
      NodeTree<T> minPrev = null;
      NodeTree<T> next = min.getSibling();
      NodeTree<T> nextPrev = min;

      while(next != null) {
        if(next.compareTo(min) < 0) {
          min = next;
          minPrev = nextPrev;
        }
        nextPrev= next;
        next = next.getSibling();
      }
      removeTreeRoot(min, minPrev);
      minimum= min.getElem();
    }
    return minimum;
  }

  private void removeTreeRoot(NodeTree<T> root, NodeTree<T> prev) {
    //Removes root from the heap
    if(root == head) {
      head = root.getSibling();
    }else {
      prev.setSibling(root.getSibling());
    }

    //Reverse the order of roots children and make a new heap
    NodeTree<T> newHead = null;
    NodeTree<T> child = root.getChild();
    while(child != null) {
      NodeTree<T> next = child.getSibling();
      child.setSibling(newHead);
      child.setParent(null);
      newHead = child;
      child = next;
    }
    BinomialHeap<T> newHeap = new BinomialHeap<T>(newHead);

    //Union the heaps and set its head as this.head
    head = union(newHeap);
  }

  private void linkTree(NodeTree<T> minNodeTree, NodeTree<T> other) {
    other.setParent(minNodeTree);
    other.setSibling(minNodeTree.getChild());
    minNodeTree.setChild(other);
    minNodeTree.setDegree(minNodeTree.getDegree()+1); //Increments in 1 the degree
  }

  /*Fusiona los 2 heaps combinando continuamente arboles del mismo
  * orden hasta que no existan dos arboles del mismo orden  */
  public NodeTree<T> union(BinomialHeap<T> heap) {
    NodeTree<T> newHead = merge(this, heap);
    head = null;
    heap.head = null;
    if(newHead == null) {
      newHead=null;
    }else {
      NodeTree<T> prev = null;
      NodeTree<T> curr = newHead;
      NodeTree<T> next = newHead.getSibling();

      while(next != null) {
        if(curr.getDegree() != next.getDegree() ||
                (next.getSibling() != null &&
                        next.getSibling().getDegree() == curr.getDegree())) {
          prev = curr;
          curr = next;
        }else {
          if(curr.compareTo(next) < 0) {
            curr.setSibling(next.getSibling());
            linkTree(curr,next);
          }else {
            if(prev == null) {
              newHead = next;
            }else {
              prev.setSibling(next);
            }
            linkTree(next,curr);
            curr = next;
          }
        }
        next = curr.getSibling();
      }
    }
    return newHead;
  }

  private NodeTree<T> merge(BinomialHeap<T> heap1, BinomialHeap<T> heap2) {
    NodeTree<T> newHead;
    if(heap1.head == null){
      newHead = heap2.head;
    }else if(heap2.head == null){
      newHead = heap1.head;
    }else{
      NodeTree<T> head;
      NodeTree<T> heap1Next = heap1.head;
      NodeTree<T> heap2Next = heap2.head;

      if(heap1.head.getDegree() <= heap2.head.getDegree()) {
        head = heap1.head;
        heap1Next = heap1Next.getSibling();
      }else{
        head = heap2.head;
        heap2Next = heap2Next.getSibling();
      }

      NodeTree<T> tail = head;
      while(heap1Next != null && heap2Next != null){
        if(heap1Next.getDegree() <= heap2Next.getDegree()){
          tail.setSibling(heap1Next);
          heap1Next = heap1Next.getSibling();
        }else{
          tail.setSibling(heap2Next);
          heap2Next = heap2Next.getSibling();
        }
        tail = tail.getSibling();
      }
      if(heap1Next != null){
        tail.setSibling(heap1Next);
      }else{
        tail.setSibling(heap2Next);
      }
      newHead = head;
    }
    return newHead;
  }

  public void print(){
    System.out.println("Binomial heap: ");
    if(head != null){
      head.print(0);
    }
  }
}
