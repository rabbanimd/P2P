package com.cortes.p2p.Services.Impl;

import com.cortes.p2p.DTO.ProjectDTO;
import com.cortes.p2p.Models.Project;
import com.cortes.p2p.Models.Tag;
import com.cortes.p2p.Models.User;
import com.cortes.p2p.Repositories.ProjectRepository;
import com.cortes.p2p.Repositories.TagRepository;
import com.cortes.p2p.Repositories.UserRepository;
import com.cortes.p2p.Services.DAO.ProjectService;
import com.cortes.p2p.Services.DAO.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Project addNewProject(ProjectDTO projectDTO, User user) {
        Project project = new Project();
        project.setTitle(projectDTO.getTitle());
        project.setDescription(projectDTO.getDescription());
        project.setProjectLink(projectDTO.getProjectLink());
        project.setImageLink(project.getImageLink());
        project.setDeadLine(projectDTO.getDeadLine());
        for(String x:projectDTO.getFtags())
        {
            Tag tag = tagRepository.findByName(x.toUpperCase());
            if(tag == null)
            {
                tag = new Tag();
                tag.setName(x.toUpperCase());
                tagRepository.save(tag);
            }
            tag.getProjects().add(project);
            tag.incrementHits();
            project.getTags().add(tag);
        }

        user.getMyProjects().add(project);
        project.setAuthor(user);
        Project saved = projectRepository.save(project);
        return saved;
    }

    @Override
    public Set<Project> getAllMyProjects(User user) {
        Set<Project> myProjects = user.getMyProjects();
        return myProjects;
    }
}
