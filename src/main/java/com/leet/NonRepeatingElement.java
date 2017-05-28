package com.code.leet;
public class NonRepeatingElement {

  public static void main(String[] args) {

    int result =0;
    int []arr={3,4,5,3,4,5,6};
    for(int el:arr) {
      result ^=el; // Bitwise XOR
    }
    System.out.println("Result is "+result);

  }

}
