package TratamientoMandelbrot;

/**
 * Construye un punto
 * 
 * @author PABLO ARIENZA
 * @version 10.07.2017
 */
public class Punto {

		private int x, y;
		/**
		 * Constructor  de la clase
		 * @param x coordenada X en dimensiones de pantalla (de 0 a infinito)
		 * @param y coordenada Y en dimensiones de pantalla (de 0 a infinito)
		 */
		public Punto(int x, int y){
			this.x = x;
			this.y =y;
		}//fin del constructor

		/**
		 * Método que devuelve la coordenada X
		 * @return x
		 */
		public int getX() {
			return x;
		}//fin del método

		/**
		 * Método que devuelve la coordenada Y
		 * @return y
		 */
		public int getY() {
			return y;
		}//fin del método
		
		/**
		 * Método que imprime en consola las coordenadas del punto en formato "[x,y]"
		 * @return "[x,y]"
		 */
		public String printPunto(){
			return "[" + x + "," + y + "]";
		}//fin del método
}//fin de la clase
