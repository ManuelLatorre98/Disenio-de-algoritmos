package Pruebas;

public class Pruebas {
    public static void main(String[] args) {
        Object o= 'a';
        Object o2='c';

        System.out.println(o2 instanceof char[]);

        System.out.println(compar(o,o2));
    }

    public static int compar(Object o1, Object o2) {
        // TODO Auto-generated method stub
        int compare=0;
        float count;
        Object i;
        try {
            if(o1 instanceof Number && o2 instanceof Number) {
                count= ((Number)o1).floatValue() - ((Number)o2).floatValue();
                System.out.println(count);
                if(count>0) {
                    compare= 1;
                }else {
                    if(count<0) {
                        compare=-1;
                    }
                }
            }else {
                if(o1 instanceof String && o2 instanceof String) {
                    compare= ((String)o1).compareTo((String)o2);
                }else {
                    throw new RuntimeException("The nodes only can contain Numbers or Strings");
                }
            }

        }catch(Exception e) {
            System.err.println(e.toString());
            System.exit(0);
        }
        return compare;
    }
}
