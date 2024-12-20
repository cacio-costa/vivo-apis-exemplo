package br.com.alura.notificacao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FaturaConsumer {

    private ObjectMapper mapper;

    public FaturaConsumer(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @RabbitListener(queues = "FaturasPagas")
    public void processaFaturaPaga(String payload) {
        try {
            Fatura fatura = mapper.readValue(payload, Fatura.class);

            System.out.println("Fatura paga recebida...");
            System.out.println(fatura);

            System.out.println("Notificando cliente: " + fatura.getClienteId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
