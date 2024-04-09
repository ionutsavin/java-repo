package org.example;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinnerRows;
    JSpinner spinnerCols;
    JButton newGameButton;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Grid size:");
        spinnerRows = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        spinnerCols = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));

        newGameButton = new JButton("New Game");

        setLayout(new FlowLayout());

        add(label);
        add(spinnerRows);
        add(spinnerCols);
        add(newGameButton);

            newGameButton.addActionListener(e -> {
                int rows = (int) spinnerRows.getValue();
                int cols = (int) spinnerCols.getValue();
                frame.getCanvas().init(rows, cols);
                frame.getCanvas().repaint();
            });
    }

    public void updateSpinners(int rows, int cols) {
        spinnerRows.setValue(rows);
        spinnerCols.setValue(cols);
    }
}
