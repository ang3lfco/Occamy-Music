/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import dtos.AlbumDTO;
import interfaces.IAlbumService;
import interfaces.IUsuarioService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;
import services.AlbumService;
import services.UsuarioService;
import ui.manager.SessionManager;

/**
 *
 * @author martinez
 */
public class pnlCanciones extends javax.swing.JPanel {
    private IAlbumService as;
    private IUsuarioService us;
    private List<AlbumDTO> albumesDTO = new ArrayList<>();
    /**
     * Creates new form pnlArtistas
     */
    public pnlCanciones() {
        initComponents();
        this.as = new AlbumService();
        albumesDTO = as.obtenerAlbumes();

        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(30);
        jScrollPane1.setBackground(Color.WHITE);

        JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
        JScrollBar horizontal = jScrollPane1.getHorizontalScrollBar();

        vertical.setUI(new CustomScrollBarUI());
        horizontal.setUI(new CustomScrollBarUI());

        panelAlbumes.setBorder(null);
        jScrollPane1.setBorder(null);
        this.setBackground(new Color(246,246,246));

//        panelAlbumes.setLayout(new GridLayout((int) Math.ceil(albumesDTO.size() / 4.0), 4, 0, 0));
        panelAlbumes.setLayout(new GridLayout(0, 3, 0, 0)); // Layout dinámico
        panelAlbumes.setBackground(new Color(246,246,246));

        for (AlbumDTO album : albumesDTO) {
            for (String cancion : album.getCanciones()) {
                JPanel albumPane = createAlbumPanel(cancion, album);
                panelAlbumes.add(albumPane);
            }
        }
        panelAlbumes.revalidate();
        panelAlbumes.repaint();

//        panelAlbumes.setPreferredSize(new Dimension(380, (int) (Math.ceil(albumesDTO.size() / 4.0) * 150)));
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setViewportView(panelAlbumes);
        add(jScrollPane1, BorderLayout.CENTER);
    }

    class CustomScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(100, 100, 100);
            this.trackColor = new Color(230, 230, 250);
            this.thumbDarkShadowColor = new Color(80, 80, 80);
            this.thumbLightShadowColor = new Color(150, 150, 150);
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            JButton button = super.createDecreaseButton(orientation);
            button.setBackground(new Color(255, 255, 255));
            return button;
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            JButton button = super.createIncreaseButton(orientation);
            button.setBackground(new Color(255, 255, 255));
            return button;
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            g.setColor(thumbColor);
            g.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.setColor(trackColor);
            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        }
    }

    private JPanel createAlbumPanel(String cancion, AlbumDTO album) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(120, 160));
        panel.setBackground(new Color(246,246,246));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(null); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblImagen = new JLabel();
        lblImagen.setIcon(new ImageIcon("portada.png"));
        lblImagen.setHorizontalAlignment(JLabel.LEFT);
        lblImagen.setAlignmentX(Component.LEFT_ALIGNMENT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblImagen, gbc);

        int imagenAncho = lblImagen.getPreferredSize().width;

        JLabel lblNombre = new JLabel(cancion);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setHorizontalAlignment(JLabel.LEFT);
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblNombre.setPreferredSize(new Dimension(imagenAncho, lblNombre.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblNombre, gbc);

        JLabel lblTipo = new JLabel(album.getNombre());
        lblTipo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblTipo.setHorizontalAlignment(JLabel.LEFT);
        lblTipo.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblTipo.setPreferredSize(new Dimension(imagenAncho, lblTipo.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblTipo, gbc);

        // Crear el menú emergente
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem itemFavoritos = new JMenuItem("Agregar a favoritos");
        JMenuItem itemNoDeseados = new JMenuItem("Agregar a no deseados");
        popupMenu.add(itemFavoritos);
        popupMenu.add(itemNoDeseados);

        // Agregar acciones a las opciones del menú
        itemFavoritos.addActionListener(e -> {
            System.out.println("Cancion " + cancion + " agregado a favoritos.");
            us = new UsuarioService();
            us.agregarCancionAFavoritos(SessionManager.getUsuarioActual().getId(), cancion, album.getId());
        });
        itemNoDeseados.addActionListener(e -> {
            System.out.println("Cancion " + cancion + " agregado a no deseados.");
        });

        // Agregar el listener para detectar clic derecho
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) { // Botón derecho del mouse
                    // Muestra el menú contextual en la posición del clic derecho
                    popupMenu.show(panel, e.getX(), e.getY());
                } else if (e.getButton() == MouseEvent.BUTTON1) { // Botón izquierdo del mouse
                    // Acción para clic izquierdo
                    System.out.println("Cancion seleccionada: " + cancion);
                }
            }
        });
        
        return panel;
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panelAlbumes = new javax.swing.JPanel();
        lblAlbumes = new javax.swing.JLabel();

        setBackground(new java.awt.Color(246, 246, 246));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        panelAlbumes.setBackground(new java.awt.Color(246, 246, 246));
        jScrollPane1.setViewportView(panelAlbumes);

        lblAlbumes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAlbumes.setText("Canciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlbumes)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblAlbumes)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlbumes;
    private javax.swing.JPanel panelAlbumes;
    // End of variables declaration//GEN-END:variables
}
