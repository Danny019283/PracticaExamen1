/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoADatos;

import java.util.ArrayList;
import Modelo.Producto;

/**
 *
 * @author estudiante
 */
public class ColeccionProducto {
    ArrayList<Producto> listaProducs = new ArrayList<>();
    
    public boolean insertar(Producto produc){
        if (buscar(produc.getCodigo()) != null){
            return false;
        }
        listaProducs.add(produc);
        return true;
    }
    public boolean modificar(Producto producMod){
        for (int i = 0; i < listaProducs.size(); i++){
            if(listaProducs.get(i).getCodigo() == producMod.getCodigo()){
                listaProducs.set(i, producMod);
                return true;
            }
        }
        return false;
    }
    public boolean eliminar(int id){
        if (listaProducs.isEmpty()){
            return false;
        }
        for (int i = 0; i < listaProducs.size(); i++) {
            if (listaProducs.get(i).getCodigo() == id){
                listaProducs.remove(i);
                return true;
            }
        }
        return false;
        
    }
    public Producto buscar(int id){
        for (Producto produc : listaProducs) {
            if (produc.getCodigo() == id) {
                return produc;
            }
        }
        return null;
    }
    
    public ArrayList<Producto> listar(){
        return new ArrayList<>(listaProducs);
    }
}
