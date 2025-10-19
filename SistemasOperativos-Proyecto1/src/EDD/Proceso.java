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
    private int instructions;
    private double processingTime = instructions * 0.005; //5ms por cada instruccion
    private int timeSpent = 0;
    private int memorySpace = instructions * 4;
    private int priority;
    private int timesIn;

    public Proceso(int id, String name, String bound, int instructions) {
        this.pcb = new PCB(id, name);
        this.bound = bound;
        this.instructions = instructions;
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

    public int getInstructions() {
        return instructions;
    }

    public void setInstructions(int instructions) {
        this.instructions = instructions;
    }
    
    /**
     * @return the processingTime
     */
    public double getProcessingTime() {
        return processingTime;
    }

    /**
     * @return the timeSpent
     */
    public int getTimeSpent() {
        return timeSpent;
    }
    
    /**
     * @return the timeSpent
     */
    public int getMemorySpace() {
        return memorySpace;
    }
    
    @Override //La clase thread ya tiene una clase run
    public void run(){
        
        while (getTimeSpent() < getProcessingTime()){
            timeSpent++;
            try {
                    Thread.sleep(1); // pausa de 1 ms
                } catch (InterruptedException e) {
                    System.out.println("Hilo interrumpido");
                    break; // salir del bucle si se interrumpe
                }  
        }
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
