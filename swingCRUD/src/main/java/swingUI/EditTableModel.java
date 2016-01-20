package swingUI;

import java.util.List;

import model.Person;

public class EditTableModel extends PersonTableModel {
	private static final long serialVersionUID = -7428067213035949195L;
	// ("Id", "First Name", "Last Name", "Middle Name", "Date of Birth")
	Class[] types = new Class [] { java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.util.Date.class  };
	

	public EditTableModel(List<Person> personsList, List<String> columnNames) {
		super(personsList, columnNames);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
	

  
        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }
}
