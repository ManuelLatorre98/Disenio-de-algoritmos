package Tp3;

import java.util.Random;
import java.util.Scanner;

public class Criptografia {
  static char [] tablaEquiNum = {'&','A','B','C','D','E','F','G','H','I','J','K','L','M','N','a','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
  public static void main(String[] args) {
    long p=17, q=43;
    Karatsuba obj = new Karatsuba();
    long publicKey= obj.mult(p,q);
    System.out.println("DATOS PUBLICOS");
    System.out.println("publicKey: "+publicKey);

    //Calcula thetaN
    long thetaN= (p-1)*(q-1);
    long e=getE(publicKey,thetaN);//e tiene que cumplir 1<e<thetaN ^ coprimo con N (pk) y thetaN
    System.out.println("e: "+e);


    //PARTE DE BERNARDO:

    System.out.println("\nBERNARDO ENCRIPTA");
    String w= encryptMessage("SI",e, publicKey);
    System.out.println("Mensaje encriptado w: "+w);


    //PARTE DE ALICIA:
    System.out.println("\nALICIA DESENCRIPTA");
    int w1= charToNumber(w.charAt(0));
    int w2= charToNumber(w.charAt(1));

    int numericValueW= w1*27+w2;
    System.out.println("Mensaje desencriptado: "+decryptMessage(e,thetaN,numericValueW,publicKey));
  }

  public static String encryptMessage(String message, long e, long pk){ //El mensaje se sabe de 2 caracteres
    ExpoMod expoMod = new ExpoMod();
    //Obtiene número base 27 de los caracteres de la cadena
    int x1 = charToNumber(message.charAt(0)); //Convierte letra a numero segun la tabla
    int x2 = charToNumber(message.charAt(1));

    int textNumber= x1 * 27 + x2;//Convierte el texto a base 27
    long y = expoMod.exponent(textNumber, e, pk); //encripta todo modificar 101 por e
    System.out.println("y: "+y);

    int y2=  (int)y%27;//y = x · 27 + z --> calcula z
    int y1= ((int)y-y2) /27;//x · 27 + z --> calcula x
    return ""+ tablaEquiNum[y1]+tablaEquiNum[y2];//Mensaje encriptado pasado a texto
  }

  public static String decryptMessage(long e, long thetaN, long y, long z){
    long d = getD(e,thetaN);
    System.out.println("d: "+d);
    ExpoMod expoMod = new ExpoMod();
    long decryptNumber = expoMod.exponent(y,d,z);
    int secondCaract=  (int)decryptNumber%27;//y = x · 27 + z --> calcula z
    int firstCaract= ((int)decryptNumber-secondCaract) /27;//x · 27 + z --> calcula x
    return ""+ tablaEquiNum[firstCaract]+tablaEquiNum[secondCaract];//Mensaje encriptado pasado a texto
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
}
