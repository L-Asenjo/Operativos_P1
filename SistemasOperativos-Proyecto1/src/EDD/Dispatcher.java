/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Eddy
 */
public class Dispatcher {

    public Dispatcher() {
    }
    
    public void activate(PCB pcb, Lista procesos) {
        int i = 0;
        var procesoAux = procesos.get(i);
        while (i < procesos.count()){
            if (pcb.getId() == ((Nodo)procesos.get(i)).getInfoPCB().getId()){
                procesoAux = procesos.get(i);
                break;
            } else {
                i++;
            } 
        }
        
        ((Nodo) procesoAux).getInfoProceso().getPcb().setStatus("running");
        ((Nodo) procesoAux).getInfoProceso().run();
        
    }
    
    public Proceso deactivate(Proceso activeProcess) {
        activeProcess.getPcb().setStatus("ready");
        activeProcess.interrupt();
        return activeProcess;
    }
    
    public Proceso getActiveProcess(Lista procesos){
        int i = 0;
        var procesoAux = procesos.get(i);
        while (i < procesos.count()){
            if (((Nodo)procesoAux).getInfoPCB().getStatus() == "running"){
                procesoAux = procesos.get(i);
                break;
            } else {
                i++;
            } 
        }
        
        return (Proceso) procesoAux;
    }
}
