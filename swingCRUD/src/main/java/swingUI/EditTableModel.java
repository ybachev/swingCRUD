package swingUI;

import java.util.List;

import model.Person;

public class EditTableModel extends PersonTableModel {
	private static final long serialVersionUID = -7428067213035949195L;
	

	public EditTableModel(List<Person> personsList, List<String> columnNames) {
		super(personsList, columnNames);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
}
