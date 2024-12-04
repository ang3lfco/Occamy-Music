/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.ext;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author martinez
 */
public class CustomRoundedPasswordField extends JPanel {
    private JPasswordField passwordField;
    private JLabel iconLabel;
    private String placeholder;

    public CustomRoundedPasswordField(String placeholder, String iconPath) {
        this.placeholder = placeholder;
        setLayout(new BorderLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(300, 40));

        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(JPasswordField.CENTER);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, -23, 5, 10));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.DARK_GRAY);

        passwordField.setText(placeholder);
        passwordField.setForeground(Color.LIGHT_GRAY);
        passwordField.setEchoChar((char) 0);

        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.DARK_GRAY);
                    passwordField.setEchoChar('•');
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(placeholder);
                    passwordField.setForeground(Color.LIGHT_GRAY);
                    passwordField.setEchoChar((char) 0);
                }
            }
        });

        iconLabel = new JLabel(new ImageIcon(iconPath));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        add(iconLabel, BorderLayout.WEST);
        add(passwordField, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(0, 0, 0, 30));
        g2d.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 25, 25);

        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public void setPassword(String password) {
        passwordField.setText(password);
    }
}
