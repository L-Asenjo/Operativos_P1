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
    
    
    // quantum medido en ms
    public void RoundRobin (int setQuantum, Cola readyQueue, Dispatcher dispatcher){
        int quantum = setQuantum;
         
        while(readyQueue.getCount() > 0){
            var processToActivate = readyQueue.get(0);
            PCB pcbOfActiveProcess = ((Nodo)processToActivate).getInfoPCB();
            
            // verificar si el proceso ya está activado y si no lo está, activarlo
            if (pcbOfActiveProcess.getStatus() != "running"){
                dispatcher.activate(pcbOfActiveProcess, processList); // ready ---> running
            }
            
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
        while(readyQueue.getCount() > 0){
            var processToActivate = readyQueue.get(0);
            PCB pcbOfActiveProcess = ((Nodo)processToActivate).getInfoProceso().getPcb();
            
            // verificar si el proceso ya está activado y si no lo está, activarlo
            if (pcbOfActiveProcess.getStatus() != "running"){
                dispatcher.activate(pcbOfActiveProcess, processList); // ready ---> running
            }
            
            Proceso toRun = dispatcher.getActiveProcess(processList);
            if (toRun.getProcessingTime() == toRun.getTimeSpent()){
                dispatcher.deactivate(toRun);
                readyQueue.dequeue();
            }
        }
    }
    
    public void PriorityPlanification(Cola readyQueue, Dispatcher dispatcher, Lista priorityList){
        for (int i = 0; i < priorityList.count(); i++){
            Cola act = (Cola)priorityList.get(i);
            var processToActivate = act.get(0);
            PCB pcbOfActiveProcess = ((Nodo)processToActivate).getInfoProceso().getPcb();
                
            while (act.getCount() > 0){
                if (pcbOfActiveProcess.getStatus() != "running"){
                    dispatcher.activate(pcbOfActiveProcess, act.getQueue());
                }
                
                Proceso toRun = dispatcher.getActiveProcess(processList);
                if (toRun.getProcessingTime() == toRun.getTimeSpent()){
                    dispatcher.deactivate(toRun);
                    act.dequeue();
                }
            }
        }
    }
    
    //La lista de prioridades es una lista que contiene las prioridades
    public Lista reorganicePriorityPlanification(Cola readyQueue, Lista priorityList){
        
        // Creando la cantidad de colas necesarias segun las prioridades existentes
        if (priorityList.count() < 1){
            int maxpriority = getPriorities(readyQueue);
            
            for (int i = 0; i < maxpriority; i++){
                Cola aux = new Cola();
                priorityList.add(aux);
            }
        } 
        
        // Agregar cada proceso a su lista de prioridad correspondiente
        for (int j=0; j < readyQueue.getCount(); j++){
            Nodo aux = (Nodo)readyQueue.get(j);
            
            for (int x=0; x < priorityList.count(); x++){
                Cola act = (Cola)priorityList.get(x);
                
                if(aux.getInfoProceso().getPriority() == x & !act.getQueue().contains(aux)){
                    act.enqueue(aux.getInfoProceso());
                }
            }
        }
    
        return priorityList;
    }
    
    public void reorganiceSPN(Cola readyQueue){
        for (int i = 0; i < readyQueue.getCount()-1; i++){
            
            Nodo first = (Nodo)readyQueue.get(i);
            Nodo next = (Nodo)readyQueue.get(i+1);
            if (first == null || next == null){break;}
            
            Proceso actualProcess = first.getInfoProceso();
            Proceso nextProcess = next.getInfoProceso();
            if (actualProcess == null || nextProcess == null){break;}

            // Se intercambian los procesos dentro de los nodos
            if (actualProcess.getProcessingTime() > nextProcess.getProcessingTime()){
                swapNodes(first, next);
            }
        }
    }
    
    public int getPriorities(Cola readyQueue){
        Lista priorities = new Lista();
        
        for (int i = 0; i < readyQueue.getCount(); i++){
            Nodo aux = (Nodo)readyQueue.get(i);
            if (!priorities.contains(aux.getInfoProceso().getPriority())){
                priorities.add(aux.getInfoProceso().getPriority());
            }
        }
        
        return priorities.count();
    }
    
    public void swapNodes(Nodo first, Nodo next){
        // Intercambio de procesos
        Proceso auxProcess = first.getInfoProceso();
        first.setInfoProceso(next.getInfoProceso());
        next.setInfoProceso(auxProcess);
        
        // Intercambio de PCB
        PCB auxPBC = first.getInfoPCB();
        first.setInfoPCB(next.getInfoPCB());
        next.setInfoPCB(auxPBC);
        
        // Intercambio de Device
        Device auxDevice = first.getInfoDevice();
        first.setInfoDevice(next.getInfoDevice());
        next.setInfoDevice(auxDevice);
    }
    
    
    
}
