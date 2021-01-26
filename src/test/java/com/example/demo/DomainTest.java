package com.example.demo;

import com.example.demo.model.Owner;
import com.example.demo.model.Pet;
import com.example.demo.model.Type;
import com.example.demo.model.Vet;
import com.example.demo.model.dto.OwnerDto;
import com.example.demo.repository.*;
import com.example.demo.repository.vet.VetRepository;
import com.example.demo.repository.owner.OwnerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
@DisplayName("전체 도메인 테스트")
@TestPropertySource("classpath:application.yml")
public class DomainTest {

    Pet pet1, pet2;

    Owner owner;

    Type type1, type2;

    Vet vet;

    @Autowired
    JPAQueryFactory query;

    @Autowired
    PetRepository petRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    SpecialtyRepository specialtyRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    VetSpecialtyRepository vetSpecialtyRepository;

    @Autowired
    VisitRepository visitRepository;

    @Autowired
    VetRepository vetRepository;

    @BeforeEach
    @DisplayName("초기 데이터 입력")
    void initTest() {

        pet1 = new Pet();
        pet2 = new Pet();

        pet1.setName("pet1");
        LocalDate birthDate = LocalDate.of(2019, 1, 2);
        pet1.setBirthDate(birthDate);

        pet2.setName("pet2");
        LocalDate birthDate2 = LocalDate.of(2018, 4, 12);
        pet2.setBirthDate(birthDate);

        petRepository.save(pet1);
        petRepository.save(pet2);

        pet1 = petRepository.findPetByName(pet1.getName());
        pet2 = petRepository.findPetByName(pet2.getName());

        owner = new Owner();
        owner.setFirstName("geunwoo");
        owner.setLastName("Baek");
        owner.AddPet(pet1);
        owner.AddPet(pet2);
        ownerRepository.save(owner);

        type1 = new Type();
        type2 = new Type();
        type1.setName("Dog");
        type2.setName("Cat");
        typeRepository.save(type1);
        typeRepository.save(type2);
        vet = new Vet();

        // pet1.setType(type1);
        // pet2.setType(type2);

    }

    @Test
    @DisplayName(" 팻 도메인 및 레퍼지토리 테스트")
    void PetTest() {
        assert petRepository.findPetByName(pet1.getName()).getName().matches(pet1.getName());
        assert petRepository.findPetByName(pet2.getName()).getName().matches(pet2.getName());
    }

    @Test
    @DisplayName(" 주인 테스트")
    void OwnerTest() {
        assert ownerRepository.findOwnerByLastName("Baek").getFirstName().matches("geunwoo");
    }

    @Test
    @DisplayName(" 주인:팻 조인 테스트")
    void JoinTest() {
        List<OwnerDto> OwnerDtos = ownerRepository.findAllPetByOwner();
        OwnerDto ownerDto = OwnerDtos.get(0);
        assert ownerDto.getFirstName().matches("geunwoo");
        assert ownerDto.getLastName().matches("Baek");
        assert ownerDto.getPets().contains("pet1");
        assert ownerDto.getPets().contains("pet2");

    }
}