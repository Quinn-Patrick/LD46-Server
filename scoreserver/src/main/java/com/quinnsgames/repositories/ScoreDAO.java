package com.quinnsgames.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.quinnsgames.models.Score;

@Transactional
public interface ScoreDAO extends CrudRepository<Score, Integer>{
	
	public List<Score> findAllByOrderByScoreDesc();
}
