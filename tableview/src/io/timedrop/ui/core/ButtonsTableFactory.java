package io.timedrop.ui.core;

import io.timedrop.ui.components.layout.GridHelper;
import io.timedrop.ui.components.table.Table;
import io.timedrop.ui.components.Panel;
import io.timedrop.ui.components.TextField;
import io.timedrop.ui.components.Button;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ButtonsTableFactory extends AbstractCellEditor implements TableCellRenderer, TableCellEditor
{
	private static final long serialVersionUID = 8117537887121074776L;

	// =======================================================

	private JTable table;
	private Object value;
	private int row;

	// =======================================================

	private Panel panel;

	private TextField textProyecto;
	private Button buttonIncrease;
	private Button buttonDecrease;

	// ~ Methods
	// =======================================================

	public ButtonsTableFactory(Table table)
	{
		super();

		// -------------------------------------------------------

		table.setBackground(Color.decode("#000000"));

		// -------------------------------------------------------

		initializeRowCell();
		editableRowCell();

		// -------------------------------------------------------
	}

	// ~ Methods
	// =======================================================

	private void initializeRowCell()
	{
		panel = new Panel();
		panel.setOpaque(true);

		GridHelper layoutPanel = new GridHelper(panel);
		{
			textProyecto = new TextField("xxx");
			textProyecto.setForeground(Color.decode("#FFFFFF"));
			layoutPanel.constrains().weightx = 1;
			layoutPanel.constrains().fill = GridBagConstraints.HORIZONTAL;
			layoutPanel.add(textProyecto, 0, 0);
		}
		{
			buttonIncrease = new Button("increase");
			buttonIncrease.setForeground(Color.decode("#FFFFFF"));
			layoutPanel.constrains().insets = new Insets(10, 10, 10, 10);
			layoutPanel.add(buttonIncrease, 1, 0);
		}
		{
			buttonDecrease = new Button("decrease");
			buttonDecrease.setForeground(Color.decode("#FFFFFF"));
			layoutPanel.constrains().insets = new Insets(10, 10, 10, 10);
			layoutPanel.add(buttonDecrease, 2, 0);
		}
	}

	private void renderRowCell(JTable table, Object value, int row)
	{
		this.table = table;
		this.value = value;
		this.row = row;

		// -------------------------------------------------------

		Tarea tarea = (Tarea) value;
		textProyecto.setText(tarea.getTarea() + "/" + tarea.getProyecto());
	}

	// ~ Methods
	// =======================================================

	private void editableRowCell()
	{
		buttonIncrease.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				((DefaultTableModel) table.getModel()).removeRow(row);
				stopCellEditing();
			}
		});

		buttonDecrease.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Tarea tarea2 = (Tarea) ((DefaultTableModel) table.getModel()).getValueAt(row, 0);
				// tarea2.setTarea("hola");

				Tarea tarea = (Tarea) value;
				tarea.setTarea("hola");
				stopCellEditing();
			}
		});
	}

	// ~ Methods
	// =======================================================

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		renderRowCell(table, value, row);

		int rowHeight = (int) panel.getPreferredSize().getHeight();
		if (table.getRowHeight(row) != rowHeight)
			table.setRowHeight(row, rowHeight);
		if (isSelected)
			panel.setBackground(table.getSelectionBackground());
		else
			panel.setBackground(table.getBackground());

		return panel;
	}

	// ~ Methods
	// =======================================================

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		renderRowCell(table, value, row);

		int rowHeight = (int) panel.getPreferredSize().getHeight();
		if (table.getRowHeight(row) != rowHeight)
			table.setRowHeight(row, rowHeight);
		panel.setBackground(table.getSelectionBackground());

		return panel;
	}

	@Override
	public Object getCellEditorValue()
	{
		return value;
	}

	@Override
	public boolean isCellEditable(EventObject anEvent)
	{
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent)
	{
		return true;
	}

	@Override
	public boolean stopCellEditing()
	{
		table.removeEditor();

		JViewport viewport = (JViewport) table.getParent();
		if (table.getModel().getRowCount() > 0)
		{
			if (table.getModel().getRowCount() == row)
			{
				table.setRowSelectionInterval(row - 1, row - 1);
			}
			else if (viewport.getViewPosition().getY() == 0)
			{
				table.setRowSelectionInterval(row, row);
			}
			else if (viewport.getExtentSize().getHeight() + viewport.getViewPosition().getY() >= table.getSize().getHeight())
			{
				table.setRowSelectionInterval(row - 1, row - 1);
			}
			else
			{
				table.setRowSelectionInterval(row, row);
			}
		}

		return true;
	}

	@Override
	public void cancelCellEditing()
	{
	}

	@Override
	public void addCellEditorListener(CellEditorListener l)
	{
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l)
	{
	}

}
