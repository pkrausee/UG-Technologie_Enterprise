package pl.edu.ug.tent.springmvcdemo.domain;

import java.sql.Date;
import java.util.UUID;

public class PotatoSack {
    private UUID id;
    private String kind;
    private double weight;
    private Date ripDate;
    private Date expirationDate;
    private String magazine;

    public PotatoSack() {
    }

    public PotatoSack(String kind, double weight, Date ripDate, Date expirationDate, String magazine) {
        this.id = null;
        this.kind = kind;
        this.weight = weight;
        this.ripDate = ripDate;
        this.expirationDate = expirationDate;
        this.magazine = magazine;
    }

    @Override
    public String toString() {
        return "PotatoSack{" +
                "id=" + id +
                ", kind='" + kind + '\'' +
                ", weight=" + weight +
                ", ripDate=" + ripDate +
                ", expirationDate=" + expirationDate +
                ", magazine='" + magazine + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getRipDate() {
        return ripDate;
    }

    public void setRipDate(Date ripDate) {
        this.ripDate = ripDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }
}
