package br.com.cmdev.cmdevjdbi.config;

import java.util.List;

import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import br.com.cmdev.cmdevjdbi.infra.database.JdbiUsuarioRepository;
import br.com.cmdev.cmdevjdbi.repository.UsuarioRepository;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public Jdbi jdbi(DataSource dataSource, List<JdbiPlugin> jdbiPlugins, List<RowMapper<?>> rowMappers) {

		TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
		Jdbi jdbi = Jdbi.create(dataSourceProxy);

		jdbiPlugins.forEach(jdbi::installPlugin);
		rowMappers.forEach(jdbi::registerRowMapper);

		return jdbi;
	}

	@Bean
	public JdbiPlugin sqlObjectPlugin() {
		return new SqlObjectPlugin();
	}

	@Bean
	public UsuarioRepository usuarioRepository(Jdbi jdbi) {
		return jdbi.onDemand(JdbiUsuarioRepository.class);
	}
}
