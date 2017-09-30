package br.com.fiap.serazo.cadastro.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.serazo.cadastro.model.Empresa;
import br.com.fiap.serazo.cadastro.repository.EmpresaRepository;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresasController {
	
	@Autowired
	private EmpresaRepository repository;
	
	@PostMapping
	public String cadastrar(@RequestBody Empresa empresa, HttpServletResponse response) {
		try {
			repository.save(empresa);
			response.setStatus(HttpServletResponse.SC_CREATED);
			return "";
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return e.getMessage();
		}
	}
	
	@GetMapping
	public void existe(String login, HttpServletResponse response) {
		try {
			boolean existe = repository.findByLogin(login) != null;
			int statusCode = existe ? HttpServletResponse.SC_FOUND : HttpServletResponse.SC_NOT_FOUND;
			response.setStatus(statusCode);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
}
