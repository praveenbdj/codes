package helper;

import codes.ReverseLinkedList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {

    public static String serialize(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException("Error in serialization", exception);
        }
    }

    public static void printLinkedList(ReverseLinkedList.ListNode head) {
        ReverseLinkedList.ListNode current = head;
        while (current != null) {
            System.out.println(current.getVal());
            current = current.getNext();
        }
    }

}
