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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="working_hours")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","generalInformationWorkingHour"})
public class WorkingHour {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="working_hour_id")
    private int workingHourId;

	@Column(name="working_hour",unique = true)
    @NotEmpty(message = "Çalışma saati alanı boş bırakılamaz..")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "00:00 - 00:00")
    private String workingHour;
	
	@OneToMany(mappedBy = "workingHour")
	private List<GeneralInformation> generalInformationWorkingHour;

}
