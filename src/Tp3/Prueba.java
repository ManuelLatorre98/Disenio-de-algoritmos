package Tp3;

import java.util.Random;
import java.util.Scanner;

public class Prueba {
  public static long multKaratsuba(long num1, long num2) {
    // Si los números son lo suficientemente chicos los multiplico y retorno
    if (num1 < 10 && num2 < 10) {
      return num1 * num2;
    }

    // Obtiene longitudes de num1 y num2
    int num1Length = numLength(num1);
    int num2Length = numLength(num2);

    // Obtiene la longitud maxima entre ambos
    int maxNumLength = Math.max(num1Length, num2Length);

    // Obtiene la mitad de la longitud del num maxim (redondeando para arriba) n/2
    int halfMaxNumLength = (int) Math.ceil(maxNumLength / 2);

    // 10^(n/2) siendo n la cantidad de dígitos y n/2 = halfMaxNumLength
    long halfMaxNumLengthPowTen = (long) Math.pow(10, halfMaxNumLength);

    // Divide los números en mitades
    long x = num1 / halfMaxNumLengthPowTen;
    long y = num1 % halfMaxNumLengthPowTen;
    long w = num2 / halfMaxNumLengthPowTen;
    long z = num2 % halfMaxNumLengthPowTen;


    // Calcula los factores de la operacion de manera recursiva
    long xw = multKaratsuba(x, w);
    long xz = multKaratsuba(x, z);
    long wy = multKaratsuba(w, y);
    long yz = multKaratsuba(y, z);

    return (xw * (long) Math.pow(10, halfMaxNumLength * 2) +
            ((xz + wy) * (long) Math.pow(10, halfMaxNumLength) + yz));

  }


  // Calcula la cantidad de dígitos
  public static int numLength(long n) {
    return ((int) (Math.log10(n)+1));
  }

  // Method 2
  // Main driver function
  public static void main(String[] args) {
    long firstNumber, secondNumber;
    Scanner in = new Scanner(System.in);
    System.out.println("Ingrese primer numero");
    firstNumber = in.nextLong();
    System.out.println("Ingrese segundo numero");
    secondNumber = in.nextLong();
    in.close();

    long expectedProduct = firstNumber * secondNumber;
    long karatsubaProduct = multKaratsuba(firstNumber, secondNumber);

    System.out.println("Esperado : " + expectedProduct);
    System.out.println("Karatsuba : " + karatsubaProduct);
  }
}
