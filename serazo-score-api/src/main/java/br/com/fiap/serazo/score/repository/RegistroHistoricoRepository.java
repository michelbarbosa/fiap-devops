package br.com.fiap.serazo.score.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.serazo.score.model.RegistroHistorico;

@Repository
public interface RegistroHistoricoRepository extends JpaRepository<RegistroHistorico, Integer> {
	List<RegistroHistorico> findAllByLoginEmpresa(String loginEmpresa);
}
