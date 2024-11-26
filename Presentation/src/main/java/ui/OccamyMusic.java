/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author martinez
 */
public class OccamyMusic extends javax.swing.JFrame {
    private int xMouse, yMouse;
    private boolean logged  = false;
    /**
     * Creates new form Example
     */
    
    public OccamyMusic() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setSize(710, 520);
        initComponents();
        setLocationRelativeTo(null);
        
        RoundedPanel mainPanel = new RoundedPanel(50, new Color(255, 255, 255));
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
        
        
        pnlLogin loginPanel = new pnlLogin();
        pnlHome homePanel = new pnlHome();
        pnlPerfil profilePanel = new pnlPerfil();
        pnlArtistas artistsPanel = new pnlArtistas();
        pnlAlbumes albumesPanel = new pnlAlbumes();
        pnlGeneros generosPanel = new pnlGeneros();
        pnlFavoritos favPanel = new pnlFavoritos();
        
        contentPanel.add(loginPanel, "Login");
        contentPanel.add(homePanel, "Home");
        contentPanel.add(profilePanel, "Profile");
        contentPanel.add(artistsPanel, "Artists");
        contentPanel.add(albumesPanel, "Albums");
        contentPanel.add(generosPanel, "Genders");
        contentPanel.add(favPanel, "Favoritos");
        
        
        // 4. Agregar listeners a los JLabel para cambiar entre los paneles
        lblHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel("Home");
            }
        });

        lblProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel("Profile");
            }
        });

        lblArtists.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel("Artists");
            }
        });
        
        lblAlbums.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel("Albums");
            }
        });
        
        lblGenders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel("Genders");
            }
        });
        
        lblFav.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchPanel("Favoritos");
            }
        });
    }
    
    public void switchPanel(String panelName) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        if(logged){
            cl.show(contentPanel, panelName);
        }
        else{
            cl.show(contentPanel, "Login");
            javax.swing.JOptionPane.showMessageDialog(this, "Debe iniciar sesion para acceder a esta seccion.", "Acceso denegado", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    /**
     * Panel personalizado con bordes redondeados.
     */
    private static class RoundedPanel extends JPanel {
        private final int cornerRadius;
        private final Color backgroundColor;

        public RoundedPanel(int cornerRadius, Color backgroundColor) {
            this.cornerRadius = cornerRadius;
            this.backgroundColor = backgroundColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Dibujar el fondo con bordes redondeados
            g2.setColor(backgroundColor);
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

            g2.dispose();
        }
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
        btnCerrar = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblArtists = new javax.swing.JLabel();
        lblAlbums = new javax.swing.JLabel();
        lblGenders = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblHome = new javax.swing.JLabel();
        lblFav = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(244, 245, 242));
        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTopBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cerrar.png"))); // NOI18N
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
        });
        pnlTopBar.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minimizar.png"))); // NOI18N
        pnlTopBar.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 6, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/expandir.png"))); // NOI18N
        pnlTopBar.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 6, -1, -1));

        lblProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/perfil.png"))); // NOI18N
        lblProfile.setText("Mi Perfil");
        lblProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlTopBar.add(lblProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 80, 40));

        jPanel1.setBackground(new java.awt.Color(244, 245, 242));

        jTextField1.setBackground(new java.awt.Color(244, 245, 242));
        jTextField1.setText("Buscar...");
        jTextField1.setBorder(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlTopBar.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 300, -1));

        pnlMain.add(pnlTopBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 40));

        lblArtists.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblArtists.setText("Artistas");
        lblArtists.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMain.add(lblArtists, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 194, -1, -1));

        lblAlbums.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAlbums.setText("Álbumes");
        lblAlbums.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMain.add(lblAlbums, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 226, -1, -1));

        lblGenders.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblGenders.setText("Géneros");
        lblGenders.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMain.add(lblGenders, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 258, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Menú de opciones");
        pnlMain.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 156, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Playlists");
        pnlMain.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Rap mexa");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMain.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 358, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("En ingles");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMain.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 386, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Pa' pistear");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMain.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 414, -1, -1));

        lblHome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        lblHome.setText("Inicio");
        lblHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMain.add(lblHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 63, -1, -1));

        lblFav.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFav.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fav.png"))); // NOI18N
        lblFav.setText("Favoritos");
        lblFav.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlMain.add(lblFav, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 95, -1, -1));

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setLayout(new java.awt.CardLayout());
        pnlMain.add(contentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 470, 430));

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

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerrarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OccamyMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OccamyMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OccamyMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OccamyMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new OccamyMusic().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAlbums;
    private javax.swing.JLabel lblArtists;
    private javax.swing.JLabel lblFav;
    private javax.swing.JLabel lblGenders;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTopBar;
    // End of variables declaration//GEN-END:variables
}
