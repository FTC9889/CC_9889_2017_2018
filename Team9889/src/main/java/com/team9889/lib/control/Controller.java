package com.team9889.lib.control;

/**
 * Created by joshua9889 on 12/28/2017
 * Used to update Control Loops as fast as possible
 */

public class Controller {

    public Controller(ControlThread Controlthread){
        this.Control = Controlthread;
    }

    private Thread thread = null;
    private ControlThread Control;

    public void startControlThread(){

        if(thread != null){
            thread.interrupt();
            try{
                thread.destroy();
            } catch (Exception e){}

        }
        if(thread == null){
            this.thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(!thread.isInterrupted()){
                        Control.update();
                        Thread.yield();
                    }
                }
            });
            thread.start();
        }
    }

    public void interruptControlThread(){
        thread.interrupt();
    }
}
