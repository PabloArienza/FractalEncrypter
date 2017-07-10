package tratamientoDatos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import TratamientoMandelbrot.ConjuntoDeConjuntos;
import TratamientoMandelbrot.ConjuntoDePuntos;
import TratamientoMandelbrot.Punto;

/**
 * Abre el archivo, encripta o desencripta y guarda el resultado
 * 
 * @author PABLO ARIENZA
 * @version 10.07.2017
 */
public class TratamientoDatos {

	ConjuntoDeConjuntos lista;
	private int encriptarODesencriptar;
	private File archivoOrigen, destino;
	private Punto inicio;
	/**
	 * Constructor de la clase
	 * 
	 * @param lista la lista de listas de puntos seleccionados del fractal para tratar los datos
	 * @param encriptarODesencriptar <ul>
	 * 									<li>0: encriptar</li>
	 * 									<li>1: desencriptar</li>
	 * 								 </ul>
	 * @param archivoOrigen archivo que se va a tratar
	 * @param destino carpeta donde se va a  guardar
	 * @param inicio punto desde el que se empieza la iteración de puntos
	 */
	public TratamientoDatos(ConjuntoDeConjuntos lista, int encriptarODesencriptar, File archivoOrigen, File destino, Punto inicio){
		this.lista = lista;
		this.encriptarODesencriptar = encriptarODesencriptar;
		this.archivoOrigen = archivoOrigen;
		this.destino = destino;
		this.inicio = inicio;
	}//fin del constructor
	
	/**
	 * Método que abre el archivo y selecciona la operación
	 */
	public void tratarArchivo(){
		if(destino != null){
			FileReader fr;
			try {
				FileWriter fw = new FileWriter(destino + "\\" + archivoOrigen.getName() + ".frac");
				try {
					fr = new FileReader(archivoOrigen);
					if(encriptarODesencriptar == 0){
						encriptar(fr, fw);
					}else{
						desencriptar(fr, fw);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}//fin del método
	
	/**
	 * Método para encriptar el archivo
	 * 
	 * @param fr el archivo a encriptar
	 * @param fw el archivo donde se guarda la encriptación
	 */
	public void encriptar(FileReader fr, FileWriter fw){
		int caracter;
		try {
			caracter = fr.read();
			Punto punto = inicio;
			while(caracter != -1){
				caracter = (caracter + punto.getX() + punto.getY())%256;
				fw.write((char)caracter);
				System.out.print(caracter + "-");
				punto = lista.leePunto();
				caracter = fr.read();
			}
			fr.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//fin del método
	
	/**
	 * Método para desencriptar el archivo
	 * 
	 * @param fr el archivo a desencriptar
	 * @param fw el archivo donde se guarda la desencriptación
	 */
	public void desencriptar(FileReader fr, FileWriter fw){
		int caracter;
		try {
			caracter = fr.read();
			Punto punto = inicio;
			while(caracter != -1){
				caracter = caracter - punto.getX() - punto.getY();
				while(caracter < 0){
					caracter += 256;
				}
				fw.write((char)caracter);
				System.out.print(caracter + "-");
				punto = lista.leePunto();
				caracter = fr.read();
			}
			fr.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//fin del método
}//fin de la clase

