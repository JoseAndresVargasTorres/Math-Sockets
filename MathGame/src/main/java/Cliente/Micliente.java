package Cliente;

import Server.ListaDoble;
import Server.NodoDoble;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.net.Socket;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Arrays;
import java.util.Random;


/**
 * Esta clase es para crear un cliente el cual tendra una interfaz de juego
 *
 * @author Kendall Marín Muñoz,Carlos Andrés Contreras Luna, Jose Andrés Vargas Torres
 * 
 */
public class Micliente extends javax.swing.JFrame {
    //Se crea lo necesario para conectarse con el servidor o jugador 1
    String iD, clientID = "";
    DataInputStream din;
    DataOutputStream dout;
    //Se declaran algunas variables que se utilizan en los métodos que permiten mover a los jugadores
    int p1x1 = 38;
    int p1y1 = 112;
    int p2x1 = 94;
    int p2y1 = 112;
    int cont = 3;
    int cont2 = 3;
    int movex1 = 104;
    int movex2 = 104;
    ListaDoble listita = new ListaDoble();
    NodoDoble aux6;
    NodoDoble aux4;
    
    /**
     * Metodo que permite mover al jugador 1 de posicion
     */
    public void mover1(){
        listita.mover1();
        
        if (cont == 0){            
            p1y1 += 75;
            this.p1.setLocation(p1x1,p1y1);
            cont = 3;
            movex1 *= -1; 
        } else {
            
            p1x1 += movex1;
            this.p1.setLocation(p1x1,p1y1);
            cont--;
            if(listita.fin.jugador1 ==true){                
                JOptionPane.showMessageDialog(null, "Ganó el jugador 1", "Perdiste", JOptionPane.INFORMATION_MESSAGE);                
                Dado2.setEnabled(false);
            }
        }
    }
    
    /**
     * Metodo que permite mover al jugador 2 de posicion
     */
    public void mover2(){
        listita.mover2();
        if (cont2 == 0){
            p2y1 += 75;
            this.p2.setLocation(p2x1,p2y1);
            cont2 = 3;
            movex2 *= -1;
        }else{
            
            p2x1 += movex2;
            this.p2.setLocation(p2x1,p2y1);
            cont2--;
            if(listita.fin.jugador2){                
                JOptionPane.showMessageDialog(null, "Felicidades", "Ganaste", JOptionPane.INFORMATION_MESSAGE);                
                Dado2.setEnabled(false);
            }
        }
    
    }
    
    /**
     * Metodo que permite retroceder de casilla al jugador 1  
     */
    public void retroceder1(){
        if (!listita.getPos().tipo.equals("Inicio")){
            listita.retroceder1();
            //listita.mostrarLIF();
            if (cont == 3 ){            
                p1y1 -= 76;
                this.p1.setLocation(p1x1,p1y1);
                cont = 0;
                movex1 *= -1;            
            }else{
               
                p1x1 -= movex1;
                this.p1.setLocation(p1x1,p1y1);
                cont++;
            }
        }
    }
    
    /**
     * Metodo que permite retroceder de casilla al jugador 2  
     */
    
    public void retroceder2(){
        if (!listita.getPos2().tipo.equals("Inicio")){
            listita.retroceder2();
            //listita.mostrarLIF();
            if (cont2 == 3){            
                p2y1 -= 76;
                this.p2.setLocation(p2x1,p2y1);
                cont2 = 0;
                movex2 *= -1;            
            }else{
                
                p2x1 -= movex2;
                this.p2.setLocation(p2x1,p2y1);
                cont2++;
            }
        }
        
    }
   

    /**
     * En la clase Micliente se encuentra el Socket que permite la conexion con el servidor, 
     * ademas de la creacion de las casillas y parte de la funcionalidad de las mismas.  
     */ 
    
    public Micliente() {
        initComponents();
    }

    /**
     * Aquí se encuentran algunas funciones quen nos permiten la comunicación con el servidor o jugador 1
     * También se crean las casillas enlazadas a los nodos
     * @param i el nombre del usuario en el cliente
     * @param s el socket que utiliza el cliente
     */
     Micliente(String i, Socket s) {
        iD = i;
        try {
            initComponents();
            //Deshabilita el botón dado
            Dado2.setEnabled(false);
            //Se crean las casillas enlazadas a los nodos de la lista doblemente enlazada
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
            aux6 = listita.inicio;
            idlabel.setText(i);
            //din y dout permiten recibir y enviar información            
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            //Localización de los jugadores
            this.p1.setLocation(p1x1,p1y1);
            this.p2.setLocation(p2x1,p2y1);
            getContentPane().setComponentZOrder(this.p1, 0);
            getContentPane().setComponentZOrder(this.p2, 0);
            //Además, aqui se inicializa el método para leer mensajes
            new Read().start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Esta clase es para recibir los mensajes
     */
    class Read extends Thread {
        /**
         * Método que permite al cliente recibir mensajes del servidor
         * y ejecutar un método dependiendo del mensaje recibido
         */
        public void run() {
            NodoDoble aux = listita.inicio;
            while (true) {
                try {
                    String m = din.readUTF();                    
                    String i = m;
                    List<String> test = Arrays.asList(i.split(","));
                    if (m.equals("mover1")){
                        mover1();
                    }else if(test.get(0).equals("p1")){
                        host.setText(test.get(1));
                    }else if(m.equals("retroceder1")){
                        retroceder1();                       
                    } else if (test.get(0).equals("0")) {   //Estas condiciones permiten asignarle                        
                        aux.pan.setText("1."+test.get(1));  //los nombres y tipos a las casillas del cliente    
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("1")) {
                        aux.pan.setText("2."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("2")) {
                        aux.pan.setText("3."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("3")) {
                        aux.pan.setText("4."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("4")) {
                        aux.pan.setText("5."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("5")) {
                        aux.pan.setText("6."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("6")) {
                        aux.pan.setText("7."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("7")) {
                        aux.pan.setText("8."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("8")) {
                        aux.pan.setText("9."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("9")) {
                        aux.pan.setText("10."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("10")) {
                        aux.pan.setText("11."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("11")) {
                        aux.pan.setText("12."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("12")) {
                        aux.pan.setText("13."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("13")) {
                        aux.pan.setText("14."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;   
                    } else if (test.get(0).equals("14")) {
                        aux.pan.setText("15."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = aux.siguiente;                       
                    } else if (test.get(0).equals("15")) {
                        aux.pan.setText("16."+test.get(1));
                        aux.tipo = test.get(1);
                        aux = listita.inicio;  
                    } else if (m.equals("correcto")) {
                        mover1();
                    } else if (m.equals("incorrecto")) {
                        while(!aux6.jugador2){
                            aux6 = aux6.siguiente;
                        }
                        if(aux6.anterior != null ){
                            mover1();
                            retroceder2();
                        }else{
                            mover1();  
                        }
                    }else if (i.equals("correcto2")) {
                        dout.writeUTF("correcto2");
                        mover2();

                    } else if (i.equals("incorrecto2")) {
                        dout.writeUTF("incorrecto2");
                        if(listita.getPos2().anterior != null ){
                            mover2();
                            retroceder1(); 
                        }else{
                            mover2();  
                        }
                        
                  //Funcionalidad de los retos de tipo suma          
                }else if (test.get(0).equals("suma")) {
                        int r = Integer.parseInt(test.get(1)) + Integer.parseInt(test.get(2));
                        String resultado3 = String.valueOf(JOptionPane.showInputDialog(null, "Escriba el resultado de la suma de "+ test.get(1) +" + "+ test.get(2) + "\n ¡Solo debe introducir números enteros!", "Reto", JOptionPane.PLAIN_MESSAGE));
                        resultado3 = resultado3.replaceAll("\\D+","");
                        if (resultado3.equals("")){
                            resultado3 = "321654987";
                        }
                        if(Integer.parseInt(resultado3) == r) {
                            JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                            dout.writeUTF("correcto");
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                            dout.writeUTF("incorrecto");
                        }
                        //Funcionalidad de los retos de tipo resta
                }else if (test.get(0).equals("resta")) {
                        int r = Integer.parseInt(test.get(1)) - Integer.parseInt(test.get(2));
                        String resultado3 = String.valueOf(JOptionPane.showInputDialog(null, "Escriba el resultado de la resta de "+ test.get(1) +" - "+ test.get(2) + "\n ¡Solo debe introducir números enteros!", "Reto", JOptionPane.PLAIN_MESSAGE));
                        resultado3 = resultado3.replaceAll("\\D+","");
                        if (resultado3.equals("")){
                            resultado3 = "321654987";
                        }
                        if(Integer.parseInt(resultado3) == r) {
                            JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                            dout.writeUTF("correcto");
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                            dout.writeUTF("incorrecto");
                        }
                        //Funcionalidad de los retos de tipo multiplicación
                }else if (test.get(0).equals("multiplicacion")) {
                        int r = Integer.parseInt(test.get(1)) * Integer.parseInt(test.get(2));
                        String resultado3 = String.valueOf(JOptionPane.showInputDialog(null, "Escriba el resultado de la multiplicación de "+ test.get(1) +" * "+ test.get(2) + "\n ¡Solo debe introducir números enteros!", "Reto", JOptionPane.PLAIN_MESSAGE));
                        resultado3 = resultado3.replaceAll("\\D+","");
                        if (resultado3.equals("")){
                            resultado3 = "321654987";
                        }
                        if(Integer.parseInt(resultado3) == r) {
                            JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                            dout.writeUTF("correcto");
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                            dout.writeUTF("incorrecto");
                        }
                       //Funcionalidad de los retos de tipo división
                }else if (test.get(0).equals("division")) {
                        int r = Integer.parseInt(test.get(1)) / Integer.parseInt(test.get(2));
                        String resultado3 = String.valueOf(JOptionPane.showInputDialog(null, "Escriba el resultado de la división de "+ test.get(1) +" ÷ "+ test.get(2) + "\n ¡Solo debe introducir números enteros!", "Reto", JOptionPane.PLAIN_MESSAGE));
                        resultado3 = resultado3.replaceAll("\\D+","");
                        if (resultado3.equals("")){
                            resultado3 = "321654987";
                        }
                        if(Integer.parseInt(resultado3) == r) {
                            JOptionPane.showMessageDialog(null, "Correcto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                            dout.writeUTF("correcto");
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto", "Reto", JOptionPane.INFORMATION_MESSAGE);
                            dout.writeUTF("incorrecto");
                        }  
                } else if (m.equals("dadoOn") && !listita.fin.jugador2) {
                        if (listita.getPos().tipo.equals("Final")||listita.getPos2().tipo.equals("Final")){
                        } else { Dado2.setEnabled(true);}   
                    }

                } catch (Exception ex) {
                    break;
                }
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
        jLabel1 = new javax.swing.JLabel();
        idlabel = new javax.swing.JLabel();
        Dado2 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        Panel13 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        Panel14 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        Panel15 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        Panel16 = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        Panel9 = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        Panel10 = new javax.swing.JTextPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        Panel11 = new javax.swing.JTextPane();
        jScrollPane15 = new javax.swing.JScrollPane();
        Panel12 = new javax.swing.JTextPane();
        jScrollPane14 = new javax.swing.JScrollPane();
        Panel5 = new javax.swing.JTextPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        Panel6 = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        Panel3 = new javax.swing.JTextPane();
        jScrollPane17 = new javax.swing.JScrollPane();
        Panel4 = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        Panel2 = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        Panel7 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        Panel1 = new javax.swing.JTextPane();
        jScrollPane18 = new javax.swing.JScrollPane();
        Panel8 = new javax.swing.JTextPane();
        p2 = new javax.swing.JLabel();
        p1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        host = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        p3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.0F);
        jPanel1.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel1.setText("Bienvenido:");

        idlabel.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        idlabel.setText(".............................");

        Dado2.setBackground(new java.awt.Color(255, 204, 204));
        Dado2.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        Dado2.setText("Dado");
        Dado2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Dado2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dado2(evt);
            }
        });

        Panel13.setFocusable(false);
        jScrollPane16.setViewportView(Panel13);

        Panel14.setFocusable(false);
        jScrollPane4.setViewportView(Panel14);

        Panel15.setFocusable(false);
        jScrollPane5.setViewportView(Panel15);

        Panel16.setFocusable(false);
        jScrollPane2.setViewportView(Panel16);

        Panel9.setFocusable(false);
        jScrollPane7.setViewportView(Panel9);

        Panel10.setFocusable(false);
        jScrollPane9.setViewportView(Panel10);

        Panel11.setFocusable(false);
        jScrollPane12.setViewportView(Panel11);

        Panel12.setFocusable(false);
        jScrollPane15.setViewportView(Panel12);

        Panel5.setFocusable(false);
        jScrollPane14.setViewportView(Panel5);

        Panel6.setFocusable(false);
        jScrollPane11.setViewportView(Panel6);

        Panel3.setFocusable(false);
        jScrollPane13.setViewportView(Panel3);

        Panel4.setFocusable(false);
        jScrollPane17.setViewportView(Panel4);

        Panel2.setFocusable(false);
        jScrollPane10.setViewportView(Panel2);

        Panel7.setFocusable(false);
        jScrollPane8.setViewportView(Panel7);

        Panel1.setFocusable(false);
        jScrollPane6.setViewportView(Panel1);

        Panel8.setFocusable(false);
        jScrollPane18.setViewportView(Panel8);

        p2.setIcon(new javax.swing.ImageIcon("src\\main\\java\\img\\ganon.png"));

        p1.setIcon(new javax.swing.ImageIcon("src\\main\\java\\img\\link.png"));

        jLabel2.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel2.setText("Host:");

        host.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        host.setText(".............................");

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel3.setText("Jugador 2");

        p3.setIcon(new javax.swing.ImageIcon("src\\main\\java\\img\\ganon.png"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(p2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p1)
                        .addGap(294, 294, 294)
                        .addComponent(Dado2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(host, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(idlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(p3)
                                .addGap(41, 41, 41)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(p1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(idlabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(host))
                                .addGap(17, 17, 17))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(p3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Dado2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método implementa un botón con el cual se ejecutan las funciones del dado
     * y las funciones de cada casilla dependiendo de donde caiga el jugador     
     * @param evt evento consecuente al pulsar el botón Todos
     */
    private void Dado2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dado2
        try {
            //Se crea un random y un int que determinara el valor del dado de forma aleatoria en un rango de 1 a 4
            //listita.mostrarLIF();
            Random r = new Random();
            int ValorDado = r.nextInt(4)+1;
            //String m = "reto";
            
            
            
            //Este ciclo permite mover al jugador dependiendo el valor del dado            
            while (ValorDado>0 && listita.getPos().siguiente != null  ) {
                dout.writeUTF("mover2");
                mover2();
                ValorDado--;
            }
            aux4 = listita.inicio;
            while(aux4.jugador2==false){
                aux4 = aux4.siguiente;
                
            }
            
            
            //Funcionalidad de las casillas tipo Túnel
            if(aux4.tipo.equals("Túnel")){                
                int tl = r.nextInt(3)+1;
                String tltext = Integer.toString(tl);
                JOptionPane.showMessageDialog(null, "Te mueves "+ tltext+ " casilla(s)", "Túnel", JOptionPane.INFORMATION_MESSAGE);
                while(tl>0 && listita.getPos().siguiente != null){
                    dout.writeUTF("mover2");
                    mover2();
                    tl--;
                }
            }
            
            //Funcionalidad de las casillas tipo Trampa
            else if(aux4.tipo.equals("Trampa")) {                
                int tp = r.nextInt(3)+1;
                String tptext = Integer.toString(tp);
                JOptionPane.showMessageDialog(null, "Te devuelves "+ tptext+ " casilla(s)", "Trampa", JOptionPane.INFORMATION_MESSAGE);               
                while(tp>0 && listita.getPos2().anterior != null){
                    dout.writeUTF("retroceder2");
                    retroceder2();
                    tp--;
                }
            }
            
            //Funcionalidad de las casillas tipo Reto
            else if(aux4.tipo.equals("Reto")) {                
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
                JOptionPane.showMessageDialog(null, "Reto para el jugador 1", "Reto", JOptionPane.INFORMATION_MESSAGE);
                try {                    
                    dout.writeUTF(Sop + "," + Sop1 + "," + Sop2); // "suma,10,5"
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "ERRRRRRRRRRROR", "Errrrr", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
  
        } catch (Exception ex) {
          
        }
        //Esto permite desabilitar el botón del dado
        this.Dado2.setEnabled(false);
        try {
            dout.writeUTF("dadoOn");
        } catch (IOException ex) {
            Logger.getLogger(Micliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
    
    }//GEN-LAST:event_Dado2

    /**
     * Este método se encarga de comunicar al servidor si un cliente ha salido
     *
     * @param evt evento consecuente al cerrar la ventana del cliente
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
            String i = "mkoihgteazdcvgyhujb096785542AXTY";
            try {
                dout.writeUTF(i);
                this.dispose();

            } catch (IOException ex) {
                Logger.getLogger(Micliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Dado2;
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
    private javax.swing.JLabel host;
    private javax.swing.JLabel idlabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p3;
    // End of variables declaration//GEN-END:variables
}
