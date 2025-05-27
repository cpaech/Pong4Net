package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.GdxRuntimeException;

import io.github.cpaech.Pong4Net.Messages.MessageTest;

/**
 * This takes care of all the menu, game, physics and lobby creation logic. It does not take care of Input gathering and network synchronisation.
 * It extends ChangeListener in order to react to button presses by the view
 */
public class Controller extends ChangeListener {
    
    private MenuView mvcMenuView;
    /**
     * Reference to the Model provided for Model-View-Controller
     */
    private Model mvcModel;
    /**
     * This is the socket that will be used to connect to the server
     */
    public Socket socket;

    /**
     * This method sets up all necessary objects for the controller.
     * @param mvcModel {@link Model} passed by the program entry in {@link Main}
     */
    public Controller(Model mvcModel, MenuView mvcMenuView) 
    {
        this.mvcMenuView =mvcMenuView;
        this.mvcModel = mvcModel;
    }

    public void connectToServer(){
        SocketHints socketHints = new SocketHints();
        // Socket will time out in 4 seconds
        socketHints.connectTimeout = 4000;
        try {
            socket = Gdx.net.newClientSocket(Protocol.TCP, AppPreferences.getAppPreferences().getServerHost(), AppPreferences.getAppPreferences().getServerPort(), socketHints);
        } catch (GdxRuntimeException e) {
            System.out.println("Server not found");
            e.printStackTrace();
            socket = null;
            mvcModel.homeMenuVisible = true;
        }
    }

    /**
     * This method is called once per frame. It is responsible for updating the game state.
     * And updating the server logic.
     */
    public void listenForServer(){
        if (socket.isConnected() == false)
        {
            System.out.println("Server disconnected");  
            socket = null;
            mvcModel.homeMenuVisible = true;
        }
        if (socket != null) {
            SocketHints sHints = new SocketHints();
            sHints.connectTimeout = 100;
            MessageTest msg = new MessageTest("Test123");
            msg.Send(socket.getOutputStream());
        }
    }

    /**
     * This method gets called whenever something happens (eg. buttonclick) in the view.
     */

    @Override
    public void changed (ChangeEvent event, Actor actor) {
        System.out.println("Button Pressed: " + actor.getName());

        if(actor.getName().equals("StartGameButton")) {
            if(mvcModel.player1Name == null){mvcMenuView.errorField.setText("Please enter a name first!");}
            else{
                connectToServer();
                mvcModel.homeMenuVisible = false;
            }
        }
        if(actor.getName().equals("SoloPlayButton")) {
            if(mvcModel.player1Name == null){mvcMenuView.errorField.setText("Please enter a name first!");}
            else{
                mvcModel.homeMenuVisible = false;
            }
        }
        if(actor.getName().equals("LoginButton")) {
            mvcModel.player1Name = mvcMenuView.usernameField.getText();
            mvcMenuView.errorField.setText("Logged in as: " + mvcModel.player1Name);
            System.out.println("Player name set to: " + mvcModel.player1Name);
        }
    }

    
    /**
     * Updates the position of the ball by adding the current ball speed
     * to its x and y coordinates. This method is responsible for moving
     * the ball in the game based on its velocity.
     */

    public void moveBall() {
        mvcModel.ball.setX(mvcModel.ball.x + mvcModel.ballSpeed.x);
        mvcModel.ball.setY(mvcModel.ball.y + mvcModel.ballSpeed.y);
    }

     /**
     * Handles collision checks for the game, including:
     * - Preventing the ball from leaving the screen through the top or bottom.
     * - Introducing a temporary border at the enemy player's side for solo play.
     * - Detecting and handling collisions between the ball and the paddle.
     * - Resetting the ball to the middle of the screen when it goes out of bounds.
     *
     * Collision behaviors:
     * - When the ball collides with the top or bottom screen borders, its vertical speed is inverted.
     * - When the ball collides with the enemy player's side border, its horizontal speed is inverted.
     * - When the ball collides with the paddle, its horizontal speed is inverted, and the player's score is incremented.
     * - When the ball goes out of bounds on the left side, the game is considered over, the ball's speed is set to zero,
     *   and it is repositioned to the center of the screen.
     */
    public void collisionChecks() {
        
        // Prevents the ball from leaving the screen through top or bottom.
        if(mvcModel.ball.getY()>=Math.abs(600)-mvcModel.ball.height||mvcModel.ball.getY()<=Math.abs(0)){
            System.out.println("Y Border collision detected");
            mvcModel.ballSpeed.set(mvcModel.ballSpeed.x, -mvcModel.ballSpeed.y);
        }

        // Introduces a border at the enemy players side. Just so solo player somehow works. TODO: delete/comment out later
        if(mvcModel.ball.x+mvcModel.ball.width>=800){
            mvcModel.ballSpeed.set(-mvcModel.ballSpeed.x, mvcModel.ballSpeed.y);
        }

        // Enables collision between ball and Paddle (for 0<ballX<=ballWidth).
        if(mvcModel.paddleA.y<=mvcModel.ball.y&&mvcModel.paddleA.y+mvcModel.paddleA.height>=mvcModel.ball.y&&mvcModel.paddleA.width>=mvcModel.ball.x&&0<mvcModel.ball.x){
            System.out.println("Paddle collision detected");
            mvcModel.ballSpeed.set(-mvcModel.ballSpeed.x, mvcModel.ballSpeed.y);
            mvcModel.scoreA++;
            if (mvcModel.scoreA > AppPreferences.getAppPreferences().getPlayerHighScore(mvcModel.player1Name)) {
                AppPreferences.getAppPreferences().setPlayerHighScore(mvcModel.player1Name, mvcModel.scoreA);
                System.out.println("New Highscore for " + mvcModel.player1Name + mvcModel.scoreA);
            }
        }

        // If the ball goes out if bounds it is returned to the middle.
        if (0>=mvcModel.ball.x) {
            System.out.println("Game over");
            //mvcModel.ballSpeed.set(0, 0);
            mvcModel.ball.setPosition(400, 300);
            mvcModel.scoreB=mvcModel.scoreB+1;
        }
    }

    public void render(float delta){
        if (mvcModel.homeMenuVisible){

        }else{
            moveBall();
            collisionChecks();
            //listenForServer();
        }
    }
    /**
     * This method disposes of the socket and all other resources used by the controller.
     * It is called when the game is closed.
     */
    public void dispose() {
        socket.dispose();
    }
}