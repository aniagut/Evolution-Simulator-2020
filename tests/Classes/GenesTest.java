package Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenesTest {
    Genes genes=new Genes(new Vector2D(3,10),40);
    @Test
    void getAmount() {
        assertEquals(genes.getAmount(),40);
    }

    @Test
    void getGenesList() {
        assertEquals(genes.getGenesList().length,40);
        Genes genes1=new Genes(new Vector2D(3,10),40);
        Genes genes2=new Genes(genes,genes1);
        boolean[] contains={false,false,false,false,false,false,false,false};
        for(int i=0;i<40;i++){
            contains[genes2.getGenesList()[i]-3]=true;
        }
        for(int i=3;i<=10;i++){
            assertTrue(contains[i-3]);
        }
    }

    @Test
    void getRange() {
        assertEquals(genes.getRange(),new Vector2D(3,10));
    }


    @Test
    void testEquals() {
        Genes genes3=new Genes(new Vector2D(1,1),10);
        Genes genes4=new Genes(new Vector2D(1,1),10);
        assertTrue(genes3.equals(genes4));
    }
}