/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author edson
 */ 

    public class UsuarioLogado {
    private static String nome;
    private static int id;
    private static String tipo;
 
    private UsuarioLogado() {}
 

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        UsuarioLogado.nome = nome;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UsuarioLogado.id = id;
    }

    public static String getTipo() {
        return tipo;
    }

    public static void setTipo(String tipo) {
        UsuarioLogado.tipo = tipo;
    }
}



