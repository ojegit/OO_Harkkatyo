# Object oriented programming: final project

### TBA: add scoring table here

### Known bugs:
- Sometimes PERISHED state is not displayed in lutemon list view even though this is the case and it is registered by the move lutemons view
- cannot move more than one lutemon at a time between states/locations (`FIXED`)
- lutemons' stats information does not fit completely into left/right layout in battle view

### Not done:
- save/load statistics
- collect statistics (e.g no days trained, no deaths, damage done, no exp, no kills, no times trained etc; do all of this color specific)
- print graphs of statistics 
- graph statistics (e.g AnyChart package)
- save/load statistics on load
- randomization
- unit tests
- documentation and class diagrams to `/documentation` and `/documentation/ht.xmi`!
- finally: review points achieved, check for bugs and most importantly make sure the program works!
- training button at train move view
- restore also in perished move view
- when selecting lutemons to battle one is able to choose their order (aka there's a listener in the checkboxes that in addition to registering order also notifies the user that only 2 can be selected at a time or the order 
is selected afterwards (no listener required))
- turn based combat (OPTIONAL, request more points)
- make it possible to load lutemon image from file (OPTIONAL, request more points)
### Done: 
- main view
- a) loading of objects in different fragments based or b) state or information passing between fragment
- fragment functionality /w viewpager2 and tab
- save / load of storage
- implement recycleview
- add pictures to each lutemon (currently fixed, but different nonetheless)
- implement fragments
- combat graphics (tip: use the fixed images here since they're already included in the objects)
- restore perished lutemons
#
### Changes:
#### 07/19/2023
- added recycle view that is capable of showing multiple layouts to battle display
- added revive button to list view: when lutemon is at PERISHED state then a button to revive lutemon back to max health becomes visible; the revived lutemon is moved back HOME
#### 07/18/2023
- fixed a bug not being able to switch states of more than one lutemon at a time (fixed by using id's to identify the checkboxes when removing them instead of int based identifier (which simialary to a list or array list doesn't work when the contents is changed; unique id is immune to this))
- added dialogue verification for lutemon permanent deletion
- added toast reminders when adding new lutemons and moving/changing states
- resetting all radio buttons when buttons are pressed
- added some battle arena functionalities:
    * selection of fighters (move state to BATTLE)
    * added textview in order to show text based information about the battle
#### 07/17/2023
- implemented load and save features for storage 
- decided to apply lutemon mover in separate page instead of within fragments. this realization enables the use of enum 
since fragment to fragment direct communication is no longer needed 
- added times created to each lutemon that is also used as their id's in the storage hashmap
- added states of color and location for Lutemons
- as result of implementing states/locations all objects are loaded from main storage and allocated to each fragment on load fragment load
- changed storage hashmap keys from integers to string which is based on the created object instance time in milliseconds

#### 07/15/2023
- began implementing statistics collection
- Main GUI pages: initialized, add page
- TabView2 & ViewPager2 added to move Lutemon page
- RecycleView added to list Lutemon page
- Add Lutemon page working and showing all generater instance on list Lutemon page
- Lutemon class and its subclasses added
- Storage singleton added
- Drawable picture reference added to Lutemon subclasses, not compulsory
- Included fragments: initialized
- Lutemons: storage, super class and subclasses done
- Functionalities: not done
- Additional features that you want additional points from: 
    * sorting list based on lutemon's abilities (string: name, int: power, defence, health)
    * sorting list based on time added (this field needs to be added)