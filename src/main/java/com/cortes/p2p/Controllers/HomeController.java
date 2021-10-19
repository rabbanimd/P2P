package com.cortes.p2p.Controllers;

import com.cortes.p2p.Models.Project;
import com.cortes.p2p.Models.Tag;
import com.cortes.p2p.Models.User;
import com.cortes.p2p.Services.DAO.TagService;
import com.cortes.p2p.Services.DAO.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @GetMapping("/{userid}")
    public ResponseEntity getAllProjects(@PathVariable("userid") Long userid)
    {
        User user = userService.getUser(userid);
        Set<Tag> tags = user.getTags();
        Set<Project> projects = tagService.getAllProjects(tags);
        return new ResponseEntity(projects, HttpStatus.OK);

    }

}
