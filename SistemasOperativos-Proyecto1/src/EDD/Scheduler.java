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
    private Lista deviceTable = new Lista();
                                     //Se agregan desde la interfaz

    public Scheduler(Lista processList, int memorySpace, Lista deviceTable) {
        this.processList = processList;
        this.memoryAvaiable = memorySpace;
        this.deviceTable = deviceTable;
        this.remainingSpace = memoryAvaiable;
    }
    
    
    // quantum medido en ms
    public void RoundRobin (int setQuantum, Cola readyQueue, Dispatcher dispatcher, Cola blockedQueue){
        int quantum = setQuantum;
         
        if (readyQueue.getCount() > 0){
            PCB processToActivate = (PCB)readyQueue.get(0);
            
            // verificar si el proceso ya está activado y si no lo está, activarlo   
            if (processToActivate.getStatus() != "running"){
                dispatcher.activate(processToActivate, processList); // ready ---> running
            }
            
            Proceso toRun = dispatcher.getActiveProcess(processList);
            
            while(toRun.getPcb().getStatus() == "running") {
                System.out.println("is running");
                if (toRun.getTimeSpent() >= quantum){
                    dispatcher.deactivate(toRun);   // running --> ready
                    var aux = readyQueue.get(0);
                    readyQueue.dequeue();
                    readyQueue.enqueue(aux);
                }
            
                if (toRun.getBound() == "IO" && toRun.getPcb().getPc()-1 == toRun.getInterruptAt()){
                    toRun.getPcb().setStatus("blocked");
                    blockedQueue.enqueue(toRun.getPcb());
                    try {
                        toRun.sleep(toRun.getIoCicles()*1000);
                        accessDevice(toRun, dispatcher, blockedQueue);
                    } 
                    catch(InterruptedException e) {
                         // this part is executed when an exception (in this example InterruptedException) occurs
                         System.out.println("en catch" + e);
                    }                }                
                
                if (toRun.getProcessingTime() <= toRun.getTotalTimeSpent()){
                    dispatcher.deactivate(toRun);
                    toRun.getPcb().setStatus("terminated");
                    System.out.println("proceso terminado");
                    readyQueue.dequeue();
                }
                try {
                    Thread.sleep(1000);
                } 
                catch(InterruptedException e) {
                     // this part is executed when an exception (in this example InterruptedException) occurs
                     System.out.println("en catch" + e);
                }
            }
        }
    }
    
    
    public void SPN(Cola readyQueue, Dispatcher dispatcher){
        if (readyQueue.getCount() > 0){
            var processToActivate = readyQueue.get(0);
            PCB pcbOfActiveProcess = ((Nodo)processToActivate).getInfoProceso().getPcb();
            
            // verificar si el proceso ya está activado y si no lo está, activarlo
            if (pcbOfActiveProcess.getStatus() != "running"){ //
                dispatcher.activate(pcbOfActiveProcess, processList); // ready ---> running
            }
            
            // while running
            Proceso toRun = dispatcher.getActiveProcess(processList);
            
            while(toRun.getPcb().getStatus() == "running"){
                if (toRun.getProcessingTime() == toRun.getTimeSpent()){
                    dispatcher.deactivate(toRun);
                    readyQueue.dequeue();
                }
                if (toRun.getPcb().getPc()-1 == toRun.getInterruptAt()){
                    toRun.getPcb().setStatus("blocked");
                    toRun.interrupt();
                }
            }
        }
    }
    
    public void PriorityPlanification(Cola readyQueue, Dispatcher dispatcher, Lista priorityList){
        for (int i = 0; i < priorityList.count(); i++){
            Cola act = (Cola)priorityList.get(i);
            var processToActivate = act.get(0);
            PCB pcbOfActiveProcess = ((Nodo)processToActivate).getInfoProceso().getPcb();
                
            if (act.getCount() > 0){
                if (pcbOfActiveProcess.getStatus() != "running"){
                    dispatcher.activate(pcbOfActiveProcess, act.getQueue());
                }
                
                //while running
                Proceso toRun = dispatcher.getActiveProcess(processList);
                while(toRun.getPcb().getStatus() == "running") {
                    if (toRun.getProcessingTime() == toRun.getTimeSpent()){
                    dispatcher.deactivate(toRun);
                    act.dequeue();
                    }
                    if (toRun.getPcb().getPc()-1 == toRun.getInterruptAt()){
                        toRun.getPcb().setStatus("blocked");
                        toRun.interrupt();
                    }
                }
            }
        }
    }
    
    public void Feedback(int setQuantum, Cola readyQueue, Lista readyQueueList, Dispatcher dispatcher, Cola blockedQueue) {
        System.out.println("en feedback");
        int quantum = setQuantum;
        int i = 0;
        PCB processToActivate = null;
        while (i < readyQueueList.count()){
            if (((Cola)readyQueueList.get(i)).getCount() > 0){
                System.out.println("activando");
                processToActivate = (PCB)(((Cola)readyQueueList.get(i)).dequeue());
                System.out.println("id del proceso" + processToActivate.getId());
                dispatcher.activate(processToActivate, processList);
                System.out.println("status del pta" + processToActivate.getStatus());
                readyQueue.getQueue().remove(readyQueue.getQueue().indexOf(processToActivate));
                break;
            }
            i++;
        }
        
        Proceso toRun = dispatcher.getActiveProcess(processList);
        /*if (toRun.getBound() == "IO" && toRun.getInterruptAt() == toRun.getPcb().getPc()-1){
            toRun.getPcb().setStatus("blocked");
        } else {
            toRun.getPcb().setStatus("ready");
        }*/
        System.out.println(toRun.getPcb().getTimesIn());        
        toRun.getPcb().setTimesIn(toRun.getPcb().getTimesIn()+1);
        System.out.println(toRun.getPcb().getStatus());
        System.out.println(toRun.getBound());
        System.out.println(toRun.getPcb().getId());
        while (toRun.getPcb().getStatus() == "running"){
            /*System.out.println("is running");
            System.out.println("time spent:" + toRun.getTimeSpent());
            System.out.println("quantum" + quantum);*/
            System.out.println(toRun.getProcessingTime() <= toRun.getTotalTimeSpent());
            System.out.println(toRun.getTimeSpent() == quantum);
            if (toRun.getTimeSpent() >= quantum || toRun.getProcessingTime() <= toRun.getTotalTimeSpent()){
                System.out.println("desactivando");
                dispatcher.deactivate(toRun);   // running --> ready
            }
            System.out.println(toRun.getPcb().getPc()-1);
            System.out.println(toRun.getInterruptAt());
            System.out.println(toRun.getPcb().getPc()-1 == toRun.getInterruptAt());
            if (toRun.getBound() == "IO" && toRun.getPcb().getPc()-1 == toRun.getInterruptAt()){
                toRun.getPcb().setStatus("blocked");
                System.out.println("bloqueado");
                blockedQueue.enqueue(toRun.getPcb());
                try {
                    toRun.sleep(toRun.getIoCicles()*1000);
                    accessDevice(toRun, dispatcher, blockedQueue);
                } 
                catch(InterruptedException e) {
                     // this part is executed when an exception (in this example InterruptedException) occurs
                     System.out.println("en catch" + e);
                }
                
            }
             try {
                Thread.sleep(1000);
            } 
            catch(InterruptedException e) {
                 // this part is executed when an exception (in this example InterruptedException) occurs
                 System.out.println("en catch" + e);
            }
            
        }
        System.out.println("ptime:" + toRun.getProcessingTime());
        System.out.println("total time spent:" + toRun.getTotalTimeSpent());
        if (toRun.getProcessingTime() <= toRun.getTotalTimeSpent()) {
            toRun.getPcb().setStatus("terminated");
        } else {
            if (toRun.getPcb().getTimesIn() < readyQueueList.count()) {
                ((Cola) readyQueueList.get(toRun.getPcb().getTimesIn())).enqueue(toRun.getPcb());
                readyQueue.enqueue(toRun.getPcb());
            } else {
                Cola aux = new Cola();
                readyQueueList.add(aux);
                ((Cola) readyQueueList.get(toRun.getPcb().getTimesIn())).enqueue(toRun.getPcb());
                readyQueue.enqueue(toRun.getPcb());
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
                if (toRun.getTimeSpent() > quantum || toRun.getProcessingTime() == toRun.getTotalTimeSpent()){
                    dispatcher.deactivate(toRun);   // running --> ready
                }
                if (toRun.getPcb().getPc()-1 == toRun.getInterruptAt()){
                    toRun.getPcb().setStatus("blocked");
                    toRun.interrupt();
                }
            }
            
            if (toRun.getProcessingTime() == toRun.getTotalTimeSpent()) {
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
                if (toRun.getProcessingTime() == toRun.getTotalTimeSpent()){
                    dispatcher.deactivate(toRun);   // running --> ready
                }
                if (toRun.getPcb().getPc()-1 == toRun.getInterruptAt()){
                    toRun.getPcb().setStatus("blocked");
                    toRun.interrupt();
                }
            }
            if (toRun.getProcessingTime() == toRun.getTotalTimeSpent()) {
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
            int index = ((PCB)readyQueue.get(i)).getTimesIn();
            while (j < readyQueueList.count()){
                if ( index == j && !(((Cola)readyQueueList.get(j)).getContains(readyQueue.get(i)))) {
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
            PCB aux = (PCB)readyQueue.get(i);
            int j = i + 1;
            
            while (j < n){
                PCB aux2 = (PCB)readyQueue.get(j);
                if (aux.getPriorityFSS()>aux2.getPriorityFSS()){
                    readyQueue.getQueue().swap(i, j);
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
            if (((PCB)readyQueue.get(i)).getPriority() == priority) {
                timesInTotal = ((PCB)readyQueue.get(i)).getTimesIn() + timesInTotal;
                priorityCount++;
            }
            i++;
        }
        i = 0;
        while (i < n) {
            if (((PCB)readyQueue.get(i)).getPriority() == priority) {
                int priorityNode = ((PCB)readyQueue.get(i)).getPriority();
                int timesIn = ((PCB)readyQueue.get(i)).getTimesIn();
                
                float newPriorityFSS = priorityNode + timesIn + (timesInTotal/priorityCount);
                
                ((PCB)readyQueue.get(i)).setPriorityFSS(newPriorityFSS);
            }
            i++;
        }
    }
    
    
    public void reorganiceSRT (Cola readyQueue){
        int i = 0;
        int n = readyQueue.getCount();
        while (i < n){
            
            PCB aux = (PCB) readyQueue.get(i);

            int j = i + 1;
            
            while (j < n){
                PCB aux2 = (PCB)readyQueue.get(j);
                if (aux.getPriorityFSS() > aux2.getPriorityFSS()){

                    readyQueue.getQueue().swap(i, j);

                }
                j++;
            }
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
            var aux = (PCB)readyQueue.get(i);
            
            if (!timesIn.contains(aux.getTimesIn())){
                timesIn.add(aux.getTimesIn());
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
    
    public void accessDevice(Proceso blockedProcess, Dispatcher dispatcher, Cola blockedQueue){
        int i = 0;
        while (i < deviceTable.count()){
            if (blockedProcess.getDeviceToUse() == ((Device)deviceTable.get(i)).getId()){
                try {
                    System.out.println("accediendo al device");
                    ((Device)deviceTable.get(i)).getSemaf().acquire();
                } catch(InterruptedException e) {
                    // this part is executed when an exception (in this example InterruptedException) occurs
                    System.out.println("en catch" + e);
                }
                break;
            }
            i++;
        }
        try {
            blockedProcess.sleep(blockedProcess.getSatisfyCicles()*1000);
            ((Device)deviceTable.get(i)).getSemaf().release();
            dispatcher.deactivate(blockedProcess);
            blockedQueue.getQueue().remove(blockedQueue.getQueue().indexOf(blockedProcess));
        } 
        catch(InterruptedException e) {
             // this part is executed when an exception (in this example InterruptedException) occurs
             System.out.println("en catch" + e);
        }
    }
    
}
