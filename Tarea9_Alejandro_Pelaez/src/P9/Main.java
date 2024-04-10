package P9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


		 Scanner scanner = new Scanner(System.in);
	        Supermercado supermercado = new Supermercado();
	        
	        // Cargar inventario desde archivo
	        supermercado.cargarInventarioDesdeArchivo("Productos.txt");
	        
	        System.out.println("Productos disponibles en este momento: ");
	        try {
				for (Producto p : Supermercado.getInventario()) {
					p.mostrarInformacion();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        int opcion;
	        do {
	            System.out.println("\n-- Menú Principal --");
	            System.out.println("1. Agregar producto");
	            System.out.println("2. Modificar producto");
	            System.out.println("3. Eliminar producto");
	            System.out.println("0. Continuar con las compras");
	            System.out.print("Ingrese su opción: ");
	            opcion = Integer.parseInt(scanner.nextLine());

	            switch (opcion) {
	                case 1:
	                    System.out.println("\n-- Agregar Producto --");
	                    System.out.print("Ingrese el nombre del producto: ");
	                    String nombreProducto = scanner.nextLine();
	                    System.out.print("Ingrese el precio del producto: ");
	                    double precioProducto = Double.parseDouble(scanner.nextLine());
	                    System.out.print("Ingrese el stock del producto: ");
	                    int stockProducto = Integer.parseInt(scanner.nextLine());
	                    System.out.println("Ingrese la categoría del producto: ");
	                    String categoriaProducto = scanner.nextLine();
	                    supermercado.agregarProducto(nombreProducto, precioProducto, stockProducto, categoriaProducto);
	                    System.out.println("Producto agregado correctamente.");
	                    break;
	                case 2:
	                    System.out.println("\n-- Modificar Producto --");
	                    System.out.print("Ingrese el nombre del producto a modificar: ");
	                    String nombreModificar = scanner.nextLine();
	                    Producto productoModificar = supermercado.buscarProducto(nombreModificar);
	                    if (productoModificar != null) {
	                        System.out.print("Ingrese el nuevo precio del producto: ");
	                        double nuevoPrecio = Double.parseDouble(scanner.nextLine());
	                        System.out.print("Ingrese el nuevo stock del producto: ");
	                        int nuevoStock = Integer.parseInt(scanner.nextLine());
	                        supermercado.modificarProducto(nombreModificar, nuevoPrecio, nuevoStock);
	                        System.out.println("Producto modificado correctamente.");
	                    } else {
	                        System.out.println("Error: Producto no encontrado.");
	                    }
	                    break;
	                case 3:
	                    System.out.println("\n-- Eliminar Producto --");
	                    System.out.print("Ingrese el nombre del producto a eliminar: ");
	                    String nombreEliminar = scanner.nextLine();
	                    supermercado.eliminarProducto(nombreEliminar);
	                    System.out.println("Producto eliminado correctamente.");
	                    break;
	                case 0:
	                    // Realizar compras
	                    List<Producto> listaCompra = new ArrayList<>();
	                    System.out.println("\n-- Compras --");
	                    String productoNombre;
	                    int cantidad;
	                    do {
	                        System.out.print("Introduzca el producto (0 para terminar): ");
	                        productoNombre = scanner.nextLine();
	                        if (!productoNombre.equals("0")) {
	                            Producto productoEnStock = supermercado.buscarProducto(productoNombre);
	                            if (productoEnStock != null) {
	                                System.out.print("Introduzca la cantidad: ");
	                                cantidad = Integer.parseInt(scanner.nextLine());
	                                listaCompra.add(new Producto(productoNombre, productoEnStock.getPrecio(), cantidad, productoEnStock.getCategoria()));
	                            } else {
	                                System.out.println("Error: No tenemos ese producto.");
	                            }
	                        }
	                    } while (!productoNombre.equals("0"));

	                 // Procesar compra
	                    double totalCompra = supermercado.procesarCompra(listaCompra);

	                    // Mostrar detalle de la compra
	                    System.out.println("\nTotal de la compra: " + totalCompra);

	                    // Preguntar al usuario si desea atender a otro cliente
	                    System.out.print("\n¿Desea atender a otro cliente? (Si/No): ");
	                    String respuesta = scanner.nextLine();
	                    if (!respuesta.equalsIgnoreCase("Si")) {
	                        
	                    }
	                    break;
	                default:
	                    System.out.println("Opción no válida.");
	            }
	        } while (opcion != 0);

	        // Mostrar productos
	        supermercado.mostrarProductos();

	        // Resto del código para realizar las compras, etc.

	        // Guardar inventario en archivo
	        supermercado.guardarInventarioEnArchivo("Productos.txt");
		
    }
}
