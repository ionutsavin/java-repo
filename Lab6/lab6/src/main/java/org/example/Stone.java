package org.example;

import java.awt.*;
import java.io.Serializable;

public class Stone implements Serializable {
    private final int x;
    private final int y;
    private final Color color;
    int stoneSize = 20;

    public Stone(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
    }
}
