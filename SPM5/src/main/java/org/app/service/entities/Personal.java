package org.app.service.entities;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

@XmlRootElement(name="personal")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Personal implements Serializable{
	@Id @GeneratedValue
	private Integer IdTrainer;
	private String Nume;
	private String Prenume;
	private Integer Experienta;
	private String Specializare;
	private String Telefon;
	private String Email;

	@OneToMany(mappedBy="personal",cascade = ALL, fetch=FetchType.EAGER)
	private List<Proiecte> proiecte = new ArrayList<>();
	

	@OneToMany(mappedBy="personal",cascade = ALL, fetch=FetchType.EAGER)
	private List<Suport> suport = new ArrayList<>();

	@XmlElement
	public Integer getIdTrainer() {
		return IdTrainer;
	}

	public void setIdTrainer(Integer idTrainer) {
		this.IdTrainer = idTrainer;
	}

	@XmlElement
	public String getNume() {
		return Nume;
	}

	public void setNume(String nume) {
		this.Nume = nume;
	}

	@XmlElement
	public String getPrenume() {
		return Prenume;
	}

	public void setPrenume(String prenume) {
		this.Prenume = prenume;
	}

	@XmlElement
	public Integer getExperienta() {
		return Experienta;
	}

	public void setExperienta(Integer experienta) {
		this.Experienta = experienta;
	}

	@XmlElement
	public String getSpecializare() {
		return Specializare;
	}

	public void setSpecializare(String specializare) {
		this.Specializare = specializare;
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

	@XmlElements({@XmlElement(name = "proiecte_p", type = Proiecte.class)})
	public List<Proiecte> getProiecte() {
		return proiecte;
	}

	public void setProiecte(List<Proiecte> proiecte) {
		this.proiecte = proiecte;
	}

	@XmlElements({@XmlElement(name = "suport_s", type = Suport.class)})
	public List<Suport> getSuport() {
		return suport;
	}

	public void setSuport(List<Suport> suport) {
		this.suport = suport;
	}

	public static String BASE_URL = "http://localhost:8080/SPM5/data/personalp/";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getIdTrainer();
        return new AtomLink(restUrl, "get-personal");
    }	
	
	public void setLink(AtomLink link){
		
	}
	
	public Personal(Integer idTrainer, String nume, String prenume, Integer experienta, String specializare,
			String telefon, String email, List<Proiecte> proiecte, List<Suport> suport) {
		this.IdTrainer = idTrainer;
		this.Nume = nume;
		this.Prenume = prenume;
		this.Experienta = experienta;
		this.Specializare = specializare;
		this.Telefon = telefon;
		this.Email = email;
		this.proiecte = proiecte;
		this.suport = suport;
	}

	public Personal() {
		super();
	}

	public Personal(Integer idTrainer, String nume, String prenume, Integer experienta, String specializare,
			String telefon, String email) {
		super();
		IdTrainer = idTrainer;
		Nume = nume;
		Prenume = prenume;
		Experienta = experienta;
		Specializare = specializare;
		Telefon = telefon;
		Email = email;
	}

}
