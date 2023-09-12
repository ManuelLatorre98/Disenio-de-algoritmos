package TP1;

public class ContadorBinario {
  public static void main(String[] args) {
    int [] a={0, 0, 0, 0, 0, 0, 0, 0};
    for (int i = 0; i < 16; i++) {
      incrementar(a);
      for (int j = a.length-1; j >= 0 ; j--) {
        System.out.print(a[j]+",");
      }
      System.out.println();
    }
    System.out.println("ACHICA");
    for (int i = 0; i < 16; i++) {
      decrementar(a);
      for (int j = a.length-1; j >= 0 ; j--) {
        System.out.print(a[j]+",");
      }
      System.out.println();
    }
  }

  public static void incrementar(int []a){
    int i=0;
    while(i < a.length && a[i] == 1) {
      a[i] = 0;
      i++;
    }
    if(i < a.length){
      a[i] = 1;
    }
  }

  public static void decrementar(int []a){
    int i=0;
    while(i < a.length && a[i] == 0) {
      a[i] = 1;
      i++;
    }
    if(i < a.length){
      a[i] = 0;
    }
  }
}
