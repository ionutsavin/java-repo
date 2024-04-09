package org.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My drawing application");
        init();
    }

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel();

        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    public DrawingPanel getCanvas() {
        return canvas;
    }
}

