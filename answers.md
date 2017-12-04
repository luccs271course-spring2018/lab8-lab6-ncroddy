**Why does FixedArrayQueue require an explicit constructor?**
 The constructor is needed because without the constructer, the object for capacity could not be created and there would be an error. 
 
 **What happens when you poll an empty FixedArrayQueue?**
 Returns null
 
 **What happens when you offer an item to a full FixedArrayQueue?**
 Returns false
 
 **What is the time and (extra) space complexity of each of the FixedArrayQueue methods?**
 All of them operate at O(1) time except asList which is O(n) time dependent.
