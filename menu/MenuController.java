package menu;

import java.util.Scanner;

public class MenuController {

    Scanner sc;
    
    public MenuController(Scanner sc){
        this.sc=sc;
              

        Menu mainMenu = new Menu("Menú Principal", sc);
        Menu config = new Menu("Configuración", sc);
        mainMenu.addMenu(config);
        Menu tareas = new Menu("Tareas", sc);
        Menu habitaciones = new Menu("Habitaciones", sc);
        Menu jugadores = new Menu("Jugadores", sc);        

        tareas.addMenu(new AddTarea(sc));
        tareas.addMenu(new RemoveTarea(sc));
        tareas.addMenu(new VerTareas(sc));
        config.addMenu(tareas);

        habitaciones.addMenu(new AddHabitacion(sc));
        habitaciones.addMenu(new RemoveHabitacion(sc));
        habitaciones.addMenu(new VerHabitaciones(sc));
        config.addMenu(habitaciones);

        jugadores.addMenu(new AddJugador(sc));
        jugadores.addMenu(new RemoveJugador(sc));
        jugadores.addMenu(new VerJugadores(sc));
        config.addMenu(jugadores);

        config.addMenu(new SetMaxTime(sc));
        config.addMenu(new ProbMentira(sc));
        config.addMenu(new Debug(sc));        
        mainMenu.addMenu(new StartGame(sc));
        
        
        
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
