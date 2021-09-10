
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Esta clase es para crear un servidor con interfaz gráfica simple
 *
 * @author Kendall Marín Muñoz
 */
public class Server extends javax.swing.JFrame {
    ServerSocket ss;
    HashMap clientColl = new HashMap();
    /**
     * Crea un nuevo Server Socket
     */
    public Server() {
        try {
            initComponents();
            ss = new ServerSocket(8080);
            this.Panel1.setEditable(false);
            this.Panel1.setText("Inicio");
            int contador = 14;
            ListaDoble listita = new ListaDoble();
            listita.agregarAlFinal("Error", Panel2, false, false);
            listita.agregarAlFinal("Error", Panel3, false, false);
            listita.agregarAlFinal("Error", Panel4, false, false);
            listita.agregarAlFinal("Error", Panel5, false, false);
            listita.agregarAlFinal("Error", Panel6, false, false);
            listita.agregarAlFinal("Error", Panel7, false, false);
            listita.agregarAlFinal("Error", Panel8, false, false);
            listita.agregarAlFinal("Error", Panel9, false, false);
            listita.agregarAlFinal("Error", Panel10, false, false);
            listita.agregarAlFinal("Error", Panel11, false, false);
            listita.agregarAlFinal("Error", Panel12, false, false);
            listita.agregarAlFinal("Error", Panel13, false, false);
            listita.agregarAlFinal("Error", Panel14, false, false);
            listita.agregarAlFinal("Error", Panel15, false, false);
            while(contador>0){
                Random r = new Random();
                int valorDado = r.nextInt(2)+1;  // Entre 0 y 5, más 1.
                System.out.println(valorDado);
                if(valorDado == 1){
                    listita.inicio.pan.setText("hola");
                    listita.inicio.pan.setEditable(false);
                    listita.deleteFirst();
                    contador--;
                }
                else if(valorDado==2){
                    listita.inicio.pan.setText("SIIIUUUUUU");
                    listita.inicio.pan.setEditable(false);
                    listita.deleteFirst();
                    contador--;
                }
                this.Panel16.setEditable(false);
                this.Panel16.setText("Inicio");
            }
            new ClientAccept().start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Esta clase es para aceptar a diferentes clientes
     */
    class ClientAccept extends Thread {
        public void run() {
            while (true) {
                try {
                    Socket s = ss.accept();
                    String i = new DataInputStream(s.getInputStream()).readUTF();
                    if (clientColl.containsKey(i)) {
                        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                        dout.writeUTF("Ya estas registrado!");
                    } else {
                        clientColl.put(i, s);
                        JP2.setText(i + " Joined!\n");
                        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                        dout.writeUTF("");
                        new MsgRead(s, i).start();
                        new PrepareClientList().start();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Esta clase es para leer y mostrar los diferentes mensajes enviados por los
     * clientes, además detecta si se solicita un monto
     */
    class MsgRead extends Thread {

        Socket s;
        String ID;

        /**
         *
         * @param s el socket del cliente que envía el mensaje
         * @param ID el nombre de usuario del cliente que envía el mensaje
         */
        MsgRead(Socket s, String ID) {
            this.s = s;
            this.ID = ID;
        }

        /**
         * Este método se encarga de recibir los paquetes de datos (mensajes)
         * y los envía a los clientes correspondientes
         */
        public void run() {
            while (!clientColl.isEmpty()) {
                try {
                    String i = new DataInputStream(s.getInputStream()).readUTF();
                    String monto = i;
                    List<String> calc = Arrays.asList(monto.split(","));
                    if (calc.get(0).equals("monto")){ //Este if separa una solicitud de monto de un mensaje normal
                        int a = Integer.parseInt(calc.get(1));
                        int b = Integer.parseInt(calc.get(2));
                        int c = Integer.parseInt(calc.get(3));
                        Double total =  ((a*b/100)+(c*0.15));
                        i = "El monto calculado es de: " + total.toString();
                        Set k = clientColl.keySet();
                        Iterator itr = k.iterator();
                        while (itr.hasNext()) {
                            String key = (String) itr.next();
                            if (!key.equalsIgnoreCase(ID)) {
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF(i);
                                } catch (Exception ex) {
                                    clientColl.remove(key);
                                    JP2.setText(key + ": salió!");
                                    new PrepareClientList().start();
                                }
                            }
                        }

                    }
                    else if (i.equals("mkoihgteazdcvgyhujb096785542AXTY")) {
                        clientColl.remove(ID);
                        JP2.setText(ID + ": salió! \n");
                        new PrepareClientList().start();
                        Set<String> k = clientColl.keySet();
                        Iterator itr = k.iterator();
                        while (itr.hasNext()) {
                            String key = (String) itr.next();
                            if (!key.equalsIgnoreCase(ID)) {
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF(i);
                                } catch (Exception ex) {
                                    clientColl.remove(key);
                                    JP2.setText(key + ": salió!");
                                    new PrepareClientList().start();
                                }
                            }
                        }
                    } else if (i.contains("#4344554@@@@@67667@@")) {
                        i = i.substring(20);
                        StringTokenizer st = new StringTokenizer(i, ":");
                        String id = st.nextToken();
                        i = st.nextToken();
                        try {
                            new DataOutputStream(((Socket) clientColl.get(id)).getOutputStream()).writeUTF("< " + ID + " para tí > " + i);
                        } catch (Exception ex) {
                            clientColl.remove(id);
                            JP2.setText(id + ": salió!");
                            new PrepareClientList().start();
                        }
                    } else {
                        Set k = clientColl.keySet();
                        Iterator itr = k.iterator();
                        while (itr.hasNext()) {
                            String key = (String) itr.next();
                            if (!key.equalsIgnoreCase(ID)) {
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF("< " + ID + " para todos > " + i);
                                } catch (Exception ex) {
                                    clientColl.remove(key);
                                    JP2.setText(key + ": salió!");
                                    new PrepareClientList().start();
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Esta clase es para crear una lista con los clientes conectados
     * ademas permite implementar un método para enviar los mensajes a un
     * cliente en concreto
     */
    class PrepareClientList extends Thread {
        /**
         * Este método se encarga de añadir los clientes conectados a una lista
         * proyectada en la interfaz de los clientes
         */
        public void run() {
            try {
                String ids = "";
                Set k = clientColl.keySet();
                Iterator itr = k.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    ids += key + ",";
                }
                if (ids.length() != 0) {
                    ids = ids.substring(0, ids.length() - 1);
                }
                itr = k.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    try {
                        new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF(":;.,/=" + ids);
                    } catch (Exception ex) {
                        clientColl.remove(key);
                        JP2.setText(key + ": salió!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Panel16 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        Panel1 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        Panel8 = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        Panel9 = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        Panel7 = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        Panel10 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        Panel15 = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        Panel2 = new javax.swing.JTextPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        Panel6 = new javax.swing.JTextPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        Panel11 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        Panel14 = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        Panel3 = new javax.swing.JTextPane();
        jScrollPane14 = new javax.swing.JScrollPane();
        Panel5 = new javax.swing.JTextPane();
        jScrollPane15 = new javax.swing.JScrollPane();
        Panel12 = new javax.swing.JTextPane();
        jScrollPane16 = new javax.swing.JScrollPane();
        Panel13 = new javax.swing.JTextPane();
        jScrollPane17 = new javax.swing.JScrollPane();
        Panel4 = new javax.swing.JTextPane();
        JP2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        jScrollPane2.setViewportView(Panel16);

        jScrollPane5.setViewportView(Panel1);

        jScrollPane6.setViewportView(Panel8);

        jScrollPane7.setViewportView(Panel9);

        jScrollPane8.setViewportView(Panel7);

        jScrollPane9.setViewportView(Panel10);

        jScrollPane3.setViewportView(Panel15);

        jScrollPane10.setViewportView(Panel2);

        jScrollPane11.setViewportView(Panel6);

        jScrollPane12.setViewportView(Panel11);

        jScrollPane4.setViewportView(Panel14);

        jScrollPane13.setViewportView(Panel3);

        jScrollPane14.setViewportView(Panel5);

        jScrollPane15.setViewportView(Panel12);

        jScrollPane16.setViewportView(Panel13);

        jScrollPane17.setViewportView(Panel4);

        JP2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        JP2.setText("...................");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(48, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(JP2)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(JP2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args los argumentos de la línea de comando
     */
    public static void main(String args[]) {
        /* Configura el aspecto y sensación del Nimbus */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Crea y proyecta la interfaz */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JP2;
    private javax.swing.JTextPane Panel1;
    private javax.swing.JTextPane Panel10;
    private javax.swing.JTextPane Panel11;
    private javax.swing.JTextPane Panel12;
    private javax.swing.JTextPane Panel13;
    private javax.swing.JTextPane Panel14;
    private javax.swing.JTextPane Panel15;
    private javax.swing.JTextPane Panel16;
    private javax.swing.JTextPane Panel2;
    private javax.swing.JTextPane Panel3;
    private javax.swing.JTextPane Panel4;
    private javax.swing.JTextPane Panel5;
    private javax.swing.JTextPane Panel6;
    private javax.swing.JTextPane Panel7;
    private javax.swing.JTextPane Panel8;
    private javax.swing.JTextPane Panel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration//GEN-END:variables
}
