package pl.kathelan.mdbackend.role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import pl.kathelan.mdbackend.BaseIntegrationTests;
import pl.kathelan.mdbackend.api.dtos.RoleRequestDto;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class RoleControllerTest extends BaseIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void getRoles() throws Exception {
        mockMvc.perform(get("/roles"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)));
    }

    @Test
    @Transactional
    public void getRole() throws Exception {
        mockMvc.perform(get("/roles/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("DEV")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", equalTo("developer")));
    }

    @Test
    @Transactional
    public void createRole() throws Exception {
        RoleRequestDto roleRequestDto = new RoleRequestDto();
        roleRequestDto.setName("new");
        roleRequestDto.setDescription("desc");

        mockMvc.perform(post("/roles")
                        .content(asJsonString(roleRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    public void patchRole() throws Exception {
        RoleRequestDto roleRequestDto = new RoleRequestDto();
        roleRequestDto.setName("new");
        roleRequestDto.setDescription("desc");

        mockMvc.perform(patch("/roles/1")
                        .content(asJsonString(roleRequestDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("new")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", equalTo("desc")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
