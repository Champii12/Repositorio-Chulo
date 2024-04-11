package P9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Supermercado {

    // VARIABLES DE INSTANCIA PARA EL INVENTARIO Y LOS TOTALES VENDIDOS
    private static List<Producto> inventario;
    private double totalVendidoAlimentosBasicosHoy;
    private double totalVendidoPrecocinadosHoy;
    private double totalVendidoBebidasHoy;
    private double totalVendidoHigieneHoy;
    private double totalVendidoLimpiezaHoy;
    private double totalVendidoHoy;
    private double totalVendidoMes;

    // CONSTRUCTOR PARA INICIALIZAR EL INVENTARIO Y LOS TOTALES VENDIDOS
    public Supermercado() {
        inventario = new ArrayList<>();
        totalVendidoAlimentosBasicosHoy = 0;
        totalVendidoPrecocinadosHoy = 0;
        totalVendidoBebidasHoy = 0;
        totalVendidoHigieneHoy = 0;
        totalVendidoLimpiezaHoy = 0;
        totalVendidoHoy = 0;
        totalVendidoMes = 0;
    }
    
    // MÉTODO PARA CARGAR EL INVENTARIO DESDE UN ARCHIVO
    public void cargarInventarioDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombre = partes[0];
                double precio = Double.parseDouble(partes[1]);
                int stock = Integer.parseInt(partes[2]);
                String categoria = partes[3];
                Producto producto = null;
                switch (categoria) {
                    case "Alimento Básico":
                        producto = new Alimentobasico(nombre, precio, stock);
                        break;
                    case "Higiene":
                        producto = new Higiene(nombre, precio, stock);
                        break;
                    case "Limpieza":
                        producto = new Limpieza(nombre, precio, stock);
                        break;
                    case "Precocinado":
                        producto = new Precocinado(nombre, precio, stock);
                        break;
                    case "Bebida":
                        producto = new Bebida(nombre, precio, stock);
                        break;
                    default:
                        System.out.println("Categoría desconocida: " + categoria);
                        break;
                }
                if (producto != null) {
                    inventario.add(producto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MÉTODO PARA GUARDAR EL INVENTARIO EN UN ARCHIVO
    public void guardarInventarioEnArchivo(String nombreArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Producto producto : inventario) {
                bw.write(producto.getNombre() + "," + producto.getPrecio() + "," + producto.getStock() + "," + producto.getCategoria());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MÉTODOS PARA AGREGAR, MODIFICAR Y ELIMINAR PRODUCTOS DEL INVENTARIO
    public void agregarProducto(String nombre, double precio, int stock, String categoria) {
        inventario.add(new Producto(nombre, precio, stock, categoria));
    }

    public void modificarProducto(String nombre, double nuevoPrecio, int nuevoStock) {
        Producto producto = buscarProducto(nombre);
        if (producto != null) {
            producto.setPrecio(nuevoPrecio);
            producto.setStock(nuevoStock);
        } else {
            System.out.println("Error! No tenemos ese producto");
        }
    }

    public void eliminarProducto(String nombre) {
        Producto producto = buscarProducto(nombre);
        if (producto != null) {
            inventario.remove(producto);
        } else {
            System.out.println("Error! No tenemos ese producto");
        }
    }

// MÉTODO PARA BUSCAR UN PRODUCTO EN EL INVENTARIO
    public Producto buscarProducto(String nombre) {
        for (Producto producto : inventario) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

// MÉTODO PARA MOSTRAR LOS PRODUCTOS DISPONIBLES
    public void mostrarProductos() {
        System.out.println("Disponemos de los siguientes productos:");
        for (Producto producto : inventario) {
            producto.mostrarInformacion();
        }
    }

// MÉTODO PARA PROCESAR UNA COMPRA
    public double procesarCompra(List<Producto> listaCompra) {
        double total = 0;
        System.out.println("\n-- Compra --");
        System.out.println("Producto\tPrecio\tSección\tCantidad\tSubtotal");
        System.out.println("--------------------------------------------------");
        for (Producto producto : listaCompra) {
            Producto productoEnStock = buscarProducto(producto.getNombre());
            if (productoEnStock != null && productoEnStock.getStock() >= producto.getStock()) {
                double subtotal = producto.getPrecio() * producto.getStock();
                total += subtotal;
                System.out.println(producto.getNombre() + "       " + producto.getPrecio() +
                        "       " + producto.getCategoria() + "       " + producto.getStock() +
                        "       " + String.format("%.2f", subtotal));
                
                productoEnStock.setStock(productoEnStock.getStock() - producto.getStock());
                
                switch (producto.getCategoria()) {
                    case "Alimento Básico":
                        totalVendidoAlimentosBasicosHoy += subtotal;
                        break;
                    case "Precocinado":
                        totalVendidoPrecocinadosHoy += subtotal;
                        break;
                    case "Bebida":
                        totalVendidoBebidasHoy += subtotal;
                        break;
                    case "Higiene":
                        totalVendidoHigieneHoy += subtotal;
                        break;
                    case "Limpieza":
                        totalVendidoLimpiezaHoy += subtotal;
                        break;
                    default:
                        // Manejar categorías desconocidas si es necesario
                        break;
                }

            } else {
                System.out.println("Error: no tenemos suficiente stock de " + producto.getNombre());
            }
        }
        actualizarTotalVendidoMes(total);
     // Guardar el inventario actualizado en el archivo
        guardarInventarioEnArchivo("Productos.txt");
        System.out.println("--------------------------------------------------");
        System.out.println("TOTAL: " + total);
        return total;
    }

// MÉTODOS PARA ACTUALIZAR Y GUARDAR EL TOTAL VENDIDO EN EL MES
    public void actualizarTotalVendidoMes(double total) {
        this.totalVendidoMes += total;
    }

    public void guardarTotalVendidoMesEnArchivo(String nombreArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            bw.write(Double.toString(totalVendidoMes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

// MÉTODO PARA CARGAR EL TOTAL VENDIDO EN EL MES DESDE UN ARCHIVO
    public void cargarTotalVendidoMesDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea = br.readLine();
            if (linea != null) {
                totalVendidoMes = Double.parseDouble(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MÉTODOS GETTER Y SETTER PARA ACCEDER A LOS TOTALES VENDIDOS Y PARA EL INVENTARIO
    public double getTotalVendidoAlimentosBasicosHoy() {
        return totalVendidoAlimentosBasicosHoy;
    }

    public double getTotalVendidoPrecocinadosHoy() {
        return totalVendidoPrecocinadosHoy;
    }

    public double getTotalVendidoBebidasHoy() {
        return totalVendidoBebidasHoy;
    }

    public double getTotalVendidoHigieneHoy() {
        return totalVendidoHigieneHoy;
    }

    public double getTotalVendidoLimpiezaHoy() {
        return totalVendidoLimpiezaHoy;
    }

    public double getTotalVendidoHoy() {
        return totalVendidoHoy;
    }

    public double getTotalVendidoMes() {
        return totalVendidoMes;
    }
    
    public static List<Producto> getInventario() {
        return inventario;
    }

    public static void setInventario(List<Producto> inventario) {
        Supermercado.inventario = inventario;
    }

    // MÉTODO PARA AGREGAR UN PRODUCTO AL INVENTARIO
    public void agregarProd(Producto prod) {
        inventario.add(prod);
    }
}
