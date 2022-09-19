package com.company.Persistens_Niveau3;

import com.company.Logik_Niveau2.TerminalInput;
import com.company.Persistens_Niveau3.ConnectionConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    static void opdaterNavn() {
        udskrivNavne();
        String sql = "update  Navne set Navne = ? where idNavne = ?";

        try (Connection con = ConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {
//            ps.setString(1, getString("angiv nyt navn"));

            // det er det her jeg søger på.
            String kundeNavn = TerminalInput.getString("angiv et nyt navn");
            ps.setString(1, kundeNavn);
            ps.setInt(2, TerminalInput.getInt("skriv et tal"));


            int res = ps.executeUpdate();    //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            if (res > 0) {

                System.out.println("Kunden med navnet " + "\"" + kundeNavn + "\"" + " er nu blevet opdateret");
            } else {
                System.out.println("en kunde med det nr fandtes ikke i listen ");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        udskrivNavne();
    }

    static void slet() {
        udskrivNavne();
        String sql = "delete from Navne where navne = ?";

        try (Connection con = ConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {

            String kundeNavn = TerminalInput.getString("skriv navnet på den kunde du vil slette");
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

    static void indsætNavne() {
        String sql = "INSERT INTO Navne (navne) VALUES (?)";

        // se lige try-with-resources f.eks. her  https://www.baeldung.com/java-try-with-resources
        try (Connection con = ConnectionConfig.getConnection();  // får en connection

             // se evt. https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            // her klargøres mit prepared statement ved at min parametre indsættes.
            ps.setString(1, TerminalInput.getString("skriv et navn"));

            ps.executeUpdate();    //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            System.out.println("vi er nået til række: " + ids.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void udskrivNavne() {
        List<String> kundeList = new ArrayList<>();

        String sql1 = "select * from Navne ";

        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql1)) {           // https://en.wikipedia.org/wiki/Prepared_statement

            ResultSet resultSet = ps.executeQuery();   //https://javaconceptoftheday.com/difference-between-executequery-executeupdate-execute-in-jdbc/

            while (resultSet.next()) {
                int id = resultSet.getInt("idNavne");
                String navn = resultSet.getString("Navne");

                kundeList.add(id + " : " + navn);

                for (String s : kundeList) {
                    System.out.println(s);

                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
