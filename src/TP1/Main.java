package TP1;

import resources.NodeTree;

public class Main {
  public static void main(String[] args) {
    NodeTree head1= new NodeTree(2);
    NodeTree head2= new NodeTree(3);
    NodeTree head3= new NodeTree(5);
    NodeTree head4= new NodeTree(8);
    BinomialHeap heap1 = new BinomialHeap(head1);
    BinomialHeap heap2 = new BinomialHeap(head2);
    BinomialHeap heap3 = new BinomialHeap(head3);
    BinomialHeap heap4 = new BinomialHeap(head4);


    /*heap2.insert(12);
    heap2.insert(6);
    heap2.insert(14);

    heap3.insert(11);*/

    heap2.insert(5);
    heap2.insert(17);
    heap2.insert(18);
    heap2.insert(22);
    heap2.insert(9);
    heap2.insert(16);
    heap2.insert(20);

    NodeTree veinte= heap2.search(20);
    heap2.decreaseKey(veinte,7);
    heap2.print();
  }
}
