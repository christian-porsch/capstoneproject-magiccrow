package de.christianporsch.backend.security.repository;

import de.christianporsch.backend.security.model.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends PagingAndSortingRepository<AppUser, String> {

}
