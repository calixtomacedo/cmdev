package br.com.cmdev.cmdevjdbi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cmdev.cmdevjdbi.model.Usuario;
import br.com.cmdev.cmdevjdbi.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("usuarios")
	public List<Usuario> listarUsuario() {
		
		List<Usuario> list = usuarioService.listar();
		
		return list;
	}
	
	
	@PostMapping("salvar")
	public void salvar() {
		Usuario usuario = new Usuario();
		usuario.setNome("Caio Macedo");
		usuario.setEmail("caio.macedo@teste.com");
		usuario.setSenha("123");
		
		usuarioService.salvar(usuario);
		
	}

}
