package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@Entity
 class YourEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column
    private String name;

    // Default constructor
    public YourEntity() {
    }

    // Constructor with name parameter
    public YourEntity(String name) {
        this.name = name;
    }

    // Getter and setter methods for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

@Repository
 interface YourEntityRepository extends JpaRepository<YourEntity, Long> {
}

@RestController
@RequestMapping("/data")
class YourEntityController {

    private final YourEntityRepository repository;

    @Autowired
    public YourEntityController(YourEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<YourEntity> getAllData() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<YourEntity> addData(@RequestBody YourEntity entity) {
        YourEntity savedEntity = repository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }
}
	