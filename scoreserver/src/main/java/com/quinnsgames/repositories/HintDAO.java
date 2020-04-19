package com.quinnsgames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quinnsgames.models.Hint;

public interface HintDAO extends JpaRepository<Hint, Integer>{

}
