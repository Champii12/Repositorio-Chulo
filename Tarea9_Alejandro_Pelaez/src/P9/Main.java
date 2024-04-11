package P9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

     // SE CREA UN OBJETO SCANNER PARA LEER LA ENTRADA DEL USUARIO DESDE LA CONSOLA.
    	Scanner scanner = new Scanner(System.in);
     // SE CREA UN OBJETO SUPERMERCADO PARA GESTIONAR LAS OPERACIONES DEL SUPERMERCADO.
        Supermercado supermercado = new Supermercado();

     // SE CARGA EL INVENTARIO DESDE UN ARCHIVO DE TEXTO.
        supermercado.cargarInventarioDesdeArchivo("Productos.txt");
     // SE CARGAN LAS GANANCIAS DEL MES DESDE UN ARCHIVO DE TEXTO.
        supermercado.cargarTotalVendidoMesDesdeArchivo("Ganancias.txt");

     // VARIABLES PARA MANTENER UN SEGUIMIENTO DE LAS VENTAS POR CATEGORÍA Y LOS TOTALES VENDIDOS HOY Y ESTE MES.
        double totalVendidoAlimentosBasicosHoy = 0;
        double totalVendidoPrecocinadosHoy = 0;
        double totalVendidoBebidasHoy = 0;
        double totalVendidoHigieneHoy = 0;
        double totalVendidoLimpiezaHoy = 0;
        double totalVendidoHoy = 0;
     // SE OBTIENE EL TOTAL VENDIDO DEL MES DESDE EL OBJETO SUPERMERCADO.
        double totalVendidoMes = supermercado.getTotalVendidoMes(); 

     // VARIABLE PARA ALMACENAR LA OPCIÓN ELEGIDA POR EL USUARIO.
        int opcion = 0;
     // VARIABLE PARA CONTROLAR SI SE DEBE ATENDER A OTRO CLIENTE O NO.
        boolean atenderOtroCliente = true; 
     // BUCLE PARA ATENDER A MÚLTIPLES CLIENTES.
        while (atenderOtroCliente) {
     // SE MUESTRA UN MENÚ PARA QUE EL USUARIO ELIJA UNA OPCIÓN.
        	System.out.println("Productos disponibles en este momento: ");
            supermercado.mostrarProductos();
            System.out.println("\n-- Menú Principal --");
            System.out.println("1. Agregar producto");
            System.out.println("2. Modificar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("0. Continuar con las compras");
            System.out.print("Ingrese su opción: ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion < 0 || opcion > 3) {
                    System.out.println("Opción no válida. Por favor, ingrese un número del 0 al 3.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número del 0 al 3.");
                scanner.next();
                continue;
            }

            switch (opcion) {
    // SI EL USUARIO ELIGE AGREGAR PRODUCTO, SE SOLICITAN LOS DETALLES DEL PRODUCTO Y SE AGREGA AL INVENTARIO.
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
                System.out.println("Producto agregado correctamente.\n");
                break;
    // SI EL USUARIO ELIGE MODIFICAR PRODUCTO, SE SOLICITA EL NOMBRE DEL PRODUCTO A MODIFICAR, 
	// SE BUSCA EN EL INVENTARIO Y SE MODIFICAN SUS DETALLES SI SE ENCUENTRA.
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
                    System.out.println("Producto modificado correctamente.\n");
                } else {
                    System.out.println("Error: Producto no encontrado.");
                }
                break;
    // SI EL USUARIO ELIGE ELIMINAR PRODUCTO, SE SOLICITA EL 
    // NOMBRE DEL PRODUCTO A ELIMINAR Y SE ELIMINA DEL INVENTARIO.
            case 3:
                System.out.println("\n-- Eliminar Producto --");
                System.out.print("Ingrese el nombre del producto a eliminar: ");
                String nombreEliminar = scanner.nextLine();
                supermercado.eliminarProducto(nombreEliminar);
                System.out.println("Producto eliminado correctamente.\n");
                break;
    // SI EL USUARIO ELIGE CONTINUAR CON LAS COMPRAS, 
    // SE PERMITE AL USUARIO HACER UNA COMPRA.
                case 0:
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

                    double totalCompra = supermercado.procesarCompra(listaCompra);

                    totalVendidoHoy += totalCompra;
                    totalVendidoMes += totalCompra;
                    for (Producto producto : listaCompra) {
                        switch (producto.getCategoria()) {
                            case "Alimento Básico":
                                totalVendidoAlimentosBasicosHoy += producto.getPrecio() * producto.getStock();
                                break;
                            case "Precocinado":
                                totalVendidoPrecocinadosHoy += producto.getPrecio() * producto.getStock();
                                break;
                            case "Bebida":
                                totalVendidoBebidasHoy += producto.getPrecio() * producto.getStock();
                                break;
                            case "Higiene":
                                totalVendidoHigieneHoy += producto.getPrecio() * producto.getStock();
                                break;
                            case "Limpieza":
                                totalVendidoLimpiezaHoy += producto.getPrecio() * producto.getStock();
                                break;
                            default:
                            	System.out.println("Opción no válida.");
                                break;
                        }
                    }

                    
                    System.out.println("\nTotal de la compra: " + totalCompra);

                    System.out.print("\n¿Desea atender a otro cliente? (Si/No): ");
                    String respuesta = scanner.nextLine();
                    if (!respuesta.equalsIgnoreCase("Si")) {
                        atenderOtroCliente = false;
                    }
                    break;
    // SI EL USUARIO ELIGE UNA OPCIÓN INVÁLIDA, SE MUESTRA UN MENSAJE DE ERROR.
                default:
                    System.out.println("Opción no válida.");
            }
        }

    // SE MUESTRAN LOS TOTALES VENDIDOS HOY Y ESTE MES.
        System.out.println("\nTOTALES VENDIDOS HOY:");
        System.out.println("ALIMENTOS BÁSICOS: " + totalVendidoAlimentosBasicosHoy);
        System.out.println("PRECOCINADOS: " + totalVendidoPrecocinadosHoy);
        System.out.println("BEBIDAS: " + totalVendidoBebidasHoy);
        System.out.println("HIGIENE: " + totalVendidoHigieneHoy);
        System.out.println("LIMPIEZA: " + totalVendidoLimpiezaHoy);
        System.out.println("TOTAL VENDIDO HOY: " + totalVendidoHoy);
        System.out.println("TOTAL VENDIDO ESTE MES: " + totalVendidoMes);

    // SE GUARDAN LOS DATOS DEL INVENTARIO Y LAS GANANCIAS EN ARCHIVOS.
        supermercado.guardarInventarioEnArchivo("Productos.txt");
        supermercado.guardarTotalVendidoMesEnArchivo("Ganancias.txt");
    // SE CIERRA EL OBJETO SCANNER.
        scanner.close();
    }
}
