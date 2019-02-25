How to use the Map Maker:

The grid on the left is a visual representation of the current level.
The grid on the right is a text representation of the level, which helps identify differences between blocks with the same look such 
as walls and movable walls.

The central menu is split into groups based on tile type, and further paired off into the tile's appearance in the light or the dark.
The game always starts in the light so bear this in mind as you create a level or the player may become invisible.



Menu:

Each red bordered group is a selectable button for a different tile. Each button shows both views of the tile, with the upper half of 
the button being the appearance at the start of the level (in light view), as well as the tile that gets placed in the map maker.
The lower half is to show what the tile will change to when a button is switched.


The top row of button groups (from left to right):
	Floor
	Permanent Wall
	Permanent Button
	Key (both light and dark versions)
	Door (both light and dark versions)
The bottom row of button groups (from left to right):
	Movable Portal (both light and dark versions)
	Movable Wall (both light and dark versions)
	Shifting Button (both light and dark versions)
	Shifting Wall (both light and dark versions)



Reset:

The reset button will reset the map to the default tile set of plain white floor surrounded by black walls.

Save Level:

This button will save a copy of the current level as a text file to the levelEditor.txt contained in this folder.



Using a Saved Level:

To add a created level to the game, copy the contents of levelEditor.txt, and paste it into levels.txt after all the other levels. Make sure
to maintain the formatting of:
---
##level array##
---
Also, bear in mind the game has a soft cap of 100 levels before it will return OutOfBounds errors and probably crash.