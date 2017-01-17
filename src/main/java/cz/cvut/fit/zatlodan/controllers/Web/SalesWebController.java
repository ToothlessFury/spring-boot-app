/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.zatlodan.controllers.Web;

import cz.cvut.fit.zatlodan.controllers.REST.CustomersRestController;
import cz.cvut.fit.zatlodan.controllers.REST.SalesRestController;
import cz.cvut.fit.zatlodan.models.Customer;
import cz.cvut.fit.zatlodan.models.Sale;
import cz.cvut.fit.zatlodan.routing.Routes;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zatlodan
 */
@Controller
public class SalesWebController {

    @Autowired
    SalesRestController salesControll;
    @Autowired
    CustomersRestController custs;

    @ModelAttribute
    void addAttribute(Model m, HttpServletRequest request) {
        m.addAttribute("title", "Sales");
        m.addAttribute("basePath", request.getContextPath());
        String referer = request.getHeader("Referer");
        m.addAttribute("backLink", referer);
    }

    @GetMapping(Routes.SALES + "/{id}")
    public String details(@PathVariable Long id, Model m) {
        Sale s = salesControll.findById(id);
        m.addAttribute("sale", s);
        return "sales/detail";
    }

    @GetMapping(Routes.SALES)
    public String all(Model m) {
        Iterable<Sale> sales = salesControll.all();
        ArrayList l = new ArrayList();
        for (Sale s : sales) {
            l.add(s);
        }
        m.addAttribute("sales", l);
        return "sales/index";
    }

    @GetMapping(Routes.SALES_CREATE)
    public String createPage(Model m) {
        m.addAttribute("customers", custs.all());
        return "sales/create";
    }

    @GetMapping(Routes.SALES_DELETE + "/{id}")
    public String deletePage(@PathVariable Long id, Model m) {
        Sale s = salesControll.findById(id);
        m.addAttribute("sale", s);
        return "sales/delete";
    }

    @GetMapping(Routes.SALES_UPDATE + "/{id}")
    public String updatePage(@PathVariable Long id, Model m) {
        Sale s = salesControll.findById(id);
        m.addAttribute("item", s);
        m.addAttribute("customers", custs.all());
        String S = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(s.getDate()).replace(" ", "T");
        m.addAttribute("formattedDate", S);
        return "sales/update";
    }

    @PostMapping(Routes.SALES_CREATE)
    public String create(
            @RequestParam(name = "info") String info,
            @RequestParam(name = "date") String date,
            @RequestParam(name = "status") char status,
            @RequestParam(name = "custid") Long custId
    ) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        if (!date.isEmpty()) {
            if (date.length() < 19) {
                date += ":00";
            }
            ts = Timestamp.valueOf(date.replace("T", " "));
        }
        salesControll.create(info, ts, status, custId);
        return "redirect:" + Routes.SALES;
    }

    @PostMapping(Routes.SALES_DELETE)
    public String delete(@RequestParam(name = "id", required = true) Long id) {
        salesControll.deleteById(id);
        return "redirect:" + Routes.SALES;
    }

    @PostMapping(Routes.SALES_UPDATE)
    public String update(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "info") String info,
            @RequestParam(name = "date") String date,
            @RequestParam(name = "status") char status,
            @RequestParam(name = "custid") Long custId) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        if (!date.isEmpty()) {
            if (date.length() < 19) {
                date += ":00";
            }
            ts = Timestamp.valueOf(date.replace("T", " "));
        }
        salesControll.update(id, info, ts, status, custId);
        return "redirect:" + Routes.SALES;
    }

}
