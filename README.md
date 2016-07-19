# Invasion
/r/dailyprogrammer Hard challenge 270

Essentially given a map of '-' openings and 'X' for obstacles, find largest unobstructed square.

Method:

bottom and rightmost row can fit a 1x1 square and no more.
working upwards, any space (x,y) can be the top-left of a square as large as 1+the minumum of (x+1,y),(x,y+1),(x+1,y+1).

-----
|w|x|
-----
|y|z|
-----

w = 1+min(x,y,z)

find the space with the largest number and that number is the size of the largest square that can fit.