package mx.edu.j2se.trinidad.tasks.tests;

import mx.edu.j2se.trinidad.tasks.AbstractTaskList;
import mx.edu.j2se.trinidad.tasks.ArrayTaskList;
import mx.edu.j2se.trinidad.tasks.LinkedTaskList;
import mx.edu.j2se.trinidad.tasks.TestTaskListFactory;
import org.junit.Assert;
import org.junit.Test;

import static mx.edu.j2se.trinidad.tasks.TaskListFactory.createTaskList;

public class TestTypes {
    @Test
    public void testLinked(){
        AbstractTaskList list = createTaskList(TestTaskListFactory.types.LINKED);
        Assert.assertTrue(list instanceof LinkedTaskList);
        Assert.assertFalse(list instanceof ArrayTaskList);
    }
    @Test
    public void testArray(){
        AbstractTaskList list = createTaskList(TestTaskListFactory.types.ARRAY);
        Assert.assertFalse(list instanceof LinkedTaskList);
        Assert.assertTrue(list instanceof ArrayTaskList);
    }
}
