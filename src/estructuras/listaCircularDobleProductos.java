/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author Milena
 */
public class listaCircularDobleProductos {
    nodoProducto listaCircularDoble=null;

    /**
     * Toma un nodo y lo agrega al final de la lista circular doblemente enlazada
     * @param nuevo  es el nuevo nodo que se agregara a la lista
     */
    public void insertarFinal(nodoProducto nuevo){
        if(listaCircularDoble == null){
            listaCircularDoble = nuevo;
            listaCircularDoble.siguiente = listaCircularDoble;
            listaCircularDoble.anterior = listaCircularDoble;
        } else{
            nodoProducto aux = listaCircularDoble;
            while(aux.siguiente != listaCircularDoble){
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
            nuevo.anterior = aux;
            nuevo.siguiente = listaCircularDoble;
            listaCircularDoble.anterior = nuevo;
        }
    }

    /**
     * Crea un nuevo nodo con los mismos datos que el nodo pasado como parametro, y luego lo inserta
     * al final de la lista circular doblemente enlazada
     * @param actual es el nodo que se esta copiando
     */
    public void copiarDatos(nodoProducto actual){
        nodoProducto nuevo = new nodoProducto(actual.getId(), actual.getNombre(), actual.getCategoria(), actual.getPrecio(), actual.getCantidad());
        insertarFinal(nuevo);
    }

    //metodo para mostrar la lista circular doblemente enlazada
    public void mostrarLista(){
        if(listaCircularDoble == null){
            System.out.println("La lista esta vacia");
        } else{
            nodoProducto aux = listaCircularDoble;
            do{
                System.out.println("Id: " + aux.getId() + " Nombre: " + aux.getNombre() + " Categoria: " + aux.getCategoria() + " Precio: " + aux.getPrecio() + " Cantidad: " + aux.getCantidad()+ " Siguiente: " + aux.getSiguiente().getNombre() + " Anterior: " + aux.getAnterior().getNombre());
                aux = aux.siguiente;
            } while(aux != listaCircularDoble);
        }
    }

    /**
     * Retorna el nodo con el id dado, o null si no existe
     * @param id el id del producto
     * @return el nodo con el id dado, o null si no existe
     */
    public nodoProducto buscarNodo(int id){
        nodoProducto aux = listaCircularDoble;
        do{
            if(aux.getId() == id){
                System.out.println("Id: " + aux.getId() + " Nombre: " + aux.getNombre() + " Categoria: " + aux.getCategoria() + " Precio: " + aux.getPrecio() + " Cantidad: " + aux.getCantidad()+ " Siguiente: " + aux.getSiguiente().getNombre() + " Anterior: " + aux.getAnterior().getNombre());
                return aux;
            }
            aux = aux.siguiente;
        } while(aux != listaCircularDoble);
        System.out.println("No se encontro el nodo con el id: " + id);
        return null;
    }

    // retorna el nodo cabeza de la lista circular doblemente enlazada
    public nodoProducto getListaCircularDoble() {
        return listaCircularDoble;
    }
    
    //mover al nodo anterior
    public nodoProducto moverAnterior(nodoProducto actual){
        return actual.anterior;
    }

    //mover al nodo siguiente
    public nodoProducto moverSiguiente(nodoProducto actual){
        return actual.siguiente;
    }
}
