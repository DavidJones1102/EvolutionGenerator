package agh.ics.oop.MapElementsValues;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x, int y){
        this.x=x;
        this.y=y;
    }

    public String toString(){
        return "("+this.x+","+this.y+")";
    }

    public boolean follows( Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }
    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }

    public Vector2d upperRight(Vector2d other){
        return new Vector2d(Math.max(this.x,other.x), Math.max(this.y, other.y));
    }
    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(Math.min(this.x,other.x), Math.min(this.y, other.y));
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x, this.y+other.y);
    }
    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x, this.y-other.y);
    }

    public Vector2d opposite(){
        return new Vector2d( -this.x, -this.y);
    }

    public boolean equals(Object other){
        if ( other instanceof Vector2d ){
            return this.x==((Vector2d)other).x && this.y==((Vector2d)other).y;
        }
        else {return false;}
    }
    public int hashCode(){ return Integer.hashCode(this.x)+Integer.hashCode(this.y);}
}


