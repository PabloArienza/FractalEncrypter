package TratamientoMandelbrot;

import java.util.ArrayList;

/**
 * Genera la lista de puntos seleccionados del fractal para tratar los datos que escapan en una iteraci�n concreta
 * 
 * @author PABLO ARIENZA
 * @version 10.07.2017
 */
public class ConjuntoDePuntos {
	
	private int iD, ultimoLeido;
	private ArrayList<Punto> listaDePuntos;
	/**
	 * Constructor de la clase
	 * 
	 * @param iD identidad del conjunto de puntos que coincide con la iteraci�n en la que escapa
	 */
	public ConjuntoDePuntos(int iD) {
		this.iD = iD;
		this.ultimoLeido = -1;
		this.listaDePuntos = new ArrayList<Punto>();
	}//fin del constructor
	
	/**
	 * M�todo que devuelve la identidad del conjunto de puntos
	 * 
	 * @return identidad del conjunto de puntos
	 */
	public int getID(){
		return iD;
	}//fin del m�todo
	
	/**
	 * M�todo que a�ade el punto a la lista
	 * @param punto el punto que se a�ade a la lista
	 */
	public void addPunto(Punto punto){
		listaDePuntos.add(punto);
		
	}//fin del m�todo
	
	/**
	 * M�todo que devuelve el siguiente punto con el que operar
	 * 
	 * @return el punto con el que operar
	 */
	public Punto getPunto(){
		ultimoLeido++;
		//si el �ltimo le�do era el �ltimo de la lista empieza desde el principio
		if(ultimoLeido >= listaDePuntos.size()){
			ultimoLeido = 0;
		}
		return listaDePuntos.get(ultimoLeido);
	}//fin del m�todo

	/**
	 * M�todo que convierte la lista en un array de dimensi�n fija
	 * 
	 * @return array de puntos del conjunto
	 */
	public Punto[] arrayDePuntos(){
		Punto[] array = new Punto[listaDePuntos.size()];
		for(int i = 0; i < listaDePuntos.size(); i++){
			array[i] = listaDePuntos.get(i);
		}
		return array;
	}//fin del m�todo
	
	/**
	 * M�todo que imprime por consola todos los puntos que pertenecen al conjunto
	 */
	public void printConjuntoDePuntos(){
		System.out.print("Conjunto " + iD + ":");
		int salto = 0;
		for(int i = 0; i < listaDePuntos.size(); i++){
			System.out.print(listaDePuntos.get(i).printPunto());
			salto++;
			if(salto < 20){
				System.out.print(",");
			}else{
				System.out.println();
				salto = 0;
				System.out.print("           ");
			}
		}
		System.out.println();
	}//fin del m�todo
	
	/**
	 * M�todo que devuelve el n�mero de puntos que hay en el conjunto
	 * 
	 * @return el n�mero de puntos del conjunto
	 */
	public int numeroDePuntos(){
		return listaDePuntos.size();
	}//fin del m�todo
}//fin de la clase
