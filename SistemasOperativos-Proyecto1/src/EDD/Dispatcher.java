/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Eddy
 */
public class Dispatcher extends Thread{

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
        /*
        aqui se corre el proceso una vez obtenido
        procesoAux.run();*/
    }
    
    public Proceso deactivate(Proceso activeProcess) {
        /*
            Aqui se deja de correr el proceso
        */
        return activeProcess;
    }
}
