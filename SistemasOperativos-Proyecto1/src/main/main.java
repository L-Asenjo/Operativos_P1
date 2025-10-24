/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import Interfaz.Interface1;
import EDD.Cola;
import EDD.Lista;
import EDD.Proceso;
import EDD.PCB;

/**
 *
 * @author miche_ysmoa6e
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Interface1 menu = new Interface1();
        menu.setVisible(true);
       // menu.runQuickAddDemo();
        
        exampleListaSwap();
        exampleColaSwap();
        // TODO code application logic here
    }
    
    // Example A: use Lista.swap directly with Proceso objects
    private static void exampleListaSwap() {
        System.out.println("=== Lista swap demo ===");
        Lista list = new Lista();

        Proceso p1 = new Proceso(1, "P1", "CPU", 5);
        Proceso p2 = new Proceso(2, "P2", "CPU", 2);
        Proceso p3 = new Proceso(3, "P3", "CPU", 8);

        // add in order p1, p2, p3
        list.add(p1);
        list.add(p2);
        list.add(p3);

        printProcessList(list, "Before swap");

        // swap index 0 and 2 (p1 <-> p3)
        list.swap(0, 2);

        printProcessList(list, "After swap (0 <-> 2)");

        // swap adjacent indices 0 and 1
        list.swap(0, 1);
        printProcessList(list, "After swap (0 <-> 1)");
    }

    // Example B: swap elements stored in a Cola's underlying Lista
    private static void exampleColaSwap() {
        System.out.println("\n=== Cola swap demo ===");
        Cola ready = new Cola();

        // For this demo we will enqueue PCB objects directly (common in your OS code)
        // but you can enqueue any object. We'll use PCB so printing is simple.
        ready.enqueue(new PCB(10, "Proc10"));
        ready.enqueue(new PCB(20, "Proc20"));
        ready.enqueue(new PCB(30, "Proc30"));

        System.out.println("Ready queue before swap:");
        for (int i = 0; i < ready.getCount(); i++) {
            PCB pcb = (PCB) ready.get(i);
            System.out.printf(" index %d -> id=%d name=%s\n", i, pcb.getId(), pcb.getName());
        }

        // Swap entries 0 and 2 inside the underlying Lista
        // Note: Cola.getQueue() returns the Lista object
        Lista underlying = ready.getQueue();
        underlying.swap(0, 2);

        System.out.println("Ready queue after swap(0,2):");
        for (int i = 0; i < ready.getCount(); i++) {
            PCB pcb = (PCB) ready.get(i);
            System.out.printf(" index %d -> id=%d name=%s\n", i, pcb.getId(), pcb.getName());
        }
    }

    private static void printProcessList(Lista list, String header) {
        System.out.println(header + " (count=" + list.count() + ")");
        for (int i = 0; i < list.count(); i++) {
            Object o = list.get(i);
            if (o instanceof Proceso) {
                Proceso p = (Proceso) o;
                PCB pcb = p.getPcb();
                System.out.printf(" index %d -> id=%d name=%s procTime=%.0f\n",
                        i, pcb.getId(), pcb.getName(), p.getProcessingTime());
            } else {
                System.out.printf(" index %d -> %s\n", i, o);
            }
        }
    }
    
}
