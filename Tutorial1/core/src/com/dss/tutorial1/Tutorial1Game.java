package com.dss.tutorial1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

public class Tutorial1Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private Sprite sprite;

    //texture
    private Texture texture;

    //pixmap
    private Pixmap pixmap;

    //textAtlas
    private TextureAtlas textureAtlas;
    private int currentFrame = 1;
    private String currentAtlasKey = new String("0001");

    //animation
    private Animation animation;
    private float elapsedTime = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //draw ui with texture
//		texture = new Texture(Gdx.files.internal("playerShip2_red.png"));

        //draw ui with pixmap
/*		// Width: 256, height: 128
		pixmap = new Pixmap(256, 128, Pixmap.Format.RGBA8888);
		// Fill red color
		pixmap.setColor(Color.RED);
		pixmap.fill();
		// Draw 2 lines
		pixmap.setColor(Color.BLACK);
		pixmap.drawLine(0, 0, pixmap.getWidth() - 1, pixmap.getHeight() - 1);
		pixmap.drawLine(0, pixmap.getHeight() - 1, pixmap.getWidth() - 1, 0);
		// Draw a circle in the middle
		pixmap.setColor(Color.YELLOW);
		pixmap.drawCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, pixmap.getHeight() / 2 - 1);
		texture = new Texture(pixmap);
		pixmap.dispose();
		sprite = new Sprite(texture);*/

        //draw with textureAtlas
/*        textureAtlas = new TextureAtlas((Gdx.files.internal("spritesheet.atlas")));
        TextureAtlas.AtlasRegion region = textureAtlas.findRegion(currentAtlasKey);
        sprite = new Sprite(region);
        sprite.setPosition(120, 100);
        sprite.scale(2.5f);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                currentFrame++;
                if (currentFrame > 4) {
                    currentFrame = 1;
                }

                currentAtlasKey = String.format("%04d", currentFrame);
                sprite.setRegion(textureAtlas.findRegion(currentAtlasKey));
            }
        }, 0, 1 / 5.0f);*/

        //Draw with animation
        textureAtlas = new TextureAtlas((Gdx.files.internal("spritesheet.atlas")));
        animation = new Animation(1 / 5.0f, textureAtlas.getRegions());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
//        sprite.draw(batch);
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 100f, 100f);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
//        texture.dispose();
        textureAtlas.dispose();
    }
}
