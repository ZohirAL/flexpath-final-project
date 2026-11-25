package org.example.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.example.daos.CookbookDao;
import org.example.models.Cookbook;
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
@RequestMapping("/cookbooks")
public class CookbookController {

    private final CookbookDao cookbookDao;

    public CookbookController(CookbookDao cookbookDao) {
        this.cookbookDao = cookbookDao;
    }

    // Get all public cookbooks.

    @GetMapping
    public ResponseEntity<List<Cookbook>> getPublicCookbooks() {
        return ResponseEntity.ok(cookbookDao.getPublicCookbooks());
    }

    // Get a user's owned recipe

    @GetMapping("/mine")
    public ResponseEntity<?> getMyCookbooks(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(cookbookDao.getMyCookbooks(principal.getName()));
    }

    // Get a cookbook by id

    @GetMapping("/{id}")
    public ResponseEntity<?> getCookbookById(@PathVariable int id, Principal principal) {

        String owner = principal == null ? null : principal.getName();

        Cookbook cookbook = cookbookDao.getCookbookById(id, owner);
        if (cookbook == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Cookbook not found: " + id));
        }
        return ResponseEntity.ok(cookbook);
    }

    // List recipes in a cookbook.

    @GetMapping("/{id}/recipes")
    public ResponseEntity<?> getCookbookRecipes(@PathVariable int id, Principal principal) {

        String owner = principal == null ? null : principal.getName();

        Cookbook cookbook = cookbookDao.getCookbookById(id, owner);
        if (cookbook == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Cookbook recipe not found: " + id));
        }
        List<Recipe> recipes = cookbookDao.getCookbookRecipes(id, owner);
        return ResponseEntity.ok(recipes);

    }

    // Creating a cookbook

    @PostMapping
    public ResponseEntity<?> createCookbook(@RequestBody Cookbook cookbook, Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (cookbook.getName() == null || cookbook.getName().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Cookbook name is required."));
        }

        cookbook.setOwner(principal.getName());
        Cookbook created = cookbookDao.createCookbook(cookbook);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Adding a recipe to an owned cookbook

    @PostMapping("/{id}/recipes/{recipeId}")
    public ResponseEntity<?> addRecipeToCookbook(
            @PathVariable int id,
            @PathVariable int recipeId,
            Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        int rowsAdded = cookbookDao.addRecipeToCookbook(id, recipeId, principal.getName());
        if (rowsAdded == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();

    }

    // Deleting a recipe from an owned cookbook.

    @DeleteMapping("/{id}/recipes/{recipeId}")
    public ResponseEntity<?> removeRecipeFromCookbook(
            @PathVariable int id,
            @PathVariable int recipeId,
            Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        int rowsDeleted = cookbookDao.removeRecipeFromCookbook(id, recipeId, principal.getName());
        if (rowsDeleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();

    }
    // Update a cookbook by id.

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCookbook(@PathVariable int id, @RequestBody Cookbook cookbook, Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (cookbook.getName() == null || cookbook.getName().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Cookbook name is required."));
        }

        cookbook.setId(id);
        cookbook.setOwner(principal.getName());

        int rowsUpdated = cookbookDao.updateCookbook(cookbook);
        if (rowsUpdated == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();

    }

    // Deleting an owned cookbook

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCookbook(@PathVariable int id, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        int rows = cookbookDao.deleteCookbook(id, principal.getName());
        if (rows == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Cookbook not found or not yours: " + id));
        }

        return ResponseEntity.noContent().build();
    }

    // Search a cookbook by name.

    @GetMapping("/search")
    public ResponseEntity<List<Cookbook>> searchCookbook(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {

        return ResponseEntity.ok(
                cookbookDao.searchPublicCookbooks(name, sortBy, direction));
    }

}