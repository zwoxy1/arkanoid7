package io.github.arkanoid;

import static io.github.arkanoid.Main.*;
import static io.github.arkanoid.ScreenGame.gameSound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenMenu implements Screen {

    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;

    Texture imgBg;

    Button btnGame;
    Button btnExit;
    Button btnSettings;



    public ScreenMenu(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;

        imgBg = new Texture("bg2.png");

        btnGame = new Button(font, "Играть", 300, 1000);
        btnExit = new Button(font, "Выйти из игры", 130, 150);
        btnSettings = new Button(font, "Настройки", SCR_WIDTH/2/2, 800);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ///////
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if (btnGame.hit(touch.x, touch.y)) {
                main.setScreen(main.screenGame);
                if (isSound) {
                    gameSound.play();
                }

            }

            if (btnExit.hit(touch.x, touch.y)) {
                Gdx.app.exit();
            }

            if (btnSettings.hit(touch.x, touch.y)){
                main.setScreen(main.screenSettings);
            }

        }


        ///////
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(imgBg, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        font.draw(batch, "АРКАНОИД", 180, 1300);

        btnGame.font.draw(batch, btnGame.text, btnGame.x, btnGame.y);
        btnGame.font.draw(batch, btnExit.text, btnExit.x, btnExit.y);
        btnSettings.font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);




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
}
