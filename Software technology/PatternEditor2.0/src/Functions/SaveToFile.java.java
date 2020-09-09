package Functions;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveToFile {
	
	private String pattern_language_name;
	private ArrayList<String> micro_pattern_labels=new ArrayList<>();
	private ArrayList<String> inductive_pattern_labels=new ArrayList<>();
	private ArrayList<String> deductive_pattern_labels=new ArrayList<>();
	private ArrayList<String> gangfour_pattern_labels=new ArrayList<>();
	private ArrayList<String> systemof_pattern_labels=new ArrayList<>();
	
	private ArrayList<ArrayList<String>> micro_pattern_fields=new ArrayList<>();
	private ArrayList<ArrayList<String>> inductive_pattern_fields=new ArrayList<>();
	private ArrayList<ArrayList<String>> deductive_pattern_fields=new ArrayList<>();
	private ArrayList<ArrayList<String>> gangfour_pattern_fields=new ArrayList<>();
	private ArrayList<ArrayList<String>> systemof_pattern_fields=new ArrayList<>();
	private Boolean micro_bool;
	private Boolean inductive_bool;
	private Boolean deductive_bool;
	private Boolean gang_bool;
	private Boolean system_bool;
	private File file;

	
	public void setLabels(ArrayList<String> micro_pattern_labels,ArrayList<String> inductive_pattern_labels,ArrayList<String> deductive_pattern_labels,ArrayList<String> gangfour_pattern_labels,ArrayList<String> systemof_pattern_labels) {
		this.micro_pattern_labels=micro_pattern_labels;
		this.inductive_pattern_labels=inductive_pattern_labels;
		this.deductive_pattern_labels=deductive_pattern_labels;
		this.gangfour_pattern_labels=gangfour_pattern_labels;
		this.systemof_pattern_labels=systemof_pattern_labels;
	}
	public void setFields(String pattern_language_name,ArrayList<ArrayList<String>> micro_pattern_fields,ArrayList<ArrayList<String>> inductive_pattern_fields,ArrayList<ArrayList<String>> deductive_pattern_fields,ArrayList<ArrayList<String>> gangfour_pattern_fields,ArrayList<ArrayList<String>> systemof_pattern_fields) {
		this.micro_pattern_fields=micro_pattern_fields;
		this.inductive_pattern_fields=inductive_pattern_fields;
		this.deductive_pattern_fields=deductive_pattern_fields;
		this.gangfour_pattern_fields=gangfour_pattern_fields;
		this.systemof_pattern_fields=systemof_pattern_fields;
		this.pattern_language_name=pattern_language_name;
	}
	public void existenceOfPatterns(Boolean micro_bool,Boolean inductive_bool,Boolean deductive_bool,Boolean gang_bool,Boolean system_bool) {
		this.micro_bool=micro_bool;
		this.inductive_bool=inductive_bool;
		this.deductive_bool=deductive_bool;
		this.gang_bool=gang_bool;
		this.system_bool=system_bool;
	}
	
	public void saveAsTxt(File file) {
		
		try{
			PrintWriter w = new PrintWriter(file.getPath(), "UTF-8");
			w.println(pattern_language_name);
			w.println("");
			if(micro_bool) {
				for(int i=0;i<micro_pattern_fields.size();i++) {
					w.println(micro_pattern_labels.get(0));
					for(int j=0;j<micro_pattern_fields.get(i).size();j++) {
						w.print(micro_pattern_labels.get(j+1));
						w.println(micro_pattern_fields.get(i).get(j));
					}
				}
			}
			if(inductive_bool) {
				
				for(int i=0;i<inductive_pattern_fields.size();i++) {
					w.println(inductive_pattern_labels.get(0));
					for(int j=0;j<inductive_pattern_fields.get(i).size();j++) {
						w.print(inductive_pattern_labels.get(j+1));
						w.println(inductive_pattern_fields.get(i).get(j));
					}
				}
			}
			if(deductive_bool) {
				
				for(int i=0;i<deductive_pattern_fields.size();i++) {
					w.println(deductive_pattern_labels.get(0));
					for(int j=0;j<deductive_pattern_fields.get(i).size();j++) {
						w.print(deductive_pattern_labels.get(j+1));
						w.println(deductive_pattern_fields.get(i).get(j));
					}
				}
			}
			if(gang_bool) {
				
				for(int i=0;i<gangfour_pattern_fields.size();i++) {
					w.println(gangfour_pattern_labels.get(0));
					for(int j=0;j<gangfour_pattern_fields.get(i).size();j++) {
						w.print(gangfour_pattern_labels.get(j+1));
						w.println(gangfour_pattern_fields.get(i).get(j));
					}
				}
			}
			if(system_bool) {
				
				for(int i=0;i<systemof_pattern_fields.size();i++) {
					w.println(systemof_pattern_labels.get(0));
					for(int j=0;j<systemof_pattern_fields.get(i).size();j++) {
						w.print(systemof_pattern_labels.get(j+1));
						w.println(systemof_pattern_fields.get(i).get(j));
					}
				}
			}
			
			w.close();
			
		}catch(IOException execption) {
			
			
		}
		
	}
	
	public void saveAsLatex(File file) {
		
		try{
			PrintWriter w = new PrintWriter(file.getPath(), "UTF-8");
			w.println("\\documentclass[12pt]{article}\r\n" + 
					"\\usepackage{lingmacros}\r\n" + 
					"\\usepackage{tree-dvips}\r\n" + 
					"\\begin{document}");
			w.println(pattern_language_name);
			if(micro_bool) {
				for(int i=0;i<micro_pattern_fields.size();i++) {
					w.println(micro_pattern_labels.get(0));
					for(int j=0;j<micro_pattern_fields.get(i).size();j++) {
						w.print(micro_pattern_labels.get(j+1));
						w.println(micro_pattern_fields.get(i).get(j));
					}
				}
			}
			if(inductive_bool) {
				for(int i=0;i<inductive_pattern_fields.size();i++) {
					w.println(inductive_pattern_labels.get(0));
					for(int j=0;j<inductive_pattern_fields.get(i).size();j++) {
						w.print(inductive_pattern_labels.get(j+1));
						w.println(inductive_pattern_fields.get(i).get(j));
					}
				}
			}
			if(deductive_bool) {
				for(int i=0;i<deductive_pattern_fields.size();i++) {
					w.println(deductive_pattern_labels.get(0));
					for(int j=0;j<deductive_pattern_fields.get(i).size();j++) {
						w.print(deductive_pattern_labels.get(j+1));
						w.println(deductive_pattern_fields.get(i).get(j));
					}
				}
			}
			if(gang_bool) {
				for(int i=0;i<gangfour_pattern_fields.size();i++) {
					w.println(gangfour_pattern_labels.get(0));
					for(int j=0;j<gangfour_pattern_fields.get(i).size();j++) {
						w.print(gangfour_pattern_labels.get(j+1));
						w.println(gangfour_pattern_fields.get(i).get(j));
					}
				}
			}
			if(system_bool) {
				for(int i=0;i<systemof_pattern_fields.size();i++) {
					w.println(systemof_pattern_labels.get(0));
					for(int j=0;j<systemof_pattern_fields.get(i).size();j++) {
						w.print(systemof_pattern_labels.get(j+1));
						w.println(systemof_pattern_fields.get(i).get(i));
					}
				}
			}
			
			w.println("\\end{document}");
			w.close();
			
		}catch(IOException execption) {
			
			
		}
		
	}

}
