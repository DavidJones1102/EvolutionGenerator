package agh.ics.oop.gui;

import agh.ics.oop.MapElementsValues.Settings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;


public class SettingsSetter extends VBox {

    public Settings settings = new Settings();
    private NumberTextField heightInput;
    private NumberTextField widthInput;
    private NumberTextField startingGrassAmountInput;
    private NumberTextField energyFromGrassInput;
    private NumberTextField dailyGrassAmountInput;
    private NumberTextField startingEnergyInput;
    private NumberTextField energyNeededToCopulationInput;
    private NumberTextField minMutationInput;
    private NumberTextField maxMutationInput;
    private NumberTextField genomeSizeInput;
    private NumberTextField startingAnimalsNumberInput;
    private NumberTextField energyTakenDuringCopulationInput;
    public boolean showMapVariant = false;
    public boolean showMutationVariant = false;
    public boolean showBehaviorVariant = false;

    public SettingsSetter(double prefWidth, double prefHeight) {
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

        Label energyTakenDuringCopulationLabel = new Label("energyTakenDuringCopulation");
        energyTakenDuringCopulationInput = new NumberTextField();
        VBox energyTakenDuringCopulation = new VBox(energyTakenDuringCopulationLabel,energyTakenDuringCopulationInput);
        getChildren().add(energyTakenDuringCopulation);

        Label minMutationLabel = new Label("minMutation");
        minMutationInput = new NumberTextField();
        VBox minMutation = new VBox(minMutationLabel,minMutationInput);
        getChildren().add(minMutation);


        Label maxMutationLabel = new Label("maxMutation");
        maxMutationInput = new NumberTextField();
        VBox maxMutation = new VBox(maxMutationLabel,maxMutationInput);
        getChildren().add(maxMutation);

        Label startingAnimalsNumberLabel = new Label("startingAnimalsNumber");
        startingAnimalsNumberInput = new NumberTextField();
        VBox startingAnimalsNumber = new VBox(startingAnimalsNumberLabel,startingAnimalsNumberInput);
        getChildren().add(startingAnimalsNumber);

        Button mapVariantButton = new Button();
        mapVariantButton.setText(!showMapVariant ? "Earth" : "--");
        mapVariantButton.setOnAction(event -> {
            showMapVariant = !showMapVariant;
            mapVariantButton.setText(!showMapVariant ? "Earth" : "Hell");
        });
        getChildren().add(mapVariantButton);

        Button mutationVariantButton = new Button();
        mutationVariantButton.setText(!showMutationVariant ? "Random" : "Adjustment mutation");
        mutationVariantButton.setOnAction(event -> {
            showMutationVariant = !showMutationVariant;
            mutationVariantButton.setText(!showMutationVariant ? "Random mutation" : "Adjustment mutation");
        });
        getChildren().add(mutationVariantButton);

        Button behaviorVariantButton = new Button();
        behaviorVariantButton.setText(!showBehaviorVariant ? "Classic behavior" : "Crazy behavior");
        behaviorVariantButton.setOnAction(event -> {
            showBehaviorVariant = !showBehaviorVariant;
            behaviorVariantButton.setText(!showBehaviorVariant ? "Classic behavior" : "Crazy behavior");
        });
        getChildren().add(behaviorVariantButton);

        Button recommendedSettingsButton = new Button();
        recommendedSettingsButton.setText("Recommended settings");
        recommendedSettingsButton.setOnAction(event -> {
            recommendedSettings();
        });
        getChildren().add(recommendedSettingsButton);

        recommendedSettings();

    }

    private void recommendedSettings(){
        heightInput.setNumber(new BigDecimal(11));
        widthInput.setNumber(new BigDecimal(11));
        startingGrassAmountInput.setNumber(new BigDecimal(10));
        energyFromGrassInput.setNumber(new BigDecimal(7));
        dailyGrassAmountInput.setNumber(new BigDecimal(8));
        startingEnergyInput.setNumber(new BigDecimal(10));
        minMutationInput.setNumber(new BigDecimal(1));
        genomeSizeInput.setNumber(new BigDecimal(10));
        startingAnimalsNumberInput.setNumber(new BigDecimal(10));
        energyTakenDuringCopulationInput.setNumber(new BigDecimal(5));
        energyNeededToCopulationInput.setNumber(new BigDecimal(10));
        maxMutationInput.setNumber(new BigDecimal(2));
    }
    public void getSettings(){
        settings.mapHeight = heightInput.getNumber().intValue();
        settings.mapWidth = widthInput.getNumber().intValue();
        settings.startingGrassAmount = startingGrassAmountInput.getNumber().intValue();//
        settings.energyFromGrass = energyFromGrassInput.getNumber().intValue();//
        settings.dailyGrassAmount = dailyGrassAmountInput.getNumber().intValue();//
        settings.startingEnergy = startingEnergyInput.getNumber().intValue();//
        settings.energyNeededToCopulation = energyNeededToCopulationInput.getNumber().intValue();//
        settings.minMutation = minMutationInput.getNumber().intValue();//
        settings.maxMutation = maxMutationInput.getNumber().intValue();//
        settings.genomeSize = genomeSizeInput.getNumber().intValue();//
        settings.startingAnimalsNumber = startingAnimalsNumberInput.getNumber().intValue();
        settings.energyTakenDuringCopulation = energyTakenDuringCopulationInput.getNumber().intValue();
        settings.behaviorVariant = showBehaviorVariant;
        settings.mutationVariant = showMutationVariant;
        settings.mapVariant = showMapVariant;
    }
}
