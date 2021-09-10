import javax.swing.*;
import java.awt.*;

public class Cliente_Servidor {

    /**
     * Este es el metodo main de la clase Servidor.
     * @param args Contiene la instancia de la clase InterfazCliente_Servidor.
     */

    public static void main(String[] args){
        InterfazCliente_Servidor interfazCliente_servidor = new InterfazCliente_Servidor();

    }
    /**
     * Elementos utilizados en la interfaz.
     */



}
class InterfazCliente_Servidor extends JFrame {

    /**
     * En este metodo se programa algunas caracteristicas de la interfaz del chat que vera el servidor, ademas, se instancia la clase ModeloInterfazServidor.
     */

    public InterfazCliente_Servidor(){

        setBounds(400, 100,800,750);

        ModeloInterfazCliente_Servidor modeloInterfazCliente_servidor = new ModeloInterfazCliente_Servidor();
        add(modeloInterfazCliente_servidor);

        setResizable(false);

        setVisible(true);



    }


}

class ModeloInterfazCliente_Servidor extends JPanel{

    public ModeloInterfazCliente_Servidor(){
        /*JLabel titulo = new JLabel("MATH-SOCKETS");
        titulo.setBounds(0,15,10,10);
        titulo.setFont(new Font("Verdana",Font.PLAIN,32));
        add(titulo);

         */
        casilla = new JTextPane();
        casilla.setLayout((new BoxLayout(casilla, BoxLayout.Y_AXIS)));
        casilla.setEditable(false);
        add(casilla);

    }


    private JScrollPane scroll;
    private JButton miboton;
    private JTextField campo1;
    private JTextArea areatexto;
    private JTextPane casilla;


}











