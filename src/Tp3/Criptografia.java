package Tp3;

import java.util.Random;
import java.util.Scanner;

public class Criptografia {
  static char [] tablaEquiNum = {'.','A','B','C','D','E','F','G','H','I','J','K','L','M','N','a','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
  public static void main(String[] args) {
    long p=17, q=43;
    Karatsuba obj = new Karatsuba();
    ExpoMod expo= new ExpoMod();
    long publicKey= obj.mult(p,q);
    //Calcula thetaN
    long thetaN= (p-1)*(q-1);
    long e=getE(publicKey,thetaN);//e tiene que cumplir 1<e<thetaN ^ coprimo con N (pk) y thetaN
    System.out.println("e: "+e);
    System.out.println("publicKey: "+publicKey);

    //PARTE DE BERNARDO:
    String w= alterEncryptMessage("SI",e, publicKey);
    System.out.println("Mensaje encriptado w: "+w);
    //PARTE DE ALICIA:
    //decryptMessageBueno(e,thetaN,w,publicKey);

  }

  public static long encryptMessage(String message, long e, long pk){ //El mensaje se sabe de 2 caracteres
    ExpoMod expoMod = new ExpoMod();
    //Obtiene número base 27 de los caracteres de la cadena
    long x1 = charToNumber(message.charAt(0));
    long x2 = charToNumber(message.charAt(1));
    String xStr= ""+x1+"0"+x2; //Concatena los códigos de los caracteres separándolos con 0
    long x = Long.parseLong(xStr); //Transforma la concatenación en long para operar
    //Cifra los números que representan las letras
    System.out.println("X original: "+x);
    long y = expoMod.exponent(x, e, pk);
    System.out.println("Y original: "+y);

    return y;//todo modificar por encryptY(y) y el tipo de la clase a string
  }

  public static String alterEncryptMessage(String message, long e, long pk){ //El mensaje se sabe de 2 caracteres
    ExpoMod expoMod = new ExpoMod();
    //Obtiene número base 27 de los caracteres de la cadena
    int x1 = charToNumber(message.charAt(0)); //Convierte letra a numero segun la tabla
    int x2 = charToNumber(message.charAt(1));

    int textNumber= x1 * 27 + x2;//Convierte el texto a base 27
    long y = expoMod.exponent(textNumber, 101, pk); //encripta todo modificar 101 por e
    System.out.println("y: "+y);

    int y2=  (int)y%27;//y = x · 27 + z --> calcula z
    int y1= ((int)y-y2) /27;//x · 27 + z --> calcula x
    return ""+ tablaEquiNum[y1]+tablaEquiNum[y2];//Mensaje encriptado pasado a texto
  }


  //Convierte y (números) en w(letras)
  public static String encryptY(long y){//Va a recibir y que puede ser cualquier numero
    Random random = new Random();
    int takeDigits ;
    long yAux=y;
    String result="";
    int opDigits=10;
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

  public static String decryptMessage(String w, long e, long pk, long p){
    ExpoMod expoMod = new ExpoMod();
    ExpoDyV expoDyv= new ExpoDyV();
    long y=stringToNumber(w);
    long d= expoMod.exponent(3, -1, 672);
    System.out.println("pum");
    System.out.println(d);

    //long decrypt= expoMod.exponent(y, d, pk);
    System.out.println(d);
    return "";
  }

  public static void decryptMessageBueno(long e, long thetaN, long y, long z){
    long d = getD(e,thetaN);
    System.out.println("d: "+d);

    ExpoMod expoMod = new ExpoMod();
    long decrypt = expoMod.exponent(y,d,z);
    System.out.println("y: "+y+", d: "+d+", z: "+z);
    System.out.println(decrypt);

  }
  public static long getE(long N, long thetaN){
    boolean finded=false;
    int e=2;
    while(!finded && e < thetaN){ //Primer condicion
      if(checkCoprimes(e,N) && checkCoprimes(e,thetaN)){
        finded=true;
      }else{
        e++;
      }
    }
    if(!finded){//Si no encuentra devuelve -1
      e=-1;
    }
    return e;
  }

  public static long getD(long e, long thetaN){ //Se podria mas eficiente?
    long d= 1;
    while(((e * d)%thetaN)!=1 ){
      d++;
    }
    return d;
  }
  public static boolean checkCoprimes(long a,long b){
    boolean coprimes=false;
    if(checkCoprimesAux(a,b)==1){
      return true;
    }
    return coprimes;
  }
  public static long checkCoprimesAux(long a, long b){
    if (a == 0 || b == 0)
      return 0;

    // base case
    if (a == b)
      return a;

    // a is greater
    if (a > b)
      return checkCoprimesAux(a-b, b);

    return checkCoprimesAux(a, b-a);
  }





  public static int charToNumber(char character){//Base 26 (sin ñ) arrancando en 1

    return new String(tablaEquiNum).indexOf(character);
  }

  public static long stringToNumber(String str){
    String resultStr="";
    for (int i = 0; i < str.length(); i++) {
      resultStr+= charToNumber(str.charAt(i));
    }
    return Long.parseLong(resultStr);
  }
}
