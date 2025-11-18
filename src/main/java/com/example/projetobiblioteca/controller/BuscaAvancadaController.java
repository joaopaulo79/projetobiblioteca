package com.example.projetobiblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.projetobiblioteca.repository.AutorRepository;
import com.example.projetobiblioteca.repository.CategoriaRepository;
import com.example.projetobiblioteca.repository.LivroRepository;
import com.example.projetobiblioteca.repository.SecaoRepository;
import com.example.projetobiblioteca.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BuscaAvancadaController {
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;
    private final SecaoRepository secaoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    
    public BuscaAvancadaController(UsuarioRepository usuarioRepository, LivroRepository livroRepository,
                            AutorRepository autorRepository, CategoriaRepository categoriaRepository, SecaoRepository secaoRepository) {
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
        this.secaoRepository = secaoRepository;
    }

    @GetMapping("/buscaavancada/")
    public String getBuscaForm() {
        return "buscaavancada/form";
    }

    @PostMapping("/buscaavancada/redirect")
    public String redirectBusca(@RequestParam String tipoPai, 
                               @RequestParam Long idPai) {
        return "redirect:/buscaavancada/" + tipoPai + "&" + idPai;
    }
    
    @GetMapping("/buscaavancada/{tipoPai}&{idPai}")
    public String getBuscaForm(@PathVariable("tipoPai") String tipoPai,
                                @PathVariable("idPai") long idPai, Model model) {
        switch (tipoPai) {
            case "autor" -> {
                autorRepository.findById(idPai).ifPresent(autor -> {
                    model.addAttribute("pai", autor);
                });
            }
            case "categoria" -> {
                categoriaRepository.findById(idPai).ifPresent(categoria -> {
                    model.addAttribute("pai", categoria);
                });
            }
            case "secao" -> {
                    secaoRepository.findById(idPai).ifPresent(secao -> {
                    model.addAttribute("pai", secao);
                });            }
            case "livro" -> {
                    livroRepository.findById(idPai).ifPresent(livro -> {
                    model.addAttribute("pai", livro);
                });              }
            case "usuario" -> {
                    usuarioRepository.findById(idPai).ifPresent(usuario -> {
                    model.addAttribute("pai", usuario);
                });              }
            default -> {}
        }
        model.addAttribute("tipo", tipoPai);
        return "buscaavancada/form";
    }
}
