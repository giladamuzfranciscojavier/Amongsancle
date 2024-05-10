package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import core.*;

public class Round {
    

    public Round(ArrayList<Estudiante> estudiantes, ArrayList<Impostor> impostores, Scanner sc){        
        Random r = new Random();

        //Lista de todos los jugadores para que la propia lógica del juego no incrimine a los impostores
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.addAll(estudiantes);jugadores.addAll(impostores);

        //Mapa que contiene los jugadores que han muerto en cada habitación
        HashMap<String, ArrayList<Estudiante>> habitaciones = new HashMap<>();
        for (Tarea t : Settings.getTareas()) {
            if(!habitaciones.containsKey(t.getHabitacion())){
                habitaciones.put(t.getHabitacion(), new ArrayList<>());
            }
        }

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

        System.out.println("\nEmpieza la ronda!");

        for (Jugador jugador : jugadores) {
            Tarea t = jugador.nextTarea();
            if(t!=null){
                System.out.println("El jugador "+jugador.getAlias()+" está realizando la tarea "+t.getNombre()+" en "+t.getHabitacion());
            }            
        }

    }
}
