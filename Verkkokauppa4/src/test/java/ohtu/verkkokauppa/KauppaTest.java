package ohtu.verkkokauppa;

import org.junit.Test;
import static org.mockito.Mockito.*;

public class KauppaTest {

    @Test
    public void pankkiaKutsutaanOikeillaParametreillaKunYksiTuoteVarastossa() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(10);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Shampoo", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pirjo", "321");

        verify(pankki).tilisiirto("pirjo", 10, "321", "33333-44455", 3);
    }

    @Test
    public void pankkiaKutsutaanOikeillaParametreillaKunKaksiEriTuotettaVarastossa() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(10);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.saldo(2)).thenReturn(1);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Shampoo", 3));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Hoitoaine", 4));

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pirjo", "321");

        verify(pankki).tilisiirto("pirjo", 10, "321", "33333-44455", 7);
    }

    @Test
    public void pankkiaKutsutaanOikeillaParametreillaKunKaksiSamaaTuotettaVarastossa() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(10);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(2);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Shampoo", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pirjo", "321");

        verify(pankki).tilisiirto("pirjo", 10, "321", "33333-44455", 6);
    }

    @Test
    public void pankkiaKutsutaanOikeillaParametreillaKunToinenTuoteVarastossa() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(10);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Shampoo", 3));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Hoitoaine", 4));

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pirjo", "321");

        verify(pankki).tilisiirto("pirjo", 10, "321", "33333-44455", 3);
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(10);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Shampoo", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pirjo", "321");

        verify(pankki).tilisiirto("pirjo", 10, "321", "33333-44455", 3);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("marjaana", "123");

        verify(pankki).tilisiirto("marjaana", 10, "123", "33333-44455", 3);
    }

    @Test
    public void jokaOstoksellaUusiViitenumero() {
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(10).thenReturn(15);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Shampoo", 3));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pirjo", "321");

        verify(pankki).tilisiirto("pirjo", 10, "321", "33333-44455", 3);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("marjaana", "123");

        verify(pankki).tilisiirto("marjaana", 15, "123", "33333-44455", 3);
    }

    @Test
    public void varastoonPalautetaanTuote() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);

        Tuote tuote = new Tuote(1, "Shampoo", 3);

        when(varasto.saldo(1)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(tuote);

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.poistaKorista(1);

        verify(varasto).palautaVarastoon(tuote);
    }
}