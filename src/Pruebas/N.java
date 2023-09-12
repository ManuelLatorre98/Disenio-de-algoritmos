package Pruebas;

public class N extends M {
  char a = 'n';
  public void print() {
    System.out.println("CLASE N: a=" + a + ", bDm=" + b+", aDm= "+super.a);
    m3();

  }

  public int getAdeM(){
    return super.a;
  }

  public static void m3(){
    System.out.println("m3 de N");
  }
}
