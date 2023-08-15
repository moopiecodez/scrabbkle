# Description of the coursework: ScraBBKle

## Intro

This is a simplified console based version of scrabble.  The game will be played between a **Human player** (the user) and a **Computer player**.  However, the board size will be `S x S`, where `S` is an integer number between `12` and `26`, instead of the usual `15 x 15`. (We only set the limit `26`  to avoid visualisation/printing problems for large boards.)

The rules of ScraBBKle have been inspired by the ones available [here](https://www.hasbro.com/common/instruct/Scrabble_\(2003\).pdf) for the Scrabble game.

When the game is started, the user is asked whether to load a specific board from the file system or whether to use the default board. The default board corresponds to the Scrabble board.

After that, both players get 7 randomly chosen tiles from the tile bag. The human player is then shown the (currently empty) board and their own tiles. The human player plays the first move.

## Gameplay

1.
The first move is done by the human player, by combining two or more of their letters to a word. The word is placed on the board to read either to the right or downwards, with one letter on the centre square. Like in Scrabble, diagonal words are not possible in ScraBBKle. Whenever a tile is placed on a square, the letter and the value of the tile replace the square on the board. If the value `S` from the intro is an even number, there are four candidates for the role of "centre square". In ScraBBKle, the top left of these four squares (i.e., the one with minimal column and row) is the centre square for the purposes of the first move.

2.
The game computes the score resulting from the move. As long as the tile bag is not empty, the player who just made a move will have their tile rack topped up with tiles taken from the tile bag so that it has 7 tiles again.

3.
The next move is the computer player's. From now on, the computer player and the human player take turns with their moves until one player has no more tiles on their rack or no more moves are possible. Before each move, the program prints the current score and the current board.

4.
The player whose turn it is adds one or more letters to the letters already on the board to form a new word. All letters played in a move must be placed in one row to the right or downwards and contribute to a new word. It is allowed to skip occupied positions (for example, one may extend `NO` to `SNOW` by adding a `S` at the beginning and at the same time a `W` at the end). In ScraBBKle, every move may lead to only one occurrence of a new or changed word on the board. In contrast to the original Scrabble game, it is not allowed to place a word at right angles to a word already on the board without an overlap, nor to place a complete word parallel immediately next to a word already played.

5.
Once a tile has been used in a move, its position on the board stays unchanged.

6.
There are special tiles, called wildcards or blanks, where the letter is initially not given, but can be chosen by the player as needed. In our game, the choice is entered as a small letter. As soon as the choice has been made for a given wildcard, it stays fixed for the rest of the game. The value of a wildcard in ScraBBKle is `3`. When a wildcard has been played, its letter used for display on the board is the chosen small letter - the choice lasts for the whole game.

7.
A player is allowed to pass the current turn (i.e., not make a move and allow the next player to continue).

8.
In ScraBBKle, it is not allowed to exchange tiles from the tile rack with tiles from the tile bag.

9.
Moves must always lead to words that are in the game's word list. (This is checked by the game, and invalid moves are rejected by the game.)

10.
The game ends when the tile bag is empty and one of the player has an empty tile rack. The game also ends if both players pass twice in a row.


## Scoring

1.
The score for each move is calculated as the sum of the letter values in the word created or modified in the move, plus the extra points obtained (or lost!) from tiles placed on *premium squares*.

2.
**Premium letter squares** have an integer number of at least `-9` and at most `99` as a factor (specifically, 0 or negative values are possible). When a tile is placed on a premium letter square, the score for the tile is its value multiplied by the factor of the premium letter square. A premium letter square has the shape `(x)` if the factor `x` is a single character and the shape `(xy` if the factor `xy` has two characters.

3.
**Premium word squares** also have an integer number of at least `-9` and at most `99` as a factor (specifically, 0 or negative values are possible). When a move places a tile on a premium word square, the factor of the premium word square will be multiplied with the score obtained for the word otherwise. If a move uses several premium letter squares, the effect is cumulative (for example, when we use a premium word square with factor `4` and a second premium word square with factor `5` in the same move, the resulting factor for the word score would be `4*5 = 20`). Premium word squares are applied only *after* premium letter squares. A premium word square has the shape `{x}` if the factor `x` is a single character and the shape `{xy` if the factor `xy` has two characters.

4.
A square cannot be at the same time both a premium letter square and a premium word square. There can also be squares that are not premium letter squares or premium word squares. Such squares are displayed as ` . ` (i.e., a space, then a dot, then a space).

5.
Letter and word premium squares are applied only in a single move. As soon as they have been covered by a tile, in later moves this tile will count at its face value (i.e., the score will not be affected by the premium formerly obtained from covering the tile).

6.
Woo-hoo! If a player manages to play all 7 tiles in one move, they are awarded an extra score of 70 points in ScraBBKle. This extra score is added only after all the other calculations for the current move are done (so also with a premium word square involved in the move, the player would still get only 70 extra score points).

7.
At the end of the game, at least one player will have unplayed tiles. Each player's score is reduced by the sum of the values of their own unplayed tiles.


## Winning player

The player who has a higher score at the end of the game wins. If the scores are equal, the game is declared a draw.



## Board files format

The **first line** of a file for a ScraBBKle board stores a integer number `S` between `12` and `26`. This number indicates the size of our `S x S` ScraBBKle board.

After the first line, there are `S` further lines. Each of these lines consists of exactly `S` of the following "tokens", each of which represents a square:

- `.` represents a standard square without premium.
- `(n)` represents a letter premium square, where `n` is an integer between `-9` and `99`.
- `{n}` represents a word premium square, where `n` is an integer between `-9` and `99`.

Note that in our file format, a premium square may need either 3 or 4 characters. Our file format does not allow for spaces.



### Computer player

The computer player is implemented such that it will always make a move with at least one tile whenever such a move is possible with the given tile rack, board state, and word list.
