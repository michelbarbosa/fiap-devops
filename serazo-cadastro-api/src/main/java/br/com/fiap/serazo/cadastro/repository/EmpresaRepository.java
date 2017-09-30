package br.com.fiap.serazo.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.serazo.cadastro.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
	Empresa findByLoginAndSenha(String login, String senha);
	Empresa findByLogin(String login);
}
