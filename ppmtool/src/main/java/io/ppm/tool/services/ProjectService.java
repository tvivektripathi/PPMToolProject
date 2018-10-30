package io.ppm.tool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ppm.tool.domain.Project;
import io.ppm.tool.exceptions.ProjectIdException;
import io.ppm.tool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveUpdateProject(Project project) {
		try {
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException(
					"Project Id: " + project.getProjectIdentifier().toUpperCase() + " already exist.");
		}

	}

	public Project findProjectByIdentifier(String projectId) {
		Project projectResponse = projectRepository.findByProjectIdentifier(projectId);
		if (null == projectResponse) {
			throw new ProjectIdException("Project Id: " + projectId.toUpperCase() + " does not exist.");
		}
		return projectResponse;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project projectResponse = findProjectByIdentifier(projectId);
		projectRepository.delete(projectResponse);
	}
}
