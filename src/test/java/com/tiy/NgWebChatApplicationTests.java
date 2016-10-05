package com.tiy;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NgWebChatApplicationTests {
	@Autowired
	UserRepository users;

	@Autowired
	MessageRepository messages;

	@Test
	public void AddMessageTest() {
		User testUser = new User("test-name", "test-password");
		testUser = users.save(testUser);
		Message testMessage = new Message("test-message",testUser);
		messages.save(testMessage);

		Message dbMessage = messages.findOne(testMessage.getId());
		User dbUser = dbMessage.user;

		assertEquals(testUser.getId(), dbUser.getId());
		assertEquals(testMessage.getId(), dbMessage.getId());

		messages.delete(testMessage.getId());
		users.delete(testUser.getId());
	}

}
