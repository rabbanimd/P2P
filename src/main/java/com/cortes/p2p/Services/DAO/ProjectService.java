package com.cortes.p2p.Services.DAO;

import com.cortes.p2p.DTO.ProjectDTO;
import com.cortes.p2p.Models.Project;
import com.cortes.p2p.Models.User;

import java.util.List;
import java.util.Set;

public interface ProjectService {
    Project addNewProject(ProjectDTO projectDTO, User user);
    Set<Project> getAllMyProjects(User user);
}
