package Pruebas;

public class Pruebas {
    public static void main(String[] args) {
        String cadena="0001";
        int carry=0;
        int firstBit = cadena.charAt(3) - '0';
        int secondBit = cadena.charAt(2) - '0';
        System.out.println(((firstBit ^ secondBit ^ carry)+'0'));
        System.out.println((int) (Math.log10(123456789)+1));
    }
}
