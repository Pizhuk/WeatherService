package controller;

import services.OpenWeatherService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import static constants.ControllerConstants.CURRENT_INIT;

public class CurrentWeatherController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("norm1");
        OpenWeatherService openWeatherService = new OpenWeatherService();
        //get body(name of the city) and transform into UTF-8
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        body = new String(body.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println("norm2");
        //send response in view of JSON-format
        String str = openWeatherService.getCurrentInfo(body);
        System.out.println(str);
        resp.getWriter().write(str);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {System.out.println(CURRENT_INIT);}
}