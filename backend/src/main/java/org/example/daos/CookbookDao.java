package org.example.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.example.exceptions.DaoException;
import org.example.models.Cookbook;
import org.example.models.Recipe;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class CookbookDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String SELECT_COOKBOOK = """
            SELECT id, name, description, is_public AS isPublic, owner
            FROM cookbooks
            """;

    public CookbookDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Get all public cookbooks, sorted in ASC order.

    public List<Cookbook> getPublicCookbooks() {
        String sql = SELECT_COOKBOOK + " WHERE is_public = TRUE ORDER BY name ASC, id ASC";

        return jdbcTemplate.query(sql, this::mapToCookbook);
    }

    // List all recipes in a cookbook the user can access: public recipes and user's own recipes.

    public List<Recipe> getCookbookRecipes(int cookbookId, String owner) {
        String sql = """
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
                FROM cookbook_recipes cr
                JOIN cookbooks cb ON cb.id = cr.cookbook_id
                JOIN recipes r ON r.id = cr.recipe_id
                LEFT JOIN countries c ON c.id = r.country_id
                LEFT JOIN categories cat ON cat.id = r.category_id
                WHERE cb.id = ?
                AND (cb.is_public = TRUE OR cb.owner = ?)
                AND (r.is_public = TRUE OR r.owner = ?)
                ORDER BY r.title ASC
                """;

        return jdbcTemplate.query(sql, this::mapToRecipe, cookbookId, owner, owner);
    }

    // Get all cookbooks owned by a user, sorted in ASC order.

    public List<Cookbook> getMyCookbooks(String owner) {
        String sql = SELECT_COOKBOOK + " WHERE owner = ? ORDER BY name ASC, id ASC";

        return jdbcTemplate.query(sql, this::mapToCookbook, owner);
    }

    // Get a Cookbook by id or owned by a requesting user.

    public Cookbook getCookbookById(int id, String owner) {
        String sql = SELECT_COOKBOOK + " WHERE id = ? AND (is_public = TRUE OR owner = ?) ";
        try {
            return jdbcTemplate.queryForObject(sql, this::mapToCookbook, id, owner);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Create a new cookbook.

    public Cookbook createCookbook(Cookbook cookbook) {

        String sql = """
                INSERT INTO cookbooks(
                name, description, is_public, owner
                )
                VALUES(?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,
                    java.sql.Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cookbook.getName());
            ps.setString(2, cookbook.getDescription());
            ps.setBoolean(3, Boolean.TRUE.equals(cookbook.getIsPublic()));
            ps.setString(4, cookbook.getOwner());

            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new DaoException("Failed to create cookbook.");
        }

        return getCookbookById(key.intValue(), cookbook.getOwner());
    }

    // Updating an existing cookbook if it belongs to the owner
    
    public int updateCookbook(Cookbook cookbook) {

        String sql = """
                UPDATE cookbooks
                SET name = ?,
                    description = ?,
                    is_public = ?
                WHERE id = ?
                AND owner = ?
                """;

        return jdbcTemplate.update(sql,
                cookbook.getName(),
                cookbook.getDescription(),
                Boolean.TRUE.equals(cookbook.getIsPublic()),
                cookbook.getId(),
                cookbook.getOwner());

    }

    // Delete a cookbook if it belongs to the given owner.

    public int deleteCookbook(int id, String owner) {
        String sql = "DELETE FROM cookbooks WHERE id = ? AND owner = ?";
        return jdbcTemplate.update(sql, id, owner);

    }

    // Add a recipe to cookbook only if the cookbook belongs to the user
    // and the recipe is public or belongs to the same user

    public int addRecipeToCookbook(int cookbookId, int recipeId, String owner) {
        String sql = """
                INSERT IGNORE INTO cookbook_recipes (cookbook_id, recipe_id)
                SELECT c.id, r.id
                FROM cookbooks c
                JOIN recipes r ON r.id = ?
                WHERE c.id = ?
                AND c.owner = ?
                AND (r.is_public = TRUE OR r.owner = ?)
                """;

        return jdbcTemplate.update(sql, recipeId, cookbookId, owner, owner);
    }

    // Remove a recipe from a user owned cookbook.

    public int removeRecipeFromCookbook(int cookbookId, int recipeId, String owner) {
        String sql = """
                DELETE cr FROM cookbook_recipes cr
                JOIN cookbooks c ON c.id = cr.cookbook_id
                WHERE cr.cookbook_id = ?
                AND cr.recipe_id = ?
                AND c.owner = ?
                """;
        return jdbcTemplate.update(sql, cookbookId, recipeId, owner);
    }

    // Mapping a database row to a cookbook.

    private Cookbook mapToCookbook(ResultSet resultSet, int rowNumber) throws SQLException {
        Cookbook cookbook = new Cookbook();
        cookbook.setId(resultSet.getInt("id"));
        cookbook.setName(resultSet.getString("name"));
        cookbook.setDescription(resultSet.getString("description"));
        cookbook.setIsPublic(resultSet.getBoolean("isPublic"));
        cookbook.setOwner(resultSet.getString("owner"));

        return cookbook;

    }

    // Mapping a database row to a recipe

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

    // Searching and sorting public cookbooks

    public List<Cookbook> searchPublicCookbooks(
            String name,
            String sortBy,
            String direction) {

        StringBuilder sql = new StringBuilder(SELECT_COOKBOOK);
        List<Object> params = new ArrayList<>();

        // Searching public cookbooks only.

        sql.append(" WHERE is_public = TRUE ");

        // Search by cookbook name.

        if (name != null && !name.isBlank()) {
            sql.append(" AND name LIKE ? ");
            params.add("%" + name.trim() + "%");
        }

        // Whitelist sorting columns.

        Map<String, String> sortColumns = Map.of("name", "name",
                "id", "id");

        String sortColumn = sortColumns.getOrDefault(sortBy, "name");

        // Validate sorting direction defaulting to ASC order.

        String sortDirection = "DESC".equalsIgnoreCase(direction) ? "DESC" : "ASC";

        // Sort by the selected field.

        String secondaryColumn = "name".equals(sortColumn) ? "id" : "name";

        sql.append(" ORDER BY ").append(sortColumn)
                .append(" ")
                .append(sortDirection)
                .append(", ")
                .append(secondaryColumn)
                .append(" ASC");

        return jdbcTemplate.query(
                sql.toString(),
                this::mapToCookbook,
                params.toArray());

    }

}
