package com.cortes.p2p.Controllers;

import com.cortes.p2p.DTO.ProjectDTO;
import com.cortes.p2p.Models.Project;
import com.cortes.p2p.Models.User;
import com.cortes.p2p.Services.DAO.ProjectService;
import com.cortes.p2p.Services.DAO.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity fetchAllproject(@PathVariable("id") Long id)
    {
        User user;
        try {
            user = userService.getUser(id);
        }catch (Exception e)
        {
            System.out.println("invalid id "+id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        Set<Project> projects = projectService.getAllMyProjects(user);
        return new ResponseEntity(projects, HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity addNewProject(@RequestBody ProjectDTO projectDTO, @PathVariable("id") Long id)
    {
        User user;
        try {
            user = userService.getUser(id);
        }catch (Exception e)
        {
            System.out.println("invalid id "+id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        Project project;
        try{
            project = projectService.addNewProject(projectDTO, user);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        if(project == null)
//        {
//            return ResponseEntity.internalServerError().build();
//        }
        return new ResponseEntity(project, HttpStatus.CREATED);
    }
}
