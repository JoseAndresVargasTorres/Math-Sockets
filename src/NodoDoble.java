public class NodoDoble {

    public String tipo;
    public boolean jugador1;
    public boolean jugador2;
    public NodoDoble next, preceding;
    public Object data;

    public NodoDoble(Object data, String casilla, boolean isjugador1, boolean isjugador2) {
        this.next = null;
        this.data = data;
        this.preceding = null;
        this.tipo = casilla;
        this.jugador1 = isjugador1;
        this.jugador2 = isjugador2;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public NodoDoble getNext() {
        return this.next;
    }

    public void setNext(NodoDoble node) {
        this.next = node;
    }

    public void setPreceding(NodoDoble pnode){this.preceding = pnode;}

    public NodoDoble getPreceding(){return this.preceding;}

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean getisJugador1() {
        return jugador1;
    }

    public boolean getisJugador2() {
        return jugador2;
    }

    public void setisJugador1(boolean jugador1) {
        this.jugador1 = jugador1;
    }

    public void setisJugador2(boolean jugador2) {
        this.jugador2 = jugador2;
    }
}
