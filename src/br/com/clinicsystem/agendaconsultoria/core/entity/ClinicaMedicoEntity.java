package br.com.clinicsystem.agendaconsultoria.core.entity;

import java.util.Objects;

public class ClinicaMedicoEntity {

    private Long id;
    private Long fk_idMedico;
    private Long fk_idClinica;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFk_idMedico() {
        return fk_idMedico;
    }

    public void setFk_idMedico(Long fk_idMedico) {
        this.fk_idMedico = fk_idMedico;
    }

    public Long getFk_idClinica() {
        return fk_idClinica;
    }

    public void setFk_idClinica(Long fk_idClinica) {
        this.fk_idClinica = fk_idClinica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClinicaMedicoEntity that = (ClinicaMedicoEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
