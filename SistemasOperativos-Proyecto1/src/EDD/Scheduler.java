/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author miche_ysmoa6e
 */
public class Scheduler extends Thread {
    
    Lista processList = new Lista(); //Lista en la cual se guardan los procesos a ejecutar
    // Creo que deberíamos hacer que se reinicien cuando se cambia de planificación
    
    public void RoundRobin (int setQuantum){
        Cola readyQueue = new Cola();
        int nextInLine = 0; //Índice del siguiente elemento a ejecutar
        int quantum = setQuantum;
        int timeStamp = 0; //Manejar el tiempo utilizado por el proceso
        
        
    }
    
    /*
    
    Por definir, lo comenté para que no salga el símbolo de error
    public SPN(){
    
    }
    
    public PriorityPlanification(){
    
    }
    */
    
}
