package com.develope.exercise8;

import com.develope.exercise8.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping("/createCar")
    public Car createCar (@RequestBody Car car){
        Car createdCar= carRepository.save(car);
        return createdCar;
    }

    @GetMapping("/carList")
    public List<Car> carList(){
        return carRepository.findAll();
    }

    @GetMapping("/carByID/{id}")
    public Optional<Car> car (@PathVariable long id){
        if (carRepository.existsById(id)){
           return carRepository.findById(id);
        }
        return Optional.empty();
    }

    @PutMapping("/updateType/{id}")
    public Optional<Car> updateType (@PathVariable long id, @RequestBody String type){
        if (carRepository.existsById(id)){
            Optional<Car> car = carRepository.findById(id);
            return car;
        }
        return null;
    }

    @DeleteMapping("/deleteCar:{id}")
    public void deleteCar(@PathVariable long id){
        if (carRepository.existsById(id)){
        carRepository.deleteById(id);
        }else {
            System.out.println("Conflict HTTP status");
        }
    }

    @DeleteMapping("/deleteList")
    public void deleteList(){
        carRepository.deleteAll();
    }
}
