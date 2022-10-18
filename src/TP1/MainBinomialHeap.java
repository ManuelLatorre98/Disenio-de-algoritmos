package TP1;

import resources.NodeTree;

public class MainBinomialHeap<T extends Comparable<T>> {
  public static void main(String[] args) {
    NodeTree<Integer> head1= new NodeTree<Integer>(2);
    NodeTree<Integer> head2= new NodeTree<Integer>(3);
    NodeTree<Integer> head3= new NodeTree<Integer>(5);
    NodeTree<Integer> head4= new NodeTree<Integer>(8);
    BinomialHeap<Integer> heap1 = new BinomialHeap<>(head1);
    BinomialHeap<Integer> heap2 = new BinomialHeap<>(head2);
    BinomialHeap<Integer> heap3 = new BinomialHeap<>(head3);
    BinomialHeap<Integer> heap4 = new BinomialHeap<>(head4);


    //Ejemplo decrease key de la pagina
    heap2.insert(5);
    heap2.insert(17);
    heap2.insert(18);
    heap2.insert(22);
    heap2.insert(9);
    heap2.insert(16);
    heap2.insert(20);

    NodeTree<Integer> veinte= heap2.search(20);
    heap2.decreaseKey(veinte,7);
    heap2.print();
  }
}
