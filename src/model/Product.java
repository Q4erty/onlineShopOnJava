package model;

public class Product {
    private int id;
    private String nameProduct;
    private String descriptionProduct;
    private int cost;
    private int left;

    public Product() {
    }

    public Product(String nameProduct, String descriptionProduct, int cost, int left) {
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.cost = cost;
        this.left = left;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }
}