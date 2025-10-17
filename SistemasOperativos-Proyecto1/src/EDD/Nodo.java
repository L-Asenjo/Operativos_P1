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
    private Object info;
    private Nodo parent;
    private int height;

    /**
     * *
     * Constructor de la clase Nodo
     *
     * @param info Objeto de tipo parada. Información que contiene el nodo.
     */
    public Nodo(Object info) {
        this.info = info;
        this.children = new Lista();
    }

    /**
     * Función que devuelve la lista de hijos.
     * 
     * @return Lista de hijos.
     */
    public Lista getChildren() {
        return children;
    }

    /**
     * Procedimiento para cambiar la lista de hijos.
     * 
     * @param children Lista nueva de hijos.
     */
    public void setChildren(Lista children) {
        this.children = children;
    }

    /**
     * Función que devuelve a objeto parada.
     * 
     * @return Objeto parada.
     */
    public Object getInfo() {
        return info;
    }

    /**
     * Procedimiento para cambiar el contenido del atributo parada.
     * 
     * @param info Objeto parada.
     */
    public void setInfo(Object info) {
        this.info = info;
    }

    /**
     * Función que devuelve el Nodo padre.
     * 
     * @return Nodo padre.
     */
    public Nodo getParent() {
        return parent;
    }

    /**
     * Procedimiento para cambiar el Nodo padre.
     * 
     * @param parent Nodo padre.
     */
    public void setParent(Nodo parent) {
        this.parent = parent;
    }

    /**
     * Función que devuelve el valor del nivel del nodo.
     * 
     * @return Nivel del nodo.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Procedimiento para cambiar el nivel del nodo.
     * 
     * @param height Nuevo nivel del nodo.
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
