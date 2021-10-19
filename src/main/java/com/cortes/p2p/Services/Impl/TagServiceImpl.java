package com.cortes.p2p.Services.Impl;

import com.cortes.p2p.Models.Project;
import com.cortes.p2p.Models.Tag;
import com.cortes.p2p.Repositories.TagRepository;
import com.cortes.p2p.Services.DAO.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Override
    public Set<Project> getAllProjects(Set<Tag> tags) {
        Set<Project> result = new HashSet<>();
        for(Tag t : tags)
        {
            result.addAll(t.getProjects());
        }
        return result;
    }
}
