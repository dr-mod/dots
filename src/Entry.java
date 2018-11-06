import core.Orchestrator;
import presentation.Window;

import java.awt.*;

public class Entry {

    public static void main(String[] args) {
        Orchestrator orchestrator = new Orchestrator();
        EventQueue.invokeLater(() -> {
            Window mainWindow = new Window(orchestrator);
            mainWindow.setVisible(true);
        });
    }
}
