package mx.edu.j2se.trinidad.tasks;

public class TaskListFactory {
    /**
     * This method creates a TaskList
     * @param type-ListTypes.type variable indicating the type of implementation
     *            of TaskList.
     * @return - An AbstractTaskList object
     */
    public static AbstractTaskList createTaskList( TestTaskListFactory.types type){
        System.out.println(type);
        return type.getTaskList();
    }
}
