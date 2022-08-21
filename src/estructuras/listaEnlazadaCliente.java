/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author Milena
 */
public class listaEnlazadaCliente {
    nodoCliente listaClientes = null;


    /**
     * Insertar un nodo al final de la lista enlazada simple
     * la funcion toma un valor y crea un nuevo nodo con ese valor. Si la lista esta vacia, el nuevo
     * nodo se convierte en la cabeza. De lo contrario, la funcion recorre la lista hasta que llega al
     * ultimo nodo. El puntero next del ultimo nodo se pone a punto a apuntar al nuevo nodo
     * @param id
     * @param nombre
     */
    public void insertarFinal(int id, String nombre, String apellido, int pagoTotal) {
        nodoCliente nodo = new nodoCliente(id, nombre, apellido, pagoTotal);
        
        //si la listaClientes esta vacia
        if(listaClientes == null){
            listaClientes = nodo;
        } else{
            nodoCliente n = listaClientes;
            while(n.getSiguiente() != null){
                n = n.getSiguiente();
            }
            n.setSiguiente(nodo);
        }
        System.out.println("Insertar nodo al final de lista enlazada simple");
    }
    
    /**
     * Mostrar listaClientes enlazada simple
 mientras el nodo actual no sea nulo, imprime el nodo actual y luego pone el nodo actual a apuntar al siguiente nodo
     */
    public void mostrarLista(){
        if(listaClientes == null){
            System.out.println("Lista vacia");
        } else{
            nodoCliente n = listaClientes;
            while(n != null){
                System.out.println(n.getId() + " " + n.getNombre() + " " + n.getApellido()+ " " + n.getPagoTotal()+ "sgte: " + n.getSiguiente().getNombre());
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

        //si la listaClientes esta vacia
        if(listaClientes == null){
            System.out.println("La lista esta vacia");
        } else{
            nodoCliente nodo = listaClientes;
            nodoCliente anterior = null;
        
            while(nodo != null){
                if(nodo.getId() == id){
                    if(anterior == null){
                        listaClientes = nodo.getSiguiente();
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
    public void modificarNodo(int id, String nombre, String apellido, int pagoTotal) {
        //si la listaClientes esta vacia
        if(listaClientes == null){
            System.out.println("La lista esta vacia");
        } else{
            nodoCliente nodo = listaClientes;
            while(nodo != null){
                if(nodo.getId() == id){
                    nodo.setNombre(nombre);
                    nodo.setApellido(apellido);
                    nodo.setPagoTotal(pagoTotal);
                    System.out.println("El nodo con id " + id + " ha sido modificado");
                    return;
                }
                nodo = nodo.getSiguiente();
            }
            System.out.println("El nodo con id " + id + " no ha sido encontrado");
        }
    }
    
    //metodo para contar la cantidad de nodos de la listaClientes
    public int contarNodos(){
        int contador = 0;
        if(listaClientes == null){
            System.out.println("La lista esta vacia");
        } else{
            nodoCliente n = listaClientes;
            while(n != null){
                contador++;
                n = n.siguiente;
            }
        }
        System.out.println("Conteo de nodos en lista enlazada simple: "+ contador);
        return contador;
    }

    //metodo para buscar un nodo en la listaClientes por su posicion
    public nodoCliente buscarNodo(int posicion){
        if(listaClientes == null){
            System.out.println("La lista esta vacia");
        } else{
            nodoCliente n = listaClientes;
            int contador = 0;
            while(n != null){
                if(contador == posicion){
                    System.out.println("Busqueda de nodo por posicion en lista enlazada simple");
                    
                    return n;
                }
                System.out.println(n.getId() + " " + n.getNombre() + " " + n.getApellido()+ " " + n.getPagoTotal()+ "sgte: " + n.getSiguiente().getNombre());
                contador++;
                n = n.siguiente;
                
            }
            System.out.println("El nodo con posicion " + posicion + " no ha sido encontrado");
        }
        return null;
    }

    //metodo para buscar un nodo en la listaClientes por su id
    public nodoCliente buscarNodoId(int id){
        if(listaClientes == null){
            System.out.println("La lista esta vacia");
        } else{
            nodoCliente n = listaClientes;
            while(n != null){
                if(n.getId() == id){
                    System.out.println("Busqueda de nodo por id en lista enlazada simple");
                    return n;
                }
                n = n.siguiente;
            }
            System.out.println("El nodo con id " + id + " no ha sido encontrado");
        }
        return null;
    }
}

