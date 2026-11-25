package org.example.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.example.exceptions.DaoException;
import org.example.models.Recipe;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeDao {

    private final JdbcTemplate jdbcTemplate;

    // Base query that joins the country with the category name

    private static final String SELECT_RECIPE_WITH_NAMES =
           
            """
                    SELECT r.id,
                            r.title,
                            r.description,
                            r.ingredients,
                            r.instructions,
                            r.prep_time AS prepTime,
                            r.country_id AS countryId,
                            r.category_id AS categoryId,
                            c.name AS countryName,
                            cat.name AS categoryName,
                            r.owner,
                            r.is_public AS isPublic,
                            r.created_at AS createdAt
                            FROM recipes r
                            LEFT JOIN countries c ON c.id = r.country_id
                            LEFT JOIN categories cat ON cat.id = r.category_id
                            """;

    public RecipeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Get all public recipes, sorted by newst firts.

    public List<Recipe> getPublicRecipes() {
        String sql = SELECT_RECIPE_WITH_NAMES + " WHERE r.is_public = TRUE ORDER BY r.created_at DESC";
        return jdbcTemplate.query(sql, this::mapToRecipe);
    }

    // Get all recipes owned by a user, sorted by newst firts.

    public List<Recipe> getRecipeByOwner(String owner) {
        String sql = SELECT_RECIPE_WITH_NAMES + " WHERE r.owner = ? ORDER BY r.created_at DESC";
        return jdbcTemplate.query(sql, this::mapToRecipe, owner);
    }

    // Get a recipe by id.

    public Recipe getRecipeById(int id) {
        String sql = SELECT_RECIPE_WITH_NAMES + " WHERE r.id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapToRecipe, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Create a new recipe.

    public Recipe createRecipe(Recipe recipe) {

        String sql = """
                INSERT INTO recipes(
                country_id, category_id, title, description, ingredients, instructions,
                prep_time, owner, is_public
                )
                VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,
                    java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, recipe.getCountryId(), Types.INTEGER);
            ps.setObject(2, recipe.getCategoryId(), Types.INTEGER);
            ps.setString(3, recipe.getTitle());
            ps.setString(4, recipe.getDescription());
            ps.setString(5, recipe.getIngredients());
            ps.setString(6, recipe.getInstructions());
            ps.setObject(7, recipe.getPrepTime(), Types.INTEGER);
            ps.setString(8, recipe.getOwner());
            ps.setBoolean(9, Boolean.TRUE.equals(recipe.getIsPublic()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new DaoException("Failed: No generated key returned");
        }

        Recipe created = getRecipeById(keyHolder.getKey().intValue());
        if (created == null) {
            throw new DaoException("Failed to create recipe.");
        }

        return created;
    }

    // Updating an existing recipe.
    public int updateRecipe(Recipe recipe) {

        String sql = """
                UPDATE recipes
                SET country_id = ?,
                    category_id = ?,
                    title = ?,
                    description = ?,
                    ingredients = ?,
                    instructions = ?,
                    prep_time = ?,
                    is_public = ?
                WHERE id = ?
                AND owner = ?
                """;

        return jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, recipe.getCountryId(), Types.INTEGER);
            ps.setObject(2, recipe.getCategoryId(), Types.INTEGER);
            ps.setString(3, recipe.getTitle());
            ps.setString(4, recipe.getDescription());
            ps.setString(5, recipe.getIngredients());
            ps.setString(6, recipe.getInstructions());
            ps.setObject(7, recipe.getPrepTime(), Types.INTEGER);
            // ps.setString(8, recipe.getOwner());
            ps.setBoolean(8, Boolean.TRUE.equals(recipe.getIsPublic()));
            ps.setObject(9, recipe.getId(), Types.INTEGER);
            ps.setString(10, recipe.getOwner());
            return ps;
        });
    }

    // Delete a recipe if it belongs to the given owner.

    public int deleteRecipe(int id, String owner) {
        String sql = "DELETE FROM recipes WHERE id = ? AND owner = ?";
        return jdbcTemplate.update(sql, id, owner);

    }

    // Mapping a row in the resultset to a recipe object.

    private Recipe mapToRecipe(ResultSet resultSet, int rowNumber) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(resultSet.getInt("id"));
        recipe.setTitle(resultSet.getString("title"));
        recipe.setDescription(resultSet.getString("description"));
        recipe.setIngredients(resultSet.getString("ingredients"));
        recipe.setInstructions(resultSet.getString("instructions"));
        recipe.setPrepTime((Integer) resultSet.getObject("prepTime"));
        recipe.setCountryId((Integer) resultSet.getObject("countryId"));
        recipe.setCategoryId((Integer) resultSet.getObject("categoryId"));
        recipe.setCountryName(resultSet.getString("countryName"));
        recipe.setCategoryName(resultSet.getString("categoryName"));
        recipe.setOwner(resultSet.getString("owner"));
        recipe.setIsPublic(resultSet.getBoolean("isPublic"));
        java.sql.Timestamp createdAt = resultSet.getTimestamp("createdAt");
        recipe.setCreatedAt(createdAt == null ? null : createdAt.toLocalDateTime());
        return recipe;

    }

    public List<Recipe> searchPublicRecipes(
            String title,
            Integer countryId,
            Integer categoryId,
            String sortBy,
            String direction) {

        StringBuilder sql = new StringBuilder(SELECT_RECIPE_WITH_NAMES);
        List<Object> params = new ArrayList<>();

        // Searching public recipes only.

        sql.append(" WHERE r.is_public = TRUE ");

        // Search by recipe title.

        if (title != null && !title.isBlank()) {
            sql.append(" AND r.title LIKE ? ");
            params.add("%" + title.trim() + "%");
        }

        // Search by country.

        if (countryId != null) {
            sql.append(" AND r.country_id = ? ");
            params.add(countryId);
        }

        // Search by category.

        if (categoryId != null) {
            sql.append(" AND r.category_id = ? ");
            params.add(categoryId);
        }

        // Whitelist sorting columns.

        Map<String, String> sortColumns = Map.of("title", "r.title",
                "prepTime", "r.prep_time");

        String sortColumn = sortColumns.getOrDefault(sortBy, "r.title");

        // Validation sorting direction defaulting to ASC order.

        String sortDirection = "DESC".equalsIgnoreCase(direction) ? "DESC" : "ASC";

        // Sort by the selected field.

        String secondaryColumn = "r.title".equals(sortColumn) ? "r.prep_time" : "r.title";

        sql.append(" ORDER BY ").append(sortColumn)
                .append(" ")
                .append(sortDirection)
                .append(", ")
                .append(secondaryColumn)
                .append(" ASC");

        return jdbcTemplate.query(sql.toString(),
                this::mapToRecipe,
                params.toArray());

    }
}
