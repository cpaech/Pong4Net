package io.github.cpaech.Pong4Net;

/**
 * This takes care of all the menu, game, physics and lobby creation logic. It does not take care of Input gathering and network synchronisation.
 * 
 */
public class Controller {
    /**
     * Reference to the Model provided for Model-View-Controller
     */
    private Model mvcModel;

    /**
     * 
     * @param mvcModel {@link Model} passed by the program entry in {@link Main}
     */
    public Controller(Model mvcModel) 
    {
        this.mvcModel = mvcModel;

    }
}
