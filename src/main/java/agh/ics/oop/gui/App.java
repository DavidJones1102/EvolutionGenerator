package agh.ics.oop.gui;

import agh.ics.oop.Interfaces.IPositionChangeObserver;
import agh.ics.oop.MapElementsValues.Vector2d;
import agh.ics.oop.MapElements.Animal;
import agh.ics.oop.Interfaces.IMapElement;
import agh.ics.oop.Maps.Jungle;
import agh.ics.oop.Simulation.SimulationEngine;
import javafx.application.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class App extends Application implements IPositionChangeObserver {
    private Jungle map;
    private Stage stage;
    private SimulationEngine engine;
    private Thread simulationThread;
    final Button button = new Button("Start");

    private boolean buttonFlag = true;

    private Animal highlightedAnimal;

    GridPane rootPane = new GridPane();

    GridPane buttonPane;
    GridPane statsPane;
    GridPane animalStatsPane;
    Scene scene = new Scene(rootPane, 800, 800);
    GridPane mapGridPane;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        button.setAlignment(Pos.CENTER);
        stage = primaryStage;

        SettingsSetter settingsSetter = new SettingsSetter(800, 100);

        button.setOnAction(actionEvent -> {
            settingsSetter.getSettings();
            map = new Jungle(settingsSetter.settings, engine);
            engine = new SimulationEngine(map, settingsSetter.settings);
            engine.subscribeAll(this);
            simulationThread = new Thread(engine);
            this.simulationThread.start();
            buttonFlag = !buttonFlag;
            button.setText("Stop");
            stage.setScene(scene);
            drawMap();
            drawButton();
        });

        VBox buttonVBox = new VBox(button);
        buttonVBox.setAlignment(Pos.CENTER);
        buttonVBox.setPadding(new Insets(16));
        gridPane.add(settingsSetter, 1, 1);
        gridPane.add(buttonVBox, 1, 2);
        Scene scene = new Scene(gridPane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    private void drawButton() {
        rootPane.getChildren().remove(buttonPane);
        buttonPane = new GridPane();
        if (buttonFlag) {
            button.setOnAction(actionEvent -> {
                simulationThread = new Thread(engine);
                this.simulationThread.start();
                buttonFlag = !buttonFlag;
                button.setText("Stop");
                drawButton();
            });

        } else {
            button.setOnAction(actionEvent -> {
                simulationThread.interrupt();
                buttonFlag = !buttonFlag;
                button.setText("Start");
                drawButton();
            });
        }
        buttonPane.add(button, 0, 0);
        rootPane.add(buttonPane, 0, 1);
    }

    private void drawMap() {
        rootPane.getChildren().remove(mapGridPane);
        rootPane.getChildren().remove(statsPane);
        mapGridPane = new GridPane();
        statsPane = new GridPane();

        rootPane.add(mapGridPane, 0, 0);
        rootPane.add(statsPane, 0, 2);
        mapGridPane.setGridLinesVisible(true);

        int freeSpace = 0;

        Map<int[], Integer> genotypes = new HashMap<>();
        int[] mostPopularGenotype = {};
        int mostPopularGenotypeCounter = 0;


        Vector2d lowerLeft = map.calcLowerLeft();
        Vector2d upperRight = map.calcUpRight();
        for (int i = lowerLeft.x + 1; i <= upperRight.x + 1; i++) {
            mapGridPane.getColumnConstraints().add(new ColumnConstraints(50));
            Text toAdd = new Text(Integer.toString(i - 1));
            mapGridPane.add(toAdd, i, 0);
            GridPane.setHalignment(toAdd, HPos.CENTER);
        }
        for (int i = lowerLeft.y; i <= upperRight.y; i++) {
            mapGridPane.getRowConstraints().add(new RowConstraints(50));
            Text toAdd = new Text(Integer.toString(i));
            mapGridPane.add(toAdd, 0, upperRight.y + 1 - i);
            GridPane.setHalignment(toAdd, HPos.CENTER);
        }
        mapGridPane.getColumnConstraints().add(new ColumnConstraints(50));
        mapGridPane.getRowConstraints().add(new RowConstraints(50));
        Text xy = new Text("y\\x");
        mapGridPane.add(xy, 0, 0);
        GridPane.setHalignment(xy, HPos.CENTER);

        for (int i = lowerLeft.x; i <= upperRight.x; i++) {
            for (int j = lowerLeft.y; j <= upperRight.y; j++) {
                IMapElement el = (IMapElement) map.objectAt(new Vector2d(i, j));
                VBox vbox;
                if (el != null) {
                    vbox = new VBox(new GuiElementBox(el).getVbox());
                    if (el instanceof Animal) {
                        vbox.setOnMouseClicked((EventHandler<Event>) event -> {
                            changeHighligtedAnima((Animal) el);
                            drawMap();
                            displaySingleAnimalStats();
                        });
                    }
                    mapGridPane.add(vbox, i + 1, upperRight.y + 1 - j);
                } else {
                    freeSpace++;
                }

                if (el instanceof Animal) {
                    int[] genotype = ((Animal) el).getGenotype().getGenes();
                    Integer counter = genotypes.get(genotype);

                    genotypes.merge(genotype, 1, Integer::sum);

                    if (counter != null && counter > mostPopularGenotypeCounter) {
                        mostPopularGenotype = genotype;
                        mostPopularGenotypeCounter = counter + 1;
                    } else if (mostPopularGenotypeCounter == 0 && counter == null) {
                        mostPopularGenotype = genotype;
                        mostPopularGenotypeCounter = 1;
                    }
                }
            }
        }

        displayMapStats(freeSpace, mostPopularGenotype);

        if (highlightedAnimal != null) {
            displaySingleAnimalStats();
        }
    }

    private void displayMapStats(int freeSpace, int[] mostPopularGenotype) {
        float[] stats = map.getAnimalsStats();
        Label animalCount = new Label("Animals on map: " + stats[0]);
        statsPane.add(animalCount, 0, 0);

        Label grassCount = new Label("Grass on map: " + map.getGrassCount());
        statsPane.add(grassCount, 0, 1);
//
        Label freeSpaceLabel = new Label("Free space: " + freeSpace);
        statsPane.add(freeSpaceLabel, 0, 2);
//
        Label mostPopularGene = new Label("Most popular genotype: " + intArrayToString(mostPopularGenotype));
        statsPane.add(mostPopularGene, 0, 3);
//
        Label avgEnergy = new Label("Average energy: " + stats[1]);
        statsPane.add(avgEnergy, 0, 4);
//
        Label avgAge = new Label("Average dead animal age: " + map.getDeadAnimalsAvgAge());
        statsPane.add(avgAge, 0, 5);
    }

    private void displaySingleAnimalStats() {
        rootPane.getChildren().remove(animalStatsPane);
        animalStatsPane = new GridPane();

        Label animalCount = new Label("Animal stats: ");
        animalStatsPane.add(animalCount, 0, 0);

        Label genome = new Label("genome: " + intArrayToString(highlightedAnimal.getGenotype().getGenes()));
        animalStatsPane.add(genome, 0, 1);

        Label currGeneNr = new Label("Current gene number: " + highlightedAnimal.getGenotype().getCurrentGeneNr());
        animalStatsPane.add(currGeneNr, 0, 2);

        Label grassEaten = new Label("grass eaten: " + highlightedAnimal.getGrassEaten());
        animalStatsPane.add(grassEaten, 0, 3);

        Label energy = new Label("energy: " + highlightedAnimal.getEnergy());
        animalStatsPane.add(energy, 0, 4);

        Label children = new Label("children: " + highlightedAnimal.getChildrenCount());
        animalStatsPane.add(children, 0, 5);

        Label age = new Label("age: " + highlightedAnimal.getAge());
        animalStatsPane.add(age, 0, 6);

        rootPane.add(animalStatsPane, 1, 2);
    }

    private void changeHighligtedAnima(Animal animal) {
        if (highlightedAnimal != null) {
            highlightedAnimal.setHighlight(false);
        }
        animal.setHighlight(true);
        highlightedAnimal = animal;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        Platform.runLater(() -> drawMap());
    }

    private String intArrayToString(int[] arr) {
        String arrayString = "";
        for (int element : arr) {
            arrayString += element + " ";
        }
        return arrayString;
    }
}
