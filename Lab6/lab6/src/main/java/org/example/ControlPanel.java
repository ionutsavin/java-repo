package org.example;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));

        add(loadBtn);
        add(saveBtn);
        add(exitBtn);

        exitBtn.addActionListener(e -> exitGame());
    }

    private void exitGame() {
        frame.dispose();
    }
}

