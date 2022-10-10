package agh.ics.oop;
import static java.lang.System.out;

public class World {
   public static void run( MoveDirection[] tab ){
       for (MoveDirection direction : tab) {
           switch (direction) {
               case FORWARD -> out.println("Zwierzak idzie do przodu");
               case BACKWARD -> out.println("Zwierzak idzie do tyłu");
               case RIGHT -> out.println("Zwierzak skręca w prawo");
               case LEFT -> out.println("Zwierzak skręca w lewo");
               default -> {
               }
           }
       }
   }
    public static MoveDirection[] change(String[] tab ){
        int length = 0;
        for (String s : tab) {
            if (s.equals("f") || s.equals("b") || s.equals("r") || s.equals("l")) {
                length++;
            }
        }
        MoveDirection[] tab_enum = new MoveDirection[length];
        int curr_index = 0;
        for(String el: tab){
            switch (el) {
                case "f" :
                    tab_enum[ curr_index ] = MoveDirection.FORWARD;
                    curr_index++;
                    break;
                case "b" :
                    tab_enum[ curr_index ] = MoveDirection.BACKWARD;
                    curr_index++;
                    break;
                case "r" :
                    tab_enum[ curr_index ] = MoveDirection.RIGHT;
                    curr_index++;
                    break;
                case "l" :
                    tab_enum[ curr_index ] = MoveDirection.LEFT;
                    curr_index++;
                    break;
                default:
                    break;
                }
            }
        return tab_enum;
    }
    public static void main( String[] args){
        out.println("system wystartował");
        MoveDirection[] tab_enum = change(args);
        run(tab_enum);
        out.println("system zakończył działanie");

        Vector2d v = new Vector2d(4,3);
        Vector2d u = new Vector2d(2,5);
        out.println(v.follows(u));
        out.println(v.subtract(u));
        out.println(u.equals(v));
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection el = MapDirection.NORTH;
        out.println( el.toString( ));
        MapDirection el2 = el.previous();
        out.println( el2.toString( ));
    }
}


