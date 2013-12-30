package com.siperian.demo.extendedDataTable.model.impl;

import java.util.EventListener;
import java.util.EventObject;

import org.ajax4jsf.event.PushEventListener;

public class PushBean implements Runnable {


    private boolean enabled = false;
    
    PushEventListener listener;

    private Thread thread;

    // private int eventsFired counter;

    public void addListener(EventListener listener) {
        synchronized (listener) {
            System.out.println("PushBean.addListener()");
            if (this.listener != listener) {
                this.listener = (PushEventListener) listener;
            }
        }
    }

    public void run() {
        System.out.println("PushBean.run() executed");
        while (thread != null) {
            try {
                listener.onEvent(new EventObject(this));
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
            setEnabled(true);
        }
    }

    public void stop() {
        if (thread != null) {
            //thread.stop();
            setEnabled(false);
            thread = null;
        }
    }

    public Thread getThread() {
        return thread;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
