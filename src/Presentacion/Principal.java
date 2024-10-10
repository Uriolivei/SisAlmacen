/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author SENATI
 */
public class Principal extends javax.swing.JFrame {
    
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        //setExtendedState(MAXIMIZED_BOTH);//Expandir la ventana al 100%
        
        //Imagenes
        ImageIcon icon = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\box_1.png");
        ImageIcon icon1 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\buy.png");
        ImageIcon icon2 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\sale_2.png");
        ImageIcon icon3 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\access_1.png");
        ImageIcon icon4 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\report.png");
        
        ImageIcon icon11 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\categories.png");
        ImageIcon icon21 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\product.png");
        ImageIcon icon31 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\box.png");
        ImageIcon icon41 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\ingresos.png");
        ImageIcon icon51 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\client.png");
        ImageIcon icon61 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\sale.png");
        ImageIcon icon71 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\rol.png");
        ImageIcon icon81 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\access_2.png");
        ImageIcon icon91 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\buy.png");
        ImageIcon icon101 = new ImageIcon("C:\\Users\\Ruth Riveiro\\OneDrive\\Documents\\OneDrive\\Documentos"
                + "\\NetBeansProjects\\git 2\\SisAlmacen\\src\\Presentacion\\Imagenes\\sale.png");
        
        
        JMenu menu1 = new JMenu("Almacén");
        JMenuItem item1 = new JMenuItem("Categorías");
        JMenuItem item2 = new JMenuItem("Artículos");
        menu1.add(item1);
        menu1.add(item2);
        JMenuBar menuBar1 = new JMenuBar();
        menuBar1.setPreferredSize(new Dimension(155, 55)); 
        Font font = new Font("Arial", Font.BOLD, 15);
        Font menufont = new Font("Arial", Font.BOLD, 13);
        menu1.setFont(font);
        item1.setFont(menufont);
        item2.setFont(menufont);
        item1.setBorder(new EmptyBorder(5, 5, 5, 5)); 
        item2.setBorder(new EmptyBorder(5, 5, 5, 5));
        item1.setIcon(icon11);
        item2.setIcon(icon21);
        menu1.setIcon(icon);
        menuBar1.add(menu1);
        sidePanel.add(menuBar1);
        
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmCategorias frmcategorias = new FrmCategorias();
                frmcategorias.setVisible(true); 

                Escritorio.add(frmcategorias);
                
                frmcategorias.toFront();
            }
        });
        
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmProductos frmproductos = new FrmProductos();
                frmproductos.setVisible(true); 

                Escritorio.add(frmproductos);
                
                frmproductos.toFront();
            }
        });

        JMenu menu2 = new JMenu("Compras");
        JMenuItem item3 = new JMenuItem("Proveedores");
        JMenuItem item4 = new JMenuItem("Ingresos");
        menu2.add(item3);
        menu2.add(item4);
        JMenuBar menuBar2 = new JMenuBar();
        menuBar2.setPreferredSize(new Dimension(155, 55));
        menu2.setFont(font);
        menu2.setIcon(icon1);
        item3.setFont(menufont);
        item4.setFont(menufont);
        item3.setIcon(icon31);
        item4.setIcon(icon41);
        item3.setBorder(new EmptyBorder(5, 5, 5, 5)); 
        item4.setBorder(new EmptyBorder(5, 5, 5, 5));
        menuBar2.add(menu2);
        sidePanel.add(menuBar2);
        
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmProveedores frmproveedores = new FrmProveedores();
                frmproveedores.setVisible(true); 

                Escritorio.add(frmproveedores);
                
                frmproveedores.toFront();
            }
        });
        
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmIngresos frmingresos = new FrmIngresos();
                frmingresos.setVisible(true); 

                Escritorio.add(frmingresos);
                
                frmingresos.toFront();
            }
        });
        
        JMenu menu3 = new JMenu("Ventas");
        JMenuItem item5 = new JMenuItem("Clientes");
        JMenuItem item6 = new JMenuItem("Ventas");
        menu3.add(item5);
        menu3.add(item6);
        JMenuBar menuBar3 = new JMenuBar();
        menuBar3.setPreferredSize(new Dimension(155, 55));
        menu3.setFont(font);
        menu3.setIcon(icon2);
        item5.setFont(menufont);
        item6.setFont(menufont);
        item5.setIcon(icon51);
        item6.setIcon(icon61);
        item5.setBorder(new EmptyBorder(5, 5, 5, 5)); 
        item6.setBorder(new EmptyBorder(5, 5, 5, 5));
        menuBar3.add(menu3);
        sidePanel.add(menuBar3);
        
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmClientes frmclientes = new FrmClientes();
                frmclientes.setVisible(true); 

                Escritorio.add(frmclientes);
                
                frmclientes.toFront();
            }
        });
        
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmVentas frmventas = new FrmVentas();
                frmventas.setVisible(true); 

                Escritorio.add(frmventas);
                
                frmventas.toFront();
            }
        });
        
        JMenu menu4 = new JMenu("Accesos");
        JMenuItem item7 = new JMenuItem("Roles");
        JMenuItem item8 = new JMenuItem("Usuarios");
        menu4.add(item7);
        menu4.add(item8);
        menu4.setIcon(icon3);
        JMenuBar menuBar4 = new JMenuBar();
        menuBar4.setPreferredSize(new Dimension(155, 55));
        menu4.setFont(font);
        item7.setFont(menufont);
        item8.setFont(menufont);
        item7.setIcon(icon71);
        item8.setIcon(icon81);
        item7.setBorder(new EmptyBorder(5, 5, 5, 5)); 
        item8.setBorder(new EmptyBorder(5, 5, 5, 5));
        menuBar4.add(menu4);
        sidePanel.add(menuBar4);
        
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmRoles frmroles = new FrmRoles();
                frmroles.setVisible(true); 

                Escritorio.add(frmroles);
                
                frmroles.toFront();
            }
        });
        
        item8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmUsuarios frmusuarios = new FrmUsuarios();
                frmusuarios.setVisible(true); 

                Escritorio.add(frmusuarios);
                
                frmusuarios.toFront();
            }
        });
        
        JMenu menu5 = new JMenu("Consultas");
        JMenuItem item9 = new JMenuItem("Consultas Compras");
        JMenuItem item10 = new JMenuItem("Consultas Ventas");
        menu5.add(item9);
        menu5.add(item10);
        menu5.setIcon(icon4);
        JMenuBar menuBar5 = new JMenuBar();
        menuBar5.setPreferredSize(new Dimension(155, 55));
        menu5.setFont(font);
        item9.setFont(menufont);
        item10.setFont(menufont);
        item9.setIcon(icon91);
        item10.setIcon(icon101);
        item9.setBorder(new EmptyBorder(5, 5, 5, 5)); 
        item10.setBorder(new EmptyBorder(5, 5, 5, 5));
        menuBar5.add(menu5);
        sidePanel.add(menuBar5);
        
        item9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmConsultasCompras frmconsultascompras = new FrmConsultasCompras();
                frmconsultascompras.setVisible(true); 

                Escritorio.add(frmconsultascompras);
                
                frmconsultascompras.toFront();
            }
        });
        
        item10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmConsultasVentas frmconsultasventas = new FrmConsultasVentas();
                frmconsultasventas.setVisible(true); 

                Escritorio.add(frmconsultasventas);
                 
                frmconsultasventas.toFront();
            }
        });

        getContentPane().add(sidePanel, BorderLayout.WEST);

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Escritorio = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblSalir = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 51, 0));

        Escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Escritorio.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        sidePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 5));

        jPanel1.setBackground(new java.awt.Color(0, 204, 51));

        lblSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/salir.png"))); // NOI18N
        lblSalir.setText("SALIR");
        lblSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalirMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("SISTEMA DE INVENTARIO \"TODO BARATO\"");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(lblSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSalir)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/bien-100.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("BIENVENIDO");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Señor JOHN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(jLabel4))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Escritorio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Escritorio))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalirMouseClicked
        // TODO add your handling code here:
        int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas salir de la Pantalla principal?","Confirmación",
            JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            FrmLogin login = new FrmLogin();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_lblSalirMouseClicked

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblSalir;
    private javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables
}
