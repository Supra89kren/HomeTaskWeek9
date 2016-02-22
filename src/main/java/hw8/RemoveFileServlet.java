package main.java.hw8;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/file/remove")
public class RemoveFileServlet extends HttpServlet {
    private String reqParameter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<body>");
        Path currentPath;
        reqParameter = req.getParameter("path");
        currentPath = Paths.get(reqParameter);

        sb.append("<H1>" + currentPath + "</H1>");

        sb.append(removeFile(currentPath));


        sb.append("</body>");
        sb.append("</html>");
        resp.getWriter().write(sb.toString());
    }

    private StringBuilder removeFile(Path path) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(path.toUri());
        if (file.exists()) {
            stringBuilder.append("<h2>" +
                    file.getAbsolutePath() +
                    " deleted</h2>");
            file.delete();

        } else stringBuilder.append("<h2>File not exists</h2>");
        return stringBuilder;
    }
}
