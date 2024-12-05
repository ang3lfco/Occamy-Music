/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import dtos.AlbumDTO;
import dtos.ArtistaDTO;
import interfaces.IUsuarioService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import org.bson.types.ObjectId;
import ui.manager.SessionManager;
import services.UsuarioService;
/**
 *
 * @author martinez
 */
public class pnlFavoritos extends javax.swing.JPanel {
    private IUsuarioService usuarioService;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    
    /**
     * Creates new form pnlFavoritos
     */
    public pnlFavoritos() {
        this.usuarioService = new UsuarioService();
        initComponents();
        configurarScrollPane();
    }
    private void configurarScrollPane() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(246, 246, 246));

        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }
    
    public void cargarFavoritos() {
        contentPanel.removeAll();
        cargarArtistas();
        cargarAlbumes();
        cargarCanciones();
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    public void cargarArtistas() {
        ObjectId usuarioId = new ObjectId(SessionManager.getUsuarioActual().getId());
        List<ArtistaDTO> artistasCopia = (List<ArtistaDTO>) (List<?>) usuarioService.getFavoritos(usuarioId.toHexString(), "artistas");

        agregarArtistas("Artistas", artistasCopia);
    }

    public void cargarAlbumes() {
        ObjectId usuarioId = new ObjectId(SessionManager.getUsuarioActual().getId());
        List<AlbumDTO> albumesCopia = (List<AlbumDTO>) (List<?>) usuarioService.getFavoritos(usuarioId.toHexString(), "albumes");

        agregarAlbumes("Álbumes", albumesCopia);
    }
    
    public void cargarCanciones() {
        ObjectId usuarioId = new ObjectId(SessionManager.getUsuarioActual().getId());
        List<Map<String, String>> cancionesCopia = (List<Map<String, String>>) (List<?>) usuarioService.getFavoritos(usuarioId.toHexString(), "canciones");

        agregarCanciones("Canciones", cancionesCopia);
    }
    
    public void agregarCanciones(String titulo, List<Map<String, String>> canciones) {
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(lblTitulo);

        JPanel pnlLista = new JPanel();
        pnlLista.setLayout(new BoxLayout(pnlLista, BoxLayout.Y_AXIS));
        pnlLista.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlLista.setBackground(Color.WHITE);
        pnlLista.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        for (Map<String, String> cancion : canciones) {
            JPanel itemPanel = crearPanelFavorito(cancion);
            pnlLista.add(itemPanel);
        }
        contentPanel.add(pnlLista);
    }

    public void agregarArtistas(String titulo, List<ArtistaDTO> artistas) {
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(lblTitulo);

        JPanel pnlLista = new JPanel();
        pnlLista.setLayout(new BoxLayout(pnlLista, BoxLayout.Y_AXIS));
        pnlLista.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlLista.setBackground(Color.WHITE);
        pnlLista.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        for (ArtistaDTO artista : artistas) {
            JPanel itemPanel = crearPanelFavorito(artista);
            pnlLista.add(itemPanel);
        }

        contentPanel.add(pnlLista);
    }

    public void agregarAlbumes(String titulo, List<AlbumDTO> albumes) {
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(lblTitulo);

        JPanel pnlLista = new JPanel();
        pnlLista.setLayout(new BoxLayout(pnlLista, BoxLayout.Y_AXIS));
        pnlLista.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlLista.setBackground(Color.WHITE);
        pnlLista.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        for (AlbumDTO album : albumes) {
            JPanel itemPanel = crearPanelFavorito(album);
            pnlLista.add(itemPanel);
        }
        contentPanel.add(pnlLista);
    }
    
    private JPanel crearPanelFavorito(Map<String, String> cancion) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        itemPanel.setBackground(Color.LIGHT_GRAY);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel lblNombre = new JLabel(obtenerTextoFavorito(cancion));
        lblNombre.setFont(lblNombre.getFont().deriveFont(Font.PLAIN, 14f));
        lblNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        itemPanel.add(lblNombre, BorderLayout.CENTER);
        itemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    mostrarMenuContextual(itemPanel, cancion, e.getX(), e.getY());
                }
            }
        });
        return itemPanel;
    }
    
    private String obtenerTextoFavorito(Map<String, String> cancion) {
        return cancion.get("titulo");
    }

    private JPanel crearPanelFavorito(ArtistaDTO artista) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        itemPanel.setBackground(Color.LIGHT_GRAY);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel lblNombre = new JLabel(obtenerTextoFavorito(artista));
        lblNombre.setFont(lblNombre.getFont().deriveFont(Font.PLAIN, 14f));
        lblNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        itemPanel.add(lblNombre, BorderLayout.CENTER);
        itemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    mostrarMenuContextual(itemPanel, artista, e.getX(), e.getY());
                }
            }
        });
        return itemPanel;
    }

    private JPanel crearPanelFavorito(AlbumDTO album) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        itemPanel.setBackground(Color.LIGHT_GRAY);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel lblNombre = new JLabel(obtenerTextoFavorito(album));
        lblNombre.setFont(lblNombre.getFont().deriveFont(Font.PLAIN, 14f));
        lblNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        itemPanel.add(lblNombre, BorderLayout.CENTER);
        itemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    mostrarMenuContextual(itemPanel, album, e.getX(), e.getY());
                }
            }
        });
        return itemPanel;
    }

    private String obtenerTextoFavorito(ArtistaDTO artista) {
        return artista.getNombre();
    }

    private String obtenerTextoFavorito(AlbumDTO album) {
        return album.getNombre();
    }
    
    private void mostrarMenuContextual(JPanel itemPanel, AlbumDTO favorito, int x, int y) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem eliminarItem = new JMenuItem("Eliminar");
        eliminarItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(itemPanel, "Se eliminara el album: " + favorito.getNombre() + " de favoritos, con ID: " + favorito.getId());
            usuarioService.eliminarDeFavoritos(SessionManager.getUsuarioActual().getId(), "albumes", favorito.getId());
        });
        menu.add(eliminarItem);
        menu.show(itemPanel, x, y);
    }
    
    private void mostrarMenuContextual(JPanel itemPanel, Map<String, String> favorito, int x, int y) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem eliminarItem = new JMenuItem("Eliminar");
        eliminarItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(itemPanel, "Se eliminara la cancion: " + favorito.toString());
            usuarioService.eliminarCancionDeFavoritos(SessionManager.getUsuarioActual().getId(), favorito.get("titulo"), favorito.get("albumId"));
        });
        menu.add(eliminarItem);
        menu.show(itemPanel, x, y);
    }

    private void mostrarMenuContextual(JPanel itemPanel, ArtistaDTO favorito, int x, int y) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem eliminarItem = new JMenuItem("Eliminar");
        eliminarItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(itemPanel, "Se eliminara el artista: " + favorito.getNombre() + " de favoritos, con ID: " + favorito.getId());
            usuarioService.eliminarDeFavoritos(SessionManager.getUsuarioActual().getId(), "artistas", favorito.getId());
        });
        menu.add(eliminarItem);
        menu.show(itemPanel, x, y);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(246, 246, 246));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
