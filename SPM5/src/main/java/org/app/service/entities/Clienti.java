package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="clienti") 
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Clienti implements Serializable{
	@Id @GeneratedValue
	private Integer IdClient;
	private String DenSoc;
	private Integer CUI;
	private String Adresa;
	private String Telefon;
	private String Email;
	
	@OneToMany(mappedBy="clienti", cascade = ALL, fetch=FetchType.EAGER)
	private List<Proiecte> proiecte = new ArrayList<>();
	
	@OneToMany(mappedBy="clienti",cascade = ALL, fetch=FetchType.EAGER)
	private List<Suport> suport = new ArrayList<>();

	@XmlElement
	public Integer getIdClient() {
		return IdClient;
	}

	public void setIdClient(Integer idClient) {
		this.IdClient = idClient;
	}

	@XmlElement
	public String getDenSoc() {
		return DenSoc;
	}

	public void setDenSoc(String denSoc) {
		this.DenSoc = denSoc;
	}

	@XmlElement
	public Integer getCUI() {
		return CUI;
	}

	public void setCUI(Integer cUI) {
		this.CUI = cUI;
	}

	@XmlElement
	public String getAdresa() {
		return Adresa;
	}

	public void setAdresa(String adresa) {
		this.Adresa = adresa;
	}

	@XmlElement
	public String getTelefon() {
		return Telefon;
	}

	public void setTelefon(String telefon) {
		this.Telefon = telefon;
	}

	@XmlElement
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	//@XmlElements({@XmlElement(name = "proiecte_p", type = Proiecte.class)})
	public List<Proiecte> getProiecte() {
		return proiecte;
	}

	public void setProiecte(List<Proiecte> proiecte) {
		this.proiecte = proiecte;
	}

	//@XmlElements({@XmlElement(name = "suport_s", type = Suport.class)})
	public List<Suport> getSuport() {
		return suport;
	}

	public void setSuport(List<Suport> suport) {
		this.suport = suport;
	}

	public static String BASE_URL = "http://localhost:8080/SPM5/data/clientic/";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getIdClient();
        return new AtomLink(restUrl, "get-client");
    }	
	
	public void setLink(AtomLink link){
		
	}
	public Clienti(Integer idClient, String denSoc, Integer cUI, String adresa, String telefon, String email,
			List<Proiecte> proiecte, List<Suport> suport) {
		this.IdClient = idClient;
		this.DenSoc = denSoc;
		this.CUI = cUI;
		this.Adresa = adresa;
		this.Telefon = telefon;
		this.Email = email;
		this.proiecte = proiecte;
		this.suport = suport;
	}

	public Clienti() {
		super();
	}

	public Clienti(Integer idClient, String denSoc, Integer cUI, String adresa, String telefon, String email) {
		super();
		IdClient = idClient;
		DenSoc = denSoc;
		CUI = cUI;
		Adresa = adresa;
		Telefon = telefon;
		Email = email;
	}

	
}
