package Herencia3;

public class AulaElectricidad extends Aula{

	private int numCalentadores;
	private int identificador;	
	private double coste;
	private static double costeCalentador = 8;
	private static double alquilerCont = 40; 
	private static double tasaImpuesto = 0.1;
	private static double mantenimientoHora = 10;
	
	public AulaElectricidad(String nombre, int horas, int numCalentadores) {
		super(nombre, horas);
		this.numCalentadores = numCalentadores;
	}

	
// GETTERS
	public double getCoste() {
		return coste;
	}
	public int getIdentificador() {
		return identificador;
	}
	public int getNumCalentadores() {
		return numCalentadores;
	}
	public static double getCosteCalentador() {
		return costeCalentador;
	}
	
	public static double getAlquilerCont() {
		return alquilerCont;
	}
	public static double getTasaImpuesto() {
		return tasaImpuesto;
	}
	public static double getMantenimientoHora() {
		return mantenimientoHora;
	}


// SETTERS
	public void setCoste(double coste) {
		this.coste = coste;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public void setNumCalentadores(int numCalentadores) {
		this.numCalentadores = numCalentadores;
	}
	public static void setCosteCalentador(double CosteCalentador) {
		AulaElectricidad.costeCalentador = CosteCalentador;
	}
	public static void setAlquilerCont(double alquilerCont) {
		AulaElectricidad.alquilerCont = alquilerCont;
	}
	public static void setMantenimientoHora(double mantenimientoHora) {
		AulaElectricidad.mantenimientoHora = mantenimientoHora;
	}
	public static void setTasaImpuesto(double tasaImpuesto) {
		AulaElectricidad.tasaImpuesto = tasaImpuesto;
	}
	
	
	
// MÉTODOS
	@Override
	void pintaGasto(){
		System.out.println("En el aula " + getNombre() +
				" con combustible Electricidad es de " + coste + " y los extintores " + 25 + "€");
	}
	
	public double calculaGastos() {
		double gasto = 0;
		
		gasto = numCalentadores * ((getHoras() + 5 ) * costeCalentador);
		
		coste = gasto;
		
		return coste;
	}
	
}
