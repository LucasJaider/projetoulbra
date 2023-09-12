package br.ulbra.model;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author aluno.saolucas
 */
public class UsuarioDAO {
    private GerenciadorConexao gerenciador;
    public UsuarioDAO(){
        this.gerenciador = GerenciadorConexao.getInstancia();
    }
    
    public boolean autenticar(String email,String senha){
        String sql = "SELECT * from TBUSUARIO WHERE emailUsu = ? and senhaUsu = ? and ativoUsu = 1";
        try {
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }
    
    public void adicionarUsuario(String nome, String email, String senha, String datan, int ativo){
        String sql = "INSERT into TAUSUARIO (nomeusu, emailUsu, senhaUsu, dataNascUsu, ativoUsu)"
                + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setString(4, datan);
            stmt.setInt(5, ativo);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuario:" + nome + " inserido com sucesso !");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }
    }
    
    public List<Usuario> read(){
        String sql = "SELECT * FROM tbusuario";
        List<Usuario> usuarios = new ArrayList<>();
        
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Usuario usuario = new Usuario();
                
                usuario.setPkUsuario(rs.getInt("pkusuario"));
                usuario.setNomeUsu(rs.getString("nmeusu"));
                usuario.setEmailUsu(rs.getString("emailusu"));
                usuario.setSenhaUsu(rs.getString("senhausu"));
                usuario.setDataNascUsu(rs.getString("datanascusu"));
                usuario.setAtivoUsu(rs.getInt("ativousu"));
                
                usuarios.add(usuario);
            }
        }catch (SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);
        }
        
        return usuarios;
    }
    
}
