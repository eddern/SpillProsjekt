package application;

import java.util.Date;

public class Score
{

	private int score;
	private String name;
	private Date date_time;
	
	public Score(int score, String name, Date date_time) {
		this.score = score;
		this.name = name;
		this.date_time = date_time;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getDate_time()
	{
		return date_time;
	}

	public void setDate_time(Date date_time)
	{
		this.date_time = date_time;
	}
	
	
	
}
