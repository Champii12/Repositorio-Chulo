package Herencia3;

public class AulaGasoil extends Aula{

	private int numRadiadores;
	private int identificador;
	private double coste;
	private int distanciaM;	
	private static double costeRad = 5;
	private static double costeXMetro = 0;
	
	public AulaGasoil(String nombre, int horas, int distanciaM, int numRadiadores) {
		super(nombre, horas);
		this.numRadiadores = numRadiadores;
		this.distanciaM = distanciaM;
	}


// GETTERS
	public int getNumRadiadores() {
		return numRadiadores;
	}	
	public double getCoste() {
		return coste;
	}
	public int getIdentificador() {
		return identificador;
	}
	public int getdistanciaM() {
		return distanciaM;
	}
	public static double getCosteRad() {
		return costeRad;
	}
	public static double getCosteXMetro() {
		return costeXMetro;
	}


	//SETTERS
	public void setNumRadiadores(int numRadiadores) {
		this.numRadiadores = numRadiadores;
	}
	public void setCoste(double coste) {
		this.coste = coste;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public void setdistanciaM(int distanciaM) {
		this.distanciaM = distanciaM;
	}
	public static void setCosteRad(double costeRad) {
		AulaGasoil.costeRad = costeRad;
	}
	public static void setCosteXMetro(double costeXMetro) {
		AulaGasoil.costeXMetro = costeXMetro;
	}

	
// MÉTODOS
	void pintaGasto(){
		System.out.println("En el aula " + getNombre()+ 
				" con combustible Gasoil es de " + coste + " y los extintores " + 25 + "€");
	}
	
	public double calculaGastos() {
		double gasto = 0;
		
		gasto = numRadiadores * ((getHoras() + 5 ) * costeRad);
		
		coste = gasto;
		
		return coste;
		
	}
	public double cambioDistancia() {
		costeXMetro = distanciaM;
		return costeXMetro;
	}
	
	
}
