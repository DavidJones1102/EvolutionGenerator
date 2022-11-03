package agh.ics.oop;
import static java.lang.System.out;

public class World {
   public static void run( MoveDirection[] tab, Animal animal ){
       for (MoveDirection direction : tab) {
           animal.move(direction);
           out.println(animal);
           //switch (direction) {
           //    case FORWARD -> out.println("Zwierzak idzie do przodu");
           //    case BACKWARD -> out.println("Zwierzak idzie do tyłu");
           //    case RIGHT -> out.println("Zwierzak skręca w prawo");
           //    case LEFT -> out.println("Zwierzak skręca w lewo");
           //    default -> {
           //    }
           //}
       }
   }
   // public static MoveDirection[] change(String[] tab ){
   //     int length = 0;
   //     for (String s : tab) {
   //         if (s.equals("f") || s.equals("b") || s.equals("r") || s.equals("l")) {
   //             length++;
   //         }
   //     }
   //     MoveDirection[] tab_enum = new MoveDirection[length];
   //     int curr_index = 0;
   //     for(String el: tab){
   //         switch (el) {
   //             case "f" :
   //                 tab_enum[ curr_index ] = MoveDirection.FORWARD;
   //                 curr_index++;
   //                 break;
   //             case "b" :
   //                 tab_enum[ curr_index ] = MoveDirection.BACKWARD;
   //                 curr_index++;
   //                 break;
   //             case "r" :
   //                 tab_enum[ curr_index ] = MoveDirection.RIGHT;
   //                 curr_index++;
   //                 break;
   //             case "l" :
   //                 tab_enum[ curr_index ] = MoveDirection.LEFT;
   //                 curr_index++;
   //                 break;
   //             default:
   //                 break;
   //             }
   //         }
   //     return tab_enum;
   // }
    public static void main( String[] args){
       //Animal animal = new Animal();
       //out.println("system wystartował");
       //MoveDirection[] tab_enum = OptionsParser.parse(args);
       //run(tab_enum, animal);
       //out.println("system zakończył działanie");

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        out.println(map);
    }
}


