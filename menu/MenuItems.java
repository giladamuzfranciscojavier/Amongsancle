package menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tools.ConsoleCodes;
import tools.Settings;
import core.Habitacion;
import core.Tarea;
import game.GameController;


class AddTarea extends ComponenteMenu{

    AddTarea(Scanner sc) {
        super("Añadir tarea", sc);        
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println(ConsoleCodes.CLEAR);
        
        String t = "";
        String h = "";
        while (t.strip().length()==0) {
            System.out.println("Introduce el nombre de la tarea");
            t=sc.nextLine();
        }

        System.out.println();

        ArrayList<Habitacion> list = Settings.getSettings().getHabitaciones();

        for(int i = 0;i<list.size();i++){
            System.out.println(i+". "+list.get(i).getNombre());
        }

        System.out.println("\nSelecciona la habitación");

        String opcion = sc.nextLine();
        int numMenu=-1;

        if(opcion.matches("\\d+")){
            int numOpcion = Integer.parseInt(opcion);

            if(numOpcion>=0 && numOpcion<list.size()){
                numMenu=numOpcion;       
                h = list.get(numMenu).getNombre();                
            }
        }

        Settings.getSettings().addTarea(t, h);  
        padre.ejecutar();        
    }
    
}


class RemoveTarea extends ComponenteMenu{
    RemoveTarea(Scanner sc) {
        super("Eliminar tarea", sc);        
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println(ConsoleCodes.CLEAR);

        List<Tarea> list = Settings.getSettings().getTareas();

        for(int i = 0;i<list.size();i++){
            System.out.println(i+". "+list.get(i));
        }
        System.out.println(list.size()+". Cancelar");

        System.out.println();

        System.out.println("Elige la tarea a eliminar");

        String opcion = sc.nextLine();

        int numMenu=-1;

        if(opcion.matches("\\d+")){
            int numOpcion = Integer.parseInt(opcion);

            if(numOpcion>=0 && numOpcion<list.size()){
                numMenu=numOpcion;       
                Settings.getSettings().removeTarea(list.get(numMenu));                
            }
        }

        padre.ejecutar();    

    }
}




class VerTareas extends ComponenteMenu{
    VerTareas(Scanner sc) {
        super("Ver tareas", sc);        
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println(ConsoleCodes.CLEAR);
        for (Tarea tarea : Settings.getSettings().getTareas()) {
            System.out.println(tarea);
        }
        System.out.println("\nPresiona ENTER para continuar");
        sc.nextLine();
        padre.ejecutar();
    }
}



class AddJugador extends ComponenteMenu{
    AddJugador(Scanner sc) {
        super("Añadir Jugador", sc);        
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println(ConsoleCodes.CLEAR);
        
        String j = "";
        while (j.strip().length()==0) {
            System.out.println("Introduce el nombre del jugador");
            j=sc.nextLine();

            if(Settings.getSettings().getJugadores().contains("@"+j)){
                System.out.println("Error, ya existe un jugador con ese nombre\n");
                j="";
            }
        }
        Settings.getSettings().addJugador(j);  
        padre.ejecutar();        
    }
}



class RemoveJugador extends ComponenteMenu{
    RemoveJugador(Scanner sc) {
        super("Eliminar Jugador", sc);        
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println(ConsoleCodes.CLEAR);
        List<String> list = Settings.getSettings().getJugadores();

        for(int i = 0;i<list.size();i++){
            System.out.println(i+". "+list.get(i));
        }

        System.out.println(list.size()+". Cancelar");

        System.out.println();

        System.out.println("Elige el jugador a eliminar");

        String opcion = sc.nextLine();

        int numMenu=-1;

        if(opcion.matches("\\d+")){
            int numOpcion = Integer.parseInt(opcion);

            if(numOpcion>=0 && numOpcion<list.size()){
                numMenu=numOpcion;     
                
                Settings.getSettings().removeJugador(list.get(numMenu));    
            }
        }
        padre.ejecutar(); 
    }
}



class VerJugadores extends ComponenteMenu{
    VerJugadores(Scanner sc) {
        super("Ver Jugadores", sc);        
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println(ConsoleCodes.CLEAR);
        for (String j : Settings.getSettings().getJugadores()) {
            System.out.println(j);
        }
        System.out.println("\nPresiona ENTER para continuar");
        sc.nextLine();
        padre.ejecutar();
    }
}

class SetMaxTime extends ComponenteMenu{
    SetMaxTime(Scanner sc) {
        super("Configurar Tiempo Máximo de Respuesta. Tiempo actual: "+Settings.getSettings().getTiempoMax(), sc);
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println("Introduce el tiempo máximo de respuesta (Mínimo 5 segundos)");
        String opcion = sc.nextLine();

        if(opcion.matches("\\d{1,4}")){
            System.out.println("a");
            int numOpcion = Integer.parseInt(opcion);
            Settings.getSettings().setTiempoMax(numOpcion);
            this.nombre = "Configurar Tiempo Máximo de Respuesta. Tiempo actual: "+Settings.getSettings().getTiempoMax();
        }
        padre.ejecutar();
    }
}


class AddHabitacion extends ComponenteMenu{
    AddHabitacion(Scanner sc){
        super("Añadir habitación", sc);
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println(ConsoleCodes.CLEAR);
        
        String h = "";       

        while (h.strip().length()==0) {
            System.out.println("\nIntroduce el nombre de la habitación");
            h=sc.nextLine();
        }
        Settings.getSettings().addHabitacion(h);  
        padre.ejecutar();     

    
    }
}


class RemoveHabitacion extends ComponenteMenu{
    RemoveHabitacion(Scanner sc) {
        super("Eliminar Habitación", sc);        
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println(ConsoleCodes.CLEAR);
        List<Habitacion> list = Settings.getSettings().getHabitaciones();

        for(int i = 0;i<list.size();i++){
            System.out.println(i+". "+list.get(i).getNombre());
        }

        System.out.println(list.size()+". Cancelar");

        System.out.println();

        System.out.println("Elige la habitación");

        String opcion = sc.nextLine();

        int numMenu=-1;

        if(opcion.matches("\\d+")){
            int numOpcion = Integer.parseInt(opcion);

            if(numOpcion>=0 && numOpcion<list.size()){
                numMenu=numOpcion;                     
                Settings.getSettings().RemoveHabitacion(list.get(numMenu));    
            }
        }
        padre.ejecutar(); 
    }
}


class VerHabitaciones extends ComponenteMenu{
    VerHabitaciones(Scanner sc) {
        super("Ver Habitaciones", sc);        
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println(ConsoleCodes.CLEAR);
        for (Habitacion h : Settings.getSettings().getHabitaciones()) {
            System.out.println(h.getNombre());
        }
        System.out.println("\nPresiona ENTER para continuar");
        sc.nextLine();
        padre.ejecutar();
    }
}

class ProbMentira extends ComponenteMenu{
    ProbMentira(Scanner sc){
        super("Configurar probabilidad de acusaciones falsas. Probabilidad actual: "+(int)(Settings.getSettings().getProbMentira()*100), sc);
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println(ConsoleCodes.CLEAR);

        System.out.println("Introducir probabilidad de acusaciones falsas (0-100). Cualquier entrada no válida restablecerá el valor por defecto (50)");

        String input = sc.nextLine();

        if(input.matches("\\d+")){
            int in = Integer.parseInt(input);
            if(in>-1 && in<101){
                Settings.getSettings().setProbMentira((double) in/100.0);
            }
            else{
                Settings.getSettings().setProbMentira(.5);
            }
        }

        else{
            Settings.getSettings().setProbMentira(.5);
        }

        nombre = "Configurar probabilidad de acusaciones falsas. Probabilidad actual: "+(int)(Settings.getSettings().getProbMentira()*100);
        padre.ejecutar();
    }
}

class StartGame extends ComponenteMenu{
    StartGame(Scanner sc){
        super("Jugar", sc);
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println(ConsoleCodes.CLEAR);
        new GameController();
    }
}


class Debug extends ComponenteMenu{
    Debug(Scanner sc){
        super("Activar modo debug (se muestra quien mata a quien)", sc);
    }

    @Override
    void ejecutar() throws IOException {
        if(!Settings.getSettings().getDebug()){
            Settings.getSettings().setDebug(true);
            nombre="Desactivar modo debug (se muestra quien mata a quien)";
        }
        else{
            Settings.getSettings().setDebug(false);
            nombre="Activar modo debug (se muestra quien mata a quien)";
        }
        padre.ejecutar();
    }
}