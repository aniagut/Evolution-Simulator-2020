package Interfaces;

import Classes.Vector2D;

public interface IGameMap{
    boolean isOccupied(Vector2D position);
    boolean place(IMapElement element);
    Object getObject(Vector2D position);
    void eating();
    void copulation();
    void moveanimals();
    void removeDead();
    void nextDay();
    int getStartEnergy();
    void refreshstats();
}
