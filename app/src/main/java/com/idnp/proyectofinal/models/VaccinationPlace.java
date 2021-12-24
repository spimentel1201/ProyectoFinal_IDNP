package com.idnp.proyectofinal.models;

import java.util.Date;

public class VaccinationPlace {
    double alat, along;
    int id;
    String placeName;
    String vaccineName;
    Date Fecha;

    public VaccinationPlace(){

    }
    public VaccinationPlace(int id,String a, String v, double b, double c){
        setId(id);
        setPlaceName(a);
        setCc(b,c);
        setVaccineName(v);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPlaceName(String placeName1){
        this.placeName=placeName1;
    }
    public void setCc(double latx,double longy){
        this.alat=latx;
        this.along=longy;
    }
    public String getPlaceName(){
        return this.placeName;
    }
    public double getLat(){
        return this.alat;
    }
    public double getLong(){
        return this.along;
    }
    public String getVaccineName() {
        return vaccineName;
    }
    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }
    public Date getFecha() {
        return Fecha;
    }
    public void setFecha(Date fecha) {
        Fecha = fecha;
    }
}
