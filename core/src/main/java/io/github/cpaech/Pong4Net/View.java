package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * This takes care of rendering the Gamewindow and everything on screen.
 */
public class View {
    /**
     * Dies ist der SpriteBatch welches uns ermöglicht die einzelnen Grafiken auf
     * den Bildschirm zu projezieren
     */
    private SpriteBatch batch;
    /**
     * This variable is used to store a
     * texture object that represents the graphical image of a paddle in a game. For
     * now this is just a colored rectangle but can be used later to draw actual
     * images
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
     * 
     * @param mvcModel {@link Model} passed over from Main
     *
     */

    MenuView mvcMenuView;
    Controller mvcController;

    /**
     * Sets up all nesessary variables for drawing things to the screen and loads
     * all resources into memory.
     * 
     * @param mvcModel      Model passed by {@link Main}
     * @param mvcController Controller passed by {@link Main}
     */
    public View(Model mvcModel, Controller mvcController) {
        batch = new SpriteBatch(); // SpriteBatch. Necessary to draw things to the screen
        paddleTexture = new Texture("libgdx.png");
        ballTexture = new Texture("libgdx.png");
        font = new BitmapFont(); // uses a default font of 15pt

        this.mvcModel = mvcModel;
        this.mvcController = mvcController;

        mvcMenuView = new MenuView(mvcModel, mvcController);
    }

    /**
     * This method draws everything on the screen that needs to be drawn. In order
     * to do that it fetches nescessary informations such as positions of the pedals
     * from the {@link io.github.cpaech.Pong4Net.Model}
     * 
     * 
     * @param delta This parameter is the time that has passed since the last
     *              execution of the render method.
     */
    public void render(float delta) {
        // clear the screen with one color
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        // begin drawing
        batch.begin();
        // weather to draw the home menu or game screen
        if (mvcModel.homeMenuVisible) {
            mvcMenuView.render(batch);
        } else {
            batch.draw(paddleTexture, mvcModel.paddleB.x, mvcModel.paddleB.y, mvcModel.paddleB.width,
                    mvcModel.paddleB.height);
            batch.draw(paddleTexture, mvcModel.paddleA.x, mvcModel.paddleA.y, mvcModel.paddleA.width,
                    mvcModel.paddleA.height);
            font.draw(batch, mvcModel.scoreA + " : " + mvcModel.scoreB, 400, 560);
            batch.draw(ballTexture, mvcModel.ball.x, mvcModel.ball.y, mvcModel.ball.width, mvcModel.ball.height);
        }
        // end drawing
        batch.end();
    }

    /**
     * This methods disposes of all graphical elements from memory
     */
    public void dispose() {
        paddleTexture.dispose();
        ballTexture.dispose();
        batch.dispose();
    }
}
