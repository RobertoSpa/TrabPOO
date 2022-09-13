package trabalho;

import java.time.LocalDate;

public class Pessoa {
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;

	public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Nome= " + nome + ", cpf= " + cpf + ", dataNascimento= " + dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

}

