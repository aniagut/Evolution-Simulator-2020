package World;

import Classes.Animal;
import Classes.Plant;
import Classes.Vector2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {
    GameMap map=new GameMap(30,40,0.1,40,50,30,12);
    @Test
    void getHeight() {
        assertEquals(map.getHeight(),40);
    }

    @Test
    void getWidth() {
        assertEquals(map.getWidth(),30);
    }

    @Test
    void getLowerleftJungle() {
        assertEquals(map.getLowerleftJungle(),new Vector2D(11,14));
    }

    @Test
    void getLowerleftMap() {
        assertEquals(map.getLowerleftMap(),new Vector2D(0,0));
    }

    @Test
    void getUpperrightJungle() {
        assertEquals(map.getUpperrightJungle(),new Vector2D(20,26));
    }

    @Test
    void getUpperrightMap() {
        assertEquals(map.getUpperrightMap(),new Vector2D(30,40));
    }

    @Test
    void getStartEnergy() {
        assertEquals(map.getStartEnergy(),12);
    }

    @Test
    void getFreeAnimal() {
        Animal animal1=new Animal(map);
        assertEquals(map.getFreeAnimal().size(),1199);

    }

    @Test
    void getDeadanimals() {
        Animal animal1=new Animal(map);
        animal1.changeEnergy(-20);
        map.removeDead();
        assertEquals(map.getDeadanimals().size(),1);
        assertTrue(map.getDeadanimals().containsKey(animal1));
    }

    @Test
    void getAnimals() {
        Animal animal1=new Animal(map);
        assertTrue(map.getAnimals().containsKey(animal1.getPosition()));
    }

    Plant plant1=new Plant(map);
    Plant plant2=new Plant(map);
    @Test
    void getFreeplant() {

        assertEquals(map.getFreeplant().size(),1198);

    }

    @Test
    void getOccupiedplant() {
        assertTrue(map.getOccupiedplant().containsKey(plant1.getPosition()));
        assertTrue(map.getOccupiedplant().containsKey(plant2.getPosition()));
    }

    @Test
    void getEra() {
        assertEquals(map.getEra(),1);
    }

    @Test
    void setEra() {
        map.setEra(3);
        assertEquals(map.getEra(),3);
    }

    @Test
    void isOccupied() {
        assertTrue(map.isOccupied(plant1.getPosition()));
        assertFalse(map.isOccupied(new Vector2D(0,0)));
    }

    @Test
    void getObject() {
        assertEquals(map.getObject(new Vector2D(0,0)),null);
        assertEquals(map.getObject(plant1.getPosition()),plant1);
    }

    @Test
    void toRightPosition() {
        assertEquals(map.toRightPosition(new Vector2D(30,40)),new Vector2D(0,0));
    }

}