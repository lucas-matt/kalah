package com.kalah.core.domain;

import com.kalah.db.GameState;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BoardImpl implements Board {

    private GameState state;

    void fromState(GameState state) {
        Map<Integer, Integer> status = state.getStatus();
        int boardSize = status.size();
        int sideSize = boardSize / 2;



        List<Integer> playerOneSpace = IntStream.rangeClosed(1, sideSize).boxed().collect(Collectors.toList());
        List<Integer> playerTwoSpace = IntStream.rangeClosed(sideSize+1, boardSize).boxed().collect(Collectors.toList());
        Map<Integer, Integer> playerOneStatus = playerOneSpace.stream().collect(Collectors.toMap(
                i -> i,
                i -> status.get(i)
        ));
        Map<Integer, Integer> playerTwoStatus = playerOneSpace.stream().collect(Collectors.toMap(
                i -> i,
                i -> status.get(i)
        ));


    }

    @Override
    public GameState toState() {
        return null;
    }

    @Override
    public Player getActivePlayer() {
        return state.getNextTurn();
    }

    @Override
    public boolean isPit(int pit) {
        return false;
    }

    @Override
    public Pit getPit(int pitId) {
        return null;
    }

    @Override
    public Player getPitOwner(int pitId) {
        return null;
    }

}
