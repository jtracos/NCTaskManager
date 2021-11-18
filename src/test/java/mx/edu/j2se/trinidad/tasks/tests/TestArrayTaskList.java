package mx.edu.j2se.trinidad.tasks.tests;


import mx.edu.j2se.trinidad.tasks.ArrayTaskList;
import mx.edu.j2se.trinidad.tasks.Task;

public class TestArrayTaskList {

    public static void main(String[] args) {
        ArrayTaskList array = new ArrayTaskList();

        array.add(new Task("Go out for a burger", 5));
        array.add(new Task("Watch spider-man", 9, 15, 4));
        array.add(new Task("Walking with unknown people", 9, 14, 2));
        array.add(new Task("Another dumb task", 21));
        array.add(new Task("Catch spider-man without his disguise", 3, 24, 2));
        array.add(new Task("Another dumber task", 7, 18, 4));
        System.out.println("Complete tasks list");
        display(array);
        array.remove(new Task("Watch spider-man", 9, 15, 4));
        System.out.print("New task list\n");
        display(array);

        activeTasks(array);
        int from, to;
        from = 7;
        to = 20;
        ArrayTaskList inTime = array.incoming(from,to);
        System.out.println("Tasks between " + from + " and " + to);
        display(inTime);
    }

    public static void activeTasks(ArrayTaskList tasks){
        for(int i = 0; i<tasks.size(); i++)
            tasks.getTask(i).setActive(true);
    }

    public static  void display(ArrayTaskList tasks){
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + " " + ": \t" + tasks.getTask(i) + "\n");
        }
        System.out.print("\n");
    }

}

