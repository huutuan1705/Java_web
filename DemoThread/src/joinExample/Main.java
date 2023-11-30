/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package joinExample;

/**
 *
 * @author huutuan
 */
public class Main {
    public static void main(String[] args) {
        ThreadDemo t1 = new ThreadDemo("Gio moi");
        ThreadDemo t2 = new ThreadDemo("Mua dong");
        ThreadDemo t3 = new ThreadDemo("Mua ha");
        
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        t3.start();
    }
}
