package util;

import base.Base;
import entities.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserIdParserTest {
    @Test
    public void getUserIdFromDescription_shouldReturnValidId() {
        Entity entity = Base.createDefaultEntity();
        String description = entity.getDescription();
        String id = UserIdParser.deriveUserIdFromEventDescription(description);
        Assertions.assertEquals("8407", id);
    }
    @Test
    public void deriveUserIdFromEventDescription_Should_Throw_Exception_On_NotFound_Group() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> UserIdParser.deriveUserIdFromEventDescription("fsfvrsververve"));
    }

    @Test
    public void deriveUserIdFromEventDescription_Should_Throw_Exception_On_Non_NumericId() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> UserIdParser.deriveUserIdFromEventDescription("The user with id 'abc' viewed the course with id '130'."));
    }
}
