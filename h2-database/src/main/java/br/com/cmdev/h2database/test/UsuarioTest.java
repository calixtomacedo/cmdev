package br.com.cmdev.h2database.test;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.cmdev.h2database.dao.UsuarioDAO;
import br.com.cmdev.h2database.model.Usuario;
import br.com.cmdev.h2database.utils.JPAUtil;

public class UsuarioTest {

	public static void main(String[] args) {

		EntityManager manager = JPAUtil.getEntityManager();
		UsuarioDAO usuarioDAO = new UsuarioDAO(manager);

		Usuario usuario = new Usuario();
		usuario.setName("Calixto Rodrigues Macedo");
		usuario.setEmail("calixto.macedo@gmail.com");
		usuario.setPassword("123456");
		usuario.setDataCadastro(LocalDateTime.now());
		
		manager.getTransaction().begin();
		usuarioDAO.save(usuario);
		manager.getTransaction().commit();
		
		listarUsuarios(usuarioDAO);
		
		manager.close();
		
	}

	private static void listarUsuarios(UsuarioDAO usuarioDAO) {
		List<Usuario> usuarioList = usuarioDAO.list();
		usuarioList.forEach(user ->  System.out.println(user));
	}

}
