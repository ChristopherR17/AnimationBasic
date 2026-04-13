package com.christophercarrillo.animationbasic;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 5;

    private SpriteBatch batch;
    private Texture walkSheet;
    private Animation<TextureRegion> walkAnimation;

    private float stateTime;

    private float posX, posY;

    @Override
    public void create() {
        batch = new SpriteBatch();

        walkSheet = new Texture("animation_sheet.png");

        TextureRegion[][] tmp = TextureRegion.split(
            walkSheet,
            walkSheet.getWidth() / FRAME_COLS,
            walkSheet.getHeight() / FRAME_ROWS
        );

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation<>(0.025f, walkFrames);

        stateTime = 0f;
        posX = 50;
        posY = 50;
    }

    @Override
    public void render() {

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        stateTime += Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        batch.begin();
        batch.draw(currentFrame, posX, posY,
            currentFrame.getRegionWidth() * 2,
            currentFrame.getRegionHeight() * 2);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        walkSheet.dispose();
    }
}
