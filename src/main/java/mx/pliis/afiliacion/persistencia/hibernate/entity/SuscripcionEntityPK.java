package mx.pliis.afiliacion.persistencia.hibernate.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class SuscripcionEntityPK implements Serializable {
	@Basic(optional = false)
    @NotNull
    @Column(name = "id_plan")
    private int idPlan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_miembro")
    private int idMiembro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pago")
    private int idPago;
    
    public SuscripcionEntityPK() {
    }
    
    public SuscripcionEntityPK(int idPlan, int idMiembro, int idPago) {
        this.idPlan = idPlan;
        this.idMiembro = idMiembro;
        this.idPago = idPago;
    }

	public int getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public int getIdMiembro() {
		return idMiembro;
	}

	public void setIdMiembro(int idMiembro) {
		this.idMiembro = idMiembro;
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}
}
