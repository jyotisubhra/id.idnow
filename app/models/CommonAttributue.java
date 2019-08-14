package models;

public class CommonAttributue {
	
	private int idntId;
	private int compId;
	private long idntWaitingTime;
	private long slaTime;
	private double slaPercentage;
	private double currentSlaPercentage;
	public int getIdntId() {
		return idntId;
	}
	public void setIdntId(int idntId) {
		this.idntId = idntId;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public long getIdntWaitingTime() {
		return idntWaitingTime;
	}
	public void setIdntWaitingTime(long idntWaitingTime) {
		this.idntWaitingTime = idntWaitingTime;
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
	
	

}
