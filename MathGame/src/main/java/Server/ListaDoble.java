package Server;

public class ListaDoble {
    public NodoDoble inicio, fin;
    
    /**
     * En este metodo se definen los atributos de inicio y fin
     */  

    public ListaDoble() {
        inicio = fin = null;
    }

    /**
     * Metodo para saber cuando la lista está vacía
     * @return booleano
     */
    public boolean estVacia() {
        return inicio == null;
    }
    /**
     * Metodo para obtener el panel de cada casilla
     * @return panel
     */
    public Object getPan() {
        return inicio.pan;
    }

    /**
     * Metodo para mover el jugador1 de nodo
     */
    public void mover1() {
        NodoDoble aux = inicio;
        while (!aux.jugador1) {
            aux = aux.siguiente;
            
        }
        if(aux.siguiente != null ){
            aux.siguiente.jugador1 = true;
            aux.jugador1 = false;
        }
        
    }
    /**
     * Metodo para mover el jugador 2 de nodo
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
     * Metodo para retroceder al jugador 1 en la lista 
     */
    public void retroceder1(){
           NodoDoble aux = fin;
           while(!aux.jugador1){
               aux = aux.anterior;        
       }
            if(aux.anterior != null ){
                aux.anterior.jugador1 = true;
                aux.jugador1 = false;    
            }
                       
       }
    
    /**
     * Metodo para retroceder al jugador 1 en la lista 
     */
    public void retroceder2(){
        NodoDoble aux = fin;
        while(!aux.jugador2){
            aux = aux.anterior;        
    }
        aux.anterior.jugador2 = true;
        aux.jugador2 = false;                
    }   
    
    

    /**
     * Metodo para agregar nodos al final
     * @param casilla este atributo recibe el tipo de casilla
     * @param panel este atributo recibe un JPanel
     * @param isjugador1 este atributo permite localizar al jugador 1     
     * @param isjugador2 este atributo permite localizar al jugador 2
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
     * Metodo para agregar nodos al inicio
     * @param casilla este atributo recibe el tipo de casilla
     * @param panel este atributo recibe un JPanel
     * @param isjugador1 este atributo permite localizar al jugador 1     
     * @param isjugador2 este atributo permite localizar al jugador 2
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
     * Metodo para eliminar al primero de la lista
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
    
    public NodoDoble getPos(){
       NodoDoble aux = inicio;
       while(aux.jugador1 == false){
           aux = aux.siguiente;
       }
       return aux;
    }
    
}