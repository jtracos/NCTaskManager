package mx.edu.j2se.trinidad.tasks;

public class TestTaskListFactory {

    public static enum types {
        LINKED(new LinkedTaskList()),
        ARRAY(new ArrayTaskList());

        private final AbstractTaskList type;

        types(AbstractTaskList type) {
            this.type = type;
        }
        public AbstractTaskList getTaskList(){
            return type;
        }
    }
}
