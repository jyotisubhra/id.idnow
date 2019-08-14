package models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Identification {
	
	private int idntId;
	private int compId;
	private String idntName;
	private String idntStartTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS"));
	private long idntWaitingTime = 0;
	private static Set<Identification> idents;
	
	
	public Identification(int idntId, int compId, String idntName, String idntStartTime, long idntWaitingTime) {
		super();
		this.idntId = idntId;
		this.compId = compId;
		this.idntName = idntName;
		this.idntStartTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS"));
		this.idntWaitingTime = 0;
	}
	
	public Identification() {		
	}
	
	static {
		idents = new HashSet<Identification>();
		idents.add(new Identification(1, 1, "Jyoti", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS")), new Long(0)));
		idents.add(new Identification(2, 2, "Subhra", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS")), new Long(0)));
	}
	
	public static Set<Identification> getAllIdents() {
		idents.stream().forEach(Identification -> {
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
			 LocalDateTime dateTime = LocalDateTime.parse(Identification.getIdntStartTime(), formatter);
			 Duration dur = Duration.between(dateTime, LocalDateTime.now());
			 Identification.setIdntWaitingTime(dur.getSeconds());
		});
		return idents;
	}
	
	public static Identification getIdentification(int idntId) {
		Identification identification = null;
		Iterator<Identification> itr = idents.iterator(); 
		while (itr.hasNext()) {
			Identification ident = (Identification) itr.next();
			if (ident.getIdntId() == idntId) {
				identification = ident;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
				LocalDateTime dateTime = LocalDateTime.parse(identification.getIdntStartTime(), formatter);
				Duration dur = Duration.between(dateTime, LocalDateTime.now());
				identification.setIdntWaitingTime(dur.getSeconds());
				break;
					
			}
		}
		return identification;
	}
	
	public static void add(Identification ident) {
		idents.add(ident);
	}

	public int getIdntId() {
		return idntId;
	}

	public void setIdntId(int idntId) {
		this.idntId = idntId;
	}

	public String getIdntName() {
		return idntName;
	}

	public void setIdntName(String idntName) {
		this.idntName = idntName;
	}

	public long getIdntWaitingTime() {
		return idntWaitingTime;
	}

	public void setIdntWaitingTime(long idntWaitingTime) {
		this.idntWaitingTime = idntWaitingTime;
	}

	public String getIdntStartTime() {
		return idntStartTime;
	}

	public void setIdntStartTime(String idntStartTime) {
		this.idntStartTime = idntStartTime;
	}

	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public static Set<Identification> removeIdent(Identification identification) {
		idents.remove(identification);
		return idents;
	}
	

}
