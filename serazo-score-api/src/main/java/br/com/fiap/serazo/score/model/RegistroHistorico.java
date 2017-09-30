package br.com.fiap.serazo.score.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historico")
public class RegistroHistorico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 100, name = "login_empresa")
	private String loginEmpresa;

	@Column(nullable = false, length = 11)
	private String cpf;
	

	@Column
	private Date data;
	
	public RegistroHistorico() {
	}
	
	public RegistroHistorico(String loginEmpresa, String cpf, Date data) {
		this.loginEmpresa = loginEmpresa;
		this.cpf = cpf;
		this.data = data;
	}
	
	public Integer getId() {
		return id;
	}
	public String getLoginEmpresa() {
		return loginEmpresa;
	}
	public String getCpf() {
		return cpf;
	}
	public int getScore() {
		int score = 0;
		int multiplier = 1;
		for (int i = 0; i < cpf.length(); ++i) {
			char c = cpf.charAt(i);
			if (!Character.isDigit(c)) {
				continue;
			}
			int digit = Character.getNumericValue(c);
			score += Math.pow(digit, multiplier++);
		}
		return score % 1001;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
