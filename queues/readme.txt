/* *****************************************************************************
 *  Name:    Estella yu
 *  NetID:   yingxian
 *  Precept: P08
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Hours to complete assignment (optional): 8
 *
 **************************************************************************** */

Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */
deque:
    data structure: doubly linked list
    reason: 1. does not need to resize array (resizing array is difficult,
                especially deque needs to support operations on both ends)
            2. singly linked list cannot solve the problem efficiently as well
                when element is removed at the "last" node, need to reassign to
                the previous node

randomized queue:
    data structure: resizable array
    reason: 1. since the class require random dequeue, using linked list will
                cost linear time, not constant time

/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

Randomized Queue:   ~  __8n___  bytes

Deque:              ~  _____  bytes




/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */




/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
