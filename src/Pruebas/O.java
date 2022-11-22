package Pruebas;

public class O extends N {

  char a ='o';
  int c = 15;
  public void print() {
    System.out.println("CLASE O: a=" + a + ", aDn=" + super.a+ ", b:"+b+", c=" + c);
    m3();
  }

  public static void m3(){
    System.out.println("m3 de O");
  }
}
