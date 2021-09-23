package logdef.humanResourcesManagementSystem.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="reports")
@AllArgsConstructor
@NoArgsConstructor
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="report_id")
	private int reportId;
	
	@Column(name="report_name")
	@NotEmpty(message = "Rapor adı alanı boş bırakılamaz..")
	private String reportName;
	
	@Column(name="report_field")
	@NotEmpty(message = "Rapor alanı boş bırakılamaz..")
	private String reportField;
	
	@JsonIgnore
	@Column(name="report_create_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-M-yyyy")
	private LocalDateTime  createDate  = LocalDateTime.now();
	
	@ManyToOne()
	@JoinColumn(name = "project_intern_id")
	private ProjectIntern projectIntern;

}
