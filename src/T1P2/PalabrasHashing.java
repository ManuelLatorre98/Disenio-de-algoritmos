package T1P2;

import java.io.*;
import java.util.Arrays;
import java.util.Hashtable;

public class PalabrasHashing {
  public static void main(String[] args) throws IOException {
    Hashtable<String, Integer> hashWords= new Hashtable<>();
    readAndHash(hashWords);
    System.out.println("Mappings of hashWords: "+hashWords);
  }

  public static void readAndHash(Hashtable<String,Integer> hashWords) throws IOException {
    String filePath = "src\\T1P2\\texto.txt";
    File file = new File(filePath);
    BufferedReader br = new BufferedReader(new FileReader(file));
    String st;
    String [] words =null;
    int lenghtArrayOfWords;

    while((st = br.readLine())!=null){//Continúa mientras tenga líneas por leer, voy línea por línea
      words = st.split(" "); //Genera array de palabras separadas por espacios
      Arrays.stream(words).map(word -> word.toLowerCase()); //Convierte a minúsculas las mayúsculas
      lenghtArrayOfWords = words.length;
      for (int i = 0; i < lenghtArrayOfWords; i++) {
        if(hashWords.containsKey(words[i])){
          hashWords.put(words[i], hashWords.get(words[i])+1);
        }else{
          hashWords.put(words[i],1);
        }
      }
    }
  }
}
