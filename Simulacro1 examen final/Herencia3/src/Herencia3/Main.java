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
	LecturaDatos.modificarGastos();
	System.out.println("¿Desea modificar los costes? 1 Sí / 0 No");
	int opcion = sc.nextInt();
	sc.nextLine();
	if(opcion == 1){
		System.out.println("Introduzca un valor de Coste Radiador (formato 0.0)");
		double costeRad = sc.nextDouble();
		AulaGasoil.setCosteRad(costeRad);
		System.out.println("introduzca un valor de Metro tubería (formato 0.0)");
		double costeXMetro = sc.nextDouble();
		AulaGasoil.setCosteXMetro(costeXMetro);
		System.out.println("introduzca un valor de Contador Electrico (formato 0.0)");
		double alquilerCont = sc.nextDouble();
		AulaElectricidad.setAlquilerCont(alquilerCont);
		System.out.println("introduzca un valor de Coste Calentadores (formato 0.0)");
		double costeCalentadores = sc.nextDouble();
		AulaElectricidad.setCosteCalentador(costeCalentadores);
		System.out.println("introduzca un valor de TasaElectricidad (formato 0.0)");
		double tasaImpuesto = sc.nextDouble();
		AulaElectricidad.setTasaImpuesto(tasaImpuesto);
	}else if(opcion == 0) {
		System.out.println("No se han modificado los costes.");
	}else {
		System.out.println("Opción no válida.");
	}
	for (Aula aula : LecturaDatos.getAulas()) {
// ALERTA, PRIMERO CALCULA LUEGO PINTA BORREGO :D
		aula.calculaGastos();
		aula.pintaGasto();
	}
	LecturaDatos.modificarGastos();
	sc.close();
	
	}
	
}
