package Project;
import java.lang.Thread;

public class Scheduler extends Thread  {
	    
		final static int MemorySize = 1024;
		
		public static void FCFS (Process[] PList, int NumOfProcesses) {
			
			int ProcessNumber = 0;
			int Memory = 0;
			int StartBT = 0;
		    int StopBT = 0 ;
			int WT = 0; 
			
			for(int i=0; i<NumOfProcesses; i++) {
				PList[i].ArrivalTime = i;
			}
			

			while (ProcessNumber < NumOfProcesses) {
				
				Memory += PList[ProcessNumber].Memory;
				if(Memory > MemorySize) {
					break;
				}
				
				StopBT = PList[ProcessNumber].BurstTime;
				
				PList[ProcessNumber].state = STATE.Ready;
				GanttChart(StartBT , StartBT + PList[ProcessNumber].Remaing_BurstTime, PList[ProcessNumber]);
				PList[ProcessNumber].state = STATE.Running;
				
				PList[ProcessNumber].CompletionTime = StartBT + StopBT;
				PList[ProcessNumber].TurnaroundTime = PList[ProcessNumber].CompletionTime - PList[ProcessNumber].ArrivalTime;
				PList[ProcessNumber].WaitingTime = WT - PList[ProcessNumber].ArrivalTime;
				
				WT = StartBT + StopBT;
				
				PList[ProcessNumber].Remaing_BurstTime = 0;
				PList[ProcessNumber].state = STATE.Terminated;
				StartBT += StopBT;
				ProcessNumber++ ;
				
			}
			Result(PList, NumOfProcesses);
		}
		 
		
		public static void SJF(Process[] PList, int NumOfProcesses) {
			
			int ProcessNumber = 0 ;
			int Memory = 0;
			int BT = 0;
			int WT = 0;
			Process Num = PList[0];
			
			for (int i=0; i <NumOfProcesses; i++) {
				for(int j=i+1; j<NumOfProcesses; j++) {
					if(PList[i].BurstTime > PList[j].BurstTime) {
						Num = PList[i];
						PList[i] = PList[j];  // Swapping
						PList[j] = Num;
					}					
				}
			}

			
			while (ProcessNumber < NumOfProcesses) {
				Memory += PList[ProcessNumber].Memory;
				if(Memory > MemorySize) {
					break;
				}
				PList[ProcessNumber].ArrivalTime = 0;
				PList[ProcessNumber].state = STATE.Ready;
				GanttChart(BT, BT+PList[ProcessNumber].Remaing_BurstTime, PList[ProcessNumber]);
				PList[ProcessNumber].state = STATE.Running;
				
				PList[ProcessNumber].CompletionTime = BT + PList[ProcessNumber].BurstTime;
				PList[ProcessNumber].TurnaroundTime = PList[ProcessNumber].CompletionTime - PList[ProcessNumber].ArrivalTime; 
				PList[ProcessNumber].WaitingTime = WT - PList[ProcessNumber].ArrivalTime;
				
				WT = PList[ProcessNumber].BurstTime + BT;
				BT += PList[ProcessNumber].BurstTime;
				PList[ProcessNumber].Remaing_BurstTime = 0;
				PList[ProcessNumber].state = STATE.Terminated;
				ProcessNumber++ ;
			}
			Result(PList, NumOfProcesses);
		}
		
		
		public static void RR(Process[] PList, int NumOfProcesses) {
				
		        int Time = 0;
				int Num = 0; 	
				int Previous_Time = 0;
				int Memory = 0;
				int TotalBT = 0;
				int Quantom = 10;
			
				for(int i=0; i<NumOfProcesses; i++) {
					
					PList[i].ArrivalTime = i;
					
					if(PList[i].BurstTime>=0)
						TotalBT += PList[i].BurstTime;			
				}
		
				
				while (TotalBT != 0) {				
					if (Num == NumOfProcesses) {
						Num = 0;
					} 
	
					if(PList[Num].BurstTime == PList[Num].Remaing_BurstTime)
						Memory += PList[Num].Memory;
	
				    
					if( Memory > MemorySize ) {
						break;
					}
					
					PList[Num].state = STATE.Ready;	
					PList[Num].state = STATE.Running;
					
					for (int i=0; i<Quantom; i++) {
						if (PList[Num].Remaing_BurstTime > 0) {
							PList[Num].Remaing_BurstTime = PList[Num].Remaing_BurstTime - 1;
							Time++;
							PList[Num].CompletionTime = Time;
							PList[Num].TurnaroundTime = PList[Num].CompletionTime - PList[Num].ArrivalTime;
							PList[Num].WaitingTime = PList[Num].TurnaroundTime - PList[Num].BurstTime;
							TotalBT--;
						}
					}
	
					if (PList[Num].Remaing_BurstTime == 0) {
						PList[Num].state = STATE.Terminated;
					} 
					
					if(Previous_Time != Time) {
						GanttChart(Previous_Time, Time, PList[Num]);
						}
					Previous_Time = Time;
					Num++;
				}
				Result(PList, NumOfProcesses);
			}
		
		
		public static void Result(Process[] PList, int NumOfProcesses) {
			
			int Total_WT = 0;
			int Total_TT = 0;
			double AWT = 0;
			double ATT = 0;
			double TerminatedCounter = 0;
			for(int i=0; i<NumOfProcesses; i++) {
				if(PList[i].state == STATE.Terminated) {

					TerminatedCounter +=1;
					Total_WT += PList[i].WaitingTime;
					Total_TT += PList[i].CompletionTime;
				}
			}
			
			if (TerminatedCounter == 0) {
				AWT = 0;
				ATT = 0;
			}
			else { 

			AWT = (Total_WT/TerminatedCounter);
			ATT = (Total_TT/TerminatedCounter);
			}
			
			System.out.println("___________________________________________________________________________");
            System.out.println((int)TerminatedCounter + " Processes Completed");
			if (TerminatedCounter < NumOfProcesses) {
				System.out.println("The Program stop execution duo to unenough memory space for other processes");
			}
			
			System.out.println("Total Waiting Time : " + Total_WT);
			System.out.println("Total Turnaround Time : "+ Total_TT);
			// --------------------------------------------------------------------------------------------------
			System.out.println("Average Waiting Time : " + AWT);
			System.out.println("Average Turnaround Time : " + ATT);
		}
		
		public static void GanttChart(int StartBT, int StopBT, Process P) {
			System.out.println(" _____");
			System.out.println("| P"+P.pid+" |");
			System.out.println(StartBT+"----"+StopBT);
	}
}