package Classes;

public class Vector2D {

    public final int x;
    public final int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public Vector2D add(Vector2D vector){

        return new Vector2D(this.x+vector.x,this.y+vector.y);
    }

    public boolean equals(Object object){
        if(this==object){
            return true;
        }
        if(object==null || object.getClass()!=this.getClass()){
            return false;
        }
        Vector2D vector= (Vector2D) object;
        return(vector.x==this.x && vector.y==this.y);
    }

    @Override
    public int hashCode() {
        int hash=13;
        hash+=this.x*17;
        hash+=this.y*31;
        return hash;
    }
}

