package Tp3;

public class ProblemaDelCambio {
  public static void main(String[] args) {
    int [] coinValues = {1,4,6};//lo supongo ordenado
    int n= coinValues.length+1; //La primer fila no se usa (a causa del índice de las denoms) asi que necesito una extra
    int [] coinDenom = new int[n];
    int m= 8;
    m++;//Indice arranca en 1

    //Las denominaciones arrancan un índice a la derecha
    coinDenom[0]= -1;
    for (int i = 0; i < coinValues.length; i++) {
      coinDenom[i+1] = coinValues[i];
    }

    int [] [] changeTable = new int[n][m];
    change(changeTable,coinDenom,n,m);

    for (int i = 0; i < changeTable.length; i++) {
      for (int j = 0; j < changeTable[i].length; j++) {
        System.out.print(changeTable[i][j]+",");
      }
      System.out.println();
    }

    int []solution= resolve(changeTable, coinDenom, n-1, m-1);
    System.out.println();
    System.out.println("SOLUCION: ");
    for (int i = 1; i < solution.length; i++) {
      System.out.println(coinValues[i-1]+"$:"+solution[i]);
    }
  }

  public static void change(int [][] changeTable, int [] coinDenom,int n, int m){ //m representa m unidades de cambio
    //Inicialmente C[i][0]=0
    for (int i = 1; i < n; i++) {
      changeTable[i][0] = 0;
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if(i==1){
          if(j<coinDenom[i]){//Caigo fuera de la tabla asi que completa con -1 para indicar que no se puede pagar con las monedas que hay;
            changeTable[i][j] = -1;
          }else{//Asigna j-coinDenom[i] osea mueve a la izquierda y a su valor le sumo 1
            changeTable[i][j] = 1 + changeTable[1][(j-coinDenom[i])];
          }
        }else if(j<coinDenom[i]){//Asigna el valor inmediatamente "arriba" de la tabla
          changeTable[i][j] = changeTable[i-1][j];
        }else if(j>=coinDenom[i]){//Asigna el minimo entre ambos
          changeTable[i][j]= Math.min(changeTable[i-1][j], (1 + changeTable[i][(j - coinDenom[i])]));
        }
      }
    }
  }

  public static int [] resolve(int [][]changeTable, int [] coinDenom,int i,int j){
    int []solution= new int [coinDenom.length];
    while(j>0){//Si no llegamos a la primer columna
      if(changeTable[i][j] == changeTable[i-1][j]){
        i= i-1;//nos desplazamos a la fila de arriba
      }else{
        j = j - coinDenom[i];//Nos movemos a la izq
        solution[i]++;//Incrementamos en 1 el indice
      }
    }
    return solution;
  }
}
