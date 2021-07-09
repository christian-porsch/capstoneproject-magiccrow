package de.christianporsch.backend.repository;

import de.christianporsch.backend.model.MagicCard;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MagicCardRepository extends PagingAndSortingRepository<MagicCard, String> {

    @Query(value="{name: {$regex:'.*?0.*',$options:'i'}}")
    List<MagicCard> filterCardsByCardName(String cardName);

    Optional<MagicCard> findMagicCardById(String id);

}
