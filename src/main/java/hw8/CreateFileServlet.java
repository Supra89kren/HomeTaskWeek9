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

@WebServlet("/file/create")
public class CreateFileServlet extends HttpServlet {
    private StringBuilder reqParameter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<body>");

        Path currentPath;
        reqParameter.append(req.getParameter("path")+"\\");
        reqParameter.append(req.getParameter("fileName"));
        currentPath = Paths.get(reqParameter.toString());

        sb.append("<H1>" + currentPath + "</H1>");

        sb.append(createFile(currentPath));



        sb.append("</body>");
        sb.append("</html>");
        resp.getWriter().write(sb.toString());
    }

    private StringBuilder createFile(Path path) {
        File file = new File(path.toUri());
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();


        return stringBuilder;
    }
}
