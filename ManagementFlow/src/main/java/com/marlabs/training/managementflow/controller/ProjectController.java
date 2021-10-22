package com.marlabs.training.managementflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.training.managementflow.entity.Project;
import com.marlabs.training.managementflow.service.ProjectService;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {


	@Autowired
	private ProjectService projectService;

	@PostMapping(value = "/")
	public Project createProject(@RequestBody Project project) {
		return projectService.createProject(project);
	}

	@GetMapping(value = "/projects")
	public List<Project> getAllProject() {
		return projectService.findByArchived(false);
	}

	@PutMapping(value = "/{id}")
	public Project updateProjectById(@PathVariable Long id) {
		return projectService.updateProjectById(id);
	}
}
