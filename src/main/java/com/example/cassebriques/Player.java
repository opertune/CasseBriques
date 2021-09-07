package com.example.cassebriques;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Player {
    // Members
    private Scene _scene;
    private Controller _controller;

    // Constructor
    Player(Scene scene, Controller controller){
        this._scene = scene;
        this._controller = controller;
    }

    // Methods
    // Rectangles y Pos
    private double p1xOfs = 427;

    // Players vitesse
    private double speed = 0;
    public void move(){
        // On key press (W,S for player 1 and UP, DOWN for player 2) add speed to player rectangle
        _scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case A: speed = -10; break;
                    case D: speed = 10; break;
                }
            }
        });

        // On key release remove speed to player rectangle
        _scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                speed = 0;
            }
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // add speed to pos player
                p1xOfs+=speed;

                // Player 1 box limit
                if(p1xOfs < 0){
                    p1xOfs = 0;
                }else if(p1xOfs > 851){
                    p1xOfs = 851;
                }

                // Increase or decrease rectangle Y position
                _controller.rctJoueur1.setX(p1xOfs);
            }
        };
        // Launch animation
        animationTimer.start();
    }
}
