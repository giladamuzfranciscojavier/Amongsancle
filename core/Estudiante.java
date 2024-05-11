package core;

public class Estudiante extends Jugador{
    public Estudiante(String alias){
        super(alias);
    }

    public boolean hasTareas(){
        if(tareas.isEmpty()){
            return false;
        }
        return true;
    }
}
