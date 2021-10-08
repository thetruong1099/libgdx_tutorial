package com.dds.tutorial6;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class MainGame extends ApplicationAdapter {
    private Stage stage;
    private Group group;

    class Person extends Actor {
        private TextureRegion _texture;

        public Person(TextureRegion texture) {
            _texture = texture;
            setBounds(getX(), getY(), _texture.getRegionWidth(), _texture.getRegionHeight());

            this.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttons) {
                    setVisible(false);
                    return true;
                }
            });
        }

        public void draw(Batch batch, float alpha) {
            batch.draw(_texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                    getScaleX(), getScaleY(), getRotation());
        }

        // kiểm tra vòng giới hạn của hit, mặc định hit là true và xóa actor
/*        public Actor hit(float x, float y, boolean touchable) {
            // actor bị ẩn không thể chạm vào hit() trả về null
            if (!this.isVisible() || this.getTouchable() == Touchable.disabled)
                return null;

            // lấy điểm chính giữa của vòng hit()
            float centerX = getWidth() / 2;
            float centerY = getHeight() / 2;

            // tính bán kính hình tròn
            float radius = (float) Math.sqrt(centerX * centerX +
                    centerY * centerY);
            //tính khoảng cách từ điển chạm đến tâm
            float distance = (float) Math.sqrt(((centerX - x) * (centerX - x))
                    + ((centerY - y) * (centerY - y)));

            if (distance <= radius) return this;

            return null;
        }*/
    }

    private Person[] people;

    @Override
    public void create() {
        stage = new Stage();
        //load ảnh
        final TextureRegion personTexture = new TextureRegion(new Texture("dante.png"));
        //ví dụ group actor
        /*
        final TextureRegion swordTexture = new TextureRegion(new Texture("sword.png"));

        //tạo actor dante
        final Actor person = new Actor() {
            public void draw(Batch batch, float alpha) {
                batch.draw(personTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                        getScaleX(), getScaleY(), getRotation());
            }
        };

        // thiết lập ranh giới cho actor
        person.setBounds(person.getX(), person.getY(), personTexture.getRegionWidth(), personTexture.getRegionHeight());

        //tạo actor sword
        final Actor sword = new Actor() {
            public void draw(Batch batch, float alpha) {
                batch.draw(swordTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                        getScaleX(), getScaleY(), getRotation());
            }
        };

        //thiết lập ranh giói cho sword
        sword.setBounds(0, 0, swordTexture.getRegionWidth(), swordTexture.getRegionHeight());

        //thiết lập vị trí của sword so với persion
        sword.setPosition(person.getWidth() - 55, 10);

        //thiết lâpp group nhóm person và sword thành 1 thực thể
        group = new Group();
        group.addActor(person);
        group.addActor(sword);

        //tạo hành động di chuyển đến vị chí 200 theo trục x trong 5s và quay 90 độ trong 5s
        group.addAction(parallel(moveTo(200, 0, 5), rotateBy(90, 5)));

        stage.addActor(group);*/

        //ví dụ về hit
        people = new Person[100];
        Random random = new Random();
        // tạo 100 đối tượng với vị trí random
        for (int i = 0; i < 100; i++) {
            people[i] = new Person(personTexture);

            people[i].setPosition(random.nextInt(Gdx.graphics.getWidth() - (int) people[i].getWidth())
                    , random.nextInt(Gdx.graphics.getHeight() - (int) people[i].getHeight()));

            stage.addActor(people[i]);
        }
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
