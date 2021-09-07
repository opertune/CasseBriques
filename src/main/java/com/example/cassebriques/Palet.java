package com.example.cassebriques;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Palet {
    // Members
    private Rectangle _rctJoueur1;
    private Circle _crcPalet;
    private Label _lblWinLose, _lblScore;
    private AnchorPane _anchorePane;
    public static boolean inGame = false;

    // Constructor
    Palet(Rectangle rctJoueur1, Circle crcPalet, Label lblWinLose, Label lblScore, AnchorPane anchorPane){
        this._rctJoueur1 = rctJoueur1;
        this._crcPalet = crcPalet;
        this._lblWinLose = lblWinLose;
        this._lblScore = lblScore;
        this._anchorePane = anchorPane;
    }

    // Methods
    // Palet starting pos
    private static final double BOTTOM_LIMIT = 530;
    private static final double INITIAL_VX = -0.5;
    private static final double INITIAL_VY = 1;


    private static Point2D v = new Point2D(INITIAL_VX, INITIAL_VY);

    public void moveP(){
        v = v.multiply(5);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // Check collision between player and palet
                if(_rctJoueur1.getBoundsInParent().intersects(_crcPalet.getBoundsInParent())){
                    v = new Point2D(v.getX(), -v.getY());

                // Collision with left side
                }else if(_crcPalet.getCenterX() < 13){
                    v = new Point2D(-v.getX(), v.getY());

                    // Check collision with right side
                }else if(_crcPalet.getCenterX() > 941){
                    v = new Point2D(-v.getX(), v.getY());

                    // Check collision with top side
                }else if(_crcPalet.getCenterY() < 13){
                    v = new Point2D(v.getX(), -v.getY());
                }

                // if palet is out of box bottom side
                if(_crcPalet.getCenterY() > BOTTOM_LIMIT) {
                    outOfGrid();
                    // Stop AnimationTimer loop
                    stop();
                }

                // Collision between palet and briques
                collision();
                if(Controller.pScore == 96){
                    _lblWinLose.setText("WIN !");
                    _lblWinLose.setTextFill(Color.GREEN);
                    stop();
                }

                // Set new position
                _crcPalet.setCenterX(_crcPalet.getCenterX() + v.getX());
                _crcPalet.setCenterY(_crcPalet.getCenterY() + v.getY());
            }
        };
        timer.start();
    }

    // Palet out of the grid (left and right side)
    void outOfGrid(){
        // set color and lose label
        _lblWinLose.setText("LOSE !");
        _lblWinLose.setTextFill(Color.RED);
        inGame = false;

        // Reset all briques
        _anchorePane.getChildren().removeAll(Briques.briquesList);
        Briques.briquesList.removeAll(Briques.briquesList);

        Briques firstGame = new Briques(_anchorePane);
        firstGame.newBriques();

        // Reset palet position
        v = new Point2D(INITIAL_VX, INITIAL_VY);
    }

    void collision(){
        for(int i = 0; i < Briques.briquesList.size(); i++){
            if(Briques.briquesList.get(i).getBoundsInParent().intersects(_crcPalet.getBoundsInParent())){
                // Delete brique
                _anchorePane.getChildren().remove(Briques.briquesList.get(i));
                Briques.briquesList.remove(i);

                // Increase score
                Controller.pScore++;
                _lblScore.setText("Score : " + Controller.pScore);

                // set new pos
                v = new Point2D(v.getX(), -v.getY());
            }
        }
    }
}
