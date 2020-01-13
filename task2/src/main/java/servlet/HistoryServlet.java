package servlet;

import dao.CalculationDAO;
import libs.Result;
import libs.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryServlet extends HttpServlet {

    private final TemplateEngine engine;
    private CalculationDAO calculationDAO;
    private Map<String, Object> data;
    public HistoryServlet(TemplateEngine engine) {
        data = new HashMap<>();
        this.calculationDAO = new CalculationDAO();
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Result> database = calculationDAO.getDatabase();
        data.put("results",database);
        engine.render("history.ftl",data,resp);
    }
}
