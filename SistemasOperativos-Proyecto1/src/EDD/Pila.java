/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 * Esta clase define un objeto de tipo stack. Contiene una lista de elementos.
 *
 * @version 27/10/2024
 * @author Michelle García
 */
public class Pila {

    private Lista stack = new Lista();

    /**
     * Procedimiento para apilar un elemento.
     *
     * @param j Elemento a apilar.
     */
    public void push(Object j) {
        stack.add(j);
    }

    /**
     * Función para desapilar un elemento y devolver el elemento desapilado.
     *
     * @return Objeto desapilado.
     */
    public Object pop() {
        if (stack.count() > 0) {
            Object value = stack.get(stack.count() - 1);
            stack.remove(stack.count() - 1);
            return value;
        }
        return null;
    }

    /**
     * Función para obtener la cantidad de elementos de la pila.
     *
     * @return Cantidad de elementos de la pila.
     */
    public int getCount() {
        return stack.count();
    }

    /**
     * Función para retornar un elemento de la lista de la pila según su índice.
     *
     * @param index Indice de un elemento de la pila.
     * @return Elemento de la pila según el índice indicado.
     */
    public Object Get(int index) {
        return stack.get(index);
    }

    /**
     * Procedimiento para eliminar un elemento de la pila según su índice.
     *
     * @param index Indice de un elemento de la pila.
     */
    public void remove(int index) {
        stack.remove(index);
    }

    /**
     * Procedimiento para apilar un elemento.
     *
     * @param j Elemento a apilar.
     */
    public void add(Object j) {
        stack.add(j);
    }

    /**
     * Función para obtener la posicion de un elemento dentro de la pila.
     *
     * @param element Elemento a encontrar.
     * @return Posición del elemento.
     */
    public int getStackPosition(Object element) {
        int c = stack.count();
        for (int i = 0; i < c; i++) {
            Object elem = stack.get(i);

            if (elem.equals(element)) {
                return i;
            }
        }
        return -1; // El elemento no fue encontrado
    }
}
