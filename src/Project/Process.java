package Project;

import java.lang.Thread ;

enum STATE { New, Ready, Running, Waiting, Terminated }
public class Process extends Thread{

	public int BurstTime;
	public int Remaing_BurstTime;
	public int Memory;
	public int ArrivalTime;
	public int WaitingTime;
	public int TurnaroundTime;
	public int CompletionTime;

	public String pid;
	public STATE state = STATE.New ;
	
	
	Process(String PID, int BurstTime, int Memory){
		this.pid = PID;
		this.BurstTime = BurstTime;
		this.Memory = Memory;
		this.Remaing_BurstTime = BurstTime;
	}
	
	
}
