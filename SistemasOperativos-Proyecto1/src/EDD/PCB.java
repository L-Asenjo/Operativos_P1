/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Eddy
 */
public class PCB {
    private int id;
    private String status;
    private String name;
    private int pc;
    private int mar;

    public PCB(int id, String name) {
        this.id = id;
        this.status = "New";
        this.name = name;
        this.pc = 0;
        this.mar = 0;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getMar() {
        return mar;
    }

    public void setMar(int mar) {
        this.mar = mar;
    }
    
    public void update(String status, int pc, int mar) {
        setStatus(status);
        setPc(pc);
        setMar(mar);
    }
    
}
