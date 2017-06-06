package gui;

import java.awt.EventQueue;
import java.io.File;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import adventureMode.AdventureMain;
import characterManager.Character;
import characterManager.CharacterManager;
import skillManager.SkillManager;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import actionManager.ActionManager;
import advantageManager.AdvantageManager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class MainWindow extends JFrame {

	// This simply gets rid of a warning. I don't know what it is.
	private static final long serialVersionUID = -8205062842025549985L;
	
	private JPanel contentPane;
	private static JTextArea control;
	private static JTextField textField;

	/**
	 * Launch the application.
	 * 
	 */
	
	private static boolean runUI = true;
	
	private JScrollPane scrollPane;
	private JPanel adventure_panel;
	private JList character_list;
	private JList participant_list;
	private JButton to_participate_button;
	private JButton to_characters_button;
	private JButton run_button;
	private JButton roll_button;
	private JTextField roll_result_field;
	private JButton reset_button;
	
	private static AdventureMain adventureMain;
	
	public static void main(String[] args) {	

		adventureMain = new AdventureMain();
		
		if(runUI) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainWindow frame = new MainWindow();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			ExecutorService pool = Executors.newFixedThreadPool(3);
			
			pool.execute(new Runnable() {
				public void run() {
					// Ensures that MainWindow has had time to instantialise control
					while(control == null || textField == null) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					
					PrintStream out = new PrintStream( new JTextAreaOutputStream( control ) );
					System.setOut( out );
					System.setErr( out );
					
					System.setIn(new JTextFieldInputStream(textField));
					
					System.out.println();
					
					AdventureMain.MainMenu();
				}
			});
		} else {
			AdventureMain.MainMenu();
		}
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		/*
		 * setIconImage should use the classpath to find an image rather than the absolute path being used.
		 */
		setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\resources\\Guthix_Tome.png"));
		setTitle("GURPSA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 796, 455);
		contentPane.add(tabbedPane);
		
		JPanel console_panel = new JPanel();
		tabbedPane.addTab("Console", null, console_panel, null);
		console_panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 771, 339);
		console_panel.add(scrollPane);
		
		control = new JTextArea();
		control.setEditable(false);
		scrollPane.setViewportView(control);
		
		
		textField = new JTextField();
		textField.setBounds(10, 371, 771, 45);
		console_panel.add(textField);
		textField.setColumns(10);
		
		adventure_panel = new JPanel();
		tabbedPane.addTab("Adventure", null, adventure_panel, null);
		adventure_panel.setLayout(null);
		
		DefaultListModel<String> action_model = new DefaultListModel<String>();
		for(int i = 0; i < 100; i++) {
			action_model.addElement("Action " + i);
		}
		
		JList action_list = new JList(action_model);
		action_list.setVisibleRowCount(15);
		action_list.setLayoutOrientation(JList.VERTICAL_WRAP);
		JScrollPane action_scroller = new JScrollPane(action_list);
		action_scroller.setBounds(10, 212, 771, 168);
		adventure_panel.add(action_scroller);
		
		
		DefaultListModel<characterManager.Character> character_model = new DefaultListModel<characterManager.Character>();
		
		for(Character character : adventureMain.getCharacters()) {
			character_model.addElement(character);
		}
		
		character_list = new JList(character_model);
		character_list.setVisibleRowCount(-1);
		JScrollPane character_scroller = new JScrollPane(character_list);
		character_scroller.setBounds(10, 11, 325, 190);
		adventure_panel.add(character_scroller);
		
		DefaultListModel<characterManager.Character> participant_model = new DefaultListModel<characterManager.Character>();
		participant_list = new JList(participant_model);
		participant_list.setVisibleRowCount(-1);
		JScrollPane participant_scroller = new JScrollPane(participant_list);
		participant_scroller.setBounds(456, 11, 325, 190);
		adventure_panel.add(participant_scroller);
		
		to_participate_button = new JButton(">");
		to_participate_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				moveSelectedToParticipants();
			}
		});
		to_participate_button.setBounds(345, 61, 101, 23);
		adventure_panel.add(to_participate_button);
		
		to_characters_button = new JButton("<");
		to_characters_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				moveSelectedToCharacters();
			}
		});
		to_characters_button.setBounds(345, 114, 101, 23);
		adventure_panel.add(to_characters_button);
		
		run_button = new JButton("Run");
		run_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				AdventureMain adventureMain = new AdventureMain();
				
				
				
				
				
				
			}
		});
		run_button.setBounds(345, 393, 101, 23);
		adventure_panel.add(run_button);
		
		reset_button = new JButton("Reset");
		reset_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				resetParticipants();
			}
		});
		reset_button.setBounds(345, 178, 101, 23);
		adventure_panel.add(reset_button);
		
		roll_result_field = new JTextField();
		roll_result_field.setText("0");
		roll_result_field.setHorizontalAlignment(SwingConstants.CENTER);
		roll_result_field.setEditable(false);
		roll_result_field.setBounds(736, 466, 38, 31);
		contentPane.add(roll_result_field);
		roll_result_field.setColumns(10);
		
		roll_button = new JButton("Roll");
		roll_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				roll_result_field.setText(utilities.Utilities.standardDiceRoll() + "");
			}
			@Override
			public void mousePressed(MouseEvent e) {
				roll_result_field.setText("??");
			}
		});
		roll_button.setBounds(670, 466, 61, 31);
		contentPane.add(roll_button);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 455, 796, 3);
		contentPane.add(separator);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, console_panel, adventure_panel}));
	}

	private void moveSelectedToCharacters() {
		DefaultListModel<characterManager.Character> character_model = (DefaultListModel<Character>) character_list.getModel();
		DefaultListModel<characterManager.Character> participant_model = (DefaultListModel<Character>) participant_list.getModel();
		
		int[] indices = participant_list.getSelectedIndices();
	    
		for(int i = indices.length - 1; i > -1; i--) {
	    	//System.out.println(index + "");

	    	int index = indices[i];
	    	
		    character_model.addElement(participant_model.getElementAt(index));

		    //Select the new item and make it visible.
		    character_list.setSelectedIndex(index);
		    character_list.ensureIndexIsVisible(index);
	    	
	    	
	    	
	    	participant_model.remove(index);
		    
	    }
	}
	
	private void moveSelectedToParticipants() {
		DefaultListModel<characterManager.Character> character_model = (DefaultListModel<Character>) character_list.getModel();
		DefaultListModel<characterManager.Character> participant_model = (DefaultListModel<Character>) participant_list.getModel();
		
		int[] indices = character_list.getSelectedIndices();
		
	    for(int i = indices.length - 1; i > -1; i--) {
	    	//System.out.println(index + "");

	    	int index = indices[i];
	    	
		    participant_model.addElement(character_model.getElementAt(index));

		    //Select the new item and make it visible.
		    participant_list.setSelectedIndex(index);
		    participant_list.ensureIndexIsVisible(index);
	    	
	    	
	    	
	    	character_model.remove(index);
		    
	    }
	    
	}
	
	private characterManager.Character[] getParticipants() {
		DefaultListModel<characterManager.Character> model = (DefaultListModel<Character>) character_list.getModel();
		Character[] characters = new Character[model.getSize()];
		for(int i = 0; i < model.getSize(); i++) {
			characters[i] = model.getElementAt(i);
		}
		
		return characters;
	}
	
	private void resetParticipants() {
		DefaultListModel<characterManager.Character> character_model = (DefaultListModel<Character>) character_list.getModel();
		DefaultListModel<characterManager.Character> participant_model = (DefaultListModel<Character>) participant_list.getModel();
		
		for(int index = participant_model.size() - 1; index > -1; index--) {
	    	
		    character_model.addElement(participant_model.getElementAt(index));

		    //Select the new item and make it visible.
		    character_list.setSelectedIndex(index);
		    character_list.ensureIndexIsVisible(index);
	    	
	    	
	    	
	    	participant_model.remove(index);
		    
	    }
	}
}