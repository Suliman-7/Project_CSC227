package Project;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread;
import java.util.Scanner;


public class Main extends Thread {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        Process[] ArrayOfProcess = new Process[30]; 
        int ProcessesCounter = 0 ; 
        
			System.out.print("Please enter the file path : ");
			String Path = sc.next();
			File F = new File(Path);
			Scanner R = new Scanner(F);
			
			while(R.hasNextLine()) {
				String data = R.nextLine();
				String[] m1 = data.split(",");
				if(m1.length != 1) {
					int BrustTime = Integer.parseInt(m1[1].split(" ")[1]);
					int Memory = Integer.parseInt(m1[2].split(" ")[1]);
					Process P = new Process(m1[0], BrustTime, Memory);
					ArrayOfProcess[ProcessesCounter] = P;
					ProcessesCounter++;
				}
			}

						
        System.out.print(
                "Please Choose Scheduling Algorithm:\n" +
                "1- First Come First Serve (FCFS) \n" +
                "2- Shortest Job First (SJF) \n" +
                "3- Round Robin (RR) \n");

        int Algorithm = sc.nextInt(); 
        
                
			switch(Algorithm){
				case 1:
					System.out.println("Your choice : First Come First Serve (FCFS)");
					Scheduler.FCFS(ArrayOfProcess, ProcessesCounter);
					break;
				case 2:
					System.out.println("Your choice : Shortest Job First (SJF)");
					Scheduler.SJF(ArrayOfProcess, ProcessesCounter);
					break;
				case 3:
					System.out.println("Your choice : Round-Robin (RR)");
					Scheduler.RR(ArrayOfProcess, ProcessesCounter);
					break;
				default:
					System.out.println("Please enter a correct number");
			}

        
        } 
        }