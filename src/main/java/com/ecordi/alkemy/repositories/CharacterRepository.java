package com.ecordi.alkemy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecordi.alkemy.entities.Character;

public interface CharacterRepository extends JpaRepository<Character, Integer> {

}
