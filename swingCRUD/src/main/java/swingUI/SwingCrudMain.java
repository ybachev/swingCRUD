package swingUI;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import model.Person;
import service.PersonService;

public class SwingCrudMain extends JPanel {
	private static final long serialVersionUID = -9065552749619842231L;

	private MyDialogPanel dialogPanel;
	private JDialog dialog;
	private Person selectedPerson;
	private String actionToPerform;

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
		button.setActionCommand("add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openTableAction(e);
			}
		});
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
				openTableAction(e);
			}
		});
		button.setActionCommand("edit");
		add(button, c);

		button = new JButton("Delete");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weighty = 0;
		// c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = table.getSelectedRow();
				selectedPerson = personTableModel.getPersonAt(selection);
				personService.delete(selectedPerson.getId());
				personTableModel.fireTableDataChanged();
			}
		});
		button.setActionCommand("delete");
		add(button, c);
		
		button = new JButton("Cancel");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weighty = 0;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			}
		});
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

	private void openTableAction(ActionEvent e) {
		int selection = table.getSelectedRow();
		selection = table.convertRowIndexToModel(selection);

		String dialogTitle = "";
		// System.out.println("openTableAction : " + selectedPerson.toString());

		if ("edit".equals(e.getActionCommand())) {
			setActionToPerform("edit");
			// ToDo add check for selection !!!
			selectedPerson = personTableModel.getPersonAt(selection);
			dialogTitle = "Edit Person";
		}
		if ("add".equals(e.getActionCommand())) {
			setActionToPerform("add");
			dialogTitle = "Add New Person";
			selectedPerson = new Person("Change", "Change", "Change", new Date(3866108));
		}

		dialogPanel = new MyDialogPanel(this);
		// lazy creation of the JDialog
		if (dialog == null) {
			Window win = SwingUtilities.getWindowAncestor(this);
			if (win != null) {
				dialog = new JDialog(win, dialogTitle, ModalityType.APPLICATION_MODAL);
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
		personTableModel.fireTableDataChanged();
	}

	public PersonService getPersonService() {
		return personService;
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

	public String getActionToPerform() {
		return actionToPerform;
	}

	private void setActionToPerform(String actionToPerform) {
		this.actionToPerform = actionToPerform;
	}
}

class MyDialogPanel extends JPanel {
	private static final long serialVersionUID = -7108948839202922735L;

	private SwingCrudMain mainPanel;
	private JTextField field = new JTextField();
	private JTable editPersonTable;
	// private PersonTableModel personModel;
	DefaultTableModel model;
	private Person personToEdit;

	PersonService personService;

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	public MyDialogPanel() {
	}

	public MyDialogPanel(SwingCrudMain mainPanel) {
		super();
		this.mainPanel = mainPanel;
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okButtonAction();
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelButtonAction();
			}
		});

		if ("add".equals(mainPanel.getActionToPerform())) {
			personToEdit = new Person("Change", "Change", "Change", new Date(3866108));
			System.out.println("MyDialogConstructor : " + personToEdit.toString());
		} else {
			personToEdit = mainPanel.getSelectedPerson();
		}

		personService = mainPanel.getPersonService();

		// can't make it to work with AbstractTableModel personModel = new
		// EditTableModel(Arrays.asList(personToEdit),
		// mainPanel.getColumnNames());
		// so I'm using DefaultTableModel - model

		// override model - editable = true
		model = new DefaultTableModel() {
			private static final long serialVersionUID = -8391638682140976961L;

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				switch (columnIndex) {
                case 0:
                    return false;
                default:
                    return true;
				}
			}
		};

		String[] columnNames = { "Id", "First Name", "Last Name", "Middle Name", "Date of Birth" };
		Object[][] data = { { personToEdit.getId(), personToEdit.getFirstName(), personToEdit.getLastName(),
				personToEdit.getMiddleName(), personToEdit.BirthDateFormated() } };

		model = new DefaultTableModel(data, columnNames);
		editPersonTable = new JTable(model){
			private static final long serialVersionUID = -4990635976009842460L;

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				switch (columnIndex) {
                case 0:
                    return false;
                default:
                    return true;
				}
			}
		};

		Action action = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				TableCellListener tcl = (TableCellListener) e.getSource();
				int columnIndex = tcl.getColumn();

				System.out.println("Edited columns:");
				System.out.println("Row   : " + tcl.getRow());
				System.out.println("Column: " + tcl.getColumn());
				System.out.println("Column Name: " + model.getColumnName(tcl.getColumn()));
				System.out.println("Old   : " + tcl.getOldValue());
				System.out.println("New   : " + tcl.getNewValue());

				// Object value = "??";

				switch (columnIndex) {
				case 0:
					personToEdit.setId((Integer) tcl.getNewValue());
					System.out.println("Changed : " + personToEdit.getId());
					break;
				case 1:
					personToEdit.setFirstName((String) tcl.getNewValue());
					System.out.println("Changed : " + personToEdit.getFirstName());
					break;
				case 2:
					personToEdit.setLastName((String) tcl.getNewValue());
					System.out.println("Changed : " + personToEdit.getLastName());
					break;
				case 3:
					personToEdit.setMiddleName((String) tcl.getNewValue());
					System.out.println("Changed : " + personToEdit.getMiddleName());
					break;
				case 4:
					// hate code like this
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String strDate = (String) tcl.getNewValue();
					Date date = null;
					try {
						date = sdf.parse(strDate);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					personToEdit.setBirthDate(date);
					break;
				}

				Person per = personService.findById(personToEdit.getId());
				if (per == null) {
					personService.persist(personToEdit);
				} else {
					personService.update(personToEdit);
				}

			}
		};

		TableCellListener tcl = new TableCellListener(editPersonTable, action);

		JScrollPane jsPane = new JScrollPane(editPersonTable);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 100));

		add(jsPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(okButton, BorderLayout.CENTER);
		buttonPanel.add(cancelButton, BorderLayout.EAST);
		buttonPanel.add(field, BorderLayout.SOUTH);

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

	private void cancelButtonAction() {
		Window win = SwingUtilities.getWindowAncestor(this);
		if (win != null) {
			win.dispose();
		}
	}
}
