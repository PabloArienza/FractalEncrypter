package TratamientoMandelbrot;

import java.security.MessageDigest;

/**
 * Genera un SHA para generar los parámetros del fractal
 * 
 * @author PABLO ARIENZA
 * @version 10.07.2017
 */
public class SHA256 {

	private String sha256;
	/**
	 * Constructor de la clase
	 * @param password el texto con el que se genera el SHA
	 */
	public SHA256(String password){
		sha256 = creaSha256(password);
	}//fin del constructor
	
	/**
	 * Método que genera el SHA
	 * @param base el texto con el que se genera el SHA
	 * @return el SHA
	 */
	private String creaSha256(String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1){
	                hexString.append('0');
	                
	            }
	            hexString.append(hex);
	        }
	    System.out.println(hexString.toString());    
        return hexString.toString();
	    }catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}//fin del método
	
	/**
	 * Método para recuperar el SHA desde otro objeto
	 * @return el SHA
	 */
	public String getSha256() {
		return sha256;
	}//fin del método
}//fin de la clase
