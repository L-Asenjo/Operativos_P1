/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author miche_ysmoa6e
 */
public class Device {
    private int id;
    private Semaforo semaf;

    public Device(int id) {
        this.id = id;
        this.semaf = new Semaforo();
    }

    public Semaforo getSemaf() {
        return semaf;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    
}
