package com.kalah.core.domain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.kalah.db.GameState;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class BoardImpl implements Board {

    private GameState state;

    private List<Pit> playable;

    private House house;

    private int boardSize;

    private Map<Integer, Sowable> index;

    void fromState(GameState state) {
        this.state = state;

        Player active = state.getNextTurn();

        Map<Integer, Integer> status = state.getStatus();
        boardSize = status.size();

        playable = pitsOf(active);
        house = houseOf(active);

        List<Pit> opponentPits = pitsOf(active.opponent());
        House opponentHouse = houseOf(active.opponent());

        putTogetherBoard(playable, house, opponentPits);
        index = indexSowable(playable, house, opponentPits, opponentHouse);
    }

    private static Map<Integer, Sowable> indexSowable(List<Pit> playable, House house, List<Pit> opponentPits, House opponentHouse) {
        List<Sowable> components = ImmutableList.<Sowable>builder()
                .addAll(playable)
                .add(house)
                .addAll(opponentPits)
                .add(opponentHouse)
                .build();
        return components.stream()
                .collect(Collectors.toMap(
                        Sowable::getIdx,
                        (s) -> s
                ));
    }

    private House houseOf(Player player) {
        int houseIdx = player.house(boardSize);
        return new House(houseIdx, state.getStatus().get(houseIdx));
    }

    private static void putTogetherBoard(List<Pit> playablePits, House house, List<Pit> opponentPits) {
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

    private List<Pit> pitsOf(Player player) {
        List<Integer> idxes = player.pits(boardSize);
        return idxes.stream()
                .map(idx -> new Pit(idx, state.getStatus().get(idx))).collect(Collectors.toList());
    }

    @Override
    public GameState toState() {
        Map<Integer, Integer> status = index.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                (e) -> e.getKey(),
                                (e) -> e.getValue().count()
                        )
                );
        state.setStatus(status);
        return state;
    }

    @Override
    public Player getActivePlayer() {
        return state.getNextTurn();
    }

    @Override
    public boolean isPit(int pitIdx) {
        return getPit(pitIdx).isPit();
    }

    @Override
    public Pit getPit(int pitId) {
        return getPit(pitId);
    }

    @Override
    public Player getPitOwner(int pitId) {
        return getPit(pitId).getOwner();
    }


}
