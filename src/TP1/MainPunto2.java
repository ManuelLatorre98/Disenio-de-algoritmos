package TP1;

public class MainPunto2 {

  public static void main(String[] args) {
    int [] universe = {1,2,3,4,5,6,7,8,9,15}; //SUPONEMOS ORDENADO MENOR A MAYOR
    int maxElem= universe[(universe.length-1)];
    int [] set = new int [maxElem+1];
    configTag(set, universe);

    int elem= 11;
    //System.out.println("setTag of elem "+elem+": "+search(elem, set));

    int elemA=1;
    int elemB=3;
    int tagA= search(elemA, set);
    int tagB= search(elemB, set);
    join(tagA, tagB,set);
    join(tagA, 7,set);
    for (int i = 0; i < universe.length; i++) {
      System.out.println("setTag of elem "+universe[i]+": "+search(universe[i], set));
    }

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
      }else{

      }

      sets[resultSearch]+=","+universe[i];
    }

    for (int i = 0; i < sets.length; i++) {
      if(sets[i]!=""){
        sets[i]= "{"+sets[i]+"},";
      }
      System.out.println(sets[i]);
    }
  }
}
