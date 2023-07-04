package med.voll.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
     public ResponseEntity trataErro404(){
         return  ResponseEntity.notFound().build();
     }
}
