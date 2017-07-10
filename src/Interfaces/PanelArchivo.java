package Interfaces;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import TratamientoMandelbrot.PreparacionDatos;
import TratamientoMandelbrot.SHA256;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelArchivo extends JPanel {
	private JTextField txtPassword;
	private SHA256 sha;
	JLabel lblSha_1 = new JLabel("sha");
	JButton btnOperar = new JButton("Encriptar o Desencriptar");
	JLabel lblNombreDeArchivo = new JLabel("Nombre de archivo");
	JTextArea txtOrigen = new JTextArea();
	JTextArea txtDestino = new JTextArea();
	private int encriptarODesencriptar;
	private File archivoOrigen, destino;
	

	/**
	 * Create the panel.
	 */
	public PanelArchivo() {
		setBorder(new TitledBorder(null, "Datos del archivo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBounds(0,143, 300, 300);
		setLayout(null);
		
		JLabel lblTituloNombre = new JLabel("Nombre:");
		lblTituloNombre.setBounds(7, 20, 58, 14);
		add(lblTituloNombre);
		
		
		lblNombreDeArchivo.setBounds(72, 20, 218, 14);
		add(lblNombreDeArchivo);
		
		JLabel lblRuta = new JLabel("Ruta:");
		lblRuta.setBounds(7, 41, 46, 14);
		add(lblRuta);
		txtOrigen.setLineWrap(true);
		
		
		txtOrigen.setFont(new Font("Arial", Font.PLAIN, 11));
		txtOrigen.setText("la ruta origen");
		txtOrigen.setRows(3);
		txtOrigen.setBackground(SystemColor.menu);
		txtOrigen.setEnabled(false);
		txtOrigen.setBounds(7, 56, 283, 45);
		add(txtOrigen);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(7, 178, 106, 14);
		add(lblPassword);
		
		lblSha_1 = new JLabel("");
		lblSha_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblSha_1.setBounds(7, 240, 283, 14);
		add(lblSha_1);
		
		txtPassword = new JTextField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String data = txtPassword.getText();
//				System.out.println(txtPassword.getText());
				sha = new SHA256(data);
				lblSha_1.setText(sha.getSha256());
			}
		});
		
		txtPassword.setBounds(7, 203, 283, 20);
		add(txtPassword);
		txtPassword.setColumns(10);
		btnOperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPassword.getText().equals("")){
					JOptionPane.showMessageDialog(getParent(), "Debe introducir una contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					PreparacionDatos pD = new PreparacionDatos(lblSha_1.getText(), encriptarODesencriptar, archivoOrigen, destino);
					
				}
			}
		});	
		btnOperar.setBounds(7, 258, 283, 36);
		add(btnOperar);
		txtDestino.setLineWrap(true);		
		txtDestino.setText("la ruta de destino");
		txtDestino.setRows(3);
		txtDestino.setFont(new Font("Arial", Font.PLAIN, 11));
		txtDestino.setEnabled(false);
		txtDestino.setBackground(SystemColor.menu);
		txtDestino.setBounds(7, 127, 283, 45);
		add(txtDestino);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setBounds(7, 112, 46, 14);
		add(lblDestino);
		
		JLabel lblSha = new JLabel("SHA-256:");
		lblSha.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblSha.setBounds(7, 222, 51, 14);
		add(lblSha);
		
		

	}

	public void setEncriptarODesencriptar(int i) {
		encriptarODesencriptar = i;
		
	}

	public File getArchivoOrigen() {
		return archivoOrigen;
	}

	public void setArchivoOrigen(File archivoOrigen) {
		this.archivoOrigen = archivoOrigen;
	}

	public File getDestino() {
		return destino;
	}

	public void setDestino(File destino) {
		this.destino = destino;
	}
	
	
}
