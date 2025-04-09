package org.example.data;

import org.example.model.business.Color;
import org.example.model.business.Pawn;

public class InfoPlayerConvertor implements Convertor<Pawn, InfoPlayerPOJO> {

    @Override
    public Pawn fromDTO(InfoPlayerPOJO dto) {
        return new Pawn(dto.getName(), dto.getScore(), dto.getId(), Color.getColor(dto.getColor()), dto.getPosition());
    }

    @Override
    public InfoPlayerPOJO toDTO(Pawn element) {
        InfoPlayerPOJO dto = new InfoPlayerPOJO();
        dto.setName(element.getName());
        dto.setScore(element.getScore());
        dto.setId(element.getId());
        dto.setColor(element.getColor().getValue());
        dto.setPosition(element.getPosition());
        return dto;
    }
}
