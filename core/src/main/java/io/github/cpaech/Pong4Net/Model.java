package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Storage and synchronisation of all gamedata
 */
public class Model {
    /**
     * Position and size of paddleA (left side). This will always be the paddle the local player controlls.
     */
    public Rectangle paddleA;
    /**
     * Position and size of paddleB (right side). This will always be the paddle the remote player controlls.
     */
    public Rectangle paddleB;
    /**
     * Position and size of the ball. This will be edited by the Controller based on {@link Model#hasAuthorityOverBall}, if not it is synchronized by the TODO: NetworkController
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
     * If the ball is travelling towards the left (player A), then the ball is controlled locally.
     * If the ball is travelling towards the right (player B), then the ball is controlled remotely.
     */
    public boolean hasAuthorityOverBall;
    /**
     * Speed and direction of the ball. This is to simulate the position of the ball
     */
    public Vector2 ballSpeed;
    public float paddleSpeed = 50.0f;
    /**
     * This tells the Model, View and MenuView if the menu or game should be visible.
     */
    public boolean homeMenuVisible = false;
    /**
     * Initializes some default values for all parameters. TODO: generate (or fetch if already exists)
     * a player ID to identify each player in games and on the scoreboard.
     */
    public Model()
    {
        paddleA = new Rectangle();
        paddleB = new Rectangle();
        ball = new Rectangle();
        ballSpeed = new Vector2(1.0f, 1.0f);

        paddleA.setSize(20, 150);
        paddleB.setSize(20, 150);
        paddleA.setPosition(0.0f, 600.0f/2.0f - (paddleA.height/2.0f));
        paddleB.setPosition(800.0f - (paddleB.width), 600.0f/2.0f - (paddleB.height/2.0f));
        
        ball.setSize(20, 20);
        ball.setPosition(400.0f, 300.0f);
    }
}
