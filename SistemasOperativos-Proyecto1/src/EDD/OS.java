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

    private Lista processList;
    private Lista processTable = new Lista();
    private Lista deviceTable = new Lista();
    private Scheduler scheduler;
    private Dispatcher dispatcher;
    private int memorySpace = 4000;
    private Lista priorityList = new Lista();
    private Lista feedbackList = new Lista();
    private Cola readyQueue = new Cola();
    private Cola longTermQueue = new Cola();
    private Cola blockedQueue = new Cola();
    private Cola suspendedReadyQueue = new Cola();
    private Cola suspendedBlockedQueue = new Cola();
    private int ioCicles;
    private int satisfyCicles;
    
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
    
    
    
    
}
