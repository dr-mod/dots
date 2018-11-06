package presentation;

import core.Orchestrator;

import javax.swing.*;

public class Window extends JFrame {


    public Window(Orchestrator orchestrator) {
        add(new Display(orchestrator));

        setResizable(false);
        pack();

        setTitle("AI");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}