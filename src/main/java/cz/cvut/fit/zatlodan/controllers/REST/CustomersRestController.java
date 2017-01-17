package cz.cvut.fit.zatlodan.controllers.REST;

import cz.cvut.fit.zatlodan.models.Customer;
import cz.cvut.fit.zatlodan.repositories.CustomersRepository;
import cz.cvut.fit.zatlodan.routing.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jack on 18.12.16.
 */
@RestController
@RequestMapping(Routes.REST_URI)
public class CustomersRestController {

    @Autowired
    CustomersRepository repository;

    @RequestMapping(value = Routes.REST_CUSTOMERS, method = RequestMethod.GET)
    public Iterable<Customer> all() {
        return repository.findAll();
    }

    @RequestMapping(value = Routes.REST_CUSTOMERS + "/{id}", method = RequestMethod.GET)
    public Customer findById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @RequestMapping(value = Routes.REST_CUSTOMERS, method = RequestMethod.POST)
    public void create(@RequestParam(name = "name") String name,
                       @RequestParam(name = "emailAddress") String emailAddress, @RequestParam(name = "address") String address,
                       @RequestParam(name = "contact") String contact) {
        repository.save(new Customer(name, emailAddress, address, contact));
    }

    @RequestMapping(value = Routes.REST_CUSTOMERS + "/{id}", method = RequestMethod.PUT)
    public int update(@PathVariable Long id,
                      @RequestParam(name = "name") String name,
                      @RequestParam(name = "emailAddress") String emailAddress,
                      @RequestParam(name = "address") String address,
                      @RequestParam(name = "contact") String contact) {
        return repository.update(id, name, address, emailAddress, contact);
    }

    @RequestMapping(value = Routes.REST_CUSTOMERS + "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        System.out.println(id);
        repository.delete(id);
    }

}
