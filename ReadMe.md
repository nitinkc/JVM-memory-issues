## VM Options
```shell
-XX:+HeapDumpOnOutOfMemoryError
-Xmx100m
-XX:HeapDumpPath=heapdump/
```

# Memory Leak 
a situation where objects that are no longer needed by the application are not properly released from memory, 
leading to a gradual accumulation of memory usage over time

A common example of a memory leak in Java involves the **misuse of collections**, 
particularly when **objects are added to collections and never removed**, 
preventing them from being garbage collected.

# Out of Memory Errors[settings.gradle](settings.gradle)

## Garbage Collection Issues

Analyze garbage collection logs and observe the behavior of the garbage collector under different scenarios.

## Memory Profiling
Use tools like VisualVM or YourKit to analyze memory usage and identify memory hotspots.

## Heap Dumps
Generate heap dumps and analyze them to identify memory-consuming objects and potential leaks.

**Improper Garbage Collection**: Java has a garbage collector (GC) that automatically 
frees up memory by reclaiming objects that are no longer reachable. 
owever, if objects are inadvertently kept reachable by strong references beyond their 
useful lifetime, they won't be garbage collected, leading to memory leaks.

**Static References**: Static fields hold references to objects for the entire lifecycle of the application. 
If static fields reference objects that should be short-lived, these objects won't be 
garbage collected until the application is shut down, potentially leading to memory leaks.

**Thread Local Variable**s: In multithreaded applications, thread local variables 
can hold references to objects for the duration of the thread's lifetime. 
If thread local variables are not properly cleared when the thread finishes its work, it can cause memory leaks.

**Resource Management**: In Spring Boot applications, improper management of resources 
such as database connections, file handles, or network sockets can lead to resource leaks,
which indirectly contribute to memory leaks.

**Caching**: While caching can improve performance, it can also lead to memory leaks 
if cached objects are not cleared when they are no longer needed.

**Session Management**: In web applications, session objects can be stored in memory. 
If session objects are not properly invalidated or expired, they can accumulate in memory and cause memory leaks.

To prevent memory leaks in Java and Spring Boot applications, it's essential to:

Properly manage object lifecycle and ensure that objects are released when they are no longer needed.
Avoid unnecessary use of static fields, thread local variables, and excessive caching.
Use tools like memory profilers to identify and diagnose memory leaks.
Monitor application memory usage and performance regularly to detect any anomalies.

```shell
sh ~/Programming/buggyapp-latest/launch.sh
```

http://localhost:9010/

## Documentation
(http://localhost:8080/swagger-ui/index.html#/app-controller)[http://localhost:8080/swagger-ui/index.html#/app-controller]

##### Actuator
(http://localhost:8080/actuator)[http://localhost:8080/actuator]