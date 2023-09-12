package Pruebas;

import java.util.Random;

public class Pruebas {
    public static void main(String[] args) {
        M m = new M();
        N n = new N();

        M.c ++ ;
        m.a = 3;
        m.b = 9;

        M.m3();
        System.out.println( m.m1() );
        System.out.println( m.m2() );
        m.m3();
        System.out.println( n.m2() );
        N.m3();
        System.out.println(n.m4(M.c));
        return;
    }
}
