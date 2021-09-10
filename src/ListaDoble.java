public class ListaDoble {
    private NodoDoble inicio, fin;

    public ListaDoble() {
        inicio = fin = null;
    }

    // Método para saber cuando la lista está vacía
    public boolean estVacia() {
        return inicio == null;
    }

    // Método para mover el jugador1
    public void mover1() {
        NodoDoble aux = inicio;
        while (!aux.jugador1) {
            aux = aux.siguiente;
        }
        aux.siguiente.jugador1 = true;
        aux.jugador1 = false;

    }
    // Método para mover el jugador 2

    public void mover2() {
        NodoDoble aux = inicio;
        while (!aux.jugador2) {
            aux = aux.siguiente;
        }
        aux.siguiente.jugador2 = true;
        aux.jugador2 = false;
        }

    // Método para agregar nodos al final
    public void agregarAlFinal(String casilla, Object panel, boolean isjugador1, boolean isjugador2) {
        if (!estVacia()) {
            fin = new NodoDoble(casilla, panel, null,  fin, isjugador1, isjugador2);
            fin.anterior.siguiente = fin;
        } else {
            inicio = fin = new NodoDoble(casilla, panel, false, false);
        }
    }
    // Método para agregar al inicio
    public void agregarAlInicio(String casilla, Object panel, boolean isjugador1, boolean isjugador2) {
        if (!estVacia()) {
            inicio = new NodoDoble(casilla, panel, inicio, null, isjugador1, isjugador2);
            inicio.siguiente.anterior = inicio;
        } else {
            inicio = fin = new NodoDoble(casilla, panel, false, false);
        }
    }
    // Método para mostrar la lista de inicio a fin
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
    // Método para mostrar la lista de fin a inicio
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
}
