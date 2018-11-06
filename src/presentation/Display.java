package presentation;

import configuration.ConfigHolder;
import core.Orchestrator;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JPanel implements ActionListener {

    private Orchestrator orchestrator;


    public Display(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;

        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(
                ConfigHolder.getInstance().getAreaWidth(),
                ConfigHolder.getInstance().getAreaHeight()));
        new Timer(ConfigHolder.getInstance().getRefresh(), this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        orchestrator.showDispatcher(new GraphicsAwt((Graphics2D) g));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        orchestrator.nextIteration();
        repaint();
    }
}