package GuI;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTextPane;

public class DeductiveMini {
	private JTextField textField;
	private JTextField textField_1;
	private boolean isCreated=false;
	private ArrayList<String> fields=new ArrayList<String>();
	private ArrayList<String> labels=new ArrayList<String>();
	private JTextPane textPane_1;
	private JTextPane textPane;
	private JTextPane textPane_2;
	private JTextPane textPane_3;
	
	/**
	 * Create the panel.
	 */
	public DeductiveMini(JPanel panel,boolean isCreated) {
		this.isCreated=isCreated;
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setBounds(0, 22, 1180, 1100);
	
		
		JLabel lblNewLabel = new JLabel("DEDUCTIVE MINI PATTERN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(428, 22, 324, 59);
		panel.add(lblNewLabel);
		
		labels.add("DEDUCTIVE MINI PATTERN");
		
		JLabel lblNewLabel_1 = new JLabel("1.NAME:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(10, 113, 93, 30);
		panel.add(lblNewLabel_1);
		
		labels.add("1.NAME:");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setToolTipText("What shall this pattern be called by practitioners? ");
		textField.setBounds(230, 113, 665, 49);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lbltemplate = new JLabel("2.TEMPLATE:");
		lbltemplate.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbltemplate.setBounds(10, 286, 141, 30);
		panel.add(lbltemplate);
		
		labels.add("2.TEMPLATE:");
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Which template is followed for the pattern specification ? ");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(230, 281, 665, 49);
		panel.add(textField_1);
		
		JLabel lblproblem = new JLabel("3.PROBLEM:");
		lblproblem.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblproblem.setBounds(10, 493, 141, 30);
		panel.add(lblproblem);
		
		labels.add("3.PROBLEM:");
		
		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_1.setToolTipText("What is motivating us to apply this pattern? ");
		textPane_1.setBounds(230, 405, 665, 234);
		panel.add(textPane_1);
		
		JScrollPane scroll_1=new JScrollPane(textPane_1);
		scroll_1.setBounds(230, 405, 665, 234);
		panel.add(scroll_1);
		
		JLabel lblsolution = new JLabel("4.SOLUTION:");
		lblsolution.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblsolution.setBounds(10, 839, 141, 30);
		panel.add(lblsolution);
		
		labels.add("4.SOLUTION:");
		
		textPane = new JTextPane();
		textPane.setToolTipText("What is motivating us to apply this pattern? ");
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane.setBounds(230, 743, 665, 234);
		panel.add(textPane);
		
		JScrollPane scroll_2=new JScrollPane(textPane);
		scroll_2.setBounds(230, 743, 665, 234);
		panel.add(scroll_2);
		
		JLabel lblbenefits = new JLabel("5.BENEFITS:");
		lblbenefits.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblbenefits.setBounds(10, 1050, 141, 30);
		panel.add(lblbenefits);
		
		labels.add("5.BENEFITS:");
		
		textPane_2 = new JTextPane();
		textPane_2.setToolTipText("What are the potential positive outcomes of applying this pattern?");
		textPane_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_2.setBounds(230, 1000, 665, 234);
		panel.add(textPane_2);
		
		JScrollPane scroll_3=new JScrollPane(textPane_2);
		scroll_3.setBounds(230, 1000, 665, 234);
		panel.add(scroll_3);
		
		JLabel lblconsequences = new JLabel("6.CONSEQUENCES:");
		lblconsequences.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblconsequences.setBounds(10, 1300, 201, 30);
		panel.add(lblconsequences);
		labels.add("6.CONSEQUENCES:");
		
		textPane_3 = new JTextPane();
		textPane_3.setToolTipText("What are potential shortcomings and consequences of applying this pattern?");
		textPane_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_3.setBounds(230, 1250, 665, 234);
		panel.add(textPane_3);
		

		JScrollPane scroll_4=new JScrollPane(textPane_3);
		scroll_4.setBounds(230, 1250, 665, 234);
		panel.add(scroll_4);
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
			textPane_1.setText(fields.get(2));
			textPane.setText(fields.get(3));
			textPane_2.setText(fields.get(4));
			textPane_3.setText(fields.get(5));
		}
		
		public void saveFields() {

			if(fields.size()==0) {
				fields.add(textField.getText());
				fields.add(textField_1.getText());
				fields.add(textPane_1.getText());
				fields.add(textPane.getText());
				fields.add(textPane_2.getText());
				fields.add(textPane_3.getText());
			}else {
				fields.set(0,textField.getText());
				fields.set(1,textField_1.getText());
				fields.set(2,textPane_1.getText());
				fields.set(3,textPane.getText());
				fields.set(4,textPane_2.getText());
				fields.set(5,textPane_3.getText());
		
			}
		}
		
		public ArrayList<String> getFields() {
			return fields;
		}
		
		public ArrayList<String> getLabels(){
			return labels;
		}
	}
