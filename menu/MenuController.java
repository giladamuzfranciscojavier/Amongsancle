package menu;

import java.util.Scanner;

public class MenuController {

    Scanner sc;
    
    public MenuController(Scanner sc){
        this.sc=sc;
              

        Menu mainMenu = new Menu("Menú Principal", sc);
        Menu tareas = new Menu("Tareas", sc);
        Menu habitaciones = new Menu("Habitaciones", sc);
        Menu jugadores = new Menu("Jugadores", sc);        

        tareas.addMenu(new AddTarea(sc));
        tareas.addMenu(new RemoveTarea(sc));
        tareas.addMenu(new VerTareas(sc));
        mainMenu.addMenu(tareas);

        habitaciones.addMenu(new AddHabitacion(sc));
        habitaciones.addMenu(new RemoveHabitacion(sc));
        habitaciones.addMenu(new VerHabitaciones(sc));
        mainMenu.addMenu(habitaciones);

        jugadores.addMenu(new AddJugador(sc));
        jugadores.addMenu(new RemoveJugador(sc));
        jugadores.addMenu(new VerJugadores(sc));
        mainMenu.addMenu(jugadores);

        mainMenu.addMenu(new SetMaxTime(sc));
        mainMenu.addMenu(new ProbMentira(sc));
        mainMenu.addMenu(new StartGame(sc));
        mainMenu.addMenu(new Debug(sc));
        
        
        //En caso de excepción se devuelve al jugador al menú principal
        while (true) {
            try {
                mainMenu.ejecutar();
            } catch (Exception e) {    

                e.printStackTrace();

                System.out.println("\nHa ocurrido un error. Si quieres salir introduce SALIR, si quieres volver al menú principal introduce cualquier otra cosa");  
                if(sc.nextLine().equals("SALIR")){
                    System.out.println("Adios!");
                    System.exit(0);
                }          
            }
        }    
    }
}
