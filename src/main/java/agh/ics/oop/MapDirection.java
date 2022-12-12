package agh.ics.oop;
public enum MapDirection{
    NORTH ,
    NORTHEAST,
    EAST ,
    SOUTHEAST,
    SOUTH ,
    SOUTHWEST,
    WEST ,
    NORTHWEST;
    private final Vector2d vn = new Vector2d(0,1);
    private final Vector2d vne = new Vector2d(1,1);
    private final Vector2d ve = new Vector2d(1,0);
    private final Vector2d vse = new Vector2d(1,-1);
    private final Vector2d vs = new Vector2d(0,-1);
    private final Vector2d vsw = new Vector2d(-1,-1);
    private final Vector2d vw = new Vector2d(-1,0);
    private final Vector2d vnw = new Vector2d(-1,1);

    public String toString(){
        switch (this){
            case NORTH: return "N";
            case SOUTH: return "S";
            case WEST: return "W";
            case EAST: return "E";
            case NORTHWEST: return "NW";
            case SOUTHWEST: return "SW";
            case NORTHEAST: return "NE";
            case SOUTHEAST: return "SE";
            default: return null;
        }

    }
    public MapDirection next( ){
        return this.values()[ (this.ordinal()+1)%8 ];
    }
    public MapDirection previous( ){
        return this.values()[ (this.ordinal()+3)%8 ];
    }

    public Vector2d toUnitVector() {
        switch (this){
            case NORTH: return vn;
            case NORTHEAST: return vne;
            case EAST: return ve;
            case SOUTHEAST: return vse;
            case SOUTH: return vs;
            case SOUTHWEST: return vsw;
            case WEST: return vw;
            case NORTHWEST: return vnw;
            default: return null;
        }
    }
}