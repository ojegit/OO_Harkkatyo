# Object oriented programming: final project
### Changes:
#### v1.0.2
- added times created to each lutemon that is also used as their id's in the storage hashmap
- added states of color and location for Lutemons
- got rid of sending objects across fragments: all objects are loaded from main storage and allocated to each fragment on load based on state
- changed storage hashmap keys from integers to string which is based on the created object instance time in milliseconds

#### v1.0.1
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
### Version: x.x.x

- Finally: add proper documentation and class diagrams to `/documentation` and `/documentation/ht.xmi`!