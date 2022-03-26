package mx.edu.j2se.trinidad.tasks;

public class TaskListFactory {
    /**
     * This method creates a TaskList
     * @param type-ListTypes.type variable indicating the type of implementation
     *            of TaskList.
     * @return - An AbstractTaskList object
     */
    public static AbstractTaskList createTaskList( ListTypes.types type){
        AbstractTaskList l;
        if(type.equals(ListTypes.types.LINKED))
            l = new LinkedTaskList();
        else
            l = new ArrayTaskList();
        System.out.println(type);
        return l;
    }

    @Override
    public boolean equals(Object obj) {
        return this.equals(obj);
    }
}
