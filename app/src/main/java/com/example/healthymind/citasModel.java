package com.example.healthymind;

public class citasModel {
    private String fecha_cita;
    private String hr_cita;
    private String id_paciente;
    private String rfc_especialista;
    public citasModel(String fecha_cita, String hr_cita, String id_paciente, String rfc_especialista) {
        this.fecha_cita = fecha_cita;
        this.hr_cita = hr_cita;
        this.id_paciente = id_paciente;
        this.rfc_especialista = rfc_especialista;
    }
    public citasModel(){

    }
    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getHr_cita() {
        return hr_cita;
    }

    public void setHr_cita(String hr_cita) {
        this.hr_cita = hr_cita;
    }

    public String getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(String id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getRfc_especialista() {
        return rfc_especialista;
    }

    public void setRfc_especialista(String rfc_especialista) {
        this.rfc_especialista = rfc_especialista;
    }
}
