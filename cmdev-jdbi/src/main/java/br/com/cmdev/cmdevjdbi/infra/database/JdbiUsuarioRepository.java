package br.com.cmdev.cmdevjdbi.infra.database;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import br.com.cmdev.cmdevjdbi.model.Usuario;
import br.com.cmdev.cmdevjdbi.repository.UsuarioRepository;

@Repository
@UseClasspathSqlLocator
public interface JdbiUsuarioRepository extends UsuarioRepository {

	@SqlQuery
	public List<Usuario> listar();
	
	@SqlUpdate
	public void salvar(@BindBean Usuario usuario);
}
