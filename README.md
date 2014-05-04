Monty Hall Problem
==================

A simulation of the Monty Hall Problem ...

Problem description
-------------------
Assume that you are attending a TV show where you can win money by picking the right box. The game show host shows you
three boxes explaining that the money is in one of the boxes. He asks you to pick one of them without opening it. After you
have picked one of the boxes he opens one of the other two boxes which is empty. Now he turns to you and asks, do you want to
change your mind, picking the remaining box?

The Task
--------
Write a program in Java randomly simulating this event over and over again in the quest of answering following question. Do I
stand a better chance to win if I change my mind?

Technology Used
---------------

* Java 7
* Maven 3

Compiling the Solution
----------------------

From the directory containing this file, type:

    mvn clean package

Running the Solution
--------------------

By default, the solution will iterate 10,000 times for the case where the player does not swap
boxes when given the opportunity, followed by 10,000 iterations of the case where the player does swap boxes.

The number of executions can be changed by modifying the following line in the configuration section of the
_exec-maven-plugin_ plugin in the file _pom.xml_.

    <arguments>
      <argument>10000</argument>
    </arguments>

To run the simulation (from the directory containing this file), type:

    mvn exec:java

