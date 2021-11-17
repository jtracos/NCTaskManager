package mx.edu.j2se.trinidad.tasks;

public class Main {

	public static void main(String[] args) {
		ArrayTaskList list = new ArrayTaskList();
		list.add(new Task("Go fighting with bad people", 5, 2, 13));
		list.add(new Task("Go being human", 11,15,3));
		list.add(new Task("Go being stupid", 11,3,19));
		System.out.println(list.size());
		System.out.println(list.getTask(1));
	}

}
