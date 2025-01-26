package com.leet;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
public class CoinChange {

    public static void main(String[] args) {
       Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? 1:-1;
            }
        };
        int[] coins = {2, 1, 5, 10};
        Integer[] coinObjects = Arrays.stream(coins).boxed().toArray(Integer[]::new);
        Arrays.sort(coinObjects, comparator);
        coins = Arrays.stream(coinObjects).mapToInt(i -> i).toArray();
        System.out.println("Sorted Coins: "+ Arrays.toString(coins));
        System.out.println("Result is "+change(25, coins));
    }

    public static int change(int amount, int[] coins) {

      List<Integer> coinList = new ArrayList<> ();
      int total = 0;

      for(int coin:coins) {
        total+=coin;
        coinList.add(coin);
        if (total == amount) {
          System.out.println(coinList);
          break;
        }
     }
     return 1;
   }
}
