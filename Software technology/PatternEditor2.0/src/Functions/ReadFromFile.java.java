package Functions;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFromFile {
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
	private String line;
	
	public void setLabels(ArrayList<String> micro_pattern_labels,ArrayList<String> inductive_pattern_labels,ArrayList<String> deductive_pattern_labels,ArrayList<String> gangfour_pattern_labels,ArrayList<String> systemof_pattern_labels) {
		this.micro_pattern_labels=micro_pattern_labels;
		this.inductive_pattern_labels=inductive_pattern_labels;
		this.deductive_pattern_labels=deductive_pattern_labels;
		this.gangfour_pattern_labels=gangfour_pattern_labels;
		this.systemof_pattern_labels=systemof_pattern_labels;
	}
	
	public void readFromTxt(File file) {
		try{
			
			BufferedReader buff = new BufferedReader(new FileReader(file));
			pattern_language_name = buff.readLine();
			while (( line=buff.readLine()) != null) {
				ArrayList<String> temp_micro=new ArrayList<String>();
				ArrayList<String> temp_ind=new ArrayList<String>();
				ArrayList<String> temp_ded=new ArrayList<String>();
				ArrayList<String> temp_gang=new ArrayList<String>();
				ArrayList<String> temp_sys=new ArrayList<String>();
				
				if(line.equals(micro_pattern_labels.get(0))) {
					micro_bool=true;
					
					for(int i=1;i<micro_pattern_labels.size();i++) {
						line=buff.readLine();
						line=line.replace(micro_pattern_labels.get(i), "");
						temp_micro.add(line);
					}
					micro_pattern_fields.add(temp_micro);
				}
				if(line.equals(inductive_pattern_labels.get(0))) {
					inductive_bool=true;
					for(int i=1;i<inductive_pattern_labels.size();i++) {
						line=buff.readLine();
						line=line.replace(inductive_pattern_labels.get(i), "");
						temp_ind.add(line);
					}
					inductive_pattern_fields.add(temp_ind);
				}
				if(line.equals(deductive_pattern_labels.get(0))) {
					deductive_bool=true;
					for(int i=1;i<deductive_pattern_labels.size();i++) {
						line=buff.readLine();
						line=line.replace(deductive_pattern_labels.get(i), "");
						temp_ded.add(line);
					}
					deductive_pattern_fields.add(temp_ded);
				}
				if(line.equals(gangfour_pattern_labels.get(0))) {
					gang_bool=true;
					for(int i=1;i<gangfour_pattern_labels.size();i++) {
						line=buff.readLine();
						line=line.replace(gangfour_pattern_labels.get(i), "");
						temp_gang.add(line);
					}
					gangfour_pattern_fields.add(temp_gang);
				}
				if(line.equals(systemof_pattern_labels.get(0))) {
					system_bool=true;
					for(int i=1;i<systemof_pattern_labels.size();i++) {
						line=buff.readLine();
						line=line.replace(systemof_pattern_labels.get(i), "");
						temp_sys.add(line);
					}
					systemof_pattern_fields.add(temp_sys);
				}
				
			}
			all_fields.add(micro_pattern_fields);all_fields.add(inductive_pattern_fields);all_fields.add(deductive_pattern_fields);
			all_fields.add(gangfour_pattern_fields);all_fields.add(systemof_pattern_fields);
			
			
		}catch (IOException e) {
			
			
		}
		
		
	}
	
	public void readFromLatex(File file) {
		try{
			
			BufferedReader buff = new BufferedReader(new FileReader(file));
			for(int i=0;i<3;i++) {
				buff.readLine();
			}
			
			pattern_language_name = buff.readLine();
			while (( line=buff.readLine()) != null) {
				ArrayList<String> temp_micro=new ArrayList<String>();
				ArrayList<String> temp_ind=new ArrayList<String>();
				ArrayList<String> temp_ded=new ArrayList<String>();
				ArrayList<String> temp_gang=new ArrayList<String>();
				ArrayList<String> temp_sys=new ArrayList<String>();
				if(line.equals(micro_pattern_labels.get(0))) {
					micro_bool=true;
					for(int i=1;i<micro_pattern_labels.size();i++) {
						line=buff.readLine();
						line=line.replace(micro_pattern_labels.get(i), "");
						temp_micro.add(line);
					}
					micro_pattern_fields.add(temp_micro);
				}
				if(line.equals(inductive_pattern_labels.get(0))) {
					inductive_bool=true;
					for(int i=1;i<inductive_pattern_labels.size();i++) {
						line=buff.readLine();
						line=line.replace(inductive_pattern_labels.get(i), "");
						temp_ind.add(line);
					}
					inductive_pattern_fields.add(temp_ind);
				}
				if(line.equals(deductive_pattern_labels.get(0))) {
					deductive_bool=true;
					for(int i=1;i<deductive_pattern_labels.size();i++) {
						line=buff.readLine();
						line=line.replace(deductive_pattern_labels.get(i), "");
						temp_ded.add(line);
					}
					deductive_pattern_fields.add(temp_ded);
				}
				if(line.equals(gangfour_pattern_labels.get(0))) {
					gang_bool=true;
					for(int i=1;i<gangfour_pattern_labels.size();i++) {
						line=buff.readLine();
						line=line.replace(gangfour_pattern_labels.get(i), "");
						temp_gang.add(line);
					}
					gangfour_pattern_fields.add(temp_gang);
				}
				if(line.equals(systemof_pattern_labels.get(0))) {
					system_bool=true;
					for(int i=1;i<systemof_pattern_labels.size();i++) {
						line=buff.readLine();
						line=line.replace(systemof_pattern_labels.get(i), "");
						temp_sys.add(line);
					}
					systemof_pattern_fields.add(temp_sys);
				}
				
			}
			all_fields.add(micro_pattern_fields);all_fields.add(inductive_pattern_fields);all_fields.add(deductive_pattern_fields);
			all_fields.add(gangfour_pattern_fields);all_fields.add(systemof_pattern_fields);
			
		}catch (IOException e) {
			
			
		}
		
		
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
	
	
	public ArrayList<ArrayList<ArrayList<String>>> getFields(){
		return all_fields;
	}
	
	public String getLangugaeName() {
		return pattern_language_name;
	}
	

}
