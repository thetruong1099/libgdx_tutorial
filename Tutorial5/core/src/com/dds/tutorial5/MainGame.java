package com.dds.tutorial5;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainGame extends ApplicationAdapter {

    public class MyActor extends Actor {
        Texture texture = new Texture(Gdx.files.internal("dante.png"));
        float actorX = 0;
        float actorY = 0;
        public boolean started = false;

        public MyActor() {
            //vd click actor -> actor di chuyển

/*            setBounds(actorX, actorY, texture.getWidth(), texture.getHeight()); // thiết lập giới hạn để phù hợp với kết cấu của actor
                                                                                //không có không thể thực hiện action click ỏ touch.
            addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ((MyActor) event.getTarget()).started = true; //xác định actor được click or touch
                    return true;
                }
            });*/

            //vd actor thực hiện di chuyển
            setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            //vd click actor -> actor di chuyển
//            batch.draw(texture, actorX, actorY);
            //vd actor thực hiện di chuyển
            batch.draw(texture, this.getX(), this.getY());
        }

        //vd click actor -> actor di chuyển

/*        @Override
        public void act(float delta) {
            if (started) {
                actorX += 5;
            }
        } //cập nhật actor theo thời gian.*/
    }

    private Stage stage;

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        MyActor myActor = new MyActor();
        //vd click actor -> actor di chuyển
//        myActor.setTouchable(Touchable.enabled);
        //vd actor thực hiện di chuyển
        MoveToAction moveToAction = new MoveToAction(); // di chuyển actor đến 1 vị trí nhất định theo thời gian.
        moveToAction.setPosition(300f, 0f); // thiết lập phương thức di chuyển
        moveToAction.setDuration(10f);  // thiết lập thời gian di chyển
        myActor.addAction(moveToAction); //gán phương thức di chuyển cho actor.
        /*Ngoài ra còn có kiểu action khác:
         *  MoveByAction, ScaleToAction, ColorAction, DelayAction, RepeatAction, RotateByAction, SequenceAction,...*/

        stage.addActor(myActor);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw(); //gọi lần lượt các phương thức draw mà stage chứa.
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
