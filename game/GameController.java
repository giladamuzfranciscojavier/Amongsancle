package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import core.*;
import tools.ScannerFactory;

public class GameController {    

    ArrayList<Estudiante> estudiantes;
    ArrayList<Impostor> impostores;
    ArrayList<Tarea> tareas;
    ArrayList<String> habitaciones;
    Scanner sc;

    public GameController(){
        estudiantes = new ArrayList<>();
        impostores = new ArrayList<>();
        tareas = Settings.getTareas();
        habitaciones = new ArrayList<>();
        sc = ScannerFactory.getScanner();

        ArrayList<String> tempjugadores = Settings.getJugadores();
        Random r = new Random();
        //Número máximo de impostores: la mitad del total de jugadores menos uno
        int sus = r.nextInt(tempjugadores.size()/2-1);
        
        //Asigna impostores hasta el límite generado anteriormente
        while (impostores.size()<sus) {
            //Obtiene un nombre aleatorio de la lista de jugadores, lo elimina de la lista y crea un impostor con dicho nombre
            String temp = tempjugadores.get(r.nextInt(tempjugadores.size()));
            tempjugadores.remove(temp);
            impostores.add(new Impostor(temp));
        }

        //El resto de jugadores serán estudiantes
        for (String string : tempjugadores) {
            estudiantes.add(new Estudiante(string));
        }
    }

    public void start(){
        System.out.println("Empieza el juego!");

        
    }

}
