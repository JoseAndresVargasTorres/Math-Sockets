package Cliente;

import Server.ListaDoble;
import Server.NodoDoble;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.net.Socket;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Arrays;


/**
 * Esta clase es para crear cada cliente y proporciona la interfaz gráfica del chat
 *
 * @author Kendall Marín Muñoz,Carlos Andres Contreras Luna, Jose Andres Vargas Torres
 * 
 */
public class Micliente extends javax.swing.JFrame {
    String iD, clientID = "";
    DataInputStream din;
    DataOutputStream dout;
    DefaultListModel dlm;
    int p1x = 20;
    int p1x2 = 106;
    int p1y = 205;
    int p2y = 205;
    int cont = 3;
    int cont2 = 3;
    int movex1 = 105;
    int movex2 = 105;
    ListaDoble listita = new ListaDoble();
    
    /**
     * Metodo que permite mover al jugador de posicion
     */
    public void mover1(){
        if (cont == 0){            
            p1y += 76;
            this.p1.setLocation(p1x,p1y);
            cont = 3;
            movex1 *= -1; 
        } else {
            System.out.println(cont);
            p1x += movex1;
            this.p1.setLocation(p1x,p1y);
            cont--;
            }
    }
        
    

    /**
     * Crea un nuevo cliente
     */
    public Micliente() {
        initComponents();
    }

    /**
     * Este método asigna el usuario y el socket al cliente
     *
     * @param i el nombre del usuario en el cliente
     * @param s el socket que utiliza el cliente
     */
     Micliente(String i, Socket s) {
        iD = i;
        try {
            initComponents();
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
            dlm = new DefaultListModel();
            UL.setModel(dlm);
            idlabel.setText(i);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            this.p1.setLocation(20,205);
            this.p2.setLocation(69,205);
            getContentPane().setComponentZOrder(this.p1, 0);
            getContentPane().setComponentZOrder(this.p2, 0);
            new Read().start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Esta clase es para enviar los mensajes
     */
    class Read extends Thread {
        /**
         * Este método envía los mensajes y detecta si se solicitó un monto,
         * respondiendo automáticamente el resultado del cálculo del monto
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
                    } else if (test.get(0).equals("0")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("1")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("2")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("3")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("4")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("5")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("6")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("7")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("8")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("9")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("10")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("11")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("12")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;
                    } else if (test.get(0).equals("13")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;   
                    } else if (test.get(0).equals("14")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;  
                    } else if (test.get(0).equals("15")) {
                        aux.pan.setText(test.get(1));
                        aux = aux.siguiente;  
                }

                } catch (Exception ex) {
                    break;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idlabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        todos = new javax.swing.JButton();
        enviar = new javax.swing.JButton();
        msgText = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        UL = new javax.swing.JList<>();
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
        jButton2 = new javax.swing.JButton();
        JP2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.0F);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Bienvenido:");

        idlabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idlabel.setText(".............................");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        todos.setBackground(new java.awt.Color(255, 204, 204));
        todos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        todos.setText("Todos");
        todos.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todos(evt);
            }
        });

        enviar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        enviar.setText("Enviar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviar(evt);
            }
        });

        UL.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ULValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(UL);

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

        p2.setIcon(new javax.swing.ImageIcon("C:\\Users\\jose\\OneDrive - Estudiantes ITCR\\ordenador\\Documentos\\NetBeansProjects\\Math-Sockets\\MathGame\\src\\main\\java\\Images\\ganon.png")); // NOI18N

        p1.setIcon(new javax.swing.ImageIcon("C:\\Users\\jose\\OneDrive - Estudiantes ITCR\\ordenador\\Documentos\\NetBeansProjects\\Math-Sockets\\MathGame\\src\\main\\java\\Images\\link.png")); // NOI18N

        jButton2.setText("jButton1");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        JP2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JP2.setText("...................");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(p2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(p1))
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
                                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(JP2)
                                        .addGap(101, 101, 101))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1)
                                        .addGap(32, 32, 32)))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(msgText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(enviar, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(todos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idlabel)
                    .addComponent(todos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addGap(19, 19, 19)
                        .addComponent(JP2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p2)
                            .addComponent(p1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(msgText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método implementa una lista donde se puede seleccionar un único
     * cliente al cual enviar el mensaje
     *
     * @param evt evento consecuente al seleccionar una opción de la lista
     */
    private void ULValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ULValueChanged
        clientID = (String) UL.getSelectedValue();
    }//GEN-LAST:event_ULValueChanged

    /**
     * Este método implementa un botón que se encarga de tomar el texto ingresado
     * y enviarlo al servidor como un paquete
     *
     * @param evt evento consecuente al presionar el botón enviar
     */
    private void enviar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviar
        try {
            String m = msgText.getText(), mm = m;
            String CI = clientID;

            if (!clientID.isEmpty()) {
                m = "#4344554@@@@@67667@@" + CI + ":" + mm;
                dout.writeUTF(m);
                msgText.setText("");
            } else {
                dout.writeUTF(m);
                msgText.setText("");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "El usuario ya no está.");
        }
    }//GEN-LAST:event_enviar

    /**
     * Este método implementa un botón con el cual seleccionar a todos los
     * clientes para enviar los mensajes de forma general
     *
     * @param evt evento consecuente al pulsar el botón Todos
     */
    private void todos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todos
        clientID = "";
    }//GEN-LAST:event_todos

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

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
        }// Evento al cerrar la ventana

    /**
     * @param args los argumentos de la línea de comando
     */

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
    private javax.swing.JList<String> UL;
    private javax.swing.JButton enviar;
    private javax.swing.JLabel idlabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField msgText;
    public javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    private javax.swing.JButton todos;
    // End of variables declaration//GEN-END:variables
}
