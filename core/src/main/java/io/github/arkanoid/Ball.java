package io.github.arkanoid;

public class Ball {
    public float x, y;
    public float vx = 10;
    public float vy = 10;
    public float width;
    public float height;


    public Ball(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void move() {
        x += vx;
        y += vy;
    }


    public boolean hit(float tx, float ty, float twidth, float theight){
        return x>=tx+theight && tx<x+twidth && y<ty+theight && ty<y+theight;
    }
}
