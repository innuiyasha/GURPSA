package gui;

import java.awt.EventQueue;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import adventureMode.AdventureMain;
import characterManager.Character;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
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
	
	public static void main(String[] args) {
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
		tabbedPane.setBounds(0, 0, 774, 508);
		contentPane.add(tabbedPane);
		
		JPanel console_panel = new JPanel();
		tabbedPane.addTab("Console", null, console_panel, null);
		console_panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 749, 419);
		console_panel.add(scrollPane);
		
		control = new JTextArea();
		control.setEditable(false);
		scrollPane.setViewportView(control);
		
		
		textField = new JTextField();
		textField.setBounds(10, 449, 749, 20);
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
		action_scroller.setBounds(10, 212, 749, 226);
		adventure_panel.add(action_scroller);
		
		
		DefaultListModel<characterManager.Character> character_model = new DefaultListModel<characterManager.Character>();
		for(int i = 0; i < 15; i++) {
			characterManager.Character character = new characterManager.Character();
			character.setName("John " + i);
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
		participant_scroller.setBounds(434, 11, 325, 190);
		adventure_panel.add(participant_scroller);
		
		to_participate_button = new JButton(">");
		to_participate_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				moveSelectedToParticipants();
			}
		});
		to_participate_button.setBounds(345, 64, 79, 23);
		adventure_panel.add(to_participate_button);
		
		to_characters_button = new JButton("<");
		to_characters_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				moveSelectedToCharacters();
			}
		});
		to_characters_button.setBounds(345, 116, 79, 23);
		adventure_panel.add(to_characters_button);
		
		run_button = new JButton("Run");
		run_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		run_button.setBounds(345, 446, 79, 23);
		adventure_panel.add(run_button);
		
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, console_panel, adventure_panel}));
	}

	private void moveSelectedToCharacters() {
		DefaultListModel<characterManager.Character> character_model = (DefaultListModel<Character>) character_list.getModel();
		DefaultListModel<characterManager.Character> participant_model = (DefaultListModel<Character>) participant_list.getModel();
		
		int[] indices = participant_list.getSelectedIndices();
	    
		for(int index : indices) {
	    	
	    	index--;
	    	
	    	if (index == -1) { //no selection, so insert at beginning
		        index = 0;
		    } else {           //add after the selected item
		        index++;
		    }

		    character_model.addElement(participant_model.getElementAt(index));

		    //Select the new item and make it visible.
		    character_list.setSelectedIndex(index);
		    character_list.ensureIndexIsVisible(index);
	    	
	    	
	    	
	    	participant_model.remove(index);

		    int size = participant_model.getSize();

		    if (size == 0) { //Nobody's left, disable firing.
		       // to_characters_button.setEnabled(false);

		    } else { //Select an index.
		        if (index == participant_model.getSize()) {
		            //removed item in last position
		            index--;
		        }

		        participant_list.setSelectedIndex(index);
		        participant_list.ensureIndexIsVisible(index);
		    }
		    
	    }
	}
	
	private void moveSelectedToParticipants() {
		DefaultListModel<characterManager.Character> character_model = (DefaultListModel<Character>) character_list.getModel();
		DefaultListModel<characterManager.Character> participant_model = (DefaultListModel<Character>) participant_list.getModel();
		
		int[] indices = character_list.getSelectedIndices();
		
	    for(int index : indices) {
	    	
	    	index--;
	    	
	    	if (index == -1) { //no selection, so insert at beginning
		        index = 0;
		    } else {           //add after the selected item
		        index++;
		    }

		    participant_model.addElement(character_model.getElementAt(index));

		    //Select the new item and make it visible.
		    participant_list.setSelectedIndex(index);
		    participant_list.ensureIndexIsVisible(index);
	    	
	    	
	    	
	    	character_model.remove(index);

		    int size = character_model.getSize();

		    if (size == 0) { //Nobody's left, disable firing.
		     //   to_participate_button.setEnabled(false);

		    } else { //Select an index.
		        if (index == character_model.getSize()) {
		            //removed item in last position
		            index--;
		        }

		        character_list.setSelectedIndex(index);
		        character_list.ensureIndexIsVisible(index);
		    }
		    
	    }
	    
	}
	
}