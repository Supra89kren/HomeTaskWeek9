package main.java.hw8;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/file/view")
public class ViewFileServlet extends HttpServlet {
    private String reqParameter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        Path currentPath;
        reqParameter = req.getParameter("path");
        currentPath = Paths.get(reqParameter);

        sb.append("<H1>" + currentPath + "</H1>");

        sb.append(viewFile(currentPath));


        sb.append("</body>");
        sb.append("</html>");
        resp.getWriter().write(sb.toString());
    }

    private StringBuilder viewFile(Path path) {
        File file = new File(path.toUri());
        StringBuilder stringBuilder = new StringBuilder();

        if (file.exists()) {
            stringBuilder.append("<textarea name=\"textFile\" id=\"textFile\" cols=\"100\" rows=\"10\" wrap=\"hard\">");
            try {
                FileReader fileReader = new FileReader(file);
                int s;
                while ((s=fileReader.read())!=-1){
                    stringBuilder.append((char)s);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append("</textarea>");
        }
        return stringBuilder;
    }

}
