package com.example.projetobiblioteca.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.projetobiblioteca.model.Locacao;
import com.example.projetobiblioteca.repository.LivroRepository;
import com.example.projetobiblioteca.repository.LocacaoRepository;
import com.example.projetobiblioteca.repository.UsuarioRepository;

@Controller
public class LocacaoController {
    private final LocacaoRepository locacaoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;


    public LocacaoController(LocacaoRepository locacaoRepository, LivroRepository livroRepository,
                                UsuarioRepository usuarioRepository) {
        this.locacaoRepository = locacaoRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/locacao/list")
    public String getLocacaoList(Model model) {
        List<Locacao> locacoes = locacaoRepository.findAll();
        model.addAttribute("locacoes", locacoes);
        return "locacao/list";
    }

    @GetMapping("/locacao/new")
    public String showAddLocacaoForm(Model model) {
        Locacao novaLocacao = new Locacao();
        model.addAttribute("locacao", novaLocacao);
        
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        model.addAttribute("listaLivros", livroRepository.findAll());

        return "locacao/add-form"; 
    }

    @PostMapping("/locacao/store")
    public String addLocacao(@ModelAttribute Locacao locacao) {
        locacaoRepository.save(locacao);
        return "redirect:/locacao/list";
    }

    @GetMapping("locacao/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Locacao a = locacaoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
       
        model.addAttribute("locacao", a);

        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        model.addAttribute("listaLivros", livroRepository.findAll());

        return "locacao/edit-form";
    }

    @PostMapping("locacao/update/{id}")
    public String updateLocacao(@PathVariable("id") long id,
                                @ModelAttribute("locacao") Locacao locacao,
                                BindingResult result,
                                Model model) {

        locacao.setId(id);
        locacaoRepository.save(locacao);

        return "redirect:/locacao/list";
    }

    @GetMapping("locacao/show/{id}")
    public String showLocacao(@PathVariable("id") long id, Model model) {
        Locacao a = locacaoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));

        model.addAttribute("locacao", a);
        return "locacao/show";
    }

    @PostMapping("locacao/delete/{id}")
    public String deleteLocacao(@PathVariable("id") long id) {
        Locacao a = locacaoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        locacaoRepository.delete(a);
        return "redirect:/locacao/list";
    }
}
