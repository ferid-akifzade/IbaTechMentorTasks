package servlet;

import libs.TemplateEngine;
import service.CalculatorService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CalculatorServlet extends HttpServlet {

    private final CalculatorService service;
    private final TemplateEngine engine;
    private Map<String, Object> data;
    public CalculatorServlet(CalculatorService service, TemplateEngine engine) {
        data = new HashMap<>();
        data.put("result","");
        this.service = service;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        engine.render("index.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String first = req.getParameter("first");
        String second = req.getParameter("second");
        String operation = req.getParameter("operation");
        data.put("result",String.format("Result : %s",service.calculate(first, second, operation)));
        resp.sendRedirect("/");
    }
}
