/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 * Esta clase define un objeto de tipo Parada. Contiene el nombre de la parada y
 * un booleano que indica si tiene o no tiene una sucursal dentro de su rango.
 *
 * @version 27/10/2024
 * @author Michelle García
 */
public class Parada {

    private String name;
    private boolean sucursal;

    /**
     * *
     * Constructor de la clase Parada.
     *
     * @param name Nombre de la Parada
     * @param sucursal Booleano que indique si tiene una sucursal dentro de su
     * rango.
     */
    public Parada(String name, boolean sucursal) {
        this.name = name;
        this.sucursal = sucursal;
    }

    /**
     * Procedimiento que cambia el valor del booleano sucursal.
     *
     * @param sucursal Booleano true o false.
     */
    public void setSucursal(boolean sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * Función que retorna el valor del atributo sucursal.
     *
     * @return Booleano true o false.
     */
    public boolean isSucursal() {
        return sucursal;
    }

    /**
     * Función que retorna el valor del atributo nombre.
     *
     * @return Nombre de la parada.
     */
    public String getName() {
        return name;
    }

    /**
     * Procedimiento que cambia el nombre de la parada.
     *
     * @param name Nombre nuevo
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Procedimiento que elimina la sucursal de la parada.
     */
    public void quitarSucursal() {
        this.setSucursal(false);
    }

    /**
     * Función que imprime el contenido de la parada
     *
     * @return String con la información del objeto parada
     */
    public String leerInfo() {
        String txt = "";
        txt = txt + "\nNombre: " + getName() + "Sucursal: " + isSucursal();
        return txt;
    }

}
