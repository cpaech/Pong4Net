package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * This takes care of rendering the Gamewindow and everything on screen.
 */
public class View {
    /** 
    * Dies ist der SpriteBatch welches uns ermöglicht die einzelnen Grafiken auf den Bildschirm zu projezieren
    */
    private SpriteBatch batch;
    /** 
    * This variable is used to store a
    * texture object that represents the graphical image of a paddle in a game. For now this is just a colored rectangle but can be used later to draw actual images
    */ 
    private Texture paddleTexture;
    /**
     * This is the texture used for the ball. For now it is just a red rectangle
     */
    private Texture ballTexture;
    /**
     * Reference to the Model in the {@link Main}
     */
    private Model mvcModel;
    /**
     * Font which is used for the Scoredisplay in the main gameview
     */
    BitmapFont font;
    /**
     * Initializes all textures and graphical elements
     * @param mvcModel {@link Model} passed over from Main
     *
     */
    public View(Model mvcModel)
    {
        batch = new SpriteBatch();
        paddleTexture = new Texture("libgdx.png");
        ballTexture = new Texture("libgdx.png");
        this.mvcModel = mvcModel;
        font = new BitmapFont();
    }

    
    /** 
     * This method draws everything on the screen that needs to be drawn. In order to do that it fetches nescessary informations such as positions of the pedals from the {@link io.github.cpaech.Pong4Net.Model}
     * 
     * 
     * @param delta This parameter is the time that has passed since the last execution of the render method.
     */
    public void render(float delta) 
    {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(paddleTexture, mvcModel.paddleA.x, mvcModel.paddleA.y, mvcModel.paddleA.width, mvcModel.paddleA.height);
        batch.draw(paddleTexture, mvcModel.paddleB.x, mvcModel.paddleB.y, mvcModel.paddleB.width, mvcModel.paddleB.height);
        batch.draw(ballTexture, mvcModel.ball.x, mvcModel.ball.y, mvcModel.ball.width, mvcModel.ball.height);
        font.draw(batch, mvcModel.scoreA + " : " + mvcModel.scoreB, 400, 560);
        batch.end();
    }
    /**
     * This methods disposes of all graphical elements from memory
     */
    public void dispose(){
        paddleTexture.dispose();
        ballTexture.dispose();
        batch.dispose();
    }
}
