package GuI;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class GangPattern extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private boolean isCreated=false;
	private ArrayList<String> fields=new ArrayList<String>();
	private ArrayList<String> labels=new ArrayList<String>();
	private JTextPane textPane_1;
	private JTextPane textPane_2;
	private JTextPane textPane_3;
	private JTextPane textPane_4;
	private JTextPane textPane_5;
	private JTextPane textPane_6;
	private JTextPane textPane_7;
	private JTextPane textPane_8;
	private JTextPane textPane_9;
	private JTextPane textPane_10;
	private JTextPane textPane_11;
	private JTextPane textPane_12;

	/**
	 * Create the panel.
	 */
	public GangPattern(JPanel panel,boolean isCreated) {
		this.isCreated=isCreated;
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setBounds(0, 22, 1180, 1100);
	
		
		JLabel lblNewLabel = new JLabel("GANG-OF-FOUR PATTERN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(428, 22, 324, 59);
		panel.add(lblNewLabel);
		labels.add("GANG-OF-FOUR PATTERN");
		
		JLabel lblNewLabel_1 = new JLabel("1.NAME:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(10, 113, 300, 30);
		panel.add(lblNewLabel_1);
		labels.add("1.NAME:");
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setToolTipText("What is the pattern called?");
		textField.setBounds(350, 113, 665, 49);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lbltemplate = new JLabel("2.TEMPLATE:");
		lbltemplate.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbltemplate.setBounds(10, 256, 300, 30);
		panel.add(lbltemplate);
		labels.add("2.TEMPLATE:");
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Which template is followed for the pattern specification ? ");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(350, 251, 665, 49);
		panel.add(textField_1);
		
		JLabel lblproblem = new JLabel("3.PATTERN CLASSIFICATION:");
		lblproblem.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblproblem.setBounds(10, 400, 300, 30);
		panel.add(lblproblem);
		labels.add("3.PATTERN CLASSIFICATION:");
		
		textField_2 = new JTextField();
		textField_2.setToolTipText(" Is the pattern creational, structural, or behavioral? ");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(350, 395, 665, 49);
		panel.add(textField_2);
		
		
		JLabel lblintent = new JLabel("4.INTENT:");
		lblintent.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblintent.setBounds(10, 500, 300, 30);
		panel.add(lblintent);
		labels.add("4.INTENT:");
		
		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_1.setToolTipText("  What problem does this pattern solve?");
		textPane_1.setBounds(350, 470, 665, 234);
		panel.add(textPane_1);
		
		JScrollPane scroll_1=new JScrollPane(textPane_1);
		scroll_1.setBounds(350, 470, 665, 234);
		panel.add(scroll_1);
		
		JLabel lblalso = new JLabel("5.ALSO KNOWN AS:");
		lblalso.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblalso.setBounds(10, 750, 300, 30);
		panel.add(lblalso);
		labels.add("5.ALSO KNOWN AS:");
		
		textPane_2 = new JTextPane();
		textPane_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_2.setToolTipText(" What are other names for this pattern? ");
		textPane_2.setBounds(350, 720, 665, 234);
		panel.add(textPane_2);
		
		JScrollPane scroll_2=new JScrollPane(textPane_2);
		scroll_2.setBounds(350, 720, 665, 234);
		panel.add(scroll_2);
		
		JLabel lblmotivation = new JLabel("6.MOTIVATION:");
		lblmotivation.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblmotivation.setBounds(10, 1000, 300, 30);
		panel.add(lblmotivation);
		labels.add("6.MOTIVATION:");
		
		textPane_3 = new JTextPane();
		textPane_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_3.setToolTipText(" What is an example scenario for applying this pattern? ");
		textPane_3.setBounds(350, 970, 665, 234);
		panel.add(textPane_3);
		
		JScrollPane scroll_3=new JScrollPane(textPane_3);
		scroll_3.setBounds(350, 970, 665, 234);
		panel.add(scroll_3);
		
		JLabel lblapplicability = new JLabel("7.APPLICABILITY:");
		lblapplicability.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblapplicability.setBounds(10, 1250, 300, 30);
		panel.add(lblapplicability);
		labels.add("7.APPLICABILITY:");
		
		textPane_4 = new JTextPane();
		textPane_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_4.setToolTipText("  When does this pattern apply?");
		textPane_4.setBounds(350, 1220, 665, 234);
		panel.add(textPane_4);
		
		JScrollPane scroll_4=new JScrollPane(textPane_4);
		scroll_4.setBounds(350, 1220, 665, 234);
		panel.add(scroll_4);
		
		JLabel lblstructure = new JLabel("8.STRUCTURE:");
		lblstructure.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblstructure.setBounds(10, 1500, 300, 30);
		panel.add(lblstructure);
		labels.add("8.STRUCTURE:");
		
		textPane_5 = new JTextPane();
		textPane_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_5.setToolTipText(" Which are the classes of the objects in this pattern? ");
		textPane_5.setBounds(350, 1470, 665, 234);
		panel.add(textPane_5);
		
		JScrollPane scroll_5=new JScrollPane(textPane_5);
		scroll_5.setBounds(350, 1470, 665, 234);
		panel.add(scroll_5);
		
		JLabel lblparticipants = new JLabel("9.PARTICIPANTS:");
		lblparticipants.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblparticipants.setBounds(10, 1750, 300, 30);
		panel.add(lblparticipants);
		labels.add("9.PARTICIPANTS:");
		
		textPane_6 = new JTextPane();
		textPane_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_6.setToolTipText("What are the objects that participate in this pattern? ");
		textPane_6.setBounds(350, 1720, 665, 234);
		panel.add(textPane_6);
		
		JScrollPane scroll_6=new JScrollPane(textPane_6);
		scroll_6.setBounds(350, 1720, 665, 234);
		panel.add(scroll_6);
		
		JLabel lblcollaborations = new JLabel("10.COLLABORATIONS:");
		lblcollaborations.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblcollaborations.setBounds(10, 2000, 300, 30);
		panel.add(lblcollaborations);
		labels.add("10.COLLABORATIONS:");
		
		textPane_7 = new JTextPane();
		textPane_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_7.setToolTipText(" How do these objects interoperate?");
		textPane_7.setBounds(350, 1970, 665, 234);
		panel.add(textPane_7);
		
		JScrollPane scroll_7=new JScrollPane(textPane_7);
		scroll_7.setBounds(350, 1970, 665, 234);
		panel.add(scroll_7);
		
		JLabel lblconsequencess = new JLabel("11.CONSEQUENCES:");
		lblconsequencess.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblconsequencess.setBounds(10, 2250, 300, 30);
		panel.add(lblconsequencess);
		labels.add("11.CONSEQUENCES:");
		
		textPane_8 = new JTextPane();
		textPane_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_8.setToolTipText(" What are the trade-offs of using this pattern");
		textPane_8.setBounds(350, 2220, 665, 234);
		panel.add(textPane_8);
		
		JScrollPane scroll_8=new JScrollPane(textPane_8);
		scroll_8.setBounds(350, 2220, 665, 234);
		panel.add(scroll_8);
		
		JLabel lblimplementations = new JLabel("12.IMPLEMENTATION:");
		lblimplementations.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblimplementations.setBounds(10, 2500, 300, 30);
		panel.add(lblimplementations);
		labels.add("12.IMPLEMENTATION:");
		textPane_9 = new JTextPane();
		textPane_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_9.setToolTipText(" Which techniques or issues arise in applying this pattern? ");
		textPane_9.setBounds(350, 2470, 665, 234);
		panel.add(textPane_9);
		
		JScrollPane scroll_9=new JScrollPane(textPane_9);
		scroll_9.setBounds(350, 2470, 665, 234);
		panel.add(scroll_9);
		
		JLabel lblsample = new JLabel("13.SAMPLE CODE:");
		lblsample.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblsample.setBounds(10, 2750, 300, 30);
		panel.add(lblsample);
		labels.add("13.SAMPLE CODE:");
		textPane_10 = new JTextPane();
		textPane_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_10.setToolTipText(" Which techniques or issues arise in applying this pattern? ");
		textPane_10.setBounds(350, 2720, 665, 234);
		panel.add(textPane_10);
		
		JScrollPane scroll_10=new JScrollPane(textPane_10);
		scroll_10.setBounds(350, 2720, 665, 234);
		panel.add(scroll_10);
		
		JLabel lbluses = new JLabel("14.KNOWN USES:");
		lbluses.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbluses.setBounds(10, 3000, 300, 30);
		panel.add(lbluses);
		labels.add("14.KNOWN USES:");
		
		textPane_11 = new JTextPane();
		textPane_11.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_11.setToolTipText(" What are some examples of real systems using this pattern? ");
		textPane_11.setBounds(350, 2970, 665, 234);
		panel.add(textPane_11);
		
		JScrollPane scroll_11=new JScrollPane(textPane_11);
		scroll_11.setBounds(350, 2970, 665, 234);
		panel.add(scroll_11);
		
		JLabel lblrelated = new JLabel("15.RELATED PATTERNS:");
		lblrelated.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblrelated.setBounds(10, 3250, 300, 30);
		panel.add(lblrelated);
		labels.add("15.RELATED PATTERNS:");
		
		textPane_12 = new JTextPane();
		textPane_12.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane_12.setToolTipText(" What other patterns from this pattern collection are related to this pattern?");
		textPane_12.setBounds(350, 3220, 665, 234);
		panel.add(textPane_12);
		
		JScrollPane scroll_12=new JScrollPane(textPane_12);
		scroll_12.setBounds(350, 3220, 665, 234);
		panel.add(scroll_12);
		
		
	
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
			textPane_1.setText(fields.get(3));
			textPane_2.setText(fields.get(4));
			textPane_3.setText(fields.get(5));
			textPane_4.setText(fields.get(6));
			textPane_5.setText(fields.get(7));
			textPane_6.setText(fields.get(8));
			textPane_7.setText(fields.get(9));
			textPane_8.setText(fields.get(10));
			textPane_9.setText(fields.get(11));
			textPane_10.setText(fields.get(12));
			textPane_11.setText(fields.get(13));
			textPane_12.setText(fields.get(14));
		
		}
		
		public void saveFields() {
		

			if(fields.size()==0) {
				fields.add(textField.getText());
				fields.add(textField_1.getText());
				fields.add(textField_2.getText());
				fields.add(textPane_1.getText());
				fields.add(textPane_2.getText());
				fields.add(textPane_3.getText());
				fields.add(textPane_4.getText());
				fields.add(textPane_5.getText());
				fields.add(textPane_6.getText());
				fields.add(textPane_7.getText());
				fields.add(textPane_8.getText());
				fields.add(textPane_9.getText());
				fields.add(textPane_10.getText());
				fields.add(textPane_11.getText());
				fields.add(textPane_12.getText());
			}else {
				fields.set(0,textField.getText());
				fields.set(1,textField_1.getText());
				fields.set(2,textField_2.getText());
				fields.set(3,textPane_1.getText());
				fields.set(4,textPane_2.getText());
				fields.set(5,textPane_3.getText());
				fields.set(6,textPane_4.getText());
				fields.set(7,textPane_5.getText());
				fields.set(8,textPane_6.getText());
				fields.set(9,textPane_7.getText());
				fields.set(10,textPane_8.getText());
				fields.set(11,textPane_9.getText());
				fields.set(12,textPane_10.getText());
				fields.set(13,textPane_11.getText());
				fields.set(14,textPane_12.getText());
			}
		}
		
		public ArrayList<String> getFields() {
			return fields;
		}
		
		public ArrayList<String> getLabels(){
			return labels;
		}
}

