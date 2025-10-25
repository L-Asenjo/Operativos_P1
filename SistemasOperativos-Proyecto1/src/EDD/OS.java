/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Eddy
 */
public class OS {

    private Lista processList = new Lista();
    private Lista processTable = new Lista();
    private Lista deviceTable = new Lista();
    private int memorySpace = 4000;
    private Scheduler scheduler;
    private Dispatcher dispatcher = new Dispatcher();
    private int remainingSpace = memorySpace;
    private Lista priorityList = new Lista();
    private Lista feedbackList = new Lista();
    private Cola readyQueue = new Cola();
    private Cola longTermQueue = new Cola();
    private Cola blockedQueue = new Cola();
    private Cola suspendedReadyQueue = new Cola();
    private Cola suspendedBlockedQueue = new Cola();
    private Lista terminatedProcessList = new Lista();
    private int currentPlanification = 0;
    private int quantum = 3;
    
    public Cola fillReadyQueue(){
        //Cola readyQueue = new Cola();
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

    public OS(int memorySpace) {
        this.scheduler = new Scheduler(processList, memorySpace, deviceTable);
    }
    
    
    public boolean canBeReady(Proceso process){
    
        int memory = getRemainingSpace() - process.getMemorySpace();
        this.setRemainingSpace(memory);
        System.out.println(memory);
        
        if (memory > 0){
            process.getPcb().setStatus("ready");
            System.out.println("si");
            return true;
        } else {
            process.getPcb().setStatus("suspendedReady");
            System.out.println("no");
            return false;
        }
    }

    private void executePriorityPlanification() {
        scheduler.reorganicePriorityPlanification(readyQueue, priorityList);
        scheduler.PriorityPlanification(readyQueue, dispatcher, priorityList);
    }

    private void executeSPN() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void executeFeedback() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void executeFSS() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void executeSRT() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void executeRoundRobin(){
        scheduler.RoundRobin(4, readyQueue, dispatcher, blockedQueue);
    }
    /**
     * @return the processList
     */

    public Lista getProcessList() {
        return processList;
    }

    public void setProcessList(Lista processList) {
        this.processList = processList;
    }

    public Lista getProcessTable() {
        return processTable;
    }

    public void setProcessTable(Lista processTable) {
        this.processTable = processTable;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public int getMemorySpace() {
        return memorySpace;
    }

    public void setMemorySpace(int memorySpace) {
        this.memorySpace = memorySpace;
    }

    public int getRemainingSpace() {
        return remainingSpace;
    }

    public void setRemainingSpace(int remainingSpace) {
        this.remainingSpace = remainingSpace;
    }

    public Cola getReadyQueue() {
        return readyQueue;
    }

    public void setReadyQueue(Cola readyQueue) {
        this.readyQueue = readyQueue;
    }

    public Cola getLongTermQueue() {
        return longTermQueue;
    }

    public void setLongTermQueue(Cola longTermQueue) {
        this.longTermQueue = longTermQueue;
    }

    public Lista getDeviceTable() {
        return deviceTable;
    }

    public void setDeviceTable(Lista deviceTable) {
        this.deviceTable = deviceTable;
    }

    public Lista getPriorityList() {
        return priorityList;
    }

    public void setPriorityList(Lista priorityList) {
        this.priorityList = priorityList;
    }

    public Lista getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(Lista feedbackList) {
        this.feedbackList = feedbackList;
    }

    public Cola getBlockedQueue() {
        return blockedQueue;
    }

    public void setBlockedQueue(Cola blockedQueue) {
        this.blockedQueue = blockedQueue;
    }

    public Cola getSuspendedReadyQueue() {
        return suspendedReadyQueue;
    }

    public void setSuspendedReadyQueue(Cola suspendedReadyQueue) {
        this.suspendedReadyQueue = suspendedReadyQueue;
    }

    public Cola getSuspendedBlockedQueue() {
        return suspendedBlockedQueue;
    }

    public void setSuspendedBlockedQueue(Cola suspendedBlockedQueue) {
        this.suspendedBlockedQueue = suspendedBlockedQueue;
    }

    public Lista getTerminatedProcessList() {
        return terminatedProcessList;
    }

    public void setTerminatedProcessList(Lista terminatedProcessList) {
        this.terminatedProcessList = terminatedProcessList;
    }

    public int getCurrentPlanification() {
        return currentPlanification;
    }

    public void setCurrentPlanification(int currentPlanification) {
        this.currentPlanification = currentPlanification;
    }
        
    /**
     * @return the quantum
     */
    public int getQuantum() {
        return quantum;
    }

    /**
     * @param quantum the quantum to set
     */
    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
    
}
