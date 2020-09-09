package Functions;
import java.io.File;
import java.util.ArrayList;

public class CollectFields {
	private ArrayList<ArrayList<ArrayList<String>>> all_fields=new ArrayList<ArrayList<ArrayList<String>>>();
	private String pattern_language_name;
	private ArrayList<ArrayList<String>> micro_pattern_fields=new ArrayList<>();
	private ArrayList<ArrayList<String>> inductive_pattern_fields=new ArrayList<>();
	private ArrayList<ArrayList<String>> deductive_pattern_fields=new ArrayList<>();
	private ArrayList<ArrayList<String>> gangfour_pattern_fields=new ArrayList<>();
	private ArrayList<ArrayList<String>> systemof_pattern_fields=new ArrayList<>();
	
	private ArrayList<String> micro_pattern_labels=new ArrayList<>();
	private ArrayList<String> inductive_pattern_labels=new ArrayList<>();
	private ArrayList<String> deductive_pattern_labels=new ArrayList<>();
	private ArrayList<String> gangfour_pattern_labels=new ArrayList<>();
	private ArrayList<String> systemof_pattern_labels=new ArrayList<>();
	private Boolean micro_bool=false;
	private Boolean inductive_bool=false;
	private Boolean deductive_bool=false;
	private Boolean gang_bool=false;
	private Boolean system_bool=false;
	private SaveToFile save=new SaveToFile();
	private ReadFromFile read=new ReadFromFile();
	
	
	public void setPatternName(String pattern_language_name) {
		this.pattern_language_name=pattern_language_name;
	}

	
	public void setMicroPatternFields(ArrayList<ArrayList<String>> micro_pattern_fields) {
		this.micro_pattern_fields=micro_pattern_fields;
	}
	
	public void setMicroPatternLabels(ArrayList<String> micro_pattern_labels) {
		this.micro_pattern_labels=micro_pattern_labels;
	}
	
	
	
	public void setInductivePatternFields(ArrayList<ArrayList<String>> inductive_pattern_fields) {
		this.inductive_pattern_fields=inductive_pattern_fields;
	}
	
	public void setInductivePatternLabels(ArrayList<String> inductive_pattern_labels) {
		this.inductive_pattern_labels=inductive_pattern_labels;
	}
	
	
	
	public void setDeductivePatternFields(ArrayList<ArrayList<String>> deductive_pattern_fields) {
		this.deductive_pattern_fields=deductive_pattern_fields;
	}
	
	public void setDeductivePatternLabels(ArrayList<String> deductive_pattern_labels) {
		this.deductive_pattern_labels=deductive_pattern_labels;
	}
	
	
	
	public void setGangFourPatternFields(ArrayList<ArrayList<String>> gangfour_pattern_fields) {
		this.gangfour_pattern_fields=gangfour_pattern_fields;
	}
	
	public void setGangFourPatternLabels(ArrayList<String> gangfour_pattern_labels) {
		this.gangfour_pattern_labels=gangfour_pattern_labels;
	}
	
	
	public void setSystemofPatternsFields(ArrayList<ArrayList<String>> systemof_pattern_fields) {
		this.systemof_pattern_fields=systemof_pattern_fields;
	}
	
	public void setSystemofPatternsLabels(ArrayList<String> systemof_pattern_labels) {
		this.systemof_pattern_labels=systemof_pattern_labels;
	}
	
	public  ArrayList<ArrayList<String>> getMicroFields() {
		return all_fields.get(0);
	}
	public  ArrayList<ArrayList<String>> getInductiveFields() {
		return  all_fields.get(1);
	}
	public  ArrayList<ArrayList<String>> getDeductiveFields() {
		return  all_fields.get(2);
	}
	public  ArrayList<ArrayList<String>> getGangFields() {
		return  all_fields.get(3);
	}
	public  ArrayList<ArrayList<String>> getSystemFields() {
		return  all_fields.get(4);
	}
	public String getLangugaeName() {
		return pattern_language_name;
	}
	
	public void setExistenceOfMicro(Boolean micro_bool) {
		this.micro_bool=micro_bool;
	}
	public void setExistenceOfInductive(Boolean inductive_bool) {
		this.inductive_bool=inductive_bool;
	}
	public void setExistenceOfDeductive(Boolean deductive_bool) {
		this.deductive_bool=deductive_bool;
	}
	public void setExistenceOfGang(Boolean gang_bool) {
		this.gang_bool=gang_bool;
	}
	public void setExistenceOfSystem(Boolean system_bool) {
		this.system_bool=system_bool;
	}
	
	public Boolean getExistenceOfMicro() {
		return micro_bool;
	}
	public Boolean getExistenceOfInductive() {
		return inductive_bool;
	}
	public Boolean getExistenceOfDeductive() {
		return deductive_bool;
	}
	public Boolean getExistenceOfGang() {
		return gang_bool;
	}
	public Boolean getExistenceOfSystem() {
		return system_bool;
	}
	
	
	public void saveToTxt(File file) {
		save.setLabels(micro_pattern_labels, inductive_pattern_labels, deductive_pattern_labels, gangfour_pattern_labels, systemof_pattern_labels);
		save.setFields(pattern_language_name,micro_pattern_fields, inductive_pattern_fields, deductive_pattern_fields, gangfour_pattern_fields, systemof_pattern_fields);
		save.existenceOfPatterns(micro_bool, inductive_bool, deductive_bool, gang_bool, system_bool);
		save.saveAsTxt(file);
	}
	
	public void saveToLatex(File file) {
		save.setLabels(micro_pattern_labels, inductive_pattern_labels, deductive_pattern_labels, gangfour_pattern_labels, systemof_pattern_labels);
		save.setFields(pattern_language_name,micro_pattern_fields, inductive_pattern_fields, deductive_pattern_fields, gangfour_pattern_fields, systemof_pattern_fields);
		save.existenceOfPatterns(micro_bool, inductive_bool, deductive_bool, gang_bool, system_bool);
		save.saveAsLatex(file);
	}
	
	public void readFromTxt(File file) {
		read.setLabels(micro_pattern_labels, inductive_pattern_labels, deductive_pattern_labels, gangfour_pattern_labels, systemof_pattern_labels);
		read.readFromTxt(file);
		all_fields=read.getFields();
		pattern_language_name=read.getLangugaeName();
		setExistenceOfMicro(read.getExistenceOfMicro());
		setExistenceOfInductive(read.getExistenceOfInductive());
		setExistenceOfDeductive(read.getExistenceOfDeductive());
		setExistenceOfGang(read.getExistenceOfGang());
		setExistenceOfSystem(read.getExistenceOfSystem());
	}
	
	public void readFromLatex(File file) {
		read.setLabels(micro_pattern_labels, inductive_pattern_labels, deductive_pattern_labels, gangfour_pattern_labels, systemof_pattern_labels);
		read.readFromLatex(file);
		all_fields=read.getFields();
		pattern_language_name=read.getLangugaeName();
		setExistenceOfMicro(read.getExistenceOfMicro());
		setExistenceOfInductive(read.getExistenceOfInductive());
		setExistenceOfDeductive(read.getExistenceOfDeductive());
		setExistenceOfGang(read.getExistenceOfGang());
		setExistenceOfSystem(read.getExistenceOfSystem());
	}
}
