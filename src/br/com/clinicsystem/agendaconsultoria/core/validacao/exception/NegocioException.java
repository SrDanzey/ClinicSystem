package br.com.clinicsystem.agendaconsultoria.core.validacao.exception;

public class NegocioException extends Exception{

    private String mensagemErro;

    public NegocioException(){}

    public NegocioException(String excessao){
        setMensagemErro(excessao);
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}
