/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra;

import java.util.Scanner;

/**
 *
 * @author CLAUDIA
 */

//código adaptado de: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/

public class Dijkstra {
     
    public static int DistanciaMin(int dist[], Boolean visitado[]) //-> Necesitamos hallar la posición mínima para cada nodo no visitado
    { 
        
        int min = Integer.MAX_VALUE, pos_min = -1, i; //-> Definimos i, seteamos min y pos_min a valores inalcanzables
  
        for ( i = 0; i < dist.length; i++) 
        {
              if (!visitado[i] && dist[i] <= min) //-> Mientras el nodo no esté visitado y su distancia sea menor al valor máximo posible, por lo que casi siempre entrará
              { 
                    min = dist[i]; //El valor mínimo pasará a ser la distancia en i
                    pos_min = i;  //la posición menor pasará a ser i
              } 
        }

        return pos_min; //se devuelve la menor posición 
    } 


    public static int dijkstra(int rutas[][], int origen, int dest) 
    { 
        int tam = rutas.length, i, j; //definimos tamaño, i e j
        int dist[] = new int[tam]; 
        Boolean visitado[] = new Boolean[tam]; 
        
        for (i = 0; i < tam; i++)
        { 
            visitado[i] = false;  //asumimos que ningún nodo ha sido visitado
            dist[i] = Integer.MAX_VALUE; //asumimos la mayor distancia posible por nodo
        } 
        
        dist[origen] = 0; //Debido a que no puede existir una distancia entre origen y origen, se iguala a 0
  
  
        for (i = 0; i < tam - 1; i++) 
        { 
        
            int pos_min = DistanciaMin(dist, visitado); //se buscará la posición mínima del nodo

            visitado[pos_min] = true; // se tomará como visitado
  
            for ( j = 0; j < tam; j++)
            {
                  if (!visitado[j] && rutas[pos_min][j] != 0 && dist[pos_min] != Integer.MAX_VALUE && dist[pos_min] + rutas[pos_min][j] < dist[j])  //Mientras el nodo no esté visitado, su ruta desde la posición menor sea diferente de 0,  su distancia desde la posición menor no sea un número grande y la suma de su distancia y ruta en posición menor sea menor a distancia en j 
                  {
                       dist[j] = dist[pos_min] + rutas[pos_min][j]; //kla distancia pasará a ser la suma de su distancia y ruta en posición mínima
                  }
                   
            }
        } 

        return dist[dest]; //devolvemos la distancia según destino
        
    }

 
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int nodos, origen, destino, i, j; // definimos variables
        
        System.out.println("Ingrese el número de nodos: ");
        nodos = sc.nextInt();
        int rutas[][] = new int[nodos][nodos]; //inicializamos la matriz con el tamaño del nodo
      
        for (i =0; i < rutas.length; i ++) //recorremos la matriz
        {
            for (j=0; j < rutas[i].length; j++) 
            {
                if(i == j)
                {
                    rutas[i][j] = 0; //si la dirección es la misma el resultado es 0
                }
                else
                {
                    System.out.println("Ingrese el peso para (" + i + ","+ j+ "): ");
                    rutas[i][j] = sc.nextInt();
            }
                }
                
        }

        System.out.println("Ingrese el origen : ");
        origen = sc.nextInt();
        System.out.println("Ingrese el destino : ");
        destino = sc.nextInt();
        
        int resultado = dijkstra(rutas, origen, destino);
         
        System.out.println("El camino más corto entre " + origen + " y  " + destino + "  es de costo " + resultado);
         
    }
}

