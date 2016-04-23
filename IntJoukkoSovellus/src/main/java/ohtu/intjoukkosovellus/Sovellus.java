package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class Sovellus {

    private IntJoukko a, b, c;
    private Scanner lukija;
    private Operoija operoija;

    public Sovellus() {
        this.a = new IntJoukko();
        this.b = new IntJoukko();
        this.c = new IntJoukko();
        this.lukija = new Scanner(System.in);
        this.operoija = new Operoija();
    }

    private IntJoukko mikaJoukko() {
        String luettu = lukija.nextLine();

        while (true) {
            if (luettu.matches("^a|A$")) {
                return a;
            } else if (luettu.matches("^b|B$")) {
                return b;
            } else if (luettu.matches("^c|C$")) {
                return c;
            } else {
                System.out.println("Virheellinen joukko! " + luettu);
                System.out.print("Yritä uudelleen!");
                luettu = lukija.nextLine();
            }
        }
    }

    private void lisaa() {
        int lisLuku;
        IntJoukko joukko;
        Scanner lukija = new Scanner(System.in);
        System.out.print("Mihin joukkoon? ");
        joukko = mikaJoukko();
        System.out.println("");
        System.out.print("Mikä luku lisätään? ");
        lisLuku = lukija.nextInt();
        joukko.lisaaLuku(lisLuku);
        return;

    }

    private void yhdiste() {
        IntJoukko aJoukko, bJoukko, c;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko();
        c = operoija.yhdiste(aJoukko, bJoukko);
        System.out.println("A yhdiste B = " + c.toString());
        return;
    }

    private void leikkaus() {
        IntJoukko aJoukko, bJoukko, c;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko();
        c = operoija.leikkaus(aJoukko, bJoukko);
        System.out.println("A leikkaus B = " + c.toString());
        return;
    }

    private void erotus() {
        IntJoukko aJoukko, bJoukko, c;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko();
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko();
        c = operoija.erotus(aJoukko, bJoukko);
        System.out.println("A erotus B = " + c.toString());
        return;
    }

    private void poista() {
        IntJoukko joukko;
        int lisLuku;
        Scanner lukija = new Scanner(System.in);
        System.out.print("Mistä joukosta? ");
        joukko = mikaJoukko();
        System.out.print("Mikä luku poistetaan? ");
        lisLuku = lukija.nextInt();
        joukko.poistaLuku(lisLuku);
        return;
    }

    private void kuuluu() {
        IntJoukko joukko;
        int kysLuku;
        Scanner lukija = new Scanner(System.in);
        System.out.print("Mihin joukkoon? ");
        joukko = mikaJoukko();
        System.out.print("Mikä luku? ");
        kysLuku = lukija.nextInt();
        int kuuluuko = joukko.etsiLuku(kysLuku);
        if (kuuluuko != -1) {
            System.out.println(kysLuku + " etsiLuku joukkoon ");
        } else {
            System.out.println(kysLuku + " ei kuulu joukkoon ");
        }
        return;
    }

    public void main(String[] args) {
        System.out.println("Tervetuloa joukkolaboratorioon!");
        System.out.println("Käytössäsi ovat joukot A, B ja C.");
        System.out.println("Komennot ovat lisää(li), poistaLuku(p), etsiLuku(k), yhdiste(y), erotus(e), leikkaus(le) ja lopetus(quit)(q).");
        System.out.println("Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.");

        String luettu;

        while (true) {
            luettu = lukija.nextLine();

            if (luettu.equals("lisää") || luettu.equals("li")) {
                lisaa();
            } else if (luettu.equalsIgnoreCase("poistaLuku") || luettu.equalsIgnoreCase("p")) {
                poista();
            } else if (luettu.equalsIgnoreCase("etsiLuku") || luettu.equalsIgnoreCase("k")) {
                kuuluu();
            } else if (luettu.equalsIgnoreCase("yhdiste") || luettu.equalsIgnoreCase("y")) {
                yhdiste();
            } else if (luettu.equalsIgnoreCase("leikkaus") || luettu.equalsIgnoreCase("le")) {
                leikkaus();
            } else if (luettu.equalsIgnoreCase("erotus") || luettu.equalsIgnoreCase("e")) {
                erotus();
            } else if (luettu.equalsIgnoreCase("A")) {
                System.out.println(a);
            } else if (luettu.equalsIgnoreCase("B")) {
                System.out.println(b);
            } else if (luettu.equalsIgnoreCase("C")) {
                System.out.println(c);
            } else if (luettu.equalsIgnoreCase("lopeta") || luettu.equalsIgnoreCase("quit") || luettu.equalsIgnoreCase("q")) {
                System.out.println("Lopetetaan, moikka!");
                break;
            } else {
                System.out.println("Virheellinen komento! " + luettu);
                System.out.println("Komennot ovat lisää(li), poistaLuku(p), etsiLuku(k), yhdiste(y), erotus(e) ja leikkaus(le).");
            }
            System.out.println("Komennot ovat lisää(li), poistaLuku(p), etsiLuku(k), yhdiste(y), erotus(e) ja leikkaus(le).");
        }
    }
}
