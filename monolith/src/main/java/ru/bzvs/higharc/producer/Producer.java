package ru.bzvs.higharc.producer;

public interface Producer {

    void produceForInvalidate(Long authorId);

    void produceForForming(Long authorId);
}
