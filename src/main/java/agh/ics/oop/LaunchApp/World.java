package agh.ics.oop.LaunchApp;
import agh.ics.oop.gui.App;
import javafx.application.Application;

import static java.lang.System.out;

public class World {
    public static void main( String[] args){
        try{
            Application.launch(App.class, args);
        }
        catch (IllegalArgumentException illegalArgumentException){
            out.println(illegalArgumentException.getMessage());
            System.exit(0);
        }
    }
}


