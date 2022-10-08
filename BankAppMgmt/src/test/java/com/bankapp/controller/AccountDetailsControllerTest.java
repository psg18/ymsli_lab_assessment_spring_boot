package com.bankapp.controller;

import com.bankapp.dto.AccountDto;
import com.bankapp.model.persitance.Account;
import com.bankapp.model.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AccountDetailsControllerTest {

    @InjectMocks
    private AccountDetailsController sut;

    @Mock
    private AccountService accountService;

    private MockMvc mockMvc;

    private static final String ROOT_PATH = "/accountops";

    static class StandaloneMvcTestViewResolver extends InternalResourceViewResolver {

        public StandaloneMvcTestViewResolver() {
            super();
        }

        @Override
        protected AbstractUrlBasedView buildView(final String viewName) throws Exception {
            final InternalResourceView view = (InternalResourceView) super.buildView(viewName);
            // prevent checking for circular view paths
            view.setPreventDispatchLoop(false);
            return view;
        }
    }

    @BeforeEach
    public void setUpBefore() {
        mockMvc = MockMvcBuilders.standaloneSetup(sut)
                .setViewResolvers(new StandaloneMvcTestViewResolver())
                .build();
    }

    @Test
    void testTransferPost() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setName("PIOLO");

        Account account = new Account();
        account.setName("PIOLO");

        when(accountService.addAccount(any(Account.class))).thenReturn(account);

        String path = ROOT_PATH + "/accounts";

        mockMvc.perform(post(path)
                        .flashAttr("accountDto", accountDto))
                .andExpect(redirectedUrl("success"))
                .andExpect(status().isFound())
                .andDo(print());
    }

    @Test
    void testTransferGet() throws Exception {
        String path = ROOT_PATH + "/success";

        mockMvc.perform(get(path))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testAccountsGet() throws Exception {
        String path = ROOT_PATH + "/accounts";

        mockMvc.perform(get(path))
                .andDo(print())
                .andExpect(forwardedUrl("addaccounts"))
                .andExpect(status().isOk());
    }
}
