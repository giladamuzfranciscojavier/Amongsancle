package core;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Jugador implements Comparable<Jugador>{
    String alias;
    Queue<Tarea> tareas;
    Habitacion habitacionActual;

    boolean finish;

    Jugador(String alias){
        this.alias = alias;
        tareas = new LinkedList<>();
        habitacionActual=null;
        finish=false;
    }

    public Boolean isFinish(){
        return finish;
    }

    public String getAlias(){
        return alias;
    }    

    public void addTarea(Tarea t){
        tareas.add(t);
    }

    //Devuelve la siguiente tarea y la elimina de la lista de tareas
    public Tarea nextTarea(){
        if(tareas.isEmpty()){
            finish=true;
        }
        return tareas.poll();
    }

    public Habitacion getHabitacionActual() {
        return habitacionActual;
    }

    public void setHabitacionActual(Habitacion habitacionActual) {
        this.habitacionActual = habitacionActual;
    }
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alias == null) ? 0 : alias.hashCode());
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
        Jugador other = (Jugador) obj;
        if (alias == null) {
            if (other.alias != null)
                return false;
        } else if (!alias.equals(other.alias))
            return false;
        return true;
    }

    @Override
    public int compareTo(Jugador j) {
        return alias.compareTo(j.alias);
    }
}
