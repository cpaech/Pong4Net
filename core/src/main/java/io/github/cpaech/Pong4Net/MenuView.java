package io.github.cpaech.Pong4Net;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

//TODO https://libgdx.com/wiki/graphics/2d/scene2d/scene2d-ui

/**
 * This is the part of the view structure which takes care of rendering 
 * the menu with scoreboard and everything. From here the player will be able to start a game.
 */
public class MenuView {
    /**
     * Shared model
     */
    private Model mvcModel;
    /**
     * Shared controller. Responsible for processing the input from UI-elements.
     */
    private Controller mvcController;
    /**
     * This is the container for all ui elements. Required by libgdx.
     */
    private Stage stage;
    /**
     * Example of a button with text.
     */
    TextButton button;
    /**
     * Style of the button (font, color, image, etc...).
     */
    TextButtonStyle textButtonStyle;
    /**
     * The Font used for all UI-elements.
     */
    BitmapFont font;
    /**
     * This is a button skin, containing all the images of each state for the button.
     */
    Skin skin;
    /**
     * Textureatlas containing all the imagery for the button graphics.
     */
    TextureAtlas buttonAtlas;
    
    /**
     * This method sets up all nesessary objects for the UI.
     * @param mvcModel Model passed by {@link Main}
     * @param mvcController Controller passed by {@link Main}, responsible for input processing.
     */
    public MenuView(Model mvcModel, Controller mvcController)
    {
        this.mvcController = mvcController;
        this.mvcModel = mvcModel;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/button.pack"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("buttonnormal");
        textButtonStyle.down = skin.getDrawable("buttonpressed");
        textButtonStyle.checked = skin.getDrawable("buttonnormal");
        textButtonStyle.fontColor = Color.BLACK;

        button = new TextButton("Button1", textButtonStyle);
        stage.addActor(button); //add the button to the stage. otherwise won't be rendered


        button.setPosition(400, 300);
        button.setWidth(120);
        button.setHeight(50);

        //this calls the mvcController changed() method if the button is pressed
        button.addListener(mvcController);
        //this is required so the controller knows which button has been pressed
        button.setName("PlayButton");
    }

    /**
     * This method draws the entire UI
     * @param batch The SpriteBatch passed by {@link View}
     */
    public void render(SpriteBatch batch)
    {
        stage.draw();
    }
    /**
     * Clear all memory and cleanup
     */
    public void dispose()
    {
        stage.dispose();
        skin.dispose();
        buttonAtlas.dispose();
    }

}
