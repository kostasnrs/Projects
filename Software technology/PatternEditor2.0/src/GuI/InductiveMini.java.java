package GuI;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class InductiveMini{
	private JTextField textField;
	private JTextField textField_1;
	private boolean isCreated=false;
	private ArrayList<String> fields=new ArrayList<String>();
	private ArrayList<String> labels=new ArrayList<String>();
	private JTextPane textPane;
	private JTextPane textPane_1;
	private JTextPane textPane_2;
	/**
	 * Create the panel.
	 */
	public InductiveMini(JPanel panel,boolean isCreated) {
		
		this.isCreated=isCreated;
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setBounds(0, 22, 1180, 1100);
		
		JLabel lblNewLabel = new JLabel("INDUCTIVE MINI-PATTERN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(484, 11, 325, 37);
		panel.add(lblNewLabel);
		labels.add("INDUCTIVE MINI-PATTERN");
		
		JLabel lblname = new JLabel("1.NAME:");
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblname.setBounds(10, 108, 110, 37);
		panel.add(lblname);
		labels.add("1.NAME:");
		
		textField = new JTextField();
		textField.setToolTipText("What shall this pattern be called by practitioners? ");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 21));
		textField.setBounds(310, 110, 379, 37);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lbltemplate = new JLabel("2.TEMPLATE:");
		lbltemplate.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbltemplate.setBounds(10, 252, 242, 37);
		panel.add(lbltemplate);
		labels.add("2.TEMPLATE:");
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("What shall this pattern be called by Which template is followed for the pattern specification ? ");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		textField_1.setColumns(10);
		textField_1.setBounds(310, 252, 379, 48);
		panel.add(textField_1);
		
		JLabel lblcontext = new JLabel("3.CONTEXT:");
		lblcontext.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblcontext.setBounds(10, 392, 242, 37);
		panel.add(lblcontext);
		labels.add("3.CONTEXT:");
		
		textPane = new JTextPane();
		textPane.setToolTipText("What are the assumed environment or a priori assumptions for applying this pattern? ");
		textPane.setBounds(310, 409, 650, 218);
		panel.add(textPane);
		
		JScrollPane scroll=new JScrollPane(textPane);
		scroll.setBounds(310, 409, 650, 218);
		panel.add(scroll);
		
		JLabel lblforces = new JLabel("4.FORCES:");
		lblforces.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblforces.setBounds(10, 698, 242, 37);
		panel.add(lblforces);
		labels.add("4.FORCES:");
		
		textPane_1 = new JTextPane();
		textPane_1.setToolTipText(" What are the different design motivations that must be balanced? ");
		textPane_1.setBounds(310, 701, 650, 218);
		panel.add(textPane_1);
		
		JScrollPane scroll_1=new JScrollPane(textPane_1);
		scroll_1.setBounds(310, 701, 650, 218);
		panel.add(scroll_1);
		
		JLabel lblsolution = new JLabel("5.SOLUTION:");
		lblsolution.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblsolution.setBounds(10, 960, 242, 37);
		panel.add(lblsolution);
		labels.add("5.SOLUTION:");
		
		textPane_2 = new JTextPane();
		textPane_2.setToolTipText(" How do we solve the problem?");
		textPane_2.setBounds(310, 960, 650, 218);
		
		JScrollPane scroll_2=new JScrollPane(textPane_2);
		scroll_2.setBounds(310, 960, 650, 218);
		panel.add(scroll_2);

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
		textPane.setText(fields.get(2));
		textPane_1.setText(fields.get(3));
		textPane_2.setText(fields.get(4));
	}
	
	public void saveFields() {
		if(fields.size()==0) {
			fields.add(textField.getText());
			fields.add(textField_1.getText());
			fields.add(textPane.getText());
			fields.add(textPane_1.getText());
			fields.add(textPane_2.getText());

		}else {
			fields.set(0,textField.getText());
			fields.set(1,textField_1.getText());
			fields.set(2,textPane.getText());
			fields.set(3,textPane_1.getText());
			fields.set(4,textPane_2.getText());

	
		}
	}
	
	public ArrayList<String> getFields() {
		return fields;
	}
	
	public ArrayList<String> getLabels(){
		return labels;
	}
}
