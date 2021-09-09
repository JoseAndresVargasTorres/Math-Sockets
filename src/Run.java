public class Run {
    public static void main(String[] args) {
        ListaDoble listita = new ListaDoble();
        listita.agregarAlFinal("Túnel", false, false);
        listita.agregarAlFinal("Reto", false, false);
        listita.agregarAlFinal("Reto", false, false);
        listita.agregarAlFinal("Túnel", false, false);
        listita.agregarAlFinal("Trampa", false, false);
        listita.agregarAlFinal("Túnel", false, false);
        listita.agregarAlFinal("Reto", false, false);
        listita.agregarAlInicio("Inicio", true, true);
        listita.agregarAlFinal("Fin", false, false);
        listita.mover1();
        listita.mover2();
        listita.mover2();
        listita.mostrarLIF();
    }
}
