/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cola_canvas_v0;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author pedro
 */
public class CanvasCola extends Canvas {

    Object[] buffer;
    int tail;
    int head;
    int capacidad;
    int hay;
    int mensaje = 0;

    CanvasCola(int cap) {
        super();
        setSize(300, 300);
        setBackground(Color.cyan);
        capacidad = cap;
    }

    public void representa(Object[] buf, int in, int out, int numele) {
        buffer = buf;
        tail = in;
        head = out;
        hay = numele;
        mensaje = 0;
        this.repaint();
    }

    public void avisa(int aviso) {
        mensaje = aviso; // 1=Cola llena, 2=Cola Vacia
        this.repaint();
    }
    //Esto lo hacemos si no queremos que se borre el canvas antes de pintar
    //Así se evita el parpadeo del borrado del update del canvas 
    // Este es el update del canvas

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public synchronized void paint(Graphics g) {
        String titulo = "Cola Circular";

        Font f1 = new Font("Helvetica", Font.ITALIC + Font.BOLD, 20);
        Font f2 = new Font("Arial", Font.BOLD, 18);

        Image offscreen = createImage(getWidth(), getHeight());
        Graphics offgraphics = offscreen.getGraphics();
        offgraphics.setFont(getFont());


        offgraphics.setColor(getBackground());
        offgraphics.fillRect(0, 0, getWidth(), getHeight());

        // Visualiza el título
        offgraphics.setColor(Color.black);
        offgraphics.setFont(f1);
        FontMetrics fm = offgraphics.getFontMetrics();
        int w = fm.stringWidth(titulo);
        int h = fm.getHeight();
        int x = (getSize().width - w) / 2;
        int y = h;
        offgraphics.drawString(titulo, x, y);
        offgraphics.drawLine(x, y + 3, x + w, y + 3);

        // Visualiza el aviso de cola llena o vacía
        offgraphics.setColor(Color.GRAY);
        offgraphics.setFont(f1);
        fm = offgraphics.getFontMetrics();
        h = fm.getHeight();
        y = getSize().height - h;
        String mens1 = "COLA LLENA";
        w = fm.stringWidth(mens1);
        x = (getSize().width / 2 - w) / 2;
        offgraphics.drawString(mens1, x, y);
        String mens2 = "COLA VACIA";
        w = fm.stringWidth(mens2);
        x = (getSize().width / 2 - w) / 2 + (getSize().width / 2);
        offgraphics.drawString(mens2, x, y);

        if (mensaje == 1) {
            offgraphics.setColor(Color.RED);
            w = fm.stringWidth(mens1);
            x = (getSize().width / 2 - w) / 2;
            offgraphics.drawString(mens1, x, y);

        } else if (mensaje == 2) {
            offgraphics.setColor(Color.RED);
            w = fm.stringWidth(mens2);
            x = (getSize().width / 2 - w) / 2 + (getSize().width / 2);
            offgraphics.drawString(mens2, x, y);

        }
        //offgraphics.drawLine(x, y + 3, x + w, y + 3);

        // Dibuja la cola
        y = getSize().height / 2 - 15;
        offgraphics.setColor(Color.white);
        offgraphics.fillRect(10, y, 50 * capacidad, 50);
        offgraphics.setColor(Color.black);
        offgraphics.setFont(f2);
        if ((head == tail) && (hay == capacidad)) {
            for (int i = 0; i < capacidad; i++) {
                offgraphics.drawRect(10 + 50 * i, y, 50, 50);
                offgraphics.drawString(" " + buffer[i] + " ", 20 + 50 * i, y + 35);
            }
        }
        if ((head == tail) && (hay == 0)) {
            for (int i = 0; i < capacidad; i++) {
                offgraphics.drawRect(10 + 50 * i, y, 50, 50);
                offgraphics.drawString("  ", 20 + 50 * i, y + 35);
            }
        }
        if (head < tail) {
            for (int i = 0; i < head; i++) {
                offgraphics.drawRect(10 + 50 * i, y, 50, 50);
                offgraphics.drawString("  ", 20 + 50 * i, y + 35);
            }
            for (int i = head; i < tail; i++) {
                offgraphics.drawRect(10 + 50 * i, y, 50, 50);
                offgraphics.drawString(" " + buffer[i] + " ", 20 + 50 * i, y + 35);
            }
            for (int i = tail; i < capacidad; i++) {
                offgraphics.drawRect(10 + 50 * i, y, 50, 50);
                offgraphics.drawString("  ", 20 + 50 * i, y + 35);
            }
        }
        if (head > tail) {
            for (int i = 0; i < tail; i++) {
                offgraphics.drawRect(10 + 50 * i, y, 50, 50);
                offgraphics.drawString(" " + buffer[i] + " ", 20 + 50 * i, y + 35);
            }
            for (int i = tail; i < head; i++) {
                offgraphics.drawRect(10 + 50 * i, y, 50, 50);
                offgraphics.drawString(" ", 20 + 50 * i, y + 35);
            }
            for (int i = head; i < capacidad; i++) {
                offgraphics.drawRect(10 + 50 * i, y, 50, 50);
                offgraphics.drawString(" " + buffer[i] + " ", 20 + 50 * i, y + 35);
            }
        }

        offgraphics.setColor(Color.blue);
        int xx[]={35+50*tail,42+50*tail,50+50*tail};
        int yy[]={y-20,y-5,y-20};
        offgraphics.fillPolygon(xx, yy, 3);                
        offgraphics.setColor(Color.yellow);
        int xxx[]={35+50*head,42+50*head,50+50*head};
        int yyy[]={y+70,y+55,y+70};
        offgraphics.fillPolygon(xxx, yyy, 3);                        
        //offgraphics.fillOval(35 + 50 * head, y + 55, 15, 15);
        g.drawImage(offscreen, 0, 0, null);
    }
}
