package tools;

public class Temporizador extends Thread{

    long limite;
    Object padre;

    public Temporizador(long limite, Object padre){
        this.limite=System.currentTimeMillis()+limite;
        this.padre=padre;
    }    

    @Override
    public void run() {
        try {
            update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void update() throws InterruptedException{        
        long current = limite-System.currentTimeMillis();
        if(current>-1){
            System.out.println((int) Math.ceil((double) current/10)/100);
            while (limite-System.currentTimeMillis()>current-1000) {}
            update();
        }                
    }
}