package br.ulbra.model;



import br.ulbra.ulties.Ulties;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
    
    
    
    
    public boolean adicionarUsuario(Usuario u){
        String sql = "INSERT into TBUSUARIO (nomeusu, emailUsu, senhaUsu, dataNascUsu, ativoUsu, imagemUsu)"
                + "VALUES (?,?,?,?,?, ?)";
        try {
            byte[] iconBytes = Ulties.iconToBytes(u.getImagemUsu());
            
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, u.getNomeUsu());
            stmt.setString(2, u.getEmailUsu());
            stmt.setString(3, u.getSenhaUsu());
            stmt.setString(4, u.getDataNascUsu());
            stmt.setInt(5, u.getAtivoUsu());
            stmt.setBytes(6, iconBytes);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuario:" + u + " inserido com sucesso !");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }
        return false;
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
    
    public List<Usuario> readForDesc (String desc) {
        String sql = "SELECT * FROM tbusuario WHERE nomeusu LIKE ?";
        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,"%"+desc+"%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()){
                
                Usuario usuario = new Usuario();
                
                usuario.setPkUsuario(rs.getInt("pkusuario"));
                usuario.setNomeUsu(rs.getString("nomeusu"));
                usuario.setEmailUsu(rs.getString("emailusu"));
                usuario.setSenhaUsu(rs.getString("senhausu"));
                usuario.setDataNascUsu(rs.getString("datanascusu"));
                usuario.setAtivoUsu(rs.getInt("ativousu"));
                usuarios.add(usuario);
                
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);
        }
        return usuarios;
    }
    
    
    public Usuario readForPk (int pk) {
        String sql = "SELECT * FROM tbusuario WHERE pkusuario = ?";
        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,pk);
            
            rs = stmt.executeQuery();
            
            while (rs.next()){
                
                usuario.setPkUsuario(rs.getInt("pkusuario"));
                usuario.setNomeUsu(rs.getString("nomeusu"));
                usuario.setEmailUsu(rs.getString("emailusu"));
                usuario.setSenhaUsu(rs.getString("senhausu"));
                usuario.setDataNascUsu(rs.getString("datanascusu"));
                usuario.setAtivoUsu(rs.getInt("ativousu"));
                
                byte[] bytes = rs.getBytes("imagemUsu");
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                BufferedImage imagem = ImageIO.read(bis);
                
                usuario.setImagemUsu(new ImageIcon(imagem));
                
                
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);
        }
        return usuario;
    }
        
        public boolean alterarUsuario(Usuario u) {
            GerenciadorConexao gerencador = GerenciadorConexao.getInstancia();
            Connection con = gerenciador.getConexao();
            PreparedStatement stmt = null;
            
            try{
                byte[] iconBytes = Ulties.iconToBytes(u.getImagemUsu());
                stmt = con.prepareStatement("UPDATE tbusuario SET nomeusu = ?,)"
                        + " emailusu =?, senhausu = ?, datanascusu = ?,"
                        + " ativousu = ?, imagemUsu = ? WHERE pkusuario = ?");
                
                stmt.setString(1, u.getNomeUsu());
                stmt.setString(2, u.getEmailUsu());
                stmt.setString(3, u.getSenhaUsu());
                stmt.setString(4, u.getDataNascUsu());
                stmt.setInt(5, u.getAtivoUsu());
                stmt.setBytes(6, iconBytes);
                stmt.setInt(7, u.getPkUsuario());
                        
                stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!!");
                return true;
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
            }  catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
            }finally {
                GerenciadorConexao.closeConnection(con, stmt);
            }
            return false;
        }
    }

