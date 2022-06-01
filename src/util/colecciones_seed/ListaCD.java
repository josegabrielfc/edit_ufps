/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.colecciones_seed;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author docenteauditorio
 */
public class ListaCD<T> implements Iterable<T> {

    private NodoD<T> cabecera;
    private int size;

    public ListaCD() {

        this.cabecera = new NodoD();
        this.cabecera.setInfo(null);
        this.cabecera.setSig(cabecera);
        this.cabecera.setAnt(cabecera);
        this.size = 0;
    }

    
    public int  buscar(ListaCD<T> l)
    {
        return 0;
    }
    
    
    public int reemplazar(ListaCD<T> origen, ListaCD<T> nueva)
    {
        return 0;
    }
    
    public void insertarInicio(T info) {
        NodoD<T> nuevo = new NodoD(info, this.cabecera.getSig(), this.cabecera);
        this.cabecera.setSig(nuevo);
        nuevo.getSig().setAnt(nuevo);
        this.size++;

    }

    public void insertarFin(T info) {
        NodoD<T> nuevo = new NodoD(info, this.cabecera, this.cabecera.getAnt());
        this.cabecera.setAnt(nuevo);
        nuevo.getAnt().setSig(nuevo);
        this.size++;

    }

    private NodoD<T> getPos(int i) throws Exception {
        if (this.esVacia() || i < 0 || i >= this.size) {
            throw new Exception("Índice ilegal");
        }

        NodoD<T> aux = this.cabecera.getSig();
        while (i > 0) {
            aux = aux.getSig();
            i--;
        }
        return aux;
    }

    public T get(int i) {

        try {
            return this.getPos(i).getInfo();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public void set(int i, T infoNuevo) {
        try {
            this.getPos(i).setInfo(infoNuevo);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public String toString() {
        if (this.esVacia()) {
            return "Lista Vacía";
        }
        String msg = "Cab->";
        for (NodoD<T> x = this.cabecera.getSig(); x != this.cabecera; x = x.getSig()) {
            msg += x.getInfo() + "<-> ";
        }
        return msg + "<-> Cab";
    }

    public String toString_Inverso() {
        if (this.esVacia()) {
            return "Lista Vacía";
        }
        String msg = "Cab->";
        for (NodoD<T> x = this.cabecera.getAnt(); x != this.cabecera; x = x.getAnt()) {
            msg += x.getInfo() + "<-> ";
        }
        return msg + "<-> Cab";
    }

    public boolean esVacia() {
        return this.cabecera == this.cabecera.getSig();
        //this.size==0
        //this.cabecera==this.cabecera.getAnt();
    }

    public int getSize() {
        return size;
    }

    /**
     * Une dos listas en la original, usando teoría de conjuntos. La lista l2 se
     * borra. ejemplo: l1=<3,4,5,6> y l2=<3,4>
     * l1.unionLista(l2); l1=<3,4,5,6>
     * l2=<>
     *
     * @param l2
     */
    public void unionLista(ListaCD<T> l2) {

    }

    public T borrar(int i) {

        try {
            NodoD<T> borrar = this.getPos(i);
            borrar.getAnt().setSig(borrar.getSig());
            borrar.getSig().setAnt(borrar.getAnt());
            borrar.setSig(null);
            borrar.setAnt(null);
            this.size--;
            return borrar.getInfo();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Concatena una lista en la original. La lista concatenada SE DEBE BORRAR.
     * NO SE PERMITE CREAR NODOS, LOS NODOS DEBEN SER REUTILIZADOS
     *
     * @param l2
     */
    public void concat(ListaCD<T> l2) {
        if (this.esVacia() || l2.esVacia()) {
            return;
        }
        if (this.esVacia() && !l2.esVacia()) {
            this.cabecera.setAnt(l2.cabecera.getAnt());
            this.cabecera.setSig(l2.cabecera.getSig());
            l2.cabecera.getSig().setAnt(this.cabecera);
            l2.cabecera.getAnt().setSig(this.cabecera);
            this.size = l2.size;
            l2.size = 0;
            this.desUnir(l2.cabecera);
            return;
        }
        if (!this.esVacia() && !l2.esVacia()) {
            NodoD<T> l2Inicio = l2.cabecera.getSig();
            NodoD<T> l2Fin = l2.cabecera.getSig();
            this.cabecera.getAnt().setSig(l2Inicio);
            l2Inicio.setAnt(this.cabecera.getAnt());
            this.cabecera.setAnt(l2Fin);
            l2Fin.setSig(this.cabecera);
            this.size += l2.size;
            l2.size = 0;
            this.desUnir(l2.cabecera);
        }
    }

    /**
     * Concatena la lista l2 en la original a partir de la posición indicada por
     * i (después de i) l2 al final del proceso queda vacía
     *
     * @param i posición donde va a insertar l2
     * @param l2 lista a ser concatenadas
     */
    public void concat(int i, ListaCD<T> l2) {

    }

    private void desUnir(NodoD<T> nodo) {
        nodo.setAnt(nodo);
        nodo.setSig(nodo);
    }

    public void borrarTodo() {
        this.cabecera.setInfo(null);
        this.cabecera.setSig(cabecera);
        this.cabecera.setAnt(cabecera);
        this.size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorLCD(this.cabecera);
    }
}
