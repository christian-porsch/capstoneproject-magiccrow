package de.christianporsch.backend.repository;

import de.christianporsch.backend.model.MagicCardInPile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MagicCardInPileRepository extends PagingAndSortingRepository<MagicCardInPile, String> {

    Optional<MagicCardInPile> findMagicCardInPileById(String id);

}
