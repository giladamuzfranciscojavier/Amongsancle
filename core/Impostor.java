package core;

import java.util.ArrayList;

public class Impostor extends Jugador {
    ArrayList<Jugador> eliminados;

    public Impostor(String alias){
        super(alias);
        eliminados = new ArrayList<>();
    }
}
