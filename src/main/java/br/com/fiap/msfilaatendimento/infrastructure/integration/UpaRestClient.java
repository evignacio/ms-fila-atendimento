package br.com.fiap.msfilaatendimento.infrastructure.integration;

import br.com.fiap.msfilaatendimento.infrastructure.integration.to.UpaTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "upaRestClient", url = "${rest-service.ms-gestao-upa.url}", path = "/api/v1/upas/")
public interface UpaRestClient {

    @GetMapping(value = "/{upaId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UpaTo> findUpa(@PathVariable String upaId);
}
