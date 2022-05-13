package controller;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import telegrambot.TelegramBotSender;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static constants.ControllerConstants.CONTACT_PAGE_INIT;

public class GetMesFromContactPageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TelegramBotSender telegramBotSender = new TelegramBotSender(new DefaultBotOptions());
        //get body(name of the city) and transform into UTF-8
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        body = new String(body.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        telegramBotSender.sendMesFromContactPage(body);
        //send response in view of JSON-format
        resp.getWriter().write("str");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {System.out.println(CONTACT_PAGE_INIT);}
}
