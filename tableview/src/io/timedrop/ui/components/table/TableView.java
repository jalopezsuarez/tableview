package io.timedrop.ui.components.table;

import java.lang.reflect.Constructor;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class TableView extends JScrollPane
{
	private static final long serialVersionUID = 4040820615116789368L;
	private DefaultTableModel model;

	public TableView(Object[][] data, String factory)
	{
		String[] columnNames = { "" };
		model = new DefaultTableModel(data, columnNames)
		{
			private static final long serialVersionUID = 172439379526451400L;

			@Override
			public Class<?> getColumnClass(int column)
			{
				return getValueAt(0, column).getClass();
			}
		};

		Table table = new Table(model);

		Object render = null;
		Object editor = null;
		try
		{
			Class<?> clazz = Class.forName(factory);
			Constructor<?> ctor = clazz.getConstructors()[0];
			render = ctor.newInstance(new Object[] { table });
			if (!(render instanceof TableCellRenderer))
				render = null;
		}
		catch (Exception ex)
		{
		}
		try
		{
			Class<?> clazz = Class.forName(factory);
			Constructor<?> ctor = clazz.getConstructors()[0];
			editor = ctor.newInstance(new Object[] { table });
			if (!(editor instanceof TableCellEditor))
				editor = null;
		}
		catch (Exception ex)
		{
		}
		table.setTableFactory((TableCellRenderer) render, (TableCellEditor) editor);

		setViewportView(table);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setBorder(BorderFactory.createEmptyBorder());
	}

	public DefaultTableModel getModel()
	{
		return model;
	}
}
