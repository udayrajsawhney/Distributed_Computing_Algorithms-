/*
 * Developed by udaysawhney on 12/2/19 1:24 PM.
 * Last modified 12/2/19 1:24 PM.
 * Github : https://github.com/udayrajsawhney
 * Copyright (c) 2019. All rights reserved.
 */

import alternatealgorithm.Modulus;
import sasakialgorithm.Sasaki;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void input() {
        int n;
        List<Integer> list = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes");
        n = sc.nextInt();
        System.out.println("Enter the value for each process");
        for (int i = 0; i < n; i++) list.add(sc.nextInt());
        System.out.println("Enter 1 to run Sasaki, 2 to run alternate (n-1) algorithm");
        int ch = sc.nextInt();
        switch (ch) {
            case 1:
                Sasaki sasaki = new Sasaki();
                sasaki.algorithm(list, list.size());
                break;
            case 2:
                Modulus modulus = new Modulus();
                modulus.algorithm(list, list.size());
                break;

        }
    }

    public static void main(String[] args) {
        App app = new App();
        List<Integer> list;
        app.input();
    }
}
