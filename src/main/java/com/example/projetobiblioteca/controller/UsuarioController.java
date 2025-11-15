package com.example.projetobiblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.projetobiblioteca.dto.UsuarioDTO;
import com.example.projetobiblioteca.model.Administrador;
import com.example.projetobiblioteca.model.Usuario;
import com.example.projetobiblioteca.repository.UsuarioRepository;


@Controller
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/usuario/list")
    public String getUsuarioList(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuario/list";
    }

    @GetMapping("/usuario/new")
    public String showAddUsuarioForm(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "usuario/add-form"; 
    }

    @PostMapping("/usuario/store")
    public String addUsuario(@ModelAttribute UsuarioDTO usuarioForm) {
        if (usuarioForm.getTipo().equals("usuario")) {
            Usuario usuario = usuarioForm.toUsuario();
            usuarioRepository.save(usuario);
        } else if (usuarioForm.getTipo().equals("administrador")) {
            Administrador administrador = usuarioForm.toAdministrador();
            usuarioRepository.save(administrador);
        } 
        return "redirect:/usuario/list";
    }

    @GetMapping("usuario/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        UsuarioDTO dto = new UsuarioDTO(usuario);

        model.addAttribute("usuario", dto);
        return "usuario/edit-form";
    }

    @PostMapping("usuario/update/{id}")
    public String updateUsuario(@PathVariable("id") long id,
                                @ModelAttribute("usuario") UsuarioDTO dto,
                                BindingResult result,
                                Model model) {
        if (dto.getTipo().equals("usuario")) {
            Usuario usuario = dto.toUsuario();
            usuarioRepository.save(usuario);
        } else {
            Administrador administrador = dto.toAdministrador();
            usuarioRepository.save(administrador);
        }


        return "redirect:/usuario/list";
    }

    @GetMapping("usuario/show/{id}")
    public String showUsuario(@PathVariable("id") long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));

        UsuarioDTO dto = new UsuarioDTO(usuario);
        model.addAttribute("usuario", dto);
        return "usuario/show";
    }

    @PostMapping("usuario/delete/{id}")
    public String deleteUsuario(@PathVariable("id") long id) {
        Usuario a = usuarioRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        usuarioRepository.delete(a);
        return "redirect:/usuario/list";
    }
}
