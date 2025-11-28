package com.example.demo2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Escoba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String modelo;
    private String color;
    private String tipoDeMadera;
    private int longitudCm; // Longitud del mango en centímetros
    private String usoRecomendado; // Ejemplo: "Doméstico", "Industrial", "Jardinería"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fabricante_id")
    @JsonBackReference
    private Fabricante fabricante;

    // --- Constructor Vacío ---
    public Escoba() {
    }

    // --- Getters y Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoDeMadera() {
        return tipoDeMadera;
    }

    public void setTipoDeMadera(String tipoDeMadera) {
        this.tipoDeMadera = tipoDeMadera;
    }

    public int getLongitudCm() {
        return longitudCm;
    }

    public void setLongitudCm(int longitudCm) {
        this.longitudCm = longitudCm;
    }

    public String getUsoRecomendado() {
        return usoRecomendado;
    }

    public void setUsoRecomendado(String usoRecomendado) {
        this.usoRecomendado = usoRecomendado;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }
}