
package ohtu.intjoukkosovellus;

import org.junit.Before;


public class IntJoukkuYksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    @Before
    @Override
    public void setUp() {
        joukko = new IntJoukko(3);
        joukko.lisaaLuku(10);
        joukko.lisaaLuku(3);
    }
    
    // perii kaikki testit luokasta IntJoukkoTest
}
