package com.idnp.proyectofinal.models;

public class VaccinationPlace {
    double alat, along;
    String placeName;
    public VaccinationPlace(){

    }
    public VaccinationPlace(String a, double b, double c){
        setPlaceName(a);
        setCc(b,c);
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
}
