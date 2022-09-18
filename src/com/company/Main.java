package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Connection connection = getConnection();
//
//        System.out.println(connection);
//
//
        String svar;

        while (true) {
            svar = getString("hvad ønsker du ? opret / udskriv / slet / opdater");

            switch (svar) {
                case "opret":
                    indsætNavne();
                    break;
                case "udskriv":
                    udskrivNavne();
                    break;
                case "slet":
                    slet();
                    break;
                case "opdater":
                    opdaterNavn();
                    break;
                default:
                    System.out.println("den fangede jeg sgu ikke lige");
            }
        }




    }

    private static void opdaterNavn() {
        udskrivNavne();
        String sql = "update  Navne set Navne = ? where idNavne = ?";

        try (Connection con =getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {
//            ps.setString(1, getString("angiv nyt navn"));

            // det er det her jeg søger på.
            String kundeNavn = getString("angiv et nyt navn");
            ps.setString(1,kundeNavn);
            ps.setInt(2, getInt("skriv et tal"));


            int res = ps.executeUpdate();    //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            if (res > 0) {

                System.out.println("Kunden med navnet " + "\"" + kundeNavn + "\"" + " er nu blevet opdateret");
            }
            else {
                System.out.println("en kunde med det nr fandtes ikke i listen ");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        udskrivNavne();
    }

    private static void slet() {
        udskrivNavne();
        String sql = "delete from Navne where navne = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {

            String kundeNavn = getString("skriv navnet på den kunde du vil slette");
            ps.setString(1, kundeNavn);


            int res = ps.executeUpdate();       //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            if (res > 0) {

                System.out.println("Kunden med navnet " + "\"" + kundeNavn + "\"" + " er nu blevet slettet");
            } else {
                System.out.println("en kunde med navnet " + "\"" + kundeNavn + "\"" + " fandtes ikke i listen");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        udskrivNavne();
    }

    private static void indsætNavne() {
        String sql = "INSERT INTO Navne (navne) VALUES (?)";

        // se lige try-with-resources f.eks. her  https://www.baeldung.com/java-try-with-resources
        try (Connection con = getConnection();  // får en connection

             // se evt. https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            // her klargøres mit prepared statement ved at min parametre indsættes.
            ps.setString(1, getString("skriv et navn"));

            ps.executeUpdate();    //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            System.out.println("vi er nået til række: " + ids.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void udskrivNavne() {
        List<String> kundeList = new ArrayList<>();

        String sql1 = "select * from Navne ";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql1)) {           // https://en.wikipedia.org/wiki/Prepared_statement

            ResultSet resultSet = ps.executeQuery();   //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            while (resultSet.next()) {
                int id = resultSet.getInt("idNavne");
                String navn = resultSet.getString("Navne");

                kundeList.add(id + " : " + navn);

                for (String s : kundeList) {
                    System.out.println(s);

                }
                for (String s : kundeList) {
                    System.out.println(s);
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static String getString(String s) {
        System.out.println(s + " : ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/demodemo?serverTimezone=CET&useSSL=false";
        String user = "root";
        String password = "AskildogKonrad";
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

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
}
