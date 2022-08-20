package TP1;

import java.util.HashMap;
import java.util.Map;

public class DisjointSets {
  private Map<Integer, Integer> parent = new HashMap<>();

  public int Find(int k)
  {
    // si `k` es root
    if (parent.get(k) == k) {
      return k;
    }

    // recurre para el padre hasta que encontramos la raíz
    return Find(parent.get(k));
  }

  public void makeSet(int[] universe)
  {
    // crear `n` conjuntos disjuntos (uno para cada elemento)
    for (int i: universe) {
      parent.put(i, i);
    }
  }


}
