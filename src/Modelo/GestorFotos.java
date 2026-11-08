package Modelo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class GestorFotos {
	
	    public static final String RUTA_BASE = "fotos/";
	    public static final String LIBROS = "fotosLibros";
	    public static final String ADMINISTRADORES = "fotosAdministradores";
	    public static final String USUARIOS = "fotosUsuarios";
	    

	    private static File rutaDe(String carpeta, String clave) { 
	    	 return new File(RUTA_BASE + carpeta + "/" + clave + ".png");
	    }
	    
	    public static void guardar(File origen, String carpeta, String clave) throws IOException { 
	    	BufferedImage imagen = ImageIO.read(origen);
	    	if (imagen == null) {
	    		throw new IllegalArgumentException("El archivo seleccionado no es una imagen valida");
	    	}
	    	File destino = rutaDe(carpeta, clave);
	    	ImageIO.write(imagen, "png", destino);
	    	
	    	
	    }
	    public static File obtener(String carpeta, String clave) {
	        File foto = rutaDe(carpeta, clave);
	        if (foto.exists()) {
	            return foto;
	        }
	        return new File(RUTA_BASE + "sinFoto.png");
	    }

	    public static void eliminar(String carpeta, String clave) {
	        File foto = rutaDe(carpeta, clave);
	        if (foto.exists()) {
	            foto.delete();
	        }
	    }
	}

