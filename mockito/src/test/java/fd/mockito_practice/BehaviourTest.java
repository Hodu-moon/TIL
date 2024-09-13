package fd.mockito_practice;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import java.util.*;


@Slf4j
public class BehaviourTest  {

    List mockedList = mock(List.class);

    @Mock LinkedList e;

    @BeforeEach
    public void addMocks(){
        MockitoAnnotations.openMocks(BehaviourTest.class);
    }

    @AfterEach
    public void clear(){
        mockedList.clear();
    }

    @Test
    public void someBehaviour(){

        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");

    }

    @Test
    public void stubbing(){
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn(true);
        when(mockedList.get(1)).thenThrow(new RuntimeException());

//        log.info("mockedList.get(0) : {}", mockedList.get(0));

    }

    @Test
    public void argumentMatchers(){
        when(mockedList.get(anyInt())).thenReturn("element!");

        // matcher
//        when(mockedList.contains(argThat((String name) -> {
//            return true;
//        }))).thenReturn(true);
        mockedList.get(0);

        verify(mockedList).get(anyInt());

    }

    @Test
    public void verifyingExactNumber(){
        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        verify(mockedList, never()).add("new");

        verify(mockedList, atMostOnce()).add("once");

    }

    // 6
    @Test
    public void verificationInOrder(){
//        List singleMock = mock(List.class);
//
//        //using a single mock
//        singleMock.add("was added first");
//        singleMock.add("was added second");
//
//        // inOrder verifier
//        InOrder inOrder = inOrder(singleMock);
//
//        inOrder.verify(singleMock).add("was added second");

        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrder.verify(firstMock).add("was called first");
        inOrder.verify(secondMock).add("was called second");

    }

    @Test
    public void markingNeverHappend(){
        mockedList.add("one");

        verify(mockedList).add("one");

        verify(mockedList, never()).add("two");
    }

    @Test
    public void redundantInvocation(){
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");
        verify(mockedList).add("two");
        verifyNoMoreInteractions(mockedList);
    }

    @Test
    public void makeMocksWithAnnotation(){
        MockitoAnnotations.openMocks(BehaviourTest.class);

        e.add("f");

    }






}