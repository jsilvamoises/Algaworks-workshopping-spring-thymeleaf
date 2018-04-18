package com.jsm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsm.model.TipoVinho;
import com.jsm.model.Vinho;
import com.jsm.repository.VinhoRepository;

@Controller
@RequestMapping("/vinhos")
public class VinhoController {

	@Autowired
	VinhoRepository repository;

	@GetMapping("/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		return novo(repository.getOne(id));
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		ModelAndView mav = new ModelAndView("vinhos/cadastro-vinho");
		mav.addObject("vinho",vinho);
		mav.addObject("tipos",TipoVinho.values());
		System.out.println("Passou Aqui.");
		return mav;
	}
	
	@PostMapping("/novo")
	public ModelAndView save(@Valid Vinho vinho, BindingResult result,RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(vinho);
		}		
		
		repository.save(vinho);
		
		attributes.addFlashAttribute("message", "Salvo com Sucesso! | ID = "+vinho.getId());
		return new ModelAndView("redirect:/vinhos/novo");
	}
	
	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView mav = new ModelAndView("vinhos/lista-vinhos");
		mav.addObject("vinhos",repository.findAll());
		return mav;
	}
	
	@DeleteMapping("/{id}")
	public ModelAndView delete(@PathVariable Long id, RedirectAttributes attrs) {
		//repository.findById(id);
		repository.deleteById(id);
		attrs.addFlashAttribute("mensagem", "Excluído com sucesso!!!");
		return new ModelAndView("redirect:/vinhos");
	}
	
	
}
