/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.ConexionDB;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTextField;


/**
 *
 * @author hp
 */
public class Producto {

    // Completa el codigo
    private String nombre;
    private String id;
    private double temperatura;
    private double valorBase;

    public Producto(String nombre, String id, double temperatura, double valorBase){
        this.nombre=nombre;
        this.id=id;
        this.temperatura=temperatura;
        this.valorBase=valorBase;
    }

    public Producto(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;   
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }
    
    public double calcularCosto(double temperatura){
        if (this.temperatura>15){
            return 2500;
        }
        else{
            return 1000;
        }
    }
    
    public double setCosto(double costo){
        return costo;
    } 
    
    // No borres lo de abajo
    @Override
    public String toString() {
        return this.getClass().getName() + "{" + "nombre=" + nombre + ", id=" + id + ", temperatura=" + temperatura + ", valorBase=" + valorBase + '}';
    }
    
    public double ParseDoubleEspecial(String strInput) {
        if (strInput != null && strInput.length() > 0) {
            try {
               return Double.parseDouble(strInput);
            } catch(NumberFormatException e) {
               return -500;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        else return -500;
     }
    
    public List<Producto> listaDeProductos(String s){
        List<Producto> listaDeProductos = new ArrayList<>();
        ConexionDB con = new ConexionDB();
        String sql = "SELECT * FROM tablaProductos" + s + ";";
        try {
            ResultSet rs = con.consultarBD(sql);
            Producto p;
            while (rs.next()) {
                p = new Producto();
                p.setId(String.valueOf(rs.getInt("id")));
                p.setNombre(rs.getString("Nombre"));
                p.setTemperatura(rs.getDouble("Temperatura"));
                p.setValorBase(rs.getDouble("ValorBase"));
                p.setCosto(rs.getDouble("Costo"));
                listaDeProductos.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Error: Error al mostrar la tabla:" + ex.getMessage());
        } finally {
            con.cerrarConexion();
        }
        return listaDeProductos;
    }
}