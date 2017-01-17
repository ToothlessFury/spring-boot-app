package cz.cvut.fit.zatlodan.repositories;

import cz.cvut.fit.zatlodan.models.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by jack on 18.12.16.
 */
@Repository
public interface CustomersRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByName(String name);

    Customer findById(Long id);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("update Customer c set c.name = ?2, c.address = ?3, c.emailAddress = ?4, c.contact = ?5 where c.id = ?1")
    int update(Long id, String name, String address, String emailAddress, String contact);
}
