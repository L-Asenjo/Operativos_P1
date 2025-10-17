/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Eddy
 */
public class Proceso extends Thread {
    private PCB pcb;
    private String bound;
    private int ioCicles;
    private int satisfyCicles;
    private int instructions;

    public Proceso(int id, String name, String bound, int instructions) {
        this.pcb = new PCB(id, name);
        this.bound = bound;
        this.instructions = instructions;
    }

    public Proceso(int id, String name, String bound, int instructions, int ioCicles, int satisfyCicles) {
        this.pcb = new PCB(id, name);
        this.bound = bound;
        this.instructions = instructions;
        this.ioCicles = ioCicles;
        this.satisfyCicles = satisfyCicles;
    }

    public PCB getPcb() {
        return pcb;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public int getIoCicles() {
        return ioCicles;
    }

    public void setIoCicles(int ioCicles) {
        this.ioCicles = ioCicles;
    }

    public int getSatisfyCicles() {
        return satisfyCicles;
    }

    public void setSatisfyCicles(int satisfyCicles) {
        this.satisfyCicles = satisfyCicles;
    }

    public int getInstructions() {
        return instructions;
    }

    public void setInstructions(int instructions) {
        this.instructions = instructions;
    }
    
    @Override //La clase thread ya tiene una clase run
    public void run(){
    
    }
    
    // proceso.start() ----> Empieza a ejecutar el proceso. Este método hará que se ejecute el run() dentro de la clase
    // ¿Cómo podemos simular la ejecución de un proceso? ¿Qué podemos meter en run?
}

/*

El syncronized es para manejar la sección crítica

class Recurso {
    public void usar() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " está usando el recurso");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}





*/
