package com.quinnsgames.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinnsgames.models.Hint;
import com.quinnsgames.models.Score;
import com.quinnsgames.repositories.HintDAO;
import com.quinnsgames.repositories.ScoreDAO;

@CrossOrigin
@RestController
@RequestMapping(value="/score")
public class ScoreController {
	@Autowired
	private ScoreDAO dao;
	
	@Autowired
	private HintDAO hDao;
	
	@GetMapping("/topten")
	public ResponseEntity<List<Score>> topTen() {
		List<Score> topten = new ArrayList<>();
		topten = ((List<Score>) dao.findAllByOrderByScoreDesc());
		int end = 0;
		if(topten.size() < 10) end = topten.size();
		else end = 10;
		topten = topten.subList(0, end);
		
		return ResponseEntity.ok().body(topten);
	}
	
	@GetMapping(value = "/addscore/{name}/{score}")
	public ResponseEntity<Score> addScore(@PathVariable("name") String name, @PathVariable("score") long score) {
		Score s = new Score(name, score);
		dao.save(s);
		return ResponseEntity.status(HttpStatus.CREATED).body(s);
	}
	
	@PostMapping
	public ResponseEntity<Score> submitScore(@RequestBody Score s){
		System.out.println("made it here");
		System.out.println(s);
		if(s.getName() == null) s.setName("No Name");
		dao.save(s);
		return ResponseEntity.status(HttpStatus.CREATED).body(s);
	}
	
	@GetMapping("/randomhint")
	public ResponseEntity<Hint> randHint() {
		List<Hint> hints = new ArrayList<>();
		hints = ((List<Hint>) hDao.findAll());
		
	    Random rand = new Random();
	    Hint randomElement = hints.get(rand.nextInt(hints.size()));
		
		return ResponseEntity.ok().body(randomElement);
	}
	
	@GetMapping("/{name}/{content}")
	public ResponseEntity<Hint> addHint(@PathVariable("name") String name, @PathVariable("content") String content) {
		Hint h = new Hint(name, content);
		hDao.save(h);
		return ResponseEntity.status(HttpStatus.CREATED).body(h);
	}

}
