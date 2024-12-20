package br.com.alura.fatura.service.fatura;

import br.com.alura.fatura.dominio.Fatura;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class FaturaPublisher {

    private ObjectMapper mapper;
    private RabbitTemplate rabbitTemplate;

    public FaturaPublisher(ObjectMapper mapper, RabbitTemplate rabbitTemplate) {
        this.mapper = mapper;
        this.rabbitTemplate = rabbitTemplate;
    }


    public void notificaFaturaPaga(Fatura fatura) {
        try {
            String payload = mapper.writeValueAsString(fatura);
            rabbitTemplate.convertAndSend("FaturasPagasExchange", "fatura.paga", payload);

            System.out.println("Fatura enviada para fila de faturas pagas: " + fatura.getId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
