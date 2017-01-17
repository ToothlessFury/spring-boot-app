package cz.cvut.fit.zatlodan.repositories;

import cz.cvut.fit.zatlodan.models.Sale;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jack on 19.12.16.
 */
@Repository
public interface SalesRepository extends CrudRepository<Sale, Long> {


    Sale findById(Long id);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("update Sale s set s.info = ?2, s.date = ?3, s.done = ?4, s.customersId = ?5 where s.id = ?1")
    int update(Long id, String info, Timestamp date, char status, Long custId);

}
