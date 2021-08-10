package br.com.cmdev.cmdevjdbi.repository;

import java.util.List;

import br.com.cmdev.cmdevjdbi.model.Usuario;

public interface UsuarioRepository {
	
	public List<Usuario> listar();

	public void salvar(Usuario usuario);
	
}
