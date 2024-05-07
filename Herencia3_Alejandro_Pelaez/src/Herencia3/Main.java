package Herencia3;

import java.io.*;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
	Scanner sc = new Scanner(System.in);
		
	LecturaDatos.cargarArchivo("estarcalentitoescarillo.txt");

	for (Aula aula : LecturaDatos.getAulas()) {
// ALERTA, PRIMERO CALCULA LUEGO PINTA BORREGO :D
		aula.calculaGastos();
		aula.pintaGasto();
		}
	System.out.println("\n");
	LecturaDatos.modificarGastos();
	System.out.println("\n");
	LecturaDatos.actualizarPrecios();
	System.out.println("\n");
	LecturaDatos.archivoSalida();
	
	
	
	sc.close();
	
	}
	
}