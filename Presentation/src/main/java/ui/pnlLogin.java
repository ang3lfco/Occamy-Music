/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import dtos.UsuarioDTO;
import interfaces.IUsuarioService;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import services.UsuarioService;
import ui.ext.CustomRoundedButton;
import ui.ext.CustomRoundedPasswordField;
import ui.ext.CustomRoundedTextField;
import ui.manager.SessionManager;

/**
 *
 * @author martinez
 */
public class pnlLogin extends javax.swing.JPanel {
    private IUsuarioService usuarioService;
    private CustomRoundedTextField emailField;
    private CustomRoundedPasswordField passField;
    /**
     * Creates new form pnlLogin
     */
    public pnlLogin() {
        initComponents();
        usuarioService = new UsuarioService();
        
        // Configurar pnlReemplazar en lugar de pnlPrincipal
        pnlCorreo.setLayout(new FlowLayout()); // Configura el layout del panel a FlowLayout o el que prefieras
        pnlPassword.setLayout(new FlowLayout());
        
        // Crear los CustomRoundedTextField
        emailField = new CustomRoundedTextField("Correo Electrónico", "email.png");
        passField = new CustomRoundedPasswordField("Password", "password.png");

        // Vaciar el panel (opcional, en caso de que ya tenga contenido)
        pnlCorreo.removeAll();
        pnlPassword.removeAll();
        
        pnlCorreo.setBackground(new Color(246,246,246));
        pnlPassword.setBackground(new Color(246,246,246));
        
        // Agregar los campos personalizados al panel
        pnlCorreo.add(emailField);
        pnlPassword.add(passField);
        
        // Actualizar el panel para reflejar los cambios
        pnlCorreo.revalidate();
        pnlCorreo.repaint();
        pnlPassword.revalidate();
        pnlPassword.repaint();
        
        pnlButton.setLayout(new FlowLayout());
        pnlButton.setBackground(new Color(246,246,246));
        CustomRoundedButton btnLogin = new CustomRoundedButton("Iniciar sesión");
        
        btnLogin.addActionListener(e -> {
            String password = new String(passField.getPassword());
            UsuarioDTO usuarioDTO = usuarioService.iniciarSesion(emailField.getText(), password);
            if(usuarioDTO != null){
                SessionManager.setUsuarioActual(usuarioDTO);
                JOptionPane.showMessageDialog(this, "Bienvenido, ahora puedes navegar.", "Acceso verificado", JOptionPane.ERROR_MESSAGE);
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Advertencia, ingresa correctamente.", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
            }
        });
        pnlButton.add(btnLogin);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        pnlCorreo = new javax.swing.JPanel();
        pnlPassword = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlButton = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlPrincipal.setBackground(new java.awt.Color(246, 246, 246));

        pnlCorreo.setBackground(new java.awt.Color(255, 255, 255));
        pnlCorreo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout pnlCorreoLayout = new javax.swing.GroupLayout(pnlCorreo);
        pnlCorreo.setLayout(pnlCorreoLayout);
        pnlCorreoLayout.setHorizontalGroup(
            pnlCorreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        pnlCorreoLayout.setVerticalGroup(
            pnlCorreoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlPassword.setBackground(new java.awt.Color(255, 255, 255));
        pnlPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout pnlPasswordLayout = new javax.swing.GroupLayout(pnlPassword);
        pnlPassword.setLayout(pnlPasswordLayout);
        pnlPasswordLayout.setHorizontalGroup(
            pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        pnlPasswordLayout.setVerticalGroup(
            pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo2.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ingresa para obtener acceso a la app");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("¿Has olvidado la contraseña?");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        pnlButton.setBackground(new java.awt.Color(255, 255, 255));
        pnlButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(pnlCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pnlButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlButtonMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_pnlButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlCorreo;
    private javax.swing.JPanel pnlPassword;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlReemplazar1;
    private javax.swing.JPanel pnlReemplazar2;
    private javax.swing.JPanel pnlReemplazar3;
    // End of variables declaration//GEN-END:variables
}
