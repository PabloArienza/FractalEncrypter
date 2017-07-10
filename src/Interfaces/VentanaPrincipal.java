package Interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import TratamientoMandelbrot.PreparacionDatos;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;

/**
 * Interfaz de encriptación y desencriptación
 * 
 * @author PABLO ARIENZA
 * @version 10.07.2017
 */
public class VentanaPrincipal {

	private JFrame frmFraCrypt;
	private int encriptarODesencriptar;
	PanelArchivo pnlArchivo = new PanelArchivo();
	JFileChooser fcOrigen, fcDestino;

	/**
	 * Lanza la aplicación.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frmFraCrypt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la aplicación.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Carga el contenido de la ventana.
	 */
	private void initialize() {
		frmFraCrypt = new JFrame();
		frmFraCrypt.getContentPane().setBackground(Color.WHITE);
		frmFraCrypt.setTitle("FraCrypt");
		frmFraCrypt.setResizable(false);
		frmFraCrypt.setBounds(100, 100, 305, 170);
		frmFraCrypt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFraCrypt.getContentPane().setLayout(null);
		
		JButton btnMostarUnFractal = new JButton("Mostrar un fractal");
		btnMostarUnFractal.addActionListener(new ActionListener() {
			/**
			 * crea una ventana con un fractal con datos por defecto
			 */
			public void actionPerformed(ActionEvent e) {
				PreparacionDatos pD = new PreparacionDatos("5498dbf6e61a5968bbfb0ea011cb12b8bac2fba1bac458f6abd324c29112a424", 0, null, null);
			}
		});
		btnMostarUnFractal.setBounds(0, 0, 300, 70);
		frmFraCrypt.getContentPane().add(btnMostarUnFractal);
		
		JButton btnSeleccionarUnArchivo = new JButton("Seleccionar un archivo para operar");
		btnSeleccionarUnArchivo.addActionListener(new ActionListener() {
			/**
			 * Comienza el proceso de selección de un archivo para operar
			 */
			public void actionPerformed(ActionEvent e) {
				Object[] opciones = {"Encriptar", "Desencriptar"};
				int dialogoOperacion = JOptionPane.showOptionDialog(frmFraCrypt, "¿Qué operación desea realizar?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
				pnlArchivo.setVisible(true);
				if(dialogoOperacion == JOptionPane.YES_OPTION){
					pnlArchivo.btnOperar.setText("Encriptar");
					pnlArchivo.setEncriptarODesencriptar(0);
					encriptarODesencriptar = 0;
					continuar(pnlArchivo);
				}else if(dialogoOperacion == JOptionPane.NO_OPTION){
					pnlArchivo.btnOperar.setText("Desencriptar");
					pnlArchivo.setEncriptarODesencriptar(1);
					encriptarODesencriptar = 1;
					continuar(pnlArchivo);
				}else{
					pnlArchivo.setVisible(false);
					frmFraCrypt.setSize(305, 170);
				}
			}//fin del método	
			
			/**
			 * Selecciona el archivo con el que operar y la carpeta donde guardar el resultado
			 * @param pnlArchivo el panel de presentación de la información de la operación
			 */
			public void continuar(PanelArchivo pnlArchivo){
				fcOrigen = new JFileChooser();
				fcOrigen.setMultiSelectionEnabled(false);
				JOptionPane.showMessageDialog(frmFraCrypt, "Seleccionar un archivo para operar.");
				int seleccion = fcOrigen.showOpenDialog(btnSeleccionarUnArchivo);
            	if(seleccion == JFileChooser.APPROVE_OPTION){
//            		System.out.println(fcOrigen.getSelectedFile());
            		pnlArchivo.lblNombreDeArchivo.setText(fcOrigen.getSelectedFile().getName());
            		pnlArchivo.txtOrigen.setText(fcOrigen.getSelectedFile().getAbsolutePath());
            		pnlArchivo.setArchivoOrigen(fcOrigen.getSelectedFile());
            		JOptionPane.showMessageDialog(frmFraCrypt, "Ahora seleccionar una carpeta donde guardarlo.");
            		fcDestino = new JFileChooser();
            		fcDestino.setMultiSelectionEnabled(false);
            		fcDestino.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            		fcDestino.setCurrentDirectory(fcOrigen.getCurrentDirectory());
            		seleccion = fcDestino.showOpenDialog(btnSeleccionarUnArchivo);
            		if(seleccion == JFileChooser.APPROVE_OPTION){
//                		System.out.println(fcDestino.getSelectedFile());
                		pnlArchivo.txtDestino.setText(fcDestino.getSelectedFile().getAbsolutePath());   
                		pnlArchivo.setDestino(fcDestino.getSelectedFile());
                		frmFraCrypt.getContentPane().add(pnlArchivo);
                		frmFraCrypt.setSize(305, 170 + 302);
            		}else{
            			pnlArchivo.setVisible(false);
    					frmFraCrypt.setSize(305, 170);
            		}	
            	}else{
            		pnlArchivo.setVisible(false);
					frmFraCrypt.setSize(305, 170);
            	}
				
			}//fin del método
		});
		btnSeleccionarUnArchivo.setBounds(0, 72, 300, 70);
		frmFraCrypt.getContentPane().add(btnSeleccionarUnArchivo);
	}
}
