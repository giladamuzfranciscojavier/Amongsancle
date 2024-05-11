//Clase en la que se guardan las opciones de configuración

package core;

import java.util.ArrayList;
import java.util.Iterator;

public class Settings {
    static ArrayList<Tarea> tareas;
    static ArrayList<Habitacion> habitaciones;
    //No tiene sentido listar objetos Jugador ya que el rol se asigna en partida y por lo tanto sería redundante
    static ArrayList<String> jugadores;       
    static int tiempoMax;   
    static boolean debug; 

    //La probabilidad de que una acusación sea mentira
    static double probMentira;

    //Inicialización y restablecimiento a las opciones por defecto
    public static void init(){
        tareas = new ArrayList<>();
        jugadores = new ArrayList<>();
        habitaciones = new ArrayList<>();
        tiempoMax = 15;    

        probMentira = .5;

        Settings.addTarea("Reparar el proyector","Aula de la bodega");     
        Settings.addTarea("Encontrar documento","Secretaria");
        Settings.addTarea("Cambiar cable Ethernet","Sala de profesorado");
        Settings.addTarea("Recoger tizas","Conserjería");
        Settings.addTarea("Proyectar película","Aula de la bodega");
        Settings.addTarea("Reparar ordenadores","Aula 01");
        Settings.addTarea("Fotocopiar documento","Conserjería");        
        Settings.addTarea("Limpiar aula","Aula 01");        
        Settings.addTarea("Reparar máquina de café","Sala de profesorado");
        Settings.addTarea("Investigar alumnos","Secretaria");

        Settings.addJugador("El_Sus");
        Settings.addJugador("Amogus");
        Settings.addJugador("Sussus_Amogus");   
        Settings.addJugador("xXx_aM0G0_xXx");     
        Settings.addJugador("Streamer_Generico42069");
    }

    static public ArrayList<Tarea> getTareas() {
        ArrayList<Tarea> temp = new ArrayList<>();

        for (Tarea tarea : tareas) {
            temp.add(tarea);
        }

        return temp;
    }

    public static double getProbMentira() {
        return probMentira;
    }

    public static void setProbMentira(double probMentira) {
        Settings.probMentira = probMentira;
    }

    static public void addTarea(String tarea, String habitacion) {
        Habitacion h = null;
        
        for (Habitacion habitacion2 : habitaciones) {
            if(habitacion2.getNombre().equals(habitacion)){
                h=habitacion2;
                break;
            }
        }

        if(h==null){
            h = new Habitacion(habitacion);
            habitaciones.add(h);
            habitaciones.sort(null);
        }

        tareas.add(new Tarea(tarea, h));
        tareas.sort(null);
    }

    public static ArrayList<Habitacion> getHabitaciones() {
        ArrayList<Habitacion> temp = new ArrayList<>();

        for (Habitacion h : habitaciones) {
            temp.add(h);
        }

        return temp;
    }  


    //Elimina la habitación Y las tareas que pertenecen a la misma
    static public void RemoveHabitacion(Habitacion h){

        //Se utiliza un iterator para poder borrar las tareas que pertenezcan a la habitación que se va a eliminar
        Iterator<Tarea> it = tareas.iterator();
        while (it.hasNext()) {
            Tarea t = it.next();
            if(t.getHabitacion().getNombre().equals(h.getNombre())){
                it.remove();
            } 
        }

        habitaciones.remove(h);
    }

    static public void addHabitacion(String habitacion) {
        habitaciones.add(new Habitacion(habitacion));
        habitaciones.sort(null);
    }

    static public void removeTarea(Tarea t){
        tareas.remove(t);
    }

    static public ArrayList<String> getJugadores() {
        ArrayList<String> temp = new ArrayList<>();

        for (String jugador : jugadores) {
            temp.add(jugador);
        }
        return temp;
    }

    static public void addJugador(String jugador){
        jugadores.add(("@"+jugador));
        jugadores.sort(null);
    }

    static public void removeJugador(String jugador){
        jugadores.remove(jugador);
    }

    static public int getTiempoMax() {
        return tiempoMax;
    }

    static public void setTiempoMax(int tMax) {
        //Para evitar que haya menos tiempo del necesario para la entrada (lo que bloquearía el juego en un estado imposible de ganar) el tiempo máximo será como mínimo 5
        if(tMax<5){
            tMax=5;
        }
        tiempoMax = tMax;
    }     

    public static void setDebug(boolean d){
        debug=d;
    }

    public static boolean getDebug(){
        return debug;
    }
}
