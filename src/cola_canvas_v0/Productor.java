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
class Productor extends Thread {

    private Cola_lenta lacola;

    public Productor(Cola_lenta lacola) {
        System.out.println("Soy el prodcutor");
        this.lacola = lacola;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep((rnd.nextInt(3) + 1) * 1000);
                lacola.Acola(i);
            } catch (Exception ex) {
                System.out.println("Hilo  " + ex.getMessage());
            }
        }
    }
}
