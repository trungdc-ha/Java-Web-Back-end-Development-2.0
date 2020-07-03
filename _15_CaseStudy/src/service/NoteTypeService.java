package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface NoteTypeService {
    void showCreateForm(HttpServletRequest request, HttpServletResponse response);

    void createNoteType(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void listTypeNote(HttpServletRequest request, HttpServletResponse response);

    void showEditForm(HttpServletRequest request, HttpServletResponse response);

    void updateTypeNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException;

    void showDeleteForm(HttpServletRequest request, HttpServletResponse response);

    void deleteTypeNote(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void showNoteByTypeNoteId(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void showMoveNoteForm(HttpServletRequest request, HttpServletResponse response);

    void showUploadForm(HttpServletRequest request, HttpServletResponse response);

    void uploadFileExcel(HttpServletRequest request, HttpServletResponse response);

    void exportExcel(HttpServletRequest request, HttpServletResponse response);
}
