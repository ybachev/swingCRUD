package swingUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Person;
import service.PersonService;

public class SwingCrudMain extends JPanel {
	private static final long serialVersionUID = -9065552749619842231L;

	private MyDialogPanel dialogPanel;
	private JDialog dialog;
	private Person selectedPerson;

	private static void createAndShowUI() {
		JFrame frame = new JFrame("Swing CRUD UI - gridbag and table model");
		frame.setPreferredSize(new Dimension(500, 200));
		frame.getContentPane().add(new SwingCrudMain());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// let's be sure to start Swing on the Swing event thread
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowUI();
			}
		});
	}

	private List<String> columnNames = Arrays.asList("Id", "First Name", "Last Name", "Middle Name", "Date of Birth");


	private JButton button;
	private JTable table;
	private JTextField field;
	private PersonTableModel personTableModel;

	private PersonService personService = new PersonService();
	List<Person> personsList;

	SwingCrudMain() {
		personsList = personService.findAll();
		personTableModel = new PersonTableModel(personsList, columnNames);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		table = new JTable(personTableModel);
		// personTableModel.

		JScrollPane jsPane = new JScrollPane(table);
		c.ipady = 100;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 4; // 4 columns wide
		c.fill = GridBagConstraints.BOTH;
		add(jsPane, c);

		button = new JButton("Add");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 0; // request any extra vertical space
		// c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 0; // aligned with button 2
		c.gridy = 1; // row
		c.gridwidth = 1;
		add(button, c);

		button = new JButton("Edit");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weighty = 0;
		// c.anchor = GridBagConstraints.PAGE_END;
		// c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openTableAction();
			}
		});

		add(button, c);

		button = new JButton("Delete");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weighty = 0;
		// c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		add(button, c);

		field = new JTextField();
		c.ipady = 20;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 4; // 4 columns wide
		// c.insets = new Insets(10, 0, 0, 0);
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.BOTH;
		add(field, c);

	}

	private void openTableAction() {
		int selection = table.getSelectedRow();
		selection = table.convertRowIndexToModel(selection);
		selectedPerson = personTableModel.getPersonAt(selection);
		System.out.println("openTableAction : " + selectedPerson.toString());

		dialogPanel = new MyDialogPanel(this);
		// lazy creation of the JDialog
		if (dialog == null) {
			Window win = SwingUtilities.getWindowAncestor(this);
			if (win != null) {
				dialog = new JDialog(win, "Edit Person", ModalityType.APPLICATION_MODAL);
				dialog.getContentPane().add(dialogPanel);
				dialogPanel.setFieldText(selectedPerson.toString());
				dialogPanel.setPersonToEdit(selectedPerson);
				dialog.pack();
				dialog.setLocationRelativeTo(null);

			}
		}
		dialog.setVisible(true); 
		// here the modal dialog takes over

		field.setText(dialogPanel.getFieldText());
		dialog = null;
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(Person selectedPerson) {
		this.selectedPerson = selectedPerson;
	}
	
	public List<String> getColumnNames() {
		return columnNames;
	}
}

class MyDialogPanel extends JPanel {
	private static final long serialVersionUID = -7108948839202922735L;

	private SwingCrudMain mainPanel;
	private JTextField field = new JTextField(10);
	private JTable editPersonTable;
	private PersonTableModel personModel;
	private Person personToEdit;


	private JButton okButton = new JButton("OK");

	public MyDialogPanel() {}

	public MyDialogPanel(SwingCrudMain mainPanel) {
		super();
		this.mainPanel = mainPanel;
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okButtonAction();
			}
		});
		
		personToEdit = mainPanel.getSelectedPerson();
		if (personToEdit != null) {
			personModel = new PersonTableModel(Arrays.asList(personToEdit), mainPanel.getColumnNames());
			//setLayout(new GridBagLayout());
			editPersonTable = new JTable(personModel);
			JScrollPane jsPane = new JScrollPane(editPersonTable);
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(600, 100));
			add(jsPane, BorderLayout.CENTER);
			add(field, BorderLayout.NORTH);
			add(okButton, BorderLayout.SOUTH);

		}
	}

	// to allow outside classes to get the text held by the JTextField
	public String getFieldText() {
		return field.getText();
	}

	public void setFieldText(String text) {
		field.setText(text);
	}
	
	public Person getPersonToEdit() {
		return personToEdit;
	}

	public void setPersonToEdit(Person personToEdit) {
		this.personToEdit = personToEdit;
	}

	// This button's action is simply to dispose of the JDialog.
	private void okButtonAction() {
		Window win = SwingUtilities.getWindowAncestor(this);
		if (win != null) {
			win.dispose();
		}
	}
}