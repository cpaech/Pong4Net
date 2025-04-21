package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    
    /**
     * View used to show all elements on screen
     */
    View mvcView;
    /**
     * Model used to store and synchronize Game Data
     */
    Model mvcModel;
    /**
     * Controller taking care of game and phsyics logic
     */
    Controller mvcController;
    /**
     * InputController for accepting user input
     */
    InputController inputController;

    /** 
    * This is the entry method for the program. It initializes the {@link Main#mvcModel}, {@link io.github.cpaech.Pong4Net.Main#mvcView} and {@link io.github.cpaech.Pong4Net.Main#mvcController}
    */
    @Override
    public void create() {
        mvcModel = new Model();
        mvcController = new Controller(mvcModel);
        mvcView = new View(mvcModel, mvcController);
        inputController = new InputController(mvcModel);
    }

    /**
     * Called every frame to update the game. This subsequently calls the render method of all other classes
     */
    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime(); // retrieve the current delta (time since last frame)
        inputController.input();
        mvcController.moveBall();
        mvcController.collisionChecks();
        mvcView.render(delta); 
    }

    /**
     * Disposes of all classes and their memory (important for graphical stuff)
     */
    @Override
    public void dispose() {
        mvcView.dispose();
    }
}
