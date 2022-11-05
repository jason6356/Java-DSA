### Idea of the ADT

> Imagine in the math operation of Set, where Set does not allow any duplication element inside the Set

### Characteristic Of the ADT
- Similar with Bag but
- Does not allow repeated, or duplicate, entries.

### Behaviors
- Add item into the set
- Remove specific item from the set
- Remove random item from the set if possible
- Empty the set
- Check the set is empty
- Convert all the elements and make it into an array
- Check the entry is inside the set

### Pseudocode

```
//Get the number of entries inside the Set

getCurrentSize() : int

//Check the Set is Empty

isEmpty() : boolean

//Add a new entry to the set
//return true if success, else false

add(newEntry) : boolean

//Remove a specific entry from this set, if possible
//Returns true when success, false if not

remove(anEntry) : boolean

//Remove an element from the set
//Return null if the set is empty, else retrun the element removed from the set

remove() : T

//Clear all the elements from the set

clear() : void

//Check whether the set contains the entry
//Returns true if the entry is inside the set

contains(anEntry) : boolean

//Convert all the elements from the set into an Array
//Note : it would return empty array if the set is empty

toArray() : T[]

```


