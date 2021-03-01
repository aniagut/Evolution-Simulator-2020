package Interfaces;

import Classes.Vector2D;

public interface Observable {
    void attach(PositionObserver observer);
    void detach(PositionObserver observer);
    void notifyObservers(Vector2D newPos);
}
