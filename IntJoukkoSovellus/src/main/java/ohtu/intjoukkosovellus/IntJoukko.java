
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
                                                 // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        this();
        if (kapasiteetti > 0)
            ljono = new int[kapasiteetti];
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        this(kapasiteetti);
        if (kasvatuskoko < 0)
            this.kasvatuskoko = kasvatuskoko;
    }

    public int[] getIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(ljono, 0, taulu, 0, alkioidenLkm);
        return taulu;
    }

    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }

    public boolean lisaaLuku(int luku) {
        if (etsiLuku(luku) != -1) {
            return false;
        }

        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;

        if (taulukkoTaynna()) {
            luoUusiTaulukko();
        }

        return true;
    }

    private boolean taulukkoTaynna() {
        return alkioidenLkm % ljono.length == 0;
    }

    private void asetaLuku(int indeksi, int luku) {
        if (indeksi >= 0 && indeksi < ljono.length) {
            ljono[indeksi] = luku;
        }
    }

    private void luoUusiTaulukko() {
        int[] vanhaTaulukko = ljono;
        ljono = new int[alkioidenLkm + kasvatuskoko];
        System.arraycopy(vanhaTaulukko, 0, ljono, 0, vanhaTaulukko.length);
    }

    public int etsiLuku(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i])
                return i;
        }

        return -1;
    }

    public boolean poistaLuku(int luku) {
        int kohta = etsiLuku(luku);
        asetaLuku(kohta, 0);
        vaihdaLuvutAlkaen(kohta);
        alkioidenLkm--;

        return true;
    }

    private void vaihdaLuvutAlkaen(int indeksi) {
        if (indeksi >= 0) {
            for (int j = indeksi; j < alkioidenLkm - 1; j++) {
                vaihda(j, j + 1);
            }
        }
    }

    private void vaihda(int indeksiA, int indeksiB) {
        int apu = ljono[indeksiA];
        ljono[indeksiA] = ljono[indeksiB];
        ljono[indeksiB] = apu;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (int i = 0; i < alkioidenLkm; i++) {
            sb.append(ljono[i]);

            if (i != alkioidenLkm - 1)
                sb.append(", ");
        }

        return sb.append("}").toString();
    }
}