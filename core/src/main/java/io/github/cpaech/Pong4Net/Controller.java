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
        if(Gdx.input.isKeyPressed(Keys.W)){
            mvcModel.paddleA.y = mvcModel.paddleA.y +mvcModel.paddleSpeed*delta;
        }
        if(Gdx.input.isKeyPressed(Keys.S)){
            mvcModel.paddleA.y = mvcModel.paddleA.y -mvcModel.paddleSpeed*delta;
        }
        if(Gdx.input.isKeyPressed(Keys.O)){
            mvcModel.paddleB.y = mvcModel.paddleB.y +mvcModel.paddleSpeed*delta;
        }
        if(Gdx.input.isKeyPressed(Keys.L)){
            mvcModel.paddleB.y = mvcModel.paddleB.y -mvcModel.paddleSpeed*delta;
        }
        if(mvcModel.paddleA.y > 600.0f-mvcModel.paddleA.height)
        {mvcModel.paddleA.y = 600.0f-mvcModel.paddleA.height;}
        if(mvcModel.paddleA.y < 0.0f)
        {mvcModel.paddleA.y = 0.0f;}
        if(mvcModel.paddleB.y > 600.0f-mvcModel.paddleB.height)
        {mvcModel.paddleB.y = 600.0f-mvcModel.paddleB.height;}
        if(mvcModel.paddleB.y < 0.0f)
        {mvcModel.paddleB.y = 0.0f;}
    }

    /**
     * This method gets called whenever something happens (eg. buttonclick) in the view.
     */
    @Override
    public void changed (ChangeEvent event, Actor actor) {
        System.out.println("Button Pressed" + actor.getName());
    }
}
