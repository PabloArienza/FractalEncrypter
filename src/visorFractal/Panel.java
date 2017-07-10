package visorFractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import TratamientoMandelbrot.ConjuntoDePuntos;
import TratamientoMandelbrot.Punto;

/**
 * Dibuja los conjuntos de puntos seleccionados para el tratamiento de los datos
 * 
 * @author PABLO ARIENZA
 * @version 10.07.2017
 */
public class Panel extends JPanel{
	private ArrayList<ConjuntoDePuntos> listaDeConjuntos;
	private int ancho, alto;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param listaDeConjuntos la lista de listas de puntos
	 * @param ancho la anchura del panel
	 * @param alto la altura del panel
	 */
	public Panel(ArrayList<ConjuntoDePuntos> listaDeConjuntos, int ancho, int alto){
		this.listaDeConjuntos = listaDeConjuntos;
		this.ancho = ancho;
		this.alto = alto;
	}//fin del constructor
	
	/**
	 * Método que sobreescribe el de la clase de la que hereda
	 */
	@Override
	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D)g;
		//pinta el fondo blanco
		g2D.setColor(Color.WHITE);
		g2D.fillRect(0, 0, ancho, alto);
		Colores color = new Colores();
		for(ConjuntoDePuntos c : listaDeConjuntos){
			//selecciona un color
			g2D.setColor(color.seleccionaColor());
			//selecciona una lista de puntos
			Punto[] array = c.arrayDePuntos();
			for(int i = 0; i < array.length; i++){
				//pinta en punto en su color en el panel
				g2D.fillRect(array[i].getX(), array[i].getY(), 1, 1);
			}
		}
	}//fin del método
}//fin de la clase
