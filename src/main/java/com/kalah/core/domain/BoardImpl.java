package com.kalah.core.domain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.kalah.db.GameState;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class BoardImpl implements Board {

    private GameState state;

    void fromState(GameState state) {
        this.state = state;

        Player active = state.getNextTurn();

        Map<Integer, Integer> status = state.getStatus();
        int boardSize = status.size();

        List<Pit> playablePits = asPits(active.pits(boardSize));
        int houseIdx = active.house(boardSize);
        House house = new House(houseIdx, status.get(houseIdx));
        List<Pit> opponentPits = asPits(active.opponent().pits(boardSize));

        putTogetherBoard(playablePits, house, opponentPits);
    }

    static void putTogetherBoard(List<Pit> playablePits, House house, List<Pit> opponentPits) {
        List<Sowable> sowable = ImmutableList.<Sowable>builder()
                .addAll(playablePits)
                .add(house)
                .addAll(opponentPits)
                .build();
        connectAdjecent(sowable);
        connectOpposite(playablePits, opponentPits);
    }

    private static void connectAdjecent(List<Sowable> sowable) {
        Sowable last = sowable.get(sowable.size() - 1);
        for (Sowable s: sowable) {
            last.setNext(s);
            s.setPrevious(last);
            last = s;
        }
    }

    private static void connectOpposite(List<Pit> playerPits, List<Pit> opponentPits) {
        List<Pit> reversed = Lists.reverse(opponentPits);
        for (int i = 0; i < playerPits.size(); i++) {
            Pit playerPit = playerPits.get(i);
            Pit oppPit = reversed.get(i);
            playerPit.setOpposite(oppPit);
            oppPit.setOpposite(playerPit);
        }
    }

    private List<Pit> asPits(List<Integer> idxes) {
        return idxes.stream()
                .map(idx -> new Pit(idx, state.getStatus().get(idx))).collect(Collectors.toList());
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
