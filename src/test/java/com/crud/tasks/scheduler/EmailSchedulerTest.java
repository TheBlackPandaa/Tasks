package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailSchedulerTest {
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private AdminConfig adminConfig;
    @InjectMocks
    private EmailScheduler emailScheduler;
    @Test
    void shouldSendEmail() {
        //Given
        long size = 5;
        String email = "mail@mail.com";
        when(taskRepository.count()).thenReturn(size);
        when(adminConfig.getAdminMail()).thenReturn(email);
        //When
        emailScheduler.sendInformationEmail();
        //Then
       verify(simpleEmailService).send(
               new Mail(
                       email,
                       null,
                       "Tasks: Once a day email",
                       "Currently in database you got: 5 tasks"
               )
       );
    }
}