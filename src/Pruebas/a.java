<<<<<<< HEAD
package Pruebas;

public class a {
  public static void main(String[] args) {
    int cuenta= 1;
    while(((101 * cuenta)%672)!=1 ){
      cuenta++;
    }
    System.out.println(cuenta);
  }
}
=======
package Pruebas;

public class a {
  public static int cambio(int total,int[] monedasDisponibles){
    int n=monedasDisponibles.length;
    int m=total+1;
    int[][] C=new int[n][m];

    for(int i=0;i<n;i++){
      C[i][0]=0;
    }
    //recorre las monedasDisponibles
    for(int i=0;i<n;i++){
      //recorre desde 1 hasta total
      for(int j=1;j<m;j++){
        //si estamos en la moneda de menor valor para dar cambio
        if(i==0){
          //si la moneda i para dar cambio es mayor al dinero que tenemos que devolver
          if(j<monedasDisponibles[i]){
            //no se puede dar cambio con la moneda disponible
            C[i][j]=Integer.MAX_VALUE;
            //si es posible devolver el cambio con la moneda de menor valor
          }else{
            C[i][j]= 1+C[0][j-monedasDisponibles[i]];
          }
          //caso comun
        }else
          //si la moneda i para dar cambio es mayor al dinero que tenemos que devolver
          if(j<monedasDisponibles[i]){
            //devolvemos teniendo en cuenta la moneda de valor anterior (i-1)
            C[i][j]=C[i-1][j];
            //si es posible utilizar la moneda i para devolver el cambio
          }else{
            //se devulve el minimo entre no usar la moneda i en el cambio o usarla
            C[i][j]=Integer.min(C[i-1][j],1+C[i][j-monedasDisponibles[i]]);
          }
      }
    }
    print(C);
    return C[n-1][m-1];
  }

  public static void print(int[][] matriz){
    for(int i=0; i<matriz.length;i++){
      for(int j=0;j<matriz[i].length;j++){
        System.out.print(matriz[i][j]+" ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[] monedasDisponibles={1,4,6};

    System.out.println(cambio(8, monedasDisponibles));
  }
}
>>>>>>> e8bd3ba9592ba2545b247237c321fcc69e11001a
