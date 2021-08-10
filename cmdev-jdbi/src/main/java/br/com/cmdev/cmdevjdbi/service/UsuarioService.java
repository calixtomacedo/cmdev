package br.com.cmdev.cmdevjdbi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cmdev.cmdevjdbi.model.Usuario;
import br.com.cmdev.cmdevjdbi.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> listar() {
		return usuarioRepository.listar();
	}

	public void salvar(Usuario usuario) {
		usuarioRepository.salvar(usuario);
	}

	
}
