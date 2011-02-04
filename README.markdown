Theremin
========

Theremin is an implementation of a word game invented (to the best of
my knowledge) by Eryk Kopczyński. There can be any number of players,
although Theremin currently only supports two, one human and one
computer. One of the player starts by providing a letter. Then the
players take turns to prepend or append a letter to the sequence in
such a way that it is possible to extend the sequence to a valid
word. During his turn the player can also challenge the previous
player by saying that either 

1. the sequence cannot be extended to a valid word, or
2. the sequence is a valid word

In the first case the challenged player has to provide a valid word
that can be obtained from the sequence; if she is succesful, the
challenging player loses, otherwise the challenged player loses. In
the second case a ditionary is consulted and if the challenge is
succesful then the challenged player loses, otherwise the challenging
player loses.

Building
--------

Make sure you have [SBT](http://code.google.com/p/simple-build-tool/)
set up on your system. Then run:

    >git clone git://github.com/mmakowski/theremin.git
    >cd theremin
    >sbt package

After that completes you should end up with a directory
`theremin/target/scala_<version>` containing `theremin_<version>.jar`.

Usage
-----

You will need scala 2.8.1 (might work with later versions as well) to
run Theremin. Increase the heap size by setting `JAVA_OPTS`
environment variable, e.g. `JAVA_OPTS="-Xmx1g"`, run `scala -cp
theremin_<version>.jar com.mmakowski.theremin.ThereminApp`, then
follow instructions. The dictionaries currently provided with
Thereming are:

* `sjp` -- Polish 
* `sowpods` -- English

New dictionaries can be added by placing a file `<dictionary
name>.txt` containing the list of words delimited by CR LF in the
classpath.

Sample game
-----------

    >set JAVA_OPTS="-Xmx1g"
    >scala -cp theremin_2.8.1-0.1.0.jar com.mmakowski.theremin.ThereminApp
    choose the dictionary: sjp
    first letter: c
    _Rc_
    [p]repend(+l), [a]ppend(+l), challenge [n]o completion or challenge complete [w]ord: ae
    _rceR_
    [p]repend(+l), [a]ppend(+l), challenge [n]o completion or challenge complete [w]ord: ak
    _Arcerk_
    [p]repend(+l), [a]ppend(+l), challenge [n]o completion or challenge complete [w]ord: ph
    _harcerkA_
    [p]repend(+l), [a]ppend(+l), challenge [n]o completion or challenge complete [w]ord: w
    harcerka is a word -- you've won!
    >

