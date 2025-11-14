package com.example.projetobiblioteca.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.projetobiblioteca.model.Livro;
import com.example.projetobiblioteca.repository.LivroRepository;
import com.example.projetobiblioteca.repository.AutorRepository;
import com.example.projetobiblioteca.repository.CategoriaRepository;
import com.example.projetobiblioteca.repository.SecaoRepository;

@Controller
public class LivroController {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;
    private final SecaoRepository secaoRepository;


    public LivroController(LivroRepository livroRepository, AutorRepository autorRepository,
                            CategoriaRepository categoriaRepository, SecaoRepository secaoRepository){
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
        this.secaoRepository = secaoRepository;
    }

    @GetMapping("/livro/list")
    public String getLivroList(Model model) {
        List<Livro> livros = livroRepository.findAll();
        model.addAttribute("livros", livros);
        return "livro/list";
    }

    @GetMapping("/livro/new")
    public String showAddLivroForm(Model model) {
        Livro novoLivro = new Livro();
        novoLivro.setStatus(true);
        model.addAttribute("livro", novoLivro);

        model.addAttribute("listaAutores", autorRepository.findAll());
        model.addAttribute("listaCategorias", categoriaRepository.findAll());
        model.addAttribute("listaSecoes", secaoRepository.findAll());


        return "livro/add-form"; 
    }

    @PostMapping("/livro/store")
    public String addLivro(@ModelAttribute Livro livro) {
        livroRepository.save(livro);
        return "redirect:/livro/list";
    }

    @GetMapping("livro/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Livro a = livroRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
       
        model.addAttribute("livro", a);
        model.addAttribute("listaAutores", autorRepository.findAll());
        model.addAttribute("listaCategorias", categoriaRepository.findAll());
        model.addAttribute("listaSecoes", secaoRepository.findAll());

        return "livro/edit-form";
    }

    @PostMapping("livro/update/{id}")
    public String updateLivro(@PathVariable("id") long id,
                                @ModelAttribute("livro") Livro livro,
                                BindingResult result,
                                Model model) {

        livro.setId(id);
        livroRepository.save(livro);

        return "redirect:/livro/list";
    }

    @GetMapping("livro/show/{id}")
    public String showLivro(@PathVariable("id") long id, Model model) {
        Livro a = livroRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));

        model.addAttribute("livro", a);
        return "livro/show";
    }

    @PostMapping("livro/delete/{id}")
    public String deleteLivro(@PathVariable("id") long id) {
        Livro a = livroRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        livroRepository.delete(a);
        return "redirect:/livro/list";
    }
}
