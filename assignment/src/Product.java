public class Product {
    private String name; // Product name
    private String country; // Product country of origin
    private String category; // Product category
    private double price; // Product price

    // Constructor
    public Product(String name,String country, String category, double price) {
        this.name = name;
        this.country = country;
        this.category = category;
        this.price = getUpdatedPrice(country, category, price);

    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }


    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public double getUpdatedPrice(String country, String category, double price) {

        switch (country) {
            case "China" -> price += 0.25 * price;

            case "USA" -> {
                if(category.equals("Electronics")){
                    price += 0.10 * price;
                }
            }
            case "Japan" -> {
                if(category.equals("Automobiles")){
                    price += 0.15 * price;
                }
            }

            case "India" -> {
                if(category.equals("Agriculture")){
                    price += 0.05 * price;
                }
            }

            case "South Korea" -> {
                if(category.equals("Electronics")){
                    price += 0.08 * price;
                }
            }

            case "Soudi Arabia" -> {
                if(category.equals("Energy")){
                    price += 0.12 * price;
                }
            }

            case "Germany" -> {
                if(category.equals("Manifacturing")){
                    price += 0.06 * price;
                }
            }

            case "Bengladesh" -> {
                if(category.equals("Textiles")){
                    price += 0.04 * price;
                }
            }


            case "Brazil" -> {
                if(category.equals("Agriculture")){
                    price += 0.09 * price;
                }
            }

        }
        return price;
    }
}
