package com.example.healthymind;

public class Posts {
    private String id;
    private String rfc;
    public String titulo;
    public String texto;
    public String imagen;

    public Posts() {
    }

    public String getId() {
        return id;
    }

    public String getRfc() {
        return rfc;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

