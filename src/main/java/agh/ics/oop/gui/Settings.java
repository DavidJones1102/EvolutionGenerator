package agh.ics.oop.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Settings extends VBox {


    public int mapHeight;//
    private NumberTextField heightInput;
    public int mapWidth;//
    private NumberTextField widthInput;
    public int startingGrassAmount;//
    private NumberTextField startingGrassAmountInput;
    public int energyFromGrass;//
    private NumberTextField energyFromGrassInput;
    public int dailyGrassAmount;//
    private NumberTextField dailyGrassAmountInput;
    public int startingEnergy;//
    private NumberTextField startingEnergyInput;
    public int energyNeededToCopulation;//
    private NumberTextField energyNeededToCopulationInput;
    public int minMutation;//
    private NumberTextField minMutationInput;
    public int maxMutation;//
    private NumberTextField maxMutationInput;
    public int genomeSize;//
    private NumberTextField genomeSizeInput;
    public boolean mutationVariant;
    public boolean behaviorVariant;
    public boolean mapVariant;
    public boolean showMapVariant = false;
    public boolean showMutationVariant = false;
    public boolean showBehaviorVariant = false;

    public Settings( double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);

        this.setAlignment(Pos.CENTER);

        Label widthLabel = new Label("width");
        widthInput = new NumberTextField();
        VBox width = new VBox(widthLabel,widthInput);
        getChildren().add(width);

        Label heightLabel = new Label("height");
        heightInput = new NumberTextField();
        VBox height = new VBox(heightLabel,heightInput);
        getChildren().add(height);

        Label dailyGrassAmountLabel = new Label("dailyGrassAmount");
        dailyGrassAmountInput = new NumberTextField();
        VBox dailyGrassAmount = new VBox(dailyGrassAmountLabel,dailyGrassAmountInput);
        getChildren().add(dailyGrassAmount);

        Label startingGrassAmountLabel = new Label("startingGrassAmount");
        startingGrassAmountInput = new NumberTextField();
        VBox startingGrassAmount = new VBox(startingGrassAmountLabel,startingGrassAmountInput);
        getChildren().add(startingGrassAmount);

        Label startingEnergyLabel = new Label("startingEnergy");
        startingEnergyInput = new NumberTextField();
        VBox startingEnergy = new VBox(startingEnergyLabel,startingEnergyInput);
        getChildren().add(startingEnergy);

        Label energyFromGrassLabel = new Label("energyFromGrass");
        energyFromGrassInput = new NumberTextField();
        VBox energyFromGrass = new VBox(energyFromGrassLabel,energyFromGrassInput);
        getChildren().add(energyFromGrass);

        Label genomeSizeLabel = new Label("genomeSize");
        genomeSizeInput = new NumberTextField();
        VBox genomeSize = new VBox(genomeSizeLabel,genomeSizeInput);
        getChildren().add(genomeSize);

        Label energyNeededToCopulationLabel = new Label("energyNeededToCopulation");
        energyNeededToCopulationInput = new NumberTextField();
        VBox energyNeededToCopulation = new VBox(energyNeededToCopulationLabel,energyNeededToCopulationInput);
        getChildren().add(energyNeededToCopulation);

        Label minMutationLabel = new Label("minMutation");
        minMutationInput = new NumberTextField();
        VBox minMutation = new VBox(minMutationLabel,minMutationInput);
        getChildren().add(minMutation);


        Label maxMutationLabel = new Label("maxMutation");
        maxMutationInput = new NumberTextField();
        VBox maxMutation = new VBox(maxMutationLabel,maxMutationInput);
        getChildren().add(maxMutation);

        Button mapVariantButton = new Button();
        mapVariantButton.setText(!showMapVariant ? "Jungle" : "--");
        mapVariantButton.setOnAction(event -> {
            showMapVariant = !showMapVariant;
            mapVariantButton.setText(!showMapVariant ? "Earth" : "Hell");
        });
        getChildren().add(mapVariantButton);

        Button mutationVariantButton = new Button();
        mutationVariantButton.setText(!showMutationVariant ? "Random" : "Adjustment");
        mutationVariantButton.setOnAction(event -> {
            showMutationVariant = !showMutationVariant;
            mutationVariantButton.setText(!showMutationVariant ? "Random" : "Adjustment");
        });
        getChildren().add(mutationVariantButton);

        Button behaviorVariantButton = new Button();
        behaviorVariantButton.setText(!showBehaviorVariant ? "Classic" : "Crazy");
        behaviorVariantButton.setOnAction(event -> {
            showBehaviorVariant = !showBehaviorVariant;
            behaviorVariantButton.setText(!showBehaviorVariant ? "Classic" : "Crazy");
        });
        getChildren().add(behaviorVariantButton);

    }

    public void getSettings(){
        mapHeight = heightInput.getNumber().intValue();
        mapWidth = widthInput.getNumber().intValue();

        startingGrassAmount = startingGrassAmountInput.getNumber().intValue();//
        energyFromGrass = energyFromGrassInput.getNumber().intValue();//
        dailyGrassAmount = dailyGrassAmountInput.getNumber().intValue();//
        startingEnergy = startingEnergyInput.getNumber().intValue();//
        energyNeededToCopulation = energyNeededToCopulationInput.getNumber().intValue();//
        minMutation = minMutationInput.getNumber().intValue();//
        maxMutation = maxMutationInput.getNumber().intValue();//
        genomeSize = genomeSizeInput.getNumber().intValue();//
    }
}
