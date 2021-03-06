package com.betrayal.betrayalchar;

public enum Stats {
    PLAYER1MIGHT(4, 0, 2, 3, 3, 4, 5, 6, 6, 7),
    PLAYER1SPEED(3, 0, 3, 4, 4, 4, 5, 6, 7, 8),
    PLAYER1KNOWLEDGE(3, 0, 1, 3, 3, 5, 5, 6, 6, 7),
    PLAYER1SANITY(4, 0, 3, 3, 3, 4, 5, 6, 7, 8),
    PLAYER2MIGHT(3, 0, 2, 3, 3, 4, 5, 6, 6, 7),
    PLAYER2SPEED(5, 0, 4, 4, 4, 5, 6, 7, 7, 8),
    PLAYER2KNOWLEDGE(3, 0, 2, 3, 3, 4, 5, 5, 5, 7),
    PLAYER2SANITY(3, 0, 1, 2, 3, 4, 5, 5, 5, 7),
    PLAYER3MIGHT(3, 0, 1, 2, 3, 4, 4, 5, 5, 7),
    PLAYER3SPEED(3, 0, 2, 3, 3, 4, 5, 6, 7, 7),
    PLAYER3KNOWLEDGE(4, 0, 1, 3, 3, 4, 5, 6, 6, 8),
    PLAYER3SANITY(5, 0, 3, 4, 5, 5, 6, 7, 7, 8),
    PLAYER4MIGHT(3, 0, 3, 3, 3, 4, 5, 6, 7, 8),
    PLAYER4SPEED(3, 0, 3, 3, 4, 5, 6, 6, 7, 8),
    PLAYER4KNOWLEDGE(5, 0, 2, 3, 3, 4, 5, 6, 7, 8),
    PLAYER4SANITY(3, 0, 3, 3, 3, 4, 5, 6, 6, 6),
    PLAYER5MIGHT(3, 0, 3, 4, 4, 4, 4, 5, 6, 8),
    PLAYER5SPEED(4, 0, 2, 3, 4, 4, 4, 5, 6, 8),
    PLAYER5KNOWLEDGE(3, 0, 2, 3, 3, 4, 4, 5, 6, 8),
    PLAYER5SANITY(5, 0, 1, 1, 2, 4, 4, 4, 5, 6),
    PLAYER6MIGHT(4, 0, 2, 3, 3, 4, 5, 5, 5, 6),
    PLAYER6SPEED(3, 0, 2, 3, 3, 5, 5, 6, 6, 7),
    PLAYER6KNOWLEDGE(4, 0, 1, 3, 4, 4, 4, 5, 6, 6),
    PLAYER6SANITY(3, 0, 4, 4, 4, 5, 6, 7, 8, 8),
    PLAYER7MIGHT(4, 0, 2, 3, 3, 3, 4, 5, 6, 7),
    PLAYER7SPEED(4, 0, 3, 4, 5, 6, 6, 6, 7, 7),
    PLAYER7KNOWLEDGE(4, 0, 2, 3, 4, 4, 5, 6, 6, 6),
    PLAYER7SANITY(3, 0, 1, 2, 3, 4, 5, 5, 6, 7),
    PLAYER8MIGHT(3, 0, 4, 5, 5, 6, 6, 7, 8, 8),
    PLAYER8SPEED(5, 0, 2, 2, 2, 3, 4, 5, 5, 6),
    PLAYER8KNOWLEDGE(3, 0, 2, 2, 3, 3, 5, 5, 6, 6),
    PLAYER8SANITY(3, 0, 2, 2, 3, 4, 5, 5, 6, 7),
    PLAYER9MIGHT(3, 0, 2, 3, 3, 4, 5, 5, 6, 8),
    PLAYER9SPEED(4, 0, 3, 3, 3, 4, 6, 6, 7, 7),
    PLAYER9KNOWLEDGE(3, 0, 3, 4, 4, 5, 6, 7, 7, 8),
    PLAYER9SANITY(4, 0, 3, 4, 4, 4, 5, 6, 6, 7),
    PLAYER10MIGHT(3, 0, 1, 2, 3, 4, 5, 5, 6, 6),
    PLAYER10SPEED(4, 0, 2, 2, 4, 4, 5, 5, 6, 6),
    PLAYER10KNOWLEDGE(5, 0, 4, 5, 5, 5, 5, 6, 7, 8),
    PLAYER10SANITY(3, 0, 1, 3, 3, 4, 5, 5, 6, 7),
    PLAYER11MIGHT(3, 0, 2, 2, 2, 4, 4, 5, 6, 6),
    PLAYER11SPEED(4, 0, 3, 4, 4, 4, 4, 6, 7, 8),
    PLAYER11KNOWLEDGE(4, 0, 4, 5, 5, 5, 5, 6, 6, 7),
    PLAYER11SANITY(3, 0, 4, 4, 4, 5, 6, 7, 8, 8),
    PLAYER12MIGHT(4, 0, 2, 2, 3, 3, 4, 4, 6, 7),
    PLAYER12SPEED(4, 0, 4, 4, 4, 4, 5, 6, 8, 8),
    PLAYER12KNOWLEDGE(3, 0, 1, 2, 3, 4, 4, 5, 5, 5),
    PLAYER12SANITY(3, 0, 3, 4, 5, 5, 6, 6, 7, 8);

    public int[] stat;
    public int start;

    private Stats(int start, int... stat) {
        this.stat = stat;
        this.start = start;
    }
}
