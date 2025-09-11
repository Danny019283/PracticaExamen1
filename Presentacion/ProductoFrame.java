/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;
import javax.swing.*;
import javax.swing.table.TableRowSorter;
import AccesoADatos.ColeccionProducto;

/**
 *
 * @author Danny
 */
public class ProductoFrame extends JFrame{
    
    private final ColeccionProducto backend = new ColeccionProducto(); //Indica la fuente de datos
    private final ProductoTableModel tableModel = new ProductoTableModel(); //Intermediario entre la tabla y los datos
    private JTable table; //Crea la tabla
    private TableRowSorter<ProductoTableModel> sorter; //Ordena por columna, ejemplo nombre alfabeticamente
    
    //Crea los espacios para escribir
    private final JTextField txtBuscarNombre = new JTextField();
    private final JComboBox<String> comboBuscarTipo = new JComboBox<String>();
    private final JTextField txtCod = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtPrecio = new JTextField();
    private final JComboBox<String> comboTipo = new JComboBox<String>();

    //Crea el constructor JFrame (configura la ventana)
    ProductoFrame(){
        setTitle("Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null); //centra la ventana
    }

    public void buildUI(){
        
    }


    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            new ProductoFrame().setVisible(true);
        });
    }
}