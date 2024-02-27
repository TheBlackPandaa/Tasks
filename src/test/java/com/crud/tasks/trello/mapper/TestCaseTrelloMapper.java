package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class TestCaseTrelloMapper {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards(){
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("id1","name1", new ArrayList<>()));
        trelloBoardDtoList.add(new TrelloBoardDto("id2","name2", new ArrayList<>()));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(2, trelloBoards.size());
        assertEquals("id1", trelloBoards.get(0).getId());
        assertEquals("id2", trelloBoards.get(1).getId());
        assertEquals("name1", trelloBoards.get(0).getName());
        assertEquals("name2", trelloBoards.get(1).getName());
    }

    @Test
    public void testMapToBoardDtoList(){
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("id1","name1", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("id2","name2", new ArrayList<>()));
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(2, trelloBoardDtoList.size());
        assertEquals("id1", trelloBoardDtoList.get(0).getId());
        assertEquals("id2", trelloBoardDtoList.get(1).getId());
        assertEquals("name1", trelloBoardDtoList.get(0).getName());
        assertEquals("name2", trelloBoardDtoList.get(1).getName());
    }

    @Test
    public void testMapToList(){
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("id1", "name1", true));
        trelloListDtoList.add(new TrelloListDto("id2", "name2", true));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);
        //Then
        assertEquals(2,trelloLists.size());
        assertEquals("id1", trelloLists.get(0).getId());
        assertEquals("id2", trelloLists.get(1).getId());
        assertEquals("name1", trelloLists.get(0).getName());
        assertEquals("name2", trelloLists.get(1).getName());
    }

    @Test
    public void testMapToListDto(){
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("id1", "name1", true));
        trelloLists.add(new TrelloList("id2", "name2", true));
        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(2,trelloListDtoList.size());
        assertEquals("id1", trelloListDtoList.get(0).getId());
        assertEquals("id2", trelloListDtoList.get(1).getId());
        assertEquals("name1", trelloListDtoList.get(0).getName());
        assertEquals("name2", trelloListDtoList.get(1).getName());
    }

    @Test
    public void testMapToCardDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard("name1", "desc1", "pos1", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("name1", trelloCardDto.getName());
        assertEquals("desc1", trelloCardDto.getDescription());
        assertEquals("pos1", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "desc1", "pos1", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("name1", trelloCard.getName());
        assertEquals("desc1", trelloCard.getDescription());
        assertEquals("pos1", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }
}
