package Pruebas;

import java.util.Random;

public class Pruebas {
    static int a=11;
    public static void main(String[] args) {
        int b= 15+a * modificar(); //5+1*1 = 6 o si modifica y despues agarra a 5+2*1=7

        System.out.println("b:"+ b);
        System.out.println("a: "+ a);

    }
    public static int modificar(){
        a--;
        return 12;
    }
}
