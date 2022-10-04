package agh.ics.oop;
import static java.lang.System.out;

public class World {
    public static void run( Direction[] tab ){
        for(Direction el: tab){
            switch (el) {
                case FORWARD:
                    out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    out.println("Zwierzak skręca w lewo");
                    break;
                default:
                    break;
            }
        }
    }
    public static Direction[] change( String[] tab ){
        int length = tab.length;
        Direction[] tab_enum = new Direction[length];
        for(int i=0; i<length; i++){
            switch (tab[i]){
                case "f":
                    tab_enum[i] = Direction.FORWARD;
                    break;
                case "b":
                    tab_enum[i] = Direction.BACKWARD;
                    break;
                case "r":
                    tab_enum[i] = Direction.RIGHT;
                    break;
                case "l":
                    tab_enum[i] = Direction.LEFT;
                    break;
                default:
                    tab_enum[i] = Direction.UNKNOWN;
                    break;
            }
        }
        return tab_enum;
    }
    public static void main( String[] args){
        out.println("system wystartował");
        Direction[] tab_enum = change(args);
        run(tab_enum);
        out.println("system zakończył działanie");
    }
}
