package com.kalah.core.domain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.kalah.db.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
class PlayerBoardView implements Board {

    private GameState state;

    private Map<Integer, Pit> playable;

    private int boardSize;

    private Map<Integer, Pit> index;

    private List<Event> events = new ArrayList<>();

    @Override
    public Player getActivePlayer() {
        return state.getNextTurn();
    }

    @Override
    public boolean isPit(int pitIdx) {
        return playable.containsKey(pitIdx);
    }

    @Override
    public Pit getPit(int pitId) {
        return index    .get(pitId);
    }

    @Override
    public void logEvent(Event evt) {
        this.events.add(evt);
    }

    @Override
    public List<Pit> getPitsFor(Player player) {
        List<Integer> idxes = player.pits(boardSize);
        return idxes.stream()
                .map((idx) -> index.get(idx))
                .collect(Collectors.toList());
    }

    @Override
    public Pit getHouse(Player player) {
        int idx = player.house(boardSize);
        return index.get(idx);
    }

    @Override
    public Event getLastEvent() {
        return events.get(events.size()-1);
    }

    @Override
    public void gameOver() {

    }

    @Override
    public void setNextTurn(Player player) {
        this.state.setNextTurn(player);
    }

    void fromState(GameState state) {
        this.state = state;

        Player active = state.getNextTurn();

        Map<Integer, Integer> status = state.getStatus();
        boardSize = status.size();

        List<Pit> playerPits = pitsOf(active);
        playable = index(playerPits);
        Pit house = houseOf(active);

        List<Pit> opponentPits = pitsOf(active.opponent());
        Pit opponentHouse = houseOf(active.opponent());

        putTogetherBoard(playerPits, house, opponentPits);
        index = indexPits(playerPits, house, opponentPits, opponentHouse);
    }

    @Override
    public GameState toState() {
        Map<Integer, Integer> status = index.entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                (e) -> e.getValue().count()
                        )
                );
        state.setStatus(status);
        return state;
    }

    private static Map<Integer, Pit> indexPits(List<Pit> playable, Pit house, List<Pit> opponentPits, Pit opponentHouse) {
        List<Pit> components = ImmutableList.<Pit>builder()
                .addAll(playable)
                .add(house)
                .addAll(opponentPits)
                .add(opponentHouse)
                .build();
        return index(components);
    }

    private static Map<Integer, Pit> index(List<Pit> components) {
        return components.stream()
                .collect(Collectors.toMap(
                        Pit::getIdx,
                        (s) -> s
                ));
    }

    private Pit houseOf(Player player) {
        int houseIdx = player.house(boardSize);
        return new Pit(houseIdx, state.getStatus().get(houseIdx)).setOwner(player);
    }

    private static void putTogetherBoard(List<Pit> playablePits, Pit house, List<Pit> opponentPits) {
        List<Pit> sowable = ImmutableList.<Pit>builder()
                .addAll(playablePits)
                .add(house)
                .addAll(opponentPits)
                .build();
        connectAdjecent(sowable);
        connectOpposite(playablePits, opponentPits);
    }

    private static void connectAdjecent(List<Pit> sowable) {
        Pit last = sowable.get(sowable.size() - 1);
        for (Pit s: sowable) {
            last.setNext(s);
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
                .map(idx -> new Pit(idx, state.getStatus().get(idx)).setOwner(player))
                .collect(Collectors.toList());
    }

}
