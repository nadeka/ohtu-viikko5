package ohtu.intjoukkosovellus;

public class Operoija {

    public IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();

        lisaaKaikki(a.getIntArray(), x);
        lisaaKaikki(b.getIntArray(), x);

        return x;
    }

    public IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();

        lisaaSamat(a.getIntArray(), b.getIntArray(), new IntJoukko());

        return y;

    }

    public IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();

        lisaaKaikki(a.getIntArray(), z);
        poistaKaikki(b.getIntArray(), z);

        return z;
    }

    private void lisaaSamat(int[] taulu1, int[] taulu2, IntJoukko joukkoon) {
        for (int i = 0; i < taulu1.length; i++) {
            for (int j = 0; j < taulu2.length; j++) {
                if (taulu1[i] == taulu2[j]) {
                    joukkoon.lisaaLuku(taulu2[j]);
                }
            }
        }
    }

    private void lisaaKaikki(int[] taulusta, IntJoukko joukkoon) {
        for (int i = 0; i < taulusta.length; i++) {
            joukkoon.lisaaLuku(taulusta[i]);
        }
    }

    private void poistaKaikki(int[] taulu, IntJoukko joukosta) {
        for (int i = 0; i < taulu.length; i++) {
            joukosta.poistaLuku(i);
        }
    }
}
