package com.cryss.tipsandlearnings.programacaodinamica;

import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mochila {

    static public int fibonanci(int n){

        if(n <= 1) return 1;

        return fibonanci (n-1) + fibonanci (n-2);
    }

   static public double[] fibonanciPD(int n){



       double[] f = new double[2];

       f[0]=0L;
       f[1] = 1L;

       for(int i = 2; i <= n-1; i++){
           f = Arrays.copyOf (f, n);
           f[i] = f[i-1] + f[i-2];
       }


       return f;
    }

    public static void main(String[] args) {


        Thread f1 = new Thread () {

            @Override
            public void run() {

                LocalDateTime begin = LocalDateTime.now ();
                double[] fibPD = fibonanciPD (50);
                Duration duration = Duration.between (begin, LocalDateTime.now ());
                long diff = Math.abs (duration.toMillisPart ());
                System.out.println ("Tempo f1: " + diff);
            }
        };

        f1.start ();

        Thread f2 = new Thread () {

            @Override
            public void run() {

                LocalDateTime begin = LocalDateTime.now ();
                fibonanci (50);
                Duration duration = Duration.between (begin, LocalDateTime.now ());
                long diff = Math.abs (duration.toMillisPart ());
                System.out.println ("Tempo f2: " + diff);
            }
        };

        f2.start ();

//        double[] fibPD = fibonanciPD (20000);
//
//
//
//        List<Double> v = Arrays.stream (fibPD)
//                .peek (System.out::println)
//                .boxed ().toList ();

//        System.out.println (fibonanci (200));


    }
}
