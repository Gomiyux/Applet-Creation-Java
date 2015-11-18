/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cola_canvas_v0;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
class Consumidor extends Thread {//implements Runnable {

    private Cola_lenta lacola;

    public Consumidor(Cola_lenta lacola) {
        System.out.println("Soy el consumidor ");
        this.lacola = lacola;
    }

    @Override
    public void run() {

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis()+125);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep((rnd.nextInt(3) + 1) * 1000);
                System.out.println("Hilo Extraigo " + lacola.Desacola());

            } catch (Exception ex) {
                System.out.println("Hilo  " + ex.getMessage());
            }
        }
    }
}