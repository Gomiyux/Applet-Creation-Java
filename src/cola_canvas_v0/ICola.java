/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cola_canvas_v0;

/**
 *
 * @author pedro
 */
public interface ICola {
    /**
     *
     * @return numero de elementos en la cola
     */
    int GetNum();
    /**
     *
     * @param elemento
     */
    void Acola(Object elemento) throws Exception;
    /**
     *
     * @return elemento
     * @throws Exception
     */
    Object Desacola() throws Exception;
    /**
     *
     * @return elemento
     */
    Object Primero() throws Exception;
    
}
