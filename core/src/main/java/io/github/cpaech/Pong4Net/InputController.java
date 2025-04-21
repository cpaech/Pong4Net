package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputController {
    /**
     * Reference to the Model provided for Model-View-Controller
     */
    private Model mvcModel;

    /**
     * 
     * @param mvcModel {@link Model} passed by the program entry in {@link Main}
     */
    public InputController(Model mvcModel) {
        this.mvcModel = mvcModel;
    }

    /**
     * This method enables paddle movement but prevents it from moving out of the
     * playig field.
     * TODO: longer key presses-> higher paddleSpeed. Maybe a mouseover for better
     * control?
     */
    public void input() {
        int paddleSpeed = 5;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && mvcModel.paddleA.y + mvcModel.paddleA.height < 600) {
            mvcModel.paddleA.setY(mvcModel.paddleA.getY() + paddleSpeed);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && mvcModel.paddleA.y > 0) {
            mvcModel.paddleA.setY(mvcModel.paddleA.getY() - paddleSpeed);
        }
    }
}
