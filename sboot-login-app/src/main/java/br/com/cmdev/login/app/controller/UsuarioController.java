package br.com.cmdev.login.app.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cmdev.login.app.model.Usuario;
import br.com.cmdev.login.app.service.UsuarioService;

@RestController()
@RequestMapping("/login-app")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping("salvar")
	public ResponseEntity<String> salvarUsuario(@RequestBody Usuario user) {
		service.salvarUsuario(user);
		return new ResponseEntity<String>("Usuário "+user.getNome()+", salvo com sucesso", HttpStatus.CREATED);
	}
	
	@GetMapping("listar")
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = service.listarUsuarios();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("pesquisar")
	public ResponseEntity<?> pesquisarPorId(@PathParam(value = "id") Long id) {
		Optional<Usuario> usuario = service.pesquisarPorId(id);
		if(usuario.isPresent()) {
			return ResponseEntity.ok(usuario);
		}
		return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
		Usuario userAtualizado = service.atualizarUsuario(usuario);
		return new ResponseEntity<Usuario>(userAtualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("deletar")
	public ResponseEntity<?> deletar(@PathParam(value = "id") Long id){
		Optional<Usuario> usuario = service.pesquisarPorId(id);
		if(usuario.isPresent()) {
			service.deletarUsuario(usuario.get().getId());
			return new ResponseEntity<>("Usuário "+usuario.get().getNome()+" deletado com sucesso. ", HttpStatus.OK);
		}
		return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
	}
	
}
