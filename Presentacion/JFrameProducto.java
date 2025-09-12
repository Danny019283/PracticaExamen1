/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import AccesoADatos.ColeccionProducto;

import java.awt.*;

/**
 *
 * @author Danny
 */
public class JFrameProducto extends JFrame{
    
    private final ColeccionProducto backend = new ColeccionProducto(); //Indica la fuente de datos
    private final tablaProducto tblProducto = new tablaProducto(); //Intermediario entre la tabla y los datos
    private JTable table = new JTable();
    private TableRowSorter<tablaProducto> sorter;

    //Crea los espacios para escribir
    private final JTextField txtBuscarNombre = new JTextField();
    private final JComboBox<String> comboBuscarTipo = new JComboBox<String>();
    private final JTextField txtCod = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtPrecio = new JTextField();
    private final JCheckBox chkImportado = new JCheckBox();
    private final JComboBox<String> cmbTipo = new JComboBox<String>();
    
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
        setVisible(true);
    }

    public void buildUI(){
        //este es el panel donde va los demas paneles como una hoja
        JPanel content = new JPanel(new BorderLayout(12, 12));
        content.setBackground(Color.LIGHT_GRAY);
        content.setBorder(new EmptyBorder(12, 20, 12, 20)); //crea espacios entre elementos
        setContentPane(content);

        //panel superior
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS)); //Va dividiendo el panel en el x
        top.setBorder(new EmptyBorder(12, 12, 12, 12));
        content.add(top, BorderLayout.NORTH);

        // TopWest: búsqueda por nombre
        JPanel topLeft = new JPanel(new BorderLayout(12, 12));
        topLeft.add(new JLabel("Nombre:"),  BorderLayout.WEST);
        topLeft.add(txtBuscarNombre, BorderLayout.CENTER);
        topLeft.add(btnBuscarNombre,  BorderLayout.EAST);
        top.add(topLeft);

        top.add(Box.createRigidArea(new Dimension(20, 10))); //agrega una separacion entre panel y panel

        //TopEast busqueda por tipo
        JPanel topRight = new JPanel(new BorderLayout(12, 12));
        topRight.add(new JLabel("Tipo:"),   BorderLayout.WEST);
        topRight.add(comboBuscarTipo,  BorderLayout.CENTER);
        topRight.add(btnBuscarTipo,  BorderLayout.EAST);
        top.add(topRight);

        // Centro: tabla
        table.setRowHeight(24);
        table = new JTable(tblProducto);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter = new TableRowSorter<>(tblProducto);
        table.setRowSorter(sorter);
        content.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(0, 2));
        bottom.setBorder(new EmptyBorder(12, 12, 12, 12));
        content.add(bottom, BorderLayout.SOUTH);

        // Abajp: formulario + boton
        JPanel bottomLeft = new JPanel();
        bottomLeft.setBackground(Color.LIGHT_GRAY);
        bottomLeft.setLayout(new GridLayout(0, 2, 12, 0));
        bottomLeft.add(field("Codigo", txtCod));
        bottomLeft.add(Box.createVerticalStrut(6));
        bottomLeft.add(field("Nombre", txtNombre));
        bottomLeft.add(Box.createVerticalStrut(6));
        bottomLeft.add(field("Precio", txtPrecio));
        bottomLeft.add(Box.createVerticalStrut(6));
        chkImportado.setBackground(Color.LIGHT_GRAY);
        bottomLeft.add(field("Importado", chkImportado));
        bottomLeft.add(Box.createVerticalStrut(6));
        bottomLeft.add(field("Tipo", cmbTipo));

        JPanel buttonA = new JPanel();
        buttonA.setBackground(Color.LIGHT_GRAY);
        buttonA.setBorder(new EmptyBorder(11, 12, 8, 12));
        buttonA.add(btnAgregar);


        bottomLeft.add(buttonA);
        bottom.add(bottomLeft);
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            new JFrameProducto().setVisible(true);
        });
    }
}