package com.example.TO9;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.JsonObject;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class To9ApplicationTests {

	private JsonObject createEmployeeRequest(String name, String surname, String date) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", name);
		jsonObject.addProperty("surname", surname);
		jsonObject.addProperty("date", date);
		return jsonObject;
	}

	@Autowired
	private MockMvc mockMvc;
	@Test
	void getEmployeesShouldReturnOk() throws Exception {
		mockMvc
				.perform(get("/getEmployees"))
				.andExpect(status().isOk());
	}

	@Test
	void postEmployeeShouldReturnOk() throws Exception {
		mockMvc
				.perform(post("/postEmployee")
						.contentType(MediaType.APPLICATION_JSON)
						.content(createEmployeeRequest("Marek", "Kasztaniec", "13.03.2020").toString()))
				.andExpect(status().isOk());
	}

	@Test
	void postEmployeeShouldReturnBadRquest() throws Exception {
		mockMvc
				.perform(post("/postEmployee")
						.contentType(MediaType.APPLICATION_JSON)
						.content(""))
				.andExpect(status().isBadRequest());
	}

	@Test
	void getEmployeesShouldReturnEverythingFromEmployeeTable() throws Exception {
		mockMvc
				.perform(post("/postEmployee")
						.contentType(MediaType.APPLICATION_JSON)
						.content(createEmployeeRequest("Marcel", "Klimek", "17.11.2019").toString()))
				.andExpect(status().isOk());
		mockMvc
				.perform(post("/postEmployee")
						.contentType(MediaType.APPLICATION_JSON)
						.content(createEmployeeRequest("Krzysiek", "Gorski", "03.02.2021").toString()))
				.andExpect(status().isOk());

		List<Employee> table = EmployeeTable.getInstance().get_employees();
		String response = "";
		for(Employee employee: table){
			response += employee.getName();
		}

		mockMvc
				.perform(get("/getEmployees"))
				.andExpect(status().isOk()).andExpect(content().string(response));
	}

}
