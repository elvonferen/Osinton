package it.osint.gui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("OSINT Intelligence Tool");
        setSize(600, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(245, 246, 248));
        root.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("OSINT Intelligence Tool");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JLabel subtitle = new JLabel(
                "<html>Open Source Intelligence<br>Person profiling & web discovery</html>");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(Color.DARK_GRAY);

        JButton open = new JButton("Start Investigation");
        open.setFont(new Font("Segoe UI", Font.BOLD, 15));
        open.setBackground(new Color(40, 90, 180));
        open.setForeground(Color.WHITE);
        open.setFocusPainted(false);
        open.setPreferredSize(new Dimension(220, 44));
        open.addActionListener(e -> new WebSearchFrame().setVisible(true));

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        center.add(title);
        center.add(Box.createVerticalStrut(10));
        center.add(subtitle);
        center.add(Box.createVerticalStrut(40));
        center.add(open);

        root.add(center, BorderLayout.CENTER);
        setContentPane(root);
    }
}
