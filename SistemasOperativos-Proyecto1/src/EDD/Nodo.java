/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 * Esta clase define un objeto de tipo Nodo. Contiene una lista donde se
 * almacenan los hijos del nodo, un Nodo donde se almacena el padre y un objeto
 * de tipo Parada referente a la información que contiene el nodo.
 *
 * @version 27/10/2024
 * @author Michelle García
 *
 */
public class Nodo {

    private Lista children;
    private PCB infoPCB;
    private Device infoDevice;
    private Nodo parent;
    
    public Nodo(PCB infoPCB) {
        this.infoPCB = infoPCB;
    }

    public Nodo(Device infoDevice) {
        this.infoDevice = infoDevice;
    }

    /**
     * @return the children
     */
    public Lista getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(Lista children) {
        this.children = children;
    }

    /**
     * @return the infoPCB
     */
    public PCB getInfoPCB() {
        return infoPCB;
    }

    /**
     * @param infoPCB the infoPCB to set
     */
    public void setInfoPCB(PCB infoPCB) {
        this.infoPCB = infoPCB;
    }

    /**
     * @return the infoDevice
     */
    public Device getInfoDevice() {
        return infoDevice;
    }

    /**
     * @param infoDevice the infoDevice to set
     */
    public void setInfoDevice(Device infoDevice) {
        this.infoDevice = infoDevice;
    }

    /**
     * @return the parent
     */
    public Nodo getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Nodo parent) {
        this.parent = parent;
    }
    
}