package main.java.hw8;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        StringBuilder sb = new StringBuilder();
//
//        sb.append("<html>");
//        sb.append("<head>");
//        sb.append("</head>");
//
//        sb.append("<body>");
//        sb.append("<H1>fdsfsf</H1>");
//        //TODO: Implement directory listing here
//        sb.append("</body>");
//        sb.append("</html>");
//        resp.getWriter().write(sb.toString());

        resp.sendRedirect("/dir/view");
    }
}
