package Tp3;

import java.util.Scanner;

public class Criptografia {
  public static void main(String[] args) {
    long p, q;
    Scanner in = new Scanner(System.in);
    System.out.println("Ingrese primer numero en binario");
    p = in.nextLong();
    System.out.println("Ingrese segundo numero en binario");
    q = in.nextLong();
    in.close();

    Karatsuba obj = new Karatsuba();
    long publicKey= obj.multKaratsuba(p,q);
    System.out.println("publicKey: "+publicKey);


  }
}
