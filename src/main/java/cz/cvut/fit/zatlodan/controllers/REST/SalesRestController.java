/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.zatlodan.controllers.REST;

import cz.cvut.fit.zatlodan.models.Customer;
import cz.cvut.fit.zatlodan.models.Sale;
import cz.cvut.fit.zatlodan.repositories.CustomersRepository;
import cz.cvut.fit.zatlodan.repositories.SalesRepository;
import cz.cvut.fit.zatlodan.routing.Routes;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zatlodan
 */
@RestController
@RequestMapping(Routes.REST_URI)
public class SalesRestController {

    @Autowired
    SalesRepository repository;
    @Autowired
    CustomersRepository crep;

    @RequestMapping(value = Routes.REST_SALES, method = RequestMethod.GET)
    public Iterable<Sale> all() {
        return repository.findAll();
    }

    @RequestMapping(value = Routes.REST_SALES + "/{id}", method = RequestMethod.GET)
    public Sale findById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @RequestMapping(value = Routes.REST_SALES, method = RequestMethod.POST)
    public void create(
            @RequestParam(name = "info") String info,
            @RequestParam(name = "date") Timestamp date,
            @RequestParam(name = "status") char status,
            @RequestParam(name = "custId") Long custId) {
        Customer c = crep.findById(custId);
        Sale s = new Sale(info, date, status, custId);
        repository.save(s);
        c.addSale(s);
        crep.save(c);
    }

    @RequestMapping(value = Routes.REST_SALES + "/{id}", method = RequestMethod.PUT)
    public int update(
            @PathVariable Long id, @RequestParam(name = "info") String info,
            @RequestParam(name = "date") Timestamp date,
            @RequestParam(name = "status") char status,
            @RequestParam(name = "custId") Long custId) {
        return repository.update(id, info, date, status, custId);
    }

    @RequestMapping(value = Routes.REST_SALES + "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        repository.delete(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
