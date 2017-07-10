package visorFractal;

import java.util.ArrayList;

import javax.swing.JFrame;

import TratamientoMandelbrot.ConjuntoDePuntos;

/**
 * Contiene el panel en el que se pintan los conjuntos de puntos seleccionados para el tratamiento de datos
 * 
 * @author PABLO ARIENZA
 * @version 10.07.2017
 */
public class Ventana {
	
	/**
	 * Constructor de la clase
	 * 
	 * @param listaDeConjuntos la lista de listas de puntos 
	 * @param ancho el ancho de la ventana
	 * @param alto el alto de la ventana
	 */
	public Ventana(ArrayList<ConjuntoDePuntos> listaDeConjuntos, int ancho, int alto){
		JFrame ventana = new JFrame("Conjunto de Mandelbrot");
		ventana.setSize(ancho,alto);		
		ventana.add(new Panel(listaDeConjuntos, ancho, alto-29));
		ventana.setVisible(true);
		//ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
	}//fin del constructor
}//fin de la clase
