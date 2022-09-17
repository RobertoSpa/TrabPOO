package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import enums.AliquotaInss;
import enums.AliquotaIr;
import interfaces.Calculo;

public class Funcionario extends Pessoa implements Calculo {
	private Double salarioBruto = 0.;
	private Double descontoInss = 0.;
	private Double descontoIR = 0.;
	private Double salarioLiquido = 0.;
	private Set<Dependente> dependentes = new HashSet<>();

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, Double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;

	}

	@Override
	public String toString() {
		return super.getNome() + ";" + super.getCpf() + ";" + String.format("%.2f", descontoInss) + ";"
				+ String.format("%.2f", descontoIR) + ";" + String.format("%.2f", salarioLiquido);
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public Double getDescontoInss() {
		return descontoInss;
	}

	public Double getDescontoIR() {
		return descontoIR;
	}

	public Double getSalarioLiquido() {
		return salarioLiquido;
	}

	public Set<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(Set<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	@Override
	public void calcularIR() {
		Double salarioBase = salarioBruto - (dependentes.size() * AliquotaIr.IR.getBaseDep()) - descontoInss;
		if (salarioBase <= AliquotaIr.IRPF1.getBase()) {
			descontoIR = AliquotaIr.IR.getMinBase();
		} else if (salarioBase >= AliquotaIr.IRPF1.getBase() && salarioBase < AliquotaIr.IRPF2.getBase()) {
			descontoIR = salarioBase * AliquotaIr.IRPF1.getAliquota() - AliquotaIr.IRPF1.getParcDeduzir();
		} else if (salarioBase >= AliquotaIr.IRPF2.getBase() && salarioBase < AliquotaIr.IRPF3.getBase()) {
			descontoIR = salarioBase * AliquotaIr.IRPF2.getAliquota() - AliquotaIr.IRPF2.getParcDeduzir();
		} else if (salarioBase >= AliquotaIr.IRPF3.getBase() && salarioBase < AliquotaIr.IRPF4.getBase()) {
			descontoIR = salarioBase * AliquotaIr.IRPF3.getAliquota() - AliquotaIr.IRPF3.getParcDeduzir();
		} else {
			descontoIR = salarioBase * AliquotaIr.IRPF4.getAliquota() - AliquotaIr.IRPF4.getParcDeduzir();
		}
	}

	@Override
	public void calcularINSS() {
		if (salarioBruto <= AliquotaInss.INSS1.getBaseInss()) {
			descontoInss = (salarioBruto * AliquotaInss.INSS1.getAliquotaInss())
					- AliquotaInss.INSS1.getParcDeduzirInss();
		} else if (salarioBruto > AliquotaInss.INSS1.getBaseInss()
				&& salarioBruto <= AliquotaInss.INSS2.getBaseInss()) {
			descontoInss = (salarioBruto * AliquotaInss.INSS2.getAliquotaInss())
					- AliquotaInss.INSS2.getParcDeduzirInss();
		} else if (salarioBruto > AliquotaInss.INSS2.getBaseInss()
				&& salarioBruto <= AliquotaInss.INSS3.getBaseInss()) {
			descontoInss = (salarioBruto * AliquotaInss.INSS3.getAliquotaInss())
					- AliquotaInss.INSS3.getParcDeduzirInss();
		} else if (salarioBruto > AliquotaInss.INSS3.getBaseInss()
				&& salarioBruto <= AliquotaInss.INSS4.getBaseInss()) {
			descontoInss = (salarioBruto * AliquotaInss.INSS4.getAliquotaInss())
					- AliquotaInss.INSS4.getParcDeduzirInss();
		} else {
			descontoInss = AliquotaInss.TETOSAL.getTetoSalario();
		}

	}

	@Override
	public void calcularSalarioLiquido() {
		salarioLiquido = salarioBruto - descontoInss - descontoIR;

	}

}