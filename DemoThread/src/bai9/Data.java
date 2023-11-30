/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bai9;

import java.util.Vector;

/**
 *
 * @author huutuan
 */
public class Data {
    Vector<Integer> v;
    private int index;
    private boolean running;

    public Data() {
        v = new Vector<>();
        running = true;
    }
    
    public void add(int x){
        v.add(x);
    }
    
    public int get(){
        return v.remove(0);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    
    
}
