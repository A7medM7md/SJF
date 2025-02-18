import java.util.Scanner;

public class SJF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Welcome To Our SJF CPU Schedual Programming");
        System.out.println(" -------------------------------------------");
        System.out.print("Please, Enter number of Process: ");
        int nProcess = sc.nextInt(); // Ask User To inPut No. of Processes
        
        //=======================
        //== Variables We Want ==
        //=======================
        int Brust_T[] = new int[nProcess];
        int Arrival_T[] = new int[nProcess];
        int Waiting_T[] = new int[nProcess];
        int TurnAround_T[] = new int[nProcess];
        int Completion_T[] = new int[nProcess];
        int f[] = new int[nProcess];//Process Finished (1) or Not (0)
        int Start_T = 0, Total = 0;
        double sumWT = 0.0, sumTAT = 0.0;

        // Ask User To inPut Process Arrival time And Process Brust time
        for (int i = 0; i < nProcess; i++) {
            System.out.print("Enter Process " + (i + 1) + " Arrival time: ");
            Arrival_T[i] = sc.nextInt();
            System.out.print("Enter Process " + (i + 1) + " Brust time: ");
            Brust_T[i] = sc.nextInt();
        }
        sc.close();// Close Scanner
        
        // Show All Inputs
        System.out.println("\nProcess \tArrival.T \tCPU Brust.T");
        for (int j = 0; j < nProcess; j++) {
            System.out.println("P" + (j + 1) + "\t\t" + Arrival_T[j] + "\t\t" + Brust_T[j]);
        }

        //==============================
        //== Main Part Of The Program ==
        //==============================
        while(true) {
            int curr = nProcess , min_Brust = 2147483647;

            // Check all Process Finished or not
            if (Total == nProcess)
                break;
            // This Loop Check Min Brust and min arrival and Finish State
            for (int i = 0; i < nProcess; i++) {
                boolean isFinished = (f[i] == 0);//This Process is Finished or Not
                boolean isMinimum = (Brust_T[i] < min_Brust); //This Process is less than other process or Not
                boolean isComeFirst = (Arrival_T[i] <= Start_T);//This Process Coming First or Not
                if (isComeFirst && isFinished && isMinimum) {
                    // If these all Conditions True : 
                    // Set 'min' equal Brust time of this process and 'current' equl index of this process
                    min_Brust = Brust_T[i];
                    curr = i;
                }
            }

            //if Arrival.T of all Processes greater than Start_Time ==> Increase Start_Time
            if (curr == nProcess)
                Start_T++;

            else {
                Completion_T[curr] = Start_T + Brust_T[curr];
                TurnAround_T[curr] = Completion_T[curr] - Arrival_T[curr];
                Waiting_T[curr] = Start_T - Arrival_T[curr];
                Start_T += Brust_T[curr];
                f[curr] = 1; // Marked This Process it has Finished
                Total++;
            }
        }

        //========================================
        //== Calc AvgWT & AvgTAT And Print Them ==
        //========================================
        for (int i = 0; i < nProcess; i++) {
            sumWT += Waiting_T[i];
            sumTAT += TurnAround_T[i];
        }
        System.out.println("\nThe Average TurnAround Time Is: " + (sumTAT / nProcess));
        System.out.println("The Average Waiting Time Is: " + (sumWT / nProcess));
        System.out.println("The Average Response Time Is: " + (sumWT / nProcess));
        // In Case of Non-Preemptive Waiting Time of Process equals to Response Time of this Process
    }
}



/*======== Example With Output ========

Welcome To Our SJF CPU Schedual Programming
-------------------------------------------
Please, Enter number of Process: 4
Enter Process 1 Arrival time: 0
Enter Process 1 Brust time: 7
Enter Process 2 Arrival time: 2
Enter Process 2 Brust time: 4
Enter Process 3 Arrival time: 4
Enter Process 3 Brust time: 1
Enter Process 4 Arrival time: 5
Enter Process 4 Brust time: 4

Process         Arrival.T       CPU Brust.T
P1              0               7
P2              2               4
P3              4               1
P4              5               4

The Average TurnAround Time Is: 8.0
The Average Waiting Time Is: 4.0
The Average Response Time Is: 4.0
 */