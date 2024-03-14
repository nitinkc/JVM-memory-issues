A common example of a memory leak in Java involves the misuse of collections, 
particularly when objects are added to collections and never removed, 
preventing them from being garbage collected.

Memory Leaks: Create scenarios where objects are not properly released from memory, leading to memory leaks.
Out of Memory Errors: Trigger OutOfMemoryError by allocating more memory than available or by creating large objects.
Garbage Collection Issues: Analyze garbage collection logs and observe the behavior of the garbage collector under different scenarios.
Memory Profiling: Use tools like VisualVM or YourKit to analyze memory usage and identify memory hotspots.
Heap Dumps: Generate heap dumps and analyze them to identify memory-consuming objects and potential leaks.

