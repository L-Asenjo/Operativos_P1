/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Eddy
 */
public class Semaforo {
    private Cola queue;
    private int available;

    public Semaforo(int id) {
        this.queue = new Cola();
        this.available = 1;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
    
    public boolean isAvailable(){
        return getAvailable()>0;
    }
    
    public void newRequest(Object j){
        int update = getAvailable() - 1;
        setAvailable(update);
        
        if (!isAvailable()){
            queue.enqueue(j);
        }
    }
    
    public Object nextRequest(){
        int update = getAvailable() + 1;
        setAvailable(update);
        
        if (!isAvailable()) {
            Object next = queue.dequeue();
            return next;
        } else {
            return null;
        }
    }
}
