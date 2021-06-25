package de.christianporsch.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "allmagiccards")
public class MagicCard {

    @Id
    private String id;
    private String name;
    private String oracle_text;
    private CardImage image_uris;
    private String set_name;
    private Price prices;

}
