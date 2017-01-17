package cz.cvut.fit.zatlodan.controllers.Web;

import cz.cvut.fit.zatlodan.controllers.REST.CustomersRestController;
import cz.cvut.fit.zatlodan.models.Customer;
import cz.cvut.fit.zatlodan.repositories.CustomersRepository;
import cz.cvut.fit.zatlodan.routing.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by jack on 19.12.16.
 */
@Controller
public class CustomersWebController {

    @Autowired
    CustomersRestController custcontroller;

    @ModelAttribute
    void addAttribute(Model m, HttpServletRequest request) {
        m.addAttribute("title", "Customers");
        m.addAttribute("basePath", request.getContextPath());
        String referer = request.getHeader("Referer");
        m.addAttribute("backLink", referer);
    }

    @GetMapping(Routes.CUSTOMERS + "/{id}")
    public String details(@PathVariable Long id, Model m) {
        Customer c = custcontroller.findById(id);
        m.addAttribute("customer", c);
        return "customers/detail";
    }

    @GetMapping(Routes.CUSTOMERS)
    public String allPage(Model m) {
        Iterable<Customer> customers = custcontroller.all();
        ArrayList l = new ArrayList();
        for (Customer c : customers) {
            l.add(c);
        }
        m.addAttribute("customers", l);
        return "customers/index";
    }

    @GetMapping(Routes.CUSTOMERS_DELETE + "/{id}")
    public String deletePage(@PathVariable Long id, Model m) {
        Customer c = custcontroller.findById(id);
        m.addAttribute("customer", c);
        return "customers/delete";
    }

    @GetMapping(Routes.CUSTOMERS_UPDATE + "/{id}")
    public String updatePage(@PathVariable Long id, Model m) {
        Customer c = custcontroller.findById(id);
        m.addAttribute("item", c);
        return "customers/update";
    }

    @GetMapping(Routes.CUSTOMERS_CREATE)
    public String createPage(Model m) {
        return "customers/create";
    }

    @PostMapping(Routes.CUSTOMERS_DELETE)
    public String delete(@RequestParam(name = "id", required = true) Long id) {
        custcontroller.deleteById(id);
        return "redirect:" + Routes.CUSTOMERS;
    }

    @PostMapping(Routes.CUSTOMERS_CREATE)
    public String create(@RequestParam(name = "name", required = true) String name,
                         @RequestParam(name = "emailAddress", required = false) String emailAddress,
                         @RequestParam(name = "address", required = true) String address,
                         @RequestParam(name = "contact", required = false) String contact
    ) {
        custcontroller.create(name, emailAddress, address, contact);
        return "redirect:" + Routes.CUSTOMERS;
    }

    @PostMapping(Routes.CUSTOMERS_UPDATE)
    public String update(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "emailAddress", required = false) String emailAddress,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "contact", required = false) String contact) {
        custcontroller.update(id, name, emailAddress, address, contact);
        return "redirect:" + Routes.CUSTOMERS;
    }

}
