package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Storage and synchronisation of all gamedata
 */
public class Model {
    /**
     * Position and size of paddleA (left side). This will always be the paddle the
     * local player controlls.
     */
    public Rectangle paddleA;
    /**
     * Position and size of paddleB (right side). This will always be the paddle the
     * remote player controlls.
     */
    public Rectangle paddleB;
    /**
     * Position and size of the ball. This will be edited by the Controller based on
     * {@link Model#hasAuthorityOverBall}, if not it is synchronized by the TODO:
     * NetworkController
     */
    public Rectangle ball;
    /**
     * local player score (local authority)
     */
    public int scoreA;
    /**
     * remote player score (server authority)
     */
    public int scoreB;
    /**
     * Wheter or not the ball position is currently updated by local or remote.
     * Whoever the ball is travelling towards has authority over it and collision
     * If the ball is travelling towards the left (player A), then the ball is
     * controlled locally.
     * If the ball is travelling towards the right (player B), then the ball is
     * controlled remotely.
     */
    public boolean hasAuthorityOverBall;
    /**
     * Speed and direction of the ball. This is to simulate the position of the ball
     */
    public Vector2 ballSpeed;
    /**
     * This tells the Model, View and MenuView if the menu or game should be
     * visible.
     * Speed of the paddle while moving
     */
    public float paddleSpeed = 50.0f;
    /**
     * This tells the Model, View and MenuView if the menu or game should be visible.
     */
    public boolean homeMenuVisible = false;


    /**
     * Initializes some default values for all parameters. TODO: generate (or fetch
     * if already exists)
     * a player ID to identify each player in games and on the scoreboard.
     */
    public Model() {
        paddleA = new Rectangle();
        paddleB = new Rectangle();
        ball = new Rectangle();
        initializeBallSpeed(); //This is useless when acting as Server
        paddleA.setSize(20, 100);
        paddleB.setSize(20, 100);
        paddleA.setPosition(0.0f, 600.0f / 2.0f - (paddleA.height / 2.0f));
        paddleB.setPosition(800.0f - (paddleB.width), 600.0f / 2.0f - (paddleB.height / 2.0f));
        ball.setSize(20, 20);
        ball.setPosition(400.0f, 300.0f);
    }

    /**
     * Initializes the speed and direction of the ball with a random angle.
     * The method calculates a random arc angle between -45 and 45 degrees,
     * then computes the x and y components of the ball's velocity based on
     * trigonometric functions. The speed is scaled by a predefined starting
     * speed value.
     *
     * The resulting velocity is stored in the `ballSpeed` vector.
     * Debugging information, including the initial arc and ball speed, is
     * printed to the console.
     */
    private void initializeBallSpeed() {
        int arc = (int) (Math.random() * 90 - 45);
        System.out.println("Initial arc: " + arc);

        float xSpeed = 0;
        if (Math.abs(arc) % 2 == 0) {
            xSpeed = -(float) Math.cos(arc * Math.PI / 180);
        } else {
            xSpeed = (float) Math.cos(arc * Math.PI / 180);
        }

        float startSpeed = 10;
        ballSpeed = new Vector2(xSpeed * startSpeed, (float) (Math.sin(arc * Math.PI / 180) * startSpeed));
        System.out.println("Initial ball speed: " + ballSpeed);
    }
}
