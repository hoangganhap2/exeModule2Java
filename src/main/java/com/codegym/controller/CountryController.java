package com.codegym.controller;

import com.codegym.model.Country;
import com.codegym.service.CityService;
import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CountryController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    public ModelAndView listCategory(Country country) {
        ModelAndView modelAndView = new ModelAndView("/country/list");
        modelAndView.addObject("countries", country);
        return modelAndView;
    }

    @GetMapping("/category/view/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Long id) {
        Country country = countryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("country/view");
        modelAndView.addObject("country", country);
        return modelAndView;
    }
}
