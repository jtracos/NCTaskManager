package mx.edu.j2se.trinidad.tasks.tests;


import mx.edu.j2se.trinidad.tasks.ArrayTaskList;
import mx.edu.j2se.trinidad.tasks.LinkedTaskList;
import mx.edu.j2se.trinidad.tasks.Task;

public class TestTaskList {

    public static void main(String[] args) {
        System.out.print("\t\tTesting array List\n");
        System.out.print("-------------------------------------------\n");
        Test(new ArrayTaskList());
        System.out.printf("\t\tTesting linked  List\n");
        System.out.printf("-------------------------------------------\n");
        Test(new LinkedTaskList());
    }


    public static void Test(ArrayTaskList list){

        System.out.print("Adding task to list...\n");
        list.add(new Task("Go out for a burger", 5));
        list.add(new Task("Watch spider-man", 9, 15, 4));
        list.add(new Task("Walking with unknown people", 9, 14, 2));
        list.add(new Task("Another dumb task", 21));
        list.add(new Task("Catch spider-man without his disguise", 3, 24, 2));
        list.add(new Task("Another dumber task", 7, 18, 4));
        System.out.print("Complete tasks list:\n");
        display(list);
        Task tmpTask = new Task("Watch spider-man", 9, 15, 4);
        System.out.println("\n\nRemoving " + tmpTask + "\n");
        list.remove(new Task("Watch spider-man", 9, 15, 4));
        System.out.print("New task list\n");
        display(list);

        activeTasks(list);
        int from, to;
        from = 7;
        to = 20;
        ArrayTaskList inTime = list.incoming(from,to);
        System.out.println("Tasks between " + from + " and " + to);
        display(inTime);
    }


    public static void Test(LinkedTaskList list){

        System.out.print("Adding task to list...\n");
        list.add(new Task("Go out for a burger", 5));
        list.add(new Task("Watch spider-man", 9, 15, 4));
        list.add(new Task("Walking with unknown people", 9, 14, 2));
        list.add(new Task("Another dumb task", 21));
        list.add(new Task("Catch spider-man without his disguise", 3, 24, 2));
        list.add(new Task("Another dumber task", 7, 18, 4));
        System.out.print("Complete tasks list:\n");
        display(list);
        Task tmpTask = new Task("Watch spider-man", 9, 15, 4);
        System.out.println("\n\nRemoving " + tmpTask + "\n");
        list.remove(new Task("Watch spider-man", 9, 15, 4));
        System.out.print("New task list\n");
        display(list);

        activeTasks(list);
        int from, to;
        from = 7;
        to = 20;
        LinkedTaskList inTime = list.incoming(from,to);
        System.out.println("Tasks between " + from + " and " + to);
        display(inTime);
    }

    public static void activeTasks(ArrayTaskList tasks){
        for(int i = 0; i<tasks.size(); i++)
            tasks.getTask(i).setActive(true);
    }
    public static void activeTasks(LinkedTaskList tasks){
        for(int i = 0; i<tasks.size(); i++)
            tasks.getTask(i).setActive(true);
    }

    public static  void display(ArrayTaskList tasks){
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + " " + ": \t" + tasks.getTask(i) + "\n");
        }
        System.out.print("\n");
    }
    public static  void display(LinkedTaskList tasks){
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + " " + ": \t" + tasks.getTask(i) + "\n");
        }
        System.out.print("\n");
    }

}

