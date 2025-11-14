package com.example.projetobiblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.projetobiblioteca.model.Secao;
import com.example.projetobiblioteca.repository.SecaoRepository;


@Controller
public class SecaoController {
    private final SecaoRepository secaoRepository;

    public SecaoController(SecaoRepository secaoRepository){
        this.secaoRepository = secaoRepository;
    }

    @GetMapping("/secao/list")
    public String getSecaoList(Model model) {
        model.addAttribute("secoes", secaoRepository.findAll());
        return "secao/list";
    }

    @GetMapping("/secao/new")
    public String showAddSecaoForm(Model model) {
        model.addAttribute("secao", new Secao());
        return "secao/add-form"; 
    }

    @PostMapping("/secao/store")
    public String addSecao(@ModelAttribute Secao secao) {
        secaoRepository.save(secao);
        return "redirect:/secao/list";
    }

    @GetMapping("secao/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Secao c = secaoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));

        model.addAttribute("secao", c);
        return "secao/edit-form";
    }

    @PostMapping("secao/update/{id}")
    public String updateSecao(@PathVariable("id") long id,
                                @ModelAttribute("secao") Secao secao,
                                BindingResult result,
                                Model model) {

        secao.setId(id);

        secaoRepository.save(secao);

        return "redirect:/secao/list";
    }

    @GetMapping("secao/show/{id}")
    public String showSecao(@PathVariable("id") long id, Model model) {
        Secao c = secaoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));

        model.addAttribute("secao", c);
        return "secao/show";
    }

    @PostMapping("secao/delete/{id}")
    public String deleteSecao(@PathVariable("id") long id) {
        Secao a = secaoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        secaoRepository.delete(a);
        return "redirect:/secao/list";
    }
}
