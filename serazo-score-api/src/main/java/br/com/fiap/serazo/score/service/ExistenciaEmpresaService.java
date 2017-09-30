package br.com.fiap.serazo.score.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExistenciaEmpresaService {
	
	public boolean existe(String login) throws IOException, URISyntaxException {
		String url = String.format("http://localhost:5001/empresas?login=%s", login);
		return new RestTemplate().getRequestFactory()
				.createRequest(new URI(url), HttpMethod.GET)
				.execute()
				.getStatusCode() == HttpStatus.FOUND;
	}

}
