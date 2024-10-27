package com.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
    private Stage uiStage;
    private MainGame appInstance;
    private Texture pauseIcon, blurBg, confirmIcon, cancelIcon, winScreen, loseScreen, backIcon;
    private ImageButton pauseBtn, confirmBtn, cancelBtn, backBtn;
    private Image blurLayer, winOverlay, loseOverlay;
    private Sound tapSound;

    public GameScreen(final MainGame appInstance) {
        this.appInstance = appInstance;
        loadAssets();
        setupStage();
        createPauseBtn();
        createConfirmCancelBtns();
        setInputProcessor();
    }

    private void loadAssets() {
        pauseIcon = new Texture(Gdx.files.internal("GameScreen/p.png"));
        blurBg = new Texture(Gdx.files.internal("GameScreen/blur.png"));
        confirmIcon = new Texture(Gdx.files.internal("GameScreen/check.png"));
        cancelIcon = new Texture(Gdx.files.internal("GameScreen/cross.png"));
        winScreen = new Texture(Gdx.files.internal("GameScreen/victory.png"));
        loseScreen = new Texture(Gdx.files.internal("GameScreen/loose.png"));
        backIcon = new Texture(Gdx.files.internal("GameScreen/back.png"));
        tapSound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
    }

    private void setupStage() {
        uiStage = new Stage(new ScreenViewport());
        blurLayer = new Image(blurBg);
        blurLayer.setSize(uiStage.getWidth(), uiStage.getHeight());
        blurLayer.setPosition(0, 0);
        uiStage.addActor(blurLayer);
    }

    private void createPauseBtn() {
        pauseBtn = new ImageButton(new TextureRegionDrawable(pauseIcon));
        pauseBtn.setPosition(-18.0F, 365.0F);
        pauseBtn.setSize(120.0F, 160.0F);
        pauseBtn.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
        pauseBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tapSound.play();
                pauseBtn.addAction(Actions.sequence(
                    Actions.fadeOut(0.5f),
                    Actions.run(() -> appInstance.setScreen(new PauseScreen(appInstance)))
                ));
            }
        });
        uiStage.addActor(pauseBtn);
    }

    private void createConfirmCancelBtns() {
        confirmBtn = setupButton(confirmIcon, 75.0F, 415.0F, 55.0F, 55.0F, this::displayWinScreen);
        cancelBtn = setupButton(cancelIcon, 136.0F, 404.0F, 55.0F, 75.0F, this::displayLoseScreen);
    }

    private ImageButton setupButton(Texture icon, float x, float y, float width, float height, Runnable onClickAction) {
        ImageButton button = new ImageButton(new TextureRegionDrawable(icon));
        button.setPosition(x, y);
        button.setSize(width, height);
        button.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tapSound.play();
                onClickAction.run();
            }
        });
        uiStage.addActor(button);
        return button;
    }

    private void displayWinScreen() {
        if (winOverlay == null) {
            winOverlay = setupOverlay(winScreen);
            createBackButton(this::navigateToLevelScreen);
        }
    }

    private void displayLoseScreen() {
        if (loseOverlay == null) {
            loseOverlay = setupOverlay(loseScreen);
            createBackButton(this::navigateToLevelScreen);
        }
    }

    private Image setupOverlay(Texture screenTexture) {
        Image overlay = new Image(screenTexture);
        overlay.setSize(uiStage.getWidth(), uiStage.getHeight());
        overlay.setPosition(0, 0);
        overlay.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
        uiStage.addActor(overlay);
        return overlay;
    }

    private void createBackButton(Runnable onClickAction) {
        backBtn = setupButton(backIcon, 10, 250, 70, 70, onClickAction);
    }

    private void navigateToLevelScreen() {
        appInstance.setScreen(new LevelEndScreen(appInstance));
    }

    private void setInputProcessor() {
        Gdx.input.setInputProcessor(uiStage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        uiStage.getViewport().update(width, height, true);
        blurLayer.setSize(uiStage.getWidth(), uiStage.getHeight());
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        uiStage.dispose();
        pauseIcon.dispose();
        blurBg.dispose();
        confirmIcon.dispose();
        cancelIcon.dispose();
        if (winOverlay != null) winScreen.dispose();
        if (loseOverlay != null) loseScreen.dispose();
        if (backBtn != null) backIcon.dispose();
        tapSound.dispose();
    }
}

