package agh.ics.oop.gui;

import agh.ics.oop.Interfaces.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private Image image;
    private ImageView imageView;
    private Label label;
    private VBox vbox;
    public GuiElementBox(IMapElement element){
        try {
            image = new Image(new FileInputStream(element.getImage()));
            imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            label = new Label(element.getLabel());
            vbox = new VBox(imageView,label);
            vbox.setAlignment(Pos.CENTER);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public VBox getVbox() {
        return vbox;
    }

}
