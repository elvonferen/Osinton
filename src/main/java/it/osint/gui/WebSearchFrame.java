package it.osint.gui;

import it.osint.model.PersonProfile;
import it.osint.web.UnifiedSearchEngine;

import javax.swing.*;
import java.awt.*;

public class WebSearchFrame extends JFrame {

    private JTextField serpKey;
    private JTextField name;
    private JTextField surname;
    private JTextField username;
    private JTextField email;
    private JTextField phone;
    private JTextArea report;

    public WebSearchFrame() {
        setTitle("OSINT Investigation Console");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(buildSidebar(), BorderLayout.WEST);
        add(buildMainPanel(), BorderLayout.CENTER);
    }

    private JPanel buildSidebar() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 800));
        panel.setBackground(new Color(34, 37, 42));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel header = new JLabel("Search Parameters");
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));

        serpKey = darkInput("SERP API Key");
        name = darkInput("First Name");
        surname = darkInput("Last Name");
        username = darkInput("Username");
        email = darkInput("Email");
        phone = darkInput("Phone Number");

        JButton run = new JButton("Run OSINT Scan");
        run.setFont(new Font("Segoe UI", Font.BOLD, 14));
        run.setBackground(new Color(40, 90, 180));
        run.setForeground(Color.WHITE);
        run.setFocusPainted(false);
        run.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        run.addActionListener(e -> executeSearch());

        panel.add(header);
        panel.add(Box.createVerticalStrut(20));
        panel.add(serpKey);
        panel.add(Box.createVerticalStrut(10));
        panel.add(name);
        panel.add(Box.createVerticalStrut(10));
        panel.add(surname);
        panel.add(Box.createVerticalStrut(10));
        panel.add(username);
        panel.add(Box.createVerticalStrut(10));
        panel.add(email);
        panel.add(Box.createVerticalStrut(10));
        panel.add(phone);
        panel.add(Box.createVerticalStrut(25));
        panel.add(run);

        return panel;
    }

    private JScrollPane buildMainPanel() {
        report = new JTextArea();
        report.setEditable(false);
        report.setFont(new Font("Consolas", Font.PLAIN, 13));
        report.setMargin(new Insets(16, 16, 16, 16));
        report.setBackground(new Color(250, 250, 250));

        JScrollPane scroll = new JScrollPane(report);
        scroll.setBorder(BorderFactory.createTitledBorder("Investigation Report"));
        return scroll;
    }

    private JTextField darkInput(String label) {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBackground(new Color(55, 59, 65));
        field.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                label,
                0,
                0,
                new Font("Segoe UI", Font.PLAIN, 12),
                Color.LIGHT_GRAY
        ));
        return field;
    }

    private void executeSearch() {
        report.setText("Running OSINT investigation...\n\n");

        if (serpKey.getText().isBlank()) {
            report.setText("SERP API Key is required.");
            return;
        }

        try {
            UnifiedSearchEngine engine =
                    new UnifiedSearchEngine(serpKey.getText().trim());

            PersonProfile profile = engine.searchByFields(
                    name.getText().trim(),
                    surname.getText().trim(),
                    username.getText().trim(),
                    email.getText().trim(),
                    phone.getText().trim()
            );

            report.setText(profile.toPrettyString());

        } catch (Exception ex) {
            report.setText("Error:\n" + ex.getMessage());
        }
    }
}
