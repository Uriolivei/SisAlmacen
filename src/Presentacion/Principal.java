/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;


import Entidades.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
/**
 *
 * @author SENATI
 */
public class Principal extends javax.swing.JFrame {
     private JMenu activeMenu;
     
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblFecha.setText(fecha());
        //setExtendedState(MAXIMIZED_BOTH);//Expandir la ventana al 100%

        //Imagenes
        ImageIcon icon = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/box_1.png"));
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/buy.png"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/sale_2.png"));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/access_1.png"));
        ImageIcon icon4 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/report.png"));
        
        ImageIcon icon11 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/categories.png"));
        ImageIcon icon21 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/product.png"));
        ImageIcon icon31 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/box.png"));
        ImageIcon icon41 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/ingresos.png"));
        ImageIcon icon51 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/client.png"));
        ImageIcon icon61 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/sale.png"));
        ImageIcon icon71 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/rol.png"));
        ImageIcon icon81 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/access_2.png"));
        ImageIcon icon91 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/buy.png"));
        ImageIcon icon101 = new ImageIcon(getClass().getResource("/Presentacion/Imagenes/sale.png"));
        
        
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
        item1.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); // Ctrl+C
        item2.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); // Ctrl+A
        item1.setIcon(icon11);
        item2.setIcon(icon21);
        menu1.setIcon(icon);
        menuBar1.add(menu1);
        setupMenu(menu1, Color.ORANGE);
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
        item3.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); // Ctrl+P
        item4.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); // Ctrl+I
        menuBar2.add(menu2);
        setupMenu(menu2, Color.GREEN);
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
        item5.setAccelerator(KeyStroke.getKeyStroke('L', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); // Ctrl+L
        item6.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); // Ctrl+V
        menuBar3.add(menu3);
        setupMenu(menu3, Color.BLUE);
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
        item7.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); // Ctrl+R
        item8.setAccelerator(KeyStroke.getKeyStroke('U', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); // Ctrl+U
        menuBar4.add(menu4);
        setupMenu(menu4, Color.ORANGE);
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
        item9.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); // Ctrl+O
        item10.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); // Ctrl+N
        menuBar5.add(menu5);
        setupMenu(menu5, Color.GREEN);
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
    
    private void setupMenu(JMenu menu, Color color) {
        menu.setForeground(Color.BLACK);
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menu.setForeground(Color.BLACK); // Color al pasar el ratón
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // No se cambia el color al salir
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Cambiar el color al hacer clic
                if (activeMenu != null) {
                    activeMenu.setForeground(Color.BLACK); // Restablecer el color del menú anterior
                }
                menu.setForeground(color); // Cambiar el color del menú actual a naranja
                activeMenu = menu; // Actualizar el menú activo
            }
        });      
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
        lblFecha = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 51, 0));

        Escritorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 928, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
        );

        Escritorio.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EscritorioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EscritorioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        sidePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 5));

        jPanel1.setBackground(new java.awt.Color(0, 204, 51));

        lblSalir.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblSalir.setForeground(new java.awt.Color(255, 255, 255));
        lblSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/salir.png"))); // NOI18N
        lblSalir.setText("SALIR");
        lblSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalirMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SISTEMA DE INVENTARIO \"LA TIENDITA DE JOHN\"");

        lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFecha.setText("Fecha Actual");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(269, 269, 269)))
                .addComponent(lblSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFecha))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblSalir)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        lblImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("BIENVENIDO");

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre.setText("Señor JOHN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblNombre)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Escritorio)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Escritorio))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    
    public static String fecha(){
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd ' de ' MMMMM ' del ' yyyy");
        return formato.format(fecha);
    }
            
            
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSalir;
    private javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables

}
