package com.lunatech.battleship.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lunatech.battleship.datatransferobject.ChallengeRequestDTO;
import com.lunatech.battleship.datatransferobject.ChallengeResponseDTO;
import com.lunatech.battleship.domainobject.Game;
import com.lunatech.battleship.domainobject.Player;
import com.lunatech.battleship.service.GameService;
import com.lunatech.battleship.service.PlayerService;
import com.lunatech.battleship.service.RuleService;

@RestController
@RequestMapping("protocol")
public class ProtocolController
{

    private GameService gameService;

    private PlayerService playerService;

    private RuleService ruleService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createPlayer(@Valid @RequestBody ChallengeRequestDTO challengeRequestDTO)
    {

        Game game = new Game();
        game.setGameId(UUID.randomUUID().toString());
        game.setRule(this.ruleService.findByName(challengeRequestDTO.getRules()));

        Player player1 = new Player();
        player1.setFullname(challengeRequestDTO.getFullName());

        return new ResponseEntity<>(new ChallengeResponseDTO(), HttpStatus.CREATED);
    }
}
