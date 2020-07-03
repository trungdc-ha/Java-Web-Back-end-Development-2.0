package dao;

import model.NoteType;
import model.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {
    private NoteTypeDAO departmentDAO = new NoteTypeDAO();

    private String jdbcURL = "jdbc:mysql://localhost:3306/INotes";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345678";

    private static final String INSERT_NOTE_SQL = "INSERT INTO note" + "  (title,content,type_id) VALUES " +
            " (?,?,?);";

    private static final String SELECT_NOTE_BY_ID = "select id,title,content,type_id from note where id =?";
    private static final String SELECT_ALL_NOTES = "select * from note inner join note_type where note.type_id=note_type.id;";
    private static final String DELETE_NOTE_SQL = "delete from note where id = ?;";
    private static final String UPDATE_NOTE_SQL = "update note set title = ?,content = ?, type_id=? where id = ?;";
    private static final String SELECT_NOTE_BY_TYPE_NOTE_ID = "select id,title,content,type_id from note where note.type_id=?;";
    private static final String DELETE_NOTE_SQL_BY_TYPE_NOTE_ID = "delete from note where type_id = ?;";

    public NoteDAO() {
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

    public void insertNote(Note note) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTE_SQL)) {
            preparedStatement.setString(1, note.getTitle());
            preparedStatement.setString(2, note.getContent());
            preparedStatement.setInt(3, note.getNoteType().getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    public Note selectNote(int id) {
        Note employee = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTE_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                int type_id = rs.getInt("type_id");
                NoteType noteType = departmentDAO.selectTypeNote(type_id);

                employee = new Note(id, title, content,noteType);

            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employee;
    }

    public List<Note> selectAllNote() {

        List<Note> notes = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTES);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int type_id = rs.getInt("type_id");
                NoteType noteType = departmentDAO.selectTypeNote(type_id);

                notes.add(new Note(id, title, content, noteType));
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return notes;
    }

    public boolean deleteNote(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_NOTE_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            connection.close();
        }
        return rowDeleted;
    }

    public boolean updateNote(Note note) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_NOTE_SQL)) {
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.setInt(3, note.getNoteType().getId());
            statement.setInt(4, note.getId());

            rowUpdated = statement.executeUpdate() > 0;
            connection.close();
        }
        return rowUpdated;
    }

    public List<Note> selectAllByTypeNoteId(int typeNoteId) {
        List<Note> notes = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTE_BY_TYPE_NOTE_ID)) {
            preparedStatement.setInt(1, typeNoteId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int type_id = rs.getInt("type_id");
                NoteType noteType = departmentDAO.selectTypeNote(type_id);

                notes.add(new Note(id, title, content,noteType));

            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return notes;
    }

    public boolean deleteAllNotesByTypeNoteId(int typeNoteId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_NOTE_SQL_BY_TYPE_NOTE_ID);) {
            statement.setInt(1, typeNoteId);
            rowDeleted = statement.executeUpdate() > 0;
            connection.close();
            return rowDeleted;
        }
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
