package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends JPanel {
    int rows, cols;
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;
    private List<Line2D> gridLines;
    private List<Stick> sticks;
    private boolean isPlayer1Turn;
    private int prevSelectedX;
    private int prevSelectedY;
    private boolean[][] coloredOvals;

    public DrawingPanel() {
        init(10, 10);
    }

    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;
        this.coloredOvals = new boolean[rows][cols];
        this.prevSelectedX = -1;
        this.prevSelectedY = -1;
        this.isPlayer1Turn = true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.coloredOvals[i][j] = false;
            }
        }
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        initGridLines();
        generateRandomSticks();
        MouseListener[] mouseListeners = getMouseListeners();
        for (MouseListener listener : mouseListeners) {
            removeMouseListener(listener);
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int X = (e.getX() - padX) / cellWidth;
                int Y = (e.getY() - padY) / cellHeight;

                if (isValidMove(X, Y)) {
                    drawStone(X, Y);
                    prevSelectedX = X;
                    prevSelectedY = Y;
                    if(isGameOver()){
                        if(isPlayer1Turn){
                            JOptionPane.showMessageDialog(getTopLevelAncestor(), "Game Over! Player 1 Wins!");
                        }else{
                            JOptionPane.showMessageDialog(getTopLevelAncestor(), "Game Over! Player 2 Wins!");
                        }
                    }
                    isPlayer1Turn = !isPlayer1Turn;
                } else{
                    if(!isGameOver()){
                        JOptionPane.showMessageDialog(getTopLevelAncestor(), "Invalid move! Please select another node.");
                    }
                }
            }
        });
    }

    private void initGridLines() {
        gridLines = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            gridLines.add(new Line2D.Double(x1, y1, x2, y1));
        }
        for (int col = 0; col < cols; col++) {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int y2 = padY + boardHeight;
            gridLines.add(new Line2D.Double(x1, y1, x1, y2));
        }
    }

    private void generateRandomSticks() {
        sticks = new ArrayList<>();
        Random random = new Random();
        for (int row = 0; row < rows - 1; row++) {
            for (int col = 0; col < cols - 1; col++) {
                boolean addHorizontal = random.nextBoolean();
                boolean addVertical = random.nextBoolean();
                if (addHorizontal) {
                    int x1 = padX + col * cellWidth;
                    int y1 = padY + (row + 1) * cellHeight;
                    int x2 = padX + (col + 1) * cellWidth;
                    sticks.add(new Stick(x1, y1, x2, y1));
                }
                if (addVertical) {
                    int x1 = padX + (col + 1) * cellWidth;
                    int y1 = padY + row * cellHeight;
                    int y2 = padY + (row + 1) * cellHeight;
                    sticks.add(new Stick(x1, y1, x1, y2));
                }
            }
        }
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
    }

    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        for (Line2D line : gridLines) {
            g.draw(line);
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
        for (Stick stick : sticks) {
            stick.draw(g);
            int x1 = (stick.getX1() - padX) / cellWidth;
            int y1 = (stick.getY1() - padY) / cellHeight;
            int x2 = (stick.getX2() - padX) / cellWidth;
            int y2 = (stick.getY2() - padY) / cellHeight;
            System.out.println(x1 + " " + y1 + " "+ x2 + " " + y2);
        }
    }

    private boolean isValidMove(int X, int Y) {
        if (prevSelectedX == X && prevSelectedY == Y) {
            return false;
        }
        if (prevSelectedX == -1 && prevSelectedY == -1) {
            for (Stick stick : sticks) {
                int x1 = (stick.getX1() - padX) / cellWidth;
                int y1 = (stick.getY1() - padY) / cellHeight;
                int x2 = (stick.getX2() - padX) / cellWidth;
                int y2 = (stick.getY2() - padY) / cellHeight;

                if ((X == x1 && Y == y1) || (X == x2 && Y == y2)) {
                    return true;
                }
            }
            return false;
        }
        for (Stick stick : sticks) {
            int x1 = (stick.getX1() - padX) / cellWidth;
            int y1 = (stick.getY1() - padY) / cellHeight;
            int x2 = (stick.getX2() - padX) / cellWidth;
            int y2 = (stick.getY2() - padY) / cellHeight;

            if ((X == x1 && Y == y1 && prevSelectedX == x2 && prevSelectedY == y2)
                    || (X == x2 && Y == y2 && prevSelectedX == x1 && prevSelectedY == y1)) {
                return true;
            }
        }
        return false;
    }


    private boolean isGameOver() {
        if (prevSelectedX == -1 && prevSelectedY == -1) {
            return false;
        }
        for (Stick stick : sticks) {
            int x1 = (stick.getX1() - padX) / cellWidth;
            int y1 = (stick.getY1() - padY) / cellHeight;
            int x2 = (stick.getX2() - padX) / cellWidth;
            int y2 = (stick.getY2() - padY) / cellHeight;

            if ((!coloredOvals[y1][x1] && prevSelectedX == x2 && prevSelectedY == y2)
                    || (!coloredOvals[y2][x2] && prevSelectedX == x1 && prevSelectedY == y1)) {
                return false;
            }
        }
        return true;
    }

    private void drawStone(int X, int Y) {
        Graphics2D g = (Graphics2D) getGraphics();
        if (isPlayer1Turn) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLUE);
        }
        coloredOvals[Y][X] = true;
        int x = padX + X * cellWidth;
        int y = padY + Y * cellHeight;
        g.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
    }
}
