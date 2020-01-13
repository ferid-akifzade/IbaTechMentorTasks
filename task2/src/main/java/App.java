import libs.TemplateEngine;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.CalculatorService;
import servlet.CalculatorServlet;
import servlet.HistoryServlet;

public class App {
    public static void main(String[] args) throws Exception {
        Server server = new Server(9001);
        ServletContextHandler handler = new ServletContextHandler();

        CalculatorService service = new CalculatorService();
        TemplateEngine engine = new TemplateEngine("./task2/src/main/resources");
        handler.addServlet(new ServletHolder(new HistoryServlet(engine)), "/history");
        handler.addServlet(new ServletHolder(new CalculatorServlet(service, engine)), "/");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
