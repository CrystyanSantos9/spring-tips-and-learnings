package com.cryss.tipsandlearnings.programacaodinamica;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class MinimumCoinsDriver {

    static Integer answer;
    static Integer subproblem;


//    static List<Integer> memo = new ArrayList<> ();

    static Integer[] memo = new Integer[1];

    static Integer min_ignore_none(Integer a, Integer b) {
        if(a == null) return b;
        if(b == null) return a;
        return Math.min (a, b);
    }

    static Integer minimum_coins(Integer m, ArrayList<Integer> coins) {
        //valor que quero alcançar
        if(m==0){
            answer = 0;
        }else{
            answer = null;
        }

        coins.forEach (coin->{
            //armazena os resultados anteriores
            subproblem = m - coin; //13-1 = 12

            //pular as soluções que alcaçam m de subproblemas negativos
            if(subproblem<0) return;

            //MIN(NULL, MC(12, [3])--> 11..0 + 1
            answer = min_ignore_none(answer,
                    minimum_coins(subproblem, coins) + 1);
        });

        return answer;
    }

    static BiFunction<Integer, ArrayList<Integer>, Integer> bottomUpminimunCoins = (m, coins) -> {

        Integer[] memoIn = new Integer[1];

        memoIn[0] = 0;


        for (int i = 1; i < m + 1; i++) {
            memoIn = Arrays.copyOf (memoIn, memoIn.length + 1);
            for (Integer coin : coins) {

                subproblem = i - coin;
                if (subproblem < 0) continue;


                memoIn[i] = min_ignore_none (memoIn[i], memoIn[subproblem] + 1);
            }

        }

        return memoIn[m];
    };


//    static Integer minimum_coins_with_memoization(Integer m, ArrayList<Integer> coins) {
//
//        memo = Arrays.copyOf (memo, memo.length + 1);
//
//        if(Arrays.stream (memo).toList ().contains (m)){
//            System.out.println ("Memo contains :" + memo[m]);
//            return memo[m];
//        }
//
////        if(memo.contains (m)){
////            return memo.get (m);
////        }
//
//        //valor que quero alcançar
//        if(m==0){
//            answer = 0;
//        }else{
//            answer = null;
//            for(Integer coin: coins){
//                //armazena os resultados anteriores
//                subproblem = m - coin; //13-1 = 12
//
//                //pular as soluções que alcaçam m de subproblemas negativos
//                if(subproblem<0) continue;
//
//                //MIN(NULL, MC(12, [3])--> 11..0 + 1
//                answer = min_ignore_none(answer,
//                        minimum_coins_with_memoization(subproblem, coins) + 1);
//            };
//        }
//
//
//
//          memo[m] = answer;
////        memo.add (m, answer);
//          return answer;
//
//
//
//    }

    public static void main(String[] args) {
//        BiFunction<Integer, Integer, Integer> min_ignore_none = (a, b) -> {
//            if(a == null) return b;
//            if(b == null) return a;
//            return Math.min (a, b);
//        };
//
//        BiFunction<Integer, ArrayList<Integer>, Integer> minimum_coins = (m, coins)->{
//
//        };

        ArrayList<Integer> coins = new ArrayList<> ();
        coins.add (1);
        coins.add (4);
        coins.add (5);

        //Qual o máximo de moedas do conjunto [1,4,5] eu preciso usar para chegar ao valor 13

        //SEM MEMOIZATION
        LocalDateTime begin = LocalDateTime.now ();

        Integer result = Stream.of (bottomUpminimunCoins.apply (8000, coins)).findAny ().get ();

        System.out.println (result);

        Duration duration = Duration.between (begin, LocalDateTime.now ());
        long diff = Math.abs (duration.toMillisPart ());
        System.out.println ("tempo da recursão em ms: " + diff);

    }




}
