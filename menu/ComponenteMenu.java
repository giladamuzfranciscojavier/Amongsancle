package menu;

import java.io.IOException;
import java.util.Scanner;

abstract class ComponenteMenu {
    ComponenteMenu padre;
    String nombre;
    Scanner sc;


    ComponenteMenu(String nombre, Scanner sc){
        this.nombre=nombre;
        this.sc=sc;
    }

    abstract void ejecutar() throws IOException;
    
}