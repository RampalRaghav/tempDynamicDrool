package org.arpit.java2blog.controller;

import java.util.List;

import org.arpit.java2blog.model.RuleSetup;
import  org.arpit.java2blog.model.form.DemoForm;
import org.arpit.java2blog.service.DemoRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CustomerController {

	@Autowired
	DemoRuleService ruleService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model) {
		return getIndex(model);
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getHomePageindex(Model model) {
		return getHomeIndex(model);
	}
	
	/*  Add Rule*/
	@RequestMapping(value="/addRule", method=RequestMethod.POST, produces = "application/json")
	public List<RuleSetup> addRule(@ModelAttribute DemoForm demoForm) {
		ruleService.addRule(demoForm);
		return ruleService.getRuleSetupList();
	}

	/*Add Order*/
	@RequestMapping(value="/addOrder", method=RequestMethod.POST)
	public String addOrder(@ModelAttribute DemoForm demoForm, Model model) {

		ruleService.addOrder(demoForm);
		return getIndex(model);
	}

	/*Generate Offer*/
	@RequestMapping(value="/generateOffer", method=RequestMethod.POST)
	public String generateOffer(@ModelAttribute DemoForm demoForm, Model model) {

		ruleService.generateOffer(demoForm, model);
		return getIndex(model);
	}

	/*Delete Order*/
	@RequestMapping(value="/deleteOrder", method=RequestMethod.POST)
	public String deleteOrder(@ModelAttribute DemoForm demoForm, Model model) {

		ruleService.addRule(demoForm);
		return getIndex(model);
	}

	private String getIndex(Model model){
		model.addAttribute("ruleSetUpList", ruleService.getRuleSetupList());
		//model.addAttribute("orderLineList",  ruleService.getOrderSetupList());
		model.addAttribute("demoForm", new DemoForm());
		return "index";
	}

	private String getHomeIndex(Model model){
		model.addAttribute("ruleSetUpList", ruleService.getRuleSetupList());
		model.addAttribute("orderLineList",  ruleService.getOrderSetupList());
		model.addAttribute("demoForm", new DemoForm());
		return "home";
	}

}
