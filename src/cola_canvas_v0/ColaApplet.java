/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cola_canvas_v0;

import java.applet.Applet;

/**
 *
 * @author pedro
 */
public class ColaApplet extends Applet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    Productor prod;
    Consumidor cons;

    @Override
    public void init() {
        CanvasCola canvas = new CanvasCola(5);
        Cola_lenta cl = new Cola_lenta(5, canvas);
        this.setSize(300, 300);
        add(canvas);
        prod = new Productor(cl);
        cons = new Consumidor(cl);

    }
    // TODO overwrite start(), stop() and destroy() methods

    @Override
    public void start() {
        
        prod.start();
        cons.start();
    }

    @Override
    public void stop() {
        prod.interrupt();
        cons.interrupt();
    }
}
