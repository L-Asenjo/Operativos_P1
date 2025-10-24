/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;


import java.util.concurrent.Semaphore;

/**
 *
 * @author miche_ysmoa6e
 */
public class Device {
    private int id;
    private Semaphore semaf;

    public Device(int id) {
        this.id = id;
        this.semaf = new Semaphore(1);
    }

    public Semaphore getSemaf() {
        return semaf;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    
}
