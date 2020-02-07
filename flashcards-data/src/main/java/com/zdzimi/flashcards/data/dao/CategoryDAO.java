package com.zdzimi.flashcards.data.dao;

import com.zdzimi.flashcards.data.dto.CategoryDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<CategoryDTO> findAll() throws SQLException {
        String sql = "select * from category";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        Integer categoryId;
        String categoryName;
        Date lastUpdate;

        while (resultSet.next()){
            categoryId = resultSet.getInt("category_id");
            categoryName = resultSet.getString("category_name");
            lastUpdate = resultSet.getDate("last_update");
            categoryDTOS.add(new CategoryDTO(categoryId,categoryName,lastUpdate));
        }
        return categoryDTOS;
    }

    public CategoryDTO findById(int id) throws SQLException {
        String sql = "select * from category where category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            return new CategoryDTO(
                    resultSet.getInt("category_id"),
                    resultSet.getString("category_name"),
                    resultSet.getDate("last_update"));
        }
        return null;
    }

    public CategoryDTO create(CategoryDTO categoryDTO) throws SQLException {
        String sql = "insert into category (category_name, last_update) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,categoryDTO.getCategoryName());
        preparedStatement.setDate(2,categoryDTO.getLastUpdate());
        preparedStatement.executeUpdate();
        return findByCategoryName(categoryDTO.getCategoryName());
    }

    public CategoryDTO findByCategoryName(String categoryName) throws SQLException {
        String sql = "select * from category where category_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,categoryName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            return new CategoryDTO(
                    resultSet.getInt("category_id"),
                    resultSet.getString("category_name"),
                    resultSet.getDate("last_update"));
        }
        return null;
    }

    public void update(CategoryDTO categoryDTO) throws SQLException {
        String sql = "update category set category_name = ?, last_update = ? where category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,categoryDTO.getCategoryName());
        preparedStatement.setDate(2,categoryDTO.getLastUpdate());
        preparedStatement.setInt(3,categoryDTO.getCategoryId());
        preparedStatement.executeUpdate();
    }

    public void delete(CategoryDTO categoryDTO) throws SQLException {
        String sql = "delete from category where category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,categoryDTO.getCategoryId());
        preparedStatement.executeUpdate();
    }
}
