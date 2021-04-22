package com.savage9ishere.osalgorithms.algorithmChooser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savage9ishere.osalgorithms.R;
import com.savage9ishere.osalgorithms.databinding.FragmentAlgorithmChooserBinding;

import java.util.ArrayList;

public class AlgorithmChooserFragment extends Fragment {

     private ArrayList<AlgorithmItem> algorithms;
     private AdapterForAlgorithmChooser adapter;
     private View view;

     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          algorithms = new ArrayList<>();
          algorithms.add(new AlgorithmItem("Banker Algorithm", "referred to as the detection Algorithm", " The Banker algorithm, sometimes referred to as the detection algorithm, is a resource allocation and deadlock avoidance algorithm developed by Edsger Dijkstra that tests for safety by simulating the allocation of predetermined maximum possible amounts of all resources, and then makes an \"s-state\" check to test for possible deadlock conditions for all other pending activities, before deciding whether allocation should be allowed to continue.\n" +
                  "\n" +
                  "For the Banker's algorithm to work, it needs to know three things:\n" +
                  "\n" +
                  "How much of each resource each process could possibly request[MAX]\n"+
                  "\n" +
                  "How much of each resource each process is currently holding[ALLOCATED]\n"+
                  "\n" +
                  "How much of each resource the system currently has available[AVAILABLE]\n"+
                  "\n" +
                  "Resources may be allocated to a process only if the amount of resources requested is less than or equal to the amount available; otherwise, the process waits until resources are available.\n" +
                  "\n" +
                  "Some of the resources that are tracked in real systems are memory, semaphores and interface access."));

          algorithms.add(new AlgorithmItem("Semaphore-lock Algorithm", "Semaphore also called as mutex and Counting Semaphore.", " Semaphores are integer variables that are used to solve the critical section problem by using two atomic operations, wait and signal that are used for process synchronization.\n" +
                  "\n" +
                  " semaphore is a simple integer variable used to provide synchronization among the processes.\n" +
                  "\n" +
                  "Processess of Semaphore are as Below\n"+
                  "\n"+
                  "Wait\n" +
                  "The wait operation decrements the value of its argument S, if it is positive. If S is negative or zero, then no operation is performed.\n" +
                  "wait(S)\n" +
                  "{\n" +
                  "   while (S<=0);\n" +
                  "\n" +
                  "   S--;\n" +
                  "}\n" +
                  "\n" +
                  "Signal\n"+
                  "The signal operation increments the value of its argument S.\n"+
                  "\n"+
                  "signal(S)\n" +
                  "{\n" +
                  "   S++;\n" +
                  "}\n"+
                  "\n"+
                  "."));
          algorithms.add(new AlgorithmItem("Peterson Algorithm", "Peterson's algorithm provides guaranteed mutual exclusion by using only the shared memory.", " Peterson's algorithm (or Peterson's solution) is a concurrent programming algorithm for mutual exclusion that allows two or more processes to share a single-use resource without conflict, using only shared memory for communication.\n" +
                  "\n" +
                  "The algorithm uses two variables, flag and turn. A flag[n] value of true indicates that the process n wants to enter the critical section. Entrance to the critical section is granted for process P0 if P1 does not want to enter its critical section or if P1 has given priority to P0 by setting turn to 0.\n" +
                  "\n" +
                  "bool flag[2] = {false, false};\n" +
                  "int turn;\n" +
                  "\n" +
                  "P0:      flag[0] = true;\n" +
                  "P0_gate: turn = 1;\n" +
                  "         while (flag[1] == true && turn == 1)\n" +
                  "         {\n" +
                  "             // busy wait\n" +
                  "         }\n" +
                  "         // critical section\n" +
                  "         ...\n" +
                  "         // end of critical section\n" +
                  "         flag[0] = false;\n" +
                  "\n" +
                  "P1:      flag[1] = true;\n" +
                  "P1_gate: turn = 0;\n" +
                  "         while (flag[0] == true && turn == 0)\n" +
                  "         {\n" +
                  "             // busy wait\n" +
                  "         }\n" +
                  "         // critical section\n" +
                  "         ...\n" +
                  "         // end of critical section\n" +
                  "         flag[1] = false;\n"+
                  "\n"+
                  "The algorithm satisfies the three essential criteria to solve the critical section problem, provided that changes to the variables turn, flag[0], and flag[1] propagate immediately and atomically. The while condition works even with preemption.\n" +
                  "\n" +
                  "The three criteria are mutual exclusion, progress, and bounded waiting.\n" +
                  "\n" +
                  "Since turn can take on one of two values, it can be replaced by a single bit, meaning that the algorithm requires only three bits of memory"));
          algorithms.add(new AlgorithmItem("Producer Consumer Problem", "also known as the *bounded-buffer* problem", "The Producer-Consumer problem is a classic problem this is used for multi-process synchronization i.e. synchronization between more than one processes.\n" +
                  "\n" +
                  "In the producer-consumer problem, there is one Producer that is producing something and there is one Consumer that is consuming the products produced by the Producer. The producers and consumers share the same memory buffer that is of fixed-size.\n" +
                  "\n" +
                  "The job of the Producer is to generate the data, put it into the buffer, and again start generating data. While the job of the Consumer is to consume the data from the buffer.\n" +
                  "\n" +
                  "void producer() {\n" +
                  "    while(T) {\n" +
                  "        produce()\n" +
                  "        wait(E)\n" +
                  "        wait(S)\n" +
                  "        append()\n" +
                  "        signal(S)\n" +
                  "        signal(F)\n" +
                  "    }\n" +
                  "}\n" +
                  "\n" +
                  "void consumer() {\n" +
                  "    while(T) {\n" +
                  "        wait(F)\n" +
                  "        wait(S)\n" +
                  "        take()\n" +
                  "        signal(S)\n" +
                  "        signal(E)\n" +
                  "        use()\n" +
                  "    }\n" +
                  "}\n" +
                  "\n" +
                  "So, this is how we can solve the producer-consumer problem"));
          algorithms.add(new AlgorithmItem("Dekker Problem", "the first provably-correct solution to the critical section problem", "To obtain such a mutual exclusion, bounded waiting, and progress there have been several algorithms implemented, one of which is Dekker’s Algorithm.\n" +
                  "\n" +
                  "Dekker's algorithm is the first known correct solution to the mutual exclusion problem in concurrent programming. The solution is attributed to Dutch mathematician Th. J. Dekker by Edsger W. Dijkstra in an unpublished paper on sequential process descriptions[1] and his manuscript on cooperating sequential processes.\n"+
                  "It avoids the strict alternation of a naïve turn-taking algorithm, and was one of the first mutual exclusion algorithms to be invented.\n"+
                  "\n"+
                  "PROCESS:\n" +
                  "\n" +
                  "do {\n" +
                  "    //entry section\n" +
                  "        critical section\n" +
                  "    //exit section\n" +
                  "        remainder section\n" +
                  "} while (TRUE);\n" +
                  "\n" +
                  "repeat\n" +
                  "\n" +
                  "        flag[i] := true;\n" +
                  "        while flag[j] do\n" +
                  "                if turn = j then\n" +
                  "                begin\n" +
                  "                        flag[i] := false;\n" +
                  "                        while turn = j do no-op;\n" +
                  "                        flag[i] := true;\n" +
                  "                end;\n" +
                  "\n" +
                  "                critical section\n" +
                  "\n" +
                  "        turn := j;\n" +
                  "        flag[i] := false;\n" +
                  "\n" +
                  "                remainder section\n" +
                  "\n" +
                  "until false;\n\n" +
                  "\n" +
                  "."));
          algorithms.add(new AlgorithmItem("Test and Set Lock", "Test and Set Lock (TSL) is a synchronization mechanism.",  " Test_and_Set is a special assembly language instruction that does two operations autonomously. That is, the instruction can not be interrupted in the middle and it is not necessary to disable interrupts. Test_and_Set (TS) is a privileged instruction requiring supervisory mode permissions. (See CPU Execution Mode). The action of Test_and_Set is described with the following pseudo-code:\n" +
                  "\n" +
                  "boolean Test_and_Set( boolean memory[m] )\n" +
                  "{ [\n" +
                  "    if( memory[m] )        // lock denied\n" +
                  "        return True;\n" +
                  "    else {                 // lock granted\n" +
                  "        memory[m] = True;\n" +
                  "        return False;\n" +
                  "    }\n" +
                  "  ]\n" +
                  "}\n\n" +
                  "\n" +
                  "In lock variable mechanism, Sometimes Process reads the old value of lock variable and enters the critical section. Due to this reason, more than one process might get into critical section. However, the code shown in the part one of the following section can be replaced with the code shown in the part two. This doesn't affect the algorithm but, by doing this, we can manage to provide the mutual exclusion to some extent but not completely.\n" +
                  "\n" +
                  "However, the solution provided in the above segment provides mutual exclusion to some extent but it doesn't make sure that the mutual exclusion will always be there. There is a possibility of having more than one process in the critical section."));
     }

     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment
          FragmentAlgorithmChooserBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_algorithm_chooser, container, false);

          RecyclerView recyclerView = binding.recyclerView;
          adapter = new AdapterForAlgorithmChooser(getContext(), algorithms);
          recyclerView.setAdapter(adapter);
          recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

          handleClickEvents();

          view = binding.getRoot();
          return binding.getRoot();
     }

     private void handleClickEvents() {



          adapter.setOnItemClickListener(new AdapterForAlgorithmChooser.OnItemClickListener() {
               @Override
               public void onItemClick(int position) {
                    switch(position) {
                         case 0:
                              //Banker's Algorithm
                              Navigation.findNavController(view).navigate(R.id.action_algorithmChooserFragment_to_algorithmParameterFragment);
                              break;
                         case 1:
                              //Semaphore-lock Algorithm
                              Navigation.findNavController(view).navigate(R.id.action_algorithmChooserFragment_to_paramsForSemaphore);
                              break;
                         case 2:
                              //Peterson Algorithm
                              Navigation.findNavController(view).navigate(R.id.action_algorithmChooserFragment_to_paramsForPeterson);
                              break;
                         case 3:
                              //Producer - Consumer Problem
                              Navigation.findNavController(view).navigate(R.id.action_algorithmChooserFragment_to_paramsForProducerConsumer);
                              break;
                         case 4:
                              //Dekker problem
                              Navigation.findNavController(view).navigate(R.id.action_algorithmChooserFragment_to_paramsForDekkerAlgorithmFragment);
                              break;
                         case 5:
                              //Test and Set Lock
                              Navigation.findNavController(view).navigate(R.id.action_algorithmChooserFragment_to_paramsForTestAndSetLock);
                              break;
                    }
               }

               @Override
               public void onInfoClick(int position) {

                    //FragmentTransaction ft = getParentFragmentManager().beginTransaction();

                    //generate a dialogbox
                    AlgorithmInfoDialog dialogFragment = AlgorithmInfoDialog.newInstance(algorithms.get(position));
                    dialogFragment.setTargetFragment(AlgorithmChooserFragment.this, 0);
                    dialogFragment.show(getParentFragmentManager(), "abc");
               }
          });
     }
}