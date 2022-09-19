package com.company.Persistens_Niveau3;

import com.company.Logik_Niveau2.TerminalInput;

import java.util.LinkedList;
import java.util.List;

public class MinListe {
    List<String> liste = new LinkedList<>();

    public MinListe() {
        liste.add("palle");
        liste.add("ulla");
        liste.add("lone");
        liste.add("bo");
    }


    protected void indsætNavne(){
       // liste.add(TerminalInput.getString("Skriv et navn"));
        Mapper.indsætNavne();
    }
    protected void opdaterNavn(){
        Mapper.opdaterNavn();
    }
    protected void slet(){
        //liste.remove(TerminalInput.getString("skriv et navn"));
        Mapper.slet();
    }



    protected void udskrivNavne(){
        for (String s : liste) {
            System.out.println(s);
        }
    }

}
