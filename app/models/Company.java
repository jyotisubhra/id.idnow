package models;

import java.util.*;
import java.util.Set;

public class Company {
	
	private int compId;
	private String compName;
	private long slaTime;
	private double slaPercentage;
	private double currentSlaPercentage;
	private static Set<Company> companies;
	
	public Company() {
		
	}
	
	public Company(int compId, String compName, long slaTime, double slaPercentage, double currentSlaPercentage) {
		super();
		this.compId = compId;
		this.compName = compName;
		this.slaTime = slaTime;
		this.slaPercentage = slaPercentage;
		this.currentSlaPercentage = currentSlaPercentage;
	}
	
	static {
		companies = new HashSet<Company>();
		companies.add(new Company(1, "IBM", new Long(60), 0.9, 0.95));
		companies.add(new Company(2, "HCL", new Long(120), 0.8, 0.95));
	}
	
	public static Set<Company> getAllCompanies() {
		return companies;
	}
	
	public static Company getCompany(int compId) {
		
		Company company = null;
		Iterator<Company> itr = companies.iterator(); 
		while (itr.hasNext()) {
			Company comp = (Company) itr.next();
			if (comp.getCompId() == compId) {
				company = comp;
				break;
					
			}
		}
		return company;
	}
	
	public static void add(Company company) {
		companies.add(company);
	}

	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public long getSlaTime() {
		return slaTime;
	}

	public void setSlaTime(long slaTime) {
		this.slaTime = slaTime;
	}

	public double getSlaPercentage() {
		return slaPercentage;
	}

	public void setSlaPercentage(double slaPercentage) {
		this.slaPercentage = slaPercentage;
	}

	public double getCurrentSlaPercentage() {
		return currentSlaPercentage;
	}

	public void setCurrentSlaPercentage(double currentSlaPercentage) {
		this.currentSlaPercentage = currentSlaPercentage;
	}

	public static Set<Company> removeComp(Company company) {
		companies.remove(company);
		return companies;
	}
	
}
