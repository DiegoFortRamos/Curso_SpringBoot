package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBilder){
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDelhamentoMedico(medico));
        
    }




    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size=10,sort = {"nome"}) Pageable paginacao){
        var page =  repository.findAll(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizaInformacoes(dados);

        return ResponseEntity.ok(new DadosDelhamentoMedico(medico));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDelhamentoMedico(medico));

    }




}
