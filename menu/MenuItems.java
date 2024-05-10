package menu;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import tools.ConsoleCodes;
import core.Settings;
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

        while (h.strip().length()==0) {
            System.out.println("\nIntroduce el nombre de la habitación en la que se realiza la tarea");
            h=sc.nextLine();
        }
        Settings.addTarea(t, h);  
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

        System.out.println(ConsoleCodes.CLEAR);
        List<Tarea> list = Settings.getTareas();

        for(int i = 0;i<list.size();i++){
            System.out.println(i+". "+list.get(i));
        }
        System.out.println(list.size()+". Cancelar");

        System.out.println();

        System.out.println("Elige la tarea a eliminar");

        String opcion = sc.nextLine();

        @SuppressWarnings("unused")
        int numMenu=-1;

        if(opcion.matches("\\d+")){
            int numOpcion = Integer.parseInt(opcion);

            if(numOpcion>=0 && numOpcion<list.size()){
                numMenu=numOpcion;       
                Settings.RemoveTarea(list.get(numOpcion));                
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
        for (Tarea tarea : Settings.getTareas()) {
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

            if(Settings.getJugadores().contains("@"+j)){
                System.out.println("Error, ya existe un jugador con ese nombre\n");
                j="";
            }
        }
        Settings.addJugador(j);  
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
        List<String> list = Settings.getJugadores();

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
                
                Settings.RemoveJugador(list.get(numMenu));    
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
        for (String j : Settings.getJugadores()) {
            System.out.println(j);
        }
        System.out.println("\nPresiona ENTER para continuar");
        sc.nextLine();
        padre.ejecutar();
    }
}

class SetMaxTime extends ComponenteMenu{
    SetMaxTime(Scanner sc) {
        super("Configurar Tiempo Máximo de Respuesta. Tiempo actual: "+Settings.getTiempoMax(), sc);
    }

    @Override
    void ejecutar() throws IOException {
        System.out.println("Introduce el tiempo máximo de respuesta (Mínimo 5 segundos)");
        String opcion = sc.nextLine();

        if(opcion.matches("\\d{1,4}")){
            System.out.println("a");
            int numOpcion = Integer.parseInt(opcion);
            Settings.setTiempoMax(numOpcion);
            this.nombre = "Configurar Tiempo Máximo de Respuesta. Tiempo actual: "+Settings.getTiempoMax();
        }
    }
}


class StartGame extends ComponenteMenu{
    StartGame(Scanner sc){
        super("Jugar", sc);
    }

    @Override
    void ejecutar() throws IOException {
        GameController game = new GameController();
        game.start();
    }
}