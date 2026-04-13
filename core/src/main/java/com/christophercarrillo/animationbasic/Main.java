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
    // Constantes para las filas y columnas del sprite sheet
    private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 5;

    // Objetos necesarios para la animación
    private SpriteBatch batch;
    private Texture walkSheet;
    private Animation<TextureRegion> walkAnimation;

    // Variable para el tiempo transcurrido de la animación
    private float stateTime;

    // Posición del personaje
    private float posX, posY;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Cargar el sprite sheet
        walkSheet = new Texture("animation_sheet.png");

        // Dividir el sprite sheet en una matriz 2D de TextureRegions
        TextureRegion[][] tmp = TextureRegion.split(
            walkSheet,
            walkSheet.getWidth() / FRAME_COLS,
            walkSheet.getHeight() / FRAME_ROWS
        );

        // Convertir la matriz 2D en un array 1D para la animación
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        // Crear la animación con 0.025 segundos por frame
        walkAnimation = new Animation<>(0.025f, walkFrames);

        // Inicializar variables
        stateTime = 0f;
        posX = 50;
        posY = 50;
    }

    @Override
    public void render() {
        // Limpiar la pantalla
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Acumular el tiempo transcurrido
        stateTime += Gdx.graphics.getDeltaTime();

        // Obtener el frame actual de la animación
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        // Dibujar el frame
        batch.begin();
        batch.draw(currentFrame, posX, posY);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        walkSheet.dispose();
    }
}
