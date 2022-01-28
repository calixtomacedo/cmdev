package br.com.cmdev.login.app.repository;

import java.util.List;
import java.util.Optional;

import br.com.cmdev.login.app.model.Usuario;

public interface UsuarioRepository {

	public List<Usuario> listar();

	public void salvar(Usuario usuario);

	public Optional<Usuario> pesquisar(Long id);

	public void deletar(Long id);

	public void atualizar(Usuario usuario);
}
