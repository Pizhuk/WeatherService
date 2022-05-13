package controller;

import services.OpenWeatherService;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import static constants.ControllerConstants.HOURLY_INIT;


public class HourlyWeatherController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        OpenWeatherService openWeatherService = new OpenWeatherService();
        //get body(name of the city) and transform into UTF-8
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        body = new String(body.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        //send response in view of JSON-format
        resp.getWriter().write(openWeatherService.getHourlyInfo(body));
    }

    @Override
    public void init(ServletConfig config){System.out.println(HOURLY_INIT);}
}
