package Server;
/**
 * Esta es la clase que permite crear los nodos de la lista doblemente enlazada
 * @author Kendall Marín Muñoz,Carlos Andrés Contreras Luna, Jose Andrés Vargas Torres
 */
public class NodoDoble {

    public String tipo;
    public javax.swing.JTextPane pan;
    public boolean jugador1;
    public boolean jugador2;
    public NodoDoble siguiente, anterior;
    
    /**
     * Este metodo recibe los atributos utilizados en otros metodos de la lista doble
     * @param casilla este atributo recibe el tipo de casilla
     * @param panel este atributo recibe un JPanel 
     * @param jugador1 este atributo permite localizar al jugador 1
     * @param jugador2 este atributo permite localizar al jugador 2
     */
    public NodoDoble(String casilla, javax.swing.JTextPane panel, boolean jugador1, boolean jugador2) {
        this(casilla, panel, null, null, false, false);
    }
    
    /**
     * Este metodo recibe los atributos utilizados en otros metodos de la lista doble
     * @param casilla este atributo recibe el tipo de casilla
     * @param panel este atributo recibe un JPanel
     * @param s este atributo permite obtener el siguiente nodo
     * @param a este atributo permite obtener el anterior nodo
     * @param isjugador1 este atributo permite localizar al jugador 1
     * @param isjugador2 este atributo permite localizar al jugador 2
     */
    public NodoDoble(String casilla, javax.swing.JTextPane panel, NodoDoble s, NodoDoble a, boolean isjugador1, boolean isjugador2){
        jugador1 = isjugador1;
        jugador2 = isjugador2;
        pan = panel;
        tipo=casilla;
        siguiente=s;
        anterior=a;
    }
    
    
}