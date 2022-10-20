package Tp3;

public class Karatsuba {
  private String addStrings(String first, String second){
    String result="";
    
    int length = lengthController(first, second);
    int carry = 0;
    for (int i = length-1; i >= 0 ; i--) {
      int firstBit = first.charAt(i) - '0'; //todo averiguar que carajo hace esto en c++
      int secondBit = second.charAt(i) - '0'; //todo averiguar que carajo hace esto en c++

      int sum = (firstBit ^ secondBit ^ carry)+'0';

      result = (char)sum + result;

      carry = (firstBit&secondBit) | (secondBit&carry) | (firstBit&carry);
    }
    if(carry==1){
      result = '1' + result;
    }
    return result;
  }
  private int lengthController(String str1, String str2){
    int len1 = str1.length();
    int len2 = str2.length();
    if(len1 < len2){
      for (int i = 0; i < len2 - len1; i++) {
        str1 = '0' + str1;
        return len2;
      }
    }else if(len1 > len2){
      for (int i = 0; i < len1 - len2; i++) {
        str2 = '0' + str2;
      }
    }
    return len1;
  }

  private String MakeShifting(String str, int stepnum){
    String shifted = str;
    for (int i = 0; i < stepnum; i++) {
      shifted = shifted + '0';
    }
    return shifted;
  }

  private String Subtraction(String lhs, String rhs){
    int length = lengthController(lhs, rhs);
    int diff;
    String result="";

    for (int i = length-1; i >=0 ; i++) {
      diff = (lhs.charAt(i)-'0') - (rhs.charAt(i)-'0');
      if(diff >= 0){
        result = DecimalToBinary(diff) + result;
      }else {
        for (int j = i - 1; j >= 0; j--) {
          lhs[j] = ((lhs[j] - '0') - 1) % 10 + '0'; //todo arreglarlo porque java es una mierda
          if (lhs[j] != '1') {
            break;//todo esto ta feo
          }
        }
        result = DecimalToBinary(diff + 2) + result;
      }
    }
    return result;
  }

  private String DecimalToBinary(long number){//todo aca usaba long long int????
    String result = "";
    if(number <= 0){
      return "0";
    }else{
      int i = 0;
      while(number > 0){
        long num = number%2;
        stringstream ss; //todo ???
        ss<<num;
        result = ss.str() + result;
        number = number / 2;
        i++;
      }
      return result;
    }
  }
  public String multiply(String X, String Y){
    int n= lengthController(X,Y);

    if(n == 1){
      return ((Y.charAt(0)-'0' == 1) && (X.charAt(0)-'0' == 1)) ? "1" : "0";//todo no se que hace el -'0'
    }
    int fh = n/2;
    int sh = (n-fh);

    String X1 = X.substring(0,fh);
    String Xr = X.substring(fh,sh);

    String Y1 = Y.substring(0,fh);
    String Yr = Y.substring(fh,sh);

    String P1 = multiply(X1,Y1);
    String P2 = multiply(Xr, Yr);
    String P3 = multiply(addStrings(X1, Xr),addStrings(Y1, Yr));

    return addStrings(addStrings(MakeShifting(P1, 2*(n-n/2)),P2),MakeShifting(Subtraction(P3,addStrings(P1,P2)),n-(n/2)));

  }
}
