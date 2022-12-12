package com.yb.mongodb.mapper;

import com.yb.mongodb.model.Project;
import com.yb.mongodb.model.dto.ProjectDTO;

public interface ProjectMapper {

    Project mapToEntity(ProjectDTO projectDTO, Project project);

    ProjectDTO mapToDTO(Project project, ProjectDTO projectDTO);
}
