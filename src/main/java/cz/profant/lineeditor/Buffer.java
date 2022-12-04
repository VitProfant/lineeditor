package cz.profant.lineeditor;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Buffer {
    private final List<String> linesList;

    public Buffer() {
        linesList = new ArrayList<>();
    }

    public Buffer(List<String> linesList) {
        this.linesList = linesList;
    }

    public List<String> getLinesList() {
        return linesList;
    }

    public void insertLineAt(int lineNo, String lineContent) throws LineEditorException {
        if (lineNo < 1 || lineNo > linesList.size() + 1) {
            throw new LineEditorException(String.format(Message.LINES_RANGE_TO_INS,
                    linesList.size() > 0 ? Message.FROM_1_TO : "", linesList.size() + 1));
        }
        linesList.add(lineNo - 1, lineContent);
    }

    public void deleteLineAt(int lineNo) throws LineEditorException {
        if (lineNo < 1 || lineNo > linesList.size()) {
            if (linesList.size() > 0) {
                throw new LineEditorException(String.format(Message.LINES_RANGE_TO_DEL,
                        linesList.size() > 1 ? Message.FROM_1_TO : "", linesList.size()));
            } else {
                throw new LineEditorException(Message.NO_DELETABLE_LINE);
            }
        }
        linesList.remove(lineNo - 1);
    }

    public String list() {
        StringBuilder sb = new StringBuilder();
        ListIterator<String> llListIter = linesList.listIterator();
        while (llListIter.hasNext()) {
            sb.append(llListIter.nextIndex() + 1).append(": ").append(llListIter.next()).append("\n");
        }
        return sb.toString();
    }
}
