package br.com.cmdev.login.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cmdev.login.app.model.Usuario;
import br.com.cmdev.login.app.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> listarUsuarios(){
		return repository.listar();
	}

	public void salvarUsuario(Usuario user) {
		user.setDataCadastro(LocalDateTime.now());
		repository.salvar(user);
	}


	public Optional<Usuario> pesquisarPorId(Long id) {
		return repository.pesquisar(id);
	}

	public void deletarUsuario(Long id) {
		repository.deletar(id);
	}

	public Usuario atualizarUsuario(Usuario usuario) {
		usuario.setDataAlteracao(LocalDateTime.now());
		repository.atualizar(usuario);
		return repository.pesquisar(usuario.getId()).get();
	}
}
