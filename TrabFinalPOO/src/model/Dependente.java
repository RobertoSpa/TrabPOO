package model;

import java.time.LocalDate;

import enums.GrauParentesco;

public class Dependente extends Pessoa {
	private GrauParentesco parentesco;

	public Dependente(String nome, String cpf, LocalDate dataNascimento, GrauParentesco parentesco) {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
	}

	@Override
	public String toString() {
		return super.toString() + ", Parentesco= " + parentesco;
	}

	public GrauParentesco getParentesco() {
		return parentesco;
	}
	
	

}
