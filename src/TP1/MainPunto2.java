package TP1;

public class MainPunto2 {

  public static void main(String[] args) {
    int [] universe = {1,2,3,4,5,6,7,8,9,10}; //SUPONEMOS ORDENADO MENOR A MAYOR
    int maxElem= universe[(universe.length-1)];
    int [] set = new int [maxElem+1];
    configTag(set, universe);
    int [] set1= {1,3,7};
    int [] set3= {2,5,9,10};
    int [] set4= {6,8};
    insertSet(set1, set);
    insertSet(set3, set);
    insertSet(set4, set);
    printSets(universe,set);

  }

  public static void configTag(int [] set, int [] universe){
    for (int i = 0; i < universe.length; i++) {
      set[universe[i]] = universe[i];
    }
  }

  public static int search(int elem, int [] set){
    int tag = -1;
    if(elem < set.length){
      tag = set[elem];
    }
    if(tag==0 && elem!=0){ //De esta manera representamos simbolicamente que los elem que no existen no tienen setTag
      tag=-1;
    }
    return tag;
  }

  public static void join(int tagA, int tagB, int[]set){
    int min = Math.min(tagA, tagB);
    int max= Math.max(tagA, tagB);

    for (int i = 0; i < set.length; i++) {
      if(set[i] == max){
        set[i] = min;
      }
    }
  }

  public static void printSets(int[]universe, int[]set){
    String [] sets = new String[set.length];
    int resultSearch;
    for (int i = 0; i < universe.length; i++) {
      resultSearch= search(universe[i], set);

      if(sets[resultSearch]==null){
        sets[resultSearch]="";
      }
      if(sets[resultSearch].equals("")){
        sets[resultSearch]+=universe[i];
      }else{
        sets[resultSearch]+=","+universe[i];
      }
    }

    for (int i = 0; i < sets.length; i++) {
      if(sets[i]!=""){
        sets[i]= "Set"+i+": {"+sets[i]+"}";
      }
      System.out.println(sets[i]);
    }
  }
  /*receive an ordered set of elements to be created*/
  public static void insertSet(int [] elems, int[] set){
    int elemA=elems[0]; //The min element is always the first
    int tagA = search(elemA, set);
    int tagB;
    for (int i = 1; i < elems.length; i++) {
      tagB=  search(elems[i], set);
      join(tagA, tagB,set);
    }
  }

}
