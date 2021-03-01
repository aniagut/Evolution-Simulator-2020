package Classes;

import java.util.*;
import Enum.Direction;
import Interfaces.Observable;
import Interfaces.IMapElement;
import Interfaces.PositionObserver;
import World.GameMap;


public class Animal implements IMapElement, Observable {

    private int energy;
    private Vector2D position;
    private final Genes genes;
    private Direction direction;
    private final GameMap map;
    private ArrayList<PositionObserver> observerlist=new ArrayList<>();
    private ArrayList<Animal> children;
    private final int bornepoque;
    private int deathepoque;



    public Animal(GameMap map){

        this.map=map;
        this.bornepoque=map.getEra();
        this.energy=map.getStartEnergy();

        this.direction=Direction.NORTH;
        int rotation=(int)(Math.random()*8);
        this.direction=this.direction.rotate(rotation);

        this.genes=new Genes(new Vector2D(0,7),32);

        map.place(this);

        this.observerlist=new ArrayList<PositionObserver>();
        this.attach(map);

        this.children=new ArrayList<Animal>();

    }

    public Animal(GameMap map, Animal parent1, Animal parent2){
        this.map=map;
        this.bornepoque=map.getEra();

        this.direction= Direction.NORTH;
        int rotation=(int)(Math.random()*8);
        this.direction=this.direction.rotate(rotation);

        this.genes=new Genes(parent1.genes,parent2.genes);
        Direction[] directions = Direction.values();
        ArrayList<Integer> notchecked=new ArrayList<>();
        for(int k=0;k<8;k++){
            notchecked.add(k);
        }
        boolean placed = false;
        while(notchecked.size()>0 && !placed){
            int j=(int)(Math.random()*notchecked.size());
            Vector2D newpos=parent1.position.add(directions[notchecked.get(j)].toVector());
            notchecked.remove(j);
            if(this.map.getFreeAnimal().contains(map.toRightPosition(newpos))) {
                this.position = map.toRightPosition(newpos);
                placed=true;
            }
        }
        if(!placed){
            int j=(int)(Math.random()*directions.length);
            Vector2D newpos=parent1.position.add(directions[j].toVector());
            this.position = map.toRightPosition(newpos);
        }
        map.getFreeplant().remove(this.position);

        this.observerlist= new ArrayList<PositionObserver>();
        this.observerlist.add(map);

        this.children=new ArrayList<Animal>();

    }

    public boolean isDead() {
        return this.energy <= 0;
    }


    public void changeEnergy(int value){
        this.energy+=value;
    }


    @Override
    public void move(){
        int idx=(int)(Math.random()*(this.genes.getAmount()));
        this.direction=this.direction.rotate(this.genes.getGenesList()[idx]);
        Vector2D where=this.position.add(this.direction.toVector());
        where=this.map.toRightPosition(where);
        notifyObservers(where);
    }

    @Override
    public Vector2D getPosition(){
        return this.position;
    }

    @Override
    public void setPosition(Vector2D position){
        this.position=position;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getEnergy(){
        return energy;
    }
    public int getBornepoque(){
        return this.bornepoque;
    }
    public Genes getGenes(){
        return genes;
    }
    public ArrayList<Animal> getChildren(){
        return children;
    }
    public void setDeathepoque(int epoque){
        deathepoque=epoque;
    }
    public int getDeathepoque() {
        return deathepoque;
    }


    public Animal copulation(Animal other){
        int energy= (int) (0.25 * this.energy+0.25* other.energy);
       this.changeEnergy((int) (-0.25 * this.energy));
       other.changeEnergy((int) (-0.25* other.energy));
       Animal children=new Animal(this.map,this,other);
       this.children.add(children);
       other.children.add(children);
       children.energy=energy;
       return children;
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public void attach(PositionObserver observer) {
        observerlist.add(observer);
    }

    @Override
    public void detach(PositionObserver observer) {
        observerlist.remove(observer);
    }

    @Override
    public void notifyObservers( Vector2D newPos) {
        for (PositionObserver positionObserver : observerlist) {
            positionObserver.update(newPos, this);
        }
    }

}

