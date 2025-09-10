/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;


import Modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import Modelo.Producto;

/**
 *
 * @author Danny
 */
public class ProductoTableModel extends AbstractTableModel{
    private final String[] cols = {"Codigo", "Nombre", "Importado", "Precio", "Tipo", "Porcentaje", "Precio Final"}; //crea las colummnas
    private final List<Producto> data = new ArrayList<>(); //data es la Lista donde se van almacenar los productos
    
    public void setData(List<Producto> productos) {
        data.clear(); //limpia antes de cargar los datos
        data.addAll(productos); //agrega los productos a la lista interna
        fireTableDataChanged(); //notifica que lo datos han sigo actualizado entonces refresca la lista
    }
    
    public Producto getAt(int row) {
        return data.get(row); //obtiene el producto que se encuentra en una fila especifica
    }
    
    public void add(Producto produc) {
        data.add(produc); //agrega un producto a la tabla
        int fila = data.size() - 1;
        fireTableRowsInserted(fila, fila); //Notifica a todos los oyentes que se han insertado filas en la ultima posicion
    }
    
    public void update(int fila, Producto produc) {
        data.set(fila, produc);
        fireTableRowsUpdated(fila, fila); //Notifica a todos los oyentes que se han actualizado en una posicion especifica
    }
    
    public void remove(int fila) {
        data.remove(fila);
        fireTableRowsDeleted(fila, fila); //Notifica a todos los oyentes que se han eliminado filas en una posicion especifica
    }
    
    //son metodos de un super tipo (propios de Table Model)
    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int column) { return cols[column]; }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto l = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> l.getId();
            case 1 -> l.getTitulo();
            case 2 -> l.getAutor();
            case 3 -> l.getAnio();
            default -> "";
        };
    }

}
