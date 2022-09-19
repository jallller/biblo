package com.company;

import com.company.Logik_Niveau2.TerminalInput;
import com.company.Persistens_Niveau3.Facade;

public class Main {

    public static void main(String[] args) {
//        Connection connection = getConnection();
//
//        System.out.println(connection);
//
//

        String svar;

        while (true) {
            svar = TerminalInput.getString("hvad ønsker du ? opret / udskriv / slet / opdater");

            switch (svar) {
                case "opret":
                    Facade.opretBruger();
                    break;
                case "udskriv":
                    Facade.udskrivBrugere();
                    break;
                case "slet":
                    Facade.sletBruger();
                    break;
                case "opdater":
                    Facade.opdaterBruger();
                    break;
                default:
                    System.out.println("den fangede jeg sgu ikke lige");
            }
        }
    }

}
