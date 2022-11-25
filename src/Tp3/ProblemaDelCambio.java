package Tp3;

public class ProblemaDelCambio {
  public static void main(String[] args) {
    int [] coinValues = {1,4,6};//lo supongo ordenado
    int n= coinValues.length;
    int m= 8;
    m++;
    int [][] changeTable = new int[n][m];
    change(changeTable,coinValues,n,m);
  }

  public static void change(int [][] changeTable, int [] coinValues,int n, int m){ //m representa m unidades de cambio

    for (int i = 0; i < n; i++) {
      changeTable[i][0] = 0;
    }

    for (int i = 0; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if(i==0){
          if(j<coinValues[i]){//Caigo fuera de la tabla asi que completa con -1 para indicar que no se puede pagar con las monedas que hay;
            changeTable[i][j] = -1;
          }else{//Asigna j-coinValues[i] osea mueve a la izquierda y a su valor le sumo 1
            changeTable[i][j] = 1 + changeTable[0][(j-coinValues[i])];
          }
        }else if(j<coinValues[i]){//Asigna el valor inmediatamente "arriba" de la tabla
          changeTable[i][j] = changeTable[i-1][j];
        }else if(j>=coinValues[i]){//Asigna el minimo entre ambos
          changeTable[i][j]= Math.min(changeTable[i-1][j], (1 + changeTable[i][(j - coinValues[i])]));
        }
      }
    }
    printChangeTable(changeTable);
    resolve(changeTable, coinValues, n-1, m-1);
  }

  public static void printChangeTable(int[][]changeTable){
    for (int i = 0; i < changeTable.length; i++) {
      for (int j = 0; j < changeTable[i].length; j++) {
        System.out.print(changeTable[i][j]+" ");
      }
      System.out.println();
    }
  }
  public static void resolve(int [][]changeTable, int [] coinValues,int i,int j){
    int []solution= new int [coinValues.length];
    while(j>0){//Si no llegamos a la primer columna
      if(changeTable[i][j] == changeTable[i-1][j]){
        i= i-1;//nos desplazamos a la fila de arriba
      }else{
        j = j - coinValues[i];//Nos movemos a la izq
        solution[i]++;//Incrementamos en 1 el indice
      }
    }

    System.out.println();
    System.out.println("SOLUCION: ");
    for (int k = 0; k < solution.length; k++) {
      System.out.println(coinValues[k]+"$:"+solution[k]);
    }
  }
}
