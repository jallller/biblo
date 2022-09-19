package com.company.Logik_Niveau2;

import java.util.Scanner;

public class TerminalInput {
    public static int getInt(String s){

        while (true) {
            try {
                int ans = Integer.parseInt(getString(s));
                return ans;
            } catch (NumberFormatException e) {
                System.out.println("husk ikke et tal ord");
            }
        }
    }

    public static String getString(String s) {
        System.out.println(s + " : ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
