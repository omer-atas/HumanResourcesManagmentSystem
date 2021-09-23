package logdef.humanResourcesManagementSystem.entities.concretes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name="social_medias")
@AllArgsConstructor
@NoArgsConstructor

public class SocialMedia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="social_media_id")
	private int socialMediaId;
	
	@Column(name="github",unique = true)
	private String github;
	
	@Column(name="linkedin",unique = true)
	private String linkedin;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "employee_id",nullable = true)
    private Employees employeesSocialMedia;
}
