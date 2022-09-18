package com.company;

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
                    Mapper.indsætNavne();
                    break;
                case "udskriv":
                    Mapper.udskrivNavne();
                    break;
                case "slet":
                    Mapper.slet();
                    break;
                case "opdater":
                    Mapper.opdaterNavn();
                    break;
                default:
                    System.out.println("den fangede jeg sgu ikke lige");
            }
        }




    }

}
