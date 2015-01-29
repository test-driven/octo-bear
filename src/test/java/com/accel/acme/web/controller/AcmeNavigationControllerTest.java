package com.accel.acme.web.controller;

import com.accel.acme.web.common.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/acme-dispatcher-servlet.xml"})
public class AcmeNavigationControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void ACMENavigationToHome_ShouldAddPublishedAdvtEntriesToModelAndRenderHomeView() throws Exception {

        mockMvc.perform(get(Constants.HOME_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(Constants.HOME))
                .andExpect(forwardedUrl("/WEB-INF/pages/home.jsp"))
                .andExpect(model().attributeExists("publishedAdvtList"));
    }

    @Test
    public void ACMENavigationToAdvertisements_ShouldAddAdvertisementEntriesToModelAndRenderAdvertisementsListView()
            throws Exception {

        mockMvc.perform(get(Constants.ADVERTISEMENTS_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(Constants.ADVERTISEMENTS))
                .andExpect(forwardedUrl("/WEB-INF/pages/advertisements.jsp"))
                .andExpect(model().attributeExists("advertisementList"));
    }

    @Test
    public void ACMENavigationToContatcus_ShouldRenderContactUsView() throws Exception {

        mockMvc.perform(get(Constants.CONTACTUS_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(Constants.CONTACTUS))
                .andExpect(forwardedUrl("/WEB-INF/pages/contactus.jsp"));
    }

    @Test
    public void ACMENavigationToAboutus_ShouldRenderAboutUsView() throws Exception {

        mockMvc.perform(get(Constants.ABOUTUS_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(Constants.ABOUTUS))
                .andExpect(forwardedUrl("/WEB-INF/pages/aboutus.jsp"));
    }

}
