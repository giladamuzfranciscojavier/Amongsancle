package tools;

import java.util.Scanner;

public class ScannerFactory {
    static Scanner sc;

    public static Scanner getScanner(){
        if(sc==null){
            sc=new Scanner(System.in);
        }
        return sc;
    }
}
