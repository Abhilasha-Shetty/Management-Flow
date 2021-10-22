package com.marlabs.training.managementflow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.marlabs.training.managementflow.dao.ProjectDao;
import com.marlabs.training.managementflow.entity.Project;
import com.marlabs.training.managementflow.entity.StatusEnum;
import com.marlabs.training.managementflow.service.ProjectService;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootTest
public class ProjectServiceTests {

	 LocalDateTime dateCreated =  LocalDateTime.now();
	
	@Autowired
	private ProjectService projectService;

	@MockBean
	private ProjectDao projectDao;

	// create project
	@Test
	public void createProjectTest() {
		Project project = new Project();
		project.setName("Test");
		when(projectDao.save(project)).thenReturn(project);
		assertEquals(project, projectService.createProject(project));
		
	    }
	

	// find project by archived
	@Test
	public void findByArchivedTest() {
		when(projectDao.findByArchived(false)).thenReturn(Stream
				.of(new Project(1L, "Test1", StatusEnum.FINISHED, dateCreated, false),
						new Project(2L, "Test2", StatusEnum.INPROGRESS, dateCreated, false))
				.collect(Collectors.toList()));
		assertEquals(2, projectService.findByArchived(false).size());
	}

	// update project
	@Test
	public void updateProjectByIdTest() {
		Project p=new Project(1L, "Test1", StatusEnum.FINISHED, dateCreated, false);
		when(projectDao.findById(1L)).thenReturn(Optional.ofNullable(p));
		when(projectDao.save(p)).thenReturn(p);
		Project p1=projectService.updateProjectById(p.getId());
		assertTrue(p1.isArchived());
	}
}