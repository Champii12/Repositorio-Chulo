package P9;

public class Producto {
	
	private String nombre;
	private double precio;
	private int stock;
	private String categoria;
	
	public Producto(String nombre, double precio, int stock, String categoria) {
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.categoria = categoria;
	}
	
	public void mostrarInformacion() {
		System.out.println(nombre + " - Precio: " + precio + " - Stock: " + stock + " unidades");
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	
}
