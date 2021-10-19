package com.cortes.p2p.Services.DAO;

import com.cortes.p2p.Models.Project;
import com.cortes.p2p.Models.Tag;

import java.util.Set;

public interface TagService {
    Set<Project> getAllProjects(Set<Tag> tags);
}
