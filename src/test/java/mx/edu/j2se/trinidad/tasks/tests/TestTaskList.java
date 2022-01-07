package mx.edu.j2se.trinidad.tasks.tests;


import mx.edu.j2se.trinidad.tasks.AbstractTaskList;
import mx.edu.j2se.trinidad.tasks.LinkedTaskList;
import mx.edu.j2se.trinidad.tasks.Task;

import java.util.stream.Stream;

public class TestTaskList {

    public static void main(String[] args) {
        System.out.print("\t\tTesting array List\n");
        AbstractTaskList lista = new LinkedTaskList();
        load(lista,
                new Task("Watch spider-man", 9, 15, 4),
                new Task("Walking with unknown people", 9, 14, 2),
                new Task("Another dumber task", 7,18,4),
                new Task("Catch spider-man without his disguise", 3, 24, 2),
                new Task("Another dumber task", 7,18,4),
                new Task("Another dumber task", 13));

        activeTasks(lista);
        //Stream implementation
        Stream<Task> st = lista.getStream();
        st.distinct().forEach(System.out::println);

        // INCOMING METHOD
        System.out.println("Incoming tasks");
        Stream<Task> inc = lista.incoming(7,13);
        inc.forEach(System.out::println);
        /*try {
            lista2 = (AbstractTaskList) lista.clone();
            for(Task ts: lista2){
                System.out.println("ts = " + ts);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        /*show(lista);
        activeTasks(lista);
        Task task = new Task("Watch spider-man", 9, 15, 4);
        System.out.println("\n\nRemoving " + task + "\n");
        lista.remove(task);
        show(lista);
        /*int from, to;
        from = 7;
        to = 20;
        AbstractTaskList inTime = lista.incoming(from,to);
        System.out.println("Tasks between " + from + " and " + to);
        display(inTime);

         */
    }


    public static void load(AbstractTaskList list, Task ... tasks){
        for(Task task: tasks){
            list.add(task);
        }
    }

    public static void show(AbstractTaskList list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + " " + ": \t" + list.getTask(i) + "\n");
        }
    }


    public static void activeTasks(AbstractTaskList tasks){
        for(int i = 0; i<tasks.size(); i++)
            tasks.getTask(i).setActive(true);
    }

    public static  void display(AbstractTaskList tasks){
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + " " + ": \t" + tasks.getTask(i) + "\n");
        }
        System.out.print("\n");
    }

}

