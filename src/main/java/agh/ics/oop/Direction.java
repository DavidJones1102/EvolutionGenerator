package agh.ics.oop;

enum MoveDirection {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT
}

enum MapDirection{
    NORTH,

    EAST,
    SOUTH,
    WEST;

    public String toString( MapDirection el){
        switch (this){
            case NORTH: return "Północ";
            case EAST: return "Zachód";
            case SOUTH: return "Południe";
            case WEST: return "Wschód";
            default: return null;
        }

    }
    public MapDirection next( ){
        return this.values()[ (this.ordinal()+1)%4 ];
    }
    public MapDirection previous( ){
        return this.values()[ (this.ordinal()+3)%4 ];
    }

    public Vector2d toUnitVector() {
        switch (this){
            case NORTH: return new Vector2d(0,1);
            case EAST: return new Vector2d(1,0);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
            default: return null;
        }
    }
}