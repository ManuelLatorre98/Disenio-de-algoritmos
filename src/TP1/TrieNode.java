package TP1;

import java.util.LinkedList;

public class TrieNode {
  private int alphabet_size = 26;
  private TrieNode [] children = new TrieNode[alphabet_size];//Cada nodo tiene una lista de hijos
  private boolean isEndOfWord;
  private String word;//Los nodos finales conocen a la palabra que conforma el camino hasta ellos
  private LinkedList<String> synonyms;//Los nodos finales conocen la lista de sinónimos de la palabra
  private char letter;
  public TrieNode(){
    isEndOfWord = false;
    for (int i = 0; i < alphabet_size; i++) {
      children[i]=null;
    }
  }

  public TrieNode(char letter){
    this.letter=letter;

  }

  public void setAlphabet_size(int alphabet_size) {
    this.alphabet_size = alphabet_size;
  }

  public void setChildren(int i, TrieNode children) {
    this.children[i] = children;
  }

  public void setEndOfWord(String word, String[]listSynonyms) {
    if(this.synonyms == null) {
      this.synonyms = new LinkedList<String>();
    }
    isEndOfWord = true;
    this.word = word;
    addMultipleSynonyms(listSynonyms);
  }

  public int getAlphabet_size() {
    return alphabet_size;
  }

  public TrieNode getChildren(int i) {
    return children[i];
  }

  public boolean isEndOfWord() {
    return isEndOfWord;
  }
  public String getWord() {
    return this.word;
  }
  public char getLetter(){
    return this.letter;
  }

  public LinkedList<String> getSynonyms(){
    return synonyms;
  }

  public void addSynonym(String synonym){
    this.synonyms.add(synonym);
  }

  public void addMultipleSynonyms(String [] listSynonyms){
    for (int i = 0; i < listSynonyms.length; i++) {
      this.synonyms.add(listSynonyms[i]);
    }
  }

  public boolean containLetter(char letter){
    int indexLetter= letter - 'a';
    return children[indexLetter] != null ;
  }
}