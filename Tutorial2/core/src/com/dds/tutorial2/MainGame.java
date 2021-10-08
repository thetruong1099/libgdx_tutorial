package com.dds.tutorial2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;
import java.util.Map;

public class MainGame extends ApplicationAdapter implements InputProcessor {

    private SpriteBatch batch;
    private BitmapFont font;
    private String message = "Bắt đầu chạm!";
    private GlyphLayout glyphLayout;
    private int w, h;

    private Map<Integer, TouchInfo> touches = new HashMap<>();

    @Override
    public void create() {
        batch = new SpriteBatch();

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("EdgeOfTheGalaxyRegular-OVEa6.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 50;
        font = fontGenerator.generateFont(fontParameter);
        font.setColor(Color.RED);

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        glyphLayout = new GlyphLayout();

        //xử lý chạm đa điểm
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        message = "";

        for (int i = 0; i < 5; i++) {

            if (touches.get(i) != null && touches.get(i).touched) {
                touches.get(i);
                message += "Ngon tay[" + i + "] cham vao toa do:" +
                        touches.get(i).touchX +
                        "," +
                        touches.get(i).touchY +
                        "\n";
            }

        }

        glyphLayout.setText(font, message);

        float x = w / 2 - glyphLayout.width / 2;
        float y = h / 2 + glyphLayout.height / 2;

        font.draw(batch, glyphLayout, x, y);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (pointer < 5) {
            touches.put(pointer, new TouchInfo(screenX, screenY, true));
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (pointer < 5) {
            touches.put(pointer, new TouchInfo(0, 0, false));
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
