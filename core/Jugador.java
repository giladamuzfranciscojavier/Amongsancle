package core;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Jugador {
    String alias;
    Queue<Tarea> tareas;
    String habitacionActual;

    Jugador(String alias){
        this.alias = alias;
        tareas = new LinkedList<>();
        habitacionActual="";
    }

    public String getAlias(){
        return alias;
    }

    public void addTarea(Tarea t){
        tareas.add(t);
    }

    //Devuelve la siguiente tarea y la elimina de la lista de tareas
    public Tarea nextTarea(){
        return tareas.poll();
    }
}
