package swingUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import model.Person;

public class PersonTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 6652749330583520683L;
	
	private List<Person> personsList;
	private List<String> columnNames = new ArrayList<String>();
	
	public PersonTableModel(List<Person> personsList, List<String> columnNames){
		this.personsList = new ArrayList<Person>(personsList);
		this.columnNames = columnNames;
		
	}

	@Override
    public String getColumnName(int column) {
        return columnNames.get(column).toString();
    }
	
	public int getRowCount() {
		return personsList.size();
	}

	public int getColumnCount() {
		return columnNames.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
	      Object value = "??";
	        Person person = personsList.get(rowIndex);
	        switch (columnIndex) {
	            case 0:
	                value = person.getId();
	                break;
	            case 1:
	                value = person.getFirstName();
	                break;
	            case 2:
	                value = person.getLastName();
	                break;
	            case 3:
	                value = person.getMiddleName();
	                break;
	            case 4:
	                value = person.BirthDateFormated();
	                break;
	        }
	        
	        return value;
	}
	
	/**
     * This will return the user at the specified row...
     * @param row
     * @return Person
     */
    public Person getPersonAt(int row) {
        return personsList.get(row);
    }

}
