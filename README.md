# Simple QuakeML

Library to work with QuakeML.  Can fetch and parse QuakeML and return a simple object graph representing
event parameters.

## Common Uses

Fetch and transform QuakeML RT 1.2 from a URL:

```
SimpleQuakeML simpleQuakeML = new QuakeML_RT_1_2();
Event event = simpleQuakeML.read("http://quakeml.geonet.org.nz/quakeml-rt/1.2/2013p325188.xml");
```

Transform QuakeML RT 1.2 from a file:

```
File source = new File("2013p321497.xml");
SimpleQuakeML simpleQuakeML = new QuakeML_RT_1_2();
Event event = simpleQuakeML.read(new FileInputStream(source));
```