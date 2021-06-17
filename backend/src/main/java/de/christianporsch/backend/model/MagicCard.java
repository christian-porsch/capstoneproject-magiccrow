package de.christianporsch.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagicCard {

    private String id;
    private String name;
    private String oracleText;
    private String imageUri;
    private Price price;

}
