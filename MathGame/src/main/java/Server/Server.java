package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 * Esta clase es para crear un servidor con interfaz gráfica simple
 *
 * @author Kendall Marín Muñoz,Carlos Andres Contreras Luna, Jose Andres Vargas Torres
 */
public class Server extends javax.swing.JFrame {
    ServerSocket ss;
    HashMap clientColl = new HashMap();
    DataInputStream din;
    DataOutputStream dout;
    DefaultListModel dlm;
    /**
     * Aqui se encuneran los metodos y atributos de utilizados en la clase server
     */
    int p1x = 55;
    int p1x2 = 106;
    int p1y1 = 115;
    int p1y2 = 115;
    int cont = 3;
    int cont2 = 3;
    int movex1 = 105;
    int movex2 = 105;
    ListaDoble listita = new ListaDoble();
    NodoDoble auxi1 ;
    NodoDoble aux2;
    NodoDoble aux3;
    NodoDoble aux4;
    NodoDoble aux5;
    /**
     * Este metodo permite mover al jugador 1 de casilla
     */
    
    public void mover1(){
        listita.mover1();
        listita.mostrarLIF();
        if (cont == 0){            
            p1y1 += 76;
            this.p1.setLocation(p1x,p1y1);
            cont = 3;
            movex1 *= -1;            
        }else{
            System.out.println(cont);
            p1x += movex1;
            this.p1.setLocation(p1x,p1y1);
            cont--;
            if(listita.fin.jugador1 ==true){
                
                JOptionPane.showMessageDialog(null, "Felicidades", "Ganaste", JOptionPane.INFORMATION_MESSAGE);
                
            }
        }
    }
    
    /**
     * Este metodo permite mover al jugador 2 de casilla
     */
    
    public void mover2(){
        listita.mover2();
        listita.mostrarLIF();
        if (cont2 == 0){
            p1y2 += 76;
            this.p2.setLocation(p1x2,p1y2);
            cont2 = 3;
            movex2 *= -1;
        }else{
            System.out.println(cont2);
            p1x2 += movex2;
            this.p2.setLocation(p1x2,p1y2);
            cont2--;
        }
    
    }
    
    /**
     * Este metodo permite al jugador 1 retroceder de casilla
     */
    
    public void retroceder1(){
        
        listita.retroceder1();
        
        listita.mostrarLIF();
        if (cont == 3 ){            
            p1y1 -= 76;
            this.p1.setLocation(p1x,p1y1);
            cont = 0;
            movex1 *= -1;            
        }else{
            System.out.println("mover1_contador_re: " + cont);
            p1x -= movex1;
            this.p1.setLocation(p1x,p1y1);
            cont++;
            
        }
    }
    
    /**
     * Este metodo permite al jugador 2 retroceder de casilla
     */
    
    public void retroceder2(){
        listita.retroceder2();
        listita.mostrarLIF();
        if (cont2 == 3){            
            p1y2 -= 76;
            this.p2.setLocation(p1x2,p1y2);
            cont2 = 0;
            movex2 *= -1;            
        }else{
            System.out.println("mover1_contador_re: " + cont);
            p1x2 -= movex2;
            this.p2.setLocation(p1x2,p1y2);
            cont2++;
            
        }
    }
    
    
    /**
     * En este metodo se encuentra la logica del juego, especificamente, las acciones que debe realizar el jugador dependiendo si su casilla es de reto, tunel o trampa.
     * @param jugador es el jugador al que afectara la accion, dependiendo de la casilla donde cayo.
     */

    /*public void Partelogica(String jugador){
        Random t = new Random();
        
        //System.out.println("******************************************");
        //System.out.println(auxi1.jugador1); 

        if(auxi1.tipo == "Reto" && jugador== "jugador1" ){
        System.out.println("funca");
        int op1 = t.nextInt(50)+1;
        int op2 = t.nextInt(50)+1;
        int operando = t.nextInt(4)+1;

        if(operando == 1){
            int resultado1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el resultado de la suma de "+ op1 +" + "+ op2, "Reto", JOptionPane.PLAIN_MESSAGE)) ;
            if(resultado1 == op1 + op2){
                JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                listita.mover1();
                listita.mover2();                  

            }else{
                JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                listita.mover1();
                //listita.mover2();
            }
        }

        if(operando == 2){
            int resultado2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el resultado de la resta de "+ op1 +" - "+ op2, "Reto", JOptionPane.PLAIN_MESSAGE)) ;
            if(resultado2 == op1 - op2){
                JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                listita.mover1();
                listita.mover2();                  

            }else{
            JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
            listita.mover1();
            //listita.mover2();
            }
        }
        if(operando == 3){
            int resultado3 = Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el resultado de la multiplicación de "+ op1 +" * "+ op2, "Reto", JOptionPane.PLAIN_MESSAGE)) ;
            if(resultado3 == op1 * op2){
                JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                listita.mover1();
                listita.mover2();                  

            }else{
            JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
            listita.mover1();
            //listita.mover2();

            }
        }
        if(operando == 4){
            int resultado4 = Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el resultado de la división de "+ op1 +" ÷ "+ op2, "Reto", JOptionPane.PLAIN_MESSAGE)) ;
            if(resultado4 == op1 / op2){
                JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                listita.mover1();
                listita.mover2();                  

            }else{
            JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
            listita.mover1();
            //listita.mover2();
            }
        }    
    }else{
        return;
    }
            
            
        
        //Se realiza si algun jugador cae en la casilla de reto, permite que un jugador le envie un reto matematico aleatorio al otro.
       
        /*
        
        
            
    }
    
    */
    
    /**
     * En Server se encuentra el metodo ServerSocket que permite la conexion con el cliente, ademas de la creacion de las casillas y algunos de sus metodos.  
     */  
    
    
    public Server() { initComponents();
        //Aqui se realiza la conexion que permite recibir del cliente, además de agregar las casillas y colocarlas aleatoriamente.
        
    }
    Server(String jugador1){
        try {
            initComponents();
            ss = new ServerSocket(8080);
            Socket s = ss.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            String i = new DataInputStream(s.getInputStream()).readUTF();
            this.JP1.setText("Bienvenido: "+jugador1);
            this.JP2.setText(i+" joined!");
            this.Panel1.setEditable(false);
            this.Panel1.setText("Inicio");
            int contador = 14;
            int reto = 7;
            int trampa = 4;
            int tunel = 3;
            
            listita.agregarAlInicio("Inicio", Panel1, true, true);            
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
            listita.agregarAlFinal("Final", Panel16, false, false);
            listita.inicio.jugador1 = true;
            listita.inicio.jugador2 = true;           
            NodoDoble aux = listita.inicio.siguiente;
            auxi1 = listita.inicio;
            
            //Se programa la aleatoriedad de las casillas
            while(contador>0){
                Random r = new Random();
                int valorDado = r.nextInt(9)+1;  // Entre 0 y 5, más 1.
                //System.out.println(valorDado);

                //Coloca de manera aleatoria las casillas de reto.
                if (valorDado == 3 && reto != 0 || valorDado == 6 && reto != 0 || valorDado == 9 && reto != 0){
                    aux.pan.setText("Reto");
                    aux.tipo = "Reto";
                    aux.pan.setEditable(false);
                    aux = aux.siguiente;
                    contador--;
                    reto--;
                    
                //Coloca de manera aleatoria las casillas de trampa.
                } else if (valorDado == 2 && trampa != 0 || valorDado == 5 && trampa != 0 || valorDado == 8 && trampa != 0) {  
                    aux.pan.setText("Trampa");
                    aux.tipo = "Trampa";
                    aux.pan.setEditable(false);
                    aux = aux.siguiente;
                    contador--;
                    trampa--;
                    
                //Coloca de manera aleatoria las casillas de tunel.
                } else if(valorDado == 1 && tunel != 0 || valorDado == 4 && tunel != 0 || valorDado == 7 && tunel != 0) {  
                    aux.pan.setText("Túnel");
                    aux.tipo = "Túnel";
                    aux.pan.setEditable(false);
                    aux = aux.siguiente;
                    contador--;
                    tunel--;
                }                                                          
            }
            //Para implementar los metodos de reto, tunel y 
            
                
            
            
           
        
        //Se realiza cuando un jugador cae en la casilla de tunel. Hace que el jugador se mueva n numero aleatorio de casillas.
        
            this.Panel16.setEditable(false);
            this.Panel16.setText("Final");            
            this.p2.setLocation(104,115);
            this.p1.setLocation(55,115);
            getContentPane().setComponentZOrder(this.p1, 0);
            getContentPane().setComponentZOrder(this.p2, 0);
            new MsgRead(s, i).start();
            listita.mostrarLIF();
            aux2 = listita.inicio;
            aux3 = listita.inicio;
            aux4 = listita.inicio;
            aux5 = listita.inicio;
            System.out.println("-----------------------------------------------");
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
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
        public void Dadooficial() {
            int valorDado2 = 2;
            //int valorDado2 = t.nextInt(4)+1;  // Entre 0 y 5, más 1.
            System.out.println("Valor del dado:  "+ valorDado2);
            //System.out.println(auxi1);
            //Realiza los movimientos del jugador, dependiendo el valor del dado que haya salido.
            while (valorDado2>0 ){
                    mover1();
                    valorDado2--;
                } 
            NodoDoble aux = listita.inicio;
            while (aux.jugador1 == false){
                aux = aux.siguiente;
            }
            if (aux.tipo.equals("Reto")){
                try {
                    new DataOutputStream(s.getOutputStream()).writeUTF("Reto");
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        /**
         * Este método se encarga de recibir los paquetes de datos (mensajes)
         * y los envía a los clientes correspondientes
         */
        public void run() {
            NodoDoble aux = listita.inicio;
            int asd = 0;
            while (aux != null){
                String m = aux.tipo;
                String a = String.valueOf(asd);
                String i = a+","+m;
                asd++;
                aux = aux.siguiente;
                try{
                    new DataOutputStream(s.getOutputStream()).writeUTF(i);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            while (true) {
                try {
                    String i = new DataInputStream(s.getInputStream()).readUTF();
                    if (i.equals("mover1")){ //Este if separa una solicitud de monto de un mensaje normal
                        new DataOutputStream(s.getOutputStream()).writeUTF(i);
                        mover1();
                        
                        
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

                        }
                    } else if (i.equals("Reto")) {
                        i = i.substring(20);
                        StringTokenizer st = new StringTokenizer(i, ":");
                        String id = st.nextToken();
                        i = st.nextToken();
                        try {
                            new DataOutputStream(((Socket) clientColl.get(id)).getOutputStream()).writeUTF("< " + ID + " para tí > " + i);
                        } catch (Exception ex) {
                            clientColl.remove(id);
                            JP2.setText(id + ": salió!");

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
        p2 = new javax.swing.JLabel();
        p1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        JP1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        setFocusable(false);
        setResizable(false);

        jPanel1.setFocusable(false);

        Panel16.setFocusable(false);
        jScrollPane2.setViewportView(Panel16);

        Panel1.setFocusable(false);
        jScrollPane5.setViewportView(Panel1);

        Panel8.setFocusable(false);
        jScrollPane6.setViewportView(Panel8);

        Panel9.setFocusable(false);
        jScrollPane7.setViewportView(Panel9);

        Panel7.setFocusable(false);
        jScrollPane8.setViewportView(Panel7);

        Panel10.setFocusable(false);
        jScrollPane9.setViewportView(Panel10);

        Panel15.setFocusable(false);
        jScrollPane3.setViewportView(Panel15);

        Panel2.setFocusable(false);
        jScrollPane10.setViewportView(Panel2);

        Panel6.setFocusable(false);
        jScrollPane11.setViewportView(Panel6);

        Panel11.setFocusable(false);
        jScrollPane12.setViewportView(Panel11);

        Panel14.setFocusable(false);
        jScrollPane4.setViewportView(Panel14);

        Panel3.setFocusable(false);
        jScrollPane13.setViewportView(Panel3);

        Panel5.setFocusable(false);
        jScrollPane14.setViewportView(Panel5);

        Panel12.setFocusable(false);
        jScrollPane15.setViewportView(Panel12);

        Panel13.setFocusable(false);
        jScrollPane16.setViewportView(Panel13);

        Panel4.setFocusable(false);
        jScrollPane17.setViewportView(Panel4);

        JP2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JP2.setText("...................");

        p2.setIcon(new javax.swing.ImageIcon("C:\\Users\\jose\\OneDrive - Estudiantes ITCR\\ordenador\\Documentos\\NetBeansProjects\\Math-Sockets\\MathGame\\src\\main\\java\\img\\ganon.png")); // NOI18N

        p1.setIcon(new javax.swing.ImageIcon("C:\\Users\\jose\\OneDrive - Estudiantes ITCR\\ordenador\\Documentos\\NetBeansProjects\\Math-Sockets\\MathGame\\src\\main\\java\\img\\link.png")); // NOI18N

        jButton1.setText("1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Dado");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        JP1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JP1.setText("...................");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 38, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)))
                        .addGap(44, 44, 44))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JP1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JP2)
                        .addGap(35, 35, 35))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(p2)
                                    .addComponent(p1))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(JP1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(JP2)
                        .addGap(36, 36, 36)))
                .addGap(6, 6, 6)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(22, 22, 22))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (aux2.siguiente!=null){
            mover1();
            aux2 = aux2.siguiente;

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (aux3.siguiente!=null){
            mover2();
            aux3 = aux3.siguiente;

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            
            Random r = new Random();
            int ValorDado = r.nextInt(4)+1;
            //String m = "reto";
            
            
            while (ValorDado>0 && listita.getPos().siguiente != null  ) {
                
                
                
                dout.writeUTF("mover1");
                mover1();
                ValorDado--;
            }
            
            while(aux4.jugador1==false){
                aux4 = aux4.siguiente;
                
            }
            
            if(aux4.tipo == "Túnel"){
                int tl = r.nextInt(3)+1;
                String tltext = Integer.toString(tl);
                JOptionPane.showMessageDialog(null, "Te mueves "+ tltext+ " casilla(s)", "Túnel", JOptionPane.INFORMATION_MESSAGE);
                while(tl>0 && listita.getPos().siguiente != null){
                    dout.writeUTF("mover1");
                    mover1();
                    tl--;
                }
            }
            
            else if(aux4.tipo == "Trampa") {
                int tp = r.nextInt(3)+1;
                String tptext = Integer.toString(tp);
                JOptionPane.showMessageDialog(null, "Te devuelves "+ tptext+ " casilla(s)", "Trampa", JOptionPane.INFORMATION_MESSAGE);
                while(tp>0 && listita.getPos().anterior != null){
                    dout.writeUTF("retroceder1");
                    retroceder1();
                    tp--;
                }
                
            }
            /*
            else if(aux.tipo == "Reto"){
                int op1 = t.nextInt(50)+1;
                int op2 = t.nextInt(50)+1;
                int operando = t.nextInt(4)+1;
                
            
            }
            */
            // Que el dado haga la función de mover el jugador en ambas ventanas se desactiva
            
            // Hasta que el dado llegue a 0  
                //Cuando llega a 0 se evalua la casilla donde quedó el jugador 1
                // aux = inicio
               //while !aux.jugador1
               //aux = aux.siguiente
               //aux.tipo / reto trampa ....
               //if aux.tipo == reto{
               //crea los operandos, resuelte el problema y le envía los datos al cliente como "reto,operacion,op1,op2"
               //si respuesta del cliente == respuesta entonces ... jugador1 se mueve
               //si la respuesta está mal, jugador 2 retrocede 1 casilla. 

            

            
        } catch (Exception ex) {
          
        }
    
        
    
        
   
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args los argumentos de la línea de comando
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JP1;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
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
    public javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    // End of variables declaration//GEN-END:variables
}
