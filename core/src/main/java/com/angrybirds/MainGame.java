//package com.angrybirds;
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.audio.Music;
//
//public class MainGame extends Game {
//    private Music backgroundMusic;
//    private Music newBackgroundMusic;
//    public Object batch;
//
//    public MainGame() {
//    }
//
//    public void create() {
//        this.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("b.mp3"));
//        this.newBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("b2.mp3"));
//        this.backgroundMusic.setLooping(true);
//        this.backgroundMusic.setVolume(1.0F);
//        this.backgroundMusic.play();
//        this.setScreen(new MainMenuScreen(this));
//    }
//
//    public void setNewBackgroundMusic(Music music) {
//        this.newBackgroundMusic = music;
//    }
//
//    public Music getNewBackgroundMusic() {
//        return this.newBackgroundMusic;
//    }
//
//    public Music getBackgroundMusic() {
//        return this.backgroundMusic;
//    }
//
//    public void dispose() {
//        if (this.backgroundMusic != null) {
//            this.backgroundMusic.dispose();
//        }
//
//    }
//
//}

package com.angrybirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MainGame extends Game {
    private Music primaryMusic;
    private Music secondaryMusic;
    public Object spriteBatch;

    public MainGame() {}

    @Override
    public void create() {
        primaryMusic = Gdx.audio.newMusic(Gdx.files.internal("b.mp3"));
        secondaryMusic = Gdx.audio.newMusic(Gdx.files.internal("b2.mp3"));

        primaryMusic.setLooping(true);
        primaryMusic.setVolume(1.0F);
        primaryMusic.play();

        setScreen(new MainMenuScreen(this));
    }

    public void setSecondaryMusic(Music music) {
        this.secondaryMusic = music;
    }

    public Music getSecondaryMusic() {
        return secondaryMusic;
    }

    public Music getPrimaryMusic() {
        return primaryMusic;
    }

    @Override
    public void dispose() {
        if (primaryMusic != null) {
            primaryMusic.dispose();
        }
    }
}
