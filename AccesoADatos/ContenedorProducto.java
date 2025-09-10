/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoADatos;

import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class ContProducto {
    ArrayList<Estudiante> listaEsts = new ArrayList<>();
    public boolean insertar(Estudiante est){
        if (buscar(est.getId()) != null){
            listaEsts.add(est);
            return true;
        }
        return false;
    }
    public boolean modificar(Estudiante estMod){
        for (int i = 0; i < listaEsts.size(); i++){
            if(listaEsts.get(i).getId().equals(estMod.getId())){
                listaEsts.set(i, estMod);
                return true;
            }
        }
        return false;
    }
    public boolean eliminar(String id){
        if (listaEsts.size() == 0){
            return false;
        }
        for (int i = 0; i < listaEsts.size(); i++) {
            if (listaEsts.get(i).getId() == id){
                listaEsts.remove(i);
                return true;
            }
        }
        return false;
        
    }
    public Estudiante buscar(String id){
        for (Estudiante est : listaEsts) {
            if (est.getId().equals(id)) {
                return est;
            }
        }
        return null;
    }
    public ArrayList listado(){
        return listaEsts;
    }
}
