/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author Milena
 */
/**
 * Es una lista enlazada simple de productos.
 */
public class listaEnlazadaProducto {
     nodoProducto lista = null;

    /**
     * Insertar un nodo al final de la lista enlazada simple
     * la funcion toma un valor y crea un nuevo nodo con ese valor. Si la lista esta vacia, el nuevo
     * nodo se convierte en la cabeza. De lo contrario, la funcion recorre la lista hasta que llega al
     * ultimo nodo. El puntero next del ultimo nodo se pone a punto a apuntar al nuevo nodo
     * @param id
     * @param nombre
     * @param precio
     * @param cantidad
     * @param categoria 
     */
    public void insertarFinal(int id, String nombre, int precio, int cantidad, String categoria) {
        nodoProducto nodo = new nodoProducto(id, nombre, categoria, precio, cantidad);
        
        //si la lista esta vacia
        if(lista == null){
            lista = nodo;
        } else{
            nodoProducto n = lista;
            while(n.getSiguiente() != null){
                n = n.getSiguiente();
            }
            n.setSiguiente(nodo);
        }
        System.out.println("Insertar nodo al final de lista enlazada simple");
    }
    
    /**
     * Mostrar lista enlazada simple
     * mientras el nodo actual no sea nulo, imprime el nodo actual y luego pone el nodo actual a apuntar al siguiente nodo
     */
    public void mostrarLista(){
        if(lista == null){
            System.out.println("Lista vacia");
        } else{
            nodoProducto n = lista;
            while(n != null){
                System.out.println(n.getId() + " " + n.getNombre() + " " + n.getPrecio() + " " + n.getCantidad()+ " " + n.getCategoria() + "sgte: " + n.getSiguiente());
                n = n.getSiguiente();
            }
        }
    }

    /**
     * la función tomara un id como parametro y buscara el nodo con ese id. Si el nodo es encontrado, la función
     * removera el nodo de la lista
     * @param id el id del nodo ha ser eliminado
     */
    public void eliminarNodo(int id) {

        //si la lista esta vacia
        if(lista == null){
            System.out.println("La lista esta vacia");
        } else{
            nodoProducto nodo = lista;
            nodoProducto anterior = null;
        
            while(nodo != null){
                if(nodo.getId() == id){
                    if(anterior == null){
                        lista = nodo.getSiguiente();
                    } else{
                        anterior.setSiguiente(nodo.getSiguiente());
                    }
                    System.out.println("El nodo con id " + id + " ha sido eliminado");
                    return;
                }
            anterior = nodo;
            nodo = nodo.getSiguiente();
            }
            System.out.println("El nodo con id " + id + " no ha sido encontrado");
        }
    }

    /**
     * la función tomara un id como parametro y buscara el nodo con ese id. Si el nodo es encontrado, la función
     * modificara el nodo de la lista
     * @param id el id del nodo ha ser modificado
     * @param nombre el nombre del nodo ha ser modificado
     * @param precio el precio del nodo ha ser modificado
     * @param cantidad la cantidad del nodo ha ser modificado
     * @param categoria la categoria del nodo ha ser modificado
     */
    public void modificarNodo(int id, String nombre, int precio, int cantidad, String categoria) {
        //si la lista esta vacia
        if(lista == null){
            System.out.println("La lista esta vacia");
        } else{
            nodoProducto nodo = lista;
            while(nodo != null){
                if(nodo.getId() == id){
                    nodo.setNombre(nombre);
                    nodo.setPrecio(precio);
                    nodo.setCantidad(cantidad);
                    nodo.setCategoria(categoria);
                    System.out.println("El nodo con id " + id + " ha sido modificado");
                    return;
                }
                nodo = nodo.getSiguiente();
            }
            System.out.println("El nodo con id " + id + " no ha sido encontrado");
        }
    }
    
    //metodo para contar la cantidad de nodos de la lista
    public int contarNodos(){
        int contador = 0;
        if(lista == null){
            System.out.println("La lista esta vacia");
        } else{
            nodoProducto n = lista;
            while(n != null){
                contador++;
                n = n.siguiente;
            }
        }
        System.out.println("Conteo de nodos en lista enlazada simple: "+ contador);
        return contador;
    }

    //metodo para buscar un nodo en la lista por su posicion
    public nodoProducto buscarNodoPos(int posicion){
        if(lista == null){
            System.out.println("La lista esta vacia");
        } else{
            nodoProducto n = lista;
            int contador = 0;
            while(n != null){
                if(contador == posicion){
                    System.out.println("Busqueda de nodo por posicion en lista enlazada simple");
                    
                    return n;
                }
                if(n.getSiguiente() == null){
                    System.out.println(n.id + " " + n.nombre + " " + n.precio + " " + n.cantidad+ " " + n.categoria + "  sgte: null" );
                }else{
                    System.out.println(n.id + " " + n.nombre + " " + n.precio + " " + n.cantidad+ " " + n.categoria + "  sgte: " + n.getSiguiente().getNombre());
                }
                
                contador++;
                n = n.siguiente;
                
            }
            System.out.println("El nodo con posicion " + posicion + " no ha sido encontrado");
        }
        return null;
    }

    //metodo para buscar un nodo en la lista por su id
    public nodoProducto buscarNodoId(int id){
        if(lista == null){
            System.out.println("La lista esta vacia");
        } else{
            nodoProducto n = lista;
            while(n != null){
                if(n.id == id){
                    System.out.println("Busqueda de nodo por id en lista enlazada simple");
                    return n;
                }
                if(n.getSiguiente() == null){
                    System.out.println(n.id + " " + n.nombre + " " + n.precio + " " + n.cantidad+ " " + n.categoria + "  sgte: null" );
                }else{
                    System.out.println(n.id + " " + n.nombre + " " + n.precio + " " + n.cantidad+ " " + n.categoria + "  sgte: " + n.getSiguiente().getNombre());
                }
                n = n.siguiente;
            }
            System.out.println("El nodo con id " + id + " no ha sido encontrado");
        }
        return null;
    }

    //funcion que retorna el primer nodo de la lista
    public nodoProducto getLista() {
        return lista;
    }
}

