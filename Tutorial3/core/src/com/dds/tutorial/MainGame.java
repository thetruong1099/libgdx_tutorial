package com.dds.tutorial;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

//cử chỉ chạm vào
public class MainGame implements ApplicationListener, GestureDetector.GestureListener {
    SpriteBatch batch;
    private BitmapFont font;
    private String message = "Khong co cu chi nao!";
    private int w, h;
    private GlyphLayout glyphLayout;

    @Override
    public void create() {
        batch = new SpriteBatch();

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("EdgeOfTheGalaxyRegular-OVEa6.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 50;
        font = fontGenerator.generateFont(fontParameter);
        font.setColor(Color.RED);
        glyphLayout = new GlyphLayout();

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        Gdx.input.setInputProcessor(new GestureDetector(this));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();


        glyphLayout.setText(font, message);

        float x = w / 2 - glyphLayout.width / 2;
        float y = h / 2 + glyphLayout.height / 2;

        font.draw(batch, glyphLayout, x, y);

        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        message = "Tap duoc thuc hien, ngon tay " + button;
        return true;
    }

    //bấm và giữ
    @Override
    public boolean longPress(float x, float y) {
        message = "Long press duoc thuc hien";
        return true;
    }

    //kéo nhanh ngón tay trên màn hình
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        message = "Fling duoc thuc hien, velocity:" + velocityX +
                "," + velocityY;
        return true;
    }

    //1 ngón tay giữ và trượt
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        message = "Pan duoc thuc hien, delta:" + deltaX +
                "," + deltaY;
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    //2 ngón tay di chuyển ra ngoài
    @Override
    public boolean zoom(float initialDistance, float distance) {
        message = "Zoom duoc thuc hien, initial Distance:" + initialDistance +
                " Distance: " + distance;
        return true;
    }

    //2 ngón tay di chuyển lại gần nhau
    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        message = "Pinch duoc thuc hien";
        return true;
    }

    @Override
    public void pinchStop() {

    }
}
