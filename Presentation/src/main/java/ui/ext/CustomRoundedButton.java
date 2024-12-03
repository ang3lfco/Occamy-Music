/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.ext;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 *
 * @author martinez
 */
public class CustomRoundedButton extends JButton {

    public CustomRoundedButton(String text) {
        super(text);
        setOpaque(false); // Permitirá dibujar el fondo manualmente
        setContentAreaFilled(false); // Evita el fondo predeterminado del botón
        setFocusPainted(false); // Elimina el borde de enfoque predeterminado
        setBorderPainted(false); // Evita bordes estándar
        setForeground(Color.WHITE); // Texto blanco
        setFont(new Font("SansSerif", Font.BOLD, 14)); // Fuente personalizada
        setPreferredSize(new Dimension(135, 26)); // Tamaño estándar
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo del botón
        g2d.setColor(new Color(64, 64, 64)); // Gris oscuro
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Dibuja el texto del botón
        super.paintComponent(g);
    }
}