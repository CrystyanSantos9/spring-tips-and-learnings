package com.cryss.tipsandlearnings.programacaodinamica;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Mochila2 {

    static int[] p = {4,2,1,3};
    static int[] v = {500, 400, 300, 450};
    static int[][] t = new int[4][4];


    public static void main(String[] args) {

        for(int i = 1; i < t.length; i++){

            for(int j = 1; j < t.length; j++){


                if(p[i] > j) {
                    t[i][j] = t[i - 1][j];
                }
                else if (p[i] <= j) {
                    t[i][j] = Math.max (t[i-1][j], v[i]+t[i-1][j-p[i]]);
                }



                System.out.println ("i: "+ (i) + " | " + "j " + (j) +" = [ "+t[i][j]+" ]");
            }
        }


//      List<Integer> r =
//              Arrays.stream (t)
//                      .peek (System.out::println)
//                      .flatMap (e -> Arrays.stream (e).boxed ())
//                      .toList ();
//
//        System.out.println (r);

    }
}
