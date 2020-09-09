package GuI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import Functions.CollectFields;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainFrame {

	private JFrame frmPatternEditor;
	private JTextField textField;
	private JPanel micro_pattern_panel;
	private MicroPattern micro;
	private JPanel inductive_panel;
	private JScrollPane inductive_scroll;
	private InductiveMini inductive_mini;
	private JPanel deductive_panel;
	private JScrollPane deductive_scroll;
	private DeductiveMini deductive_mini;
	private JPanel gang_panel;
	private JScrollPane gang_scroll;
	private GangPattern gang;
	private JPanel system_panel;
	private JScrollPane system_scroll;
	private SystemTemplate system;
	private CollectFields collect=new CollectFields();
	private String pattern_language_name;
	private File file;
	private JFileChooser choose_file=new JFileChooser();
	private ArrayList<MicroPattern> micro_array=new ArrayList<MicroPattern>();
	private ArrayList<InductiveMini> inductive_mini_array=new ArrayList<InductiveMini>();
	private ArrayList<DeductiveMini> deductive_mini_array=new ArrayList<DeductiveMini>();
	private ArrayList<GangPattern> gang_array=new ArrayList<GangPattern>();
	private ArrayList<SystemTemplate> system_array=new ArrayList<SystemTemplate>();
	private ArrayList<JPanel> micro_array_of_panels=new ArrayList<JPanel>();
	private ArrayList<JScrollPane> inductive_mini_array_of_panels=new ArrayList<JScrollPane>();
	private ArrayList<JScrollPane> deductive_mini_array_of_panels=new ArrayList<JScrollPane>();
	private ArrayList<JScrollPane> gang_array_of_panels=new ArrayList<JScrollPane>();
	private ArrayList<JScrollPane> system_array_of_panels=new ArrayList<JScrollPane>();
	private ArrayList<ArrayList<String>> micro_array_of_fields=new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> inductive_mini_array_of_fields=new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> deductive_mini_array_of_fields=new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> gang_array_of_fields=new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> system_array_of_fields=new ArrayList<ArrayList<String>>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmPatternEditor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPatternEditor = new JFrame();
		frmPatternEditor.setTitle("Pattern Editor");
		frmPatternEditor.setResizable(false);
		frmPatternEditor.setBounds(100, 100, 1200, 900);
		frmPatternEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPatternEditor.getContentPane().setLayout(null);
		
		micro_pattern_panel=new JPanel();
		micro=new MicroPattern(micro_pattern_panel,false);
		
		
		inductive_panel = new JPanel();
		inductive_panel.setPreferredSize(new Dimension(1100,1400));
		inductive_scroll=new JScrollPane(inductive_panel);
		inductive_scroll.setPreferredSize(new Dimension(1100,1400));
		inductive_scroll.setBounds(0, 22, 1200, 1000);
		inductive_mini=new InductiveMini(inductive_panel,false);
		
		
		deductive_panel = new JPanel();
		deductive_panel.setPreferredSize(new Dimension(1100,1700));
		deductive_scroll=new JScrollPane(deductive_panel);
		deductive_scroll.setPreferredSize(new Dimension(1100,1700));
		deductive_scroll.setBounds(0, 22, 1200, 1000);
		deductive_mini=new DeductiveMini(deductive_panel,false);
		
		gang_panel = new JPanel();
		gang_panel.setPreferredSize(new Dimension(1100,3600));
		gang_scroll=new JScrollPane(gang_panel);
		gang_scroll.setPreferredSize(new Dimension(1100,3600));
		gang_scroll.setBounds(0, 22, 1200, 1000);
		gang=new GangPattern(gang_panel,false);
		
		system_panel = new JPanel();
		system_panel.setPreferredSize(new Dimension(1100,3400));
		system_scroll=new JScrollPane(system_panel);
		system_scroll.setPreferredSize(new Dimension(1100,3400));
		system_scroll.setBounds(0, 22, 1200, 1000);
		system=new SystemTemplate(system_panel,false);


		JPanel transitional=new JPanel();
		transitional.setBounds(0, 22, 1200, 1000);
		transitional.setLayout(null);
		
		JLabel lblChoose = new JLabel("CHOOSE A TEMPLATE TO EDIT FROM TOOLBAR!");
		lblChoose.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblChoose.setBounds(300, 180, 700, 700);
		transitional.add(lblChoose);
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.LIGHT_GRAY);
		toolBar.setBounds(0, 0, 150, 22);
		frmPatternEditor.getContentPane().add(toolBar);
		
		String[] combox={"Templates"};
		JComboBox comboBox = new JComboBox(combox);
		comboBox.setName("Templates");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {}));
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setToolTipText("Choose available templates.");
		toolBar.add(comboBox);
		
		JToolBar toolBar1 = new JToolBar();
		toolBar1.setBackground(Color.LIGHT_GRAY);
		toolBar1.setBounds(150, 0, 230, 22);
		frmPatternEditor.getContentPane().add(toolBar1);
		
		String[] combox1={"Add"};
		JComboBox comboBox1 = new JComboBox(combox1);
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"Add Micro-Pattern","Add Inductive-Mini", "Add Deductive-Mini", "Add Gang-of-Four", "Add System of Patterns"}));
		comboBox1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox1.setToolTipText("Add available templates to pattern language.");
		toolBar1.add(comboBox1);
		
		
		JToolBar toolBar2 = new JToolBar();
		toolBar2.setBackground(Color.LIGHT_GRAY);
		toolBar2.setBounds(380, 0, 230, 22);
		frmPatternEditor.getContentPane().add(toolBar2);
		
		String[] combox2={"Remove"};
		JComboBox comboBox2 = new JComboBox(combox2);
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {"Remove Micro-Pattern","Remove Inductive-Mini", "Remove Deductive-Mini", "Remove Gang-of-Four", "Remove System of Patterns"}));
		comboBox2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox2.setToolTipText("Remove available templates to pattern language.");
		toolBar2.add(comboBox2);
		

		JToolBar toolBar3 = new JToolBar();
		toolBar3.setBackground(Color.LIGHT_GRAY);
		toolBar3.setBounds(610, 0, 230, 22);
		frmPatternEditor.getContentPane().add(toolBar3);
		
		String[] combox3={"Save"};
		JComboBox comboBox3 = new JComboBox(combox3);
		comboBox3.setModel(new DefaultComboBoxModel(new String[] {"Save as Txt","Save as Latex"}));
		comboBox3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox3.setToolTipText("Save Contents");
		toolBar3.add(comboBox3);
		
		JToolBar toolBar4 = new JToolBar();
		toolBar4.setBackground(Color.LIGHT_GRAY);
		toolBar4.setBounds(840, 0, 230, 22);
		frmPatternEditor.getContentPane().add(toolBar4);
		
		String[] combox4={"Read"};
		JComboBox comboBox4 = new JComboBox(combox4);
		comboBox4.setModel(new DefaultComboBoxModel(new String[] {"Read from Txt","Read from Latex"}));
		comboBox4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox4.setToolTipText("Save Contents");
		toolBar4.add(comboBox4);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 1190, 850);
		frmPatternEditor.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNameYourPattern = new JLabel("NAME YOUR PATTERN LANGUAGE");
		lblNameYourPattern.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNameYourPattern.setBounds(34, 159, 376, 92);
		panel.add(lblNameYourPattern);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField.setBounds(34, 291, 376, 73);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblChoosePatternTemplates = new JLabel("CHOOSE PATTERN TEMPLATES TO INCLUDE IN PATTERN LANGUAGE");
		lblChoosePatternTemplates.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblChoosePatternTemplates.setBounds(570, 11, 610, 92);
		panel.add(lblChoosePatternTemplates);
		
		toolBar.setVisible(false);toolBar1.setVisible(false);toolBar2.setVisible(false);toolBar3.setVisible(false);toolBar4.setVisible(false);
		
		JButton btnAddMicropatternTemplate = new JButton("ADD MICRO-PATTERN TEMPLATE");
		btnAddMicropatternTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(micro_array.size()==0) {
					micro_array.add(micro);
					micro_array_of_panels.add(micro_pattern_panel);
				}
				if(!micro.getCreation()) {
					micro.setCreation(true);
				}
			
			}
		});
		btnAddMicropatternTemplate.setBounds(742, 87, 300, 60);
		panel.add(btnAddMicropatternTemplate);
		
		JButton btnAddInductivepatternTemplate = new JButton("ADD INDUCTIVE-PATTERN TEMPLATE");
		btnAddInductivepatternTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(inductive_mini_array.size()==0) {
					inductive_mini_array.add(inductive_mini);
					inductive_mini_array_of_panels.add(inductive_scroll);
				}
				if(!inductive_mini.getCreation()) {
					inductive_mini.setCreation(true);
				}
			}
		});
		btnAddInductivepatternTemplate.setBounds(742, 168, 300, 66);
		panel.add(btnAddInductivepatternTemplate);
		
		JButton btnAddDeductivePatter = new JButton("ADD DEDUCTIVE-PATTERN TEMPLATE");
		btnAddDeductivePatter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(deductive_mini_array.size()==0) {
					deductive_mini_array.add(deductive_mini);
					deductive_mini_array_of_panels.add(deductive_scroll);
				}

				if(!deductive_mini.getCreation()) {
					deductive_mini.setCreation(true);
				}
			}
		});
		btnAddDeductivePatter.setBounds(742, 263, 300, 61);
		panel.add(btnAddDeductivePatter);
		
		JButton btnAddGangOf = new JButton("ADD GANG OF FOUR PATTERN");
		btnAddGangOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gang_array.size()==0) {
					gang_array.add(gang);
					gang_array_of_panels.add(gang_scroll);
				}
				if(!gang.getCreation()) {
					gang.setCreation(true);
				}
				
			}
		});
		btnAddGangOf.setBounds(742, 370, 300, 61);
		panel.add(btnAddGangOf);
		
		JButton btnAddSystemOf = new JButton("ADD SYSTEM OF PATTERNS TEMPLATE");
		btnAddSystemOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(system_array.size()==0) {
					system_array.add(system);
					system_array_of_panels.add(system_scroll);
				}
				if(!system.getCreation()) {
					system.setCreation(true);
				}
			}
		});
		btnAddSystemOf.setBounds(742, 477, 300, 61);
		panel.add(btnAddSystemOf);
		
		JButton btnNewButton = new JButton("CREATE YOUR PATTERN LANGUAGE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("")) {
					JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"NO NAME SELECTED.TEMPLATE LANGUAGE NAMED 'GEOFF'!");
					frmPatternEditor.setTitle("GEOFF");
					pattern_language_name="GEOFF";				
				}else {
					frmPatternEditor.setTitle(textField.getText());
					pattern_language_name=textField.getText();
				}
				
				if(micro.getCreation()) {
					frmPatternEditor.getContentPane().add(micro_pattern_panel);
					comboBox.addItem("Micro-Pattern:"+micro_array.size());
				}
				
				if(inductive_mini.getCreation()) {
					frmPatternEditor.getContentPane().add(inductive_scroll);
					comboBox.addItem("Inductive-Mini:"+inductive_mini_array.size());
				}
				if(deductive_mini.getCreation()) {
					frmPatternEditor.getContentPane().add(deductive_scroll);
					comboBox.addItem("Deductive-Mini:"+deductive_mini_array.size());
				}
				if(gang.getCreation()) {
					frmPatternEditor.getContentPane().add(gang_scroll);
					comboBox.addItem("Gang-of-Four:"+gang_array.size());
				}
				if(system.getCreation()) {
					frmPatternEditor.getContentPane().add(system_scroll);
					comboBox.addItem("System of Patterns:"+system_array.size());
				}
				
				
				
				
				if(!micro.getCreation()&&!inductive_mini.getCreation()&&!deductive_mini.getCreation()&&!gang.getCreation()&&!system.getCreation()) {
					
					JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"NO TEMPLATE SELECTED.MICRO PATTERN ADDED!!!");
					frmPatternEditor.getContentPane().add(micro_pattern_panel);
					micro.setCreation(true);
					micro_array.add(micro);
					micro_array_of_panels.add(micro_pattern_panel);
					comboBox.addItem("Micro-Pattern:"+micro_array.size());
				}
				frmPatternEditor.getContentPane().add(transitional);
				transitional.setVisible(true);
				micro_pattern_panel.setVisible(false);
				panel.setVisible(false);
				inductive_scroll.setVisible(false);
				deductive_scroll.setVisible(false);
				gang_scroll.setVisible(false);
				system_scroll.setVisible(false);
				toolBar.setVisible(true);toolBar1.setVisible(true);toolBar2.setVisible(true);toolBar3.setVisible(true);toolBar4.setVisible(true);
			
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton.setBounds(315, 646, 418, 98);
		panel.add(btnNewButton);
		
		
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transitional.setVisible(false);
				
				if(comboBox.getSelectedItem()!=null) {
					String[] split=comboBox.getSelectedItem().toString().split(":");
					for(int i=0;i<micro_array_of_panels.size();i++) {
						if(split[1].equals(Integer.toString(i+1))&&split[0].equals("Micro-Pattern")) {
							micro_array_of_panels.get(i).setVisible(true);
						}else {
							micro_array_of_panels.get(i).setVisible(false);
						}
					}
					
					for(int i=0;i<inductive_mini_array_of_panels.size();i++) {
						if(split[1].equals(Integer.toString(i+1))&&split[0].equals("Inductive-Mini")) {
							inductive_mini_array_of_panels.get(i).setVisible(true);
						}else {
							inductive_mini_array_of_panels.get(i).setVisible(false);
						}
					}
					
					for(int i=0;i<deductive_mini_array_of_panels.size();i++) {
						if(split[1].equals(Integer.toString(i+1))&&split[0].equals("Deductive-Mini")) {
							deductive_mini_array_of_panels.get(i).setVisible(true);
						}else {
							deductive_mini_array_of_panels.get(i).setVisible(false);
						}
					}
					
					for(int i=0;i<gang_array_of_panels.size();i++) {
						if(split[1].equals(Integer.toString(i+1))&&split[0].equals("Gang-of-Four")) {
							gang_array_of_panels.get(i).setVisible(true);
						}else {
							gang_array_of_panels.get(i).setVisible(false);
						}
					}
					
					for(int i=0;i<system_array_of_panels.size();i++) {
						if(split[1].equals(Integer.toString(i+1))&&split[0].equals("System of Patterns")) {
							system_array_of_panels.get(i).setVisible(true);
						}else {
							system_array_of_panels.get(i).setVisible(false);
						}
					}
				}
			
			}
		});
		
		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox1.getSelectedItem().equals("Add Micro-Pattern")) {
					//if(!micro.getCreation()) {
					
						micro_pattern_panel=new JPanel();
						micro=new MicroPattern(micro_pattern_panel,false);
						micro_array.add(micro);
						micro_array_of_panels.add(micro_pattern_panel);
						frmPatternEditor.getContentPane().add(micro_pattern_panel);
						micro.setCreation(true);
						comboBox.addItem("Micro-Pattern:"+micro_array.size());
						
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"MICRO PATTERN TEMPLATE ADDED IN PATTERN LANGUAGE!!!");
					
				}
				if(comboBox1.getSelectedItem().equals("Add Inductive-Mini")) {
					//if(!inductive_mini.getCreation()) {
					inductive_panel = new JPanel();
					inductive_panel.setPreferredSize(new Dimension(1100,1400));
					inductive_scroll=new JScrollPane(inductive_panel);
					inductive_scroll.setPreferredSize(new Dimension(1100,1400));
					inductive_scroll.setBounds(0, 22, 1200, 1000);
					inductive_mini=new InductiveMini(inductive_panel,false);
					inductive_mini_array.add(inductive_mini);
					inductive_mini_array_of_panels.add(inductive_scroll);
					frmPatternEditor.getContentPane().add(inductive_scroll);
					inductive_mini.setCreation(true);
					inductive_scroll.setVisible(false);
					comboBox.addItem("Inductive-Mini:"+inductive_mini_array.size());
					JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"INDUCTIVE-MINI TEMPLATE ADDED IN PATTERN LANGUAGE!!!");
					
				}
				
				if(comboBox1.getSelectedItem().equals("Add Deductive-Mini")) {
					
						deductive_panel = new JPanel();
						deductive_panel.setPreferredSize(new Dimension(1100,1700));
						deductive_scroll=new JScrollPane(deductive_panel);
						deductive_scroll.setPreferredSize(new Dimension(1100,1700));
						deductive_scroll.setBounds(0, 22, 1200, 1000);
						deductive_mini=new DeductiveMini(deductive_panel,false);
						deductive_mini_array.add(deductive_mini);
						deductive_mini_array_of_panels.add(deductive_scroll);
						frmPatternEditor.getContentPane().add(deductive_scroll);
						deductive_mini.setCreation(true);
						deductive_scroll.setVisible(false);
						comboBox.addItem("Deductive-Mini:"+deductive_mini_array.size());
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"DEDUCTIVE-MINI TEMPLATE ADDED IN PATTERN LANGUAGE!!!");
					
				}
				
				if(comboBox1.getSelectedItem().equals("Add Gang-of-Four")) {
					
					gang_panel = new JPanel();
					gang_panel.setPreferredSize(new Dimension(1100,3600));
					gang_scroll=new JScrollPane(gang_panel);
					gang_scroll.setPreferredSize(new Dimension(1100,3600));
					gang_scroll.setBounds(0, 22, 1200, 1000);
					gang=new GangPattern(gang_panel,false);
					gang_array.add(gang);
					gang_array_of_panels.add(gang_scroll);
					frmPatternEditor.getContentPane().add(gang_scroll);
					gang.setCreation(true);
					gang_scroll.setVisible(false);
					comboBox.addItem("Gang-of-Four:"+gang_array.size());
					JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"GANG-OF-FOUR ADDED IN PATTERN LANGUAGE!!!");
					
				}
				
				if(comboBox1.getSelectedItem().equals("Add System of Patterns")) {
					
					system_panel = new JPanel();
					system_panel.setPreferredSize(new Dimension(1100,3400));
					system_scroll=new JScrollPane(system_panel);
					system_scroll.setPreferredSize(new Dimension(1100,3400));
					system_scroll.setBounds(0, 22, 1200, 1000);
					system=new SystemTemplate(system_panel,false);
					system_array.add(system);
					system_array_of_panels.add(system_scroll);
					frmPatternEditor.getContentPane().add(system_scroll);
					system.setCreation(true);
					system_scroll.setVisible(false);
					comboBox.addItem("System of Patterns:"+system_array.size());
					JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"SYSTEM OF PATTERNS ADDED IN PATTERN LANGUAGE!!!");
				
				}
			}
		});
		
		
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox2.getSelectedItem().equals("Remove Micro-Pattern")) {
					if(micro_array.size()>0) {
						comboBox.removeItem("Micro-Pattern:"+micro_array.size());
						micro_array_of_panels.get(micro_array_of_panels.size()-1).setVisible(false);
						frmPatternEditor.getContentPane().remove(micro_array_of_panels.get(micro_array_of_panels.size()-1));
						micro_array.remove(micro_array.size()-1);
						micro_array_of_panels.remove(micro_array_of_panels.size()-1);
						
						micro.setCreation(false);
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"MICRO PATTERN TEMPLATE REMOVED FROM PATTERN LANGUAGE!!!");
					}else {
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"MICRO PATTERN TEMPLATE NOT IN PATTERN LANGUAGE.NOTHING TO REMOVE!!!");
					}
				}
				if(comboBox2.getSelectedItem().equals("Remove Inductive-Mini")) {
					if(inductive_mini_array.size()>0) {
						comboBox.removeItem("Inductive-Mini:"+inductive_mini_array.size());
						inductive_mini_array_of_panels.get(inductive_mini_array_of_panels.size()-1).setVisible(false);
						frmPatternEditor.getContentPane().remove(inductive_scroll);
						inductive_mini_array.remove(inductive_mini_array.size()-1);
						inductive_mini_array_of_panels.remove(inductive_mini_array_of_panels.size()-1);
						
						inductive_mini.setCreation(false);
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"INDUCTIVE-MINI TEMPLATE REMOVED FROM PATTERN LANGUAGE!!!");
					}else {
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"INDUCTIVE-MINI TEMPLATE NOT IN PATTERN LANGUAGE.NOTHING TO REMOVE!!!");
					}
				}
				
				if(comboBox2.getSelectedItem().equals("Remove Deductive-Mini")) {
					if(deductive_mini_array.size()>0) {
						comboBox.removeItem("Deductive-Mini:"+deductive_mini_array.size());
						deductive_mini_array_of_panels.get(deductive_mini_array_of_panels.size()-1).setVisible(false);
						frmPatternEditor.getContentPane().remove(deductive_scroll);
						deductive_mini_array.remove(deductive_mini_array.size()-1);
						deductive_mini_array_of_panels.remove(deductive_mini_array_of_panels.size()-1);
						
						deductive_mini.setCreation(false);
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"DEDUCTIVE-MINI TEMPLATE REMOVE FROM PATTERN LANGUAGE!!!");
					}else {
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"DEDUCTIVE-MINI TEMPLATE NOT IN PATTERN LANGUAGE.NOTHING TO REMOVE!!!");
					}
				}
				
				if(comboBox2.getSelectedItem().equals("Remove Gang-of-Four")) {
					if(gang_array.size()>0) {
						comboBox.removeItem("Gang-of-Four:"+gang_array.size());
						gang_array_of_panels.get(gang_array_of_panels.size()-1).setVisible(false);
						frmPatternEditor.getContentPane().remove(gang_scroll);
						gang_array.remove(gang_array.size()-1);
						gang_array_of_panels.remove(gang_array_of_panels.size()-1);
						
						gang.setCreation(false);
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"GANG-OF-FOUR REMOVE FROM PATTERN LANGUAGE!!!");
					}else {
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"GANG-OF-FOUR TEMPLATE NOT IN PATTERN LANGUAGE.NOTHING TO REMOVE!!!");
					}
				}
				
				if(comboBox2.getSelectedItem().equals("Remove System of Patterns")) {
					if(system_array.size()>0) {
						comboBox.removeItem("System of Patterns:"+system_array.size());
						system_array_of_panels.get(system_array_of_panels.size()-1).setVisible(false);
						frmPatternEditor.getContentPane().remove(system_scroll);
						system_array.remove(system_array.size()-1);
						system_array_of_panels.remove(system_array_of_panels.size()-1);
						
						system.setCreation(false);
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"SYSTEM OF PATTERNS REMOVED FROM PATTERN LANGUAGE!!!");
					}else {
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"SYSTEM OF PATTERNS NOT IN PATTERN LANGUAGE.NOTHING TO REMOVE!!!");
					}
				}
			}
		});
		
		comboBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox3.getSelectedItem().equals("Save as Txt")) {
					
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Text", "txt", "text");
					choose_file.setFileFilter(filter);
					choose_file.setAcceptAllFileFilterUsed(false);
					int result = choose_file.showDialog(new JFrame(""), null);
					if (result == JFileChooser.APPROVE_OPTION) {
						file = choose_file.getSelectedFile();

						if(micro_array.size()>0) {
							for(int i=0;i<micro_array.size();i++) {
								micro_array.get(i).saveFields();
								collect.setExistenceOfMicro(true);
								micro_array_of_fields.add(micro_array.get(i).getFields());
							}
							collect.setMicroPatternFields(micro_array_of_fields);
							collect.setMicroPatternLabels(micro_array.get(0).getLabels());
						}
						if(inductive_mini_array.size()>0) {
							for(int i=0;i<inductive_mini_array.size();i++) {
								inductive_mini_array.get(i).saveFields();
								collect.setExistenceOfInductive(true);
								inductive_mini_array_of_fields.add(inductive_mini_array.get(i).getFields());
							}
							collect.setInductivePatternFields(inductive_mini_array_of_fields);
							collect.setInductivePatternLabels(inductive_mini_array.get(0).getLabels());
						}
						if(deductive_mini_array.size()>0) {
							for(int i=0;i<deductive_mini_array.size();i++) {
								deductive_mini_array.get(i).saveFields();
								collect.setExistenceOfDeductive(true);
								deductive_mini_array_of_fields.add(deductive_mini_array.get(i).getFields());
							}
							collect.setDeductivePatternFields(deductive_mini_array_of_fields);
							collect.setDeductivePatternLabels(deductive_mini_array.get(0).getLabels());
						}
						if(gang_array.size()>0) {
							for(int i=0;i<gang_array.size();i++) {
								gang_array.get(i).saveFields();
								collect.setExistenceOfGang(true);
								gang_array_of_fields.add(gang_array.get(i).getFields());
							}
							collect.setGangFourPatternFields(gang_array_of_fields);
							collect.setGangFourPatternLabels(gang_array.get(0).getLabels());
						}
						if(system_array.size()>0) {
							for(int i=0;i<system_array.size();i++) {
								system_array.get(i).saveFields();
								collect.setExistenceOfSystem(true);
								system_array_of_fields.add(system_array.get(i).getFields());
							}
							collect.setSystemofPatternsFields(system_array_of_fields);
							collect.setSystemofPatternsLabels(system_array.get(0).getLabels());
						}
						collect.setPatternName(pattern_language_name);
						collect.saveToTxt(file);
					}else {
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"PLEASE SELECT FILE TO WRITE TO!");
					}
					
					
				}
				
				if(comboBox3.getSelectedItem().equals("Save as Latex")) {
					
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Latex", "tex", "text");
					choose_file.setFileFilter(filter);
					choose_file.setAcceptAllFileFilterUsed(false);
					int result = choose_file.showDialog(new JFrame(""), null);
					if (result == JFileChooser.APPROVE_OPTION) {
						file = choose_file.getSelectedFile();
						if(micro_array.size()>0) {
							for(int i=0;i<micro_array.size();i++) {
								micro_array.get(i).saveFields();
								collect.setExistenceOfMicro(true);
								micro_array_of_fields.add(micro_array.get(i).getFields());
							}
							collect.setMicroPatternFields(micro_array_of_fields);
							collect.setMicroPatternLabels(micro.getLabels());
						}
						if(inductive_mini_array.size()>0) {
							for(int i=0;i<inductive_mini_array.size();i++) {
								inductive_mini_array.get(i).saveFields();
								collect.setExistenceOfInductive(true);
								inductive_mini_array_of_fields.add(inductive_mini_array.get(i).getFields());
							}
							collect.setInductivePatternFields(inductive_mini_array_of_fields);
							collect.setInductivePatternLabels(inductive_mini_array.get(0).getLabels());
						}
						if(deductive_mini_array.size()>0) {
							for(int i=0;i<deductive_mini_array.size();i++) {
								deductive_mini_array.get(i).saveFields();
								collect.setExistenceOfDeductive(true);
								deductive_mini_array_of_fields.add(deductive_mini_array.get(i).getFields());
							}
							collect.setDeductivePatternFields(deductive_mini_array_of_fields);
							collect.setDeductivePatternLabels(deductive_mini_array.get(0).getLabels());
						}
						if(gang_array.size()>0) {
							for(int i=0;i<gang_array.size();i++) {
								gang_array.get(i).saveFields();
								collect.setExistenceOfGang(true);
								gang_array_of_fields.add(gang_array.get(i).getFields());
							}
							collect.setGangFourPatternFields(gang_array_of_fields);
							collect.setGangFourPatternLabels(gang_array.get(0).getLabels());
						}
						if(system_array.size()>0) {
							for(int i=0;i<system_array.size();i++) {
								system_array.get(i).saveFields();
								collect.setExistenceOfSystem(true);
								system_array_of_fields.add(system_array.get(i).getFields());
							}
							collect.setSystemofPatternsFields(system_array_of_fields);
							collect.setSystemofPatternsLabels(system_array.get(0).getLabels());
						}
						collect.setPatternName(pattern_language_name);
						collect.saveToLatex(file);
					}else {
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"PLEASE SELECT FILE TO WRITE TO!");
					}
					
					
					
				}
			}
		});
		
		comboBox4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox4.getSelectedItem().equals("Read from Txt")) {
					

					FileNameExtensionFilter filter = new FileNameExtensionFilter("Text", "txt", "text");
					choose_file.setFileFilter(filter);
					choose_file.setAcceptAllFileFilterUsed(false);
					int result = choose_file.showDialog(new JFrame(""), null);
					if (result == JFileChooser.APPROVE_OPTION) {
						file = choose_file.getSelectedFile();
						if(!micro.getCreation()&&!inductive_mini.getCreation()&&!deductive_mini.getCreation()&&!gang.getCreation()&&!system.getCreation()) {
							JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"CANT READ BEFORE CREATING PATTERN LANGUAGE!!!");
						}else {
							collect.setMicroPatternLabels(micro.getLabels());
							collect.setInductivePatternLabels(inductive_mini.getLabels());
							collect.setDeductivePatternLabels(deductive_mini.getLabels());
							collect.setGangFourPatternLabels(gang.getLabels());
							collect.setSystemofPatternsLabels(system.getLabels());
							collect.readFromTxt(file);
							if(collect.getExistenceOfMicro()) {
								
								for(int i=0;i<micro_array.size();i++) {
									comboBox.removeItem("Micro-Pattern:"+(i+1));
									micro_array_of_panels.get(i).setVisible(false);
									frmPatternEditor.getContentPane().remove(micro_array_of_panels.get(i));
									micro_array.clear();
									micro_array_of_panels.clear();
								}
								
								for(int i=0;i<collect.getMicroFields().size();i++) {
									micro_pattern_panel=new JPanel();
									micro=new MicroPattern(micro_pattern_panel,true);
									micro.setFields(collect.getMicroFields().get(i));
									micro_array.add(micro);
									micro_array_of_panels.add(micro_pattern_panel);
									frmPatternEditor.getContentPane().add(micro_pattern_panel);
									comboBox.addItem("Micro-Pattern:"+micro_array.size());
									micro_pattern_panel.setVisible(false);
									
								}
							}
							if(collect.getExistenceOfInductive()) {
								
								for(int i=0;i<inductive_mini_array.size();i++) {
									comboBox.removeItem("Inductive-Mini:"+(i+1));
									inductive_mini_array_of_panels.get(i).setVisible(false);
									frmPatternEditor.getContentPane().remove(inductive_mini_array_of_panels.get(i));
									inductive_mini_array.clear();
									inductive_mini_array_of_panels.clear();
								}
								
								for(int i=0;i<collect.getInductiveFields().size();i++) {
									inductive_panel = new JPanel();
									inductive_panel.setPreferredSize(new Dimension(1100,1400));
									inductive_scroll=new JScrollPane(inductive_panel);
									inductive_scroll.setPreferredSize(new Dimension(1100,1400));
									inductive_scroll.setBounds(0, 22, 1200, 1000);
									inductive_mini=new InductiveMini(inductive_panel,true);
									inductive_mini.setFields(collect.getInductiveFields().get(i));
									inductive_mini_array.add(inductive_mini);
									inductive_mini_array_of_panels.add(inductive_scroll);
									frmPatternEditor.getContentPane().add(inductive_scroll);
									inductive_scroll.setVisible(false);
									comboBox.addItem("Inductive-Mini:"+inductive_mini_array.size());
									
								}

							}
							if(collect.getExistenceOfDeductive()) {
								
								for(int i=0;i<deductive_mini_array.size();i++) {
									comboBox.removeItem("Deductive-Mini:"+(i+1));
									deductive_mini_array_of_panels.get(i).setVisible(false);
									frmPatternEditor.getContentPane().remove(deductive_mini_array_of_panels.get(i));
									deductive_mini_array.clear();
									deductive_mini_array_of_panels.clear();
								}
								
								for(int i=0;i<collect.getDeductiveFields().size();i++) {
									deductive_panel = new JPanel();
									deductive_panel.setPreferredSize(new Dimension(1100,1400));
									deductive_scroll=new JScrollPane(deductive_panel);
									deductive_scroll.setPreferredSize(new Dimension(1100,1400));
									deductive_scroll.setBounds(0, 22, 1200, 1000);
									deductive_mini=new DeductiveMini(deductive_panel,true);
									deductive_mini.setFields(collect.getDeductiveFields().get(i));
									
									deductive_mini_array.add(deductive_mini);
									deductive_mini_array_of_panels.add(deductive_scroll);
									frmPatternEditor.getContentPane().add(deductive_scroll);
									deductive_scroll.setVisible(false);
									comboBox.addItem("Deductive-Mini:"+deductive_mini_array.size());
									
								}
								
							}
							if(collect.getExistenceOfGang()) {
								for(int i=0;i<gang_array.size();i++) {
									comboBox.removeItem("Gang-of-Four:"+(i+1));
									gang_array_of_panels.get(i).setVisible(false);
									frmPatternEditor.getContentPane().remove(gang_array_of_panels.get(i));
									gang_array.clear();
									gang_array_of_panels.clear();
								}
								
								for(int i=0;i<collect.getGangFields().size();i++) {
									gang_panel = new JPanel();
									gang_panel.setPreferredSize(new Dimension(1100,3600));
									gang_scroll=new JScrollPane(gang_panel);
									gang_scroll.setPreferredSize(new Dimension(1100,3600));
									gang_scroll.setBounds(0, 22, 1200, 1000);
									gang=new GangPattern(gang_panel,false);
									gang.setFields(collect.getGangFields().get(i));
									gang_array.add(gang);
									gang_array_of_panels.add(gang_scroll);
									frmPatternEditor.getContentPane().add(gang_scroll);
									gang_scroll.setVisible(false);
									comboBox.addItem("Gang-of-Four:"+gang_array.size());
									
								}
							}
							if(collect.getExistenceOfSystem()) {
								for(int i=0;i<system_array.size();i++) {
									comboBox.removeItem("System of Patterns:"+(i+1));
									system_array_of_panels.get(i).setVisible(false);
									frmPatternEditor.getContentPane().remove(system_array_of_panels.get(i));
									system_array.clear();
									system_array_of_panels.clear();
								}
								for(int i=0;i<collect.getSystemFields().size();i++) {
									system_panel = new JPanel();
									system_panel.setPreferredSize(new Dimension(1100,3400));
									system_scroll=new JScrollPane(system_panel);
									system_scroll.setPreferredSize(new Dimension(1100,3400));
									system_scroll.setBounds(0, 22, 1200, 1000);
									system=new SystemTemplate(system_panel,true);
									system.setFields(collect.getSystemFields().get(i));
									system_array.add(system);
									system_array_of_panels.add(system_scroll);
									frmPatternEditor.getContentPane().add(system_scroll);
									system_scroll.setVisible(false);
									comboBox.addItem("System of Patterns:"+system_array.size());
									
								}
							}
							JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"READ SUCCESSFULL!!!");
						}
					}else {
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"PLEASE SELECT FILE TO READ FROM!");
					}
					
					
					
				}
				if(comboBox4.getSelectedItem().equals("Read from Latex")) {
					
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Latex", "tex", "text");
					choose_file.setFileFilter(filter);
					choose_file.setAcceptAllFileFilterUsed(false);
					int result = choose_file.showDialog(new JFrame(""), null);
					if (result == JFileChooser.APPROVE_OPTION) {
						file = choose_file.getSelectedFile();
						if(!micro.getCreation()&&!inductive_mini.getCreation()&&!deductive_mini.getCreation()&&!gang.getCreation()&&!system.getCreation()) {
							JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"CANT READ BEFORE CREATING PATTERN LANGUAGE!!!");
						}else {
							collect.setMicroPatternLabels(micro.getLabels());
							collect.setInductivePatternLabels(inductive_mini.getLabels());
							collect.setDeductivePatternLabels(deductive_mini.getLabels());
							collect.setGangFourPatternLabels(gang.getLabels());
							collect.setSystemofPatternsLabels(system.getLabels());
							collect.readFromLatex(file);
							if(collect.getExistenceOfMicro()) {
							
								for(int i=0;i<micro_array.size();i++) {
									comboBox.removeItem("Micro-Pattern:"+(i+1));
									micro_array_of_panels.get(i).setVisible(false);
									frmPatternEditor.getContentPane().remove(micro_array_of_panels.get(i));
									micro_array.clear();
									micro_array_of_panels.clear();
								}
								
								for(int i=0;i<collect.getMicroFields().size();i++) {
									micro_pattern_panel=new JPanel();
									micro=new MicroPattern(micro_pattern_panel,true);
									micro.setFields(collect.getMicroFields().get(i));
									micro_array.add(micro);
									micro_array_of_panels.add(micro_pattern_panel);
									frmPatternEditor.getContentPane().add(micro_pattern_panel);
									comboBox.addItem("Micro-Pattern:"+micro_array.size());
									micro_pattern_panel.setVisible(false);
									
								}
							}
							if(collect.getExistenceOfInductive()) {
								
								for(int i=0;i<inductive_mini_array.size();i++) {
									comboBox.removeItem("Inductive-Mini:"+(i+1));
									inductive_mini_array_of_panels.get(i).setVisible(false);
									frmPatternEditor.getContentPane().remove(inductive_mini_array_of_panels.get(i));
									inductive_mini_array.clear();
									inductive_mini_array_of_panels.clear();
								}
								
								for(int i=0;i<collect.getInductiveFields().size();i++) {
									inductive_panel = new JPanel();
									inductive_panel.setPreferredSize(new Dimension(1100,1400));
									inductive_scroll=new JScrollPane(inductive_panel);
									inductive_scroll.setPreferredSize(new Dimension(1100,1400));
									inductive_scroll.setBounds(0, 22, 1200, 1000);
									inductive_mini=new InductiveMini(inductive_panel,true);
									inductive_mini.setFields(collect.getInductiveFields().get(i));
									inductive_mini_array.add(inductive_mini);
									inductive_mini_array_of_panels.add(inductive_scroll);
									frmPatternEditor.getContentPane().add(inductive_scroll);
									inductive_scroll.setVisible(false);
									comboBox.addItem("Inductive-Mini:"+inductive_mini_array.size());
									
								}

							}
							if(collect.getExistenceOfDeductive()) {
								
								for(int i=0;i<deductive_mini_array.size();i++) {
									comboBox.removeItem("Deductive-Mini:"+(i+1));
									deductive_mini_array_of_panels.get(i).setVisible(false);
									frmPatternEditor.getContentPane().remove(deductive_mini_array_of_panels.get(i));
									deductive_mini_array.clear();
									deductive_mini_array_of_panels.clear();
								}
								
								for(int i=0;i<collect.getDeductiveFields().size();i++) {
									deductive_panel = new JPanel();
									deductive_panel.setPreferredSize(new Dimension(1100,1400));
									deductive_scroll=new JScrollPane(deductive_panel);
									deductive_scroll.setPreferredSize(new Dimension(1100,1400));
									deductive_scroll.setBounds(0, 22, 1200, 1000);
									deductive_mini=new DeductiveMini(deductive_panel,true);

									deductive_mini.setFields(collect.getDeductiveFields().get(i));
									
									deductive_mini_array.add(deductive_mini);
									deductive_mini_array_of_panels.add(deductive_scroll);
									frmPatternEditor.getContentPane().add(deductive_scroll);
									deductive_scroll.setVisible(false);
									comboBox.addItem("Deductive-Mini:"+deductive_mini_array.size());
									
								}
								
							}
							if(collect.getExistenceOfGang()) {
								for(int i=0;i<gang_array.size();i++) {
									comboBox.removeItem("Gang-of-Four:"+(i+1));
									gang_array_of_panels.get(i).setVisible(false);
									frmPatternEditor.getContentPane().remove(gang_array_of_panels.get(i));
									gang_array.clear();
									gang_array_of_panels.clear();
								}
								
								for(int i=0;i<collect.getGangFields().size();i++) {
									gang_panel = new JPanel();
									gang_panel.setPreferredSize(new Dimension(1100,3600));
									gang_scroll=new JScrollPane(gang_panel);
									gang_scroll.setPreferredSize(new Dimension(1100,3600));
									gang_scroll.setBounds(0, 22, 1200, 1000);
									gang=new GangPattern(gang_panel,false);
									gang.setFields(collect.getGangFields().get(i));
									gang_array.add(gang);
									gang_array_of_panels.add(gang_scroll);
									frmPatternEditor.getContentPane().add(gang_scroll);
									gang_scroll.setVisible(false);
									comboBox.addItem("Gang-of-Four:"+gang_array.size());
									
								}
							}
							if(collect.getExistenceOfSystem()) {
								for(int i=0;i<system_array.size();i++) {
									comboBox.removeItem("System of Patterns:"+(i+1));
									system_array_of_panels.get(i).setVisible(false);
									frmPatternEditor.getContentPane().remove(system_array_of_panels.get(i));
									system_array.clear();
									system_array_of_panels.clear();
								}
								for(int i=0;i<collect.getSystemFields().size();i++) {
									system_panel = new JPanel();
									system_panel.setPreferredSize(new Dimension(1100,3400));
									system_scroll=new JScrollPane(system_panel);
									system_scroll.setPreferredSize(new Dimension(1100,3400));
									system_scroll.setBounds(0, 22, 1200, 1000);
									system=new SystemTemplate(system_panel,true);
									system.setFields(collect.getSystemFields().get(i));
									system_array.add(system);
									system_array_of_panels.add(system_scroll);
									frmPatternEditor.getContentPane().add(system_scroll);
									system_scroll.setVisible(false);
									comboBox.addItem("System of Patterns:"+system_array.size());
									
								}
							}
							JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"READ SUCCESSFULL!!!");
						}
					}else {
						JOptionPane.showMessageDialog(frmPatternEditor.getContentPane(),"PLEASE SELECT FILE TO READ FROM!");
					}
				}
					
					
			}
		});
	}
}
