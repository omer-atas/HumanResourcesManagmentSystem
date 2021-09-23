package logdef.humanResourcesManagementSystem.entities.concretes;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="general_informations")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","employees"})
public class GeneralInformation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="general_information_id")
    private int generalInformationId;
    
    @ManyToOne()
	@JoinColumn(name = "studied_department_id")
	private StudiedDepartment studiedDepartment;
    
    @ManyToOne()
	@JoinColumn(name = "studied_section_id")
	private StudiedSection studiedSection;

    @Column(name="superscription")
    @NotEmpty(message = "Ünvan alanı boş bırakılamaz..")
    private String superscription;

    @Column(name="started_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startedDate;

    @Column(name="telephone_number")
    @NotEmpty(message = "Telefon numarası alanı boş bırakılamaz..")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "(222) 222-2222")
    private String telephoneNumber;

    @Column(name="email")
    @NotEmpty(message = "Email alanı boş bırakılamaz..")
    @Email
    private String email;

    @ManyToOne()
   	@JoinColumn(name = "working_hour_id")
   	private WorkingHour workingHour;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "employee_id",nullable = true)
    private Employees employeesGeneralInformation;

}
