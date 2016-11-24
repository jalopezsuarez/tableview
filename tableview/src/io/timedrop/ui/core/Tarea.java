package io.timedrop.ui.core;

public class Tarea
{
	private String tarea;
	private String proyecto;

	public Tarea(String tarea, String proyecto)
	{
		this.tarea = tarea;
		this.proyecto = proyecto;
	}

	public String getTarea()
	{
		return tarea;
	}

	public void setTarea(String tarea)
	{
		this.tarea = tarea;
	}

	public String getProyecto()
	{
		return proyecto;
	}

	public void setProyecto(String proyecto)
	{
		this.proyecto = proyecto;
	}
}
