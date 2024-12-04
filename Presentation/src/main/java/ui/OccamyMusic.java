/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import ui.ext.RoundedPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import services.AlbumService;
import services.ArtistaService;
import services.precarga.Precargador;
import ui.manager.SessionManager;
/**
 *
 * @author martinez
 */
public class OccamyMusic extends javax.swing.JFrame {
    private int xMouse, yMouse;
    
    pnlLogin loginPanel = new pnlLogin();
    pnlHome homePanel = new pnlHome();
    pnlPerfil profilePanel = new pnlPerfil();
    pnlArtistas artistasPanel = new pnlArtistas();
    pnlAlbumes albumesPanel = new pnlAlbumes();
    pnlCanciones cancionesPanel = new pnlCanciones();
    pnlFavoritos favoritosPanel = new pnlFavoritos();
    pnlBloqueados bloqueadosPanel = new pnlBloqueados();
    
    /**
     * Creates new form Example
     */
    public OccamyMusic() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setSize(710, 520);
        initComponents();
        setLocationRelativeTo(null);
        
        RoundedPanel mainPanel = new RoundedPanel(50, new Color(246,246,246));
        mainPanel.setOpaque(false);
        setContentPane(mainPanel);

        // Añadir un layout para respetar margenes internos
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(layout);

        // Ajustar márgenes para evitar que los componentes cubran los bordes redondeados
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10) // Márgenes izquierdos
                    .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addGap(10)) // Márgenes derechos
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10) // Márgenes superiores
                    .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addGap(10)) // Márgenes inferiores
        );

        pnlMain.setOpaque(false);
        pnlTopBar.setOpaque(false);

        pnlTopBar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xMouse = evt.getX();
                yMouse = evt.getY();
            }
        });

        pnlTopBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                setLocation(evt.getXOnScreen() - xMouse, evt.getYOnScreen() - yMouse);
            }
        });
        
        contentPanel.add(loginPanel, "Login");
        contentPanel.add(homePanel, "Home");
        contentPanel.add(profilePanel, "Perfil");
        contentPanel.add(artistasPanel, "Artistas");
        contentPanel.add(albumesPanel, "Albumes");
        contentPanel.add(cancionesPanel, "Canciones");
        contentPanel.add(favoritosPanel, "Favoritos");
        contentPanel.add(bloqueadosPanel, "Bloqueados");
        
        
        //Agregar listeners a los JLabel para cambiar entre los paneles
        lblHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SessionManager.isLogueado()){
                    switchPanel("Home");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        lblProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SessionManager.isLogueado()){
                    switchPanel("Perfil");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        lblArtistas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SessionManager.isLogueado()){
                    switchPanel("Artistas");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        lblAlbumes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SessionManager.isLogueado()){
                    switchPanel("Albumes");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        lblCanciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SessionManager.isLogueado()){
                    switchPanel("Canciones");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        lblFavoritos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SessionManager.isLogueado()){
                    switchPanel("Favoritos");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        lblBloqueados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SessionManager.isLogueado()){
                    switchPanel("Bloqueados");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    
    public void switchPanel(String panelName) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, panelName);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlTopBar = new javax.swing.JPanel();
        lblCerrar = new javax.swing.JLabel();
        lblMinimizar = new javax.swing.JLabel();
        lblMaximizar = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        pnlBuscarContenedor = new javax.swing.JPanel();
        txfBuscador = new javax.swing.JTextField();
        lblBuscarIcon = new javax.swing.JLabel();
        lblArtistas = new javax.swing.JLabel();
        lblAlbumes = new javax.swing.JLabel();
        lblCanciones = new javax.swing.JLabel();
        lblMenuOpciones = new javax.swing.JLabel();
        lblMenuAcciones = new javax.swing.JLabel();
        lblInsertarArtistas = new javax.swing.JLabel();
        lblRegistrarme = new javax.swing.JLabel();
        lblAccion3 = new javax.swing.JLabel();
        lblHome = new javax.swing.JLabel();
        lblBloqueados = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        lblFavoritos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(244, 245, 242));
        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTopBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cerrar.png"))); // NOI18N
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });
        pnlTopBar.add(lblCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        lblMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minimizar.png"))); // NOI18N
        lblMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizarMouseClicked(evt);
            }
        });
        pnlTopBar.add(lblMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 6, -1, -1));

        lblMaximizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/expandir.png"))); // NOI18N
        lblMaximizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMaximizarMouseClicked(evt);
            }
        });
        pnlTopBar.add(lblMaximizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 6, -1, -1));

        lblProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/perfil.png"))); // NOI18N
        lblProfile.setText("Mi Perfil");
        lblProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProfileMouseClicked(evt);
            }
        });
        pnlTopBar.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 80, 40));

        pnlBuscarContenedor.setBackground(new java.awt.Color(244, 245, 242));

        txfBuscador.setBackground(new java.awt.Color(244, 245, 242));
        txfBuscador.setText("Buscar...");
        txfBuscador.setBorder(null);

        lblBuscarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N

        javax.swing.GroupLayout pnlBuscarContenedorLayout = new javax.swing.GroupLayout(pnlBuscarContenedor);
        pnlBuscarContenedor.setLayout(pnlBuscarContenedorLayout);
        pnlBuscarContenedorLayout.setHorizontalGroup(
            pnlBuscarContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscarContenedorLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblBuscarIcon)
                .addGap(14, 14, 14)
                .addComponent(txfBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        pnlBuscarContenedorLayout.setVerticalGroup(
            pnlBuscarContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBuscarContenedorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlBuscarContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBuscarIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlTopBar.add(pnlBuscarContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 300, -1));

        pnlMain.add(pnlTopBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 40));

        lblArtistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblArtistas.setText("Artistas");
        lblArtistas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblArtistas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblArtistasMouseClicked(evt);
            }
        });
        pnlMain.add(lblArtistas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        lblAlbumes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAlbumes.setText("Álbumes");
        lblAlbumes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAlbumes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAlbumesMouseClicked(evt);
            }
        });
        pnlMain.add(lblAlbumes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        lblCanciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCanciones.setText("Canciones");
        lblCanciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCanciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCancionesMouseClicked(evt);
            }
        });
        pnlMain.add(lblCanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        lblMenuOpciones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMenuOpciones.setText("Navegación");
        pnlMain.add(lblMenuOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        lblMenuAcciones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMenuAcciones.setText("Acciones");
        pnlMain.add(lblMenuAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        lblInsertarArtistas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblInsertarArtistas.setText("Inserción masiva");
        lblInsertarArtistas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblInsertarArtistas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInsertarArtistasMouseClicked(evt);
            }
        });
        pnlMain.add(lblInsertarArtistas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        lblRegistrarme.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblRegistrarme.setText("Registrarme");
        lblRegistrarme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRegistrarme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegistrarmeMouseClicked(evt);
            }
        });
        pnlMain.add(lblRegistrarme, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        lblAccion3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAccion3.setText("Cambiar apariencia");
        lblAccion3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAccion3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAccion3MouseClicked(evt);
            }
        });
        pnlMain.add(lblAccion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        lblHome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        lblHome.setText("Inicio");
        lblHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMain.add(lblHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        lblBloqueados.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBloqueados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ban.png"))); // NOI18N
        lblBloqueados.setText("No deseados");
        lblBloqueados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMain.add(lblBloqueados, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setLayout(new java.awt.CardLayout());
        pnlMain.add(contentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 470, 430));

        lblFavoritos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFavoritos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fav.png"))); // NOI18N
        lblFavoritos.setText("Favoritos");
        lblFavoritos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblFavoritos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFavoritosMouseClicked(evt);
            }
        });
        pnlMain.add(lblFavoritos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void lblInsertarArtistasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInsertarArtistasMouseClicked
        // TODO add your handling code here:
        ArtistaService artistaService = new ArtistaService();
        AlbumService albumService = new AlbumService();
        try {
            artistaService.autoInsertarDatos();
            albumService.autoInsertarDatos();
            javax.swing.JOptionPane.showMessageDialog(this, "Inserción masiva de artistas y albumes realizada con éxito.", "Completado", javax.swing.JOptionPane.OK_OPTION);
        } catch (ParseException ex) {
            Logger.getLogger(OccamyMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblInsertarArtistasMouseClicked

    private void lblArtistasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArtistasMouseClicked
        // TODO add your handling code here:
        if(SessionManager.isLogueado()){
            pnlArtistas artistasPanel = new pnlArtistas();
            contentPanel.add(artistasPanel, "Artistas");
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "Artistas");
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_lblArtistasMouseClicked

    private void lblRegistrarmeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistrarmeMouseClicked
        // TODO add your handling code here:
        frmDatos registro = new frmDatos();
        registro.setVisible(true);
    }//GEN-LAST:event_lblRegistrarmeMouseClicked

    private void lblMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizarMouseClicked
        // TODO add your handling code here:
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimizarMouseClicked

    private void lblMaximizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaximizarMouseClicked
        // TODO add your handling code here:
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_lblMaximizarMouseClicked

    private void lblAccion3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAccion3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblAccion3MouseClicked

    private void lblCancionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCancionesMouseClicked
        // TODO add your handling code here:
        if(SessionManager.isLogueado()){
            pnlCanciones cancionesPanel = new pnlCanciones();
            contentPanel.add(cancionesPanel, "Canciones");
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "Canciones");
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_lblCancionesMouseClicked

    private void lblAlbumesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAlbumesMouseClicked
        // TODO add your handling code here:
        if(SessionManager.isLogueado()){
            pnlAlbumes albumesPanel = new pnlAlbumes();
            contentPanel.add(albumesPanel, "Albumes");
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "Albumes");
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_lblAlbumesMouseClicked

    private void lblFavoritosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFavoritosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblFavoritosMouseClicked

    private void lblProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProfileMouseClicked
        // TODO add your handling code here:
        if(SessionManager.isLogueado()){
            pnlPerfil profilePanel = new pnlPerfil();
            contentPanel.add(profilePanel, "Perfil");
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "Perfil");
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_lblProfileMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(OccamyMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(OccamyMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(OccamyMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(OccamyMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> {
//            new OccamyMusic().setVisible(true);
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel lblAccion3;
    private javax.swing.JLabel lblAlbumes;
    private javax.swing.JLabel lblArtistas;
    private javax.swing.JLabel lblBloqueados;
    private javax.swing.JLabel lblBuscarIcon;
    private javax.swing.JLabel lblCanciones;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblFavoritos;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblInsertarArtistas;
    private javax.swing.JLabel lblMaximizar;
    private javax.swing.JLabel lblMenuAcciones;
    private javax.swing.JLabel lblMenuOpciones;
    private javax.swing.JLabel lblMinimizar;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblRegistrarme;
    private javax.swing.JPanel pnlBuscarContenedor;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTopBar;
    private javax.swing.JTextField txfBuscador;
    // End of variables declaration//GEN-END:variables
}
