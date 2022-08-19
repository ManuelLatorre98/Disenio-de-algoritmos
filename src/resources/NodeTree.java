package resources;

public class NodeTree implements Comparable<NodeTree> {
  private Object elem;
  private NodeTree child;
  private NodeTree sibling;
  private NodeTree parent;
  private int degree;

  public NodeTree(Object elem) {
    this.elem = elem;
  }

  public Object getElem() {
    return this.elem;
  }

  public NodeTree getParent() {
    return this.parent;
  }
  public NodeTree getChild() {
    return this.child;
  }

  public NodeTree getSibling() {
    return this.sibling;
  }

  public int getDegree() {
    return this.degree;
  }

  public void setElem(Object elem) {
    this.elem= elem;
  }

  public void setParent(NodeTree parent) {
    this.parent= parent;
  }

  public void setChild(NodeTree child) {
    this.child= child;
  }

  public void setDegree(int newDegree) {
    this.degree= newDegree;
  }


  public void setSibling(NodeTree sibling) {
    this.sibling = sibling;
  }

  @Override
  public int compareTo(NodeTree other) {
    int compare=0;
    float count;
    System.out.println(other.elem instanceof Number );
    try {
      if(this.elem instanceof Number && other.elem instanceof Number) {
        count= ((Number)this.elem).floatValue() - ((Number)other.elem).floatValue();
        if(count>0) {
          compare= 1;
        }else {
          if(count<0) {
            compare=-1;
          }
        }
      }else {
        if(this.elem instanceof String && other.elem instanceof String) {
          compare= ((String)this.elem).compareTo((String)other.elem);
        }else {
          throw new RuntimeException("The nodes only can contain Numbers or Strings");
        }
      }
    }catch(Exception e) {
      System.err.println(e.toString());
      System.exit(0);
    }
    return compare;
  }

  public void print(int level) {
    String father, sib;
    NodeTree curr = this;
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
