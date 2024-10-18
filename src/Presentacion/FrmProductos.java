package Presentacion;

import Entidades.Categoria;
import Negocio.ProductoControl;
import Datos.ProductoDAO;
import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author SENATI
 */
public class FrmProductos extends javax.swing.JInternalFrame {
    private final ProductoControl CONTROL;
    private String accion;
    private String nombreAnt;
    
    private String rutaOrigen;
    private String rutaDestino;
    private final String DIRECTORIO = "src/files/productos/";
    private String imagen = "";
    private String imagenAnt;
    
    private int totalPorPagina = 10;
    private int numPagina = 1;
    private boolean primeraCarga = true;
    private int totalRegistros;
    

    /**
     * Creates new form FrmProductos
     */
    public FrmProductos() {
        initComponents();
        initComponents();
        this.CONTROL=new ProductoControl();
        this.paginar();
        this.listar("",false);
        this.primeraCarga=false;
        tabGeneral.setEnabledAt(1, false);
        this.accion="Guardar";
        txtId.setVisible(false);
        this.cargarCategorias();
        this.cargarDatosComboBox();
        
        /*txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                listar(txtBuscar.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                listar(txtBuscar.getText()); 
            }

            public void changedUpdate(DocumentEvent e) {
                //no se usa con JTextField
            }
        });*/
        
        fecha_vencimiento.setMinSelectableDate(new Date());

    }
    
    private void ocultarColumnas(){
        tablaListado.getColumnModel().getColumn(1).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(1).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(9).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(9).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
    }

    
    private void paginar(){
        int totalPaginas;

        this.totalRegistros = this.CONTROL.total();

        String seleccionado = (String)cboTotalPorPagina.getSelectedItem();
        if (seleccionado != null && !seleccionado.isEmpty()) {
            try {
                this.totalPorPagina = Integer.parseInt(seleccionado);
            } catch (NumberFormatException e) {
                System.out.println("Error: El valor seleccionado no es un número válido");
                return; 
            }
        } else {
            System.out.println("Error: No se seleccionó ningún valor válido");
            return;
        }

        totalPaginas = (int)(Math.ceil((double)this.totalRegistros / this.totalPorPagina));

        if (totalPaginas == 0) {
            totalPaginas = 1;
        }

        cboNumPagina.removeAllItems();

        for (int i = 1; i <= totalPaginas; i++) {
            cboNumPagina.addItem(Integer.toString(i));
        }
        cboNumPagina.setSelectedIndex(0);
    }
    
    private void listar(String texto, boolean paginar) {
        try {
            // Obtener totalPorPagina de cboTotalPorPagina
            String totalPorPaginaStr = (String) cboTotalPorPagina.getSelectedItem();
            if (totalPorPaginaStr != null && !totalPorPaginaStr.isEmpty()) {
                this.totalPorPagina = Integer.parseInt(totalPorPaginaStr);
            } else {
                
                this.totalPorPagina = 10; 
            }

            String numPaginaStr = (String) cboNumPagina.getSelectedItem();
            if (numPaginaStr != null && !numPaginaStr.isEmpty()) {
                this.numPagina = Integer.parseInt(numPaginaStr);
            } else {
                
                this.numPagina = 1;
            }

            if (paginar) {
                tablaListado.setModel(this.CONTROL.listar(texto, this.totalPorPagina, this.numPagina));
            } else {
                tablaListado.setModel(this.CONTROL.listar(texto, this.totalPorPagina, 1));
            }

            TableRowSorter orden = new TableRowSorter(tablaListado.getModel());
            tablaListado.setRowSorter(orden);

            this.ocultarColumnas();
            lblTotalRegistros.setText("Mostrando " + this.CONTROL.totalMostrados() + " de un total de " + this.CONTROL.total() + " registros");

        } catch (NumberFormatException e) {
            System.out.println("Error al convertir los valores seleccionados: " + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
            
        }
    }
    
    private void cargarDatosComboBox() {
    // Poblamos el ComboBox para total de registros por página
    cboTotalPorPagina.removeAllItems();
    cboTotalPorPagina.addItem("10");
    cboTotalPorPagina.addItem("20");
    cboTotalPorPagina.addItem("30");

    // Poblamos el ComboBox para número de páginas
    cboNumPagina.removeAllItems();
    int totalRegistros = this.CONTROL.total();
    int totalPaginas = (int) Math.ceil((double) totalRegistros / totalPorPagina); // Calcula las páginas
    for (int i = 1; i <= totalPaginas; i++) {
        cboNumPagina.addItem(String.valueOf(i));
    }
}
    
    private void cargarCategorias(){
        DefaultComboBoxModel items = this.CONTROL.seleccionar();
        cboCategoria.setModel(items);
    }
    
    private void subirImagenes() {
        File origen=new File(this.rutaOrigen);
            File destino=new File(this.rutaDestino);
            try {
                InputStream in= new FileInputStream(origen);
                OutputStream out=new FileOutputStream(destino);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
}

    
    //metodos pra las ventanas emergentes
    private void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje,"ERROR",JOptionPane.ERROR_MESSAGE);
    }
    
    private void mensajeOk(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje,"Éxito",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void limpiar(){
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtCodigo.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtMarca.setText("");
        this.accion = "Guardar";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabGeneral = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnDesactivar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        lblTotalRegistros = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        xd = new javax.swing.JScrollPane();
        tablaListado = new javax.swing.JTable();
        cboNumPagina = new javax.swing.JComboBox<>();
        cboTotalPorPagina = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        fecha_vencimiento = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblImagenProducto = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnAgregarImagen = new javax.swing.JButton();
        txtMarca = new javax.swing.JTextField();
        txtId = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Productos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/productos.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        btnDesactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/off.png"))); // NOI18N
        btnDesactivar.setText("Desactivar Productos");
        btnDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivarActionPerformed(evt);
            }
        });

        btnActivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/check.png"))); // NOI18N
        btnActivar.setText("Activar Productos");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        lblTotalRegistros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTotalRegistros.setText("Total de registros: ");

        jLabel2.setText("Nombre Producto");

        txtBuscar.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscar.setText("Buscar...");
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/search-1.png"))); // NOI18N
        btnBuscar.setText("Buscar Producto");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/register.png"))); // NOI18N
        btnNuevo.setText("Registrar Producto");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/edit.png"))); // NOI18N
        btnEditar.setText("Editar Producto");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        tablaListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        xd.setViewportView(tablaListado);

        cboNumPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumPaginaActionPerformed(evt);
            }
        });

        cboTotalPorPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTotalPorPaginaActionPerformed(evt);
            }
        });

        jLabel1.setText("N° Página");

        jLabel11.setText("Total de Registros por Página");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(xd, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 30, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnDesactivar)
                                .addGap(77, 77, 77)
                                .addComponent(btnActivar)))
                        .addGap(213, 213, 213))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(xd, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActivar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Listado de Productos", jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jLabel3.setText("Categoría");

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arroz", "Azúcar", "Aceite", " ", " " }));

        jLabel4.setText("Nombre Producto");

        jLabel6.setText("Descripción");

        txtNombre.setText("Escriba el nombre");
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.setText("Descripción");
        txtDescripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescripcionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescripcionFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(txtDescripcion);

        jLabel5.setText("Fecha de Vencimiento");

        jLabel7.setText("Cantidad Producto");

        txtCantidad.setText("Escribe la cantidad");
        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidadFocusLost(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel8.setText("Marca de Producto");

        jLabel9.setText("Precio");

        txtPrecio.setText("Digite el precio");
        txtPrecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPrecioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioFocusLost(evt);
            }
        });
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel10.setText("Código Producto");

        lblImagenProducto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtCodigo.setText("Escribe el código");
        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });

        btnAgregarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentacion/Imagenes/agregar.png"))); // NOI18N
        btnAgregarImagen.setText("AGREGAR IMAGEN");
        btnAgregarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImagenActionPerformed(evt);
            }
        });

        txtMarca.setText("Escribe la Marca");
        txtMarca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMarcaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMarcaFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarImagen)
                        .addGap(31, 31, 31)
                        .addComponent(lblImagenProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNombre)
                                .addComponent(jScrollPane2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fecha_vencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(txtMarca)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))))
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(fecha_vencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblImagenProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAgregarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(127, 127, 127))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))))))
        );

        tabGeneral.addTab("Registro Productos", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusGained
        // TODO add your handling code here:
        if(txtNombre.getText().equals("Escriba el nombre")){
            txtNombre.setText("");
        }
    }//GEN-LAST:event_txtNombreFocusGained

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        // TODO add your handling code here:
        if(txtNombre.getText().isEmpty()){
            txtNombre.setText("Escriba el nombre");
        }
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusGained
        // TODO add your handling code here:
        if(txtCodigo.getText().equals("Escribe el código")){
            txtCodigo.setText("");
        }
    }//GEN-LAST:event_txtCodigoFocusGained

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        // TODO add your handling code here:
        if(txtCodigo.getText().isEmpty()){
            txtCodigo.setText("Escribe el código");
        }
    }//GEN-LAST:event_txtCodigoFocusLost

    private void txtCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusGained
        // TODO add your handling code here:
        if(txtCantidad.getText().equals("Escribe la cantidad")){
            txtCantidad.setText("");
        }
    }//GEN-LAST:event_txtCantidadFocusGained

    private void txtCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusLost
        // TODO add your handling code here:
        if(txtCantidad.getText().isEmpty()){
            txtCantidad.setText("Escribe la cantidad");
        }
    }//GEN-LAST:event_txtCantidadFocusLost

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filtroImagenes = new FileNameExtensionFilter("Imágenes (JPG, PNG, GIF)", 
                "jpg", "jpeg", "png", "gif");
        file.setFileFilter(filtroImagenes);
        int estado=file.showOpenDialog(this);
        if (estado==JFileChooser.APPROVE_OPTION){
            this.imagen=file.getSelectedFile().getName();
            this.rutaOrigen=file.getSelectedFile().getAbsolutePath();
            this.rutaDestino=this.DIRECTORIO + this.imagen;
            
            ImageIcon im=new ImageIcon(this.rutaOrigen);
            Icon icono=new ImageIcon(im.getImage().getScaledInstance(lblImagenProducto.getWidth(),lblImagenProducto.getHeight(),
                    Image.SCALE_DEFAULT));
            lblImagenProducto.setIcon(icono);
            lblImagenProducto.repaint();
        }
    }//GEN-LAST:event_btnAgregarImagenActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        this.listar(txtBuscar.getText(),false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        // TODO add your handling code here:
        if(txtBuscar.getText().equals("Buscar...")){
            txtBuscar.setText("");
        }
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        // TODO add your handling code here:
        if(txtBuscar.getText().isEmpty()){
            txtBuscar.setText("Buscar...");
        }
    }//GEN-LAST:event_txtBuscarFocusLost

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        // TODO add your handling code here:
        if(tablaListado.getSelectedRowCount() == 1){
            String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),0));
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),0));
            if(JOptionPane.showConfirmDialog(this, "Deseas activar el registro: " + nombre + "?", "Activar",JOptionPane.YES_NO_OPTION)==0){
                String resp = this.CONTROL.activar(Integer.parseInt(id));
                if(resp.equals("OK")){
                    this.mensajeOk("Registro activado");
                    this.listar("",false);
                }else{
                    this.mensajeError(resp);
                }
            }
        }else{
            this.mensajeError("Seleciona 1 registro a activar");
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed
        // TODO add your handling code here:
        if(tablaListado.getSelectedRowCount() == 1){
            String id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),0));
            String nombre = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),0));
            if(JOptionPane.showConfirmDialog(this, "Deseas desactivar el registro: " + nombre + "?", "Desactivar",JOptionPane.YES_NO_OPTION)==0){
                String resp = this.CONTROL.desactivar(Integer.parseInt(id));
                if(resp.equals("OK")){
                    this.mensajeOk("Registro desactivado");
                    this.listar("",false);
                }else{
                    this.mensajeError(resp);
                }
            }
        }else{
            this.mensajeError("Seleciona 1 registro a desactivar");
        }
    }//GEN-LAST:event_btnDesactivarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        tabGeneral.setEnabledAt(1, true);
        tabGeneral.setEnabledAt(0, false);
        tabGeneral.setSelectedIndex(1);
        this.accion = "Guardar";
        btnGuardar.setText("Guardar");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtDescripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescripcionFocusGained
        // TODO add your handling code here:
        if(txtDescripcion.getText().equals("Descripción")){
            txtDescripcion.setText("");
        }
    }//GEN-LAST:event_txtDescripcionFocusGained

    private void txtDescripcionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescripcionFocusLost
        // TODO add your handling code here:
        if(txtDescripcion.getText().isEmpty()){
            txtDescripcion.setText("Descripción");
        }
    }//GEN-LAST:event_txtDescripcionFocusLost

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        tabGeneral.setEnabledAt(0,true);
        tabGeneral.setEnabledAt(1,false);
        tabGeneral.setSelectedIndex(0);
        this.limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (txtNombre.getText().length() == 0 || txtNombre.getText().length() > 70) {
        JOptionPane.showMessageDialog(this, "Debes ingresar un nombre válido (máximo 70 caracteres).", "Sistema", JOptionPane.WARNING_MESSAGE);
        txtNombre.requestFocus();
        return;
    }
    if (txtDescripcion.getText().length() == 0 || txtDescripcion.getText().length() > 50) {
        JOptionPane.showMessageDialog(this, "Debes ingresar una descripción válida (máximo 50 caracteres).", "Sistema", JOptionPane.WARNING_MESSAGE);
        txtDescripcion.requestFocus();
        return;
    }
    if (txtCodigo.getText().length() == 0 || txtCodigo.getText().length() > 64) {
        JOptionPane.showMessageDialog(this, "Debes ingresar un código válido (máximo 64 caracteres).", "Sistema", JOptionPane.WARNING_MESSAGE);
        txtCodigo.requestFocus();
        return;
    }
    if (txtMarca.getText().length() > 20) {
        JOptionPane.showMessageDialog(this, "Debes ingresar una marca válida (máximo 20 caracteres).", "Sistema", JOptionPane.WARNING_MESSAGE);
        txtMarca.requestFocus();
        return;
    }
    if (txtCantidad.getText().length() == 0) {
        JOptionPane.showMessageDialog(this, "Debes ingresar una cantidad válida.", "Sistema", JOptionPane.WARNING_MESSAGE);
        txtCantidad.requestFocus();
        return;
    }
    if (txtPrecio.getText().length() == 0) {
        JOptionPane.showMessageDialog(this, "Debes ingresar un precio válido.", "Sistema", JOptionPane.WARNING_MESSAGE);
        txtPrecio.requestFocus();
        return;
    }

    // Variables para almacenar los datos del formulario
    int categoria_id = ((Categoria)cboCategoria.getSelectedItem()).getIdcategoria();  // Obtener el id de la categoría seleccionada
    String nombre = txtNombre.getText();
    String descripcion = txtDescripcion.getText();
    String codigo = txtCodigo.getText();
    String marca = txtMarca.getText();
    int cantidad = Integer.parseInt(txtCantidad.getText());
    Date fechaSeleccionada = fecha_vencimiento.getDate();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    String fecha_vencimiento = formatoFecha.format(fechaSeleccionada);
    double precio_compra = Double.parseDouble(txtPrecio.getText());

    // Ejecutar la acción dependiendo si es "insertar" o "editar"
    String resp;
    if (this.accion.equals("editar")) {
        // Actualizar registro existente
        String imagenActual = this.imagen.isEmpty() ? this.imagenAnt : this.imagen;  // Si no hay imagen nueva, usar la anterior
        resp = this.CONTROL.actualizar(Integer.parseInt(txtId.getText()), categoria_id, nombre, this.nombreAnt, descripcion, 
                imagenActual, codigo, marca, cantidad, fecha_vencimiento,precio_compra
        );
        if (resp.equals("OK")) {
            if (!this.imagen.isEmpty()) {
                this.subirImagenes();  // Subir las nuevas imágenes si las hay
            }
            this.mensajeOk("Actualizado correctamente");
            this.limpiar();  // Limpiar el formulario
            this.listar("", false);  // Listar nuevamente los productos
            tabGeneral.setSelectedIndex(0);  // Cambiar a la pestaña principal
        } else {
            this.mensajeError(resp);
        }
    } else {
        // Insertar un nuevo registro
        resp = this.CONTROL.insertar(
                categoria_id, nombre, descripcion, this.imagen, codigo, marca,  cantidad, fecha_vencimiento, precio_compra
        );
        if (resp.equals("OK")) {
            if (!this.imagen.isEmpty()) {
                this.subirImagenes();  // Subir las nuevas imágenes si las hay
            }
            this.mensajeOk("Registrado correctamente");
            this.limpiar();  // Limpiar el formulario
            this.listar("", false);  // Listar nuevamente los productos
            tabGeneral.setSelectedIndex(0);  // Cambiar a la pestaña principal
        } else {
            this.mensajeError(resp);
        }
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cboNumPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumPaginaActionPerformed
        // TODO add your handling code here:
        if (this.primeraCarga==false){
            this.listar("",true);
        }
    }//GEN-LAST:event_cboNumPaginaActionPerformed

    private void cboTotalPorPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalPorPaginaActionPerformed
        // TODO add your handling code here:
        if (this.primeraCarga==false){
            this.listar("",true);
        }
    }//GEN-LAST:event_cboTotalPorPaginaActionPerformed

    private void txtMarcaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMarcaFocusGained
        // TODO add your handling code here:
        if(txtMarca.getText().equals("Escribe la Marca")){
            txtMarca.setText("");
        }
    }//GEN-LAST:event_txtMarcaFocusGained

    private void txtMarcaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMarcaFocusLost
        // TODO add your handling code here:
        if(txtMarca.getText().isEmpty()){
            txtMarca.setText("Escribe la Marca");
        }
    }//GEN-LAST:event_txtMarcaFocusLost

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        if (tablaListado.getSelectedRowCount()==1){
            String id= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),0));
            int categoriaId=Integer.parseInt(String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),1)));
            String nombre= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),2));
            this.nombreAnt= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),2));
            String descripcion= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),3));
            this.imagenAnt=String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),4));
            String codigo=String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),5));
            String marca = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),6));
            String cantidad= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),7));
            String fecha_venci = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 8));
            String precioCompra= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),9)); 
            
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); // Cambia el formato si es necesario

            try {
                Date fecha = formato.parse(fecha_venci); // Convierte String a Date
                fecha_vencimiento.setDate(fecha); // Establece la fecha en el JDateChooser
            } catch (ParseException e) {
                // Manejo de excepción
                
            }

            txtId.setText(id);
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            txtCodigo.setText(codigo);
            txtMarca.setText(marca);
            txtCantidad.setText(cantidad);
            txtPrecio.setText(precioCompra);
                        
            
            
            ImageIcon im=new ImageIcon(this.DIRECTORIO+this.imagenAnt);
            Icon icono=new ImageIcon(im.getImage().getScaledInstance(lblImagenProducto.getWidth(),lblImagenProducto.getHeight(),
                    Image.SCALE_DEFAULT));
            lblImagenProducto.setIcon(icono);
            lblImagenProducto.repaint();
            
            
            tabGeneral.setEnabledAt(0, false);
            tabGeneral.setEnabledAt(1, true);
            tabGeneral.setSelectedIndex(1);
            this.accion="editar";
            btnGuardar.setText("Editar");
        }else{
            this.mensajeError("Seleccione 1 registro a editar.");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        
        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            
            JOptionPane.showMessageDialog(rootPane, "Solo ingrese números");
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        
        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            
            JOptionPane.showMessageDialog(rootPane, "Solo ingrese números");
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtPrecioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioFocusGained
        // TODO add your handling code here:
        if(txtPrecio.getText().equals("Digite el precio")){
            txtPrecio.setText("");
        }
    }//GEN-LAST:event_txtPrecioFocusGained

    private void txtPrecioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioFocusLost
        // TODO add your handling code here:
        if(txtPrecio.getText().isEmpty()){
            txtPrecio.setText("Digite el precio");
        }
    }//GEN-LAST:event_txtPrecioFocusLost

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        // TODO add your handling code here:
        String texto = txtCantidad.getText();
        if(texto.length()>0 && texto.charAt(0) == '-'){
            txtCantidad.setText(texto.substring(1));
        }
        try {
            double numero = Double.parseDouble(texto);
            if(numero < 0){
                txtCantidad.setText("");
            }
        } catch (NumberFormatException e) {
            txtCantidad.setText("");
        }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        // TODO add your handling code here:
        String texto = txtPrecio.getText();
        if(texto.length()>0 && texto.charAt(0) == '-'){
            txtPrecio.setText(texto.substring(1));
        }
        try {
            double numero = Double.parseDouble(texto);
            if(numero < 0){
                txtPrecio.setText("");
            }
        } catch (NumberFormatException e) {
            txtPrecio.setText("");
        }
    }//GEN-LAST:event_txtPrecioKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnAgregarImagen;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JComboBox<String> cboNumPagina;
    private javax.swing.JComboBox<String> cboTotalPorPagina;
    private com.toedter.calendar.JDateChooser fecha_vencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImagenProducto;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JLabel txtId;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JScrollPane xd;
    // End of variables declaration//GEN-END:variables

    private JFileChooser JFileChooser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
