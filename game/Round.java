package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import core.*;
import tools.ConsoleCodes;

public class Round {
    
    ArrayList<Impostor> impostores;
    ArrayList<Estudiante> estudiantes;
    ArrayList<Jugador> jugadores;
    ArrayList<Tarea> tareas;
    HashMap<Jugador, String[]> playerStatus;
    Scanner sc;

    public Round(ArrayList<Estudiante> est, ArrayList<Impostor> imp, ArrayList<Tarea> ta,HashMap<Jugador, String[]> ps, Scanner sc){    
        
        playerStatus=ps;
        tareas=ta;
        estudiantes = est;
        impostores = imp;
        this.sc = sc;

        boolean juicio = false;
        
        jugadores = new ArrayList<>();
        jugadores.addAll(estudiantes);jugadores.addAll(impostores);

        System.out.println("\nEmpieza la ronda!\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Jugador jugador : jugadores) {
            Tarea t = jugador.nextTarea();
            if(t!=null){
                jugador.setHabitacionActual(t.getHabitacion());
                if(jugador.getHabitacionActual().hasMuertos()){
                    System.out.println(ConsoleCodes.RED+"El jugador "+jugador.getAlias()+" ha encontrado un muerto en "+t.getHabitacion()+"!"+ConsoleCodes.RESET);
                    juicio = true;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    acusaciones();
                    break;
                }

                else{
                    System.out.println("El jugador "+jugador.getAlias()+" ha realizado la tarea "+t.getNombre()+" en "+t.getHabitacion());
                }
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }            
        }

        //Solo se mata si no se han encontrado muertos
        if(!juicio){
            for (Impostor impostor : impostores) {
                Iterator<Estudiante> it = estudiantes.iterator();
                boolean mata=false;
                while (it.hasNext()) {
                    Estudiante e = it.next();
    
                    //Detecta si hay estudiantes en la habitación
                    if(e.getHabitacionActual().equals(impostor.getHabitacionActual())){
                        //Si no ha matado todavía mata al estudiante que encuentra
                        if(!mata){
                            Iterator<Jugador> jug = jugadores.iterator();
                            Jugador j;
                            while (jug.hasNext()) {
                                j=jug.next();
                                if(j.equals(e)){
                                    break;
                                }
                            }
                            impostor.kill(e, it, jug, e.getHabitacionActual(),playerStatus);
                            if(Settings.getDebug()){
                                System.out.println(impostor.getAlias()+" ha matado a "+e.getAlias());
                            }
                            mata=true;
                        }                    
    
                        //Si ya ha matado se sigue comprobando por si hubieran testigos
                        else{
                            System.out.println("\n\n\n");

                            //Probabilidad de que el testigo acuse al impostor. En caso contrario, el impostor intentará acusar al testigo
                            if(Math.random()>Settings.getProbMentira()){
                                System.out.println(ConsoleCodes.RED+e.getAlias()+" acusa a "+impostor.getAlias()+" de asesinato!"+ConsoleCodes.RESET);
                            }
                            else{
                                System.out.println(ConsoleCodes.RED+impostor.getAlias()+" acusa a "+e.getAlias()+" de asesinato!"+ConsoleCodes.RESET);
                            }

                            if(impostores.size()>=estudiantes.size()){
                                System.out.println(ConsoleCodes.RED+"Sin embargo, los impostores son capaces de tomar el control!"+ConsoleCodes.RESET);
                            }
                            else{
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                                juicio=true;
                                break;
                            }                            
                        }
                    }
                }
            }
            if(juicio){
                acusaciones();
            }
        }            
    }

    /*Devuelve el estado final de la ronda:
        1: Juego ganado
        -1: Juego perdido
        0: Continúa el juego
    */
    int status(){
        if(impostores.size()>=estudiantes.size()){
            return -1;
        }

        if(impostores.isEmpty()){
            return 1;
        }

        //Se comprueba si los estudiantes han terminado sus tareas
        boolean completado=true;
        for (Estudiante estudiante : estudiantes) {
            if(!estudiante.isFinish()){
                completado=false;
                break;
            }
        }

        if(completado){
            return 1;
        }

        return 0;
    }







    void acusaciones(){
        System.out.println("\nEmpieza el juicio!\nQuién es el impostor?\nTiempo límite: "+Settings.getTiempoMax());
        long limite = System.currentTimeMillis()+Settings.getTiempoMax()*1000;

        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println(i+". "+jugadores.get(i).getAlias());
        }
        System.out.println(jugadores.size()+". No acusar a nadie");

        System.out.println();

        System.out.println("Acusa al impostor!\n");

        String opcion = sc.nextLine();

        int numMenu=-1;

        if(limite-System.currentTimeMillis()>0){
            if(opcion.matches("\\d+")){
                int numOpcion = Integer.parseInt(opcion);
    
                if(numOpcion>=0 && numOpcion<jugadores.size()){
                    numMenu=numOpcion;       
                    Jugador j = jugadores.get(numMenu);              
                    if(j.getClass().getName().equals("core.Impostor")){
                        jugadores.remove(j);
                        impostores.remove(j);
                        playerStatus.put(j, new String[]{"impostor","muerto por expulsión"});
                        System.out.println(ConsoleCodes.GREEN+j.getAlias()+" era un impostor!"+ConsoleCodes.RESET);
                    }
                    else{
                        jugadores.remove(j);
                        estudiantes.remove(j);
                        playerStatus.put(j, new String[]{"estudiante","muerto por expulsión"});
                        System.out.println(ConsoleCodes.RED+j.getAlias()+" NO era un impostor!"+ConsoleCodes.RESET);
                    }
                }
                else if(numOpcion==jugadores.size()){
                    System.out.println("No se ha acusado a nadie");
                }
                else{
                    System.out.println("No existe ese jugador! Se cancela el juicio!");
                }
            }
            else{
                System.out.println("Entrada errónea! Se cancela el juicio!");
            }
        }

        else{
            System.out.println("Se acabó el tiempo!");
        }    
        for (Tarea tarea : tareas) {
            tarea.getHabitacion().limpiaMuertos();
        }
    }
}
