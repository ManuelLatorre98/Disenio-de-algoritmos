package Tp3;

public class ExpoMod {
  public long exponent(long a, long n, long z) {
    Karatsuba karatsuba = new Karatsuba();
    // Casos base
    if (a == 0) return 0;
    if (n == 0) return 1;

    // Si n par
    long y;
    if (n % 2 == 0)
    {
      y = exponent(a, n / 2, z); //Reduce el n a la mitad y hace llamado recursivo
      y = (karatsuba.mult(y, y)) % z;
    } else {//Si n impar
      y = a % z;
      y = (karatsuba.mult(y, exponent(a, n - 1, z) % z) % z);
    }
    return (long)((y + z) % z);
  }
}
