package com.zdzimi.flashcards.data.dao;

import com.zdzimi.flashcards.data.dto.FlashcardDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlashcardDAO {

    private Connection connection;

    public FlashcardDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(FlashcardDTO flashcardDTO) throws SQLException {
        String sql = "insert into flashcard (question, answer, flashcard_status, category_id) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,flashcardDTO.getQuestion());
        preparedStatement.setString(2,flashcardDTO.getAnswer());
        preparedStatement.setInt(3,flashcardDTO.getFlashcardStatus());
        preparedStatement.setInt(4,flashcardDTO.getCategoryId());
        preparedStatement.executeUpdate();
    }

    public void deleteByCategoryId(Integer categoryId) throws SQLException {
        String sql = "delete from flashcard where category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,categoryId);
        preparedStatement.executeUpdate();
    }

    public List<FlashcardDTO> findByCategoryId(Integer cId) throws SQLException {
        String sql = "select * from flashcard where category_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,cId);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<FlashcardDTO> flashcardDTOS = new ArrayList<>();
        Integer flashcardId, flashcardStatus, categoryId;
        String question, answer;

        while (resultSet.next()){
            flashcardId = resultSet.getInt("flashcard_id");
            question = resultSet.getString("question");
            answer = resultSet.getString("answer");
            flashcardStatus = resultSet.getInt("flashcard_status");
            categoryId = resultSet.getInt("category_id");
            flashcardDTOS.add(new FlashcardDTO(flashcardId,question,answer,flashcardStatus,categoryId));
        }
        return flashcardDTOS;
    }

    public void update(FlashcardDTO flashcardDTO) throws SQLException {
        String sql = "update flashcard set question = ?, answer = ?, flashcard_status = ?, category_id = ? where flashcard_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,flashcardDTO.getQuestion());
        preparedStatement.setString(2,flashcardDTO.getAnswer());
        preparedStatement.setInt(3,flashcardDTO.getFlashcardStatus());
        preparedStatement.setInt(4,flashcardDTO.getCategoryId());
        preparedStatement.setInt(5,flashcardDTO.getFlashcardId());
        preparedStatement.executeUpdate();
    }
}
