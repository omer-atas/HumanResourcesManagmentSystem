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
@Table(name="studied_sections")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","generalInformationsStudiedSection","studiedSectionIntern"})
public class StudiedSection {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="studied_sections_id")
    private int studiedSectionId;

	@Column(name="studied_section",unique = true)
	@NotEmpty(message = "Çalıştığı şube alanı boş bırakılamaz..")
	private String studiedSection;
	
	@JsonIgnore
	@OneToMany(mappedBy = "studiedSection")
	private List<GeneralInformation> generalInformationsStudiedSection;
	
	@JsonIgnore
 	@OneToMany(mappedBy = "studiedSectionIntern")
	private List<Intern> studiedSectionIntern;

}
