package co.uniquindio.proyectofinal.chat;

import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
@SuppressWarnings("deprecation")
public class Frm1 extends javax.swing.JFrame implements Observer {
 
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Frm1() {
    	
    	String nombre = JOptionPane.showInputDialog("Ingresa Tu Nombre");
    	
        initComponents(nombre);
        this.getRootPane().setDefaultButton(this.btnEnviar);
        Servidor s = new Servidor(5000);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
    }
 
    
    // &amp;lt;editor-fold defaultstate="collapsed" desc="Generated Code"&amp;gt;                          
    private void initComponents(String nombre) {
 
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTexto = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        txtTextoEnviar = new javax.swing.JTextField();
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat de: " + nombre);
 
        txtTexto.setColumns(20);
        txtTexto.setRows(5);
        jScrollPane1.setViewportView(txtTexto);
 
        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt, nombre);
                
                
            }
        });
 
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(txtTextoEnviar, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
        				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(txtTextoEnviar, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        				.addComponent(btnEnviar, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);
 
        pack();
    }// &amp;lt;/editor-fold&amp;gt;                        
 
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt, String nombre) {                                          
 
        
		String mensaje = nombre + ": " + this.txtTextoEnviar.getText() + "\n";
 
        this.txtTexto.append(mensaje);
 
        Cliente c = new Cliente(6000, mensaje);
        Thread t = new Thread(c);
        
        
        
        t.start();
        
        txtTextoEnviar.setText("");
 
 
    }   
    
    
    
   
 
    @Override
    public void update(Observable o, Object arg) {
        this.txtTexto.append((String) arg);
    }
 
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Frm1().setVisible(true);
//            }
//        });
//    }
 
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtTexto;
    private javax.swing.JTextField txtTextoEnviar;
}