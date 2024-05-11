package core;

import java.util.ArrayList;

public class Habitacion implements Comparable<Habitacion>{
    private String nombre;
    private ArrayList<Tarea> tareas;
    private ArrayList<Jugador> muertos;

    @Override
    public String toString() {
        return nombre;
    }

    public Habitacion(String nombre){
        this.nombre=nombre;
        tareas=new ArrayList<>();
        muertos=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void addTareas(Tarea tarea) {
        tareas.add(tarea);
    }

    public ArrayList<Jugador> getMuertos() {
        return muertos;
    }

    public boolean hasMuertos(){
        if(muertos.isEmpty()){
            return false;
        }
        return true;
    }

    public void limpiaMuertos(){
        muertos = new ArrayList<>();
    }

    public void addMuerto(Jugador j){
        muertos.add(j);
    }

    @Override
    public int compareTo(Habitacion h) {
        return nombre.compareTo(h.nombre);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Habitacion other = (Habitacion) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }
    
}
