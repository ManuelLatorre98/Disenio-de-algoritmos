package Tp3;

import java.util.Random;
import java.util.Scanner;

public class Criptografia {
  public static void main(String[] args) {
    long p=11, q=3;
    /*Scanner in = new Scanner(System.in);
    System.out.println("Ingrese primer numero en binario");
    p = in.nextLong();
    System.out.println("Ingrese segundo numero en binario");
    q = in.nextLong();
    in.close();*/

    Karatsuba obj = new Karatsuba();
    ExpoMod expo= new ExpoMod();
    System.out.println(expo.exponent(2,5,13));
    long e=3;//a (p-1)*(q-1)
    long publicKey= obj.mult(p,q);
    System.out.println("publicKey: "+publicKey);

    //PARTE DE BERNARDO:
    //String w= encryptMessage("SI",e, publicKey);
    String w= alterEncryptMessage("SI",e, publicKey);

    //PARTE DE ALICIA:
    decryptMessage(w,e,publicKey);

  }

  public static String encryptMessage(String message, long e, long pk){ //El mensaje se sabe de 2 caracteres
    ExpoMod expoMod = new ExpoMod();
    //Obtiene número base 27 de los caracteres de la cadena
    long x1 = charToNumber(message.charAt(0));
    long x2 = charToNumber(message.charAt(1));
    String xStr= ""+x1+"0"+x2; //Concatena los códigos de los caracteres separándolos con 0
    long x = Long.parseLong(xStr); //Transforma la concatenación en long para operar
    //Cifra los números que representan las letras
    System.out.println("X original: "+x);
    long y = expoMod.exponent(x, e, pk);

    return encryptY(y);
  }

  public static String alterEncryptMessage(String message, long e, long pk){ //El mensaje se sabe de 2 caracteres
    ExpoMod expoMod = new ExpoMod();
    //Obtiene número base 27 de los caracteres de la cadena
    long x1 = charToNumber(message.charAt(0));
    long x2 = charToNumber(message.charAt(1));
    long y1 = expoMod.exponent(x1, e, pk);
    long y2 = expoMod.exponent(x2, e, pk);
    String yStr= ""+y1+"0"+y2;
    long y = Long.parseLong(yStr);
    return encryptY(y);
  }
  //Convierte y (números) en w(letras)
  public static String encryptY(long y){//Va a recibir y que puede ser cualquier numero
    Random random = new Random();
    int takeDigits ;
    long yAux=y;
    String result="";
    int opDigits=10;
    System.out.println("y:"+ y);
    while(yAux!=0){
      takeDigits= random.nextInt(2)+1;//Toma 1 o 2 números para convertirlo en letra
      if(takeDigits==2 && yAux/10!=0 && yAux%100<=26 && yAux%100>9){//Si toma 2 dígitos, quedan 2 o más dígitos, estos pueden ser convertidos en letra y dan mayor a 9
        opDigits=100;//Va a trabajar con 2 caracteres
      }else{//Si toma un digito o no se pudieron tomar 2 dígitos
        opDigits=10;//Va a trabajar con 1 caracter
      }
      int letterNum = (int) yAux % opDigits;//Obtiene uno o dos números
      char letter= (char) (letterNum+'A'-1);
      result= letter + result;
      yAux/= opDigits;
    }

    return result;
  }

  public static String decryptMessage(String w, long e, long pk){
    ExpoMod expoMod = new ExpoMod();
    long y=stringToNumber(w);
    System.out.println("pum");
    long d= expoMod.exponent(e, -1, pk);
    //long decrypt= expoMod.exponent(y, d, pk);
    System.out.println(d);
    return "";
  }
  public static int charToNumber(char character){//Base 26 (sin ñ) arrancando en 1
    return (Character.toUpperCase(character) - 'A')+1;
  }

  public static long stringToNumber(String str){
    String resultStr="";
    for (int i = 0; i < str.length(); i++) {
      resultStr+= charToNumber(str.charAt(i));
    }
    return Long.parseLong(resultStr);
  }
}
