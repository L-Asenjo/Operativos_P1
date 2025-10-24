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
    private int remainingSpace = memoryAvaiable;
                                     //Se agregan desde la interfaz

    public Scheduler(Lista processList, int memorySpace) {
        this.processList = processList;
        this.memoryAvaiable = memorySpace;
    }
    
    
    // quantum medido en ms
    public void RoundRobin (int setQuantum, Cola readyQueue, Dispatcher dispatcher){
        int quantum = setQuantum;
         
        if (readyQueue == null){
        return;}
        
        while(readyQueue.getCount() > 0){           //if
            
            PCB pcbOfActiveProcess = (PCB) readyQueue.get(0);
            System.out.println(pcbOfActiveProcess.getStatus());
            
            // verificar si el proceso ya está activado y si no lo está, activarlo   
            if (!"running".equals(pcbOfActiveProcess.getStatus())){
                dispatcher.activate(pcbOfActiveProcess, getProcessList()); // ready ---> running
            }
            
            Proceso toRun = dispatcher.getActiveProcess(getProcessList());
            
            // while
            if ( pcbOfActiveProcess.getTimesIn() > quantum){
                toRun.setTotalTimeSpent(toRun.getTimeSpent());
                dispatcher.deactivate(toRun);   // running --> ready
                var aux = readyQueue.get(0);
                readyQueue.dequeue();
                readyQueue.enqueue(aux);
            }
            
            if (toRun.getProcessingTime() == toRun.getTotalTimeSpent()){
                dispatcher.deactivate(toRun);
                readyQueue.dequeue();
            }
        }
    }
    
    
    public void SPN(Cola readyQueue, Dispatcher dispatcher){
        while(readyQueue.getCount() > 0){       //if readyQueue.getCount() > 0
            
            PCB pcbOfActiveProcess = (PCB) readyQueue.get(0);
            
            // verificar si el proceso ya está activado y si no lo está, activarlo
            if (pcbOfActiveProcess.getStatus() != "running"){ //
                dispatcher.activate(pcbOfActiveProcess, getProcessList()); // ready ---> running
            }
            
            // while running
            Proceso toRun = dispatcher.getActiveProcess(getProcessList());
            if (toRun.getProcessingTime() == toRun.getTimeSpent()){
                dispatcher.deactivate(toRun);
                readyQueue.dequeue();
            }
        }
    }
    
    public void PriorityPlanification(Cola readyQueue, Dispatcher dispatcher, Lista priorityList){
        for (int i = 0; i < priorityList.count(); i++){
            Cola act = (Cola)priorityList.get(i);
            
            PCB pcbOfActiveProcess = (PCB) act.get(0);
                
            while (act.getCount() > 0){ //if act.getCount() > 0
                if (pcbOfActiveProcess.getStatus() != "running"){
                    dispatcher.activate(pcbOfActiveProcess, act.getQueue());
                }
                
                //while running
                Proceso toRun = dispatcher.getActiveProcess(getProcessList());
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
                dispatcher.activate(((PCB)(processToActivate)), getProcessList());
                
                readyQueue.getQueue().remove(readyQueue.getQueue().indexOfPCB(processToActivate));
                break;
            }
            i++;
        }
        
        Proceso toRun = dispatcher.getActiveProcess(getProcessList());
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
            dispatcher.activate(((PCB)(processToActivate)), getProcessList());
            
            Proceso toRun = dispatcher.getActiveProcess(getProcessList());
            
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
            dispatcher.activate(((PCB)(processToActivate)), getProcessList());
            
            Proceso toRun = dispatcher.getActiveProcess(getProcessList());
            
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
            PCB aux = (PCB)readyQueue.get(j);
            
            for (int x=0; x < priorityList.count(); x++){
                Cola act = (Cola)priorityList.get(x);
                
                if(aux.getPriority() == x & !act.getQueue().contains(aux)){
                    act.enqueue(aux);
                }
            }
        }
    
        return priorityList;
    }
    
    /*
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
        int i = 0;
        int n = readyQueue.getCount();
        while (i < n){
            Nodo aux = (Nodo)readyQueue.get(i);
            int j = i + 1;
            
            while (j < n){
                Nodo aux2 = (Nodo)readyQueue.get(j);
                if (aux.getInfoPCB().getPriorityFSS()>aux2.getInfoPCB().getPriorityFSS()){
                    swapNodes(aux, aux2);
                }
                j++;
            }
            i++;
        }
    }
    
    public void recalculateFSS (Cola readyQueue, int priority) {
        int i = 0;
        int n = readyQueue.getCount();
        int timesInTotal = 0;
        int priorityCount = 0;
        while (i < n) {
            if (((Nodo)readyQueue.get(i)).getInfoPCB().getPriority() == priority) {
                timesInTotal = ((Nodo)readyQueue.get(i)).getInfoPCB().getTimesIn() + timesInTotal;
                priorityCount++;
            }
            i++;
        }
        i = 0;
        while (i < n) {
            if (((Nodo)readyQueue.get(i)).getInfoPCB().getPriority() == priority) {
                int priorityNode = ((Nodo)readyQueue.get(i)).getInfoPCB().getPriority();
                int timesIn = ((Nodo)readyQueue.get(i)).getInfoPCB().getTimesIn();
                
                float newPriorityFSS = priorityNode + timesIn + (timesInTotal/priorityCount);
                
                ((Nodo)readyQueue.get(i)).getInfoPCB().setPriorityFSS(newPriorityFSS);
            }
            i++;
        }
    }
    
    
    public void reorganiceSRT (Cola readyQueue){
        int i = 0;
        int n = readyQueue.getCount();
        while (i < n){
            Nodo aux = (Nodo)readyQueue.get(i);
            int j = i + 1;
            
            while (j < n){
                Nodo aux2 = (Nodo)readyQueue.get(j);
                if (aux.getInfoPCB().getPriorityFSS()>aux2.getInfoPCB().getPriorityFSS()){
                    swapNodes(aux, aux2);
                }
                j++;
            }
            i++;
        }
    }
    */
    public int getPriorities(Cola readyQueue){
        Lista priorities = new Lista();
        
        for (int i = 0; i < readyQueue.getCount(); i++){
            PCB aux = (PCB)readyQueue.get(i);
            if (!priorities.contains(aux.getPriority())){
                priorities.add(aux.getPriority());
            }
        }
        
        return priorities.count();
    }

    public int getTimesIn(Cola readyQueue){
        Lista timesIn = new Lista();
        
        for (int i = 0; i < readyQueue.getCount(); i++){
            PCB aux = (PCB)readyQueue.get(i);
            
            if (!timesIn.contains(aux.getTimesIn())){
                timesIn.add(aux.getTimesIn());
            }
        }
        
        return timesIn.count();
    }
    
    /*
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
    */
    

    /**
     * @return the processList
     */
    public Lista getProcessList() {
        return processList;
    }

    /**
     * @param processList the processList to set
     */
    public void setProcessList(Lista processList) {
        this.processList = processList;
    }

    /**
     * @return the memoryAvaiable
     */
    public int getMemoryAvaiable() {
        return memoryAvaiable;
    }

    /**
     * @param memoryAvaiable the memoryAvaiable to set
     */
    public void setMemoryAvaiable(int memoryAvaiable) {
        this.memoryAvaiable = memoryAvaiable;
    }

    /**
     * @return the remainingSpace
     */
    public int getRemainingSpace() {
        return remainingSpace;
    }

    /**
     * @param remainingSpace the remainingSpace to set
     */
    public void setRemainingSpace(int remainingSpace) {
        this.remainingSpace = remainingSpace;
    }
    
    
    
}
