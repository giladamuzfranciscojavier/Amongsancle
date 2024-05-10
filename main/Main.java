package main;

import core.Settings;
import menu.MenuController;
import tools.ScannerFactory;

public class Main {
    //El main se limita a inicializar las opciones y el controlador de menú
    public static void main(String[] args) {                
        Settings.init();
        new MenuController(ScannerFactory.getScanner());
    }
}
