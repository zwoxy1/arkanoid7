package io.github.arkanoid;

import static io.github.arkanoid.Main.SCR_HEIGHT;
import static io.github.arkanoid.Main.SCR_WIDTH;
import static io.github.arkanoid.Main.isChebur;
import static io.github.arkanoid.Main.isSound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenSettings implements Screen {

    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;

    Texture imgBg;


    Button btnBack;
    Button btnSound;
    Button btnCharacter;

    public ScreenSettings(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;

        imgBg = new Texture("bg.png");

        btnBack = new Button(font, "x", 830, 1580);
        btnSound = new Button(font, soundBtnText(), 50,750);
        btnCharacter = new Button(font, characterBtnText(), 230, 550);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if (btnBack.hit(touch.x, touch.y)) {
                main.setScreen(main.screenMenu);
            }

            if (btnSound.hit(touch.x, touch.y)){
                isSound = !isSound;
                btnSound.setText(soundBtnText());

            }
             if(btnCharacter.hit(touch.x, touch.y)){
                 isChebur = !isChebur;
                 btnCharacter.setText(characterBtnText());
             }
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(imgBg, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        font.draw(batch, "Настройки", 200, 1300);

        btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
        btnSound.font.draw(batch, btnSound.text, btnSound.x, btnSound.y);
        btnCharacter.font.draw(batch, btnCharacter.text, btnCharacter.x, btnCharacter.y);


        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        imgBg.dispose();

    }

    private String soundBtnText() {
        return isSound ? "Музыка включена" : "Музыка выключена";
    }

    public String characterBtnText() {
        return isChebur ? "Персонаж: " + "\n" + "Чебурашка" : "Персонаж:" + "\n" + "Карлсон";
    }
}
