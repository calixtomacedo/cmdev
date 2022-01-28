package br.com.cmdev.login.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import br.com.cmdev.login.app.model.Usuario;

@Component
public class UsuarioRowMapper implements RowMapper<Usuario> {

	@Override
	public Usuario map(ResultSet rs, StatementContext ctx) throws SQLException {
		
		Usuario usuario = new Usuario();
		usuario.setId(rs.getLong("id"));
		usuario.setNome(rs.getString("nome"));
		usuario.setEmail(rs.getString("email"));
		usuario.setSenha(rs.getString("senha"));
		usuario.setDataCadastro(rs.getTimestamp("dataCadastro").toLocalDateTime());
		usuario.setDataAlteracao(rs.getTimestamp("dataAlteracao") != null ? rs.getTimestamp("dataAlteracao").toLocalDateTime(): null);
		
		return usuario;
	}

}
