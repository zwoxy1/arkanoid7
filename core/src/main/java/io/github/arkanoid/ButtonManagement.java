package io.github.arkanoid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class ButtonManagement {

    public Texture img;
    float x;
    float y;
    float width, height;


    public ButtonManagement(Texture img, float x, float y) {
        this.img = img;
        this.x = x;
        this.y = y;


    }


    public boolean hit(float tx, float ty){
        return x<tx && tx<x+width && y>ty && ty>y-height;
    }

    public boolean hit(Vector3 t){
        return x<t.x && t.x<x+width && y>t.y && t.y>y-height;
    }
}
