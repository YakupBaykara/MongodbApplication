package com.yb.mongodb.mapper;

import com.yb.mongodb.model.Model;
import com.yb.mongodb.model.dto.ModelDTO;

public interface ModelMapper {

    Model mapToEntity(ModelDTO modelDTO, Model model);

    ModelDTO mapToDTO(Model model, ModelDTO modelDTO);
}
