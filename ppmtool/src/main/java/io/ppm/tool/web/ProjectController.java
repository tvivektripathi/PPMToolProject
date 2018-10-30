package io.ppm.tool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ppm.tool.domain.Project;
import io.ppm.tool.services.MapValidationErrorService;
import io.ppm.tool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private MapValidationErrorService errorService;

	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> errorMap = errorService.mapValidationService(result);
		if (null != errorMap) {
			return errorMap;
		}
		return new ResponseEntity<>(projectService.saveUpdateProject(project), HttpStatus.CREATED);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
		Project projectResponse = projectService.findProjectByIdentifier(projectId.toUpperCase());
		return new ResponseEntity<>(projectResponse, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllProjects() {
		return new ResponseEntity<>(projectService.findAllProjects(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId){
		projectService.deleteProjectByIdentifier(projectId.toUpperCase());
		return new ResponseEntity<>("Project Id: "+projectId+" has been deleted.", HttpStatus.OK);
	}
}
