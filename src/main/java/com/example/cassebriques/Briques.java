package com.example.cassebriques;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Briques {
    // Members
    private AnchorPane _anchorPane;
    public static ObservableList<Rectangle> briquesList = FXCollections.observableArrayList();

    // Constructor
    Briques(AnchorPane anchorPane){
        this._anchorPane = anchorPane;
    }

    // Methods
    void newBriques(){
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 16 ;j++){
                Rectangle brique = new Rectangle();
                brique.setWidth(53); brique.setHeight(24);
                brique.setFill(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
                brique.setLayoutX(j*60); brique.setLayoutY(i * 40);
                _anchorPane.getChildren().add(brique);
                briquesList.add(brique);
            }
        }
    }
}
