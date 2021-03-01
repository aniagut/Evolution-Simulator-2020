package Enum;

import Classes.Vector2D;

public enum Direction {
    NORTH,NORTHEAST,EAST,SOUTHEAST,SOUTH,SOUTHWEST,WEST,NORTHWEST;

    @Override
    public String toString() {

        return switch (this) {
            case NORTH -> "↑";
            case SOUTH -> "↓";
            case WEST -> "←";
            case EAST -> "→";
            case NORTHWEST -> "↖";
            case SOUTHWEST -> "↙";
            case NORTHEAST -> "↗";
            case SOUTHEAST -> "↘";
        };
    }

    public Direction rotate(int rotation) {
        Direction[] arr = Direction.values();
        int i = this.ordinal();

        return arr[(i + rotation) % arr.length];
    }

    public Vector2D toVector(){
        switch(this){
            case NORTH:
                return new Vector2D(0,1);
            case NORTHEAST:
                return new Vector2D(1,1);
            case EAST:
                return new Vector2D(1,0);
            case SOUTHEAST:
                return new Vector2D(1,-1);
            case SOUTH:
                return new Vector2D(0,-1);
            case SOUTHWEST:
                return new Vector2D(-1,-1);
            case WEST:
                return new Vector2D(-1,0);
            case NORTHWEST:
                return new Vector2D(-1,1);
        }
        return null;
    }

}
