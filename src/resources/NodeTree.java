package resources;

public class NodeTree <T extends Comparable<T>> implements Comparable<NodeTree<T>> {
  private T elem;
  private NodeTree<T> child;
  private NodeTree<T> sibling;
  private NodeTree<T> parent;
  private int degree;

  public NodeTree(T elem) {
    this.elem = elem;
  }

  public T getElem() {
    return this.elem;
  }

  public NodeTree<T> getParent() {
    return this.parent;
  }
  public NodeTree<T> getChild() {
    return this.child;
  }

  public NodeTree<T> getSibling() {
    return this.sibling;
  }

  public int getDegree() {
    return this.degree;
  }

  public void setElem(T elem) {
    this.elem= elem;
  }

  public void setParent(NodeTree<T> parent) {
    this.parent= parent;
  }

  public void setChild(NodeTree<T> child) {
    this.child= child;
  }

  public void setDegree(int newDegree) {
    this.degree= newDegree;
  }


  public void setSibling(NodeTree<T> sibling) {
    this.sibling = sibling;
  }

  @Override
  public int compareTo(NodeTree<T> other) {
    return this.elem.compareTo(other.elem);
  }

  public void print(int level) {
    String father, sib;
    NodeTree<T> curr = this;
    while(curr != null){
      father="";
      sib="";
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < level; i++) {
        sb.append("");
      }
      if(curr.getParent()!=null){
        father = curr.getParent().elem.toString();
      }
      if(curr.getSibling()!=null){
        sib = curr.getSibling().elem.toString();
      }
      sb.append((curr.elem.toString()+"(f:"+father+")(s:"+sib+")"));
      System.out.println(sb.toString());
      if(curr.child != null){
        curr.child.print(level+1);
      }
      curr = curr.getSibling();
    }
  }
}
