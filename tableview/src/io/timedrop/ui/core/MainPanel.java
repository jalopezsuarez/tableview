package io.timedrop.ui.core;

import io.timedrop.ui.components.Button;
import io.timedrop.ui.components.table.TableView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public final class MainPanel extends JPanel
{
	private static final long serialVersionUID = 5166920443198677877L;

	public MainPanel()
	{
		super(new BorderLayout());

		Object[][] data = { { new Tarea("pepino", "verduras") }, { new Tarea("tomate", "verduras") }, { new Tarea("calabacin", "verduras") }, { new Tarea("pimiento", "verduras") } };

		TableView tableView = new TableView(data, ButtonsTableFactory.class.getName());

		add(tableView, BorderLayout.CENTER);

		Button button = new Button("xxx");

		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Vector row = new Vector();
				row.addElement(new Tarea("time: " + System.currentTimeMillis(), "construccion"));
				tableView.getModel().insertRow(tableView.getModel().getRowCount(), row);
			}
		});

		add(button, BorderLayout.NORTH);

		setPreferredSize(new Dimension(600, 300));
	}

	public static void main(String... args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}

				JFrame frame = new JFrame("MultipleButtonsInTableCell");
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				frame.getContentPane().add(new MainPanel());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
