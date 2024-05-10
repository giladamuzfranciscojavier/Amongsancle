//Clase en la que se guardan las opciones de configuración

package core;

import java.util.ArrayList;

public class Settings {
    static ArrayList<Tarea> tareas;
    //No tiene sentido listar objetos Jugador ya que el rol se asigna en partida y por lo tanto sería redundante
    static ArrayList<String> jugadores;       
    static int tiempoMax;    

    //Inicialización y restablecimiento a las opciones por defecto
    public static void init(){
        tareas = new ArrayList<>();
        jugadores = new ArrayList<>();
        tiempoMax = 30;    
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

    static public void addTarea(String tarea, String habitacion) {
        tareas.add(new Tarea(tarea, habitacion));
        tareas.sort(null);
    }

    static public void RemoveTarea(Tarea t){
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

    static public void RemoveJugador(String jugador){
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
}
