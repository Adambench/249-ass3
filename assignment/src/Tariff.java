/*
Assignment 3
Question : Part 2
Written by: Mountaga Sy 40312584 and Adam Benchekroun 40306874

This class represents a Tariff with attributes for destination country, origin country, 
product category, and minimum tariff.


*/



public class Tariff {
    private String destinationCountry;
    private String originCountry;
    private String productCategory;
    private double minimumTariff;


    public Tariff(String destinationCountry, String originCountry, String productCategory, double minimumTariff) {
        this.destinationCountry = destinationCountry;
        this.originCountry = originCountry;
        this.productCategory = productCategory;
        this.minimumTariff = minimumTariff;
    }

    public Tariff(Tariff tariff) {
        this.destinationCountry = tariff.destinationCountry;
        this.originCountry = tariff.originCountry;
        this.productCategory = tariff.productCategory;
        this.minimumTariff = tariff.minimumTariff;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }
    
    public String getOriginCountry() {
        return originCountry;
    }
    public String getProductCategory() {
        return productCategory;
    }
    public double getMinimumTariff() {
        return minimumTariff;
    }
    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setMinimumTariff(double minimumTariff) {
        this.minimumTariff = minimumTariff;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "destinationCountry='" + destinationCountry + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", minimumTariff=" + minimumTariff +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tariff tariff = (Tariff) obj;
        return Double.compare(tariff.minimumTariff, minimumTariff) == 0 &&
                destinationCountry.equals(tariff.destinationCountry) &&
                originCountry.equals(tariff.originCountry) &&
                productCategory.equals(tariff.productCategory);
    }
    
    public Tariff clone() {
        return new Tariff(destinationCountry, originCountry, productCategory, minimumTariff);
    }



}
