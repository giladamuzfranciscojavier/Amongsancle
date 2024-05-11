package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Impostor extends Jugador {
    ArrayList<Jugador> eliminados;

    public Impostor(String alias){
        super(alias);
        eliminados = new ArrayList<>();
    }


    public void kill(Estudiante e, Iterator<Estudiante> es,Iterator<Jugador> j, Habitacion habitacion, HashMap<Jugador, String[]> playerStatus){
        eliminados.add(e);
        es.remove();
        j.remove();
        habitacion.addMuerto(e);
        playerStatus.put(e, new String[]{"estudiante","asesinado por "+alias});
    }
}
