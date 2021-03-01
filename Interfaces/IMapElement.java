package Interfaces;

import Classes.Vector2D;


public interface IMapElement {
    Vector2D getPosition();
    void setPosition(Vector2D position);
    boolean isMovable();
    void move();
}
