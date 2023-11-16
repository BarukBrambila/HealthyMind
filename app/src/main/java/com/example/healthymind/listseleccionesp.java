package com.example.healthymind;

public class listseleccionesp {
    public String nombre;
    public String especialidad;
    public String preio;
    public String foto;

    public listseleccionesp(String nombre, String especialidad, String preio, String foto) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.preio = preio;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getPreio() {
        return preio;
    }

    public void setPreio(String preio) {
        this.preio = preio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
