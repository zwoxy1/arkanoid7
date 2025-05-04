package io.github.arkanoid;

import com.badlogic.gdx.graphics.Texture;

public class Enemy {
    public Texture img;
    public float x;
    public float y;
    public float width, height;



    public Enemy(Texture img, float x, float y, float width, float height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean hit(float x, float y, float tx, float ty){
        return x<=tx+100 && tx+100<=x+200 && ty+200>y;

    }

    void leave() {
        x = - 3000;






    }


}
