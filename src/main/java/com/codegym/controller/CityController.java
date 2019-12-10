package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.CityService;
import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> countries() {
        return countryService.findAll();
    }

    @GetMapping("/cities")
    public ModelAndView listCities(Pageable pageable) {
        Page<City> cities = cityService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/city/create")
    public ModelAndView createCityForm() {
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/city/create")
    public ModelAndView saveCreateCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/city/create");
            return modelAndView;
        }
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("redirect:/cities");
        modelAndView.addObject("city", new City());
        modelAndView.addObject("message", "Create new City successfully");
        return modelAndView;
    }

    @GetMapping("/city/edit/{id}")
    public ModelAndView editCityForm(@PathVariable("id") Long id) {
        City city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/city/edit");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @PostMapping("/city/edit")
    public ModelAndView saveEditCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/city/edit");
            return modelAndView;
        }
        cityService.save(city);
        ModelAndView modelAndView = new ModelAndView("redirect:/cities");
        modelAndView.addObject("city", city);
        modelAndView.addObject("message", "Update successfully");
        return modelAndView;
    }

    @GetMapping("/city/delete/{id}")
    public ModelAndView deleteCityForm(@PathVariable("id") Long id) {
        City city = cityService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/city/delete");
        modelAndView.addObject("city",city);
        return modelAndView;
    }

    @PostMapping("/city/delete")
    public ModelAndView deleteCity(@ModelAttribute("city") City city, Pageable pageable) {
        cityService.remove(city.getId());

        ModelAndView modelAndView = new ModelAndView("redirect:/cities");
        modelAndView.addObject("cities", cityService.findAll(pageable));
        return modelAndView;
    }
}
