package br.com.fiap.serazo.score.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.serazo.score.model.ConsultaScoreDto;
import br.com.fiap.serazo.score.model.RegistroHistorico;
import br.com.fiap.serazo.score.model.RegistroHistoricoDto;
import br.com.fiap.serazo.score.repository.RegistroHistoricoRepository;
import br.com.fiap.serazo.score.service.ExistenciaEmpresaService;

@RestController
@RequestMapping("/")
public class RegistrosHistoricosController {
	
	@Autowired
	private RegistroHistoricoRepository registroRepo;
	
	@Autowired
	private ExistenciaEmpresaService existenciaEmpresaService;
	
	@PostMapping(path = "/score")
	public RegistroHistoricoDto consultarScore(@RequestBody ConsultaScoreDto consulta, HttpServletResponse response) {
		try {
			boolean existe = existenciaEmpresaService.existe(consulta.getLogin());
			
			if (!existe) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			}
			RegistroHistorico registro = new RegistroHistorico(consulta.getLogin(), consulta.getCpf(), new Date());
			registroRepo.save(registro);
			return new RegistroHistoricoDto(registro);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
	
	@GetMapping(path = "empresas/historico")
	public List<RegistroHistoricoDto> listarHistorico(String login, HttpServletResponse response) {
		try {
			boolean existe = existenciaEmpresaService.existe(login);
			if (!existe) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			}
			return registroRepo.findAllByLoginEmpresa(login)
					.stream()
					.sorted((left, right) -> right.getData().compareTo(left.getData()))
					.map(RegistroHistoricoDto::new)
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}
}
