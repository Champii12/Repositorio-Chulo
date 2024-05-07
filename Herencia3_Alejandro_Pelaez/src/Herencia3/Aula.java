package Herencia3;

public abstract class Aula {
	
	private String nombre;
	private int horas;
	
	
	public Aula(String nombre, int horas) {
		this.nombre = nombre;
		this.horas = horas;
	}

//GETTERS
	public String getNombre() {
		return nombre;
	}

	public int getHoras() {
		return horas;
	}
	
// SETTERS
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	
//MÉTODOS
	double calculaGastos() {
		return calculaGastos();
	}
	abstract void pintaGasto();
}
