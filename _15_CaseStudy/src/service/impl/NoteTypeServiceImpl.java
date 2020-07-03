package service.impl;

import dao.NoteTypeDAO;
import dao.NoteDAO;
import model.Note;
import model.NoteType;
import service.NoteTypeService;
import service.NoteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@MultipartConfig
public class NoteTypeServiceImpl implements NoteTypeService {
    private NoteTypeDAO noteTypeDAO = new NoteTypeDAO();
    private NoteService noteService = new NoteServiceImpl();
    private NoteDAO noteDAO = new NoteDAO();
    public static final String SAVE_DIRECTORY = "uploadExcel";


    public void listTypeNote(HttpServletRequest request, HttpServletResponse response) {
        List<NoteType> noteTypes = noteTypeDAO.selectAllTypeNote();
        request.setAttribute("noteTypes", noteTypes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/note_type/list.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/note_type/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNoteType(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        NoteType noteType = new NoteType(name, description);
        this.noteTypeDAO.insertTypeNote(noteType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/note_type/create.jsp");
        request.setAttribute("message", "Type note mới đã được thêm");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        NoteType noteType = this.noteTypeDAO.selectTypeNote(id);
        RequestDispatcher dispatcher;
        if (noteType == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("noteType", noteType);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/note_type/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTypeNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        NoteType noteType = this.noteTypeDAO.selectTypeNote(id);
        RequestDispatcher dispatcher;
        if (noteType == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            noteType.setName(name);
            noteType.setDescription(description);
            this.noteTypeDAO.updateTypeNote(noteType);
            request.setAttribute("noteType", noteType);
            request.setAttribute("message", "Thông tin của type note đã được cập nhật");
            dispatcher = request.getRequestDispatcher("WEB-INF/views/note_type/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        NoteType noteType = this.noteTypeDAO.selectTypeNote(id);
        RequestDispatcher dispatcher;
        if (noteType == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("noteType", noteType);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/note_type/delete.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTypeNote(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        NoteType noteType = this.noteTypeDAO.selectTypeNote(id);
        RequestDispatcher dispatcher;
        if (noteType == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            this.noteTypeDAO.deleteTypeNote(id);
        }
        try {
            response.sendRedirect("/typeNote");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showNoteByTypeNoteId(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        this.noteService.selectAllNoteByTypeNoteId(request, response);
    }

    @Override
    public void showMoveNoteForm(HttpServletRequest request, HttpServletResponse response) {
        this.noteService.showMoveNoteForm(request, response);
    }

    @Override
    public void showUploadForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        NoteType noteType = this.noteTypeDAO.selectTypeNote(id);
        RequestDispatcher dispatcher;
        if (noteType == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("noteType", noteType);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/note_type/upload.jsp");
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
    public void uploadFileExcel(HttpServletRequest request, HttpServletResponse response) {
        int typeNoteId = Integer.valueOf(request.getParameter("id"));
        NoteType noteType = this.noteTypeDAO.selectTypeNote(typeNoteId);
        ReadExcelServiceImpl readExcelService = new ReadExcelServiceImpl();
        String filePath = "";
        try {
            String appPath = request.getServletContext().getRealPath("");
            appPath = appPath.replace('\\', '/');

            String fullSavePath;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + SAVE_DIRECTORY;
            } else {
                fullSavePath = appPath + "/" + SAVE_DIRECTORY;
            }
            Part part = null;
            try {
                part = request.getPart("file");

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ServletException e1) {
                e1.printStackTrace();
            }
            String fileName = extractFileName(part);
            System.out.println(fileName);
            if (fileName != null && fileName.length() > 0) {
                filePath = fullSavePath + File.separator + fileName;
                try {
                    part.write(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            response.sendRedirect(request.getContextPath() + "/typeNote");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/department/list.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        try {
            List<Note> notes = readExcelService.readBooksFromExcelFile(filePath);
            for (Note note : notes) {
                note.setNoteType(noteType);
                this.noteDAO.insertNote(note);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        int typeNote_id = Integer.parseInt(request.getParameter("id"));
        NoteType noteType = this.noteTypeDAO.selectTypeNote(typeNote_id);
        WriteExcelServiceImpl writeExcelService = new WriteExcelServiceImpl();
        List<Note> notes = this.noteDAO.selectAllByTypeNoteId(typeNote_id);
        try {
            String excelFilePath = "E:\\WorkSpace\\CodeGym\\Training\\TrainingAllModule\\Truong_Tan_Hai\\Module3\\SS15.CaseStudy\\export\\" + noteType.getName() + ".xls";

            writeExcelService.writeExcel(notes, excelFilePath);
            List<NoteType> noteTypes = noteTypeDAO.selectAllTypeNote();
            request.setAttribute("noteTypes", noteTypes);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/note_type/list.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
}

