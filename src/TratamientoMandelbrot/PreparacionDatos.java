package TratamientoMandelbrot;

import java.io.File;
import java.util.ArrayList;
import tratamientoDatos.TratamientoDatos;

/**
 * Llama a los objetos necesarios para realizar el tratamiento de los datos
 * 
 * @author PABLO ARIENZA
 * @version 10.07.2017
 */
public class PreparacionDatos {

	private String sha;
	private int encriptarODesencriptar;
	private File archivoOrigen, destino;

	/**
	 * Constructor de la clase
	 * 
	 * @param sha el SHA de la contrase�a
	 * @param encriptarODesencriptar <ul>
	 * 									<li>0: encriptar</li>
	 * 									<li>1: desencriptar</li>
	 * 								 </ul>
	 * @param archivoOrigen archivo que se va a tratar
	 * @param destino carpeta donde se va a  guardar
	 */
	public PreparacionDatos(String sha, int encriptarODesencriptar, File archivoOrigen, File destino) {
		this.sha = sha;
		this.encriptarODesencriptar = encriptarODesencriptar;
		//almacena los par�metros del fractal
		int[] SCE = cortaSha();
		this.archivoOrigen = archivoOrigen;
		this.destino = destino;
		ConjuntoDeConjuntos c = new ConjuntoDeConjuntos(encriptarODesencriptar);
		//se capturan las listas de puntos seleccionados del fractal
		ArrayList<ConjuntoDePuntos> lista = c.calculaLosConjuntos(SCE[0], SCE[1], new Punto(SCE[2], SCE[3]), new Punto(SCE[4], SCE[5]), SCE[6], SCE[7], SCE[8]);
		TratamientoDatos TD = new TratamientoDatos(c, encriptarODesencriptar, archivoOrigen, destino, new Punto(SCE[4], SCE[5]));
		//se transforma el archivo
		TD.tratarArchivo();
	}//fin del constructor
	
	/**
	 * M�todo para separar el SHA en grupos de caracteres y se transforman para obtener los p�rametros del fractal
	 * 
	 * @return los par�metros del fractal
	 */
	public int[] cortaSha(){
		int[] shaCortadoEntero = new int[10];
		String nombres[] = {"ancho", "alto", "xCentro", "yCentro", "xInicio", "yInicio", "iteraciones", "escala", "limite", "resto"};
		//separa los caracteres del SHA, los transforma a entero y los suma a su par�metro
		for(int i = 0; i < 64; i++){
			shaCortadoEntero[i/7] += Character.getNumericValue(sha.charAt(i));
		}
		//garantiza que el resto no sea 0
		shaCortadoEntero[9] += 10;
		//garantiza que el punto de inicio est� dentro de los l�mites
		if(shaCortadoEntero[4] > shaCortadoEntero[2]) shaCortadoEntero[4] /= 2;
		if(shaCortadoEntero[5] > shaCortadoEntero[3]) shaCortadoEntero[5] /= 2;
		//ampl�a el plano para obtener un mayor n�mero de puntos �tiles
		for(int i = 0; i < 9; i++){
			shaCortadoEntero[i] *= shaCortadoEntero[9];	
		}
		//imprime en consola los par�metros del fractal
		for(int j = 0; j < 10; j++){
			System.out.println(nombres[j] + ": " + shaCortadoEntero[j]);
		}
		return shaCortadoEntero;
	}//fin del m�todo
	
	/**
	 * M�todo que devuelve si la operaci�n es encriptar o desencriptar
	 * 
	 * @return <ul>
	 * 			   <li>0: encriptar</li>
	 * 			   <li>1: desencriptar</li>
	 * 		   </ul>
	 */
	public int getEncriptarODesencriptar(){
		return encriptarODesencriptar;
	}//fin del m�todo
}//fin de la clase
