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
    
    public Cola fillReadyQueue(){
        //Cola readyQueue = new Cola();
        for (int i = 0; i < getProcessList().count(); i++){
            PCB auxProcessPCB = ((Proceso) getProcessList().get(i)).getPcb();
            if (auxProcessPCB.getStatus() == "ready"){
                getReadyQueue().enqueue(getProcessList().get(i));
            } else{
                i++;
            }
        }
        return getReadyQueue();
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
    

    public void executeRoundRobin(){
        scheduler.RoundRobin(4, readyQueue, dispatcher);
    }
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
     * @return the processTable
     */
    public Lista getProcessTable() {
        return processTable;
    }

    /**
     * @param processTable the processTable to set
     */
    public void setProcessTable(Lista processTable) {
        this.processTable = processTable;
    }

    /**
     * @return the deviceTable
     */
    public Lista getDeviceTable() {
        return deviceTable;
    }

    /**
     * @param deviceTable the deviceTable to set
     */
    public void setDeviceTable(Lista deviceTable) {
        this.deviceTable = deviceTable;
    }

    /**
     * @return the scheduler
     */
    public Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * @param scheduler the scheduler to set
     */
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * @return the dispatcher
     */
    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    /**
     * @param dispatcher the dispatcher to set
     */
    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * @return the memorySpace
     */
    public int getMemorySpace() {
        return memorySpace;
    }

    /**
     * @param memorySpace the memorySpace to set
     */
    public void setMemorySpace(int memorySpace) {
        this.memorySpace = memorySpace;
    }

    /**
     * @return the priorityList
     */
    public Lista getPriorityList() {
        return priorityList;
    }

    /**
     * @param priorityList the priorityList to set
     */
    public void setPriorityList(Lista priorityList) {
        this.priorityList = priorityList;
    }

    /**
     * @return the feedbackList
     */
    public Lista getFeedbackList() {
        return feedbackList;
    }

    /**
     * @param feedbackList the feedbackList to set
     */
    public void setFeedbackList(Lista feedbackList) {
        this.feedbackList = feedbackList;
    }

    /**
     * @return the readyQueue
     */
    public Cola getReadyQueue() {
        return readyQueue;
    }

    public int getRemainingSpace() {
        return remainingSpace;
    }

    public void setRemainingSpace(int remainingSpace) {
        this.remainingSpace = remainingSpace;
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
    
    
    
}
