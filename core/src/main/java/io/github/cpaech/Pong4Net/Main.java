package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

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
     * Main class holding all the logic for the server
     */
    ServerController serverController;

    /**
     * Checks wheter this is a server build
     */
    boolean isServerBuild = false; //change to start menu -> Start Server/Client

    /** 
    * This is the entry method for the program. It initializes the {@link Main#mvcModel}, {@link io.github.cpaech.Pong4Net.Main#mvcView} and {@link io.github.cpaech.Pong4Net.Main#mvcController}
    */
    @Override
    public void create() {
        mvcModel = new Model();
        if (!isServerBuild) {
            mvcController = new Controller(mvcModel);
            mvcView = new View(mvcModel, mvcController);
            inputController = new InputController(mvcModel);
        }
        else {
            serverController = new ServerController(mvcModel);
        }
    }

    /**
     * This method updates all components of the game. It is called once per frame.
     * It is responsible for rendering the game and updating the game state. 
     * Or updating the server state.
     */
    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime(); // retrieve the current delta (time since last frame)
        if (!isServerBuild){
            inputController.input();
            mvcController.render(delta);
            mvcController.moveBall();
            mvcController.collisionChecks();
            mvcView.render(delta); 
        }
        else {
            serverController.render();
        }
    }

    /**
     * Disposes of all classes and their memory (important for graphical stuff)
     */
    @Override
    public void dispose() {
        mvcView.dispose();
    }
}
