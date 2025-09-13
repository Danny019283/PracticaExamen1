/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;   // <-- esto incluye ActionListener, ActionEvent, etc.


import AccesoADatos.ColeccionProducto;
import AccesoADatos.ColeccionTipo;
import Modelo.Producto;
import Modelo.Tipo;




/**
 *
 * @author Danny
 */
public class JFrameProducto extends JFrame{
    
    private final ColeccionProducto backend = new ColeccionProducto(); //Indica la fuente de datos
    private final ColeccionTipo backendTipo = new ColeccionTipo();
    private final tablaProducto tblProducto = new tablaProducto(); //Intermediario entre la tabla y los datos
    private JTable tabla = new JTable();
    private TableRowSorter<tablaProducto> sorter;

    //Crea los espacios para escribir
    private final JTextField txtBuscarNombre = new JTextField();
    private final JComboBox<String> comboBuscarTipo = new JComboBox<String>();
    private final JTextField txtCod = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtPrecio = new JTextField();
    private final JCheckBox chkImportado = new JCheckBox();
    private final JComboBox<String> cmbTipo = new JComboBox<>();
    
    //Crea los botones
    private final JButton btnBuscarNombre = new JButton("Buscar");
    private final JButton btnBuscarTipo = new JButton("Buscar");
    private final JButton btnAgregar = new JButton("Agregar");

    //Crea el constructor JFrame (configura la ventana)
    JFrameProducto(){
        setTitle("Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null); //centra la ventana
        buildUI();
        seedAndLoad();
        setVisible(true);
    }

    public void buildUI() {
        //este es el panel donde va los demas paneles como una hoja
        JPanel pnlContenido = new JPanel(new BorderLayout(12, 12));
        pnlContenido.setBackground(Color.LIGHT_GRAY);
        pnlContenido.setBorder(new EmptyBorder(12, 20, 12, 20)); //crea espacios entre elementos
        setContentPane(pnlContenido);

        //panel superior
        JPanel pnlArriba = new JPanel();
        pnlArriba.setLayout(new BoxLayout(pnlArriba, BoxLayout.X_AXIS)); //Va dividiendo el panel en el x
        pnlArriba.setBorder(new EmptyBorder(12, 12, 12, 12));
        pnlContenido.add(pnlArriba, BorderLayout.NORTH);

        // TopWest: búsqueda por nombre
        JPanel pnlArribaIzq = new JPanel(new BorderLayout(12, 12));
        pnlArribaIzq.add(new JLabel("Nombre:"), BorderLayout.WEST);
        pnlArribaIzq.add(txtBuscarNombre, BorderLayout.CENTER);
        pnlArribaIzq.add(btnBuscarNombre, BorderLayout.EAST);
        pnlArriba.add(pnlArribaIzq);

        pnlArriba.add(Box.createRigidArea(new Dimension(20, 10))); //agrega una separacion entre panel y panel

        //TopEast busqueda por tipo
        JPanel pnlArribaDer = new JPanel(new BorderLayout(12, 12));
        pnlArribaDer.add(new JLabel("Tipo:"), BorderLayout.WEST);
        pnlArribaDer.add(comboBuscarTipo, BorderLayout.CENTER);
        pnlArribaDer.add(btnBuscarTipo, BorderLayout.EAST);
        pnlArriba.add(pnlArribaDer);

        // Centro: tabla
        tabla.setRowHeight(24);
        tabla = new JTable(tblProducto);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter = new TableRowSorter<>(tblProducto);
        tabla.setRowSorter(sorter);
        pnlContenido.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel pnlAbajo = new JPanel(new GridLayout(0, 2));
        pnlAbajo.setBorder(new EmptyBorder(12, 12, 12, 12));
        pnlContenido.add(pnlAbajo, BorderLayout.SOUTH);

        // Abajp: formulario + boton
        JPanel pnlAbajoIzq = new JPanel();
        pnlAbajoIzq.setBackground(Color.LIGHT_GRAY);
        pnlAbajoIzq.setLayout(new GridLayout(0, 2, 12, 0));
        pnlAbajoIzq.add(field("Codigo", txtCod));
        pnlAbajoIzq.add(Box.createVerticalStrut(6));
        pnlAbajoIzq.add(field("Nombre", txtNombre));
        pnlAbajoIzq.add(Box.createVerticalStrut(6));
        pnlAbajoIzq.add(field("Precio", txtPrecio));
        pnlAbajoIzq.add(Box.createVerticalStrut(6));
        chkImportado.setBackground(Color.LIGHT_GRAY);
        pnlAbajoIzq.add(field("Importado", chkImportado));
        pnlAbajoIzq.add(Box.createVerticalStrut(6));
        pnlAbajoIzq.add(field("Tipo", cmbTipo));

        JPanel pnlBotonA = new JPanel();
        pnlBotonA.setBackground(Color.LIGHT_GRAY);
        pnlBotonA.setBorder(new EmptyBorder(11, 12, 8, 12));
        pnlBotonA.add(btnAgregar);

        pnlAbajoIzq.add(pnlBotonA);
        pnlAbajo.add(pnlAbajoIzq);


        // Listeners
        btnBuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtBuscarNombre.getText().trim();
                if (nombre.isEmpty()) {
                    sorter.setRowFilter(null); // mostrar todo
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + nombre));
                }
            }
        });

        btnBuscarTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) comboBuscarTipo.getSelectedItem();
                if (tipo.isEmpty()) {
                    sorter.setRowFilter(null); // mostrar todo
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + tipo));
                }
            }
        });

        btnAgregar.addActionListener(this::onAgregar);

    }

    private void seedAndLoad() {
        // Sembrar si está vacío

        if (backend.listar().isEmpty()) {

            Tipo t1 = new Tipo(1, "Canasta basica", 5);
            backendTipo.AgregarTipo(t1);

            Tipo t2 = new Tipo(2, "Electrodomésticos", 15);
            backendTipo.AgregarTipo(t2);

            Tipo t3 = new Tipo(3, "Bebidas", 10);
            backendTipo.AgregarTipo(t3);

            backend.insertar(new Producto(123, "Leche", 1000, false, t1));
            backend.insertar(new Producto(124, "Arroz", 800, false, t1));
            backend.insertar(new Producto(200, "Refrigeradora", 350000, true, t2));
            backend.insertar(new Producto(201, "Coca Cola 2L", 1500, false, t3));

        }
        tblProducto.setData(backend.listar());
        setCmb();
    }

    //crea filas de JPanel
    private JPanel field(String label, JComponent input) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.LIGHT_GRAY);
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(50, 25));
        p.add(lbl, BorderLayout.WEST);
        p.add(input, BorderLayout.CENTER);
        return p;
    }

    private void onAgregar(ActionEvent e) {
        try {
            Producto nuevo = readForm();
            if (!backend.insertar(nuevo)) {
                throw new IllegalArgumentException("El codigo ya existe: " + nuevo.getCodigo());
            }
            tblProducto.add(nuevo);
            clearForm();
            JOptionPane.showMessageDialog(this, "Producto agregado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void setCmb() {
        for (Tipo tipo : backendTipo.getListaTipos()) {
            cmbTipo.addItem(tipo.getDescripcion());
            comboBuscarTipo.addItem(tipo.getDescripcion());
        }
    }


    //crea y valida el Producto a agregar
    private Producto readForm() {
        int cod;
        try {
             cod = Integer.parseInt(txtCod.getText());
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El codigo solo puede llevar numeros enteros");
        }
        String nombre = txtNombre.getText().trim();
        String precio = txtPrecio.getText().trim();
        boolean importado = chkImportado.isSelected();
        Tipo tipo = backendTipo.buscarTipo((String) cmbTipo.getSelectedItem());

        if (cod == 0 ||  nombre.isEmpty() || precio.isEmpty()) {
            throw new IllegalArgumentException("Complete todos los campos.");
        }
        float precio1;
        try { precio1 = Float.parseFloat(precio); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("El año debe ser entero."); }

        if (precio1 < 0) throw new IllegalArgumentException("El año no puede ser negativo.");

        return new Producto(cod, nombre, precio1, importado, tipo);
    }

private void clearForm() {
    txtCod.setText("");
    txtNombre.setText("");
    txtPrecio.setText("");
    chkImportado.setSelected(false);
    cmbTipo.setSelectedIndex(0);

    tabla.clearSelection();
    txtCod.requestFocus();
}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            new JFrameProducto().setVisible(true);
        });
    }
}