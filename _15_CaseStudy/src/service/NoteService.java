package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;

public interface NoteService {
    void showListNote(HttpServletRequest request, HttpServletResponse response);

    void showFormCreate(HttpServletRequest request, HttpServletResponse response);

    void createNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException;

    void showFormEdit(HttpServletRequest request, HttpServletResponse response);

    void editNote(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void showFormDelete(HttpServletRequest request, HttpServletResponse response);

    void deleteNote(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void selectAllNoteByTypeNoteId(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void deleteAllNoteByTypeNoteId(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void showMoveNoteForm(HttpServletRequest request, HttpServletResponse response);

    void moveNote(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void showInfoNote(HttpServletRequest request, HttpServletResponse response);
}
