package com.example.projetobiblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.projetobiblioteca.model.Categoria;
import com.example.projetobiblioteca.repository.CategoriaRepository;


@Controller
public class CategoriaController {
    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/categoria/list")
    public String getCategoriaList(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "categoria/list";
    }

    @GetMapping("/categoria/new")
    public String showAddCategoriaForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/add-form"; 
    }

    @PostMapping("/categoria/store")
    public String addCategoria(@ModelAttribute Categoria categoria) {
        categoriaRepository.save(categoria);
        return "redirect:/categoria/list";
    }

    @GetMapping("categoria/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Categoria c = categoriaRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));

        model.addAttribute("categoria", c);
        return "categoria/edit-form";
    }

    @PostMapping("categoria/update/{id}")
    public String updateCategoria(@PathVariable("id") long id,
                                @ModelAttribute("categoria") Categoria categoria,
                                BindingResult result,
                                Model model) {

        categoria.setId(id);

        categoriaRepository.save(categoria);

        return "redirect:/categoria/list";
    }

    @GetMapping("categoria/show/{id}")
    public String showCategoria(@PathVariable("id") long id, Model model) {
        Categoria c = categoriaRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));

        model.addAttribute("categoria", c);
        return "categoria/show";
    }

    @PostMapping("categoria/delete/{id}")
    public String deleteCategoria(@PathVariable("id") long id) {
        Categoria a = categoriaRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        categoriaRepository.delete(a);
        return "redirect:/categoria/list";
    }
}
