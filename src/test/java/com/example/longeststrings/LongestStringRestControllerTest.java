package com.example.longeststrings;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LongestStringRestController.class)
public class LongestStringRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testGetLongStrings() throws Exception {

		mvc.perform(get("/api/longeststrings").contentType(MediaType.APPLICATION_JSON)
				.param("str",
				"When I consider how my light is spent Ere half my days, in this dark world and wide, And that one Talent which is death to hide Lodged with me useless, though my Soul more bent To serve therewith my Maker, and present My true account, lest he returning chide;  \"Doth God exact day-labour, light denied?\"  I fondly ask. But patience, to prevent  That murmur, soon replies, \"God doth not need Either man's work or his own gifts; who best  Bear his mild yoke, they serve him best. His state  Is Kingly. Thousands at his bidding speed   And post o'er Land and Ocean without rest:  They also serve who only stand and wait.")
				.param("k", "5"))
		.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(org.hamcrest.Matchers.containsString("returning")));

	}
}
