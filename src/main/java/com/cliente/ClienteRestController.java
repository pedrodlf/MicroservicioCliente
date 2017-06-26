package com.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cliente")
public class ClienteRestController {
	
	@Autowired
	private ClienteDAO clienteDao;

	@RequestMapping(path="/guardar/{idcomercial}", method = RequestMethod.POST)
	public String guardarCliente(@RequestBody Cliente cliente, @PathVariable(name= "idcomercial") Long idcomercial){
		RestTemplate restTemplate = new RestTemplate();
	    clienteDao.saveAndFlush(cliente);
	    String respuesta = restTemplate.getForObject("http://localhost:6060/nexo/guardar/"+cliente.getId()+"/"+idcomercial, String.class);
		return respuesta;
	}
}
