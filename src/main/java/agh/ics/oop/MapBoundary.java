package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

    private IWorldMap map;
    private TreeSet<PosType> axisX = new TreeSet<>(new VectorXComparator());
    private TreeSet<PosType> axisY = new TreeSet<>(new VectorYComparator());
    public Vector2d calcUpRight(){
        return new Vector2d(axisX.last().position.x, axisY.last().position.y);
    }
    public Vector2d calcLowerLeft(){
        return new Vector2d(axisX.first().position.x, axisY.first().position.y);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        this.removeElement(new PosType(oldPosition,1));
        this.addElement(new PosType(newPosition,1));
    }
    public void addElement(IMapElement element){
        axisX.add(new PosType(element));
        axisY.add(new PosType(element));
    }
    public void addElement(PosType element){
        axisX.add(element);
        axisY.add(element);
    }
    public void removeElement(Vector2d position, int id){
        axisX.remove(new PosType(position, id));
        axisY.remove(new PosType(position, id));
    }
    public void removeElement(PosType element){
        axisX.remove(element);
        axisY.remove(element);
    }
    private class PosType{
        Vector2d position;
        int id;
        public PosType(IMapElement element){
            position = element.getPosition();
            if( element instanceof Animal){
                this.id=1;
            }
            else if( element instanceof Grass ){
                this.id=2;
            }
            else{
                this.id=3;
            }
        }
        public PosType(Vector2d positionGiven, int id){
            position = positionGiven;
            this.id = id;
        }
    }
    private class VectorXComparator implements Comparator<PosType> {
        @Override
        public int compare( PosType v1, PosType v2){
            if( v1.position.x < v2.position.x ){return -1;}
            else if( v1.position.x > v2.position.x ){return 1;}
            else if( v1.position.y < v2.position.y ){return -1;}
            else if( v1.position.y > v2.position.y ){return 1;}
            else if( v1.id>v2.id ){ return 1;}
            else if( v2.id>v1.id ){ return -1;}
            else {return 0;}
        }
    }
    class VectorYComparator implements Comparator<PosType> {
        @Override
        public int compare( PosType v1, PosType v2){
            if( v1.position.y < v2.position.y ){return -1;}
            else if( v1.position.y > v2.position.y ){return 1;}
            else if( v1.position.x < v2.position.x ){return -1;}
            else if( v1.position.x > v2.position.x ){return 1;}
            else if( v1.id>v2.id ){ return 1;}
            else if( v2.id>v1.id ){ return -1;}
            else {return 0;}
        }
    }

}



