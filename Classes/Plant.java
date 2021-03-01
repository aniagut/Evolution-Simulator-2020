package Classes;

import Interfaces.IMapElement;
import World.GameMap;

public class Plant implements IMapElement {
    private Vector2D position;
    private GameMap map;

    public Plant(GameMap map){
        this.map=map;
        int n=this.map.getFreeplant().size();
        if(n>0){
            int j=(int)(Math.random()*n);
            this.position=this.map.getFreeplant().get(j);
            this.map.getFreeplant().remove(position);
            this.map.getOccupiedplant().put(position,this);
        }
    }
    public Plant(GameMap map,Vector2D position){
        this.map=map;
        this.position=position;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2D position){
        this.position=position;
    }

    @Override
    public boolean isMovable(){
        return false;
    }
    @Override
    public void move(){}
    @Override
    public String toString() {
        return "⚘";
    }
}
