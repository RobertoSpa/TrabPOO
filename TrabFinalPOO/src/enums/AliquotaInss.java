package enums;

public enum AliquotaInss {
	INSS1(1212., 0.075, 0.),INSS2(2427.35, 0.09, 18.18),INSS3(3641.03, 0.12, 91.0),INSS4(7087.22, 0.14, 163.82), TETOSAL(828.39);
	
	private Double baseInss;
	private Double aliquotaInss;
	private Double parcDeduzirInss;
	private Double tetoSalario;
	
	private AliquotaInss(Double baseInss, Double aliquotaInss, Double parcDeduzirInss) {
		this.baseInss = baseInss;
		this.aliquotaInss = aliquotaInss;
		this.parcDeduzirInss = parcDeduzirInss;
	}

	private AliquotaInss(Double tetoSalario) {
		this.tetoSalario = tetoSalario;
	}

	public Double getBaseInss() {
		return baseInss;
	}

	public Double getAliquotaInss() {
		return aliquotaInss;
	}

	public Double getParcDeduzirInss() {
		return parcDeduzirInss;
	}

	public Double getTetoSalario() {
		return tetoSalario;
	}
	
}