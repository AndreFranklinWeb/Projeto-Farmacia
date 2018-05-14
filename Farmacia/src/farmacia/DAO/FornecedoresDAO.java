package farmacia.DAO;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import farmacia.dominio.Fornecedores;
import farmacia.factory.conexao.ConexaoDB;

public class FornecedoresDAO {

	public void salvar(Fornecedores f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO fornecedores ");
		sql.append("(descricao)");
		sql.append("VALUES(?)");

		Connection conexao = ConexaoDB.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}
	
	public void excluir(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM fornecedores ");
		sql.append(" WHERE codigo = ? ");
				
		Connection conexao = ConexaoDB.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
	}
	
	public void editar(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE fornecedores ");
		sql.append(" SET descricao = ? ");
		sql.append(" WHERE codigo = ? ");
				
		Connection conexao = ConexaoDB.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
	}
	
	public Fornecedores buscarPorCodigo(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT codigo, descricao ");
		sql.append(" FROM fornecedores ");
		sql.append(" WHERE codigo = ? ");
				
		Connection conexao = ConexaoDB.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, f.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;
		
		if(resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		
		}
		return retorno;				
	}
	
	public static void main(String[] args) {
		/*========================= TESTE DO SALVAR ===================================
		Fornecedores f1 = new Fornecedores();
		f1.setDescricao("DESCRICAO 1");
		
		Fornecedores f2 = new Fornecedores();
		f2.setDescricao("DESCRICAO 2");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.salvar(f1);
			fdao.salvar(f2);
			System.out.println("Salvo no banco com sucesso!");
		}catch(SQLException e){
			System.out.println("Erro ao salvar no banco!");
			e.printStackTrace();
		}*/
		/*==============================TESTE DO EXCLUIR =================================
		Fornecedores f1 = new Fornecedores();
		
		f1.setCodigo(1L);
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.excluir(f1);
			
			System.out.println("Deletado do banco com sucesso!");
		}catch(SQLException e){
			System.out.println("Erro ao deletar do banco!");
			e.printStackTrace();
		}
	   */
		/*====================================TESTE DO EDITAR ===============================
		Fornecedores f1 = new Fornecedores();
		
		f1.setCodigo(4L);
		f1.setDescricao(" TESTANDO O BANCO ");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.editar(f1);
			
			System.out.println("Banco editado com sucesso!");
		}catch(SQLException e){
			System.out.println("Erro ao editar o banco!");
			e.printStackTrace();
		}
		====================================TESTE DO PESQUISAR POR CODIGO =================*/
		Fornecedores f2 = new Fornecedores();
		f2.setCodigo(3L);
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			Fornecedores f3 = fdao.buscarPorCodigo(f2);
			System.out.println("Resultado 2:"+f3);
			
		}catch(SQLException e){
			System.out.println("Erro ao Buscar o banco!");
			e.printStackTrace();
		}
		
	}

}
