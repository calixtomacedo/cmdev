package br.com.cmdev.h2database.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.cmdev.h2database.model.Usuario;

public class UsuarioDAO {

	private EntityManager manager;

	public UsuarioDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void save(Usuario usuario) {
		this.manager.persist(usuario);
	}
	
	public List<Usuario> list(){
		String jpql = "SELECT u FROM Usuario u";
		return this.manager.createQuery(jpql, Usuario.class).getResultList();
	}
}
