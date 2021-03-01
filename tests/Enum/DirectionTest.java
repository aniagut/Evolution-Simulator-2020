package Enum;

import Classes.Vector2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {
    Direction direction=Direction.NORTH;
    @Test
    void rotate() {
        assertEquals(direction.rotate(2),Direction.EAST);
        assertEquals(Direction.SOUTH.rotate(3),Direction.NORTHWEST);
    }

    @Test
    void toVector() {
        assertEquals(direction.toVector(),new Vector2D(0,1));
        assertEquals(Direction.NORTHWEST.toVector(),new Vector2D(-1,1));
    }
}