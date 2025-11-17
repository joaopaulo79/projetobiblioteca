package com.example.projetobiblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.projetobiblioteca.repository.AutorRepository;
import com.example.projetobiblioteca.repository.CategoriaRepository;
import com.example.projetobiblioteca.repository.LivroRepository;
import com.example.projetobiblioteca.repository.LocacaoRepository;
import com.example.projetobiblioteca.repository.SecaoRepository;
import com.example.projetobiblioteca.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BuscaAvancada {
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;
    private final SecaoRepository secaoRepository;
    private final LocacaoRepository locacaoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    
    public BuscaAvancada(LocacaoRepository locacaoRepository, UsuarioRepository usuarioRepository, LivroRepository livroRepository,
                            AutorRepository autorRepository, CategoriaRepository categoriaRepository, SecaoRepository secaoRepository) {
        this.locacaoRepository = locacaoRepository;
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
    
    @GetMapping("/buscaavancada/{tipoPai}&{idPai}&{tipoFilho}")
    public String getBuscaForm(@PathVariable("tipoPai") String tipoPai,
                                @PathVariable("tipoFilho") String tipoFilho,
                                @PathVariable("id") long idPai, Model model) {
        switch (tipoPai) {
            case "autor" -> {
                if (tipoFilho.equals("livro")) {
                    model.addAttribute("pai", autorRepository.findById(idPai));
                    model.addAttribute("filho", livroRepository.findByAutorId(idPai));
                }
            }
            case "categoria" -> {
                if (tipoFilho.equals("livro")) {
                    model.addAttribute("pai", categoriaRepository.findById(idPai));
                    model.addAttribute("filho", livroRepository.findByCategoriaId(idPai));
                }
            }
            case "secao" -> {
                if (tipoFilho.equals("livro")) {
                    model.addAttribute("pai", secaoRepository.findById(idPai));
                    model.addAttribute("filho", livroRepository.findBySecaoId(idPai));
                }
            }
            case "livro" -> {
                if (tipoFilho.equals("livro")) {
                    model.addAttribute("filho", livroRepository.findByAutorId(idPai));
                }
                model.addAttribute("pai", livroRepository.findById(idPai));
            }
            case "usuario" -> {
                model.addAttribute("pai", usuarioRepository.findById(idPai));
            }
            default -> {}
        }


        return "buscaavancada/form";
    }
}
