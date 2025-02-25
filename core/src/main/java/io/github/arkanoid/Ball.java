package io.github.arkanoid;

public class Ball {
    public float x, y;
    public float vx = 10;
    public float vy = 10;

    public Ball(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void move(){
        x += vx;
        y += vy;
    }
}
