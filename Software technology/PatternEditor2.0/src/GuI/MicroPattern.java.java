package GuI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;

public class MicroPattern{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private boolean isCreated=false;
	private ArrayList<String> fields=new ArrayList<String>();
	private ArrayList<String> labels=new ArrayList<String>();

	/**
	 * Create the panel.
	 */
	public MicroPattern(JPanel panel,boolean isCreated) {
		this.isCreated=isCreated;
		
		panel.setLayout(null);
		
		panel.setBounds(10, 0, 1190, 850);
		
		JLabel lblMicropatternTemplate = new JLabel("MICRO-PATTERN TEMPLATE");
		lblMicropatternTemplate.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblMicropatternTemplate.setBounds(396, 11, 381, 74);
		panel.add(lblMicropatternTemplate);
		labels.add("MICRO-PATTERN TEMPLATE");
		
		JLabel lblName = new JLabel("1.NAME:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblName.setBounds(71, 148, 109, 38);
		panel.add(lblName);
		labels.add("1.NAME:");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(168, 149, 528, 38);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lbltemplate = new JLabel("2.TEMPLATE:");
		lbltemplate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltemplate.setBounds(71, 341, 133, 44);
		panel.add(lbltemplate);
		labels.add("2.TEMPLATE:");
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setBounds(196, 347, 500, 38);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblproblem = new JLabel("3.PROBLEM:");
		lblproblem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblproblem.setBounds(71, 516, 133, 44);
		panel.add(lblproblem);
		labels.add("3.PROBLEM:");
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(207, 522, 500, 38);
		panel.add(textField_2);
		
		JLabel lblsolution = new JLabel("4.SOLUTION:");
		lblsolution.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblsolution.setBounds(71, 699, 133, 44);
		panel.add(lblsolution);
		labels.add("4.SOLUTION");
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(207, 703, 500, 38);
		panel.add(textField_3);

	}
	
	public boolean getCreation() {
		
		return isCreated;
	}
	
	public void setCreation(boolean isCreated) {
		
		this.isCreated=isCreated;
	}
	
	public void setFields(ArrayList<String> fields) {
		this.fields=fields;
		textField.setText(fields.get(0));
		textField_1.setText(fields.get(1));
		textField_2.setText(fields.get(2));
		textField_3.setText(fields.get(3));
	}
	
	

	public void saveFields() {
		if(fields.size()==0) {
			fields.add(textField.getText());
			fields.add(textField_1.getText());
			fields.add(textField_2.getText());
			fields.add(textField_3.getText());
		}else {
			fields.set(0,textField.getText());
			fields.set(1,textField_1.getText());
			fields.set(2,textField_2.getText());
			fields.set(3,textField_3.getText());
		}
		
	}
	
	public ArrayList<String> getFields() {
		return fields;
	}
	
	public ArrayList<String> getLabels(){
		return labels;
	}

}
