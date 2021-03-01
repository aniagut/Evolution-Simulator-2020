package Classes;

import World.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlantTest {
    GameMap map=new GameMap(10,10,0.4,30,90,93,43);
    Plant plant=new Plant(map);

    @Test
    void setPosition() {
        plant.setPosition(new Vector2D(3,4));
        assertEquals(plant.getPosition(),new Vector2D(3,4));
    }


    @Test
    void isMovable() {
        assertFalse(plant.isMovable());
    }
}