package TP2P1;

import java.lang.reflect.Array;
import java.util.Arrays;

public class AsignacionesEstables {
  public static void main(String[] args) {
    int[][] menPref = { //Indice fila es el hombre, indice col es la preferencia, valor es el indice mujer
            {1, 2, 0},
            {1, 2, 0},
            {1, 2, 0},
    };
    int[][] womenPref = { //Indice fila es el hombre, indice col es la preferencia, valor es el indice mujer
            {0, 2, 1},
            {2, 1, 0},
            {0, 1, 2},
    };
    int[][] bannedCouples = { //Indice fila es el hombre, indice col mujeres, 1 = pueden ser pareja, 0 = no pueden ser pareja
            {1, 1, 0},//Hombre 0 con mujer 2 no
            {1, 1, 1},
            {0, 1, 1}, //Hombre 2 con mujer 0 no
    };

    int[] womanCouples = new int[womenPref.length]; //indice hombres, -1 = sin pareja, otro valor indice de su pareja
    int[] manCouples = new int[menPref.length];
    assignCouples(menPref,womenPref,bannedCouples,manCouples,womanCouples);

    for (int i = 0; i < menPref.length; i++) {
      System.out.println("Hombre "+i+" --> "+" Mujer "+manCouples[i]);
    }
    for (int i = 0; i < womenPref.length; i++) {
      System.out.println("Mujer "+i+" --> "+" Hombre "+womanCouples[i]);
    }
  }

  public static int[] assignCouples(int[][] menPref, int[][] womenPref, int[][] bannedPartners, int[]manCouples,int[]womanCouples) {
    int amountMen = menPref.length;
    int freeMen = amountMen; //Cantidad de hombres restantes sin pareja

    Arrays.fill(manCouples, -1);//Inicialmente no tienen pareja
    Arrays.fill(womanCouples, -1);//Inicialmente no tienen pareja
    while (freeMen > 0) {//Mientras queden hombres sin pareja
      int indexMan = 0;
      while (indexMan < amountMen && manCouples[indexMan] > -1) {//Busca al siguiente hombre sin pareja
        indexMan++;
      }
      int indexPref = 0;
      while (indexMan < amountMen && manCouples[indexMan] == -1 && indexPref<womenPref.length) {
        int selectedWoman = menPref[indexMan][indexPref];
        if (bannedPartners[indexMan][selectedWoman] == 1) { //si pueden ser pareja
          if (womanCouples[selectedWoman] == -1) {//si mujer NO tiene pareja, los empajera
            womanCouples[selectedWoman] = indexMan;
            manCouples[indexMan] = selectedWoman; //Los empareja
            freeMen--; //Hay un hombre menos sin pareja
          } else {//Si la mujer tiene pareja
            int currentWomanCouple = womanCouples[selectedWoman];//Busca la pareja de la mujer
            if (betterCouple(womenPref, currentWomanCouple, indexMan)) {//Si el hombre actual es mejor pareja lo asigna
              womanCouples[selectedWoman] = indexMan;
              manCouples[currentWomanCouple] = -1; //La pareja actual deja de serlo
              manCouples[indexMan] = selectedWoman;//Se le asigna su nueva pareja
            }
          }
        }
        indexPref++;//Busca al siguiente hombre
      }
      //Si no encontro pareja es porque no se lo permitio la restriccion, por lo tanto quedara sin pareja
      //Para evitar bucle se decrementa en 1 la cantidad de hombres libres
      if(indexMan<amountMen && manCouples[indexMan]==-1){
        freeMen--;
      }

    }
    return womanCouples;
  }


  public static boolean betterCouple(int[][] womenPref,int currentWomanCouple, int newCouple){
    int indexCurrent = Arrays.asList(womenPref).indexOf(currentWomanCouple);
    int indexNew = Arrays.asList(womenPref).indexOf(currentWomanCouple);
    return indexNew < indexCurrent; //A menor indice mayor preferencia
  }
}
