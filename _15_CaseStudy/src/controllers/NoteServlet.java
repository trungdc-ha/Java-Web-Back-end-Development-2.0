package controllers;

import service.NoteService;
import service.impl.NoteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet("/notes")
public class NoteServlet extends HttpServlet {
    private NoteService noteService = new NoteServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                try {
                    this.noteService.createNote(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "edit": {
                try {
                    this.noteService.editNote(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "delete": {
                try {
                    this.noteService.deleteNote(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }

            default:

                break;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                this.noteService.showFormCreate(request, response);
                break;
            }
            case "edit": {
                this.noteService.showFormEdit(request, response);
                break;
            }
            case "delete": {
                this.noteService.showFormDelete(request, response);
                break;
            }
            case "viewNote": {
                this.noteService.showInfoNote(request,response);
            }

            default:
                this.noteService.showListNote(request, response);
                break;

        }
    }
}
