/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author miche_ysmoa6e
 */
public class Scheduler {
    
    // Tomando que 1 ciclo de ejecución del CPU son 0.00001ms
    
    private Lista processList; //Lista en la cual se guardan todos los procesos a ejecutar.
    private int memoryAvaiable;
                                     //Se agregan desde la interfaz

    public Scheduler(Lista processList, int memorySpace) {
        this.processList = processList;
        this.memoryAvaiable = memorySpace;
    }
    
    /*
    public Cola fillReadyQueue(){
        Cola readyQueue = new Cola();
        for (int i = 0; i < processList.count(); i++){
            PCB auxProcessPCB = ((Nodo) processList.get(i)).getInfoProceso().getPcb();
            if (auxProcessPCB.getStatus() == "ready"){
                readyQueue.enqueue(processList.get(i));
            } else{
                i++;
            }
        }
        return readyQueue;
    }
    */
    
    // quantum medido en ms
    public void RoundRobin (int setQuantum, Cola readyQueue, Dispatcher dispatcher){
        int quantum = setQuantum;
         
        while(readyQueue.getCount() > 0){
            var processToActivate = readyQueue.get(0);
            PCB pcbOfActiveProcess = ((Nodo)processToActivate).getInfoProceso().getPcb();
            memoryAvaiable = memoryAvaiable - ((Nodo)processToActivate).getInfoProceso().getMemorySpace();
            
            // No se si hacer un double loop aqui. En teoria no
            dispatcher.activate(pcbOfActiveProcess, processList); // ready ---> running
            Proceso toRun = dispatcher.getActiveProcess(processList);
            
            if (((Nodo)processToActivate).getInfoProceso().getTimeSpent() > quantum){
                dispatcher.deactivate(toRun);   // running --> ready
                var aux = readyQueue.get(0);
                readyQueue.dequeue();
                readyQueue.enqueue(aux);
            }
            
            if (toRun.getProcessingTime() == toRun.getTimeSpent()){
                dispatcher.deactivate(toRun);
                readyQueue.dequeue();
            }
        }
    }
    
    
    public void SPN(Cola readyQueue, Dispatcher dispatcher){
    
    }
    
    public void PriorityPlanification(Cola readyQueue, Dispatcher dispatcher){
    
    }
    
}
