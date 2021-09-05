package com.example.cassebriques;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Palet {
    // Members
    private Rectangle _rctJoueur1;
    private Circle _crcPalet;
    private Label _lblWinLose, _lblScore;
    public static boolean inGame = false;

    // Constructor
    Palet(Rectangle rctJoueur1, Circle crcPalet, Label lblWinLose, Label lblScore){
        this._rctJoueur1 = rctJoueur1;
        this._crcPalet = crcPalet;
        this._lblWinLose = lblWinLose;
        this._lblScore = lblScore;
    }

    // Methods
    // Palet starting pos
    private static final double BOTTOM_LIMIT = 530;
    private static final double INITIAL_VX = 0;
    private static final double INITIAL_VY = 1;
    private static final double SPEED_LIMITE = 30;
    private static final double ACCELERATION = 1.1;

    private static double angle = Math.atan2(INITIAL_VY, INITIAL_VX);
    private static double magnitude = Math.sqrt(5);

    private static double deltaX = magnitude * Math.cos(angle);
    private static double deltaY = magnitude * Math.sin(angle);

    public void moveP(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // Check collision between player and palet
                if(_rctJoueur1.getBoundsInParent().intersects(_crcPalet.getBoundsInParent())){
                    // increased palet speed
                    //magnitude *= (magnitude < SPEED_LIMITE) ? ACCELERATION : 1;

                    // more the palet are close to the center less the angle are wide
                    angle = (Math.PI / 4) * -Math.abs((_rctJoueur1.getX() + 75 - _crcPalet.getCenterX() - 12) / 75);
                    deltaX = Math.abs(magnitude * Math.cos(angle));

                    // value depends on the place or hits the palet : left or right side
                    //deltaY = /*Si crcPalet touche le côté gauche alors*/(_crcPalet.getCenterY() <= /*left side*/_rctJoueur1.getX() + 75 ? Math.abs(magnitude * Math.sin(angle)) : -Math.abs(magnitude * Math.sin(angle)));
                    if(_crcPalet.getCenterY() <= _rctJoueur1.getX() + 75){ // LEFT
                        deltaY = -Math.abs(magnitude * Math.sin(angle));
                    }else if(_crcPalet.getCenterY() >= _rctJoueur1.getX() - 75){ // RIGHT
                        deltaY = -Math.abs(magnitude * Math.sin(angle));
                    }

                    // Check collision with left frame
                }else if(_crcPalet.getCenterX() < 13){
                    deltaX = -deltaX;

                    // Check collision with right frame
                }else if(_crcPalet.getCenterX() > 941){
                    deltaX = -deltaX;

                    // Check collision with top frame
                }else if(_crcPalet.getCenterY() < 13){
                    deltaY = -deltaY;
                }

                // if palet is out of box bottom side
                if(_crcPalet.getCenterY() > BOTTOM_LIMIT) {
                    outOfGrid("Lose !");

                    // Stop AnimationTimer loop
                    stop();
                }

                // Set new position
                _crcPalet.setCenterX(_crcPalet.getCenterX() + deltaX);
                _crcPalet.setCenterY(_crcPalet.getCenterY() + deltaY);
            }
        };
        timer.start();
    }

    // Palet out of the grid (left and right side)
    void outOfGrid(String txt){
        _lblWinLose.setText(txt);
        _lblWinLose.setTextFill(Color.RED);

        // Increase player score
        Controller.pScore++;
        _lblScore.setText("Score : " + Controller.pScore);
        inGame = false;

        // Reset magnitude and delta
        magnitude = Math.sqrt(3);
        deltaX = magnitude * Math.cos(Math.atan2(1, 0));
        deltaY = magnitude * Math.sin(Math.atan2(1, 0));
    }
}
