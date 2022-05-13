package controller;

import services.DirService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static constants.ControllerConstants.LIST_INIT;

public class SendImgListController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DirService dirService = new DirService();
        //get body(name of the city) and transform into UTF-8
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(body);
//        body = new String(body.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        //send response in view of JSON-format
        String str = dirService.getFilesList();
        System.out.println(str);
        resp.getWriter().write(str);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {System.out.println(LIST_INIT);}

}
