package Pruebas;

public class M {
    int a = 0, b = 0;
    static int c=9;
    int m1() {
      int x = 11;
      b = b - x;
      c = c * 2;
      System.out.println("En M a = " + a);
      System.out.println(" b = " + b);
      System.out.println(" c = " + c);
      return 1;
    }

    int m2() {
      c = c * 3;
      System.out.println(++N.d);
      return 4;
    }

    static void m3() {
      System.out.println("En M c = " + c);
    }


  /*public void print() {

    System.out.println("CLASE M: " + a);
    m3();
  }

  public static void m3(){
    System.out.println("m3 de M");
  }*/
}
