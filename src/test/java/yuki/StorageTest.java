package yuki;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void dummyTest(){
        Storage storage = new Storage("1");
        storage.save(new TaskList<>());
    }
}
