package T1P2;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class conteo {
  public static void main(String[] args) {
    int [] A = {62,31,84,96,19,47};
    int [] result = ordenarPorConteo(A);
    System.out.print("result= [");
    for (int i = 0; i < result.length; i++) {
      System.out.print(result[i]+",");
    }
    System.out.println("]");


    char [] C = {'a','b','B','A','c','f','F'};
    char [] resultC = ordenarPorConteoChar(C);
    System.out.print("resultC= [");
    for (int i = 0; i < resultC.length; i++) {
      System.out.print(resultC[i]+", ");
    }
    System.out.println("]");


  }
  public static int [] ordenarPorConteo(int [] A){
    int n = A.length;
    int [] count = new int [n];
    int [] sorted = new int [n];
    for (int i = 0; i < n; i++) {
      count[i] = 0;
    }

    for (int i = 0; i < n-1; i++) {
      for (int j = i+1; j < n; j++) {
        if(A[i]<A[j]){
          count[j]++;
        }else{
          count[i]++;
        }
      }
    }
    for (int i = 0; i < n; i++) {
      sorted[count[i]]=A[i];
    }
    return sorted;
  }

  public static char [] ordenarPorConteoChar(char [] C){
    int n = C.length;
    int [] count = new int [n];
    char [] sorted = new char[n];
    for (int i = 0; i < n; i++) {
      count[i] = 0;
    }
    for (int i = 0; i < n-1; i++) {
      for (int j = i+1; j < n; j++) {
        if(Character.compare(Character.toLowerCase(C[i]), Character.toLowerCase(C[j]))<0){
          count[j]++;
        }else{
          count[i]++;
        }
      }
    }
    for (int i = 0; i < n; i++) {
      sorted[count[i]]=C[i];
    }
    return sorted;
  }
}

