package com.spi.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spi.logic.GetStates;

@Controller
public class BaseController {
	

	@RequestMapping(value = "/click", method = RequestMethod.GET)
	public ModelAndView registration() {
		/*System.out.println("Success view called ::");
		return new ModelAndView("success");*/
		
		
		ModelAndView model=new ModelAndView("success");
		model.addObject("name", "Kingshuk Nandy");
		return model;
	}
	
	@RequestMapping(value = "/login/{username}", method = RequestMethod.GET)
	public ModelAndView login(@PathVariable("username") String name) {
		/*System.out.println("Success view called ::");
		return new ModelAndView("success");*/
		String msge = null;
		if(name.equalsIgnoreCase("kingshuk")){
			msge="Hello Kingshuk Nandy";
		}else if(name.equalsIgnoreCase("tom")){
			msge="Hello Tom Morgan";
		}else{
			msge="Hello Unknown User";
		}
		
		ModelAndView model=new ModelAndView("login");
		model.addObject("msg", msge);
		return model;
	}
	
	@RequestMapping(value = "/welcome/{username}/{countryname}", method = RequestMethod.GET)
	public ModelAndView welcome(@PathVariable Map<String, String> pathVars) {
	
		String country=pathVars.get("countryname");
		String name=pathVars.get("username");
		ModelAndView model=new ModelAndView("login");
		model.addObject("msg", "Country:"+country+"\t"+"Name:"+name);
		return model;
	}
	
	
	@RequestMapping(value = "/country", method = RequestMethod.GET)
	public ModelAndView country() {
	
		
		ModelAndView model=new ModelAndView("index");
		model.addObject("msg", "");
		model.addObject("search", "SEARCH");
		return model;
	}
	
	@RequestMapping(value = "/citiesDetails", method = RequestMethod.POST)
	public ModelAndView citiesDetails(@RequestParam("countryName") String CountryName) {
	
		
		ModelAndView model=new ModelAndView("citylist");
		List <String> li=GetStates.getCity(CountryName);
	
		model.addObject("msg11", li);
		return model;
	}
	
	

}
