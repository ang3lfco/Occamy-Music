/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;


import interfaces.IUsuarioService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import ui.manager.SessionManager;
import services.UsuarioService;

/**
 *
 * @author martinez
 */
public class pnlBloqueados extends javax.swing.JPanel {
    private IUsuarioService usuarioService;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    
    /**
     * Creates new form pnlFavoritos
     */
    public pnlBloqueados() {
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
    
    public void cargarBloqueados() {
        contentPanel.removeAll();
        cargarNoDeseados();
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    public void cargarNoDeseados() {
        try{
            List<String> generos = usuarioService.getGenerosNoDeseados(SessionManager.getUsuarioActual().getId());
            if (generos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay contenido bloqueado.");
            } 
            else {
                agregarGeneros("Generos", generos);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al cargar los géneros bloqueados: " + e.getMessage(), 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void agregarGeneros(String titulo, List<String> generos) {
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(lblTitulo);

        JPanel pnlLista = new JPanel();
        pnlLista.setLayout(new BoxLayout(pnlLista, BoxLayout.Y_AXIS));
        pnlLista.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlLista.setBackground(Color.WHITE);
        pnlLista.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        for (String genero : generos) {
            JPanel itemPanel = crearPanelBloqueado(genero);
            pnlLista.add(itemPanel);
        }

        contentPanel.add(pnlLista);
    }
    private JPanel crearPanelBloqueado(String genero) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        itemPanel.setBackground(Color.LIGHT_GRAY);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel lblNombre = new JLabel(genero);
        lblNombre.setFont(lblNombre.getFont().deriveFont(Font.PLAIN, 14f));
        lblNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        itemPanel.add(lblNombre, BorderLayout.CENTER);
        itemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    mostrarMenuContextual(itemPanel, genero, e.getX(), e.getY());
                }
            }
        });
        return itemPanel;
    }
    
    private void mostrarMenuContextual(JPanel itemPanel, String genero, int x, int y) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem eliminarItem = new JMenuItem("Eliminar");
        eliminarItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(itemPanel, "Se eliminara el genero: " + genero + " de no deseados");
            usuarioService.eliminarBloqueo(SessionManager.getUsuarioActual().getId(), genero);
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
