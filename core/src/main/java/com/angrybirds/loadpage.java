//
//package com.angrybirds;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.utils.Array;
//import com.badlogic.gdx.graphics.GL20;
//
//public class loadpage implements Screen {
//    private static final float LOADING_DURATION = 0.5F; // Loading time duration
//    private float elapsedTime = 0.0F;
//    private SpriteBatch batch;
//    private Animation<TextureRegion> loadingAnimation;
//    private MainGame game;
//    private Screen nextScreen;
//
//    public loadpage(MainGame game, Screen nextScreen) {
//        this.game = game;
//        this.nextScreen = nextScreen;
//        initializeAssets();
//        createLoadingAnimation();
//    }
//
//    private void initializeAssets() {
//        batch = new SpriteBatch();
//    }
//
//    private void createLoadingAnimation() {
//        Array<TextureRegion> frames = new Array<>();
//
//        for (int i = 1; i <= 8; i++) {
//            Texture frameTexture = new Texture(Gdx.files.internal("loading/" + i + ".jpg"));
//            frames.add(new TextureRegion(frameTexture));
//        }
//
//        loadingAnimation = new Animation<>(0.1F, frames, Animation.PlayMode.LOOP);
//    }
//
//    @Override
//    public void show() {
//        // Called when this screen becomes the current screen for a game
//    }
//
//    @Override
//    public void render(float delta) {
//        elapsedTime += delta;
//
//        // Clear screen with a light blue background
//        Gdx.gl.glClearColor(0.6F, 0.8F, 1.0F, 1.0F);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        // Render the loading animation
//        batch.begin();
//        TextureRegion currentFrame = loadingAnimation.getKeyFrame(elapsedTime, true);
//        batch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        batch.end();
//
//        // Transition to the next screen after the loading duration
//        if (elapsedTime >= LOADING_DURATION) {
//            game.setScreen(nextScreen);
//        }
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        // Handle resizing if necessary
//    }
//
//    @Override
//    public void pause() {
//        // Handle pause state
//    }
//
//    @Override
//    public void resume() {
//        // Handle resume state
//    }
//
//    @Override
//    public void hide() {
//        // Clean up when the screen is no longer visible
//        dispose();
//    }
//
//    @Override
//    public void dispose() {
//        // Properly dispose of resources to prevent memory leaks
//        batch.dispose();
//    }
//}

package com.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.GL20;

public class loadpage implements Screen {
    private static final float LOAD_DURATION = 0.8F; // Loading time
    private float timeCounter = 0.0F;
    private SpriteBatch spriteBatch;
    private Animation<TextureRegion> loadingAnim;
    private MainGame mainGame;
    private Screen nextDisplayScreen;

    public loadpage(MainGame mainGame, Screen nextDisplayScreen) {
        this.mainGame = mainGame;
        this.nextDisplayScreen = nextDisplayScreen;
        setupBatch();
        createLoadingAnim();
    }

    private void setupBatch() {
        spriteBatch = new SpriteBatch();
    }

    private void createLoadingAnim() {
        Array<TextureRegion> frameArray = new Array<>();

        for (int frameNum = 1; frameNum <= 8; frameNum++) {
            Texture frameImage = new Texture(Gdx.files.internal("loading/" + frameNum + ".jpg"));
            frameArray.add(new TextureRegion(frameImage));
        }

        loadingAnim = new Animation<>(0.1F, frameArray, Animation.PlayMode.LOOP);
    }

    @Override
    public void show() {
        // Executed when this screen is set as the current screen
    }

    @Override
    public void render(float deltaTime) {
        timeCounter += deltaTime;

        // Clear screen with a light blue color
        Gdx.gl.glClearColor(0.6F, 0.8F, 1.0F, 1.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the loading animation
        spriteBatch.begin();
        TextureRegion currentFrame = loadingAnim.getKeyFrame(timeCounter, true);
        spriteBatch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();

        // Switch to the next screen once loading duration has passed
        if (timeCounter >= LOAD_DURATION) {
            mainGame.setScreen(nextDisplayScreen);
        }
    }

    @Override
    public void resize(int width, int height) {
        // Handle resizing if needed
    }

    @Override
    public void pause() {
        // Handle screen pause
    }

    @Override
    public void resume() {
        // Handle screen resume
    }

    @Override
    public void hide() {
        // Cleanup when screen is no longer visible
        dispose();
    }

    @Override
    public void dispose() {
        // Release resources to avoid memory leaks
        spriteBatch.dispose();
    }
}
