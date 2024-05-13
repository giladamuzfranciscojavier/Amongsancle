//Clase en la que se guardan las opciones de configuración

package core;

import java.util.ArrayList;
import java.util.Iterator;

public class Settings {
    ArrayList<Tarea> tareas;
    ArrayList<Habitacion> habitaciones;
    //No tiene sentido listar objetos Jugador ya que el rol se asigna en partida y por lo tanto sería redundante
    ArrayList<String> jugadores;       
    int tiempoMax;   
    boolean debug; 

    //La probabilidad de que una acusación sea mentira
    double probMentira;

    private Settings(){}

    static Settings settings;

    
    public static Settings getSettings(){
        if(settings==null){
            settings = new Settings();
            settings.init();
        }
        return settings;        
    }

    //Inicialización y restablecimiento a las opciones por defecto
    public void init(){
        settings.tareas = new ArrayList<>();
        settings.jugadores = new ArrayList<>();
        settings.habitaciones = new ArrayList<>();
        settings.tiempoMax = 15;    

        settings.probMentira = .5;

        settings.addTarea("Reparar el proyector","Aula de la bodega");     
        settings.addTarea("Encontrar documento","Secretaria");
        settings.addTarea("Cambiar cable Ethernet","Sala de profesorado");
        settings.addTarea("Recoger tizas","Conserjería");
        settings.addTarea("Proyectar película","Aula de la bodega");
        settings.addTarea("Reparar ordenadores","Aula 01");
        settings.addTarea("Fotocopiar documento","Conserjería");        
        settings.addTarea("Limpiar aula","Aula 01");        
        settings.addTarea("Reparar máquina de café","Sala de profesorado");
        settings.addTarea("Investigar alumnos","Secretaria");

        settings.addJugador("El_Sus");
        settings.addJugador("Amogus");
        settings.addJugador("Sussus_Amogus");   
        settings.addJugador("xXx_aM0G0_xXx");     
        settings.addJugador("Streamer_Generico42069");
        settings.addJugador("SussyBaka");
        settings.addJugador("NoSeMeOcurrenMásNombres");
    }

    public ArrayList<Tarea> getTareas() {
        ArrayList<Tarea> temp = new ArrayList<>();

        for (Tarea tarea : tareas) {
            temp.add(tarea);
        }

        return temp;
    }

    public double getProbMentira() {
        return probMentira;
    }

    public void setProbMentira(double probMentira) {
        this.probMentira = probMentira;
    }

    public void addTarea(String tarea, String habitacion) {
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

    public ArrayList<Habitacion> getHabitaciones() {
        ArrayList<Habitacion> temp = new ArrayList<>();

        for (Habitacion h : habitaciones) {
            temp.add(h);
        }

        return temp;
    }  


    //Elimina la habitación Y las tareas que pertenecen a la misma
    public void RemoveHabitacion(Habitacion h){

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

    public void addHabitacion(String habitacion) {
        habitaciones.add(new Habitacion(habitacion));
        habitaciones.sort(null);
    }

    public void removeTarea(Tarea t){
        tareas.remove(t);
    }

    public ArrayList<String> getJugadores() {
        ArrayList<String> temp = new ArrayList<>();

        for (String jugador : jugadores) {
            temp.add(jugador);
        }
        return temp;
    }

    public void addJugador(String jugador){
        jugadores.add(("@"+jugador));
        jugadores.sort(null);
    }

    public void removeJugador(String jugador){
        jugadores.remove(jugador);
    }

    public int getTiempoMax() {
        return tiempoMax;
    }

    public void setTiempoMax(int tMax) {
        //Para evitar que haya menos tiempo del necesario para la entrada (lo que bloquearía el juego en un estado imposible de ganar) el tiempo máximo será como mínimo 5
        if(tMax<5){
            tMax=5;
        }
        tiempoMax = tMax;
    }     

    public void setDebug(boolean d){
        debug=d;
    }

    public boolean getDebug(){
        return debug;
    }
}
