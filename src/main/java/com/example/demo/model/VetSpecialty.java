package com.example.demo.model;

import com.example.demo.model.comon.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "vet_specialties")
public class VetSpecialty extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "vet_id")
	Vet vet;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "specialties_id")
	Specialty specialty;

	@Builder
	public VetSpecialty(Vet vet, Specialty specialty) {
		this.vet = vet;
		vet.addVetSpecialty(this);
		this.specialty = specialty;
		specialty.addVetSpecialty(this);
	}

}
