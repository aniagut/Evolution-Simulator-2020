package Interfaces;

import Classes.Vector2D;

public interface PositionObserver {
    void update(Vector2D newPos, IMapElement element);
}
