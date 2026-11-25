package org.example.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.example.daos.RecipeDao;
import org.example.models.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeDao recipeDao;

    public RecipeController(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @GetMapping("/public")
    public List<Recipe> getPublicRecipes() {
        return recipeDao.getPublicRecipes();
    }

    @GetMapping("/mine")
    public ResponseEntity<Object> getMyRecipes(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(recipeDao.getRecipeByOwner(principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable int id) {
        Recipe recipe = recipeDao.getRecipeById(id);
        if (recipe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Recipe not dound: " + id));
        }
        return ResponseEntity.ok(recipe);
    }

    @PostMapping
    public ResponseEntity<?> createRecipe(@RequestBody Recipe recipe, Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (recipe.getTitle() == null || recipe.getTitle().isBlank() || recipe.getCountryId() == null
                || recipe.getCategoryId() == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "title, countryId and categoryId are required. "));
        }

        recipe.setOwner(principal.getName());
        Recipe created = recipeDao.createRecipe(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable int id, @RequestBody Recipe recipe, Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (recipe.getTitle() == null || recipe.getTitle().isBlank() || recipe.getCountryId() == null
                || recipe.getCategoryId() == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Recipe not found or not yours " + id));
        }

        recipe.setId(id);
        recipe.setOwner(principal.getName());

        int rowsUpdated = recipeDao.updateRecipe(recipe);
        if (rowsUpdated == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable int id, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        int rows = recipeDao.deleteRecipe(id, principal.getName());
        if (rows == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Recipe not found or not yours: " + id));
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {

        List<Recipe> recipes = recipeDao.searchPublicRecipes(title, countryId, categoryId, sortBy, direction);

        return ResponseEntity.ok(recipes);
    }

}
