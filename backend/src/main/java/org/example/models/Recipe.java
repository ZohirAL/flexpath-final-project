package org.example.models;

import java.time.LocalDateTime;

public class Recipe {

    private Integer id;
    private String title;
    private String description;
    private String ingredients;
    private String instructions;
    private Integer prepTime;
    private Integer countryId;
    private Integer categoryId;
    private String countryName;
    private String categoryName;
    private String owner;
    private boolean isPublic;
    private LocalDateTime createdAt;

    public Recipe() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getOwner() {
        return owner;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
