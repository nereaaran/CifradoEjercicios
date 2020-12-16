package Utilidades;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utilidades {
	public static String leerString(){
		 String cadena="";
		 InputStreamReader entrada =new InputStreamReader(System.in);
		 BufferedReader teclado= new BufferedReader(entrada);
		 try{
			 cadena=teclado.readLine();
		 }
		 catch(IOException er){
			 System.out.println("error al introducir datos");
			 System.exit(0);
		 }
		 return cadena;
		}
	public static String introducirCadena(){
		 String cadena="";
		 InputStreamReader entrada =new InputStreamReader(System.in);
		 BufferedReader teclado= new BufferedReader(entrada);
		 try{
			 cadena=teclado.readLine();
		 }
		 catch(IOException er){
			 System.out.println("error al introducir datos");
			 System.exit(0);
		 }
		 return cadena;
		}
	public static String introducirCadena(String mensaje,int max){
		 String cadena="";
		 InputStreamReader entrada =new InputStreamReader(System.in);
		 BufferedReader teclado= new BufferedReader(entrada);
		 System.out.println(mensaje);
		 try{
			do {
			 cadena=teclado.readLine();
			 if(cadena.length()>max) {
				 System.out.println("Tamaño maximo: "+ max);
			 }
		 }while(cadena.length()>max);
		 }
		 catch(IOException er){
			 System.out.println("error al introducir datos");
			 System.exit(0);
		 }
		 return cadena;
		}
	
	public static String leerStringN(String mensaje){
		 String cadena="";
		 InputStreamReader entrada =new InputStreamReader(System.in);
		 BufferedReader teclado= new BufferedReader(entrada);
		 System.out.println(mensaje);
		 try{
			 do {
			 cadena=teclado.readLine();
			 if(cadena.length()==0) {
				 System.out.println("Error la cadena esta vacia");
			 }
			 }while(cadena.length()==0);
		 }
		 catch(IOException er){
			 System.out.println("error al introducir datos");
			 System.exit(0);
		 }
		 return cadena;
		}
	
	public static int leerInt() {
		int num=0;
		boolean error;
		do {
			error=false;
			try{
				num=Integer.parseInt(introducirCadena());
			}
			catch(NumberFormatException e) {
				System.out.println("Error, introduce un n�mero:");
				error=true;
			}
		}while (error);
		return num;
	}
	public static int leerIntM(String mensaje) {
		int num=0;
		boolean error;
		do {
			error=false;
			 System.out.println(mensaje);
			try{
				num=Integer.parseInt(introducirCadena());
			}
			catch(NumberFormatException e) {
				System.out.println("Error, introduce un n�mero:");
				error=true;
			}
		}while (error);
		return num;
	}
	public static int leerIntNum(int x, int y) {
		boolean ok;
		int num=0;
		
		do {
			ok=true;
			try {
				num=Integer.parseInt(introducirCadena());
			}catch(NumberFormatException e) {
				ok=false;
				System.out.println("ERROR INTRODUCE OTRA VEZ");
			
			}
			if(num<x||num>y) {
				ok=false;
				System.out.println("Error numero fuera de rango");
			}
			
		}while(!ok);
		
		return num;
	}
	public static char leerChar() {
		char letra;
		String frase;
		do {
			frase =introducirCadena();
			if (frase.length()!=1) {
				System.out.println("Error introduce un unico caracter");
				
			}
					
		}while(frase.length()!=1);
		letra=frase.charAt(0);
		return letra;
	}
	public static float leerFloat() {
		float num=0;
		short correcto=0;
		
		do {
			correcto=1;
			try {
				num=Float.parseFloat(introducirCadena());
			}catch(Exception e) {
				System.out.println("ERROR INTRODUCE OTRA VEZ");
				correcto=0;
			}
			
			
		}while(correcto==0);
		
		return num;
	}
	public static LocalDate leerFecha() {
		LocalDate fecha=LocalDate.now();
		String aux;
		boolean error;
		DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		do {
			error=false;
			aux=leerString();
			try {
				fecha=LocalDate.parse(aux, formatear);
				
			}catch(DateTimeParseException e){
				error=true;
				System.out.println("Error,Introduce fecha con formato dd/mm/aaaa: ");
			}
		}while(error);
		return fecha;
		
	}
	
	//Devuelve la cantidad de objetos de un fichero
	 public static int calculoFichero(File fich){
	 	int cont=0;
	 	
	 	if(fich.exists()) {
			 	FileInputStream fis=null;
			 	ObjectInputStream ois=null;
			 	try{
			 		fis=new FileInputStream(fich);
			 		ois=new ObjectInputStream(fis);
		
			 		Object aux=ois.readObject();
		
			 		while (aux!=null){
			 			cont++;
			 			aux=ois.readObject();
			 		}
			 		
		
			 	}catch(EOFException e1){
					//System.out.println("Has terminado de leer, "+cont+" objetos");
					
			 	}catch (Exception e2){
					 e2.printStackTrace();
			 	}
			 	
			 	try {
					ois.close();
					fis.close();
				} catch (IOException e) {
				
					System.out.println("Error al cerrar los flujos. ");
				}
	 	}	
	 	return cont;
	 }
}
