package TratamientoMandelbrot;

import java.util.ArrayList;

import visorFractal.Ventana;

/**
 * Genera la lista de listas de puntos seleccionados del fractal para tratar los datos que escapan en una iteración concreta
 * 
 * @author PABLO ARIENZA
 * @version 10.07.2017
 */

public class ConjuntoDeConjuntos {
	
	private ArrayList<ConjuntoDePuntos> listaDeConjuntos;
	private int ultimoLeido;
	private int encriptarODesencriptar;

	/**
	 * Constructor de la clase
	 * 
	 * @param encriptarODesencriptar <ul>
	 * 									<li>0: encriptar</li>
	 * 									<li>1: desencriptar</li>
	 * 								 </ul>
	 */
	public ConjuntoDeConjuntos(int encriptarODesencriptar) {
		this.listaDeConjuntos = new ArrayList<ConjuntoDePuntos>();
		this.ultimoLeido = -1;
		this.encriptarODesencriptar = encriptarODesencriptar;
	}//fin del constructor
	
	/**
	 * Método que añade un punto al conjunto al que pertenece en función de su valor de escape
	 * 
	 * @param punto el punto a incluir en un conjunto
	 * @param iteracion el valor de escape
	 */
	public void addPuntoASuConjunto(Punto punto, int iteracion){
		boolean anotado = false;
		for(ConjuntoDePuntos c : listaDeConjuntos){
			if(c.getID() == iteracion){
				c.addPunto(punto);
				anotado = true;
				break;
			}
		}
		//Si no existe un conjunto con el valor de escape, lo crea y añade el punto
		if(!anotado){
			ConjuntoDePuntos nuevo = new ConjuntoDePuntos(iteracion);
			nuevo.addPunto(punto);
			listaDeConjuntos.add(nuevo);
		}
	}//fin del método
	
	/**
	 * Método que calcula la iteración en la que el punto escapa del límite establecido
	 * 
	 * @param punto el punto a iterar
	 * @param centro el punto considerado como centro cartesiano
	 * @param iteraciones el número de veces máximo que se iterará el punto
	 * @param limite el valor máximo de la ecuación de escape
	 * @param escala el modificador de la ecuación de escape
	 */
	public void calculaElConjuntoDelPunto(Punto punto, Punto centro, int iteraciones, int limite, int escala){
		//se transforman las coordenadas de pantalla de cada punto en cartesianas desde el punto centro
		float xC = ((float)punto.getX() - (float)centro.getX())/(float)limite;
		float yC = ((float)centro.getY() - (float)punto.getY())/(float)limite;
		float zX = 0;
		float zY = 0;
		//para el punto c = a + bi
		//f(z)=z^2+c
		for(int i = 0; i < iteraciones; i++){
			float nX = zX * zX - zY * zY + xC;
			float nY = 2 * zX * zY + yC;
			zX = nX;
			zY = nY;
			//la ecuación de escape es x^2-y^2<limite
			if(zX * zX - zY * zY > (float)limite){
				addPuntoASuConjunto(punto, i);
				break;
			}
		}
	}//fin del método
	
	/**
	 * Método que calcula a qué conjunto pertenece cada punto de un plano
	 * 
	 * @param ancho el ancho del plano
	 * @param alto alto del plano
	 * @param centro centro cartesiano
	 * @param inicio punto desde el que se empieza la iteración de puntos
	 * @param iteraciones l número de veces máximo que se iterará cada punto
	 * @param limite el valor máximo de la ecuación de escape
	 * @param escala el modificador de la ecuación de escape
	 * @return la lista de listas de puntos seleccionados del fractal para tratar los datos
	 */
	public ArrayList<ConjuntoDePuntos> calculaLosConjuntos(int ancho, int alto, Punto centro, Punto inicio, int iteraciones, int limite, int escala){
	
		for(int i = 0; i < ancho; i++){
			for(int j = 0; j < alto; j++){
				calculaElConjuntoDelPunto(new Punto(i, j), centro, iteraciones, limite, escala);
			}
		}
		//convierte la lista de listas en un array de listas
		ConjuntoDePuntos[] arrayDePuntos = new ConjuntoDePuntos[iteraciones];
		for(ConjuntoDePuntos c : listaDeConjuntos){
			arrayDePuntos[c.getID()] = c;
		}
		ArrayList<Integer> filasVacias = new ArrayList<Integer>();
		for(int i = 0; i  < arrayDePuntos.length; i++){
			if(arrayDePuntos[i] == null) filasVacias.add(i);
		}
		ConjuntoDePuntos[] arrayDeConjuntosDepurado = new ConjuntoDePuntos[iteraciones - filasVacias.size()];
		int restadas = 0;
		for(int i = 0; i < arrayDePuntos.length; i++){
			if(arrayDePuntos[i] == null){
				restadas++;
			}else{
				arrayDeConjuntosDepurado[i - restadas] = arrayDePuntos[i];
			}
		}
		//presenta en pantalla una imagen con los conjuntos de puntos
		Ventana v = new Ventana(listaDeConjuntos, ancho, alto+29);
		return listaDeConjuntos;
	}//fin del método
	
	/**
	 * Método que devuelve el siguiente punto con el que transformar el siguiente dato
	 * 
	 * @return el punto siguiente del siguiente conjunto
	 */
	public Punto leePunto(){
		ultimoLeido++;
		//si llega al final reinicia la lista
		if(ultimoLeido >= listaDeConjuntos.size()){
			ultimoLeido = 0;
		}	
		return listaDeConjuntos.get(ultimoLeido).getPunto();
	}//fin del método

	/**
	 * Método que imprime por consola los conjuntos de puntos seleccionados
	 */
	public void printConjuntoDeConjuntos(){
		for(ConjuntoDePuntos c : listaDeConjuntos){
			c.printConjuntoDePuntos();
		}
	}//fin del método
	
	/**
	 * Método que imprime por consola el número de puntos en cada conjunto
	 */
	public void numeroDeConjuntos(){
		
		for(ConjuntoDePuntos c : listaDeConjuntos){
			System.out.println("Puntos en el conjunto " + c.getID() + ": " + c.numeroDePuntos());
		}
		System.out.println("Número de conjuntos : " + listaDeConjuntos.size());
	}//fin del método
}//fin de la clase
