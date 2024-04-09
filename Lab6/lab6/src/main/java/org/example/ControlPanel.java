package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton exitBtn = new JButton("Exit");
    JButton exportBtn = new JButton("Export");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 5));

        add(loadBtn);
        add(saveBtn);
        add(exportBtn);
        add(exitBtn);

        loadBtn.addActionListener(e -> loadGameState());
        saveBtn.addActionListener(e -> saveGameState());
        exportBtn.addActionListener(e -> exportImage());
        exitBtn.addActionListener(e -> exitGame());
    }

    public void loadGameState() {
        String fileName = JOptionPane.showInputDialog(frame, "Enter the file name to load game state from:");
        if (fileName != null) {
            GameState gameState = GameState.loadFromFile(fileName);
            if (gameState != null) {
                frame.getCanvas().updateGameState(gameState);
                frame.getConfigPanel().updateSpinners(gameState.getRows(), gameState.getCols());
            }
        }
    }


    private void saveGameState() {
        String fileName = JOptionPane.showInputDialog(frame, "Enter the file name to save game state to:");
        if (fileName != null) {
            frame.getCanvas().saveGameState(fileName);
        }
    }
    private void exportImage() {
        BufferedImage image = new BufferedImage(frame.getCanvas().getWidth(), frame.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        frame.getCanvas().paintComponent(g2d);
        g2d.dispose();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export Image");
        int userSelection = fileChooser.showSaveDialog(frame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            if (!filePath.toLowerCase().endsWith(".png")) {
                filePath += ".png";
            }

            try {
                ImageIO.write(image, "PNG", new File(filePath));
                JOptionPane.showMessageDialog(frame, "Image exported successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error exporting image: " + ex.getMessage());
            }
        }
    }

    private void exitGame() {
        frame.dispose();
    }
}
