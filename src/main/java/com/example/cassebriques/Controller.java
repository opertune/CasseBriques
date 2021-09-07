package com.example.cassebriques;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Rectangle rctJoueur1;
    public Circle crcPalet;
    public Button btnStart;
    public Label lblScore, lblWinLose;
    public AnchorPane anchorPane;
    public static int pScore;


    @FXML
    private void clickStartButton(){
        // Check if a game is in progress : false -> launch palet
        if(!Palet.inGame){
            lblWinLose.setText("");
            crcPalet.setCenterX(477); crcPalet.setCenterY(315);
            Palet palet = new Palet(rctJoueur1, crcPalet, lblWinLose, lblScore, anchorPane);
            palet.moveP();
            Palet.inGame = true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Briques firstGame = new Briques(anchorPane);
        firstGame.newBriques();
    }
}