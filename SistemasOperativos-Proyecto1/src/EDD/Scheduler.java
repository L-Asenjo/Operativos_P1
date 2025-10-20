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
            PCB pcbOfActiveProcess = ((Nodo)processToActivate).getInfoProceso().getPcb();
            
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
    
    public void Feedback(int setQuantum, Cola readyQueue, Lista readyQueueList, Dispatcher dispatcher) {
        int quantum = setQuantum;
        int i = 0;
        Object processToActivate = null;
        while (i < readyQueueList.count()){
            if (((Cola)readyQueueList.get(i)).getCount() > 0){
                
                processToActivate = (((Cola)readyQueueList.get(i)).dequeue());
                dispatcher.activate(((Nodo)(processToActivate)).getInfoPCB(), processList);
                
                readyQueue.getQueue().remove(readyQueue.getQueue().indexOfPCB(processToActivate));
                break;
            }
            i++;
        }
        
        Proceso toRun = dispatcher.getActiveProcess(processList);
        toRun.getPcb().setTimesIn(toRun.getPcb().getTimesIn()+1);
        while (toRun.getPcb().getStatus() == "running"){
            if (toRun.getTimeSpent() > quantum || toRun.getProcessingTime() == toRun.getTimeSpent()){
                dispatcher.deactivate(toRun);   // running --> ready
            }
        }
        if (toRun.getProcessingTime() == toRun.getTimeSpent()) {
            /*se termina el proceso*/
        } else {
            if (toRun.getPcb().getTimesIn() < readyQueueList.count()) {
                ((Cola) readyQueueList.get(toRun.getPcb().getTimesIn())).enqueue(toRun);
            } else {
                Cola aux = new Cola();
                readyQueueList.add(aux);
                ((Cola) readyQueueList.get(toRun.getPcb().getTimesIn())).enqueue(toRun);
            }
        }
    }

    public void FSS(int setQuantum, Cola readyQueue, Dispatcher dispatcher) {
        int quantum = setQuantum;
        if (readyQueue.getCount() > 0) {
            
            var processToActivate = readyQueue.dequeue();
            dispatcher.activate(((Nodo)processToActivate).getInfoPCB(), processList);
            
            Proceso toRun = dispatcher.getActiveProcess(processList);
            
            while (toRun.getPcb().getStatus() == "running"){
                if (toRun.getTimeSpent() > quantum || toRun.getProcessingTime() == toRun.getTimeSpent()){
                    dispatcher.deactivate(toRun);   // running --> ready
                }
                }
                if (toRun.getProcessingTime() == toRun.getTimeSpent()) {
                    /*se termina el proceso*/
                } else {
                    readyQueue.enqueue(toRun);
                }
        }
    }
    
    public void SRT (Cola readyQueue, Dispatcher dispatcher) {
        if (readyQueue.getCount() > 0) {
            var processToActivate = readyQueue.dequeue();
            dispatcher.activate(((Nodo)processToActivate).getInfoPCB(), processList);
            
            Proceso toRun = dispatcher.getActiveProcess(processList);
            
            while (toRun.getPcb().getStatus() == "running"){
                if (toRun.getProcessingTime() == toRun.getTimeSpent()){
                    dispatcher.deactivate(toRun);   // running --> ready
                }
                }
                if (toRun.getProcessingTime() == toRun.getTimeSpent()) {
                    /*se termina el proceso*/
                } else {
                    readyQueue.enqueue(toRun);
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
                
                if(aux.getInfoProceso().getPriority() == x){
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
    
    public void reorganiceFeedback (Cola readyQueue, Lista readyQueueList) {
        
        if (readyQueueList.count() < getTimesIn(readyQueue)){
            int maxTimesIn = getTimesIn(readyQueue);
            
            int i = readyQueueList.count();
            
            while (i < maxTimesIn) {
                Cola aux = new Cola();
                readyQueueList.add(aux);
                i++;
            }
        }
        
        int i = 0;
        while (i < readyQueue.getCount()) {
            int j = 0;
            while (j < ((Cola)readyQueueList.get(i)).getCount()){
                if (!(((Cola)readyQueueList.get(j)).getContains(readyQueue.get(i)))) {
                    ((Cola)readyQueueList.get(j)).enqueue(readyQueue.get(i));
                }
                j++;
            }
            i++;
        }
    }
    
    public void reorganiceFSS (Cola readyQueue){
        int i = 1;
        int n = readyQueue.getCount();
        while (i < n){
            Nodo aux = (Nodo)readyQueue.get(i);
            int j = i - 1;
            
            while (j >= 0 && ((Nodo)readyQueue.get(j)).getInfoPCB().getPriorityFSS() > aux.getInfoPCB().getPriorityFSS()){
                readyQueue.getQueue();
            }
            /*
                esto esta incompleto pero no doy más por hoy
            */
            i++;
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

    public int getTimesIn(Cola readyQueue){
        Lista timesIn = new Lista();
        
        for (int i = 0; i < readyQueue.getCount(); i++){
            var aux = (Nodo)readyQueue.get(i);
            
            if (!timesIn.contains(aux.getInfoPCB().getTimesIn())){
                timesIn.add(aux.getInfoPCB().getTimesIn());
            }
        }
        
        return timesIn.count();
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
