package Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {
    Vector2D vector=new Vector2D(3,2);
    Vector2D vector1=new Vector2D(4,5);
    Vector2D vector2=new Vector2D(4,5);
    @Test
    void testToString() {
        assertEquals(vector.toString(),"(3,2)");
    }

    @Test
    void add() {
        assertEquals(vector.add(vector1),new Vector2D(7,7));
        assertEquals(vector.add(vector2),new Vector2D(7,7));

    }

    @Test
    void testEquals() {
        assertFalse(vector.equals(vector1));
        assertTrue(vector1.equals(vector2));
    }

    @Test
    void testHashCode() {
        assertEquals(vector1.hashCode(),vector2.hashCode());
        assertNotEquals(vector.hashCode(),vector1.hashCode());
    }
}