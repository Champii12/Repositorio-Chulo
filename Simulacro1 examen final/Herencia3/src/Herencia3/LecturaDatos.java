package Herencia3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LecturaDatos {

	private static List<Aula> aulas = new ArrayList<>();
	
	
	
// GETTERS
	
	public static List<Aula> getAulas() {
		return aulas;
	}

// SETTERS
	public static void setAulas(List<Aula> aulas) {
		LecturaDatos.aulas = aulas;
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
		double gastoG = 0.0;
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
		
		double gastoE = 0.0;
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
		double gastoT = 0.0;
		gastoT = gastoG + gastoE;
		System.out.println("Gasto mensual: " + gastoT);
		System.out.println("Gasto de Gasoil: " + gastoG);
		System.out.println("Gasto de Electricidad: " + gastoE);
	}
	
}
