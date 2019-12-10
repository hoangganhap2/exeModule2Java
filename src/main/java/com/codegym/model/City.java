package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @Positive
    private double acreage;
    @Positive
    private double population;
    @Positive
    private double GDP;
    @NotEmpty
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public City() {
    }

    public City(String name, double acreage, double population, double GDP, String description) {
        this.name = name;
        this.acreage = acreage;
        this.population = population;
        this.GDP = GDP;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("City[id=%d, name='%s', acreage='%s', population='%s', GDP='%s', description='%s'",id, name, acreage, population, GDP, description);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAcreage() {
        return acreage;
    }

    public void setAcreage(double acreage) {
        this.acreage = acreage;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public double getGDP() {
        return GDP;
    }

    public void setGDP(double GDP) {
        this.GDP = GDP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
