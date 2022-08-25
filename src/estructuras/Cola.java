package estructuras;

public class Cola {
    nodoProducto cabecera = null;
    nodoProducto cola = null;
    int cantidadNodos = 0;

    /**
     * It creates a new node, sets the new node's next to null, and then sets the old node's next to
     * the new node
     * 
     * @param id int
     * @param nombre name of the product
     * @param precio price
     * @param cantidad quantity
     * @param categoria String
     */
    public void encolar(int id, String nombre, int precio, int cantidad, String categoria){
        nodoProducto nuevo = new nodoProducto(id, nombre, categoria, precio,cantidad);
        nodoProducto aux = this.cabecera;
        if(this.cabecera == null){
            this.cabecera = nuevo;
            this.cola = nuevo;
        } else{
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
            this.cola = nuevo;
        }
        cantidadNodos++;
    }

    // Checking if the queue is empty.
    public boolean estaVacia(){
        // Checking if the queue is empty.
        return cabecera == null;
    }

    /**
     * The function removes the first element of the queue
     */
    public void desencolar(){
        if(estaVacia()){
            System.out.println("La cola esta vacia");
        } else{
            cabecera = cabecera.getSiguiente();
            cantidadNodos--;
        }
    }

    //copiar datos de un nodo para encolar
    public void copiarDatos(nodoProducto nodo){
        encolar(nodo.getId(), nodo.getNombre(), nodo.getPrecio(), nodo.getCantidad(), nodo.getCategoria());
    }

    //mostrar cola
    public void mostrarCola(){
        System.out.println("Cola :");
        nodoProducto aux = cabecera;
        while(aux != null){
            System.out.print( aux.getNombre() + " <-");
            aux = aux.getSiguiente();
        }
        System.out.println("");
    }


    public int getCantidadNodos() {
        return cantidadNodos;
    }

    public nodoProducto getCabecera() {
        return cabecera;
    }
    
    public nodoProducto getCola() {
        return cola;
    }
}
