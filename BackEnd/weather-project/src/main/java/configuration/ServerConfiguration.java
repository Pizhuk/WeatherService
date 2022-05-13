package configuration;

import controller.*;
import filter.CORSFilter;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ServerConfiguration {
    public Server buildServer(){
        Server server = new Server();
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(8090);
        server.setConnectors(new ServerConnector[] {serverConnector});

        CurrentWeatherController currentWeatherController = new CurrentWeatherController();
        HourlyWeatherController hourlyWeatherController = new HourlyWeatherController();
        TomorrowWeatherController tomorrowWeatherController = new TomorrowWeatherController();
        WeekWeatherController weekWeatherController = new WeekWeatherController();
        UploadImgsController uploadImgsController = new UploadImgsController();
        SendImgListController sendImgListController = new SendImgListController();
        GetMesFromContactPageController getMesFromContactPageController = new GetMesFromContactPageController();

        ServletContextHandler contextHandler = new ServletContextHandler();
        ServletHolder currentWeatherHolder = new ServletHolder("currentWeatherHolder", currentWeatherController);
        ServletHolder hourlyWeatherHolder = new ServletHolder("hourlyWeatherHolder", hourlyWeatherController);
        ServletHolder tomorrowWeatherHolder = new ServletHolder("tomorrowWeatherHolder", tomorrowWeatherController);
        ServletHolder weekWeatherHolder = new ServletHolder("weekWeatherHolder", weekWeatherController);
        ServletHolder uploadHolder = new ServletHolder("uploadHolder", uploadImgsController);
        ServletHolder sendImgListHolder = new ServletHolder("sendList", sendImgListController);
        ServletHolder getMesFromContactPageHolder = new ServletHolder("getMesFromContactPage", getMesFromContactPageController);

        contextHandler.addServlet(currentWeatherHolder, "/current");
        contextHandler.addServlet(hourlyWeatherHolder, "/hourly");
        contextHandler.addServlet(tomorrowWeatherHolder, "/tomorrow");
        contextHandler.addServlet(weekWeatherHolder, "/week");
        contextHandler.addServlet(uploadHolder, "/upload");
        contextHandler.addServlet(sendImgListHolder, "/list");
        contextHandler.addServlet(getMesFromContactPageHolder, "/contactUs");
        contextHandler.addFilter(CORSFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        HandlerCollection hc = new HandlerCollection();
        hc.setHandlers(new Handler[]{contextHandler});
        server.setHandler(hc);
        return server;
    }
}
