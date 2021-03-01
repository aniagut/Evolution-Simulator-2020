package Classes;

import World.GameMap;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    GameMap map=new GameMap(40,20,0.1,20,3,15,50);
    Animal animal=new Animal(map);
    @org.junit.jupiter.api.Test
    void isDead() {
        assertFalse(animal.isDead());
        animal.changeEnergy(-50);
        assertTrue(animal.isDead());

    }

    @org.junit.jupiter.api.Test
    void changeEnergy() {
        animal.changeEnergy(40);
        assertEquals(animal.getEnergy(),90);
    }

    @org.junit.jupiter.api.Test
    void move() {
        Vector2D position=animal.getPosition();
        animal.move();
        assertNotEquals(animal.getPosition(),position);
    }

    @org.junit.jupiter.api.Test
    void getPosition() {
        animal.setPosition(new Vector2D(1,1));
        assertEquals(animal.getPosition(),new Vector2D(1,1));
    }


    @org.junit.jupiter.api.Test
    void getEnergy() {
        assertEquals(animal.getEnergy(),50);
    }

    @org.junit.jupiter.api.Test
    void getBornepoque() {
        assertEquals(animal.getBornepoque(),1);
    }

    @org.junit.jupiter.api.Test
    void getGenes() {
        assertEquals(animal.getGenes().getAmount(),32);
        assertEquals(animal.getGenes().getRange(),new Vector2D(0,7));
    }

    @org.junit.jupiter.api.Test
    void getChildren() {
        LinkedList<Animal> empty=new LinkedList<>();
        assertEquals(animal.getChildren(),empty);
    }

    @org.junit.jupiter.api.Test
    void setDeathepoque() {
        animal.setDeathepoque(30);
        assertEquals(animal.getDeathepoque(),30);
    }


    @org.junit.jupiter.api.Test
    void copulation() {
        Animal animal2=new Animal(map);
        animal.copulation(animal2);
        assertEquals(animal.getChildren().size(),1);
        assertEquals(animal2.getChildren().size(),1);
    }

    @org.junit.jupiter.api.Test
    void isMovable() {
        assertTrue(animal.isMovable());
    }

}