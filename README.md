# Object oriented programming: final project
### Not done:
- save/load of statistics
- combat graphics
- collect statistics (e.g no days trained, no deaths, damage done, no exp, no kills, no times trained etc; do all of this color specific)
- print graphs of statistics 
- restore perished lutemons
- graph statistics (e.g AnyChart package)
- save/load statistics on load
- randomization
- unit tests
- documentation and class diagrams to `/documentation` and `/documentation/ht.xmi`!
- finally: review points achieved, check for bugs and most importantly make sure the program works!
### Done: 
- main view
- a) loading of objects in different fragments based or b) state or information passing between fragment
- fragment functionality /w viewpager2 and tab
- save / load of storage
#
### Changes:
#### 07/16/2023
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