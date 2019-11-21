
package ohtu.matkakortti;

import ohtu.matkakortti.Maksukortti;
import ohtu.matkakortti.Kassapaate;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti mockKortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        mockKortti = mock(Maksukortti.class);
    }
    
    @Test
    public void kortiltaVelotetaanHintaJosRahaaOn() {
        when(mockKortti.getSaldo()).thenReturn(10);
        kassa.ostaLounas(mockKortti);
        
        verify(mockKortti, times(1)).getSaldo();
        verify(mockKortti).osta(eq(Kassapaate.HINTA));
    }

    @Test
    public void kortiltaEiVelotetaJosRahaEiRiita() {
        when(mockKortti.getSaldo()).thenReturn(4);
        kassa.ostaLounas(mockKortti);
        
        verify(mockKortti, times(1)).getSaldo();
        verify(mockKortti, times(0)).osta(anyInt());
    }
    
    @Test
    public void lataaJosLadattavaSummaOnPositiivinen() {
        when(mockKortti.getSaldo()).thenReturn(15);
        kassa.lataa(mockKortti, 5);
        
        verify(mockKortti, times(1)).getSaldo();
        verify(mockKortti).lataa(eq(5));
    }
    
    @Test
    public void eiLataaJosLadattavaSummaOnNegatiivinen() {
        when(mockKortti.getSaldo()).thenReturn(-1);
        kassa.lataa(mockKortti, 5);
        
        verify(mockKortti, times(1)).getSaldo();
        verify(mockKortti, times(0)).lataa(anyInt());
    }
      
}
