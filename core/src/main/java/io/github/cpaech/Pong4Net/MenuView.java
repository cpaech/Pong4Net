package io.github.cpaech.Pong4Net;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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
    TextButton loginButton;

    TextButton soloPlayButton;

    TextButton startGameButton;

    TextField usernameField;

    TextField serverPortField;

    TextField serverHostField;

    TextField errorField;

    /**
     * Style of the button (font, color, image, etc...).
     */
    TextButtonStyle textButtonStyle;

    TextField.TextFieldStyle textFieldStyle;

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
    public MenuView(Model mvcModel)
    {
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

        textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.RED;

        /**
         * 
        startGameButton = new TextButton("Start Game", textButtonStyle);
        //add the button to the stage. otherwise won't be rendered
        stage.addActor(startGameButton);

        startGameButton.setPosition(400, 300);
        startGameButton.setWidth(120);
        startGameButton.setHeight(50);
        //this calls the mvcController changed() method if the button is pressed
        startGameButton.addListener(mvcController);
        //this is required so the controller knows which button has been pressed
        startGameButton.setName("PlayButton"); 
        */
        loginButton = newTextButton(loginButton, "LoginButton", "Login", 200, 200, 120, 50);
        soloPlayButton = newTextButton(soloPlayButton, "SoloPlayButton", "Solo Play", 400, 400, 120, 50);
        startGameButton = newTextButton(startGameButton, "StartGameButton", "Start Game", 400, 200, 120, 50);
        usernameField = newTextField(usernameField, "usernameField", "Enter Username", 200, 300, 120, 50);
        serverPortField = newTextField(serverPortField, "serverPortField", "Enter Server Port", 400, 250, 120, 50);
        serverHostField = newTextField(serverHostField, "serverHostField", "Enter Server Host", 400, 300, 120, 50);
        errorField = newTextField(errorField, null, null, 200, 0, 300, 50);
    }
    public TextField newTextField(TextField newField, String name, String messageText, float x, float y, float width, float height)
    {
        newField = new TextField("", textFieldStyle);
        newField.setMessageText(messageText);
        newField.setBounds(x, y, width, height);
        newField.setName(name);
        stage.addActor(newField);
        if(name == "serverPortTextField"){newField.setText(String.valueOf(AppPreferences.getAppPreferences().getServerPort()));}
        if(name == "serverHostTextField"){newField.setText(AppPreferences.getAppPreferences().getServerHost());}
        return newField;
    }

    public TextButton newTextButton(TextButton newButton, String name, String text, float x, float y, float width, float height)
    {
        newButton = new TextButton(text, textButtonStyle);
        newButton.setBounds(x, y, width, height);
        newButton.setName(name);
        stage.addActor(newButton);
        return newButton;
    }

    public void setController(Controller mvcController) {  //used in view to manage circular dependency between MenuView and Controller.
        this.mvcController = mvcController;
        soloPlayButton.addListener(mvcController);
        startGameButton.addListener(mvcController);
        usernameField.addListener(mvcController);
        serverPortField.addListener(mvcController);
        serverHostField.addListener(mvcController);
        loginButton.addListener(mvcController);
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
