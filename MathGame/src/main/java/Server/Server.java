package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
    int p1x1 = 40;
    int p2x1 = 96;
    int p1y1 = 120;
    int p2y1 = 120;
    int cont = 3;
    int cont2 = 3;
    int movex1 = 104;
    int movex2 = 104;
    ListaDoble listita = new ListaDoble();
    NodoDoble auxi1 ;
    NodoDoble aux2;
    NodoDoble aux3;
    NodoDoble aux4;
    NodoDoble aux5;
    NodoDoble aux6;
    /**
     * Este metodo permite mover al jugador 1 de casilla
     */
    
    public void mover1(){
        listita.mover1();        
        if (cont == 0){            
            p1y1 += 75;
            this.p1.setLocation(p1x1,p1y1);
            cont = 3;
            movex1 *= -1;            
        }else{
            System.out.println(cont);
            p1x1 += movex1;
            this.p1.setLocation(p1x1,p1y1);
            cont--;
            if(listita.fin.jugador1){                
                JOptionPane.showMessageDialog(null, "Felicidades", "Ganaste", JOptionPane.INFORMATION_MESSAGE);
                Dado1.setEnabled(false);
            }
        }
    }
    
        /**
     * Este metodo permite mover al jugador 2 de casilla
     */
    
    public void mover2(){
        listita.mover2();        
        if (cont2 == 0){
            p2y1 += 75;
            this.p2.setLocation(p2x1,p2y1);
            cont2 = 3;
            movex2 *= -1;
        }else{
            System.out.println(cont2);
            p2x1 += movex2;
            this.p2.setLocation(p2x1,p2y1);
            cont2--;
            if(listita.fin.jugador2){                
                JOptionPane.showMessageDialog(null, "Ganó el jugador 2", "Perdiste", JOptionPane.INFORMATION_MESSAGE);                
                Dado1.setEnabled(false);
            }
        }
    
    }
    
    /**
     * Este metodo permite al jugador 1 retroceder de casilla
     */
    
    public void retroceder1(){
        listita.retroceder1();        
        if (cont == 3 ){            
            p1y1 -= 76;
            this.p1.setLocation(p1x1,p1y1);
            cont = 0;
            movex1 *= -1;            
        }else{
            System.out.println("mover1_contador_re: " + cont);
            p1x1 -= movex1;
            this.p1.setLocation(p1x1,p1y1);
            cont++;
             
        }
    }
    
    /**
     * Este metodo permite al jugador 2 retroceder de casilla
     */
    
    public void retroceder2(){

        listita.retroceder2();        
        if (cont2 == 3){            
            p2y1 -= 76;
            this.p2.setLocation(p2x1,p2y1);
            cont2 = 0;
            movex2 *= -1;            
        }else{
            System.out.println("mover1_contador_re: " + cont);
            p2x1 -= movex2;
            this.p2.setLocation(p2x1,p2y1);
            cont2++;
        }
        
    }
    
    /**
     * En Server se encuentra el metodo ServerSocket que permite la conexion con el cliente, 
     * ademas de la creacion de las casillas y algunos de sus metodos.  
     */  
    
    
    public Server() { initComponents();
        /**
         * Aqui se realiza la conexion que permite recibir del cliente, además de agregar las casillas y colocarlas aleatoriamente.
         */
        
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
            this.JP2.setText(i+" se unió!");
            dout.writeUTF("p1,"+jugador1);
            this.Panel1.setEditable(false);
            this.Panel1.setText("1.Inicio");
            int contador = 2;
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
            while(contador<=15){
                String index = Integer.toString(contador);
                Random r = new Random();
                int valorDado = r.nextInt(9)+1;  // Entre 0 y 5, más 1.
                //System.out.println(valorDado);

                //Coloca de manera aleatoria las casillas de reto.
                if (valorDado == 3 && reto != 0 || valorDado == 6 && reto != 0 || valorDado == 9 && reto != 0){
                    
                    aux.pan.setText(index +".Reto");
                    aux.tipo = "Reto";
                    aux.pan.setEditable(false);
                    aux = aux.siguiente;
                    contador++;
                    reto--;
                    
                //Coloca de manera aleatoria las casillas de trampa.
                } else if (valorDado == 2 && trampa != 0 || valorDado == 5 && trampa != 0 || valorDado == 8 && trampa != 0) {  
                    aux.pan.setText(index +".Trampa");
                    aux.tipo = "Trampa";
                    aux.pan.setEditable(false);
                    aux = aux.siguiente;
                    contador++;
                    trampa--;
                    
                //Coloca de manera aleatoria las casillas de tunel.
                } else if(valorDado == 1 && tunel != 0 || valorDado == 4 && tunel != 0 || valorDado == 7 && tunel != 0) {  
                    aux.pan.setText(index +".Túnel");
                    aux.tipo = "Túnel";
                    aux.pan.setEditable(false);
                    aux = aux.siguiente;
                    contador++;
                    tunel--;
                }                                                          
            }
                                                                                  
        //Se instanician algunos aux que permitiran realizar correctamente algunos de los métodos de abajo
        //Además, algunos paneles son editados y se inicializa el método para leer mensajes
        
            this.Panel16.setEditable(false);
            this.Panel16.setText("16.Final");            
            this.p2.setLocation(p2x1,p2y1);
            this.p1.setLocation(p1x1,p1y1);
            getContentPane().setComponentZOrder(this.p1, 0);
            getContentPane().setComponentZOrder(this.p2, 0);
            new MsgRead(s, i).start();            
            aux2 = listita.inicio;
            aux3 = listita.inicio;
            aux4 = listita.inicio;
            aux5 = listita.inicio;
            aux6 = listita.inicio;
            System.out.println("-----------------------------------------------");
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Esta clase es para leer y mostrar los diferentes mensajes enviados por el cliente
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
            
            //Este ciclo permite ejecutar diferentes métodos dependiendo de lo que reciba el servidor
            //Como lo pueden ser los retos que se le envian al otro jugador, entre otros métodos
            while (true) {
                try {
                    
                    String i = new DataInputStream(s.getInputStream()).readUTF();
                    String m = i;
                    List<String> test = Arrays.asList(i.split(","));
                    if(i.equals("mover2")){
                        mover2();
                    }else if(m.equals("retroceder2")){
                        retroceder2();
                        
                    }else if (i.equals("correcto")) {
                        dout.writeUTF("correcto");
                        mover1();
                        
                    } else if (i.equals("incorrecto")) {
                        dout.writeUTF("incorrecto");
                        while(!aux6.jugador2){
                            aux6 = aux6.siguiente;
                        }
                        if(aux6.anterior != null ){
                            mover1();
                            retroceder2();
                        }else{
                            mover1();  
                        }
                    }else if(m.equals("retroceder2")){
                        retroceder2();
                    } else if (m.equals("correcto2")) {
                        mover2();
                    } else if (m.equals("incorrecto2")) {
                        while(!aux6.jugador1){
                            aux6 = aux6.siguiente;
                        }
                        if(aux6.anterior != null ){
                            mover2();
                            retroceder1();
                        }else{
                            mover2();  
                        }
                        
                            
                    }else if (test.get(0).equals("suma")) {
                        int r = Integer.parseInt(test.get(1)) + Integer.parseInt(test.get(2));
                        int resultado3 = Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el resultado de la suma de "+ test.get(1) +" + "+ test.get(2) + "\n ¡Solo debe introducir números enteros!", "Reto", JOptionPane.PLAIN_MESSAGE));
                        if(resultado3 == r) {
                            JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                            dout.writeUTF("correcto2");
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                            dout.writeUTF("incorrecto2");
                        }  
                    }else if (test.get(0).equals("resta")) {
                            int r = Integer.parseInt(test.get(1)) - Integer.parseInt(test.get(2));
                            int resultado3 = Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el resultado de la resta de "+ test.get(1) +" - "+ test.get(2)+ "\n ¡Solo debe introducir números enteros!", "Reto", JOptionPane.PLAIN_MESSAGE)) ;
                            if(resultado3 == r) {
                                JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                                dout.writeUTF("correcto2");
                            } else {
                                JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                                dout.writeUTF("incorrecto2");
                            }  
                    }else if (test.get(0).equals("multiplicacion")) {
                            int r = Integer.parseInt(test.get(1)) * Integer.parseInt(test.get(2));
                            int resultado3 = Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el resultado de la multiplicación de "+ test.get(1) +" * "+ test.get(2)+ "\n ¡Solo debe introducir números enteros!", "Reto", JOptionPane.PLAIN_MESSAGE)) ;
                            if(resultado3 == r) {
                                JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                                dout.writeUTF("correcto2");
                            } else {
                                JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                                dout.writeUTF("incorrecto2");
                            }  
                    }else if (test.get(0).equals("division")) {
                            int r = Integer.parseInt(test.get(1)) / Integer.parseInt(test.get(2));
                            int resultado3 = Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el resultado de la división de "+ test.get(1) +" / "+ test.get(2)+ "\n ¡Solo debe introducir números enteros!", "Reto", JOptionPane.PLAIN_MESSAGE)) ;
                            if(resultado3 == r) {
                                JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                                dout.writeUTF("correcto2");
                            } else {
                                JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                                dout.writeUTF("incorrecto2");
                            }  
                        } else if (m.equals("dadoOn") && !listita.fin.jugador1) {
                            Dado1.setEnabled(true);
                    }

                    } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            }
        
        }
        
    }
    
    /**
     * Esta clase es para crear una lista con los clientes conectados
     * que en este caso solo seria únicamente el jugador 2
     */
    class PrepareClientList extends Thread {
        /**
         * Este método se encarga de añadir al cliente conectado         
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
    /**
     * La siguiente línea suprime algunas advertencias del compilador
     */
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
        JP3 = new javax.swing.JLabel();
        Dado1 = new javax.swing.JButton();
        JP1 = new javax.swing.JLabel();
        p3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");
        setFocusable(false);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
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

        JP2.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        JP2.setText("...................");

        p2.setIcon(new javax.swing.ImageIcon("C:\\Users\\gmg\\Desktop\\ProyectoDatos\\Math-Sockets\\MathGame\\src\\main\\java\\img\\ganon.png")); // NOI18N

        p1.setIcon(new javax.swing.ImageIcon("C:\\Users\\gmg\\Desktop\\ProyectoDatos\\Math-Sockets\\MathGame\\src\\main\\java\\img\\link.png")); // NOI18N

        JP3.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        JP3.setText("Jugador 1");

        Dado1.setBackground(new java.awt.Color(255, 204, 204));
        Dado1.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        Dado1.setText("Dado");
        Dado1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Dado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dado1(evt);
            }
        });

        JP1.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        JP1.setText("...................");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(JP1)
                            .addComponent(JP2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Dado1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(p1)
                        .addGap(37, 37, 37)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(JP3)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(p2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(p3)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(32, 32, 32))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JP1)
                    .addComponent(JP3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(JP2)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Dado1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(p2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
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
     * Aqui se encuentra lo que se ejecuta al presionar el botón del dado del jugador 1
     * @param evt hace referencia al evento del botón
     */
    private void Dado1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dado1
        try {
            //Se crea un int que será el valor del dado e irá de 1 a 4
            //También se crea un random que permitira que el valor del dado sea aleatorio, además de implementarse en algunas funciones
            Random r = new Random();
            int ValorDado = r.nextInt(4)+1;
            //String m = "reto";
            
            System.out.println("---------------------------*-------------");
            System.out.println(ValorDado);
            System.out.println("---------------------------*-------------");
            
            //Mueve al jugador la cantidad de casillas que indique al dado y permite delimitar su movimiento si llega al final del juego
            while (ValorDado>0 && listita.getPos().siguiente != null  ) {
                dout.writeUTF("mover1");
                mover1();
                ValorDado--;
            }
            aux4 = listita.inicio;
            while(aux4.jugador1==false){
                aux4 = aux4.siguiente;
                
            }
            System.out.println(aux4.tipo);
            
            //Funcionalidad de las casillas tipo Túnel
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
            
            //Funcionalidad de las casillas tipo Trampa
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
            
            //Funcionalidad de las casillas tipo Reto
            else if(aux4.tipo == "Reto") {
                int op1 = r.nextInt(50) + 1;
                int op2 = r.nextInt(50) + 1;
                int operando = r.nextInt(4) + 1;
                String Sop1 = Integer.toString(op1);
                String Sop2 = Integer.toString(op2);                
                String Sop = "";
                if(operando == 1){
                    Sop = "suma";
                }else if(operando == 2){
                    Sop = "resta";
                }else if(operando == 3){
                    Sop = "multiplicacion";
                }else if(operando == 4){
                    Sop = "division";
                }                
                JOptionPane.showMessageDialog(null, "Reto para el jugador 2", "Reto", JOptionPane.INFORMATION_MESSAGE);
                try {                    
                    dout.writeUTF(Sop + "," + Sop1 + "," + Sop2); // "suma,10,5"
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "ERRRRRRRRRRROR", "Errrrr", JOptionPane.INFORMATION_MESSAGE);
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
        
        //Esto permite desabilitar el botón del dado
        this.Dado1.setEnabled(false);
        try {
            dout.writeUTF("dadoOn");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_Dado1

    /**
     * Aqui se crean los elementos utilizados en la interfaz
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Dado1;
    private javax.swing.JLabel JP1;
    private javax.swing.JLabel JP2;
    private javax.swing.JLabel JP3;
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
    public javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    public javax.swing.JLabel p3;
    // End of variables declaration//GEN-END:variables
}
