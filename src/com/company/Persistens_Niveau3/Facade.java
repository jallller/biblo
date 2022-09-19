package com.company.Persistens_Niveau3;

public class Facade {
    static MinListe minListe = new MinListe();

    public static void opretBruger() {
        Mapper.indsætNavne();
//        minListe.indsætNavne();
    }

    public static void opdaterBruger() {
        Mapper.opdaterNavn();
//        minListe.opdaterNavn();
    }

    public static void sletBruger() {
        Mapper.slet();
//        minListe.slet();
    }

    public static void udskrivBrugere() {
        Mapper.udskrivNavne();
       // minListe.udskrivNavne();
    }
}
