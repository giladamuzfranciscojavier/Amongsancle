package core;

public class Tarea implements Comparable<Tarea> {
    private String nombre;
    private Habitacion habitacion;

    public Tarea(String nombre, Habitacion habitacion){
        this.nombre=nombre;
        this.habitacion=habitacion;
    }

    @Override
    public String toString() {
        return nombre + " en "+habitacion.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((habitacion == null) ? 0 : habitacion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tarea other = (Tarea) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (habitacion == null) {
            if (other.habitacion != null)
                return false;
        } else if (!habitacion.equals(other.habitacion))
            return false;
        return true;
    }

    @Override
    public int compareTo(Tarea t) {
        if(habitacion.compareTo(t.habitacion)<0){
            return -1;
        }
        else if(habitacion.compareTo(t.habitacion)>0){
            return 1;
        }

        else{
            if(nombre.toLowerCase().compareTo(t.nombre.toLowerCase())>1){
                return 1;
            }
            else if(nombre.toLowerCase().compareTo(t.nombre.toLowerCase())<1){
                return -1;
            }
            else{
                return 0;
            }
        }
    }
}
