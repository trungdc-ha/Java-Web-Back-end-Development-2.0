package controllers;


import service.NoteTypeService;
import service.NoteService;
import service.impl.NoteTypeServiceImpl;
import service.impl.NoteServiceImpl;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet("/typeNote")
@MultipartConfig
public class NoteTypeServlet extends javax.servlet.http.HttpServlet {
    public NoteTypeService noteTypeService = new NoteTypeServiceImpl();
    public NoteService noteService = new NoteServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                try {
                    this.noteTypeService.createNoteType(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "edit": {
                try {
                    this.noteTypeService.updateTypeNote(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "delete": {
                try {
                    this.noteService.deleteAllNoteByTypeNoteId(request, response);
                    this.noteTypeService.deleteTypeNote(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "move": {
                try {
                    this.noteService.moveNote(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case "upload": {
                this.noteTypeService.uploadFileExcel(request,response);
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
                this.noteTypeService.showCreateForm(request, response);
                break;
            }
            case "edit": {
                this.noteTypeService.showEditForm(request, response);
                break;
            }
            case "delete": {
                this.noteTypeService.showDeleteForm(request, response);
                break;
            }
            case "view": {
                try {
                    this.noteTypeService.showNoteByTypeNoteId(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case "move": {
                this.noteService.showMoveNoteForm(request, response);
                break;
            }
            case "upload": {
                this.noteTypeService.showUploadForm(request, response);
                break;
            }
            case "export": {
                this.noteTypeService.exportExcel(request, response);
                break;
            }
            default:
                this.noteTypeService.listTypeNote(request, response);
                break;

        }
    }
}
