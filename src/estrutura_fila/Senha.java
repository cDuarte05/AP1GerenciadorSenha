package estrutura_fila;
import java.time.LocalDateTime;

public class Senha {
	
	private String tipo; //ainda to pensando se uso string ou boolean
	private int tentativas;
	private String status; //ainda to pensando se uso string ou boolean
	private LocalDateTime horaChegada;
	private LocalDateTime horaSaida;
	private int numeroSenha;
	
	public Senha(String tipo,String status, int numeroSenha) {
		this.tipo = tipo;
		this.status = status;
		this.horaChegada = LocalDateTime.now(); //registra a hora do começo automaticamente
		this.tentativas = 0; //inicio
		this.numeroSenha = numeroSenha;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getTentativas() {
		return tentativas;
	}
	
	public void setTentativas(int tentativas) {
		this.tentativas = tentativas;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalDateTime getHoraChegada() {
        return horaChegada; // Retorna a hora original
    }
	
    public LocalDateTime getHoraSaida() {
        return horaSaida; // Retorna null se ainda não foi registrada
    }
    
    public void setHoraSaida() {
        this.horaSaida = LocalDateTime.now();
    }
    
    public int getNumeroSenha() {
    	return numeroSenha;
    }
    
    public String toString() {
    	return "Senha [" + numeroSenha + "] - " + tipo;
    }
}
