package service.impl;

import dao.NoteTypeDAO;
import dao.NoteDAO;
import model.NoteType;
import model.Note;
import service.NoteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class NoteServiceImpl implements NoteService {
    private NoteDAO noteDAO = new NoteDAO();
    private NoteTypeDAO typeNoteDAO = new NoteTypeDAO();

    @Override
    public void showListNote(HttpServletRequest request, HttpServletResponse response) {
        List<Note> notes = this.noteDAO.selectAllNote();
        request.setAttribute("notes", notes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/note/list.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/note/create.jsp");
        List<NoteType> noteTypes = this.typeNoteDAO.selectAllTypeNote();
        request.setAttribute("noteTypes", noteTypes);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createNote(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int typeNoteId = Integer.valueOf(request.getParameter("typeNoteId"));
        NoteType typeNote = this.typeNoteDAO.selectTypeNote(typeNoteId);

        Note note = new Note(title, content,typeNote);
        this.noteDAO.insertNote(note);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/note/create.jsp");
        request.setAttribute("message", "Note mới đã được thêm vào");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFormEdit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Note note = this.noteDAO.selectNote(id);
        List<NoteType> noteTypes = this.typeNoteDAO.selectAllTypeNote();
        request.setAttribute("noteTypes", noteTypes);
        RequestDispatcher dispatcher;
        if (note == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("note", note);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/note/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editNote(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int typeNoteId = Integer.parseInt(request.getParameter("typeNoteId"));
        NoteType typeNote = this.typeNoteDAO.selectTypeNote(typeNoteId);

        Note note = this.noteDAO.selectNote(id);
        RequestDispatcher dispatcher;
        if (note == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            note.setTitle(title);
            note.setContent(content);
            note.setNoteType(typeNote);
            this.noteDAO.updateNote(note);
            List<NoteType> noteTypes = this.typeNoteDAO.selectAllTypeNote();
            request.setAttribute("noteTypes", noteTypes);
            request.setAttribute("note", note);
            request.setAttribute("message", "Thông tin của note đã được cập nhật");
            dispatcher = request.getRequestDispatcher("WEB-INF/views/note/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFormDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Note note = this.noteDAO.selectNote(id);
        RequestDispatcher dispatcher;
        if (note == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("note", note);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/note/delete.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNote(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Note note = this.noteDAO.selectNote(id);
        RequestDispatcher dispatcher;
        if (note == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            this.noteDAO.deleteNote(id);
        }
        try {
            response.sendRedirect("/notes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectAllNoteByTypeNoteId(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int department_id = Integer.parseInt(request.getParameter("id"));
        NoteType noteType = this.typeNoteDAO.selectTypeNote(department_id);
        request.setAttribute("noteType", noteType);
        List<Note> notes = this.noteDAO.selectAllByTypeNoteId(department_id);
        request.setAttribute("notes", notes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/note/view.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllNoteByTypeNoteId(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.noteDAO.deleteAllNotesByTypeNoteId(id);
    }

    @Override
    public void showMoveNoteForm(HttpServletRequest request, HttpServletResponse response) {
        int typeNoteId=Integer.parseInt((request.getParameter("id")));
        NoteType noteType = this.typeNoteDAO.selectTypeNote(typeNoteId);
        request.setAttribute("noteType", noteType);
        List<NoteType> noteTypes = this.typeNoteDAO.selectAllTypeNote();
        request.setAttribute("noteTypes", noteTypes);
        List<Note> notes=this.noteDAO.selectAllByTypeNoteId(typeNoteId);
        request.setAttribute("notes", notes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/note/move.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveNote(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.valueOf(request.getParameter("idNote"));
        Note note=this.noteDAO.selectNote(id);
        int typeNoteId = Integer.valueOf(request.getParameter("typeNoteId"));
        NoteType noteType = this.typeNoteDAO.selectTypeNote(typeNoteId);
        RequestDispatcher dispatcher;
        if (note == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            NoteType noteType1=note.getNoteType();
            note.setNoteType(noteType);
            this.noteDAO.updateNote(note);
            request.setAttribute("noteType1", noteType1);
            List<NoteType> noteTypes = this.typeNoteDAO.selectAllTypeNote();
            request.setAttribute("noteTypes", noteTypes);
            List<Note> notes=this.noteDAO.selectAllByTypeNoteId(noteType1.getId());
            request.setAttribute("notes", notes);
            request.setAttribute("message", "Bạn đã chuyển thành công note " +note.getTitle()+
                    " của loại note "+noteType1.getName()+ " sang " +noteType.getName()+"!");
            dispatcher = request.getRequestDispatcher("WEB-INF/views/note/move.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showInfoNote(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Note note = this.noteDAO.selectNote(id);
        RequestDispatcher dispatcher;
        if (note == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("note", note);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/note/viewNote.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
