public class ListaDoble {
    /**
     * La clase ListaDoble es la que permite conectar las casillas, complementandose con la clase NodoDoble
     */
    public NodoDoble inicio, fin;

    public ListaDoble() {
        inicio = fin = null;
    }

    /**
     * Metodo para saber cuando la lista está vacía
      */

    public boolean estVacia() {
        return inicio == null;
    }

    /**
     * Metodo para obtener el panel de cada casilla
     */

    public Object getPan() {
        return inicio.pan;
    }

    /**
     * Metodo para mover el jugador1
     */
    public void mover1() {
        NodoDoble aux = inicio;
        while (!aux.jugador1) {
            aux = aux.siguiente;
        }
        aux.siguiente.jugador1 = true;
        aux.jugador1 = false;

    }

    /**
     * Metodo para mover el jugador
     */
    public void mover2() {
        NodoDoble aux = inicio;
        while (!aux.jugador2) {
            aux = aux.siguiente;
        }
        aux.siguiente.jugador2 = true;
        aux.jugador2 = false;
    }

    /**
     * Metodo para agregar nodos al final
     * @param casilla este parametro se utiliza para definir el tipo de casilla (Reto, Trampa, Tunel)
     * @param panel este parametro se utiliza para definir el JPanel de cada casilla
     * @param isjugador1 este parametro permite saber si el jugador1 se encuentra en la casilla
     * @param isjugador2 este parametro permite saber si el jugador2 se encuentra en la casilla
     */
    public void agregarAlFinal(String casilla, javax.swing.JTextPane panel, boolean isjugador1, boolean isjugador2) {
        if (!estVacia()) {
            fin = new NodoDoble(casilla, panel, null, fin, isjugador1, isjugador2);
            fin.anterior.siguiente = fin;
        } else {
            inicio = fin = new NodoDoble(casilla, panel, false, false);
        }
    }

    /**
     * Metodo para agregar al inicio
     * @param casilla este parametro se utiliza para definir el tipo de casilla (Reto, Trampa, Tunel)
     * @param panel este parametro se utiliza para definir el JPanel de cada casilla
     * @param isjugador1 este parametro permite saber si el jugador1 se encuentra en la casilla
     * @param isjugador2 este parametro permite saber si el jugador2 se encuentra en la casilla
     */
    public void agregarAlInicio(String casilla, javax.swing.JTextPane panel, boolean isjugador1, boolean isjugador2) {
        if (!estVacia()) {
            inicio = new NodoDoble(casilla, panel, inicio, null, isjugador1, isjugador2);
            inicio.siguiente.anterior = inicio;
        } else {
            inicio = fin = new NodoDoble(casilla, panel, false, false);
        }
    }

    /**
     * Metodo para mostrar la lista de inicio a fin
     */
    public void mostrarLIF(){
        if (!estVacia()) {
            String isj1 = "Jugador1:";
            String isj2 = "Jugador2:";
            NodoDoble aux=inicio;
            while (aux!=null) {
                String dato = aux.tipo;
                System.out.print(dato+": "+isj1+aux.jugador1+"\n"+dato+": "+isj2+aux.jugador2+"\n");
                aux = aux.siguiente;

            }
        }
    }

    /**
     * Metodo para mostrar la lista de fin a inicio
     */
    public void mostrarLFI(){
        if (!estVacia()) {
            String isj1 = "Jugador1:";
            String isj2 = "Jugador2:";
            NodoDoble aux=fin;
            while (aux!=null) {
                String dato = aux.tipo;
                System.out.print(dato+": "+isj1+aux.jugador1+"\n"+dato+": "+isj2+aux.jugador2+"\n");
                aux=aux.anterior;
            }
        }
    }

    /**
     * Este metodo permite eliminar el primer nodo
     * @return null
     */
    public NodoDoble deleteFirst() {
        if (this.inicio != null) {
            NodoDoble temp = this.inicio;
            this.inicio = this.inicio.siguiente;
            return temp;
        } else {
            return null;
        }
    }
}
