package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.stream.Collectors;
import services.UploadImagesService;
import static constants.ControllerConstants.UPLOAD_INIT;

public class UploadImgsController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UploadImagesService uploadImagesService = new UploadImagesService();
        //get the body(exp and photo material)
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        //process the info(save it on server) and send the response "OK" if everything is ok
        resp.getWriter().write(uploadImagesService.uploadImages(body));
    }

    @Override
    public void init(ServletConfig config) {System.out.println(UPLOAD_INIT);}
}
