package de.christianporsch.backend.security.model;

import de.christianporsch.backend.model.MagicCardInPile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "user")
public class AppUser {

    @Id
    private String username;
    private String password;
    @DBRef
    private List<MagicCardInPile> pileOfCards;
}
