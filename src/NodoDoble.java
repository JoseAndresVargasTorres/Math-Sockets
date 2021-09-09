public class NodoDoble {

    public String tipo;
    public boolean jugador1;
    public boolean jugador2;
    NodoDoble siguiente, anterior;
    public NodoDoble(String casilla, boolean jugador1, boolean jugador2) {
        this(casilla, null, null, false, false);
    }
    public NodoDoble(String casilla, NodoDoble s, NodoDoble a, boolean isjugador1, boolean isjugador2){
        jugador1 = isjugador1;
        jugador2 = isjugador2;
        tipo=casilla;
        siguiente=s;
        anterior=a;

    }
}
