package cz.cvut.fit.zatlodan.controllers.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jack on 20.12.16.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @ModelAttribute
    void addAttribute(Model m, HttpServletRequest request) {
        m.addAttribute("title", "Customers");
        m.addAttribute("basePath", request.getContextPath());
        String referer = request.getHeader("Referer");
        m.addAttribute("backLink", referer);
    }

    @GetMapping("")
    public String index(Model m) {
        return "index";
    }

}
