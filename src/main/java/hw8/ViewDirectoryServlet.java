package main.java.hw8;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(value = "/dir/view", initParams = {
        @WebInitParam(name = "root", value = "C:\\")
})
public class ViewDirectoryServlet extends HttpServlet {

    private static Path ROOT_PATH;
    private String reqParameter;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ROOT_PATH = Paths.get(config.getInitParameter("root"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<body>");
        Path currentPath;
        reqParameter = req.getParameter("path");

        if (reqParameter == null) {
            currentPath = ROOT_PATH;
        } else {
            currentPath = Paths.get(reqParameter);
        }
        sb.append("<H1>" + currentPath + "</H1>");

        sb.append(viewDirectory(currentPath));

        sb.append("<form action=/file/create method=GET>");
        sb.append("<input type=text name=path value="+currentPath+">");
        sb.append("<input type=text name=fileName placeholder=EnterFileName>");
        sb.append("<input type=submit id=sub value=Create>");
        sb.append("</form");

        sb.append("</body>");

        sb.append("</html>");
        resp.getWriter().write(sb.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private StringBuilder viewDirectory(Path path) {
        File files = new File(String.valueOf(path));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        for (File inerFile : files.listFiles()) {
            stringBuilder.append("<tr>");

            if (inerFile.isDirectory()) {
                stringBuilder.append("<td></td>");
                stringBuilder.append("<td><a href=\"/dir/view?path=");
            }
            if (inerFile.isFile()) {
                stringBuilder.append("<td><a href=\"/file/remove?path="+inerFile.getAbsolutePath()+"\">Del</a></td>");
                stringBuilder.append("<td><a href=\"/file/view?path=");
            }
            stringBuilder.append(inerFile.getAbsolutePath() + "\">" + inerFile.getName() + "</a></td>");
            stringBuilder.append("</tr>");

        }
        stringBuilder.append("</table>");
        return stringBuilder;
    }


}
