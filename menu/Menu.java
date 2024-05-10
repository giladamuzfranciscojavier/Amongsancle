package menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tools.ConsoleCodes;

public class Menu extends ComponenteMenu{
    List<ComponenteMenu> hijos = new ArrayList<>();
    

    Menu(String nombre, Scanner sc){
        super(nombre, sc);
    }

    void addMenu(ComponenteMenu cm){
        hijos.add(cm);
        cm.padre=this;
    }

    @Override
    public void ejecutar() throws IOException{
        int numMenu=-1;

        System.out.print(ConsoleCodes.CLEAR);
        while (numMenu==-1) {
            System.out.println();
            for (int i = 0; i < hijos.size(); i++) {
                System.out.println(i+". "+hijos.get(i).nombre);
            }
            String txt = padre==null?"Salir":"Cancelar";
            System.out.println(hijos.size()+". "+txt);
            System.out.println();

            String opcion = sc.nextLine();

            if(opcion.matches("\\d+")){
                int numOpcion = Integer.parseInt(opcion);

                if(numOpcion>=0 && numOpcion<=hijos.size()){
                    numMenu=numOpcion;
                }
            }
        }        

        if(numMenu==hijos.size()){
            if(padre==null){
                System.out.println("Adios!");
                System.exit(0);
            }
            else{
                padre.ejecutar();
            }
        }
        else{
            hijos.get(numMenu).ejecutar();
        }
    }
}
