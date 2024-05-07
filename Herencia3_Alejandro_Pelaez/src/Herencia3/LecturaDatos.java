package Herencia3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LecturaDatos {

	static Scanner sc = new Scanner(System.in);
	
	private static List<Aula> aulas = new ArrayList<>();
	private static double gastoG = 0.0;
	private static double gastoE = 0.0;
	private static double gastoT = 0.0;
	

// GETTERS
	
	public static List<Aula> getAulas() {
		return aulas;
	}
	public static double getGastoG() {
		return gastoG;
	}
	public static double getGastoE() {
		return gastoE;
	}
	public static double getGastoT() {
		return gastoT;
	}

	
// SETTERS

	public static void setAulas(List<Aula> aulas) {
		LecturaDatos.aulas = aulas;
	}
	public static void setGastoG(double gastoG) {
		LecturaDatos.gastoG = gastoG;
	}
	public static void setGastoE(double gastoE) {
		LecturaDatos.gastoE = gastoE;
	}
	public static void setGastoT(double gastoT) {
		LecturaDatos.gastoT = gastoT;
	}
	
	
	public static void cargarArchivo(String archivo) throws IOException {
		
		try(BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while((linea = br.readLine()) != null) {
				String[] datosAula = linea.split(";");
				if(datosAula[2].equalsIgnoreCase("1")) {
					String nombre = datosAula[0].trim();
					int horas = Integer.parseInt(datosAula[1]);
					int numCalentadores =  Integer.parseInt(datosAula[4]);
					AulaElectricidad aE = new AulaElectricidad(nombre, horas, numCalentadores);
					aulas.add(aE);
				}else if(datosAula[2].equalsIgnoreCase("0")) {
					String nombre = datosAula[0].trim();
					int horas = Integer.parseInt(datosAula[1]);
					int distanciaM = Integer.parseInt(datosAula[3]);
					int numRadiadores =  Integer.parseInt(datosAula[5]);
					AulaGasoil aG= new AulaGasoil(nombre, horas, distanciaM, numRadiadores);
					aulas.add(aG);
				}
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
	
	public static void modificarGastos() {
		double horasG = 0.0;
		double mantenimientoG = 0.0;
		double gastoExt = 0.0;
		for (Aula aula : getAulas()) {
			if (aula instanceof AulaGasoil) {
// Si el aula es de tipo AulaGasoil, entonces puedes llamar a los métodos específicos de esa clase
			gastoG += ((AulaGasoil) aula).calculaGastos();
			horasG = AulaGasoil.getCosteXMetro();
			gastoG = gastoG + (horasG * 0.25);
// 10 PORQUE SON 10 AULAS
			gastoExt = 25 * 9;
			gastoG = gastoG + gastoExt;
// NUM RADIADORES * (2 * SEMANA(4 TIENE UN MES);
			
		        mantenimientoG = ((AulaGasoil) aula).getNumRadiadores() * (2 * 4);
		        gastoG = gastoG + mantenimientoG;
			}

			gastoG = gastoG * 1.01;
		}
		
		
		double horasE = 0.0;
		double mantenimientoE = 0.0;
		double gastoExt2 = 0.0;
		for (Aula aula : getAulas()) {
			if (aula instanceof AulaElectricidad) {
// Si el aula es de tipo AulaGasoil, entonces puedes llamar a los métodos específicos de esa clase
			gastoE += ((AulaElectricidad)aula).calculaGastos();
			horasE = ((AulaElectricidad)aula).getHoras();
			gastoE = gastoE + horasE;
// 5 EXTINTORES PORQUE SON 5 AULAS
			gastoExt2 = 25 * 5;
			gastoE = gastoE + gastoExt2;
// NUM RADIADORES * 10(POR LO MENOS SI NO QUIERES MODIFICAR);
			
		        mantenimientoE = ((AulaElectricidad)aula).getNumCalentadores() * AulaElectricidad.getMantenimientoHora();
		        gastoE = gastoE + mantenimientoE;
			}

			gastoE = (gastoE + AulaElectricidad.getAlquilerCont()) - ((gastoE + AulaElectricidad.getAlquilerCont()) * AulaElectricidad.getTasaImpuesto());
		}
		
		gastoT = gastoG + gastoE;
		System.out.println("Gasto mensual: " + gastoT);
		System.out.println("Gasto de Gasoil: " + gastoG);
		System.out.println("Gasto de Electricidad: " + gastoE);
	}
	
	public static void actualizarPrecios() throws IOException {
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
	        for (Aula aula : LecturaDatos.getAulas()) {
	        	
	        	aula.pintaGasto();
	        }
	        LecturaDatos.modificarGastos();
	        
	    } else if(opcion == 0) {
	        System.out.println("No se han modificado los costes.");
	    } else {
	        System.out.println("Opción no válida.");
	    }
	}

	
	public static void archivoSalida() throws IOException {
	    try (BufferedWriter brw = new BufferedWriter(new FileWriter("Calculoestarcalentitoescarillo.txt"))) {
	        brw.write("\nLos datos utilizados en esta ejecucion han sido:\n");
	        brw.newLine();
	        brw.write("CosteRadiador-> " + AulaGasoil.getCosteRad() + ", Coste Metro por tuberia-> " + AulaGasoil.getCosteXMetro() +
	                ", Coste Alquiler Contador-> " + AulaElectricidad.getAlquilerCont() + ", Coste Calentadores-> " +
	                AulaElectricidad.getCosteCalentador() + ", Tasa Electricidad-> " + AulaElectricidad.getTasaImpuesto());
	        brw.newLine();
	        brw.write("Gasto total  " + getGastoT());
	        brw.newLine();
	        brw.write("Gasto total Electricidad " + getGastoE());
	        brw.newLine();
	        brw.write("Gasto total Gasoil " + getGastoG());
	    }
	    System.out.println("Los datos utilizados en esta ejecucion han sido:\n");
	    System.out.println("CosteRadiador-> " + AulaGasoil.getCosteRad() + ", Coste Metro por tuberia-> " + AulaGasoil.getCosteXMetro() +
	                ", Coste Alquiler Contador-> " + AulaElectricidad.getAlquilerCont() + ", Coste Calentadores-> " +
	                AulaElectricidad.getCosteCalentador() + ", Tasa Electricidad-> " + AulaElectricidad.getTasaImpuesto());
	    System.out.println("Gasto total " + getGastoT() + 
	    		"\nGasto total Gasoil " + getGastoG()+ 
	    		"\nGasto total Electricidad " + getGastoE());
	}

	
}
