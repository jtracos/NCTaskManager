package mx.edu.j2se.trinidad.tasks.tests;

import mx.edu.j2se.trinidad.tasks.AbstractTaskList;
import mx.edu.j2se.trinidad.tasks.ArrayTaskList;
import mx.edu.j2se.trinidad.tasks.LinkedTaskList;
import mx.edu.j2se.trinidad.tasks.ListTypes;
import org.junit.Assert;
import org.junit.Test;

import static mx.edu.j2se.trinidad.tasks.TaskListFactory.createTaskList;

public class TestTaskListFactory {
    @Test
    public void testLinked(){
        AbstractTaskList list = createTaskList(ListTypes.types.LINKED);
        Assert.assertTrue(list instanceof LinkedTaskList);
        Assert.assertFalse(list instanceof ArrayTaskList);
    }
    @Test
    public void testArray(){
        AbstractTaskList list = createTaskList(ListTypes.types.ARRAY);
        Assert.assertFalse(list instanceof LinkedTaskList);
        Assert.assertTrue(list instanceof ArrayTaskList);
    }
}
