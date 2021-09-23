package logdef.humanResourcesManagementSystem.entities.concretes;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="project_intern")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","report"})
public class ProjectIntern {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_intern_id")
	private int projectInternId;
	
	@Column(name="project_name")
	@NotEmpty(message = "Proje alanı boş bırakılamaz..")
	private String projectName;
	
	@Column(name="source_code")
	@NotEmpty(message = "Projenin kaynak kod alanı boş bırakılamaz..")
	private String sourceCode;
	
	@ManyToOne()
	@JoinColumn(name = "intern_id")
	private Intern projectIntern;
	
	@JsonIgnore
	@OneToMany(mappedBy = "projectIntern")
	private List<Report> report;
}
