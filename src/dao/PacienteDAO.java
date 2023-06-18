package dao;

import mapeamento.Paciente;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utilitario.Conectar;

public class PacienteDAO {
    
 public void cadastrar(Paciente p){
 Connection con = Conectar.getConectar();
 String sql = "INSERT INTO paciente(nome,cpf,email,datanasc,telefone,sexo) VALUES(?,?,?,?,?,?)";
 try(PreparedStatement smt = con.prepareStatement(sql)){
   smt.setString(1, p.getNome());
   smt.setString(2, p.getCpf());
   smt.setString(3, p.getEmail());
   smt.setString(4, p.getDatanasc());
   smt.setString(5, p.getTelefone());
   smt.setString(6, p.getSexo());
   smt.executeUpdate();
   smt.close();
   con.close();
   JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
 }catch(Exception ex){
     JOptionPane.showMessageDialog(null,"Erro ao cadastar");
 }
 
 }   
 public void atulizar(Paciente p){
 Connection con = Conectar.getConectar();
 String sql = "UPDATE paciente SET nome = ?, cpf = ?, email = ?, datanasc = ?, telefone = ?, sexo = ?  WHERE id_paciente = ?";
 try(PreparedStatement smt = con.prepareStatement(sql)){
   smt.setString(1, p.getNome());
   smt.setString(2, p.getCpf());
   smt.setString(3, p.getEmail());
   smt.setString(4, p.getDatanasc());
   smt.setString(5, p.getTelefone());
   smt.setString(6, p.getSexo());
   smt.setInt(7, p.getId_paciente());
   smt.executeUpdate();
   smt.close();
   con.close();
   JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
 }catch(Exception ex){
 JOptionPane.showMessageDialog(null, "Erro ao atualizar o registro!");
 }
 }
 public void excluir(Paciente p){
     Connection con = Conectar.getConectar();
     String sql = "DELETA FROM paciente WHERE id_paciente = ?";
     int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o pacinte "+p.getNome()+"?", "Excluir", JOptionPane.YES_NO_OPTION);
     if(opcao == JOptionPane.YES_OPTION) {
      try(PreparedStatement smt = con.prepareStatement(sql)){
     smt.setInt(1, p.getId_paciente());
     smt.executeUpdate();
     smt.close();
     con.close();
   JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
     }catch(Exception ex){
     JOptionPane.showMessageDialog(null, "Erro ao excluir o registro!");
     }
     }
 }
 
 public List<Paciente> listarTodos(){
 Connection con = Conectar.getConectar();
 List<Paciente> lista = new ArrayList<>();
 String sql = "SELECT * FROM paciente ORDER BY nome";
 try(PreparedStatement smt = con.prepareStatement(sql)){
     ResultSet resultado = smt.executeQuery();
     while(resultado.next()){
     Paciente p = new Paciente();
    p.getId_paciente(resultado.getInt("id_paciente"));
    p.setNome(resultado.getString("nome"));
    p.setCpf(resultado.getString("cpf"));
    p.setEmail(resultado.getString("email"));
    p.setDatanasc(resultado.getString("datanasc"));
    p.setTelefone(resultado.getString("telefone"));
    p.setSexo(resultado.getString("sexo"));
    lista.add(p);
     }
 }catch(Exception ex){
 JOptionPane.showMessageDialog(null, "Erro ao buscar os registros!");
 }
 
 return lista;
 }
}
