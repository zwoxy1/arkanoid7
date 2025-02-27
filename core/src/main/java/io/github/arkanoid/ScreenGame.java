package io.github.arkanoid;

import static io.github.arkanoid.Main.SCR_HEIGHT;
import static io.github.arkanoid.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenGame implements Screen {

    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;



    Texture imgBg;

    Texture imgBall;
    Texture imgPlatform;

    Texture imgLeft;
    Texture imgRight;


    Button btnBack;
    ButtonManagement btnLeft;
    ButtonManagement btnRight;

    Ball ball;
    Platform platform;

    public ScreenGame(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;

        imgBg = new Texture("bg.png");

        btnBack = new Button(font, "x", 830, 1580);

        imgLeft = new Texture("playleft.png");
        imgRight = new Texture("playright.png");

        btnLeft = new ButtonManagement(imgLeft, 100, 200, imgLeft.getWidth(), imgLeft.getHeight());
        btnRight = new ButtonManagement(imgRight, 600, 200, imgRight.getWidth(), imgRight.getHeight());

        imgBall = new Texture("ball.png");
        imgPlatform = new Texture("platform.png");

        ball = new Ball(300, 300, imgBall.getWidth(), imgBall.getHeight());
        platform = new Platform(200, 450, 100, imgPlatform.getWidth(), imgPlatform.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ///////
        if(Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if (btnBack.hit(touch.x, touch.y)) {
                main.setScreen(main.screenMenu);
            }

            if (btnLeft.hit(touch.x, touch.y) && platform.x > 0 ) {
                platform.x-=45;

            }

            if (btnRight.hit(touch.x, touch.y) && platform.x < SCR_WIDTH-200){
                platform.x+=45;

            }

        }



        ball.move();

        if (ball.x > SCR_WIDTH-200) {
            ball.vx *=-1;
        }
        if (ball.x < 0){
            ball.vx *=-1;
        }
        if (ball.y>SCR_HEIGHT-200){
            ball.vy*=-1;
        }
        /////
        if (ball.y < 0){
            ball.vy*=-1;
        }

        //////




        if (ball.hit(platform.x, platform.y, platform.width, platform.height)){
            ball.vy*=-1;
        }












        ///////
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(imgBg, 0, 0, SCR_WIDTH, SCR_HEIGHT);

        //font.draw(batch, "Игра", 350, 1300);

        btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);


        batch.draw(imgBall, ball.x, ball.y);
        batch.draw(imgPlatform,platform.x, platform.y);

        batch.draw(btnLeft.img, btnLeft.x, btnLeft.y, btnLeft.width, btnLeft.height);
        //batch.draw(btnLeft.img, btnLeft.x, btnLeft.y);
        batch.draw(btnRight.img, btnRight.x, btnRight.y, btnRight.width,btnRight.height);






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
