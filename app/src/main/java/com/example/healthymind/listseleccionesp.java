package com.example.healthymind;

public class listseleccionesp {
    public String nombres;
    public String especialidad;
    public String preio;
    public String foto;

    public listseleccionesp(String nombres, String especialidad, String preio, String foto) {
        this.nombres = nombres;
        this.especialidad = especialidad;
        this.preio = preio;
        this.foto = foto;
    }
    public listseleccionesp(){

    }
    public String getNombre() {
        return nombres;
    }

    public void setNombre(String nombres) {
        this.nombres = nombres;
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
