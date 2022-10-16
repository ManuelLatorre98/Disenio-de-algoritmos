package TP1;

public class ConjuntosDisjuntos {

  public static void main(String[] args) {
    int[] universe = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //SUPONEMOS ORDENADO MENOR A MAYOR
    int maxElem = universe[(universe.length - 1)];
    int[] table = new int[maxElem + 1];
    configTag(table, universe); //Configura la tabla inicial

    //Crea los conjuntos del ejemplo
    int[] set1 = {1, 3, 7};
    int[] set2 = {2, 5, 9, 10};
    int[] set6 = {6, 8};
    insertSet(set1, table);
    insertSet(set2, table);
    insertSet(set6, table);
    System.out.println("Conjuntos iniciales:");
    printSets(universe, table);

    //Fusiona set6 con set2
    fusion(2, 6, table);
    System.out.println("Conjuntos resultantes:");
    printSets(universe, table);
  }

  public static void configTag(int[] table, int[] universe) {
    for (int i = 0; i < universe.length; i++) {
      table[universe[i]] = universe[i];
    }
  }

  public static int search(int elem, int[] table) { //Theta(1)
    int tag = -1;
    if (elem < table.length) {
      tag = table[elem];
    }
    if (tag == 0 && elem != 0) { //De esta manera representamos simbólicamente que los elem que no existen no tienen setTag
      tag = -1;
    }
    return tag;
  }

  public static void fusion(int tagA, int tagB, int[] table) {//Theta(n)
    int min = Math.min(tagA, tagB);
    int max = Math.max(tagA, tagB);

    for (int i = 0; i < table.length; i++) {
      if (table[i] == max) {
        table[i] = min;
      }
    }
  }

  public static void printSets(int[] universe, int[] table) {
    String[] sets = new String[table.length];
    int resultSearch;
    for (int i = 0; i < universe.length; i++) {
      resultSearch = search(universe[i], table);

      if (sets[resultSearch] == null) {
        sets[resultSearch] = "";
      }
      if (sets[resultSearch].equals("")) {
        sets[resultSearch] += universe[i];
      } else {
        sets[resultSearch] += "," + universe[i];
      }
    }

    for (int i = 0; i < sets.length; i++) {
      if (sets[i] != "") {
        sets[i] = "Set" + i + ": {" + sets[i] + "}";
      }
      System.out.println(sets[i]);
    }
  }

  /*Recibe un conjunto de elementos ordenados para crear un conjunto a partir de ellos*/
  public static void insertSet(int[] elems, int[] table) {
    int elemA = elems[0]; //El elemento minimo siempre es el primero, ya que está ordenado
    int tagA = search(elemA, table);
    int tagB;
    for (int i = 1; i < elems.length; i++) {
      tagB = search(elems[i], table);//Verificamos las etiquetas de los valores
      fusion(tagA, tagB, table);
    }
  }

}
