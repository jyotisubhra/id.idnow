package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import views.html.*;

import com.fasterxml.jackson.databind.JsonNode;

import models.*;
import play.data.*;
import play.libs.Json;
import play.mvc.*;

public class RestController extends Controller {
	
	@Inject
	FormFactory formFactory;

    public Result startIdentification() {
    	//Get the parsed JSON data
    	JsonNode json = request().body().asJson();
    	Identification ident = null;
    	//Do something with the identification
    	if (null != json) {
    		ident = Json.fromJson(json, Identification.class); 
    	} else {
    		Form<Identification> identForm = formFactory.form(Identification.class).bindFromRequest();
    		ident = identForm.get();
    	} 
    	ident.setIdntStartTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS")));
    	Identification.add(ident);    	
        return getAllIdents();
    }

    public Result addCompany() {
    	//Get the parsed JSON data
    	JsonNode json = request().body().asJson();  
    	Company company = null;
    	//Do something with the company
    	if (null != json) {
    		company = Json.fromJson(json, Company.class);  
    	} else {
    		Form<Company> compForm = formFactory.form(Company.class).bindFromRequest();
    		company = compForm.get();
    	}
    	Company.add(company);
    	return getAllCompanies();
    }

    public Result identifications() {
    	//JsonNode identifications = Json.newArray();
    	
    	//Get the current identification
    	//Compute correct order
    	//Create new identification JSON with JsonNode identification = Json.newObject();
    	//Add identification to identifications list 
    	
    	ArrayList<Identification> idents = new ArrayList<Identification>(Identification.getAllIdents());
		
		/*for (Identification comA : idents) {
            System.out.println(comA.getIdntId());
        }*/
		
		List<CommonAttributue> comattrts = new ArrayList<CommonAttributue>();
		Iterator<Identification> itr = idents.iterator(); 
		while (itr.hasNext()) {
			Identification ident = (Identification) itr.next();
			CommonAttributue comAtr = getCommonAttributes(ident, Company.getCompany(ident.getCompId()));
			comattrts.add(comAtr);
		}
		
		Comparator<CommonAttributue> compareById = (CommonAttributue o1, CommonAttributue o2) -> {
			
			if (o1.getCompId() == o2.getCompId()) {
				if (o1.getIdntWaitingTime() > o2.getIdntWaitingTime()) {
					return 1;
				} else if (o1.getIdntWaitingTime() == o2.getIdntWaitingTime()) {
					return 0;
				} else {
					return -1;
				}
			} else {
				if ((o1.getCurrentSlaPercentage() - o1.getSlaPercentage()) > (o2.getCurrentSlaPercentage() - o2.getSlaPercentage())) {
					return 1;
				} else if ((o1.getCurrentSlaPercentage() - o1.getSlaPercentage()) == (o2.getCurrentSlaPercentage() - o2.getSlaPercentage())) {
					return 0;
				} else {
					return -1;
				}
			}
		};
		
		Collections.sort(comattrts, compareById);
		List<Identification> results = getIdentList(comattrts);
		
		/*for (CommonAttributue comA : comattrts) {
            System.out.println(comA.getIdntId() + comA.getCurrentSlaPercentage());
        }*/
		
        return ok(Json.toJson(results));
    }
    
    private List<Identification> getIdentList(List<CommonAttributue> comattrts) {
    	
    	List<Identification> idents = new ArrayList<Identification>();
		for(CommonAttributue item : comattrts){
			Identification ident = Identification.getIdentification(item.getIdntId());
			idents.add(ident);
		}
		return idents;
	}

	private CommonAttributue getCommonAttributes(Identification ident, Company company) {
    	
    	CommonAttributue comAtt = new CommonAttributue();
		comAtt.setCompId(company.getCompId());
		comAtt.setIdntId(ident.getIdntId());
		comAtt.setIdntWaitingTime(ident.getIdntWaitingTime());
		comAtt.setSlaTime(company.getSlaTime());
		comAtt.setSlaPercentage(company.getSlaPercentage());
		comAtt.setCurrentSlaPercentage(company.getCurrentSlaPercentage());
		return comAtt;
	}

	
	public Result getAllCompanies() {
    	Set<Company> companies = Company.getAllCompanies();
    	//companies.stream().forEach((Company) -> {System.out.println(Company.getCompId());});
    	return ok(Json.toJson(companies));
    }
    
    public Result getAllIdents() {
    	Set<Identification> idents = Identification.getAllIdents();
    	//idents.stream().forEach(Identification -> System.out.println(Identification.getIdntId()));
    	return ok(Json.toJson(idents));
    }
    
    public Result removeIdent(int identId) {
    	Identification identification = Identification.getIdentification(identId);
    	if (identification != null) {
    		Set<Identification> idents = Identification.removeIdent(identification);
    		return ok(Json.toJson(idents));
    	} else {
    		return ok(Json.toJson("Identification not available to remove with Id " + identId));
    	}
    }
    
    public Result removeComp(int compId) {
    	Company company = Company.getCompany(compId);    	
    	if (company != null) {
    		Set<Company> companies = Company.removeComp(company);
    		return ok(Json.toJson(companies));
    	} else {
    		return ok(Json.toJson("Company not available to remove with Id " + compId));
    	}
    }
    
  //Create Identification from Form
    public Result createIdents() {
    	Form<Identification> identForm = formFactory.form(Identification.class);
    	return ok(createIdnt.render(identForm));
    }
    
    //Create company from Form
    public Result createComp() {
    	Form<Company> compForm = formFactory.form(Company.class);
    	return ok(createComp.render(compForm));
    }
    
    //edit Company from Form
    public Result editComp(int compId) {
    	Company company = Company.getCompany(compId); 
    	if (company != null) {
    		Form<Company> compForm = formFactory.form(Company.class).fill(company);
    		return ok(editComp.render(compForm));
    	} else {
    		return ok(Json.toJson("Company not available to edit with Id " + compId));
    	}   	
    }
    
    //edit Identification from Form
    public Result editIdent(int identId) {
    	Identification identification = Identification.getIdentification(identId);
    	if (identification != null) {
    		Form<Identification> identForm = formFactory.form(Identification.class).fill(identification);
        	return ok(editIdnt.render(identForm));
    	} else {
    		return ok(Json.toJson("Identification not available to edit with Id " + identId));
    	}
    }
    
    //Update Company
    public Result updateCompany() {
    	
    	Company company = formFactory.form(Company.class).bindFromRequest().get();
    	Company oldCompany = Company.getCompany(company.getCompId()); 
    	oldCompany.setCompName(company.getCompName());
    	oldCompany.setSlaTime(company.getSlaTime());
    	oldCompany.setSlaPercentage(company.getSlaPercentage());
    	oldCompany.setCurrentSlaPercentage(company.getCurrentSlaPercentage());
    	
    	return getAllCompanies();
    }
    
    //Update Identification
    public Result updateIdentification() {
    	
    	Identification ident = formFactory.form(Identification.class).bindFromRequest().get();
    	Identification oldIdent = Identification.getIdentification(ident.getIdntId());
    	
    	oldIdent.setCompId(ident.getIdntId());
    	oldIdent.setIdntName(ident.getIdntName());
    	oldIdent.setCompId(ident.getCompId());
    	
    	return getAllIdents();
    }

}
