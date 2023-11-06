package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CoffeeApiController {

    @Autowired
    private CoffeeService coffeeService;

    //GET
    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return coffeeService.index();
    }

    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id) {
        return coffeeService.show(id);
    }

    //POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto dto) {
        Coffee created = coffeeService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto) {
        Coffee updated = coffeeService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee deleted = coffeeService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    @Autowired
//    private CoffeeRepository coffeeRepository;
//    //GET
//    @GetMapping("/api/coffees")
//    public List<Coffee> index() {
//        return coffeeRepository.findAll();
//    }
//
//    @GetMapping("/api/coffees/{id}")
//    public Coffee show(@PathVariable Long id) {
//        return coffeeRepository.findById(id).orElse(null);
//    }
//
//    //POST
//    @PostMapping("/api/coffees")
//    public Coffee create(@RequestBody CoffeeDto dto) {
//        Coffee coffee = dto.toEntity();
//        return coffeeRepository.save(coffee);
//    }
//
//    //PATCH
//    @PatchMapping("/api/coffees/{id}")
//    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto) {
//        Coffee coffee = dto.toEntity();
//        log.info("id: {}, coffee: {}", id, coffee.toString());
//
//        Coffee target = coffeeRepository.findById(id).orElse(null);
//
//        if (target == null || id != coffee.getId()) {
//            log.info("잘못된 요청! id: {}, coffee: {}", id, coffee.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//
//        target.patch(coffee);
//        Coffee updated = coffeeRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//    //DELETE
//    @DeleteMapping("/api/coffees/{id}")
//    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
//        Coffee target = coffeeRepository.findById(id).orElse(null);
//
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//
//        coffeeRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
