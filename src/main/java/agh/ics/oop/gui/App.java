package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.*;
import javafx.geometry.HPos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.*;


public class App extends Application {
    private AbstractWorldMap map;
    @Override
    public void init() throws Exception {
        String [] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] directions = new OptionsParser().parse(args);
        //map = new RectangularMap(10, 5);
        map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);
    }

    @Override
    public void start(Stage primaryStage){
        GridPane gridPane = new GridPane();
//        gridPane.getColumnConstraints().add(new ColumnConstraints(500));
//        gridPane.getRowConstraints().add(new RowConstraints(500));
        gridPane.setGridLinesVisible(true);
        Vector2d lowerLeft = map.calcLowerLeft();
        Vector2d upperRight = map.calcUpRight();
        for(int i=lowerLeft.x+1 ;i<=upperRight.x;i++){
            Text toAdd = new Text(Integer.toString(i-1));
            gridPane.add(toAdd,i,0);
            GridPane.setHalignment(toAdd, HPos.CENTER);
        }
        for(int i=lowerLeft.y ;i<=upperRight.y;i++){
            Text toAdd = new Text(Integer.toString(i));
            gridPane.add(toAdd,0,upperRight.y+2-i);
            GridPane.setHalignment(toAdd, HPos.CENTER);
        }
        Text xy = new Text("x\\y");
        gridPane.add(xy,0,0);
        GridPane.setHalignment(xy, HPos.CENTER);

        for(int i=lowerLeft.x ;i<=upperRight.x;i++){
            for(int j=lowerLeft.y ;j<=upperRight.y;j++){
                IMapElement el = (IMapElement) map.objectAt(new Vector2d(i,j));
                Text toAdd = new Text();
                toAdd.setText("\n   ");
                if(el!=null){
                    toAdd.setText(el.toString());
                }
                toAdd.setFont(Font.font ("arial", 24));
                gridPane.add(toAdd,i+1,upperRight.y+2-j);
                GridPane.setHalignment(toAdd, HPos.CENTER);
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

}
