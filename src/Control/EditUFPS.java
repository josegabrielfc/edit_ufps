/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import util.colecciones_seed.ListaCD;

/**
 *
 * @author docenteauditorio
 */
public class EditUFPS {

    private ListaCD<Character> documento = new ListaCD();

    public EditUFPS() {
    }

    public EditUFPS(String texto) {
        this.documento = this.crearLista(texto);
    }

    private ListaCD<Character> crearLista(String texto) {
        char datos[] = texto.toCharArray();
        ListaCD<Character> l = new ListaCD();
        for (char x : datos) {
            l.insertarFin(x);
        }
        return l;
    }

    public int buscar(String palabra) {
        
        return this.documento.buscar(this.crearLista(palabra));
    }

    public int eliminar(String palabra) {
        return 0;
    }

    public int reemplazar(String palabra, String palabraNueva) {
        
        
        return this.documento.reemplazar(this.crearLista(palabra), this.crearLista(palabraNueva));
        
    }
}
