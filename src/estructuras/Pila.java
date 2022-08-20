package estructuras;

import static java.lang.System.exit; 

   
public class Pila {  
    // tope de la pila
    nodoProducto pila=null; 
    
    /**
     * Metodo para agregar un nodo a la pila
     * @param id int
     * @param nombre nombre del producto
     * @param precio precio del producto
     * @param cantidad cantidad
     * @param categoria String
     */
    public void push(int id, String nombre, int precio, int cantidad, String categoria)  { 
        // creando un nodo 
        nodoProducto nodo = new nodoProducto(id, nombre, categoria, precio, cantidad); 
        
        if(pila != null){
            nodo.siguiente = pila;
        }
        //si la pila esta vacia
        pila = nodo;
    }   
    
    /**
     * Si la pila esta vacia, retorna true. De lo contrario, retorna false
     *
     * @return El metodo retorna un valor booleano
     */
    public boolean estaVacio()  { 
        return pila == null; 
        
    } 
 
    /**
     * @return Retorna el tope de la pila
     */
    public nodoProducto peek()  { 
        // revisa si la pila esta vacia
        if (!estaVacio()) { 
            return pila; 
        } 
        else { 
            System.out.println("La pila esta vacia"); 
            return null; 
        } 
    } 
   
    // elimina el tope de la pila
    public void pop() { 
        // revisa si la pila esta vacia
        if (pila == null) { 
            System.out.println("La pila esta vacia"); 
            return; 
        } else{
            nodoProducto temp = pila;
            // establece el tope de la pila a apuntar al siguiente nodo
            pila = pila.siguiente; 
        }
        
    } 
   
    //imprime la pila
    public void display()   { 
        // revisa si la pila esta vacia 
        if (pila == null) { 
            System.out.printf("Pila vacia"); 
            exit(1); 
        } 
        else { 
            nodoProducto temp = pila;
            System.out.println("Nombres de productos en pila:");
            while (temp != null) { 
                  // imprime el nombre del producto
                System.out.print(temp.nombre + "->");
                temp = temp.siguiente; 
            } 
        } 
    }
    
    public int contar(){
        nodoProducto temp = pila;
        int n=0;
        while(temp != null){
            n++;
            System.out.println("Numero de elementos en la pila: "+n);
            temp = temp.siguiente; 
        }
        return n;
    }
}