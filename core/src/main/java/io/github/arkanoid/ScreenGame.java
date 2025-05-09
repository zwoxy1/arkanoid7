package io.github.arkanoid;

import static io.github.arkanoid.Main.SCR_HEIGHT;
import static io.github.arkanoid.Main.SCR_WIDTH;
import static io.github.arkanoid.Main.isSound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;



import java.util.ArrayList;
import java.util.List;



public class ScreenGame implements Screen {

    Main main;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;

    int a = 65;
    int b = 8;
    public boolean isGameOver = false;
    private boolean hasWon = false;
    public boolean hasLose = false;

    private boolean showWinText = false;
    private String winText = "Вы выиграли!";

    private boolean showLoseText = false;
    private String loseText = "Вы проиграли!";

    public static Music gameSound;
    public static Sound loseSound;
    public static Sound winSound;


    Texture imgBg;

    Texture imgBall;
    Texture imgPlatform;
    Texture imgEnemy;


    List<Enemy> enemies = new ArrayList<>();


    Texture imgLeft;
    Texture imgRight;


    Button btnBack;
    Button btnBackBig;
    ButtonManagement btnLeft;
    ButtonManagement btnRight;
    Button btnReturn;


    Ball ball;
    Platform platform;


    public ScreenGame(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;

        imgBg = new Texture("bg.png");

        btnBack = new Button(font, "x", 850, 1595);
        btnBackBig = new Button(font, "Вернуться в меню", 5000, 9000);

        btnReturn = new Button(font, "Заново", 300, 900);


        imgLeft = new Texture("playleft.png");
        imgRight = new Texture("playright.png");

        btnLeft = new ButtonManagement(imgLeft, 100, 150, imgLeft.getWidth(), imgLeft.getHeight());
        btnRight = new ButtonManagement(imgRight, 600, 150, imgRight.getWidth(), imgRight.getHeight());

        imgBall = new Texture("ball.png");
        imgPlatform = new Texture("platform.png");
        imgEnemy = new Texture("chebur.png");

        gameSound = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        gameSound.setLooping(true);
        winSound = Gdx.audio.newSound(Gdx.files.internal("winsound.mp3"));
        loseSound = Gdx.audio.newSound(Gdx.files.internal("losesound.mp3"));

        resetGame();
    }

    @Override
    public void show() {
        if (isSound && !gameSound.isPlaying()) {
            gameSound.play();
        }
    }

    public void resetGame(){
        isGameOver = false;
        hasWon = false;
        hasLose = false;
        showWinText = false;
        showLoseText = false;
        a = 65;
        b = 8;



        gameSound.stop();



        // Очистка списка врагов и создание новых
        enemies.clear();
        for (int row = 0; row < 2; row++) {  // Два ряда врагов
            for (int i = 0; i < 4; i++) {    // По 4 врага в каждом ряду
                float yPos = SCR_HEIGHT - imgEnemy.getHeight() - 20 - (row * 200);
                enemies.add(new Enemy(imgEnemy, a, yPos, imgEnemy.getWidth(), imgEnemy.getHeight()));
                a += 200;
            }
            a = 65; // Сбрасываем X-координату для нового ряда
        }

        // Создание новых мяча и платформы
        ball = new Ball(30, 750, imgBall.getWidth(), imgBall.getHeight());
        platform = new Platform(300, 375, 100, imgPlatform.getWidth(), imgPlatform.getHeight());

        // Возврат кнопок на место
        btnLeft.x = 100;
        btnRight.x = 600;
        btnBack.x = 850;
        btnBack.y = 1595;
        btnBack.text = "x";
        btnBackBig.x = 3000;
        btnReturn.x = 3000;



    }

    @Override
    public void render(float delta) {
        ///////
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if (btnBack.hit(touch.x, touch.y)) {
                main.setScreen(main.screenMenu);
                resetGame();

            }
            if (btnLeft.hit(touch.x, touch.y) && platform.x > 0) {
                platform.x -= 45;
            }
            if (btnRight.hit(touch.x, touch.y) && platform.x < SCR_WIDTH - 200) {
                platform.x += 45;
            }
            if (btnBackBig.hit(touch.x, touch.y)){
                main.setScreen(main.screenMenu);
                resetGame();
            }
        }
        ////
        ball.move();
        ////
        if (ball.x > SCR_WIDTH - 200) {
            ball.vx *= -1;
        }
        if (ball.x < 0) {
            ball.vx *= -1;
        }
        if (ball.y > SCR_HEIGHT - 200) {
            ball.vy *= -1;
        }
        /////

        if (ball.y + ball.height < 0){
            lose();
        }


        if (ball.hit(platform.x, platform.y, platform.width, platform.height)) {
            ball.vy *= -1;
        }

        ///////
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(imgBg, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        btnBack.font.draw(batch, btnBack.text, btnBack.x, btnBack.y);

        for (Enemy e : enemies) {
            batch.draw(e.img, e.x, e.y);
        }

        for (Enemy e : enemies) {
            if (e.hit(e.x, e.y, ball.x, ball.y)) {
                e.leave();
                b -= 1;
                ball.vy *= -1;
            }
        }


        batch.draw(imgBall, ball.x, ball.y);
        batch.draw(imgPlatform, platform.x, platform.y);


        batch.draw(btnLeft.img, btnLeft.x, btnLeft.y, btnLeft.width, btnLeft.height);
        batch.draw(btnRight.img, btnRight.x, btnRight.y, btnRight.width, btnRight.height);

        btnBackBig.font.draw(batch, btnBackBig.text, btnBackBig.x, btnBackBig.y);

        if (showWinText) {
            font.draw(batch, winText, 150, 1100);
            btnReturn.font.draw(batch, btnReturn.text, btnReturn.x, btnReturn.y);
            if(btnReturn.hit(touch.x, touch.y)){
                resetGame();
                if(isSound){
                    gameSound.play();
                }
            }
        }

        if (showLoseText){
            font.draw(batch, loseText, 150, 1100);
            btnReturn.font.draw(batch, btnReturn.text, btnReturn.x, btnReturn.y);
            if(btnReturn.hit(touch.x, touch.y)){
                resetGame();
                if(isSound){
                    gameSound.play();
                }
            }
        }

        batch.end();



        if (b == 0 && !hasWon) {
            win();

        }



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
        imgBall.dispose();
        imgPlatform.dispose();
        imgEnemy.dispose();
        imgLeft.dispose();
        imgRight.dispose();
        gameSound.dispose();


    }



    public void win() {
        if (hasWon) return;
        if (showWinText) return;
        hasWon = true;
        showWinText = true;
        ball.stop();
        btnRight.x = 3000;
        btnLeft.x = 3000;
        btnBackBig.x = 50;
        btnBackBig.y = 700;
        btnReturn.x = 300;
        btnBack.x += 100;
        gameSound.stop();

        if (isSound) {
            winSound.play();
        }


    }

    public void lose(){
        if (hasLose) return;
        if(showLoseText) return;
        hasLose = true;
        showLoseText = true;
        ball.stop();
        btnRight.x = 3000;
        btnLeft.x = 3000;
        btnBackBig.x = 50;
        btnBackBig.y = 700;
        btnReturn.x = 300;
        btnBack.x += 100;
        gameSound.stop();
        if(isSound){
            loseSound.play();
        }

    }


}
