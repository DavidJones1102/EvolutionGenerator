package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.*;
import javafx.geometry.HPos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class App extends Application implements IPositionChangeObserver {
    private AbstractWorldMap map;
    private Stage stage;
    private SimulationEngine engine;
    private Thread simulationThread;
    private Button startButton = new Button("Start");
    private GridPane gridPane = new GridPane();
    private TextField textField = new TextField ();
    @Override
    public void init() {
        map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        engine = new SimulationEngine(map, positions);
        engine.subscribeAll(this);
        simulationThread = new Thread(engine);

        VBox vBox = new VBox(new Label("Direction:"),  textField);
        gridPane.add(startButton,1,1);
        gridPane.add(vBox,2,1);
    }

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;

        startButton.setOnAction( actionEvent->{
            MoveDirection[] directions = new OptionsParser().parse(textField.getText().split(" "));
            engine.setDirection(directions);
            this.simulationThread.start();
        });

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void draw(Stage stage){
        GridPane gridPane = new GridPane();
//        gridPane.getColumnConstraints().add(new ColumnConstraints(500));
//        gridPane.getRowConstraints().add(new RowConstraints(500));
        gridPane.setGridLinesVisible(true);
        Vector2d lowerLeft = map.calcLowerLeft();
        Vector2d upperRight = map.calcUpRight();
        for(int i=lowerLeft.x+1 ;i<=upperRight.x+1;i++){
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
                VBox vbox;
                toAdd.setText("\n   ");
                if(el==null){
                    vbox = new VBox(toAdd);
                    toAdd.setFont(Font.font ("arial", 24));
                }
                else {
                    vbox = new VBox( new GuiElementBox(el).getVbox() );
                }

                gridPane.add(vbox,i+1,upperRight.y+2-j);
                //GridPane.setHalignment(toAdd, HPos.CENTER);
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater( ()->draw(stage) );
    }
}
