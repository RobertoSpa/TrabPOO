package enums;

public enum AliquotaIr {
	IRPF1(1903.98, 0.075, 142.8),IRPF2(2826.66, 0.15, 354.8),IRPF3(3751.06, 0.225, 636.13),IRPF4(4664.68, 0.275, 869.36), IR(0., 189.59);
	
	private Double base;
	private Double aliquota;
	private Double parcDeduzir;
	private Double minBase;
	private Double baseDep;
	
	private AliquotaIr(Double base, Double aliquota, Double parcDeduzir) {
		this.base = base;
		this.aliquota = aliquota;
		this.parcDeduzir = parcDeduzir;
	}

	private AliquotaIr(Double minBase, Double baseDep) {
		this.minBase = minBase;
		this.baseDep = baseDep;
	}

	public Double getBase() {
		return base;
	}

	public Double getAliquota() {
		return aliquota;
	}

	public Double getParcDeduzir() {
		return parcDeduzir;
	}

	public Double getMinBase() {
		return minBase;
	}

	public Double getBaseDep() {
		return baseDep;
	}

}
