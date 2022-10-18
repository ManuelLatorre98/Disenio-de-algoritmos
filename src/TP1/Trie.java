package TP1;

import resources.NodeTree;

import java.util.LinkedList;

public class Trie {
  public TrieNode root;

  public Trie(TrieNode root){
    this.root=root;
  }

  public void insert(String key, String[] synonyms){
    int length = key.length();
    int index;
    TrieNode current = root;

    for (int i = 0; i < length; i++) {
      index = key.charAt(i) - 'a';
      if(current.getChildren(index) == null) {
        current.setChildren(index, new TrieNode(key.charAt(i)));
      }
      current = current.getChildren(index);
    }

    current.setEndOfWord(key, synonyms);//Setea al último nodo como final de palabra y le asigna la lista de sinónimos
  }

  public TrieNode search(String key){
    int index;
    TrieNode currentNode = root;

    for (int i = 0; i < key.length(); i++) {
      char currentLetter = key.charAt(i);
      if(currentNode.containLetter(currentLetter)){
        index=key.charAt(i)-'a';
        currentNode = currentNode.getChildren(index);
      }else{
        return null;
      }
    }
    return (currentNode);
  }

  public LinkedList<String> getSynonyms(String key){
    TrieNode lastNodeKey = search(key);
    if(lastNodeKey==null){
      return null;
    }
    return lastNodeKey.getSynonyms();
  }

  public boolean addSynonymToWord(String key, String synonym){
    TrieNode lastNodeKey = search(key);
    if(lastNodeKey==null){
      return false;
    }
    lastNodeKey.addSynonym(synonym);
    return true;
  }
  public LinkedList<String> listOfWords() {
    LinkedList<String> list = new LinkedList<String>();
    this.auxListOfWords(root, list);

    return list;
  }
  private void auxListOfWords(TrieNode currentNode, LinkedList<String> list) {
    int alphabetSize = root.getAlphabet_size();
    if(currentNode.isEndOfWord()){//Si es el final de la palabra el nodo posee a la palabra entera
      list.add(currentNode.getWord());//Listo la palabra
    }
    for (int i = 0; i < alphabetSize; i++) {//Por más que la palabra fuese final puede seguir teniendo nodos hijos asi que se recorre igual
      if(currentNode.getChildren(i)!=null){//Si se tiene un nodo para bajar
        this.auxListOfWords(currentNode.getChildren(i), list);//Se mueve al siguiente hijo
      }
    }
  }
}
