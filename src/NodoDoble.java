public class NodoDoble {

    public String tipo;
    public Object pan;
    public boolean jugador1;
    public boolean jugador2;
    NodoDoble siguiente, anterior;
    public NodoDoble(String casilla, Object panel, boolean jugador1, boolean jugador2) {
        this(casilla, panel, null, null, false, false);
    }
    public NodoDoble(String casilla, Object panel, NodoDoble s, NodoDoble a, boolean isjugador1, boolean isjugador2){
        jugador1 = isjugador1;
        jugador2 = isjugador2;
        pan = panel;
        tipo=casilla;
        siguiente=s;
        anterior=a;
    }
}

