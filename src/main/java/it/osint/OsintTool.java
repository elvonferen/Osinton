package it.osint;

import it.osint.gui.MainMenu;
import javax.swing.SwingUtilities;

public class OsintTool {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}
