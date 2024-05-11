package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import core.*;
import tools.ScannerFactory;

public class GameController {    

    //Versión de las listas que permanecerá en memoria en caso de reiniciar
    ArrayList<Estudiante> saveEstudiantes;
    ArrayList<Impostor> saveImpostores;
    ArrayList<Jugador> jugadores;
    ArrayList<Estudiante> estudiantes;
    ArrayList<Impostor> impostores;
    ArrayList<Tarea> tareas = Settings.getTareas();
    Scanner sc;

    //Inicialización y reparto de tareas
    public GameController(){
        saveEstudiantes = new ArrayList<>();
        saveImpostores = new ArrayList<>();
        sc = ScannerFactory.getScanner();

        ArrayList<String> tempjugadores = Settings.getJugadores();
        Random r = new Random();
        //Número máximo de impostores: la mitad del total de jugadores menos uno
        int sus = r.nextInt(1, Math.max(1, tempjugadores.size()/2));
        
        //Asigna impostores hasta el límite generado anteriormente
        while (saveImpostores.size()<sus) {
            //Obtiene un nombre aleatorio de la lista de jugadores, lo elimina de la lista y crea un impostor con dicho nombre
            String temp = tempjugadores.get(r.nextInt(tempjugadores.size()));
            tempjugadores.remove(temp);
            saveImpostores.add(new Impostor(temp));
        }

        //El resto de jugadores serán estudiantes
        for (String string : tempjugadores) {
            saveEstudiantes.add(new Estudiante(string));
        }

        //Lista de todos los jugadores para que la propia lógica del juego no incrimine a los impostores
        jugadores = new ArrayList<>();
        jugadores.addAll(saveEstudiantes);jugadores.addAll(saveImpostores);

        //Se asignan las tareas. Los impostores también tendrán tareas, pero por razones evidentes no influirán en el resultado
        for (Jugador jugador : jugadores) {
            //Las tareas se pueden repetir entre jugadores, pero no en el mismo jugador
            ArrayList<Tarea> tareas = Settings.getTareas();
            for (int i = 0; i < 5; i++) {
                Tarea t = tareas.get(r.nextInt(tareas.size()));
                tareas.remove(t);
                jugador.addTarea(t);
            }
        }
        
        start();
    }

    public void start(){
        estudiantes = new ArrayList<>();
        load(estudiantes, saveEstudiantes);
        impostores = new ArrayList<>();
        load(impostores, saveImpostores);

        //Se limpian las habitaciones por si quedan muertos de una partida anterior
        for (Tarea tarea : tareas) {
            tarea.getHabitacion().limpiaMuertos();
        }

        System.out.println("Empieza el juego!");

        int status;

        while ((status = new Round(estudiantes, impostores, tareas, sc).status())==0) {}

        switch (status) {

            case 1:   
                System.out.println("\nVictoria\n");             
                break;

            case -1:
                System.out.println("\nDerrota\n");
                break;
        
            default:
                System.out.println("\nbug\n");
                break;
        }

        while (true) {
            System.out.println("Quieres reiniciar esta partida?(s/n)");
            String input=sc.nextLine().toLowerCase();
            char c = input.length()>0?input.charAt(0):' ';
            
            if(c=='s'){
                start();
                break;
            }

            System.out.println("Quieres salir?(s/n)");
            input=sc.nextLine().toLowerCase();
            c = input.length()>0?input.charAt(0):' ';            

            if(c=='s'){
                System.exit(0);
            }
            break;
        }        
    }

    //Método genérico para cargar las listas
    <T> void load(ArrayList<T> juego, ArrayList<T> mem){
        for (T t : mem) {
            juego.add(t);
        }
    }

}
