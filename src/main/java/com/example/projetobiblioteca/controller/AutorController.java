package com.example.projetobiblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.projetobiblioteca.model.Autor;
import com.example.projetobiblioteca.repository.AutorRepository;


@Controller
public class AutorController {
    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    @GetMapping("/autor/list")
    public String getAutorList(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        return "autor/list";
    }

    @GetMapping("/autor/new")
    public String showAddAutorForm(Model model) {
        model.addAttribute("autor", new Autor());
        return "autor/add-form"; 
    }

    @PostMapping("/autor/store")
    public String addAutor(@ModelAttribute Autor autor) {
        autorRepository.save(autor);
        return "redirect:/autor/list";
    }

    @GetMapping("autor/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Autor a = autorRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));

        model.addAttribute("autor", a);
        return "autor/edit-form";
    }

    @PostMapping("autor/update/{id}")
    public String updateAutor(@PathVariable("id") long id,
                                @ModelAttribute("autor") Autor autor,
                                BindingResult result,
                                Model model) {

        autor.setId(id);

        autorRepository.save(autor);

        return "redirect:/autor/list";
    }

    @GetMapping("autor/show/{id}")
    public String showAutor(@PathVariable("id") long id, Model model) {
        Autor a = autorRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));

        model.addAttribute("autor", a);
        return "autor/show";
    }

    @PostMapping("autor/delete/{id}")
    public String deleteAutor(@PathVariable("id") long id) {
        Autor a = autorRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        autorRepository.delete(a);
        return "redirect:/autor/list";
    }
}
