package cz.profant.lineeditor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BufferTest {
    private Buffer buffer;

    @BeforeEach
    void setBuffer() {
        buffer = new Buffer();
    }

    @Test
    void insertLineAtMinusOne() {
        Exception exception = assertThrows(LineEditorException.class, () -> buffer.insertLineAt(-1, ""));
        assertEquals(String.format(Message.LINES_RANGE_TO_INS, "", 1), exception.getMessage());
    }

    @Test
    void insertLineAtTooBigIndex() {
        assertDoesNotThrow(() -> buffer.insertLineAt(1, ""));
        Exception exception = assertThrows(LineEditorException.class, () -> buffer.insertLineAt(3, ""));
        assertEquals(String.format(Message.LINES_RANGE_TO_INS, Message.FROM_1_TO, 2), exception.getMessage());
    }

    @Test
    void deleteLineAtZero() {
        assertDoesNotThrow(() -> buffer.insertLineAt(1, ""));
        Exception exception = assertThrows(LineEditorException.class, () -> buffer.deleteLineAt(0));
        assertEquals(String.format(Message.LINES_RANGE_TO_DEL, "", 1), exception.getMessage());
    }

    @Test
    void deleteLineAtTooBigIndex() {
        assertDoesNotThrow(() -> {
            buffer.insertLineAt(1, "");
            buffer.insertLineAt(2, "");
        });
        Exception exception = assertThrows(LineEditorException.class, () -> buffer.deleteLineAt(3));
        assertEquals(String.format(Message.LINES_RANGE_TO_DEL, Message.FROM_1_TO, 2), exception.getMessage());
    }

    @Test
    void deleteLineFromEmptyBuffer() {
        Exception exception = assertThrows(LineEditorException.class, () -> buffer.deleteLineAt(1));
        assertEquals(Message.NO_DELETABLE_LINE, exception.getMessage());
    }

    @Test
    void list() {
        String line_1 = "This is the first entered line.";
        String line_2 = "This is the second entered line.";
        String line_3 = "This is the third entered line.";
        assertDoesNotThrow(() -> {
            buffer.insertLineAt(1, line_1);
            buffer.insertLineAt(1, line_2);
            buffer.insertLineAt(3, line_3);
            buffer.deleteLineAt(2);
        });
        String expectedListing = "1: " + line_2 + "\n" +
                "2: " + line_3 + "\n";
        assertEquals(expectedListing, buffer.list());
    }
}
