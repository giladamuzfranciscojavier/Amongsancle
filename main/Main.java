package main;

import menu.MenuController;
import tools.*;

public class Main {
    public static void main(String[] args) {            
        System.out.println(ConsoleCodes.CLEAR);    
        new MenuController(ScannerFactory.getScanner());
    }
}
