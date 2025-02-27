package io.github.arkanoid;

import com.badlogic.gdx.graphics.Texture;


public class ButtonManagement {

    public Texture img;
    float x;
    float y;
    float width, height;


    public ButtonManagement(Texture img, float x, float y, float width, float height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }


    public boolean hit(float tx, float ty){
        return x<tx && tx<x+width && y<ty && ty<y+height;
    }


}
