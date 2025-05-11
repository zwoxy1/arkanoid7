package io.github.arkanoid;

public class Ball {
    public float x, y;
    public float vx = 7;
    public float vy = -7;
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

    public void stop(){
        vx = 0;
        vy = 0;

    }


    public boolean hit(float tx, float ty, float twidth, float theight){
        return tx+twidth+10>=x+(width/2) && x+(width/2)>=tx-10 && y<ty+theight && ty<y+theight;
    }
}
