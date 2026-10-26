package mk.ukim.finki.wp.lab.web.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.WorkshopRepository;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name="WorkshopListServlet", urlPatterns = "/" )
public class WorkshopListServlet extends HttpServlet {

    private final WorkshopService workshopService;
    private final SpringTemplateEngine  templateEngine;

    public WorkshopListServlet(WorkshopService workshopService,
                               SpringTemplateEngine templateEngine) {
        this.workshopService = workshopService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String text = req.getParameter("text");
        if (text != null && text.isBlank()) {
            text = null;
        }

        Double maxPrice = null;
        String maxPriceParam = req.getParameter("maxPrice");
        if (maxPriceParam != null && !maxPriceParam.isBlank()) {
            try {
                maxPrice = Double.parseDouble(maxPriceParam);
            } catch (NumberFormatException ignored) {
            }
        }

        List<Workshop> workshops;
        if (text != null || maxPrice != null) {
            workshops = workshopService.search(text, maxPrice);
        } else {
            workshops = workshopService.listAll();
        }

        //context.setVariable("workshops", workshopService.listAll());
        context.setVariable("workshops", workshops);
        context.setVariable("errorMessage", req.getParameter("errorMessage"));

        resp.setContentType("text/html; charset=UTF-8");
        templateEngine.process("listWorkshops", context, resp.getWriter());
    }
}
