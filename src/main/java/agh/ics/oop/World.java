package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Application;

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

    public static void main( String[] args){
       //Animal animal = new Animal();
       //out.println("system wystartował");
       //MoveDirection[] tab_enum = OptionsParser.parse(args);
       //run(tab_enum, animal);
       //out.println("system zakończył działanie");

        try{
//            MoveDirection[] directions = new OptionsParser().parse(args);
//            IWorldMap map = new RectangularMap(10, 5);
//            //IWorldMap map = new GrassField(10);
//            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//            IEngine engine = new SimulationEngine(directions, map, positions);
//            engine.run();
            Application.launch(App.class, args);
            //out.println(map);
        }
        catch (IllegalArgumentException illegalArgumentException){
            out.println(illegalArgumentException.getMessage());
            System.exit(0);
        }
    }
}


