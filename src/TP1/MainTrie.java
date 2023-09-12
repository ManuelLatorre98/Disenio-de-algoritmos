package TP1;

public class MainTrie {
  public static void main(String[] args) {
    TrieNode root = new TrieNode();
    Trie tree = new Trie(root);

    String keys[]= {"hola", "que", "tal"};
    String output[] = {"No se encuentra en el trie", "Encontrado en el trie"};
    String sinonimos[] = {"h","q","t"};

    for (int i = 0; i < keys.length; i++) {
      tree.insert(keys[i], sinonimos);
    }
    tree.insert("clave", sinonimos);
    if(tree.search("hola")!=null)
      System.out.println("hola --- " + output[1]);
    else System.out.println("hola --- " + output[0]);

    if(tree.search("que")!=null)
      System.out.println("que --- " + output[1]);
    else System.out.println("que --- " + output[0]);

    if(tree.search("tal")!=null)
      System.out.println("tal --- " + output[1]);
    else System.out.println("tal --- " + output[0]);

    if(tree.search("profe")!=null)
      System.out.println("profe --- " + output[1]);
    else System.out.println("profe --- " + output[0]);

    System.out.println("root: "+root.getChildren(7).getLetter());
    System.out.println(tree.getSynonyms("hola"));
    tree.addSynonymToWord("hola","eyyy");

    System.out.println(tree.listOfWords().toString());
    tree.addSynonymToWord("clave","key");
    System.out.println(tree.getSynonyms("clave"));
  }
}