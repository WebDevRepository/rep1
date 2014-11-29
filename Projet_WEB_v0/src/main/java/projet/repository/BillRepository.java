package projet.repository;

import org.springframework.data.repository.CrudRepository;


import projet.model.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {

}
