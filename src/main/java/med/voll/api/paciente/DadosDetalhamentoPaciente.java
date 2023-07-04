package med.voll.api.paciente;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,


        String email,

        String telefone,


        String cpf,

        med.voll.api.endereco.Endereco endereco
) {

    public DadosDetalhamentoPaciente(Paciente paciente){
        this(paciente.getId(),paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(),
                paciente.getEndereco());
    }
}
