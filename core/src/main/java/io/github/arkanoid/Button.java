package io.github.arkanoid;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Button {
    BitmapFont font;
    String text;
    float x, y;
    float width, height;

    public Button(BitmapFont font, String text, float x, float y) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
        height = glyphLayout.height;
    }

    public boolean hit(float tx, float ty){
        return x<tx && tx<x+width && y>ty && ty>y-height;
    }
    public void setText(String text){
        this.text = text;
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
    }
}
