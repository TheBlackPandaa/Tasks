package com.crud.tasks.trello.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {
    @Mock
    private TrelloClient trelloClient;
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private SimpleEmailService emailService;

    @Test
    void shouldFetchTrelloBoards () {
        //Given
        List<TrelloListDto> trelloListDtoList = List.of(new TrelloListDto(
                "id",
                "name",
                false));
        List<TrelloBoardDto> trelloBoardDtoList = List.of(new TrelloBoardDto(
                "id",
                "name",
                trelloListDtoList));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(trelloBoardDtoList, result);
    }

    @Test
    void shouldCreateTrelloCard (){
        TrelloCardDto cardDto = new TrelloCardDto("name", "desc", "pos", "listId");
        CreatedTrelloCardDto created = new CreatedTrelloCardDto("id", "name", "shortUrl");
        when(trelloClient.createNewCard(cardDto)).thenReturn(created);
        when(adminConfig.getAdminMail()).thenReturn("${admin.mail}");
        //When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(cardDto);
        //Then
        assertEquals(created, result);
    }
}
