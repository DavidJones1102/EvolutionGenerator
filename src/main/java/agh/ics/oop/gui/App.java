package agh.ics.oop.gui;

import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.MapElements.Grass;
import agh.ics.oop.MapElementsValues.Vector2d;
import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.Interfaces.IMapElement;
import agh.ics.oop.Maps.AbstractWorldMap;
import agh.ics.oop.Maps.Jungle;
import agh.ics.oop.Simulation.SimulationEngine;
import javafx.application.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;


public class App extends Application implements IPositionChangeObserver {
    private AbstractWorldMap map;
    private Stage stage;
    private SimulationEngine engine;
    private Thread simulationThread;
    private Button button = new Button("Start");

    private boolean buttonFlag = true;
    private GridPane gridPane = new GridPane();
    private TextField textField = new TextField ();

    @Override
    public void init() {
    }

    @Override
    public void start(Stage primaryStage){
        GridPane gridPane = new GridPane();
        button.setAlignment(Pos.CENTER);
        stage = primaryStage;

        SettingsSetter settingsSetter = new SettingsSetter(800, 100 );

        button.setOnAction( actionEvent->{
            settingsSetter.getSettings();
            map = new Jungle(settingsSetter.settings, engine);
            engine = new SimulationEngine(map, settingsSetter.settings);
            engine.subscribeAll(this);
            simulationThread = new Thread(engine);
            this.simulationThread.start();
            buttonFlag = !buttonFlag;
            button.setText("Stop");
            draw(stage);
        });

        VBox buttonVBox = new VBox(button);
        buttonVBox.setAlignment(Pos.CENTER);
        buttonVBox.setPadding(new Insets(16));
        gridPane.add(settingsSetter,1,1);
        gridPane.add(buttonVBox,1,2);
        Scene scene = new Scene(gridPane, 800, 800);
        stage.setScene(scene);
        stage.show();

        //draw(stage);
    }
    private void draw(Stage stage){
        GridPane rootPane = new GridPane();
        GridPane gridPane = new GridPane();
        GridPane buttonPane = new GridPane();
        GridPane statsPane = new GridPane();
        int freeSpace = 0;

        Map<int[], Integer> genotypes = new HashMap<>();
        int[] mostPopularGenotype={};
        int mostPopularGenotypeCounter=0;

        if(buttonFlag){
            button.setOnAction( actionEvent->{
                simulationThread = new Thread(engine);
                this.simulationThread.start();
                buttonFlag = !buttonFlag;
                button.setText("Stop");
                draw(stage);
            });

        }
        else {
            button.setOnAction( actionEvent->{
                simulationThread.interrupt();
                buttonFlag = !buttonFlag;
                button.setText("Start");

                draw(stage);
            });
        }
        buttonPane.add(button,0,0);

        rootPane.add(gridPane,0,0);
        rootPane.add(buttonPane,0,1);
        rootPane.add(statsPane,0,2);

        gridPane.setGridLinesVisible(true);
        Vector2d lowerLeft = map.calcLowerLeft();
        Vector2d upperRight = map.calcUpRight();
        for(int i=lowerLeft.x+1 ;i<=upperRight.x+1;i++){
            gridPane.getColumnConstraints().add(new ColumnConstraints(50));
            Text toAdd = new Text(Integer.toString(i-1));
            gridPane.add(toAdd,i,0);
            GridPane.setHalignment(toAdd, HPos.CENTER);
        }
        for(int i=lowerLeft.y ;i<=upperRight.y;i++){
            gridPane.getRowConstraints().add(new RowConstraints(50));
            Text toAdd = new Text(Integer.toString(i));
            gridPane.add(toAdd,0,upperRight.y+1-i);
            GridPane.setHalignment(toAdd, HPos.CENTER);
        }
        gridPane.getColumnConstraints().add(new ColumnConstraints(50));
        gridPane.getRowConstraints().add(new RowConstraints(50));
        Text xy = new Text("y\\x");
        gridPane.add(xy,0,0);
        GridPane.setHalignment(xy, HPos.CENTER);

        for(int i=lowerLeft.x ;i<=upperRight.x;i++){
            for(int j=lowerLeft.y ;j<=upperRight.y;j++){
                IMapElement el = (IMapElement) map.objectAt(new Vector2d(i,j));
                Text toAdd = new Text();
                VBox vbox;
                if(el!=null){
                    vbox = new VBox( new GuiElementBox(el).getVbox() );
                    gridPane.add(vbox,i+1,upperRight.y+1-j);
                }
                else{
                    freeSpace++;
                }

                if(el instanceof Animal){
                    int[]genotype =((Animal) el).getGenotype().getGenes();
                    Integer counter = genotypes.get(genotype);
                    if(counter==null){
                        genotypes.put(genotype,1);
                    }
                    else {
                        genotypes.put(genotype,counter+1);
                    }
                    if(counter!= null && counter>mostPopularGenotypeCounter){
                        mostPopularGenotype = genotype;
                        mostPopularGenotypeCounter=counter+1;
                    }
                    else if(mostPopularGenotypeCounter==0 && counter == null){
                        mostPopularGenotype = genotype;
                        mostPopularGenotypeCounter=1;
                    }
                }

            }
        }
        float [] stats = map.getAnimalsStats();
        Label animalCount = new Label("Animals on map: "+stats[0]);
        statsPane.add(animalCount,0,0);

        Label grassCount = new Label("Grass on map: " + map.getGrassCount());
        statsPane.add(grassCount,0,1);
//
        Label freeSpaceLabel = new Label("Free space: " + freeSpace);
        statsPane.add(freeSpaceLabel,0,2);
//
        Label mostPopularGene = new Label("Most popular genotype: "+ intArrayToString(mostPopularGenotype));
        statsPane.add(mostPopularGene,0,3);
//
        Label avgEnergy= new Label("Average energy: "+stats[1]);
        statsPane.add(avgEnergy,0,4);
//
        Label avgAge = new Label("Average dead animal age: "+map.getDeadAnimalsAvgAge());
        statsPane.add(avgAge,0,5);


        Scene scene = new Scene(rootPane, 800, 800);
        stage.setScene(scene);
        stage.show();


    }
    private String intArrayToString(int[] arr){
        String arrayString = "";
        for (int element: arr) {
            arrayString+=element+" ";
        }
        return arrayString;
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        Platform.runLater( ()->draw(stage) );
    }

}
