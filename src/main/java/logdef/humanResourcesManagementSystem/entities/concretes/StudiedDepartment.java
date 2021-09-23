package logdef.humanResourcesManagementSystem.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="studied_departments")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","generalInformationsStudiedDepartment","studiedDepartmentIntern"})
public class StudiedDepartment {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="studied_department_id")
	    private int studiedDepartmentId;

	 	@Column(name="studied_department",unique = true)
	    @NotEmpty(message = "Studied Department field cannot be left blank..")
	    private String studiedDepartment;
	 	
	 	@JsonIgnore
	 	@OneToMany(mappedBy = "studiedDepartment")
		private List<GeneralInformation> generalInformationsStudiedDepartment;
	 	
	 	@JsonIgnore
	 	@OneToMany(mappedBy = "studiedDepartmentIntern")
		private List<Intern> studiedDepartmentIntern;
}
