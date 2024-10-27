

package com.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class settingScreen implements Screen {
    private Stage stage;
    private MainGame game;
    private Texture crtexture, blurTexture, screenTexture, creditTexture, buttonOnTexture, buttonOffTexture;
    private Image blurImage, screenImage, creditImage;
    private ImageButton buttonOn, buttonOff, buttonOn1, buttonOff1;
    private Sound clickSound;

    public settingScreen(final MainGame game) {
        this.game = game;
        initializeAssets();
        createStage();
        configureButtons();
        setupInputProcessor();
    }

    private void initializeAssets() {
        game.getPrimaryMusic().setVolume(0.4F); // Lower volume on setting screen

        blurTexture = new Texture(Gdx.files.internal("Settings/blur.png"));
        screenTexture = new Texture(Gdx.files.internal("Settings/1.png"));
        crtexture = new Texture(Gdx.files.internal("Settings/2.png"));
        creditTexture = new Texture(Gdx.files.internal("Settings/3.png"));
        buttonOnTexture = new Texture(Gdx.files.internal("Settings/4.png"));
        buttonOffTexture = new Texture(Gdx.files.internal("Settings/6.png"));

        clickSound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
    }

    private void createStage() {
        stage = new Stage(new ScreenViewport());

        blurImage = new Image(blurTexture);
        blurImage.setSize(stage.getWidth(), stage.getHeight());
        blurImage.setPosition(0, 0);

        screenImage = new Image(screenTexture);
        screenImage.setSize(285, 250);
        screenImage.setPosition(183, 120);
        screenImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f))); // Fade-in animation

        creditImage = new Image(creditTexture);
        creditImage.setSize(200, 70);
        creditImage.setPosition(225, 120);

        stage.addActor(blurImage);
        stage.addActor(screenImage);
        stage.addActor(creditImage);
    }

    private void configureButtons() {
        // Creating button sets with new visibility logic and smoother transitions
        buttonOn = new ImageButton(new TextureRegionDrawable(buttonOnTexture));
        buttonOff = new ImageButton(new TextureRegionDrawable(buttonOffTexture));
        buttonOn1 = new ImageButton(new TextureRegionDrawable(buttonOnTexture));
        buttonOff1 = new ImageButton(new TextureRegionDrawable(buttonOffTexture));

        // Setting initial positions
        configureButtonPosition(buttonOn, 257, 268);
        configureButtonPosition(buttonOff, 257, 268, false);
        configureButtonPosition(buttonOn1, 257, 216);
        configureButtonPosition(buttonOff1, 257, 216, false);


        addClickListener(buttonOn, buttonOff);
        addClickListener(buttonOff, buttonOn);
        addClickListener(buttonOn1, buttonOff1);
        addClickListener(buttonOff1, buttonOn1);

        stage.addActor(buttonOn);
        stage.addActor(buttonOff);
        stage.addActor(buttonOn1);
        stage.addActor(buttonOff1);

        // Back button with animation
        ImageButton backButton = new ImageButton(new TextureRegionDrawable(new Texture(Gdx.files.internal("Settings/2.png"))));
        backButton.setPosition(530, 375);
        backButton.setSize(40, 120);
        backButton.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                game.setScreen(new MainMenuScreen(game));
                game.getPrimaryMusic().setVolume(1.0F); // Reset volume when returning to the main menu
            }
        });

        stage.addActor(backButton);
    }

    private void configureButtonPosition(ImageButton button, float x, float y) {
        configureButtonPosition(button, x, y, true);
    }

    private void configureButtonPosition(ImageButton button, float x, float y, boolean visible) {
        button.setPosition(x, y);
        button.setSize(203, 77);
        button.setVisible(visible);
    }

    private void addClickListener(final ImageButton visibleButton, final ImageButton hiddenButton) {
        visibleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                visibleButton.setVisible(false);
                hiddenButton.setVisible(true);
                hiddenButton.addAction(Actions.fadeIn(0.5f)); // Smooth fade-in transition
            }
        });
    }

    private void setupInputProcessor() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen for a game
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9F, 0.9F, 0.9F, 1); // Custom background color
        Gdx.gl.glClear(16384);


        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        blurImage.setSize(stage.getWidth(), stage.getHeight());
    }

    @Override
    public void pause() {
        // Handle game pause
    }

    @Override
    public void resume() {
        // Handle game resume
    }

    @Override
    public void hide() {
        // Called when the screen is hidden
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        blurTexture.dispose();
        screenTexture.dispose();
        crtexture.dispose();
        creditTexture.dispose();
        buttonOnTexture.dispose();
        buttonOffTexture.dispose();
        clickSound.dispose();
    }
}
