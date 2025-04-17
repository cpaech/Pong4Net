package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * This takes care of all the menu, game, physics and lobby creation logic. It does not take care of Input gathering and network synchronisation.
 * It extends ChangeListener in order to react to button presses by the view
 */
public class Controller extends ChangeListener{
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

    public void render(float delta)
    {
        
    }

    /**
     * This method gets called whenever something happens (eg. buttonclick) in the view.
     */
    @Override
    public void changed (ChangeEvent event, Actor actor) {
        System.out.println("Button Pressed" + actor.getName());
    }
}
