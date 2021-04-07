package br.com.iti.password.it;

import br.com.iti.password.config.ErrorEntity;
import br.com.iti.password.entrypoint.ChangePasswordRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PasswordChangerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String TITLE_MESSAGE = "Password validation problem";

    @Test
    public void testValidPassword() throws Exception {
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .password("aV4l1DpAsw0rd@")
                .build();

        mockMvc.perform(put("/password")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
    }

    @Test
    public void testInvalidPasswordSpacesBegin() throws Exception {
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .password(" aV4l1DpAsw0rd@")
                .build();

        MvcResult mvcResult = mockMvc.perform(put("/password")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andReturn();

        ErrorEntity errorEntity = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorEntity.class);

        assert errorEntity != null;
        assert errorEntity.getStatus().equals(HttpStatus.UNPROCESSABLE_ENTITY.value());
        assert errorEntity.getTitle().equals(TITLE_MESSAGE);
        assert errorEntity.getDetail().equals("Password must not have spaces between");
    }

    @Test
    public void testInvalidPasswordSpacesBetween() throws Exception {
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .password("aV4l1DpA sw0rd@")
                .build();

        MvcResult mvcResult = mockMvc.perform(put("/password")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andReturn();

        ErrorEntity errorEntity = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorEntity.class);

        assert errorEntity != null;
        assert errorEntity.getStatus().equals(HttpStatus.UNPROCESSABLE_ENTITY.value());
        assert errorEntity.getTitle().equals(TITLE_MESSAGE);
        assert errorEntity.getDetail().equals("Password must not have spaces between");
    }

    @Test
    public void testInvalidPasswordSpacesEnd() throws Exception {
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .password("aV4l1DpAsw0rd@ ")
                .build();

        MvcResult mvcResult = mockMvc.perform(put("/password")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andReturn();

        ErrorEntity errorEntity = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorEntity.class);

        assert errorEntity != null;
        assert errorEntity.getStatus().equals(HttpStatus.UNPROCESSABLE_ENTITY.value());
        assert errorEntity.getTitle().equals(TITLE_MESSAGE);
        assert errorEntity.getDetail().equals("Password must not have spaces between");
    }

    @Test
    public void testInvalidPasswordDigit() throws Exception {
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .password("aVlDpAswrd@")
                .build();

        MvcResult mvcResult = mockMvc.perform(put("/password")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andReturn();

        ErrorEntity errorEntity = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorEntity.class);

        assert errorEntity != null;
        assert errorEntity.getStatus().equals(HttpStatus.UNPROCESSABLE_ENTITY.value());
        assert errorEntity.getTitle().equals(TITLE_MESSAGE);
        assert errorEntity.getDetail().equals("Password must have at least one number character");
    }

    @Test
    public void testInvalidPasswordCapitalized() throws Exception {
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .password("avl4pxswrd@")
                .build();

        MvcResult mvcResult = mockMvc.perform(put("/password")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andReturn();

        ErrorEntity errorEntity = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorEntity.class);

        assert errorEntity != null;
        assert errorEntity.getStatus().equals(HttpStatus.UNPROCESSABLE_ENTITY.value());
        assert errorEntity.getTitle().equals(TITLE_MESSAGE);
        assert errorEntity.getDetail().equals("Password must have at least one capitalized character");
    }

    @Test
    public void testInvalidPasswordUncapitalized() throws Exception {
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .password("AVL4PXSWRD@")
                .build();

        MvcResult mvcResult = mockMvc.perform(put("/password")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andReturn();

        ErrorEntity errorEntity = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorEntity.class);

        assert errorEntity != null;
        assert errorEntity.getStatus().equals(HttpStatus.UNPROCESSABLE_ENTITY.value());
        assert errorEntity.getTitle().equals(TITLE_MESSAGE);
        assert errorEntity.getDetail().equals("Password must have at least one uncapitalized character");
    }

    @Test
    public void testInvalidPasswordRepeatedCharacters() throws Exception {
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .password("aV4l1DpAsw0rda@")
                .build();

        MvcResult mvcResult = mockMvc.perform(put("/password")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andReturn();

        ErrorEntity errorEntity = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorEntity.class);

        assert errorEntity != null;
        assert errorEntity.getStatus().equals(HttpStatus.UNPROCESSABLE_ENTITY.value());
        assert errorEntity.getTitle().equals(TITLE_MESSAGE);
        assert errorEntity.getDetail().equals("Password with duplicated characters detected");
    }

    @Test
    public void testInvalidPasswordSpecialCharacters() throws Exception {
        ChangePasswordRequest request = ChangePasswordRequest.builder()
                .password("aV4l1DpAsw0rd")
                .build();

        MvcResult mvcResult = mockMvc.perform(put("/password")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .andReturn();

        ErrorEntity errorEntity = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorEntity.class);

        assert errorEntity != null;
        assert errorEntity.getStatus().equals(HttpStatus.UNPROCESSABLE_ENTITY.value());
        assert errorEntity.getTitle().equals(TITLE_MESSAGE);
        assert errorEntity.getDetail().equals("Password must have at least one special character");
    }
}
