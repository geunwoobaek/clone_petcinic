package com.example.demo.model;

import com.example.demo.model.comon.Person;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
@Getter
public class Vet extends Person {

	@OneToMany(mappedBy = "vet",cascade = CascadeType.ALL)
	Set<VetSpecialty> vetSpecialtyList = new HashSet<>();

	//연관관계 메서드
	public void addVetSpecialty(VetSpecialty vetSpecialty){
		vetSpecialtyList.add(vetSpecialty);
	}

	//
}
