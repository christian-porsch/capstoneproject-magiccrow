package de.christianporsch.backend.repository;

import de.christianporsch.backend.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserPileRepository extends PagingAndSortingRepository<User, String> {

    User findUserById(String id);
}
