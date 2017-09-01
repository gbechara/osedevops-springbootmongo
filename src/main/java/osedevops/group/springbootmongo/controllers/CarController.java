package osedevops.group.springbootmongo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import osedevops.group.springbootmongo.model.Car;
import osedevops.group.springbootmongo.repository.CarMongoRepository;
import osedevops.group.springbootmongo.repository.CarSearchRepository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.Collections;
import java.util.List;


@Controller
public class CarController {

    @Autowired
    CarMongoRepository carRepository;

    @Autowired
    CarSearchRepository carSearchRepository;

    @RequestMapping("/")
    public String home() {
        return "redirect:home";
    }
    
    @RequestMapping("/home")
    @HystrixCommand(groupKey="ListCars", fallbackMethod = "getFallback")
    public String home(Model model) {
        model.addAttribute("carList", carRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public String addCar(@ModelAttribute Car car) {
        carRepository.save(car);
        return "redirect:home";
    }

    @RequestMapping(value = "/search")
    public String search(Model model, @RequestParam String search) {
        model.addAttribute("carList", carSearchRepository.searchCars(search));
        model.addAttribute("search", search);
        return "home";
    }
    
    public List<Car> getFallback(Integer StartPosition, Integer maxResult) {
		Car car = new Car();
		car.setModel("Fallback");
		return Collections.singletonList(car);
	}

}