package dao;

import model.NoteType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteTypeDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/INotes?useUnicode=true&characterEncoding=UTF-8";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345678";

    private static final String INSERT_TYPE_NOTE_SQL = "INSERT INTO note_type (name,description) VALUES " +
            " (?,?);";

    private static final String SELECT_TYPE_NOTE_BY_ID = "select id,name,description from note_type where id =?";
    private static final String SELECT_ALL_TYPE_NOTE = "select * from note_type";
    private static final String DELETE_TYPE_NOTE_SQL = "delete from note_type where id = ?;";
    private static final String UPDATE_TYPE_NOTE_SQL = "update note_type set name = ?,description=? where id = ?;";

    public NoteTypeDAO() {
    }

    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertTypeNote(NoteType noteType) throws SQLException {
        System.out.println(INSERT_TYPE_NOTE_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TYPE_NOTE_SQL)) {
            preparedStatement.setString(1, noteType.getName());
            preparedStatement.setString(2, noteType.getDescription());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public NoteType selectTypeNote(int id) {
        NoteType noteType = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TYPE_NOTE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                noteType = new NoteType(id, name,description);
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return noteType;
    }

    public List<NoteType> selectAllTypeNote() {

        List<NoteType> noteTypes = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TYPE_NOTE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                noteTypes.add(new NoteType(id, name,description));
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return noteTypes;
    }

    public boolean deleteTypeNote(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TYPE_NOTE_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            connection.close();
        }
        return rowDeleted;
    }

    public boolean updateTypeNote(NoteType noteType) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_TYPE_NOTE_SQL);) {
            statement.executeQuery("SET NAMES 'UTF8'");
            statement.executeQuery("SET CHARACTER SET 'UTF8'");
            statement.setString(1, noteType.getName());
            statement.setString(2, noteType.getDescription());
            statement.setInt(3, noteType.getId());
            rowUpdated = statement.executeUpdate() > 0;
            connection.close();
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
