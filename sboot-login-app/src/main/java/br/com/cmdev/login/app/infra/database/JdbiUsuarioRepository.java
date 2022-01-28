package br.com.cmdev.login.app.infra.database;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import br.com.cmdev.login.app.model.Usuario;
import br.com.cmdev.login.app.repository.UsuarioRepository;

@Repository
@UseClasspathSqlLocator
public interface JdbiUsuarioRepository extends UsuarioRepository {
	
	@SqlQuery
	public List<Usuario> listar();

	@SqlUpdate
	public void salvar(@BindBean Usuario usuario);
	
	@SqlQuery
	public Optional<Usuario> pesquisar(Long id);
	
	@SqlUpdate
	public void deletar(Long id);
	
	@SqlUpdate
	public void atualizar(@BindBean Usuario usuario);
		
}
