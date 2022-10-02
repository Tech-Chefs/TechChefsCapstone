package learn.techchefs.domain;

import java.util.ArrayList;
import java.util.List;

public class Result <T>{
    private ResultType resultType = ResultType.SUCCESS;
    private List <String> messages = new ArrayList<>();
    private T payload;

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public void addMessage (String message, ResultType resultType) {
        messages.add(message);
        this.resultType = resultType;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public List<String> getMessages() {
        return messages;
    }
}
