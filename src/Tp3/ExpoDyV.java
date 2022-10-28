package Tp3;

public class ExpoDyV {
  public long expo(long  a, long n){
    if(n == 1) return a;

    long b;
    Karatsuba karatsuba = new Karatsuba();
    if(n%2==0){//Si n par
      b = expo(a,n/2);
      return karatsuba.mult(b, b);
    }else{
      return karatsuba.mult(a, expo(a, n-1));
    }
  }
}
