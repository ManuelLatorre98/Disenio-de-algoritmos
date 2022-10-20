package Tp3;

import java.util.Scanner;

public class Criptografia {
  public static void main(String[] args) {
    String firstNumber, secondNumber;
    Scanner in = new Scanner(System.in);
    System.out.println("Ingrese primer numero en binario");
    firstNumber = in.nextLine();
    System.out.println("Ingrese segundo numero en binario");
    secondNumber = in.nextLine();
    in.close();

    int len1= firstNumber.length();
    int len2 = secondNumber.length();
    int general_len = firstNumber.length();

    if(len1 < len2){
      for (int i = 0; i < len2 - len1; i++) {
        firstNumber = '0' + firstNumber;
      }
      general_len = firstNumber.length();
    } else if (len1 > len2) {
      for (int i = 0; i < len1 - len2; i++) {
        secondNumber = '0' + secondNumber;
      }
      general_len = secondNumber.length();
    }

    System.out.println("Karatsuba: ");
    Karatsuba obj;
    String karatsuba = obj.multiply(firstNumber, secondNumber);
  }
}
