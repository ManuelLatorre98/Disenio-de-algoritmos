package TP1;

import java.util.ArrayList;
import java.util.List;

import resources.NodeTree;

public class BinomialHeap {
  private NodeTree head;

  public BinomialHeap(NodeTree head) {
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
  public void insert(Object elem) {
    NodeTree node= new NodeTree(elem);
    BinomialHeap tempHeap= new BinomialHeap(node);
    head = union(tempHeap);
  }

  /*Itera a traves de las raices de cada arbol del heap buscando
  * el minimo*/
  public Object findMinimum() {//O(N)
    Object minimum;
    if(head == null) {
      minimum = null;
    }else {
      NodeTree min= head;
      NodeTree next= min.getSibling();

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
  public NodeTree search(Object elem) {
    NodeTree nodeSearched = null;
    List<NodeTree> nodes= new ArrayList<NodeTree>();
    nodes.add(head); //Adds head to list
    while(!nodes.isEmpty()) {
      NodeTree curr = nodes.get(0);
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
  public void decreaseKey(NodeTree node, Object newElem) {
    node.setElem(newElem);
    bubbleUp(node, false);
  }

  /*Elimina el nodo pasado por parametro*/
  public void delete(NodeTree node) {
    node = bubbleUp(node,true);
    if(head == node) {
      removeTreeRoot(node,null);
    }else {
      NodeTree prev = head;
      while(prev.getSibling().compareTo(node) != 0) {
        prev = prev.getSibling();
      }
      removeTreeRoot(node,prev);
    }
  }

  private NodeTree bubbleUp(NodeTree node, boolean toRoot) {
    NodeTree parent = node.getParent();
    while(parent != null && (toRoot || node.compareTo(parent)<0)) {
      Object temp = node.getElem();
      node.setElem(parent.getElem());
      parent.setElem(temp);
      node = parent;
      parent = parent.getParent();
    }
    return node;
  }

  public Object extractMin() {
    Object minimum;
    if(head == null) {
      minimum= null;
    }else {
      NodeTree min = head;
      NodeTree minPrev = null;
      NodeTree next = min.getSibling();
      NodeTree nextPrev = min;

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

  private void removeTreeRoot(NodeTree root, NodeTree prev) {
    //Removes root from the heap
    if(root == head) {
      head = root.getSibling();
    }else {
      prev.setSibling(root.getSibling());
    }

    //Reverse the order of roots children and make a new heap
    NodeTree newHead = null;
    NodeTree child = root.getChild();
    while(child != null) {
      NodeTree next = child.getSibling();
      child.setSibling(newHead);
      child.setParent(null);
      newHead = child;
      child = next;
    }
    BinomialHeap newHeap = new BinomialHeap(newHead);

    //Union the heaps and set its head as this.head
    head = union(newHeap);
  }

  private void linkTree(NodeTree minNodeTree, NodeTree other) {
    other.setParent(minNodeTree);
    other.setSibling(minNodeTree.getChild());
    minNodeTree.setChild(other);
    minNodeTree.setDegree(minNodeTree.getDegree()+1); //Increments in 1 the degree
  }

  /*Fusiona los 2 heaps combinando continuamente arboles del mismo
  * orden hasta que no existan dos arboles del mismo orden  */
  public NodeTree union(BinomialHeap heap) {
    NodeTree newHead = merge(this, heap);

    head = null;
    heap.head = null;
    if(newHead == null) {
      newHead=null;
    }else {
      NodeTree prev = null;
      NodeTree curr = newHead;
      NodeTree next = newHead.getSibling();

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

  private NodeTree merge(BinomialHeap heap1, BinomialHeap heap2) {
    NodeTree newHead;
    if(heap1.head == null){
      newHead = heap2.head;
    }else if(heap2.head == null){
      newHead = heap1.head;
    }else{
      NodeTree head;
      NodeTree heap1Next = heap1.head;
      NodeTree heap2Next = heap2.head;

      if(heap1.head.getDegree() <= heap2.head.getDegree()) {
        head = heap1.head;
        heap1Next = heap1Next.getSibling();
      }else{
        head = heap2.head;
        heap2Next = heap2Next.getSibling();
      }

      NodeTree tail = head;
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
