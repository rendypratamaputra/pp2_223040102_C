package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.JenisMember;

public class JenisMemberDao {

    public int insert(JenisMember jenisMember) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            String sql = "INSERT INTO jenis_member(id, nama) VALUES(?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, jenisMember.getId());
                statement.setString(2, jenisMember.getNama());

                result = statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(JenisMember jenisMember) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            String sql = "UPDATE jenis_member SET nama = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, jenisMember.getNama());
                statement.setString(2, jenisMember.getId());

                result = statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(JenisMember jenisMember) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            String sql = "DELETE FROM jenis_member WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, jenisMember.getId());

                result = statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<JenisMember> findAll() {
        List<JenisMember> list = new ArrayList<>();
        String sql = "SELECT * FROM jenis_member";
        try (Connection connection = MySqlConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            // Retrieving the data
            while (resultSet.next()) {
                JenisMember jenisMember = new JenisMember();
                jenisMember.setId(resultSet.getString("id"));
                jenisMember.setNama(resultSet.getString("nama"));
                list.add(jenisMember);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
