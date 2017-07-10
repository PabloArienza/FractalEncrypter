package visorFractal;

import java.awt.Color;
/**
 * Prepara los colores para representar los diferentes conjuntos de puntos seleccionados para el tratamiento de los datos
 * 
 * @author PABLO ARIENZA
 * @version 10.07.2017
 */
public class Colores {
	

	private Color[] colores ={Color.RED, Color.BLUE, Color.GREEN, Color.GRAY, Color.YELLOW, 
					  		  Color.CYAN, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.MAGENTA};
	
	private int colorElegido;
	
	/**
	 * Constructor de la clase
	 */
	public Colores(){
		this.colorElegido = 0;
	}//fin del constructor
	
	/**
	 * Método para seleccionar el color del conjunto
	 * 
	 * @return el color para el conjunto
	 */
	public Color seleccionaColor(){
		colorElegido++;
		//reinicia la selección de color
		if(colorElegido == colores.length) colorElegido = 0;
		return colores[colorElegido];
	}//fin del método
}//fin de la clase
