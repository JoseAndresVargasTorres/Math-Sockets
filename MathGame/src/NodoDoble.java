public class NodoDoble {

    /**
     * Se declaran las variables que se usaran en la clase NodoDoble
     */
    public String tipo;
    public javax.swing.JTextPane pan;
    public boolean jugador1;
    public boolean jugador2;
    NodoDoble siguiente, anterior;

    /**
     * Se pasan los atributos que se utilizaran en los metodos de la clase ListaDoble
     * @param casilla este parametro se utiliza para definir el tipo de casilla (Reto, Trampa, Tunel)
     * @param panel este parametro se utiliza para definir el JPanel de cada casilla
     * @param jugador1 este parametro permite saber si el jugador1 se encuentra en la casilla
     * @param jugador2 este parametro permite saber si el jugador2 se encuentra en la casilla
     */
    public NodoDoble(String casilla, javax.swing.JTextPane panel, boolean jugador1, boolean jugador2) {
        this(casilla, panel, null, null, false, false);
    }

    /**
     * Se declaran las variables utilizadas por la clase NodoDoble
     * @param casilla este parametro se utiliza para definir el tipo de casilla (Reto, Trampa, Tunel)
     * @param panel este parametro se utiliza para definir el JPanel de cada casilla
     * @param s este parametro hace referencia al nodo siguiente
     * @param a este parametro hace referencia al nodo anterior
     * @param isjugador1 este parametro permite saber si el jugador1 se encuentra en la casilla
     * @param isjugador2 este parametro permite saber si el jugador2 se encuentra en la casilla
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
