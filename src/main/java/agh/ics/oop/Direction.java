package agh.ics.oop;

enum MoveDirection {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT
}

enum MapDirection{
    NORTH ,
    EAST ,
    SOUTH ,
    WEST ;
    private final Vector2d vn = new Vector2d(0,1);
    private final Vector2d ve = new Vector2d(1,0);
    private final Vector2d vs = new Vector2d(0,-1);
    private final Vector2d vw = new Vector2d(-1,0);

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

    public Vector2d toUnitVector() { //!zrobić lepiej, tylko zwracać
        switch (this){
            case NORTH: return vn;
            case EAST: return ve;
            case SOUTH: return vs;
            case WEST: return vw;
            default: return null;
        }
    }
}