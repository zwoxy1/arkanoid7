package io.github.arkanoid;

public class Platform {
    public float x, y;
    public float vx;



    public Platform(float x, float y, float vx) {
        this.x = x;
        this.y = y;
        this.vx = vx;
    }

    public void move(){
        x += vx;
    }


}
